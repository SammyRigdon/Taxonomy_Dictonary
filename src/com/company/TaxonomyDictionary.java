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
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked", "rawtypes"})
public class TaxonomyDictionary extends Application {
    // Create nodes with events
    Button searchButton = new Button("Search");
    TextField searchTermEntry = new TextField();
    ToggleGroup searchType = new ToggleGroup();
    RadioButton itisSerialNumberButton = new RadioButton("ITIS Serial Number");
    RadioButton commonNameButton = new RadioButton("Common Name");
    RadioButton taxonomicNameButton = new RadioButton("Scientific Name");

    // TableView for each Kingdom
    TableView<Animalia> animaliaTableView = new TableView<>();
    TableView<Archaea> archaeaTableView = new TableView<>();
    TableView<Bacteria> bacteriaTableView = new TableView<>();
    TableView<Chromista> chromistaTableView = new TableView<>();
    TableView<Fungi> fungiTableView = new TableView<>();
    TableView<Plantae> plantaeTableView = new TableView<>();
    TableView<Protozoa> protozoaTableView = new TableView<>();

    // Animalia Columns
    TableColumn<Animalia, String> animaliaItisSerialColumn = new TableColumn<>("ITIS Serial Number");
    TableColumn<Animalia, String> animaliaCommonNameColumn = new TableColumn<>("Common Name");
    TableColumn<Animalia, String> animaliaTaxonomicNameColumn = new TableColumn<>("Scientific Name");
    TableColumn<Animalia, String> animaliaSubkingdomColumn = new TableColumn<>("Subkingdom");
    TableColumn<Animalia, String> animaliaInfrakingdomColumn = new TableColumn<>("Infrakingdom");
    TableColumn<Animalia, String> animaliaSuperphylumColumn = new TableColumn<>("Superphylum");
    TableColumn<Animalia, String> animaliaPhylumColumn = new TableColumn<>("Phylum");
    TableColumn<Animalia, String> animaliaSubphylumColumn = new TableColumn<>("Subphylum");
    TableColumn<Animalia, String> animaliaInfraphylumColumn = new TableColumn<>("Infraphylum");
    TableColumn<Animalia, String> animaliaSuperclassColumn = new TableColumn<>("Superclass");
    TableColumn<Animalia, String> animaliaClassColumn = new TableColumn<>("Class");
    TableColumn<Animalia, String> animaliaSubclassColumn = new TableColumn<>("Subclass");
    TableColumn<Animalia, String> animaliaInfraclassColumn = new TableColumn<>("Infraclass");
    TableColumn<Animalia, String> animaliaSuperorderColumn = new TableColumn<>("Superorder");
    TableColumn<Animalia, String> animaliaOrderColumn = new TableColumn<>("Order");
    TableColumn<Animalia, String> animaliaSuborderColumn = new TableColumn<>("Suborder");
    TableColumn<Animalia, String> animaliaInfraorderColumn = new TableColumn<>("Infraorder");
    TableColumn<Animalia, String> animaliaSectionColumn = new TableColumn<>("Section");
    TableColumn<Animalia, String> animaliaSubsectionColumn = new TableColumn<>("Subsection");
    TableColumn<Animalia, String> animaliaSuperfamilyColumn = new TableColumn<>("Superfamily");
    TableColumn<Animalia, String> animaliaFamilyColumn = new TableColumn<>("Family");
    TableColumn<Animalia, String> animaliaSubfamilyColumn = new TableColumn<>("Subfamily");
    TableColumn<Animalia, String> animaliaTribeColumn = new TableColumn<>("Tribe");
    TableColumn<Animalia, String> animaliaSubtribeColumn = new TableColumn<>("Subtribe");
    TableColumn<Animalia, String> animaliaGenusColumn = new TableColumn<>("Genus");
    TableColumn<Animalia, String> animaliaSubgenusColumn = new TableColumn<>("Subgenus");
    TableColumn<Animalia, String> animaliaSpeciesColumn = new TableColumn<>("Species");
    TableColumn<Animalia, String> animaliaSubspeciesColumn = new TableColumn<>("Subspecies");
    TableColumn<Animalia, String> animaliaVarietyColumn = new TableColumn<>("Variety");
    TableColumn<Animalia, String> animaliaFormColumn = new TableColumn<>("Form");
    TableColumn<Animalia, String> animaliaRaceColumn = new TableColumn<>("Race");
    TableColumn<Animalia, String> animaliaStripColumn = new TableColumn<>("Strip");
    TableColumn<Animalia, String> animaliaMorphColumn = new TableColumn<>("Morph");

    // Archaea Columns

    // Bacteria Columns

    // Chromista Columns

    // Fungi Columns

    // Plantae Columns
    TableColumn<Plantae, String> plantaeItisSerialColumn = new TableColumn<>("ITIS Serial Number");
    TableColumn<Plantae, String> plantaeCommonNameColumn = new TableColumn<>("Common Name");
    TableColumn<Plantae, String> plantaeTaxonomicNameColumn = new TableColumn<>("Scientific Name");
    TableColumn<Plantae, String> plantaeSubkingdomColumn = new TableColumn<>("Subkingdom");
    TableColumn<Plantae, String> plantaeInfrakingdomColumn = new TableColumn<>("Infrakingdom");
    TableColumn<Plantae, String> plantaeSuperdivisionColumn = new TableColumn<>("Superdivision");
    TableColumn<Plantae, String> plantaeDivisionColumn = new TableColumn<>("Division");
    TableColumn<Plantae, String> plantaeSubdivisionColumn = new TableColumn<>("Subdivision");
    TableColumn<Plantae, String> plantaeInfradivisionColumn = new TableColumn<>("Infradivision");
    TableColumn<Plantae, String> plantaeSuperclassColumn = new TableColumn<>("Superclass");
    TableColumn<Plantae, String> plantaeClassColumn = new TableColumn<>("Class");
    TableColumn<Plantae, String> plantaeSubclassColumn = new TableColumn<>("Subclass");
    TableColumn<Plantae, String> plantaeInfraclassColumn = new TableColumn<>("Infraclass");
    TableColumn<Plantae, String> plantaeSuperorderColumn = new TableColumn<>("Superorder");
    TableColumn<Plantae, String> plantaeOrderColumn = new TableColumn<>("Order");
    TableColumn<Plantae, String> plantaeSuborderColumn = new TableColumn<>("Suborder");
    TableColumn<Plantae, String> plantaeFamilyColumn = new TableColumn<>("Family");
    TableColumn<Plantae, String> plantaeSubfamilyColumn = new TableColumn<>("Subfamily");
    TableColumn<Plantae, String> plantaeTribeColumn = new TableColumn<>("Tribe");
    TableColumn<Plantae, String> plantaeSubtribeColumn = new TableColumn<>("Subtribe");
    TableColumn<Plantae, String> plantaeGenusColumn = new TableColumn<>("Genus");
    TableColumn<Plantae, String> plantaeSubgenusColumn = new TableColumn<>("Subgenus");
    TableColumn<Plantae, String> plantaeSectionColumn = new TableColumn<>("Section");
    TableColumn<Plantae, String> plantaeSubsectionColumn = new TableColumn<>("Subsection");
    TableColumn<Plantae, String> plantaeSpeciesColumn = new TableColumn<>("Species");
    TableColumn<Plantae, String> plantaeSubspeciesColumn = new TableColumn<>("Subspecies");
    TableColumn<Plantae, String> plantaeVarietyColumn = new TableColumn<>("Variety");
    TableColumn<Plantae, String> plantaeSubvarietyColumn = new TableColumn<>("Subvariety");
    TableColumn<Plantae, String> plantaeFormColumn = new TableColumn<>("Form");
    TableColumn<Plantae, String> plantaeSubformColumn = new TableColumn<>("Subform");


    // Protozoa Columns

    final String[] kingdoms = {"Animalia", "Archaea", "Bacteria", "Chromista", "Fungi", "Plantae", "Protoza"};
    ComboBox<String> kingdomComboBox = new ComboBox(FXCollections.observableArrayList(kingdoms));

    VBox root = new VBox();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Pane to hold all nodes
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setAlignment(Pos.CENTER);

        // Sets default kingdom
        kingdomComboBox.setValue("Animalia");

        // Hbox to hold radio buttons
        HBox radioGroup = new HBox();
        radioGroup.setPadding(new Insets(10, 10, 10, 10));

        // Animalia Column Factories
        animaliaItisSerialColumn.setCellValueFactory(new PropertyValueFactory<>("itisSerialNumber"));
        animaliaCommonNameColumn.setCellValueFactory(new PropertyValueFactory<>("commonName"));
        animaliaTaxonomicNameColumn.setCellValueFactory(new PropertyValueFactory<>("taxonomicName"));
        animaliaSubkingdomColumn.setCellValueFactory(new PropertyValueFactory<>("subkingdom"));
        animaliaInfrakingdomColumn.setCellValueFactory(new PropertyValueFactory<>("infrakingdom"));
        animaliaSuperphylumColumn.setCellValueFactory(new PropertyValueFactory<>("superphylum"));
        animaliaPhylumColumn.setCellValueFactory(new PropertyValueFactory<>("phylum"));
        animaliaSubphylumColumn.setCellValueFactory(new PropertyValueFactory<>("subphylum"));
        animaliaInfraphylumColumn.setCellValueFactory(new PropertyValueFactory<>("infraphylum"));
        animaliaSuperclassColumn.setCellValueFactory(new PropertyValueFactory<>("superclass"));
        animaliaClassColumn.setCellValueFactory(new PropertyValueFactory<>("taxonomyClass"));
        animaliaSubclassColumn.setCellValueFactory(new PropertyValueFactory<>("subclass"));
        animaliaInfraclassColumn.setCellValueFactory(new PropertyValueFactory<>("infraclass"));
        animaliaSuperorderColumn.setCellValueFactory(new PropertyValueFactory<>("superorder"));
        animaliaOrderColumn.setCellValueFactory(new PropertyValueFactory<>("order"));
        animaliaSuborderColumn.setCellValueFactory(new PropertyValueFactory<>("suborder"));
        animaliaInfraorderColumn.setCellValueFactory(new PropertyValueFactory<>("infraorder"));
        animaliaSectionColumn.setCellValueFactory(new PropertyValueFactory<>("section"));
        animaliaSubsectionColumn.setCellValueFactory(new PropertyValueFactory<>("subsection"));
        animaliaSuperfamilyColumn.setCellValueFactory(new PropertyValueFactory<>("superfamily"));
        animaliaFamilyColumn.setCellValueFactory(new PropertyValueFactory<>("family"));
        animaliaSubfamilyColumn.setCellValueFactory(new PropertyValueFactory<>("subfamily"));
        animaliaTribeColumn.setCellValueFactory(new PropertyValueFactory<>("tribe"));
        animaliaSubtribeColumn.setCellValueFactory(new PropertyValueFactory<>("subtribe"));
        animaliaGenusColumn.setCellValueFactory(new PropertyValueFactory<>("genus"));
        animaliaSubgenusColumn.setCellValueFactory(new PropertyValueFactory<>("subgenus"));
        animaliaSpeciesColumn.setCellValueFactory(new PropertyValueFactory<>("species"));
        animaliaSubspeciesColumn.setCellValueFactory(new PropertyValueFactory<>("subspecies"));
        animaliaVarietyColumn.setCellValueFactory(new PropertyValueFactory<>("variety"));
        animaliaFormColumn.setCellValueFactory(new PropertyValueFactory<>("form"));
        animaliaRaceColumn.setCellValueFactory(new PropertyValueFactory<>("race"));
        animaliaStripColumn.setCellValueFactory(new PropertyValueFactory<>("strip"));
        animaliaMorphColumn.setCellValueFactory(new PropertyValueFactory<>("morph"));

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
        plantaeItisSerialColumn.setCellValueFactory(new PropertyValueFactory<>("itisSerialNumber"));
        plantaeCommonNameColumn.setCellValueFactory(new PropertyValueFactory<>("commonName"));
        plantaeTaxonomicNameColumn.setCellValueFactory(new PropertyValueFactory<>("taxonomicName"));
        plantaeSubkingdomColumn.setCellValueFactory(new PropertyValueFactory<>("subkingdom"));
        plantaeInfrakingdomColumn.setCellValueFactory(new PropertyValueFactory<>("infrakingdom"));
        plantaeSuperdivisionColumn.setCellValueFactory(new PropertyValueFactory<>("superdivision"));
        plantaeDivisionColumn.setCellValueFactory(new PropertyValueFactory<>("division"));
        plantaeSubdivisionColumn.setCellValueFactory(new PropertyValueFactory<>("subdivision"));
        plantaeInfradivisionColumn.setCellValueFactory(new PropertyValueFactory<>("infradivision"));
        plantaeSuperclassColumn.setCellValueFactory(new PropertyValueFactory<>("superclass"));
        plantaeClassColumn.setCellValueFactory(new PropertyValueFactory<>("taxonomyClass"));
        plantaeSubclassColumn.setCellValueFactory(new PropertyValueFactory<>("subclass"));
        plantaeInfraclassColumn.setCellValueFactory(new PropertyValueFactory<>("infraclass"));
        plantaeSuperorderColumn.setCellValueFactory(new PropertyValueFactory<>("superorder"));
        plantaeOrderColumn.setCellValueFactory(new PropertyValueFactory<>("order"));
        plantaeSuborderColumn.setCellValueFactory(new PropertyValueFactory<>("suborder"));
        plantaeFamilyColumn.setCellValueFactory(new PropertyValueFactory<>("family"));
        plantaeSubfamilyColumn.setCellValueFactory(new PropertyValueFactory<>("subfamily"));
        plantaeTribeColumn.setCellValueFactory(new PropertyValueFactory<>("tribe"));
        plantaeSubtribeColumn.setCellValueFactory(new PropertyValueFactory<>("subtribe"));
        plantaeGenusColumn.setCellValueFactory(new PropertyValueFactory<>("genus"));
        plantaeSubgenusColumn.setCellValueFactory(new PropertyValueFactory<>("subgenus"));
        plantaeSectionColumn.setCellValueFactory(new PropertyValueFactory<>("section"));
        plantaeSubsectionColumn.setCellValueFactory(new PropertyValueFactory<>("subsection"));
        plantaeSpeciesColumn.setCellValueFactory(new PropertyValueFactory<>("species"));
        plantaeSubspeciesColumn.setCellValueFactory(new PropertyValueFactory<>("subspecies"));
        plantaeVarietyColumn.setCellValueFactory(new PropertyValueFactory<>("variety"));
        plantaeSubvarietyColumn.setCellValueFactory(new PropertyValueFactory<>("subvariety"));
        plantaeFormColumn.setCellValueFactory(new PropertyValueFactory<>("form"));
        plantaeSubformColumn.setCellValueFactory(new PropertyValueFactory<>("subform"));


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
        String searchTerm = searchTermEntry.getText();
        List<Map> searchResults = FXCollections.observableArrayList();
        if (searchType.getSelectedToggle() == itisSerialNumberButton) {
            try {
                searchResults = searcher.itisSerialNumberSearch(searchTerm);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
/*
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
 */
        final ObservableList<Animalia> data = FXCollections.observableArrayList();
        Map<Integer, Animalia> animaliaMap = searchResults.get(0);
        for(int i=0; i<=animaliaMap.size()-1; i++){
            Animalia currentResult = animaliaMap.get(i);
            data.add(currentResult);
        }
        animaliaTableView.setItems(data);
    }

    public void setTable()
    {
        switch (kingdomComboBox.getValue()) {
            case "Animalia" -> {
                root.getChildren().remove(5);
                root.getChildren().add(animaliaTableView);
            }
            case "Plantae" -> {
                root.getChildren().remove(5);
                root.getChildren().add(plantaeTableView);
            }
        }
    }
}