/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualsorter.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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
        sliderHBox = new HBox();
        rectSliderBox = new VBox();
        
        createSliderBox();
        
        toolBar.getChildren().addAll(rectSliderBox);
        
        super.setBottom(toolBar);
        super.setWidth(USE_PREF_SIZE);
        super.setHeight(USE_PREF_SIZE);
        BorderPane.setAlignment(super.getBottom(), Pos.CENTER);
    }
    
    private void createSliderBox(){
        sliderLabel = new Label("Number of Rectangles:");
        rectSlider = new Slider(1, 100, 25);
        rectSlider.setShowTickMarks(true);
        rectSlider.setBlockIncrement(1);
        sliderHBox.getChildren().addAll(rectSlider);
        rectSliderBox.getChildren().addAll(sliderLabel, sliderHBox);
    }
    
    private final HBox toolBar;
    
    private final HBox sliderHBox;
    
    private final VBox rectSliderBox;
    
    private Slider rectSlider;
    
    private Label sliderLabel;
}
