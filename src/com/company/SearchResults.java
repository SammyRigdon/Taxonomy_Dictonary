package com.company;

import java.util.HashMap;

public class SearchResults
{
    String itisSerialNumber;
    String taxonomicName;
    String commonName;
    String kingdomName;
    String kingdomID;
    String hierarchyString;
    HashMap<String, String> hierarchy;

    public SearchResults(String itisSerialNumber, String taxonomicName, String commonName, String kingdomName,
                         String kingdomID, String hierarchyString, HashMap<String, String> hierarchy)
    {
        this.itisSerialNumber = itisSerialNumber;
        this.taxonomicName = taxonomicName;
        this.commonName = commonName;
        this.kingdomName = kingdomName;
        this.kingdomID = kingdomID;
        this.hierarchyString = hierarchyString;
        this.hierarchy = hierarchy;
    }

    public String getTaxonomicName() {return taxonomicName;}
    public String getItisSerialNumber() {return itisSerialNumber;}
    public String getCommonName() {return commonName;}
    public HashMap<String, String> getHierarchy() {return hierarchy;}

    public void setCommonName(String commonName) {this.commonName = commonName;}
    public void setItisSerialNumber(String itisSerialNumber) {this.itisSerialNumber = itisSerialNumber;}
    public void setTaxonomicName(String taxonomicName) {this.taxonomicName = taxonomicName;}
    public void setHierarchy(HashMap<String, String> hierarchy) {this.hierarchy = hierarchy;}

    public Animalia toAnimalia() {
        // Create new object and return
        // TODO Seperate hiererchy HashMap into strings for Animalia object
        if(this.hierarchy != null)
        {
            return new Animalia(this.itisSerialNumber, this.taxonomicName, this.commonName, this.kingdomName,
                    this.hierarchy.get("Subkingdom"), this.hierarchy.get("Infrakingdom"), this.hierarchy.get("Superphylum"),
                    this.hierarchy.get("Pyhlum"), this.hierarchy.get("Subphylum"), this.hierarchy.get("Infraphylum"),
                    this.hierarchy.get("Superclass"), this.hierarchy.get("Class"), this.hierarchy.get("Subclass"),
                    this.hierarchy.get("Infraclass"), this.hierarchy.get("Superorder"), this.hierarchy.get("Order"),
                    this.hierarchy.get("Suborder"), this.hierarchy.get("Infraorder"), this.hierarchy.get("Section"),
                    this.hierarchy.get("Subsection"), this.hierarchy.get("Superfamily"), this.hierarchy.get("Family"),
                    this.hierarchy.get("Subfamily"), this.hierarchy.get("Tribe"), this.hierarchy.get("Subtribe"),
                    this.hierarchy.get("Genus"), this.hierarchy.get("Subgenus"), this.hierarchy.get("Species"),
                    this.hierarchy.get("Subspecies"), this.hierarchy.get("Variety"), this.hierarchy.get("Form"),
                    this.hierarchy.get("Race"), this.hierarchy.get("Strip"), this.hierarchy.get("Morph"));
        }
        else{return new Animalia(this.itisSerialNumber, this.taxonomicName, this.commonName);}
    }
}
