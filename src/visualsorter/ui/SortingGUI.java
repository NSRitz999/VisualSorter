/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualsorter.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author msawchuk9
 */
public class SortingGUI extends BorderPane {
    
    public SortingGUI(){
        toolBar = new HBox();
        rectSliderBox = new VBox();
        
        createSliderBox();
        
        toolBar.getChildren().addAll(rectSliderBox);
        toolBar.setAlignment(Pos.CENTER);
        
        super.setBottom(toolBar);
        BorderPane.setAlignment(super.getBottom(), Pos.CENTER);
    }
    
    private void createSliderBox(){
        sliderLabel = new Label("Number of Rectangles:");
        rectSlider = new Spinner(1, 100, 25);
        
        rectSlider.setPrefWidth(100);
        
        rectSliderBox.getChildren().addAll(sliderLabel, rectSlider);
        rectSliderBox.setAlignment(Pos.CENTER);
        rectSliderBox.setSpacing(5);
    }
    
    private final HBox toolBar;
    
    private final VBox rectSliderBox;
    
    private Spinner rectSlider;
    
    private Label sliderLabel;
    
    private Label currNumRectangles;
}
