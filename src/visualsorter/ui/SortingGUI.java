/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualsorter.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
        generateRectsBtn = new Button("Generate");
        bubbleSortBtn = new Button("Bubble Sort");
        quickSortBtn = new Button("Quick Sort");
        insertSortBtn = new Button("Insertion Sort");
        sortButton = new Button("Sort");
        sorter = new AnimationController(25);
        algorithmBox = new ChoiceBox();
        
        createSliderBox();
        addHandlers();
        createAlgorithmBox();
        
        toolBar.getChildren().addAll(rectSliderBox, generateRectsBtn, algorithmBox, sortButton);
        toolBar.setAlignment(Pos.BOTTOM_CENTER);
        toolBar.setSpacing(10);
        
        super.setBottom(toolBar);
        super.setCenter(sorter);
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
    
    private void addHandlers(){
        generateRectsBtn.setOnAction(e -> {
            sorter.update((int) rectSlider.getValue());
        });
        
        bubbleSortBtn.setOnAction(e -> {
            sorter.bubbleSort();
            
        });
        
        quickSortBtn.setOnAction(e -> {
            sorter.quickSort();
        });
        
        insertSortBtn.setOnAction(e -> {
            sorter.insertionSort();
        });
        
        sortButton.setOnAction(e -> {
            String algType = (String) algorithmBox.getValue();
            switch(algType){
                case "Bubble Sort":
                    sorter.bubbleSort();
                    break;
                case "Quick Sort":
                    sorter.quickSort();
                    break;
                case "Insertion Sort":
                    sorter.insertionSort();
                    break;
                case "Merge Sort":
                    sorter.mergeSort();
                    break;
                default:
                    break;
                    
            }
        });
    }
    
    private void createAlgorithmBox(){
        algorithmBox.getItems().addAll("Bubble Sort",
                                       "Quick Sort",
                                       "Insertion Sort",
                                       "Merge Sort");
    }
    
    private AnimationController sorter;
    
    private final HBox toolBar;
    
    private final VBox rectSliderBox;
    
    private final ChoiceBox algorithmBox;
    
    private Spinner rectSlider;
    
    private Button generateRectsBtn;
    
    private Button bubbleSortBtn;
    
    private Button quickSortBtn;
    
    private Button insertSortBtn;
    
    private Button sortButton;
    
    private Label sliderLabel;
    
    private Label currNumRectangles;
}
