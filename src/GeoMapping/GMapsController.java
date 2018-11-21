/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeoMapping;

import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.MarkerPlace;
import com.teamdev.jxmaps.javafx.MapView;
import dbUtil.dbConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ggupt
 */
public class GMapsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private BorderPane borderMap;

    private dbConnection dc;
    private final String sql = "SELECT Address FROM Students ";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.dc = new dbConnection();

        MapView.InitJavaFX();
        final MapView mapView = new MapView();
        final Map map = mapView.getMap();

        mapView.setOnMapReadyHandler(new MapReadyHandler() {

            @Override
            public void onMapReady(MapStatus status) {
                if (status == MapStatus.MAP_STATUS_OK) {
                    

                    // Creating a map options object
                    MapOptions options = new MapOptions();
                    // Creating a map type control options object
                    MapTypeControlOptions controlOptions = new MapTypeControlOptions();
                    // Changing position of the map type control

                    map.setCenter(new LatLng(43.7106696, -79.8244042));
                    // Setting initial zoom value
                    map.setZoom(13.0);
                    // Creating a new marker object
                    Marker marker = new Marker(map);
                    // Setting marker position
                    marker.setPosition(map.getCenter());
                    // Creating info window, that will be initially displayed on the marker
                    final InfoWindow infoWindow = new InfoWindow(map);
                    // Setting info window text
                    infoWindow.setContent("Pathways 2 Excellence");
                    // Showing info windows under the marker
                    infoWindow.open(map, marker);
                    // Adding event listener that intercepts clicking on map
               

                }
            }

        });
        Stage mStage = new Stage();
        Scene scene = new Scene(new BorderPane(mapView), 1200, 700);
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                final Marker marker = new Marker(map);
                try {
                    Connection conn = dbConnection.getConnection();
                    ResultSet rs = conn.createStatement().executeQuery("Select Address FROM Students");
                  
                    while (rs.next()){
                        
                        
                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(GMapsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                          
            }
        });
        mStage.setTitle("Map");
        mStage.setScene(scene);
        mStage.show();

    }

}
