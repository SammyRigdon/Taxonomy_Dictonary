package com.company;/*
Taxonomy Dictionary
Created by Sammy Rigdon, started on 04/02/21
Taxonomy Dictionary allows the user to find the common english name and full taxonomy for any species using the ITIS database
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.SQLException;

public class TaxonomyDictionary extends Application {
    // Create nodes with events
    Button searchButton = new Button("Search");
    TextField searchTermEntry = new TextField();
    ToggleGroup searchType = new ToggleGroup();
    RadioButton itisSerialNumberButton = new RadioButton("ITIS Serial Number");
    RadioButton commonNameButton = new RadioButton("Common Name");
    RadioButton taxonomicNameButton = new RadioButton("Scientific Name");

    // TableView for each Kingdom
    TableView<Animalia> animaliaTableView = new TableView<Animalia>();
    TableView<Archaea> archaeaTableView = new TableView<Archaea>();
    TableView<Bacteria> bacteriaTableView = new TableView<Bacteria>();
    TableView<Chromista> chromistaTableView = new TableView<Chromista>();
    TableView<Fungi> fungiTableView = new TableView<Fungi>();
    TableView<Plantae> plantaeTableView = new TableView<Plantae>();
    TableView<Protozoa> protozoaTableView = new TableView<Protozoa>();

    // Animalia Columns
    TableColumn<Animalia, String> animaliaItisSerialColumn = new TableColumn<Animalia, String>("ITIS Serial Number");
    TableColumn<Animalia, String> animaliaCommonNameColumn = new TableColumn<Animalia, String>("Common Name");
    TableColumn<Animalia, String> animaliaTaxonomicNameColumn = new TableColumn<Animalia, String>("Scientific Name");
    TableColumn<Animalia, String> animaliaSubkingdomColumn = new TableColumn<Animalia, String>("Subkingdom");
    TableColumn<Animalia, String> animaliaInfrakingdomColumn = new TableColumn<Animalia, String>("Infrakingdom");
    TableColumn<Animalia, String> animaliaSuperphylumColumn = new TableColumn<Animalia, String>("Superphylum");
    TableColumn<Animalia, String> animaliaPhylumColumn = new TableColumn<Animalia, String>("Phylum");
    TableColumn<Animalia, String> animaliaSubphylumColumn = new TableColumn<Animalia, String>("Subphylum");
    TableColumn<Animalia, String> animaliaInfraphylumColumn = new TableColumn<Animalia, String>("Infraphylum");
    TableColumn<Animalia, String> animaliaSuperclassColumn = new TableColumn<Animalia, String>("Superclass");
    TableColumn<Animalia, String> animaliaClassColumn = new TableColumn<Animalia, String>("Class");
    TableColumn<Animalia, String> animaliaSubclassColumn = new TableColumn<Animalia, String>("Subclass");
    TableColumn<Animalia, String> animaliaInfraclassColumn = new TableColumn<Animalia, String>("Infraclass");
    TableColumn<Animalia, String> animaliaSuperorderColumn = new TableColumn<Animalia, String>("Superorder");
    TableColumn<Animalia, String> animaliaOrderColumn = new TableColumn<Animalia, String>("Order");
    TableColumn<Animalia, String> animaliaSuborderColumn = new TableColumn<Animalia, String>("Suborder");
    TableColumn<Animalia, String> animaliaInfraorderColumn = new TableColumn<Animalia, String>("Infraorder");
    TableColumn<Animalia, String> animaliaSectionColumn = new TableColumn<Animalia, String>("Section");
    TableColumn<Animalia, String> animaliaSubsectionColumn = new TableColumn<Animalia, String>("Subsection");
    TableColumn<Animalia, String> animaliaSuperfamilyColumn = new TableColumn<Animalia, String>("Superfamily");
    TableColumn<Animalia, String> animaliaFamilyColumn = new TableColumn<Animalia, String>("Family");
    TableColumn<Animalia, String> animaliaSubfamilyColumn = new TableColumn<Animalia, String>("Subfamily");
    TableColumn<Animalia, String> animaliaTribeColumn = new TableColumn<Animalia, String>("Tribe");
    TableColumn<Animalia, String> animaliaSubtribeColumn = new TableColumn<Animalia, String>("Subtribe");
    TableColumn<Animalia, String> animaliaGenusColumn = new TableColumn<Animalia, String>("Genus");
    TableColumn<Animalia, String> animaliaSubgenusColumn = new TableColumn<Animalia, String>("Subgenus");
    TableColumn<Animalia, String> animaliaSpeciesColumn = new TableColumn<Animalia, String>("Species");
    TableColumn<Animalia, String> animaliaSubspeciesColumn = new TableColumn<Animalia, String>("Subspecies");
    TableColumn<Animalia, String> animaliaVarietyColumn = new TableColumn<Animalia, String>("Variety");
    TableColumn<Animalia, String> animaliaFormColumn = new TableColumn<Animalia, String>("Form");
    TableColumn<Animalia, String> animaliaRaceColumn = new TableColumn<Animalia, String>("Race");
    TableColumn<Animalia, String> animaliaStripColumn = new TableColumn<Animalia, String>("Strip");
    TableColumn<Animalia, String> animaliaMorphColumn = new TableColumn<Animalia, String>("Morph");

    // Archaea Columns

    // Bacteria Columns

    // Chromista Columns

    // Fungi Columns

    // Plantae Columns
    TableColumn<Plantae, String> plantaeItisSerialColumn = new TableColumn<Plantae, String>("ITIS Serial Number");
    TableColumn<Plantae, String> plantaeCommonNameColumn = new TableColumn<Plantae, String>("Common Name");
    TableColumn<Plantae, String> plantaeTaxonomicNameColumn = new TableColumn<Plantae, String>("Scientific Name");
    TableColumn<Plantae, String> plantaeSubkingdomColumn = new TableColumn<Plantae,String >("Subkingdom");
    TableColumn<Plantae, String> plantaeInfrakingdomColumn = new TableColumn<Plantae, String>("Infrakingdom");
    TableColumn<Plantae, String> plantaeSuperdivisionColumn = new TableColumn<Plantae, String>("Superdivision");
    TableColumn<Plantae, String> plantaeDivisionColumn = new TableColumn<Plantae, String>("Division");
    TableColumn<Plantae, String> plantaeSubdivisionColumn = new TableColumn<Plantae, String>("Subdivision");
    TableColumn<Plantae, String> plantaeInfradivisionColumn = new TableColumn<Plantae, String>("Infradivision");
    TableColumn<Plantae, String> plantaeSuperclassColumn = new TableColumn<Plantae, String>("Superclass");
    TableColumn<Plantae, String> plantaeClassColumn = new TableColumn<Plantae, String>("Class");
    TableColumn<Plantae, String> plantaeSubclassColumn = new TableColumn<Plantae, String>("Subclass");
    TableColumn<Plantae, String> plantaeInfraclassColumn = new TableColumn<Plantae, String>("Infraclass");
    TableColumn<Plantae, String> plantaeSuperorderColumn = new TableColumn<Plantae, String>("Superorder");
    TableColumn<Plantae, String> plantaeOrderColumn = new TableColumn<Plantae, String>("Order");
    TableColumn<Plantae, String> plantaeSuborderColumn = new TableColumn<Plantae, String>("Suborder");
    TableColumn<Plantae, String> plantaeFamilyColumn = new TableColumn<Plantae, String>("Family");
    TableColumn<Plantae, String> plantaeSubfamilyColumn = new TableColumn<Plantae, String>("Subfamily");
    TableColumn<Plantae, String> plantaeTribeColumn = new TableColumn<Plantae, String>("Tribe");
    TableColumn<Plantae, String> plantaeSubtribeColumn = new TableColumn<Plantae, String>("Subtribe");
    TableColumn<Plantae, String> plantaeGenusColumn = new TableColumn<Plantae, String>("Genus");
    TableColumn<Plantae, String> plantaeSubgenusColumn = new TableColumn<Plantae, String>("Subgenus");
    TableColumn<Plantae, String> plantaeSectionColumn = new TableColumn<Plantae, String>("Section");
    TableColumn<Plantae, String> plantaeSubsectionColumn = new TableColumn<Plantae, String>("Subsection");
    TableColumn<Plantae, String> plantaeSpeciesColumn = new TableColumn<Plantae, String>("Species");
    TableColumn<Plantae, String> plantaeSubspeciesColumn = new TableColumn<Plantae, String>("Subspecies");
    TableColumn<Plantae, String> plantaeVarietyColumn = new TableColumn<Plantae, String>("Variety");
    TableColumn<Plantae, String> plantaeSubvarietyColumn = new TableColumn<Plantae, String>("Subvariety");
    TableColumn<Plantae, String> plantaeFormColumn = new TableColumn<Plantae, String>("Form");
    TableColumn<Plantae, String> plantaeSubformColumn = new TableColumn<Plantae, String>("Subform");


    // Protozoa Columns

    final String[] kingdoms = {"Animalia", "Archaea", "Bacteria", "Chromista", "Fungi", "Plantae", "Protoza"};
    ComboBox<String> kingdomComboBox = new ComboBox(FXCollections.observableArrayList(kingdoms));

    VBox root = new VBox();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Pane to hold all nodes
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setAlignment(Pos.CENTER);

        // Sets default kingdom
        kingdomComboBox.setValue("Animalia");

        // Hbox to hold radio buttons
        HBox radioGroup = new HBox();
        radioGroup.setPadding(new Insets(10, 10, 10, 10));

        // Animalia Column Factories
        animaliaItisSerialColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String>("itisSerialNumber"));
        animaliaCommonNameColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String>("commonName"));
        animaliaTaxonomicNameColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String>("taxonomicName"));
        animaliaSubkingdomColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String>("subkingdom"));
        animaliaInfrakingdomColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String>("infrakingdom"));
        animaliaSuperphylumColumn.setCellValueFactory(new PropertyValueFactory<Animalia,String>("superphylum"));
        animaliaPhylumColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String>("phylum"));
        animaliaSubphylumColumn.setCellValueFactory(new PropertyValueFactory<Animalia,String>("subphylum"));
        animaliaInfraphylumColumn.setCellValueFactory(new PropertyValueFactory<Animalia,String>("infraphylum"));
        animaliaSuperclassColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String>("superclass"));
        animaliaClassColumn.setCellValueFactory(new PropertyValueFactory<Animalia,String>("taxonomyClass"));
        animaliaSubclassColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String>("subclass"));
        animaliaInfraclassColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String >("infraclass"));
        animaliaSuperorderColumn.setCellValueFactory(new PropertyValueFactory<Animalia,String>("superorder"));
        animaliaOrderColumn.setCellValueFactory(new PropertyValueFactory<Animalia,String >("order"));
        animaliaSuborderColumn.setCellValueFactory(new PropertyValueFactory<Animalia,String >("suborder"));
        animaliaInfraorderColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String>("infraorder"));
        animaliaSectionColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String>("section"));
        animaliaSubsectionColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String >("subsection"));
        animaliaSuperfamilyColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String>("superfamily"));
        animaliaFamilyColumn.setCellValueFactory(new PropertyValueFactory<Animalia,String >("family"));
        animaliaSubfamilyColumn.setCellValueFactory(new PropertyValueFactory<Animalia,String>("subfamily"));
        animaliaTribeColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String>("tribe"));
        animaliaSubtribeColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String>("subtribe"));
        animaliaGenusColumn.setCellValueFactory(new PropertyValueFactory<Animalia,String>("genus"));
        animaliaSubgenusColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String>("subgenus"));
        animaliaSpeciesColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String>("species"));
        animaliaSubspeciesColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String>("subspecies"));
        animaliaVarietyColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String>("variety"));
        animaliaFormColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String>("form"));
        animaliaRaceColumn.setCellValueFactory(new PropertyValueFactory<Animalia,String>("race"));
        animaliaStripColumn.setCellValueFactory(new PropertyValueFactory<Animalia,String >("strip"));
        animaliaMorphColumn.setCellValueFactory(new PropertyValueFactory<Animalia, String>("morph"));

        // Animalia Table Settings
        animaliaTableView.setEditable(true);
        animaliaTableView.getColumns().addAll(animaliaItisSerialColumn, animaliaCommonNameColumn,
                animaliaTaxonomicNameColumn, animaliaSubkingdomColumn, animaliaInfrakingdomColumn, animaliaSuperphylumColumn,
                animaliaPhylumColumn, animaliaSubphylumColumn, animaliaInfraphylumColumn, animaliaSuperclassColumn,
                animaliaClassColumn, animaliaSubclassColumn, animaliaInfraclassColumn, animaliaSuperorderColumn,
                animaliaOrderColumn, animaliaSuborderColumn, animaliaInfraorderColumn, animaliaSectionColumn,
                animaliaSubsectionColumn, animaliaSuperfamilyColumn, animaliaFamilyColumn, animaliaSubfamilyColumn,
                animaliaTribeColumn, animaliaSubtribeColumn, animaliaGenusColumn, animaliaSubgenusColumn,
                animaliaSpeciesColumn, animaliaSubspeciesColumn, animaliaVarietyColumn, animaliaFormColumn,
                animaliaRaceColumn, animaliaStripColumn, animaliaMorphColumn);
        animaliaItisSerialColumn.prefWidthProperty().bind(root.widthProperty().divide(30.0));
        animaliaCommonNameColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaTaxonomicNameColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaSubkingdomColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaInfrakingdomColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaSuperphylumColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaPhylumColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaSubphylumColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaInfraphylumColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaSuperclassColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaClassColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaSubclassColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaInfraclassColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaSuperorderColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaOrderColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaSuborderColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaInfraorderColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaSectionColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaSubsectionColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaSuperfamilyColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaFamilyColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaSubfamilyColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaTribeColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaSubtribeColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaGenusColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaSubgenusColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaSpeciesColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaSubspeciesColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaVarietyColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaFormColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaRaceColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaStripColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaMorphColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        animaliaTableView.prefHeightProperty().bind(root.heightProperty());
        animaliaTableView.setPlaceholder(new Label("No Search results Kingdom Animalia"));

        // Plantae Column Factories
        plantaeItisSerialColumn.setCellValueFactory(new PropertyValueFactory<Plantae, String>("itisSerialNumber"));
        plantaeCommonNameColumn.setCellValueFactory(new PropertyValueFactory<Plantae, String>("commonName"));
        plantaeTaxonomicNameColumn.setCellValueFactory(new PropertyValueFactory<Plantae, String>("taxonomicName"));
        plantaeSubkingdomColumn.setCellValueFactory(new PropertyValueFactory<Plantae, String>("subkingdom"));
        plantaeInfrakingdomColumn.setCellValueFactory(new PropertyValueFactory<Plantae, String>("infrakingdom"));
        plantaeSuperdivisionColumn.setCellValueFactory(new PropertyValueFactory<Plantae,String>("superdivision"));
        plantaeDivisionColumn.setCellValueFactory(new PropertyValueFactory<Plantae, String>("division"));
        plantaeSubdivisionColumn.setCellValueFactory(new PropertyValueFactory<Plantae,String>("subdivision"));
        plantaeInfradivisionColumn.setCellValueFactory(new PropertyValueFactory<Plantae,String>("infradivision"));
        plantaeSuperclassColumn.setCellValueFactory(new PropertyValueFactory<Plantae, String>("superclass"));
        plantaeClassColumn.setCellValueFactory(new PropertyValueFactory<Plantae,String>("taxonomyClass"));
        plantaeSubclassColumn.setCellValueFactory(new PropertyValueFactory<Plantae, String>("subclass"));
        plantaeInfraclassColumn.setCellValueFactory(new PropertyValueFactory<Plantae, String >("infraclass"));
        plantaeSuperorderColumn.setCellValueFactory(new PropertyValueFactory<Plantae,String>("superorder"));
        plantaeOrderColumn.setCellValueFactory(new PropertyValueFactory<Plantae,String >("order"));
        plantaeSuborderColumn.setCellValueFactory(new PropertyValueFactory<Plantae,String >("suborder"));
        plantaeFamilyColumn.setCellValueFactory(new PropertyValueFactory<Plantae,String >("family"));
        plantaeSubfamilyColumn.setCellValueFactory(new PropertyValueFactory<Plantae,String>("subfamily"));
        plantaeTribeColumn.setCellValueFactory(new PropertyValueFactory<Plantae, String>("tribe"));
        plantaeSubtribeColumn.setCellValueFactory(new PropertyValueFactory<Plantae, String>("subtribe"));
        plantaeGenusColumn.setCellValueFactory(new PropertyValueFactory<Plantae,String>("genus"));
        plantaeSubgenusColumn.setCellValueFactory(new PropertyValueFactory<Plantae, String>("subgenus"));
        plantaeSectionColumn.setCellValueFactory(new PropertyValueFactory<Plantae, String>("section"));
        plantaeSubsectionColumn.setCellValueFactory(new PropertyValueFactory<Plantae, String >("subsection"));
        plantaeSpeciesColumn.setCellValueFactory(new PropertyValueFactory<Plantae, String>("species"));
        plantaeSubspeciesColumn.setCellValueFactory(new PropertyValueFactory<Plantae, String>("subspecies"));
        plantaeVarietyColumn.setCellValueFactory(new PropertyValueFactory<Plantae, String>("variety"));
        plantaeSubvarietyColumn.setCellValueFactory(new PropertyValueFactory<Plantae,String>("subvariety"));
        plantaeFormColumn.setCellValueFactory(new PropertyValueFactory<Plantae, String>("form"));
        plantaeSubformColumn.setCellValueFactory(new PropertyValueFactory<Plantae,String>("subform"));


        // Plantae Table Settings
        plantaeTableView.setEditable(true);
        plantaeTableView.getColumns().addAll(plantaeItisSerialColumn, plantaeCommonNameColumn,
                plantaeTaxonomicNameColumn, plantaeSubkingdomColumn, plantaeInfrakingdomColumn,
                plantaeSuperdivisionColumn, plantaeDivisionColumn, plantaeSubdivisionColumn, plantaeInfradivisionColumn,
                plantaeSuperclassColumn, plantaeClassColumn, plantaeSubclassColumn, plantaeInfraclassColumn,
                plantaeSuperorderColumn, plantaeOrderColumn, plantaeSuborderColumn, plantaeFamilyColumn,
                plantaeSubfamilyColumn, plantaeTribeColumn, plantaeSubtribeColumn, plantaeGenusColumn,
                plantaeSubgenusColumn, plantaeSectionColumn, plantaeSubsectionColumn, plantaeSpeciesColumn,
                plantaeSubspeciesColumn, plantaeVarietyColumn, plantaeSubvarietyColumn, plantaeFormColumn,
                plantaeSubformColumn);
        plantaeItisSerialColumn.prefWidthProperty().bind(root.widthProperty().divide(30.0));
        plantaeCommonNameColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeTaxonomicNameColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeSubkingdomColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeInfrakingdomColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeSuperdivisionColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeDivisionColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeSubdivisionColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeInfradivisionColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeSuperclassColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeClassColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeSubclassColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeInfraclassColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeSuperorderColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeOrderColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeSuborderColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeFamilyColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeSubfamilyColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeTribeColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeSubtribeColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeGenusColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeSubgenusColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeSectionColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeSubsectionColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeSpeciesColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeSubspeciesColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeVarietyColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeSubvarietyColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeFormColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeSubformColumn.prefWidthProperty().bind(root.widthProperty().divide(10.0));
        plantaeTableView.prefHeightProperty().bind(root.heightProperty());

        // Add buttons to group
        itisSerialNumberButton.setToggleGroup(searchType);
        commonNameButton.setToggleGroup(searchType);
        taxonomicNameButton.setToggleGroup(searchType);

        // Create labels
        Label title = new Label("Taxonomic Dictionary");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Label instructions = new Label("Enter a search term below, you can search by Scientific name, Common name, or ITIS serial number\n" +
                "Select the button for what you're searching for. All serach terms are case-sensitive.");
        instructions.setFont(Font.font("Arial", FontPosture.ITALIC, 12));

        // Set button handler
        searchButton.setOnAction(e -> search());
        kingdomComboBox.setOnAction(e -> setTable());

        // Add nodes
        radioGroup.setAlignment(Pos.CENTER);
        radioGroup.getChildren().addAll(itisSerialNumberButton, commonNameButton, taxonomicNameButton, kingdomComboBox);
        root.getChildren().addAll(title, instructions, searchTermEntry, radioGroup, searchButton, animaliaTableView);

        // Create Scene and place in stage
        Scene scene = new Scene(root);
        primaryStage.setTitle("Taxonomy Dictionary");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public void search()
    {
        // Create database object for searches
        ITIS searcher = new ITIS();

        // Get search term
        String searchTerm = searchTermEntry.getText().toString();
        ObservableList<Animalia> searchResults = FXCollections.observableArrayList();
        if (searchType.getSelectedToggle() == itisSerialNumberButton) {
            try {
                searchResults = searcher.itisSerialNumberSearch(searchTerm);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        else if (searchType.getSelectedToggle() == commonNameButton) {
            try {
                searchResults = searcher.commonNameSearch(searchTerm);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        else if (searchType.getSelectedToggle() == taxonomicNameButton) {
            try {
                searchResults = searcher.taxonomicNameSearch(searchTerm);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        animaliaTableView.setItems(searchResults);
    }

    public void setTable()
    {
        switch (kingdomComboBox.getValue().toString())
        {
            case "Animalia":
                root.getChildren().remove(5);
                root.getChildren().add(animaliaTableView);
                break;

            case "Plantae":
                root.getChildren().remove(5);
                root.getChildren().add(plantaeTableView);
                break;
        }
    }
}