package sample;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Violation
{
    private IntegerProperty id;
    private StringProperty Category;
    private IntegerProperty Date;

    public Violation (int id, String Category, int Date)
    {
        this.id = new SimpleIntegerProperty(id);
        this.Category = new SimpleStringProperty(Category);
        this.Date = new SimpleIntegerProperty(Date);
    }

    public Violation()
    {
        this(0,"",0);
    }
    public IntegerProperty getId()
    {
        return id;
    }
    public Integer getIntegerId() {
        return id.get();
    }
    public void setIntegerId(int id) {
        this.id.set(id);
    }

    public StringProperty getCategory() { return Category; }
    public String getStringCategory() {
        return Category.get();
    }
    public void setCategory(StringProperty category) {
        Category = category;
    }
    public void setStringCategory(String category) {
        this.Category.set(category);
    }

    public IntegerProperty getDate()
    {
        return Date;
    }
    public Integer getIntegerDate() {
        return Date.get();
    }
    public void setIntegerDate(int date) {
        this.Date.set(date);
    }
}
