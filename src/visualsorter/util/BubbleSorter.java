/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualsorter.util;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author msawchuk9
 */
public class BubbleSorter {
    public BubbleSorter(Pane rPane){
        rectPane = rPane;
    }
    
    public List<ParallelTransition> beginSort(){
        List<ParallelTransition> ptList = new ArrayList();
        boolean isSorted = false;
        int currIndex = 0;
        while(!isSorted){
            isSorted = true;
            for(int i = 0; i < rectPane.getChildren().size() - 1; i++){
                if(((sortingRect) rectPane.getChildren().get(i)).getData() > 
                 ((sortingRect) rectPane.getChildren().get(i + 1)).getData()){
                    isSorted = false;
                    currIndex = i;
                    break;
                }
            }
            
            while(!isSorted && ((sortingRect) rectPane.getChildren().get(currIndex)).getData() > 
                 ((sortingRect) rectPane.getChildren().get(currIndex + 1)).getData()){
                ptList.add(startTransition(currIndex));
                ptList.add(midTransition(currIndex));
                ptList.add(endTransition(currIndex));
                
                swap(((sortingRect) rectPane.getChildren().get(currIndex)),
                     ((sortingRect) rectPane.getChildren().get(currIndex + 1)),
                     currIndex);
                
                currIndex++;
                if(currIndex == rectPane.getChildren().size() - 1)
                    break;
            }
        }
        
        for(int i = 0; i < rectPane.getChildren().size(); i++){
            sortingRect rect = (sortingRect) rectPane.getChildren().get(i);
            ParallelTransition pt = new ParallelTransition();
            FillTransition ft1 = new FillTransition(HLIGHT_TIME, rect, Color.RED, Color.BLUE);
            pt.getChildren().add(ft1);
            ptList.add(pt);
        }
        
        return ptList;
    }
    
    private void swap(sortingRect lhsRect, sortingRect rhsRect, int currIndex){
        rectPane.getChildren().remove(currIndex+1);
        rectPane.getChildren().set(currIndex, rhsRect);
        rectPane.getChildren().add(currIndex + 1, lhsRect);
    }
    
    private ParallelTransition startTransition(int currIndex){
        sortingRect lhsRect = (sortingRect) rectPane.getChildren().get(currIndex);
        sortingRect rhsRect = (sortingRect) rectPane.getChildren().get(currIndex + 1);
        
        FillTransition ft1 = new FillTransition(TIME);
        FillTransition ft2 = new FillTransition(TIME);
        ft1.setShape(lhsRect);
        ft2.setShape(rhsRect);
        ft1.setToValue(Color.GREEN);
        ft2.setToValue(Color.GREEN);
        
        return new ParallelTransition(ft1, ft2);
    }
    
    private ParallelTransition midTransition(int currIndex){
        sortingRect lhsRect = (sortingRect) rectPane.getChildren().get(currIndex);
        sortingRect rhsRect = (sortingRect) rectPane.getChildren().get(currIndex + 1);
        
        System.out.println("\nCurrentXPosition: " + (currIndex * 6 + 50));
        System.out.println("\nNextXPosition: " + ((currIndex + 1) * 6 + 50));
        int distance = ((currIndex + 1) * 6 + 50) - ((currIndex) * 6 + 50);
        
        TranslateTransition tt1 = new TranslateTransition(TIME);
        TranslateTransition tt2 = new TranslateTransition(TIME);
        tt1.setNode(lhsRect);
        tt2.setNode(rhsRect);
        tt1.setByX(distance);
        tt2.setByX(-distance);
        
        return new ParallelTransition(tt1, tt2);
    }
    
    private ParallelTransition endTransition(int currIndex){
        sortingRect lhsRect = (sortingRect) rectPane.getChildren().get(currIndex);
        sortingRect rhsRect = (sortingRect) rectPane.getChildren().get(currIndex + 1);
        
        FillTransition ft1 = new FillTransition(TIME);
        FillTransition ft2 = new FillTransition(TIME);
        ft1.setShape(lhsRect);
        ft2.setShape(rhsRect);
        ft1.setToValue(Color.BLACK);
        ft2.setToValue(Color.BLACK);
        
        return new ParallelTransition(ft1, ft2);
    }
    
    private Pane rectPane;
    private final Duration TIME = Duration.millis(10);
    private final Duration HLIGHT_TIME = Duration.millis(5);
}
