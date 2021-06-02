package sample;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Rooms
{
    private IntegerProperty id;
    private IntegerProperty Floor;
    private StringProperty Chear;
    private IntegerProperty NumberRoom;
    private IntegerProperty IdStudent;

    public Rooms(int id, int Floor, String Chear, int NumberRoom, int IdStudent)
    {
        this.id = new SimpleIntegerProperty(id);
        this.Floor = new SimpleIntegerProperty(Floor);
        this.Chear = new SimpleStringProperty(Chear);
        this.NumberRoom = new SimpleIntegerProperty(NumberRoom);
        this.IdStudent=new SimpleIntegerProperty(IdStudent);
    }
    public Rooms()
    {
        this(0,0,"",0,0);
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

    public IntegerProperty getFloor()
    {
        return Floor;
    }
    public Integer getIntegerFloor() {
        return Floor.get();
    }
    public void setIntegerFloor(int floor) {
        this.Floor.set(floor);
    }

    public StringProperty getChear() { return Chear; }
    public String getStringChear() {
        return Chear.get();
    }
    public void setChear(StringProperty chear) {
        Chear = chear;
    }
    public void setStringChear(String chear) {
        this.Chear.set(chear);
    }

    public IntegerProperty getNumberRoom()
    {
        return NumberRoom;
    }
    public Integer getIntegerNumberRoom() {
        return NumberRoom.get();
    }
    public void setIntegerNumberRoom(int numberRoom) {
        this.NumberRoom.set(numberRoom);
    }

    public IntegerProperty getIdStudentRoom()
    {
        return IdStudent;
    }
    public Integer getIntegerdStudentRoom() {
        return IdStudent.get();
    }
    public void setIntegerdStudentRoom(int idStudent) {
        this.IdStudent.set(idStudent);
    }
}
