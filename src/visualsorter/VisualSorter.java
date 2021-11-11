/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualsorter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import visualsorter.ui.SortingGUI;

/**
 *
 * @author Matthew Sawchuk
 */
public class VisualSorter extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        SortingGUI sortGUI = new SortingGUI();
        root.getChildren().add(sortGUI);
        
        root.setAlignment(Pos.BASELINE_CENTER);
        
        Scene scene = new Scene(root, 720, 480);
        
        primaryStage.setTitle("Visual Sorter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
