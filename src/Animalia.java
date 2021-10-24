/*
Class for all species belonging to the kingdom animalia
 */

import javafx.beans.property.SimpleStringProperty;

public class Animalia extends Species
{
    // Animalia Variables
    SimpleStringProperty subkingdom = new SimpleStringProperty("Null");
    SimpleStringProperty infrakingdom = new SimpleStringProperty("Null");
    SimpleStringProperty superphylum = new SimpleStringProperty("Null");
    SimpleStringProperty phylum = new SimpleStringProperty("Null");
    SimpleStringProperty subphylum = new SimpleStringProperty("Null");
    SimpleStringProperty infraphylum = new SimpleStringProperty("Null");
    SimpleStringProperty superclass = new SimpleStringProperty("Null");
    SimpleStringProperty taxonomyClass = new SimpleStringProperty("Null");
    SimpleStringProperty subclass = new SimpleStringProperty("Null");
    SimpleStringProperty infraclass = new SimpleStringProperty("Null");
    SimpleStringProperty superorder = new SimpleStringProperty("Null");
    SimpleStringProperty order = new SimpleStringProperty("Null");
    SimpleStringProperty suborder = new SimpleStringProperty("Null");
    SimpleStringProperty infraorder = new SimpleStringProperty("Null");
    SimpleStringProperty section = new SimpleStringProperty("Null");
    SimpleStringProperty subsection = new SimpleStringProperty("Null");
    SimpleStringProperty superfamily = new SimpleStringProperty("Null");
    SimpleStringProperty family = new SimpleStringProperty("Null");
    SimpleStringProperty subfamily = new SimpleStringProperty("Null");
    SimpleStringProperty tribe = new SimpleStringProperty("Null");
    SimpleStringProperty subtribe = new SimpleStringProperty("Null");
    SimpleStringProperty genus = new SimpleStringProperty("Null");
    SimpleStringProperty subgenus = new SimpleStringProperty("Null");
    SimpleStringProperty species = new SimpleStringProperty("Null");
    SimpleStringProperty subspecies = new SimpleStringProperty("Null");
    SimpleStringProperty variety = new SimpleStringProperty("Null");
    SimpleStringProperty form = new SimpleStringProperty("Null");
    SimpleStringProperty race = new SimpleStringProperty("Null");
    SimpleStringProperty strip = new SimpleStringProperty("Null");
    SimpleStringProperty morph = new SimpleStringProperty("Null");

    // Full Constructor method for Animalia
    public Animalia(String itisSerialNumber, String taxonomicName, String commonName,String kingdom, String subkingdom,
                    String infrakingdom, String superphylum, String phylum, String subphylum, String infraphylum,
                    String superclass, String taxonomyClass, String subclass, String infraclass, String superorder,
                    String order, String suborder, String infraorder, String section, String subsection,
                    String superfamily, String family, String subfamily, String tribe, String subtribe, String genus,
                    String subgenus, String species, String subspecies, String variety, String form, String race, String strip,
                    String morph)
    {
        this.itisSerialNumber = new SimpleStringProperty(itisSerialNumber);
        this.taxonomicName = new SimpleStringProperty(taxonomicName);
        this.commonName = new SimpleStringProperty(commonName);
        this.kingdom = new SimpleStringProperty(kingdom);
        this.subkingdom = new SimpleStringProperty(subkingdom);
        this.infrakingdom = new SimpleStringProperty(infrakingdom);
        this.superphylum = new SimpleStringProperty(superphylum);
        this.phylum = new SimpleStringProperty(phylum);
        this.subphylum = new SimpleStringProperty(subphylum);
        this.infraphylum = new SimpleStringProperty(infraphylum);
        this.superclass = new SimpleStringProperty(superclass);
        this.taxonomyClass = new SimpleStringProperty(taxonomyClass);
        this.subclass = new SimpleStringProperty(subclass);
        this.infraclass = new SimpleStringProperty(infraclass);
        this.superorder = new SimpleStringProperty(superorder);
        this.order = new SimpleStringProperty(order);
        this.suborder = new SimpleStringProperty(suborder);
        this.infraorder = new SimpleStringProperty(infraorder);
        this.section = new SimpleStringProperty(section);
        this.subsection = new SimpleStringProperty(subsection);
        this.superfamily = new SimpleStringProperty(superfamily);
        this.family = new SimpleStringProperty(family);
        this.subfamily = new SimpleStringProperty(subfamily);
        this.tribe = new SimpleStringProperty(tribe);
        this.subtribe = new SimpleStringProperty(subtribe);
        this.genus = new SimpleStringProperty(genus);
        this.subgenus = new SimpleStringProperty(subgenus);
        this.species = new SimpleStringProperty(species);
        this.subspecies = new SimpleStringProperty(subspecies);
        this.variety = new SimpleStringProperty(variety);
        this.form = new SimpleStringProperty(form);
        this.race = new SimpleStringProperty(race);
        this.strip = new SimpleStringProperty(strip);
        this.morph = new SimpleStringProperty(morph);
    }
    
    // Constructor with only basic info
    public Animalia(String itisSerialNumber, String taxonomicName, String commonName)
    {
        this.itisSerialNumber = new SimpleStringProperty(itisSerialNumber);
        this.taxonomicName = new SimpleStringProperty(taxonomicName);
        this.commonName = new SimpleStringProperty(commonName);
    }

    // Setters
    public void setSubkingdom(String subkingdom) {this.subkingdom.set(subkingdom);}
    public void setInfrakingdom(String infrakingdom) {this.infrakingdom.set(infrakingdom);}
    public void setInfraphylum(String infraphylum) {this.infraphylum.set(infraphylum);}
    public void setPhylum(String phylum) {this.phylum.set(phylum);}
    public void setSubphylum(String subphylum) {this.subphylum.set(subphylum);}
    public void setSuperclass(String superclass) {this.superclass.set(superclass);}
    public void setSuperphylum(String superphylum) {this.superphylum.set(superphylum);}
    public void setTaxonomyClass(String taxonomyClass) {this.taxonomyClass.set(taxonomyClass);}
    public void setSubclass(String subclass) {this.subclass.set(subclass);}
    public void setInfraclass(String infraclass) {this.infraclass.set(infraclass);}
    public void setInfraorder(String infraorder) {this.infraorder.set(infraorder);}
    public void setOrder(String order) {this.order.set(order);}
    public void setSection(String section) {this.section.set(section);}
    public void setSuborder(String suborder) {this.suborder.set(suborder);}
    public void setFamily(String family) {this.family.set(family);}
    public void setSuperorder(String superorder) {this.superorder.set(superorder);}
    public void setForm(String form) {this.form.set(form);}
    public void setGenus(String genus) {this.genus.set(genus);}
    public void setMorph(String morph) {this.morph.set(morph);}
    public void setRace(String race) {this.race.set(race);}
    public void setStrip(String strip) {this.strip.set(strip);}
    public void setSubfamily(String subfamily) {this.subfamily.set(subfamily);}
    public void setSubgenus(String subgenus) {this.subgenus.set(subgenus);}
    public void setSubsection(String subsection) {this.subsection.set(subsection);}
    public void setSpecies(String species) {this.species.set(species);}
    public void setSubspecies(String subspecies) {this.subspecies.set(subspecies);}
    public void setSubtribe(String subtribe) {this.subtribe.set(subtribe);}
    public void setSuperfamily(String superfamily) {this.superfamily.set(superfamily);}
    public void setTribe(String tribe) {this.tribe.set(tribe);}
    public void setVariety(String variety) {this.variety.set(variety);}

    // Getters
    public String getInfrakingdom() {return infrakingdom.get();}
    public String getInfraclass() {return infraclass.get();}
    public String getInfraphylum() {return infraphylum.get();}
    public String getPhylum() {return phylum.get();}
    public String getSubkingdom() {return subkingdom.get();}
    public String getSubphylum() {return subphylum.get();}
    public String getSuperclass() {return superclass.get();}
    public String getSubclass() {return subclass.get();}
    public String getSuperphylum() {return superphylum.get();}
    public String getTaxonomyClass() {return taxonomyClass.get();}
    public String getFamily() {return family.get();}
    public String getForm() {return form.get();}
    public String getGenus() {return genus.get();}
    public String getInfraorder() {return infraorder.get();}
    public String getMorph() {return morph.get();}
    public String getOrder() {return order.get();}
    public String getRace() {return race.get();}
    public String getSection() {return section.get();}
    public String getSpecies() {return species.get();}
    public String getStrip() {return strip.get();}
    public String getSuperorder() {return superorder.get();}
    public String getSubfamily() {return subfamily.get();}
    public String getSubgenus() {return subgenus.get();}
    public String getSuborder() {return suborder.get();}
    public String getSubsection() {return subsection.get();}
    public String getSubspecies() {return subspecies.get();}
    public String getSubtribe() {return subtribe.get();}
    public String getSuperfamily() {return superfamily.get();}
    public String getTribe() {return tribe.get();}
    public String getVariety() {return variety.get();}
}