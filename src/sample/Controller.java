package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Controller {


    public TableColumn<Student, Integer> IdStudent;
    public TableColumn<Student, String> FamilyStudent;
    public TableColumn<Student, String> NameStudent;
    public TableColumn<Student, String> OtchStudent;
    public TableColumn<Student, String> Sex;
    public TableColumn<Student, Integer> Birthday;
    public TableColumn<Student, Integer> SeriaStudent;
    public TableColumn<Student, Integer> NumberStudent;
    public TableView<Student> TableStudent;

    public ObservableList<Student> studentTableObservableList = FXCollections.observableArrayList();
    public ObservableList<Rooms> roomsTableObservableList = FXCollections.observableArrayList();

    public TableView <Rooms> TableRooms;
    public TableColumn <Rooms, Integer> Floor;
    public TableColumn <Rooms, Integer> NumberRooms;
    public TableColumn <Rooms, String> Chear;




    public TableView  Violation;
    public TableColumn  NameViolation;
    public TableColumn DetailsViolation;


    public TableView <Violation> ViolationStudent;
    public TableColumn <Violation, Integer>  idViolationStudent;
    public TableColumn <Violation, String>  CategoryViolation;
    public TableColumn <Violation, Integer>  DateViolation;


    @FXML
    void initialize() {
        IdStudent.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        FamilyStudent.setCellValueFactory(clientStringCellDataFeaturese -> clientStringCellDataFeaturese.getValue().getLastName());
        NameStudent.setCellValueFactory(clientStringCellDataFeaturese -> clientStringCellDataFeaturese.getValue().getName());
        OtchStudent.setCellValueFactory(clientStringCellDataFeaturese -> clientStringCellDataFeaturese.getValue().getOtch());
        Sex.setCellValueFactory(clientStringCellDataFeaturese -> clientStringCellDataFeaturese.getValue().getSex());
        Birthday.setCellValueFactory(cellData -> cellData.getValue().getDateBirthday().asObject());
        SeriaStudent.setCellValueFactory(cellData -> cellData.getValue().getPasportSerial().asObject());
        NumberStudent.setCellValueFactory(cellData -> cellData.getValue().getPasportNumber().asObject());

        TableStudent.setItems(studentTableObservableList);

        Floor.setCellValueFactory(cellData -> cellData.getValue().getFloor().asObject());
        NumberRooms.setCellValueFactory(cellData -> cellData.getValue().getNumberRoom().asObject());
        Chear.setCellValueFactory(clientStringCellDataFeaturese -> clientStringCellDataFeaturese.getValue().getChear());
        TableRooms.setItems(roomsTableObservableList);

        ShowStudentInToTable();
        ShowRoomsInToTable();
    }

    public void ShowStudentInToTable() {

        List<Student> clin = GetListStudentInDataBase();

        for (Student clint : clin) {
            studentTableObservableList.add(clint);
        }
    }

    public void ShowRoomsInToTable() {

        List<Rooms> clin = GetListRoomsInDataBase();

        for (Rooms clint : clin) {
            roomsTableObservableList.add(clint);
        }
    }


    DbHandler DB = new DbHandler();
    Connection conn = DB.getConnection();
    Statement statement;
    ResultSet resSet;


    public List<Student> GetListStudentInDataBase() {
        String query = "SELECT * FROM dormitory.student ";
        ArrayList<Student> studentArrayList = new ArrayList<>();
        try {
            statement = conn.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        {
            try {
                resSet = statement.executeQuery(query);


                while (resSet.next()) {
                    int id;
                    String name, LastName, Otch;
                    int PasportSerial, PasportNumber;
                    String sex;
                    int Date;
                    String Town;
                    id = resSet.getInt("idStudent");
                    name = resSet.getString("Name");
                    LastName = resSet.getString("LastName");
                    Otch = resSet.getString("Otch");
                    PasportSerial = resSet.getInt("PasportSerial");
                    PasportNumber = resSet.getInt("PasportNumber");
                    sex = resSet.getString("Sex");
                    Date = resSet.getInt("DateBirthday");
                    Town = resSet.getString("Town");

                    Student student = new Student();
                    student.setIntegerId(id);
                    student.setStringFirstName(name);
                    student.setStringLastName(LastName);
                    student.setStringOtch(Otch);
                    student.setIntegerPasportSerial(PasportSerial);
                    student.setIntegerPasportNumber(PasportNumber);
                    student.setStringSex(sex);
                    student.setIntegerDateBirthday(Date);
                    student.setStringTown(Town);

                    studentArrayList.add(student);


                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return studentArrayList;
        }
    }

    public List<Rooms> GetListRoomsInDataBase() {
        ArrayList<Rooms> roomsArrayList = new ArrayList<>();

        String query = "SELECT * FROM dormitory.rooms;";

        try {
            statement = conn.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        {
            try {
                resSet = statement.executeQuery(query);


                while (resSet.next())
                {
                    int id, Floor,NumberRoom;
                    String Chear;
                    id = resSet.getInt("idRooms");
                    Floor = resSet.getInt("Floor");
                    Chear=resSet.getString("Chear");
                    NumberRoom=resSet.getInt("NumberRoom");

                    Rooms rooms=new Rooms();
                    rooms.setIntegerId(id);
                    rooms.setIntegerFloor(Floor);
                    rooms.setIntegerNumberRoom(NumberRoom);
                    rooms.setStringChear(Chear);


                    roomsArrayList.add(rooms);

                }



            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return roomsArrayList;
    }



    public List<Violation> GetListViolationInDataBase() {
        ArrayList<Violation> violationsArrayList = new ArrayList<>();

        String query = "SELECT * FROM dormitory.violation;";

        try {
            statement = conn.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        {
            try {
                resSet = statement.executeQuery(query);


                while (resSet.next())
                {
                    int id,Date;
                    String Category;
                    id = resSet.getInt("idStudent");
                    Category=resSet.getString("category");
                    Date=resSet.getInt("Date");

                    Violation violation=new Violation();
                    violation.setIntegerId(id);
                    violation.setIntegerDate(Date);
                    violation.setStringCategory(Category);


                    violationsArrayList.add(violation);

                }



            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return violationsArrayList;
    }
}


