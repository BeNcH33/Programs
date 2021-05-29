package sample;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Student
{
    private IntegerProperty id;
    private StringProperty Name;
    private StringProperty LastName;
    private StringProperty Otch;
    private IntegerProperty PasportSerial;
    private IntegerProperty PasportNumber;
    private StringProperty Sex;
    private IntegerProperty DateBirthday;
    private StringProperty Town;

    public Student (int id, String Name,String LastName, String Otch, int PasportSerial, int PasportNumber, String Sex, int DateBirthday, String Town)
    {
        this.id = new SimpleIntegerProperty(id);
        this.Name = new SimpleStringProperty(Name);
        this.LastName = new SimpleStringProperty(LastName);
        this.Otch = new SimpleStringProperty(Otch);
        this.PasportSerial = new SimpleIntegerProperty(PasportSerial);
        this.PasportNumber = new SimpleIntegerProperty(PasportNumber);
        this.Sex = new SimpleStringProperty(Sex);
        this.DateBirthday = new SimpleIntegerProperty(DateBirthday);
        this.Town = new SimpleStringProperty(Town);
    }
    public Student()
    {
        this(0, "","", "", 0,0,"",0,"");
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


    public StringProperty getName() {
        return Name;
    }
    public String getStringName() {
        return Name.get();
    }
    public void setFirstName(StringProperty firstName) {
        Name = firstName;
    }
    public void setStringFirstName(String firstName) {
        this.Name.set(firstName);
    }


    public StringProperty getLastName() {
        return LastName;
    }
    public String getStringLastName() {
        return LastName.get();
    }
    public void setLastName(StringProperty lastName) {
        LastName = lastName;
    }
    public void setStringLastName(String lastName) {
        this.LastName.set(lastName);
    }

    public StringProperty getOtch() {
        return Otch;
    }
    public String getStringOtch() {
        return Otch.get();
    }

    public void setOtch(StringProperty otch) {
        Otch = otch;
    }
    public void setStringOtch(String otch) {
        this.Otch.set(otch);
    }


    public IntegerProperty getPasportSerial()
    {
        return PasportSerial;
    }
    public Integer getIntegerPasportSerial() {
        return PasportSerial.get();
    }
    public void setIntegerPasportSerial(int pasportSerial) {
        this.PasportSerial.set(pasportSerial);
    }

    public IntegerProperty getPasportNumber()
    {
        return PasportNumber;
    }
    public Integer getIntegerPasportNumber() {
        return PasportNumber.get();
    }
    public void setIntegerPasportNumber(int pasportNumber) {
        this.PasportNumber.set(pasportNumber);
    }

    public StringProperty getSex() {
        return Sex;
    }
    public String getStringSex() {
        return Sex.get();
    }
    public void setSex(StringProperty sex) {
        Sex = sex;
    }
    public void setStringSex(String sex) {
        this.Sex.set(sex);
    }

    public IntegerProperty getDateBirthday()
    {
        return DateBirthday;
    }
    public Integer getIntegerDateBirthday() {
        return DateBirthday.get();
    }
    public void setIntegerDateBirthday(int dateBirthday) {
        this.DateBirthday.set(dateBirthday);
    }

    public StringProperty getTown() {
        return Town;
    }
    public String getStringTown() {
        return Town.get();
    }
    public void setTown(StringProperty town) {
        Town = town;
    }
    public void setStringTown(String town) {
        this.Town.set(town);
    }
}
