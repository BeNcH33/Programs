package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViolationContent
{
    private IntegerProperty id;
    private StringProperty Name;
    private StringProperty Description;

    public ViolationContent(int id, String Name,String Description)
    {
        this.id=new SimpleIntegerProperty(id);
        this.Name=new SimpleStringProperty(Name);
        this.Description=new SimpleStringProperty(Description);
    }
    public ViolationContent(){this (0,"","");}

    public int getId() { return id.get(); }

    public IntegerProperty idProperty() { return id; }

    public void setId(int id) { this.id.set(id); }


    public String getName() { return Name.get(); }

    public StringProperty getnameProperty() { return Name; }

    public void setName(String name) { this.Name.set(name); }


    public String getDescription() { return Description.get(); }

    public StringProperty getdescriptionProperty() { return Description; }

    public void setDescription(String description) { this.Description.set(description); }

}
