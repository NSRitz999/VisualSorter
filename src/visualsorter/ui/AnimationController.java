/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualsorter.ui;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
//import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import visualsorter.util.BubbleSorter;
import visualsorter.util.InsertionSorter;
import visualsorter.util.MergeSorter;
import visualsorter.util.QuickSorter;

/**
 *
 * @author Matthew Sawchuk
 */
public class AnimationController extends Pane {
    public AnimationController(int num){
        numOfRects = num;
        currentData = new ArrayList();
        baseLineRect = new Rectangle(700, 5);
        rectPane = new Pane();
        seqT = new SequentialTransition();
        
        LHSBar = new Rectangle(5, 500);
        RHSBar = new Rectangle(5, 500);
        baseLineRect.setFill(Color.GRAY);
        baseLineRect.setY(500);
        LHSBar.setFill(Color.CADETBLUE);
        LHSBar.setX(45);
        RHSBar.setFill(Color.CADETBLUE);
        RHSBar.setX(650);
        
        super.getChildren().addAll(baseLineRect, LHSBar, RHSBar, rectPane);
        createRectangles(); 
    }
    
    public void update(int num){
        numOfRects = num;
        rectPane.getChildren().clear();
        currentData.clear();
        createRectangles();
    }
    
    private void createRectangles(){
        for(int i = 0; i < numOfRects; i++){
            int newData = generateRandom();
            sortingRect newRect = new sortingRect(newData, numOfRects);
            rectPane.getChildren().add(newRect);
            newRect.setX(i * 6 + 50);
            newRect.setY(500 - newRect.getHeight());
            currentData.add(newData);
        }
    }
    
    private int generateRandom(){
        int num = (int) (Math.random() * (500 - 1) + 1);
        while(currentData.contains(num)){
            num = (int) (Math.random() * (500 - 1) + 1);
        }
        return num;
    }
    
    public class sortingRect extends Rectangle{
        public sortingRect(int dat, int numRects){
            data = dat;
            super.setWidth(5);
            super.setHeight(dat);
        }
        
        public int getData(){
            return data;
        }
        
        private final int data;
    }
    
    public void bubbleSort(){
        displayRectangles();
        BubbleSorter bs = new BubbleSorter(rectPane);
        SequentialTransition sq = new SequentialTransition();
        sq.getChildren().addAll(bs.beginSort());
        sq.play();
        displayRectangles();
    }
    
    public void quickSort(){
        QuickSorter qs = new QuickSorter(rectPane);
        SequentialTransition sq = new SequentialTransition();
        sq.getChildren().addAll(qs.beginSort());
        sq.play();
    }
    
    public void insertionSort(){
        System.out.print("Pre-");
        displayRectangles();
        InsertionSorter is = new InsertionSorter(rectPane);
        SequentialTransition sq = new SequentialTransition();
        sq.getChildren().addAll(is.beginSort());
        sq.play();
        System.out.print("Post-");
        displayRectangles();
        
    }
    
    public void mergeSort(){
        MergeSorter ms = new MergeSorter(rectPane);
        SequentialTransition sq = new SequentialTransition();
        sq.getChildren().addAll(ms.beginSort());
        sq.play();
        displayRectangles();
    }
    
    private void displayRectangles(){
        System.out.print("Data{");
        for(int i = 0; i < rectPane.getChildren().size(); i++){
            System.out.print(((sortingRect) rectPane.getChildren().get(i)).getData());
            if(i != rectPane.getChildren().size() - 1)
                System.out.print(", ");
        }
        System.out.print("}\n");
    }
    
    private int numOfRects;
    
    private ArrayList currentData;
    
    private final SequentialTransition seqT;
    
    private final Rectangle baseLineRect;
    
    private final Rectangle LHSBar;
    
    private final Rectangle RHSBar;
    
    private Pane rectPane;
    
    private final double TRANSITION_DURATION = 10.0;
}
