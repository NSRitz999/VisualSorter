/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualsorter.ui;

import java.util.ArrayList;
//import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Matthew Sawchuk
 */
public class SortingWidget extends Pane {
    public SortingWidget(int num){
        numOfRects = num;
        currentData = new ArrayList();
        baseLineRect = new Rectangle(700, 5);
        rectPane = new Pane();
        
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
//        super.getChildren().addAll(baseLineRect, LHSBar, RHSBar);
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
        System.out.println("Finished creating all rectangles!");
    }
    
    private int generateRandom(){
        int num = (int) (Math.random() * (500 - 1) + 1);
        while(currentData.contains(num)){
            System.out.println("Found rand in curr data!");
            num = (int) (Math.random() * (500 - 1) + 1);
            System.out.println("New int attempt: " + num);
        }
        return num;
    }
    
    private class sortingRect extends Rectangle{
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
        for(int i = 0; i < numOfRects; i++){
            ((sortingRect) rectPane.getChildren().get(i)).setFill(Color.RED);
            for(int j = 0; j < numOfRects; j++){
                if(((sortingRect) rectPane.getChildren().get(j)).getData() >
                        ((sortingRect) rectPane.getChildren().get(i)).getData()){
                    swap((sortingRect) rectPane.getChildren().get(j),
                            (sortingRect) rectPane.getChildren().get(i));
                    ((sortingRect) rectPane.getChildren().get(i)).setFill(Color.RED);
                }
            }
        }
    }
    
    private void swap(sortingRect lhsRect, sortingRect rhsRect){
        int i = lhsRect.getData();
        System.out.println("LHS: " + lhsRect.getData() + "; RHS: " + rhsRect.getData());
        lhsRect.setFill(Color.GREEN);
        rhsRect.setFill(Color.GREEN);
        lhsRect.setX(rhsRect.getX() * 6 + 50);
        rhsRect.setX(i * 6 + 50);
        lhsRect.setFill(Color.BLACK);
        rhsRect.setFill(Color.BLACK);
    }
    
    private int numOfRects;
    
    private ArrayList currentData;
    
    private final Rectangle baseLineRect;
    
    private final Rectangle LHSBar;
    
    private final Rectangle RHSBar;
    
    private Pane rectPane;
}
