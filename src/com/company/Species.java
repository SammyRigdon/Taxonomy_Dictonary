package com.company;/*
Abstract class for the implementation of the animalia platea and fungi classes
 */

import javafx.beans.property.SimpleStringProperty;

abstract class Species
{
    // Declare variables for abstract class
    SimpleStringProperty itisSerialNumber = new SimpleStringProperty("Null");
    SimpleStringProperty taxonomicName = new SimpleStringProperty("Null");
    SimpleStringProperty commonName = new SimpleStringProperty("Null");
    SimpleStringProperty kingdom = new SimpleStringProperty("Null");

    // Getters
    public String getCommonName() {return commonName.get();}
    public String getItisSerialNumber() {return itisSerialNumber.get();}
    public String getTaxonomicName() {return taxonomicName.get();}
    public String getKingdom() {return kingdom.get();}

    // Setters
    public void setCommonName(String commonName) {this.commonName.set(commonName);}
    public void setItisSerialNumber(String itisSerialNumber) {this.itisSerialNumber.set(itisSerialNumber);}
    public void setTaxonomicName(String taxonomicName) {this.taxonomicName.set(taxonomicName);}
    public void setKingdom(String kingdom) {this.kingdom.set(kingdom);}
}
