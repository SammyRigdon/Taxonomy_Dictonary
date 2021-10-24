import javafx.beans.property.SimpleStringProperty;

public class Plantae extends Species
{
    // TODO Plantae Implementation
    // Plantae Variables
    SimpleStringProperty subkingdom = new SimpleStringProperty("Null");
    SimpleStringProperty infrakingdom = new SimpleStringProperty("Null");
    SimpleStringProperty superdivision = new SimpleStringProperty("Null");
    SimpleStringProperty division = new SimpleStringProperty("Null");
    SimpleStringProperty subdivision = new SimpleStringProperty("Null");
    SimpleStringProperty infradivision = new SimpleStringProperty("Null");
    SimpleStringProperty superclass = new SimpleStringProperty("Null");
    SimpleStringProperty taxonomyClass = new SimpleStringProperty("Null");
    SimpleStringProperty subclass = new SimpleStringProperty("Null");
    SimpleStringProperty infraclass = new SimpleStringProperty("Null");
    SimpleStringProperty superorder = new SimpleStringProperty("Null");
    SimpleStringProperty order = new SimpleStringProperty("Null");
    SimpleStringProperty suborder = new SimpleStringProperty("Null");
    SimpleStringProperty family = new SimpleStringProperty("Null");
    SimpleStringProperty subfamily = new SimpleStringProperty("Null");
    SimpleStringProperty tribe = new SimpleStringProperty("Null");
    SimpleStringProperty subtribe = new SimpleStringProperty("Null");
    SimpleStringProperty genus = new SimpleStringProperty("Null");
    SimpleStringProperty subgenus = new SimpleStringProperty("Null");
    SimpleStringProperty section = new SimpleStringProperty("Null");
    SimpleStringProperty subsection = new SimpleStringProperty("Null");
    SimpleStringProperty species = new SimpleStringProperty("Null");
    SimpleStringProperty subspecies = new SimpleStringProperty("Null");
    SimpleStringProperty variety = new SimpleStringProperty("Null");
    SimpleStringProperty subvariety = new SimpleStringProperty("Null");
    SimpleStringProperty form = new SimpleStringProperty("Null");
    SimpleStringProperty subform = new SimpleStringProperty("Null");

    // Basic Constructor
    public Plantae(String itisSerialNumber, String taxonomicName, String commonName)
    {
        this.itisSerialNumber = new SimpleStringProperty(itisSerialNumber);
        this.taxonomicName = new SimpleStringProperty(taxonomicName);
        this.commonName = new SimpleStringProperty(commonName);
    }

    // Full Constructor
    public Plantae(String subkingdom, String infrakingdom, String superdivision, String division, String subdivision,
                   String infradivision, String superclass, String taxonomyClass, String subclass, String infraclass,
                   String superorder, String order, String suborder, String family, String subfamily, String tribe,
                   String subtribe, String genus, String subgenus, String section, String subsection, String species,
                   String subspecies, String variety, String subvariety, String form, String subform)
    {
        this.subkingdom = new SimpleStringProperty(subkingdom);
        this.infrakingdom = new SimpleStringProperty(infrakingdom);
        this.superdivision = new SimpleStringProperty(superdivision);
        this.division = new SimpleStringProperty(division);
        this.subdivision = new SimpleStringProperty(subdivision);
        this.infradivision = new SimpleStringProperty(infradivision);
        this.superclass = new SimpleStringProperty(superclass);
        this.taxonomyClass = new SimpleStringProperty(taxonomyClass);
        this.subclass = new SimpleStringProperty(subclass);
        this.infraclass = new SimpleStringProperty(infraclass);
        this.superorder = new SimpleStringProperty(superorder);
        this.order = new SimpleStringProperty(order);
        this.suborder = new SimpleStringProperty(suborder);
        this.family = new SimpleStringProperty(family);
        this.subfamily = new SimpleStringProperty(subfamily);
        this.tribe = new SimpleStringProperty(tribe);
        this.subtribe = new SimpleStringProperty(subtribe);
        this.genus = new SimpleStringProperty(genus);
        this.subgenus = new SimpleStringProperty(subgenus);
        this.section = new SimpleStringProperty(section);
        this.subsection = new SimpleStringProperty(subsection);
        this.species = new SimpleStringProperty(species);
        this.subspecies = new SimpleStringProperty(subspecies);
        this.variety = new SimpleStringProperty(variety);
        this.subvariety = new SimpleStringProperty(subvariety);
        this.form = new SimpleStringProperty(form);
        this.subform = new SimpleStringProperty(subform);
    }

    // Getters
    public SimpleStringProperty subkingdomProperty() { return subkingdom; }
    public SimpleStringProperty infrakingdomProperty() { return infrakingdom; }
    public SimpleStringProperty superdivisionProperty() { return superdivision; }
    public SimpleStringProperty divisionProperty() { return division; }
    public SimpleStringProperty subdivisionProperty() { return subdivision; }
    public SimpleStringProperty infradivisionProperty() { return infradivision; }
    public SimpleStringProperty superclassProperty() { return superclass; }
    public SimpleStringProperty taxonomyClassProperty() { return taxonomyClass; }
    public SimpleStringProperty subclassProperty() { return subclass; }
    public SimpleStringProperty infraclassProperty() { return infraclass; }
    public SimpleStringProperty superorderProperty() { return superorder; }
    public SimpleStringProperty orderProperty() { return order; }
    public SimpleStringProperty suborderProperty() { return suborder; }
    public SimpleStringProperty familyProperty() { return family; }
    public SimpleStringProperty subfamilyProperty() { return subfamily; }
    public SimpleStringProperty tribeProperty() { return tribe; }
    public SimpleStringProperty subtribeProperty() { return subtribe; }
    public SimpleStringProperty genusProperty() { return genus; }
    public SimpleStringProperty subgenusProperty() { return subgenus; }
    public SimpleStringProperty sectionProperty() { return section; }
    public SimpleStringProperty subsectionProperty() { return subsection; }
    public SimpleStringProperty speciesProperty() { return species; }
    public SimpleStringProperty subspeciesProperty() { return subspecies; }
    public SimpleStringProperty varietyProperty() { return variety; }
    public SimpleStringProperty subvarietyProperty() { return subvariety; }
    public SimpleStringProperty formProperty() { return form; }
    public SimpleStringProperty subformProperty() { return subform; }


    // Setters
    public void setSubkingdom(String subkingdom) { this.subkingdom.set(subkingdom); }
    public void setInfrakingdom(String infrakingdom) { this.infrakingdom.set(infrakingdom); }
    public void setSuperdivision(String superdivision) { this.superdivision.set(superdivision); }
    public void setDivision(String division) { this.division.set(division); }
    public void setSubdivision(String subdivision) { this.subdivision.set(subdivision); }
    public void setInfradivision(String infradivision) { this.infradivision.set(infradivision); }
    public void setSuperclass(String superclass) { this.superclass.set(superclass); }
    public void setTaxonomyClass(String taxonomyClass) { this.taxonomyClass.set(taxonomyClass); }
    public void setSubclass(String subclass) { this.subclass.set(subclass); }
    public void setInfraclass(String infraclass) { this.infraclass.set(infraclass); }
    public void setSuperorder(String superorder) { this.superorder.set(superorder); }
    public void setOrder(String order) { this.order.set(order); }
    public void setSuborder(String suborder) { this.suborder.set(suborder); }
    public void setFamily(String family) { this.family.set(family); }
    public void setSubfamily(String subfamily) { this.subfamily.set(subfamily); }
    public void setTribe(String tribe) { this.tribe.set(tribe); }
    public void setSubtribe(String subtribe) { this.subtribe.set(subtribe); }
    public void setGenus(String genus) { this.genus.set(genus); }
    public void setSubgenus(String subgenus) { this.subgenus.set(subgenus); }
    public void setSection(String section) { this.section.set(section); }
    public void setSubsection(String subsection) { this.subsection.set(subsection); }
    public void setSpecies(String species) { this.species.set(species); }
    public void setSubspecies(String subspecies) { this.subspecies.set(subspecies); }
    public void setVariety(String variety) { this.variety.set(variety); }
    public void setSubvariety(String subvariety) { this.subvariety.set(subvariety); }
    public void setForm(String form) { this.form.set(form); }
    public void setSubform(String subform) { this.subform.set(subform); }
}