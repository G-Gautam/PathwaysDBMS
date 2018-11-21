package LoginApp;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class LoginApp  extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = (Pane) FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.setTitle ("Pathways 2 Excellence");
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
