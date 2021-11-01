package com.company;/*
Class for managing the connection to the database and any queries
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.*;

public class ITIS
{
    // Creates the connection
    private Connection connect()
    {
        // Connection datatype is used to establish a connection with the database
        Connection conn = null;
        try
        {
            // Location of database, database in project root so only the name is needed
            String url = "jdbc:sqlite:ITIS.sqlite";

            // Creates the connection
            conn = DriverManager.getConnection(url);
           // System.out.println("Database connection successful");
        }
        catch(SQLException e)
        {
            // Print issue on failure rather than just crashing
            System.out.println(e.getMessage());
        }
        // Return connection for use in queries
        return conn;
    }

    // Sends query
    private ResultSet runQuery(String queryString, String searchTerm)
    {
        // Declare Variables
        Connection conn;
        ResultSet rs = null;
        PreparedStatement preparedStatement;

        // Run Query
        try
        {
            // Establish connection
            conn = this.connect();

            // Prepare Statement and execute query
            preparedStatement = conn.prepareStatement(queryString);
            preparedStatement.setString(1, searchTerm);
            rs = preparedStatement.executeQuery();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    // Convert ResultSet to  SearchResult object
    public HashMap<Integer, SearchResults> toSearchResult(ResultSet resultSet) throws SQLException
    {
        // Declare Variables
        HashMap<Integer, SearchResults> resultsHashMap = new HashMap<Integer, SearchResults>();
        String taxonomicName;
        String commonName;
        String itisSerialNumber;
        String kingdomID;
        String kingdomName;
        String hierarchyString;
        SearchResults currentResult;
        HashMap<String, String> hierarchy;

        // Create Results objects, without hierarchy
        if(resultSet.next())
        {
            int index = 0;
            do
            {
                // Get information from each row
                itisSerialNumber = resultSet.getString("tsn");
                taxonomicName = resultSet.getString("completename");
                commonName = resultSet.getString("vernacular_name");
                kingdomName = resultSet.getString("kingdom_name");
                kingdomID = resultSet.getString("kingdom_id");
                hierarchyString = resultSet.getString("hierarchy_string");

                // Create SearchResults object
                SearchResults result = new SearchResults(itisSerialNumber, taxonomicName, commonName, kingdomName, kingdomID, hierarchyString,null);
                resultsHashMap.put(index, result);
                index++;

            } while (resultSet.next());
            for(int i=0; i<resultsHashMap.size(); i++)
            {
                currentResult = resultsHashMap.get(i);
                kingdomName = currentResult.kingdomName;
                kingdomID = currentResult.kingdomID;
                hierarchyString = currentResult.hierarchyString;
                currentResult.hierarchy = getHierarchy(hierarchyString, kingdomName, kingdomID);
            }
        }
        else
        {
            resultsHashMap = null;
        }
        return resultsHashMap;
    }

    // Change Hierarchy String to array and retrieve rank of each part of string
    public HashMap<String, String> getHierarchy(String hierarchyString, String kingdomName, String kingdomID) throws SQLException {
        // Define variables
        final String RANK_QUERY;
        String[] hierarchyNames;
        String[] hierarchyRanks;
        HashMap<String, String> hierarchyWithRank = new HashMap<>();
        ResultSet searchResult;

        RANK_QUERY = "SELECT rank_id FROM taxonomic_units WHERE tsn=?";

        if(hierarchyString != null)
        {
            // Split string into array
            hierarchyNames = hierarchyString.split("-");
            // Find rank ID based on hierarchy array
            hierarchyRanks = new String[hierarchyNames.length];
            for(int i=0; i < hierarchyNames.length; i++)
            {
                searchResult = runQuery(RANK_QUERY, hierarchyNames[i]);
                hierarchyRanks[i] = searchResult.getString("rank_id");
            }

            // Translate to words
            hierarchyWithRank = convertHierarchyString(hierarchyRanks, hierarchyNames, kingdomName, kingdomID);
            
            // Return to toSearchResults
            return hierarchyWithRank;
        }else{return null;}
    }

    // Turn serial numbers into latin names and rank id into rank
    public HashMap<String, String> convertHierarchyString (String[] hierarchyRanks, String[] hierarchyNames, String kingdomName, String kingdomID) throws SQLException {
        // TODO implement queries for longnames.completename for each part of hierarchy_string and taxon_unit_types.rank_name for each rank id
        // Define Variables
        final String NAME_QUERY;
        final String RANK_QUERY;
        HashMap<String, String> hierarchyWithRank = new HashMap<>();
        ResultSet currentResults;
        String currentRank;
        String currentName;
        String kingdom;

        NAME_QUERY = "SELECT completename FROM longnames WHERE tsn=?";

        // Get Kingdom Name and add to HashMap
        hierarchyWithRank.put("Kingdom", kingdomName);

        // Set RANK_QUERY
        RANK_QUERY = "SELECT rank_name FROM taxon_unit_types WHERE rank_id=? AND kingdom_id=" + kingdomID;

        // Run Queries for the rest of the ranks
        for(int i=1; i<hierarchyNames.length; i++)
        {
            // Run rank Query
            currentResults = runQuery(RANK_QUERY, hierarchyRanks[i]);
            currentRank = currentResults.getString("rank_name");

            // Run name Query
            currentResults = runQuery(NAME_QUERY, hierarchyNames[i]);
            currentName = currentResults.getString("completename");

            // Put in HashMap
            hierarchyWithRank.put(currentRank, currentName);
        }
        // return to getHierarchy
        return hierarchyWithRank;
    }

    // TODO Rewrite Query functions to use smaller specialized functions
    // Get info based on ITIS SN
    public ArrayList<ArrayList<SearchResults>> itisSerialNumberSearch(String searchTerm) throws SQLException
    {
        // Queries
        final String QUERY_STRING;

        // Results
        ResultSet queryResults = null;
        HashMap<Integer, SearchResults> masterResultsHashMap;
        ArrayList<SearchResults> animaliaHashMap = new ArrayList<>();
        ArrayList<SearchResults> archaeaHashMap = new ArrayList<>();
        ArrayList<SearchResults> bacteriaHashMap = new ArrayList<>();
        ArrayList<SearchResults> chromistaHashMap = new ArrayList<>();
        ArrayList<SearchResults> fungiHashMap = new ArrayList<>();
        ArrayList<SearchResults> plantaeHashMap = new ArrayList<>();
        ArrayList<SearchResults> protozoaHashMap = new ArrayList<>();
        @SuppressWarnings("rawtypes")
        ArrayList<ArrayList<SearchResults>> data = new ArrayList<>();
        // Add % to searchTerm
        searchTerm = "%" + searchTerm + "%";

        // Query for names and serial number
        QUERY_STRING = "SELECT longnames.tsn, longnames.completename, vernaculars.vernacular_name, taxonomic_units.kingdom_id, kingdoms.kingdom_name, hierarchy.hierarchy_string\n" +
                "FROM longnames \n" +
                "LEFT JOIN vernaculars on longnames.tsn = vernaculars.tsn\n" +
                "LEFT JOIN taxonomic_units on longnames.tsn = taxonomic_units.tsn \n" +
                "LEFT JOIN kingdoms on taxonomic_units.kingdom_id = kingdoms.kingdom_id\n" +
                "LEFT JOIN hierarchy on longnames.tsn = hierarchy.TSN\n" +
                "WHERE longnames.tsn LIKE ?\n" +
                "AND (vernaculars.language = 'English' OR vernaculars.vernacular_name is NULL);";

        // Run Query
        //System.out.println("***Running query for search term " + searchTerm + " using TSN search");
        queryResults = runQuery(QUERY_STRING, searchTerm);

        // Convert to SearchResult
        // TODO prevent null pointer exception
        masterResultsHashMap = toSearchResult(queryResults);


        // Convert results objects to the proper type based on Kingdom
        for(int i=0; i<=masterResultsHashMap.size()-1; i++)
        {
            SearchResults currentResult = masterResultsHashMap.get(i);
            System.out.println(currentResult.kingdomName);
            switch(currentResult.kingdomName.toString()){
                case "Animalia" -> {animaliaHashMap.add(currentResult);}
                case "Archaea" -> {archaeaHashMap.add(currentResult);}
                case "Bacteria" -> {bacteriaHashMap.add(currentResult);}
                case "Chromista" -> {chromistaHashMap.add(currentResult);}
                case "Fungi" -> {fungiHashMap.add(currentResult);}
                case "Plantae" -> {plantaeHashMap.add(currentResult);}
                case "Protoza" -> {protozoaHashMap.add(currentResult);}
            }
        }

        // Update data with all results
        data.add(0, animaliaHashMap);
        data.add(1, archaeaHashMap);
        data.add(2, bacteriaHashMap);
        data.add(3, chromistaHashMap);
        data.add(4, fungiHashMap);
        data.add(5, plantaeHashMap);
        data.add(6, protozoaHashMap);
        return data;
    }

    // Get info based on common name
    public ObservableList<Animalia> commonNameSearch(String searchTerm) throws SQLException
    {
        // Declare variables
        // Query
        String queryString;

        // Results
        ResultSet queryResults = null;
        HashMap<Integer, SearchResults> resultsHashMap = new HashMap<Integer, SearchResults>();
        String taxonomicName;
        String commonName;
        String itisSerialNumber;
        HashMap<Integer, Animalia> animaliaHashMap = new HashMap<Integer, Animalia>();

        // Add % to searchTerm
        searchTerm = "%" + searchTerm + "%";

        // Query for names and serial number
        queryString = "SELECT longnames.tsn, longnames.completename, vernaculars.vernacular_name\n" +
                "FROM longnames LEFT JOIN vernaculars on longnames.tsn = vernaculars.tsn\n" +
                "WHERE vernaculars.vernacular_name LIKE ?\n" +
                "AND vernaculars.language = 'English';";

        // Run Query
        //System.out.println("***Running query for search term " + searchTerm + " using common name search");
        queryResults = runQuery(queryString, searchTerm);


        if(queryResults.next())
        {
            int index = 0;
            do
            {
                // Get information from each row
                itisSerialNumber = queryResults.getString("tsn");
                taxonomicName = queryResults.getString("completename");
                commonName = queryResults.getString("vernacular_name");

                // Create SearchResults object
                SearchResults result = new SearchResults(itisSerialNumber, taxonomicName, commonName, null, null, null, null);
                resultsHashMap.put(index, result);
                index++;
            } while (queryResults.next());
        }
        else
        {
            //System.out.println("No Results");
        }

        for(int i=0; i<=resultsHashMap.size()-1; i++)
        {
            SearchResults currentResult = resultsHashMap.get(i);
            animaliaHashMap.put(i, currentResult.toAnimalia());
            /*
            System.out.println("ITIS Serial Number: " + currentResult.itisSerialNumber);
            System.out.println("Taxonomic Name: " + currentResult.taxonomicName);
            System.out.println("Common name: " + currentResult.commonName);
             */
        }
        final ObservableList<Animalia> data = FXCollections.observableArrayList();
        for(int i=0; i<=animaliaHashMap.size()-1; i++){
            Animalia currentResult = animaliaHashMap.get(i);
            data.add(currentResult);
        }
        return data;
    }

    // Get info based on taxonomic name
    public ObservableList<Animalia> taxonomicNameSearch(String searchTerm) throws SQLException
    {
        // Declare variables
        // Query
        String queryString;

        // Results
        ResultSet queryResults = null;
        HashMap<Integer, SearchResults> resultsHashMap = new HashMap<Integer, SearchResults>();
        String taxonomicName;
        String commonName;
        String itisSerialNumber;
        HashMap<Integer, Animalia> animaliaHashMap = new HashMap<Integer, Animalia>();

        // Add % to searchTerm
        searchTerm = "%" + searchTerm + "%";

        // Query for names and serial number
        queryString = "SELECT longnames.tsn, longnames.completename, vernaculars.vernacular_name\n" +
                "FROM longnames LEFT JOIN vernaculars on longnames.tsn = vernaculars.tsn\n" +
                "WHERE longnames.completename LIKE ?\n" +
                "AND (vernaculars.language = 'English' OR vernaculars.vernacular_name IS NULL);";

        // Run Query
        //System.out.println("***Running query for search term " + searchTerm + " using scientific name search");
        queryResults = runQuery(queryString, searchTerm);


        if(queryResults.next())
        {
            int index = 0;
            do
            {
                // Get information from each row
                itisSerialNumber = queryResults.getString("tsn");
                taxonomicName = queryResults.getString("completename");
                commonName = queryResults.getString("vernacular_name");

                // Create SearchResults object
                SearchResults result = new SearchResults(itisSerialNumber, taxonomicName, commonName, null, null, null, null);
                resultsHashMap.put(index, result);
                index++;
            } while (queryResults.next());
        }
        else
        {
            //System.out.println("No Results");
        }

        for(int i=0; i<=resultsHashMap.size()-1; i++)
        {
            SearchResults currentResult = resultsHashMap.get(i);
            animaliaHashMap.put(i, currentResult.toAnimalia());
            /*
            System.out.println("ITIS Serial Number: " + currentResult.itisSerialNumber);
            System.out.println("Taxonomic Name: " + currentResult.taxonomicName);
            System.out.println("Common name: " + currentResult.commonName);
             */
        }
        final ObservableList<Animalia> data = FXCollections.observableArrayList();
        for(int i=0; i<=animaliaHashMap.size()-1; i++){
            Animalia currentResult = animaliaHashMap.get(i);
            data.add(currentResult);
        }
        return data;
    }
}

