package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import jdk.jfr.Name;

import java.sql.*;
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
    public ObservableList<Violation> violationTableObservableList = FXCollections.observableArrayList();
    public ObservableList<ViolationContent> violationcontentTableObservableList = FXCollections.observableArrayList();

    public TableView <Rooms> TableRooms;
    public TableColumn <Rooms, Integer> Floor;
    public TableColumn <Rooms, Integer> NumberRooms;
    public TableColumn <Rooms, String> Chear;
    public TableColumn <Rooms, Integer> IdStudent2;




    public TableView <ViolationContent>  Violation;
    public TableColumn <ViolationContent, String>  NameViolation;
    public TableColumn <ViolationContent, String> DetailsViolation;


    public TableView <Violation> ViolationStudent;
    public TableColumn <Violation, Integer>  idViolationStudent;
    public TableColumn <Violation, String>  CategoryViolation;
    public TableColumn <Violation, Integer>  DateViolation;

    public TextField AddFamily;
    public TextField AddName;
    public TextField AddLastname;
    public TextField AddSex;
    public TextField AddDateBirthday;
    public TextField AddSeria;
    public TextField AddNumber;
    public TextField AddTown;

    public TextField AddFloor;
    public TextField AddNumberRoom;
    public TextArea AddFurniture;

    public TextField AddNameViolation;
    public TextArea AddDeskriptionViolation;

    public TextField AddIdStudViolation;
    public TextField AddNameStudViolation;
    public TextField addDateViolation;

    public TextField RoomsIdStudent;


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
        IdStudent2.setCellValueFactory(cellData -> cellData.getValue().getIdStudentRoom().asObject());
        TableRooms.setItems(roomsTableObservableList);

        idViolationStudent.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        CategoryViolation.setCellValueFactory(clientStringCellDataFeaturese -> clientStringCellDataFeaturese.getValue().getCategory());
        DateViolation.setCellValueFactory(cellData -> cellData.getValue().getDate().asObject());
        ViolationStudent.setItems(violationTableObservableList);

        NameViolation.setCellValueFactory(clientStringCellDataFeaturese -> clientStringCellDataFeaturese.getValue().getnameProperty());
        DetailsViolation.setCellValueFactory(clientStringCellDataFeaturese -> clientStringCellDataFeaturese.getValue().getdescriptionProperty());
        Violation.setItems(violationcontentTableObservableList);

        ShowStudentInToTable();
        ShowRoomsInToTable();
        ShowViolationInToTable();
        ShowViolationContentInToTable();

    }

    public void ShowStudentInToTable() {

        List<Student> clin = GetListStudentInDataBase();
        for (Student clint : clin) {
            studentTableObservableList.add(clint);
        }

    }

    public void ShowRoomsInToTable() {

        List<Rooms> clin = GetListRoomsInDataBase();
        roomsTableObservableList.clear();
        for (Rooms clint : clin) {
            roomsTableObservableList.add(clint);
        }
    }
    public void ShowRoomsIdInToTable() {
        Student selectedItem= TableStudent.getSelectionModel().getSelectedItem();

        List<Rooms> clin = GetListRoomsInDataBase(selectedItem.getIntegerId());
        roomsTableObservableList.clear();
        for (Rooms clint : clin) {
            roomsTableObservableList.add(clint);
        }
    }

    public void ShowViolationInToTable() {

        List<Violation> clin = GetListViolationInDataBase();
        violationTableObservableList.clear();
        for (Violation clint : clin) {
            violationTableObservableList.add(clint);
        }
    }

    public void ShowViolationIdInToTable() {
        Student selectedItem= TableStudent.getSelectionModel().getSelectedItem();

        List<Violation> clin = GetListViolationInDataBase(selectedItem.getIntegerId());
        violationTableObservableList.clear();
        for (Violation clint : clin) {
            violationTableObservableList.add(clint);
        }
    }

    public void ShowViolationContentInToTable() {

        List<ViolationContent> clin = GetListViolationContentInDataBase();
        violationcontentTableObservableList.clear();
        for (ViolationContent clint : clin) {
            violationcontentTableObservableList.add(clint);
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

                    studentTableObservableList.add(student);


                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return studentArrayList;
        }
    }


    //?????????? ???????????????????? ???? ?????????????????????????? ????????????????????????
    public List<Rooms> GetListRoomsInDataBase(int ID) {

        ArrayList<Rooms> roomsArrayList = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM dormitory.rooms WHERE idStudent=?");
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id, Floor, NumberRoom, idStudent;
                String Chear;
                id = resultSet.getInt("idrooms");
                Floor = resultSet.getInt("Floor");
                Chear = resultSet.getString("Chear");
                NumberRoom = resultSet.getInt("NumberRoom");
                idStudent = resultSet.getInt("idStudent");


                Rooms rooms = new Rooms();
                rooms.setIntegerId(id);
                rooms.setIntegerFloor(Floor);
                rooms.setIntegerNumberRoom(NumberRoom);
                rooms.setIntegerdStudentRoom(idStudent);
                rooms.setStringChear(Chear);

                roomsArrayList.add(rooms);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return roomsArrayList;

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
                    int id, Floor,NumberRoom, idStudent;
                    String Chear;
                    id = resSet.getInt("idrooms");
                    Floor = resSet.getInt("Floor");
                    Chear=resSet.getString("Chear");
                    NumberRoom=resSet.getInt("NumberRoom");
                    idStudent=resSet.getInt("idStudent");


                    Rooms rooms=new Rooms();
                    rooms.setIntegerId(id);
                    rooms.setIntegerFloor(Floor);
                    rooms.setIntegerNumberRoom(NumberRoom);
                    rooms.setIntegerdStudentRoom(idStudent);
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

    // ?????????? ???????????????????? ???? ??????????
    public List<Violation> GetListViolationInDataBase(int ID) {
        ArrayList<Violation> violationsArrayList = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM dormitory.violation WHERE idStudent=?");
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                int id,Date;
                String Category;
                id = resultSet.getInt("idStudent");
                Category=resultSet.getString("category");
                Date=resultSet.getInt("Date");

                Violation violation=new Violation();
                violation.setIntegerId(id);
                violation.setIntegerDate(Date);
                violation.setStringCategory(Category);


                violationsArrayList.add(violation);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return violationsArrayList;
    }


    public List<ViolationContent> GetListViolationContentInDataBase() {
        ArrayList<ViolationContent> violationscontentArrayList = new ArrayList<>();

        String query = "SELECT * FROM dormitory.violationcontent;";

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
                    int id;
                    String Name,Description;
                    id = resSet.getInt("idViolationContent");
                    Name=resSet.getString("NameViolation");
                    Description=resSet.getString("Description");


                    ViolationContent violation=new ViolationContent();
                    violation.setId(id);
                    violation.setName(Name);
                    violation.setDescription(Description);

                    violationscontentArrayList.add(violation);

                }



            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return violationscontentArrayList;
    }






    public void ButtonAddStudentClick(ActionEvent actionEvent) throws SQLException {
        try {
            statement = conn.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String Family, Name, LastName, Sex, Town;
        int DateBirthday, Seria, Number;

        Family = AddFamily.getText();
        Name = AddName.getText();
        LastName = AddLastname.getText();
        Sex = AddSex.getText();
        Town = AddTown.getText();
        DateBirthday = Integer.parseInt(AddDateBirthday.getText());
        Seria = Integer.parseInt(AddSeria.getText());
        Number = Integer.parseInt(AddNumber.getText());

        if (AddFamily.getText() != "" & AddName.getText() != ""
                & AddLastname.getText() != "" & AddSex.getText() != ""
                & AddTown.getText() != "" & AddDateBirthday.getText() != ""
                & AddSeria.getText() != "" & AddNumber.getText() != "")
        {

            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement("INSERT Student(Name, LastName, Otch, PasportSerial, PasportNumber, Sex, DateBirthday, Town) VALUES(?,?,?,?,?,?,?,?)");
            pstmt.setString(1, Name);
            pstmt.setString(2, Family);
            pstmt.setString(3, LastName);
            pstmt.setInt(4, Seria);
            pstmt.setInt(5, Number);
            pstmt.setString(6, Sex);
            pstmt.setInt(7, DateBirthday);
            pstmt.setString(8, Town);
            pstmt.executeUpdate();

            studentTableObservableList.clear();
            ShowStudentInToTable();


            AddFamily.clear();
            AddName.clear();
            AddLastname.clear();
            AddSex.clear();
            AddTown.clear();
            AddDateBirthday.clear();
            AddSeria.clear();
            AddNumber.clear();

        }
    }

    public void ButtonAddRoomClick(ActionEvent actionEvent) throws SQLException {
        try {
            statement = conn.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        int Floor, NumberRoom, idStudent; String Furniture;
        Floor=Integer.parseInt(AddFloor.getText());
        NumberRoom=Integer.parseInt(AddNumberRoom.getText());
        Furniture=AddFurniture.getText();
        idStudent=Integer.parseInt(RoomsIdStudent.getText());

        if (AddFloor.getText()!="" & AddNumberRoom.getText()!="" & AddFurniture.getText()!="")
        {

            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement("INSERT rooms (Floor,NumberRoom,Chear, idStudent) VALUES(?,?,?,?)");
            pstmt.setInt(1, Floor);
            pstmt.setInt(2, NumberRoom);
            pstmt.setString(3, Furniture);
            pstmt.setInt(4, idStudent);
            pstmt.executeUpdate();
            ShowRoomsInToTable();
            AddFloor.clear();
            AddNumberRoom.clear();
            AddFurniture.clear();
            RoomsIdStudent.clear();
        }
    }

    public void ButtonAddViolationContentClick(ActionEvent actionEvent) throws SQLException {
        try {
            statement = conn.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String NameViolation, Description;
        NameViolation=AddNameViolation.getText();
        Description=AddDeskriptionViolation.getText();

        if (AddNameViolation.getText()!="" & AddDeskriptionViolation.getText()!="" )
        {

            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement("INSERT violationcontent (NameViolation, Description) VALUES (?,?)");
            pstmt.setString(1, NameViolation);
            pstmt.setString(2, Description);
            pstmt.executeUpdate();
            ShowViolationContentInToTable();
            AddNameViolation.clear();
            AddDeskriptionViolation.clear();
        }

    }

    public void ButtonAddViolationClick(ActionEvent actionEvent) throws SQLException {
        try {
            statement = conn.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int id, Date; String Name;

        id=Integer.parseInt(AddIdStudViolation.getText());
        Name=AddNameStudViolation.getText();
        Date=Integer.parseInt(addDateViolation.getText());

        if (AddIdStudViolation.getText()!="" & AddNameStudViolation.getText()!="" & addDateViolation.getText()!="")
        {

            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement("INSERT violation (category,Date, idStudent) VALUES (?,?,?)");
            pstmt.setString(1, Name);
            pstmt.setInt(2, Date);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            ShowViolationInToTable();
            AddIdStudViolation.clear();
            AddNameStudViolation.clear();
            addDateViolation.clear();
        }

    }

    public void OnTableStudentClick(MouseEvent mouseEvent)
    {
        if (mouseEvent.getClickCount()>0)
        {
            Student selectedItem= TableStudent.getSelectionModel().getSelectedItem();
            AddFamily.setText(selectedItem.getStringLastName());
            AddName.setText((selectedItem.getStringName()));
            AddLastname.setText(selectedItem.getStringOtch());
            AddSex.setText(selectedItem.getStringSex());
            String Date=String.valueOf(selectedItem.getIntegerDateBirthday());
            AddDateBirthday.setText(Date);
            String Serial=String.valueOf(selectedItem.getIntegerPasportSerial());
            AddSeria.setText(Serial);
            String Number =String.valueOf(selectedItem.getIntegerPasportNumber());
            AddNumber.setText(Number);
            AddTown.setText(selectedItem.getStringTown());
            String ID=String.valueOf(selectedItem.getIntegerId());
            AddIdStudViolation.setText(ID);
            RoomsIdStudent.setText(ID);


            GetListRoomsInDataBase(selectedItem.getIntegerId());
            ShowRoomsIdInToTable();
            GetListViolationInDataBase(selectedItem.getIntegerId());
            ShowViolationIdInToTable();
        }

    }

    public void OnTableRoomsClick(MouseEvent mouseEvent)
    {
        if (mouseEvent.getClickCount()>1)
        {
            Rooms selectedItem= TableRooms.getSelectionModel().getSelectedItem();
            String Floor=String.valueOf(selectedItem.getIntegerFloor());
            AddFloor.setText(Floor);
            String NumberRoom=String.valueOf(selectedItem.getIntegerNumberRoom());
            AddNumberRoom.setText(NumberRoom);
            AddFurniture.setText(selectedItem.getStringChear());
        }
    }

    public void OnTableViolationContentClick(MouseEvent mouseEvent)
    {
        if (mouseEvent.getClickCount()>1)
        {
            ViolationContent selectedItem=Violation.getSelectionModel().getSelectedItem();
            AddNameViolation.setText(selectedItem.getName());
            AddDeskriptionViolation.setText(selectedItem.getDescription());
            AddNameStudViolation.setText(selectedItem.getName());
        }
    }

    public void OnTableViolationStudentClick(MouseEvent mouseEvent)
    {
        if (mouseEvent.getClickCount()>1)
        {
            Violation selectedItem= ViolationStudent.getSelectionModel().getSelectedItem();
            String Id=String.valueOf(selectedItem.getIntegerId());
            AddIdStudViolation.setText(Id);
            AddNameStudViolation.setText(selectedItem.getStringCategory());
            String Date=String.valueOf(selectedItem.getIntegerDate());
            addDateViolation.setText(Date);
        }
    }

    public void ButtonDeleteClick(MouseEvent mouseEvent) throws SQLException {
        try {
            statement = conn.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        int Number=Integer.parseInt(AddNumber.getText());



        PreparedStatement pstmt = null;
        pstmt = conn.prepareStatement("DELETE FROM Student WHERE PasportNumber=?");
        pstmt.setInt(1, Number);
        pstmt.executeUpdate();
        studentTableObservableList.clear();
        ShowStudentInToTable();

    }

    public void ButtonEditClick(MouseEvent mouseEvent) throws SQLException {
        try {
            statement = conn.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String Family= AddFamily.getText();
        String Name=AddName.getText();
        String LastName=AddLastname.getText();
        String Sex=AddSex.getText();
        String Town=AddTown.getText();
        int DateBirthday=Integer.parseInt(AddDateBirthday.getText());
        int Seria=Integer.parseInt(AddSeria.getText());
        int Number=Integer.parseInt(AddNumber.getText());

        PreparedStatement pstmt = null;
        pstmt = conn.prepareStatement("UPDATE Student SET Name=?, LastName=?, Otch=?, PasportSerial=?, PasportNumber=?, Sex=?, DateBirthday=?, Town=?");
        pstmt.setString(1, Name);
        pstmt.setString(2, Family);
        pstmt.setString(3, LastName);
        pstmt.setInt(4, Seria);
        pstmt.setInt(5, Number);
        pstmt.setString(6, Sex);
        pstmt.setInt(7, DateBirthday);
        pstmt.setString(8, Town);
        pstmt.executeUpdate();
        studentTableObservableList.clear();
        ShowStudentInToTable();

        AddFamily.clear();AddName.clear();AddLastname.clear();AddSex.clear();AddTown.clear();AddDateBirthday.clear();AddSeria.clear();AddNumber.clear();

    }
}
