/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import GeoMapping.GMapsController;
import dbUtil.dbConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminController implements Initializable {

    private dbConnection dc;
    private ObservableList<StudentData> data;
    private String sql = "SELECT *FROM students";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.dc = new dbConnection();

    }

    @FXML
    private void loadStudentData(ActionEvent event) throws SQLException {
        try {
            Connection conn = dbConnection.getConnection();
            this.data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                this.data.add(new StudentData(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));

            }
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }

        this.IDColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("ID"));
        this.firstNameColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("firstName"));
        this.lastNameColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("lastName"));
        this.addressColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("address"));
        this.emailColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("email"));
        this.subjectColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("subject"));
        this.feeColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("fee"));
        this.paymentColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("payment"));
        this.timeColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("timings"));

        this.studentTable.setItems(null);
        this.studentTable.setItems(this.data);

        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int lastDay = cal.getInstance().getActualMaximum(cal.DAY_OF_MONTH);
        
        if (dayOfMonth == lastDay){
            String pay = "No";
            String sqlUpdate = ("UPDATE STUDENTS SET id = '" + pay);
            
            Connection conn = dbConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);

            pstmt.executeUpdate();
        }
    }

    @FXML
    private void addStudent(ActionEvent event) {
        String sqlInsert = "INSERT INTO students(id , fName , lName , Address , "
                + "Email , Subject , fee , Payment , Timings ) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlInsert);

            stmt.setString(1, this.IDText.getText());
            stmt.setString(2, this.firstText.getText());
            stmt.setString(3, this.lastText.getText());
            stmt.setString(4, this.addressText.getText());
            stmt.setString(5, this.emailText.getText());
            stmt.setString(6, this.subjectText.getText());
            stmt.setString(7, this.feeText.getText());
            stmt.setString(8, this.paymentText.getText());
            stmt.setString(9, this.timeText.getText());

            stmt.execute();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clearForm(ActionEvent event) {
        this.IDText.setText("");
        this.firstText.setText("");
        this.lastText.setText("");
        this.addressText.setText("");
        this.emailText.setText("");
        this.subjectText.setText("");
        this.feeText.setText("");
        this.paymentText.setText("");
        this.timeText.setText("");

    }

    @FXML
    private void findEntry(ActionEvent event) throws SQLException {
        try {

            String sqlFind = ("SELECT id, fName, lName, address, email, subject, fee, payment, "
                    + "timings FROM Students WHERE fName = ? and lName = ?");
            Connection conn1 = dbConnection.getConnection();

            PreparedStatement stmt2 = conn1.prepareStatement(sqlFind);
            stmt2.setString(1, firstSearchText.getText());
            stmt2.setString(2, lastSearchText.getText());

            ResultSet rs1 = stmt2.executeQuery();

            while (rs1.next()) {
                IDText.setText(rs1.getString("id"));
                firstText.setText(rs1.getString("fName"));
                lastText.setText(rs1.getString("lName"));
                addressText.setText(rs1.getString("address"));
                emailText.setText(rs1.getString("email"));
                subjectText.setText(rs1.getString("subject"));
                feeText.setText(rs1.getString("fee"));
                paymentText.setText(rs1.getString("payment"));
                timeText.setText(rs1.getString("timings"));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void deleteEntry(ActionEvent event) {
        try {
            String sqlDelete = ("DELETE FROM STUDENTS WHERE fName = ? and lName = ?");
            Connection conn = dbConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlDelete);

            // set the corresponding param
            pstmt.setString(1, firstSearchText.getText());
            pstmt.setString(2, lastSearchText.getText());
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void map(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        Pane root = (Pane) loader.load(getClass().getResource("/GeoMapping/GMaps.fxml").openStream());
        GMapsController controller = (GMapsController) loader.getController();

    }
    

    @FXML
    private void updateEntry(ActionEvent event) throws SQLException {
        try {
            String id = IDText.getText();
            String fname = firstText.getText();
            String lname = lastText.getText();
            String ad = addressText.getText();
            String email = emailText.getText();
            String sub = subjectText.getText();
            String fee = feeText.getText();
            String pay = paymentText.getText();
            String time = timeText.getText();

            String sqlUpdate = ("UPDATE STUDENTS SET id = '" + id + "', fName = '" + fname + "', "
                    + "lName = '" + lname + "', address='" + ad + "', email='" + email + "',subject='" + sub + "',"
                    + "fee='" + fee + "', payment='" + pay + "', timings='" + time + "' WHERE fname = ?");

            Connection conn = dbConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);

            pstmt.setString(1, firstSearchText.getText());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private AnchorPane root;

    @FXML
    private TextField IDText;

    @FXML
    private TextField firstText;

    @FXML
    private TextField lastText;

    @FXML
    private TextField addressText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField subjectText;

    @FXML
    private TextField feeText;

    @FXML
    private TextField paymentText;

    @FXML
    private TextField timeText;

    @FXML
    private TextField firstSearchText;

    @FXML
    private TextField lastSearchText;

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button loadButton;

    @FXML
    private TableView<StudentData> studentTable;

    @FXML
    private TableColumn<StudentData, String> IDColumn;

    @FXML
    private TableColumn<StudentData, String> firstNameColumn;

    @FXML
    private TableColumn<StudentData, String> lastNameColumn;

    @FXML
    private TableColumn<StudentData, String> addressColumn;

    @FXML
    private TableColumn<StudentData, String> emailColumn;

    @FXML
    private TableColumn<StudentData, String> subjectColumn;

    @FXML
    private TableColumn<StudentData, String> feeColumn;

    @FXML
    private TableColumn<StudentData, String> paymentColumn;

    @FXML
    private TableColumn<StudentData, String> timeColumn;

}
