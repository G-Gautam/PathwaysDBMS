package LoginApp;

import Admin.AdminController;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController implements Initializable {

  
    LoginModel loginModel = new LoginModel();

    @FXML
    private Label dbStatus;

    @FXML
    private TextField username;

    @FXML
    private Button loginButton;
    
    @FXML
    private PasswordField password; 
    
    @FXML
    private Label labelStatus;

   
    
    public void initialize (URL url, ResourceBundle rb){
        if(this.loginModel.isDatabaseConnected()){
            this.dbStatus.setText("Connected");
        } else{
            this.dbStatus.setText("Not Connected To Database");
        }
    }
    
    public void Login(ActionEvent event) throws Exception{
     try{
            if(this.loginModel.isLogin(this.username.getText(), this.password.getText()))
            {
                Stage stage = (Stage)this.loginButton.getScene().getWindow();
                stage.close(); 
                Admin();
            }
            else{
                this.labelStatus.setText("Wrong Username or Password");
            }
     }
     catch(Exception localException){
         
     }
    }
    
    public void Admin() throws IOException{
        try{
         Stage adminstage = new Stage();
                FXMLLoader loader = new FXMLLoader();

                Pane root = (Pane)loader.load(getClass().getResource("/Admin/Admin.fxml").openStream());

                AdminController  adminController =  (AdminController)loader.getController();
                Scene scene = new Scene(root);
                adminstage.setScene(scene);
                adminstage.setTitle("Admin Dashboard");
                adminstage.setResizable(false);
                adminstage.show();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
    
    
}
