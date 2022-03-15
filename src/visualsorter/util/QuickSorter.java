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
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author msawchuk9
 */
public class QuickSorter {
    public QuickSorter(Pane rPane){
        rectPane = rPane;
    }
    
    public List<ParallelTransition> beginSort(){
        List<ParallelTransition> pList = new ArrayList();
        quickSort(pList, 0, rectPane.getChildren().size() - 1);
        for(int i = 0; i < rectPane.getChildren().size(); i++){
            sortingRect rect = (sortingRect) rectPane.getChildren().get(i);
            ParallelTransition pt = new ParallelTransition();
            FillTransition ft1 = new FillTransition(HLIGHT_TIME, rect, Color.RED, Color.BLUE);
            pt.getChildren().add(ft1);
            pList.add(pt);
        }
        return pList;
    }
    
    public void quickSort(List aList, int lowIndex, int highIndex){
        if(lowIndex < highIndex){
            int partition = partition(aList, lowIndex, highIndex);
            quickSort(aList, lowIndex, partition - 1);
            quickSort(aList, partition + 1, highIndex);
        }
    }
    
    private int partition(List aList, int low, int high){
        sortingRect pivot = (sortingRect) rectPane.getChildren().get(high);
        int i = low - 1;
        for(int j = low; j < high; j++){
            if(((sortingRect) rectPane.getChildren().get(j)).getData() < pivot.getData()){
                i++;
                aList.add(startTransition(i, j));
                aList.add(midTransition(i, j));
                aList.add(endTransition(i, j));
                swap((sortingRect) rectPane.getChildren().get(i),
                        (sortingRect) rectPane.getChildren().get(j),
                        i,
                        j);
            }
        }
        aList.add(startTransition(i+1, high));
        aList.add(midTransition(i+1, high));
        aList.add(endTransition(i+1, high));
        swap((sortingRect) rectPane.getChildren().get(i+1),
                (sortingRect) rectPane.getChildren().get(high),
                i+1,
                high);
        return i + 1;
    }
    
    private ParallelTransition startTransition(int i, int j){
        sortingRect lhsRect = (sortingRect) rectPane.getChildren().get(i);
        sortingRect rhsRect = (sortingRect) rectPane.getChildren().get(j);
        
        FillTransition ft1 = new FillTransition(TIME);
        FillTransition ft2 = new FillTransition(TIME);
        ft1.setShape(lhsRect);
        ft2.setShape(rhsRect);
        ft1.setToValue(Color.GREEN);
        ft2.setToValue(Color.GREEN);
        
        return new ParallelTransition(ft1, ft2);
    }
    
    private ParallelTransition midTransition(int i, int j){
        sortingRect lhsRect = (sortingRect) rectPane.getChildren().get(i);
        sortingRect rhsRect = (sortingRect) rectPane.getChildren().get(j);
        
        int distance = ((j) * 6 + 50) - ((i) * 6 + 50);
        
        TranslateTransition tt1 = new TranslateTransition(TIME);
        TranslateTransition tt2 = new TranslateTransition(TIME);
        tt1.setNode(lhsRect);
        tt2.setNode(rhsRect);
        tt1.setByX(distance);
        tt2.setByX(-distance);
        
        return new ParallelTransition(tt1, tt2);
    }
    
    private ParallelTransition endTransition(int i, int j){
        sortingRect lhsRect = (sortingRect) rectPane.getChildren().get(i);
        sortingRect rhsRect = (sortingRect) rectPane.getChildren().get(j);
        
        FillTransition ft1 = new FillTransition(TIME);
        FillTransition ft2 = new FillTransition(TIME);
        ft1.setShape(lhsRect);
        ft2.setShape(rhsRect);
        ft1.setToValue(Color.BLACK);
        ft2.setToValue(Color.BLACK);
        
        return new ParallelTransition(ft1, ft2);
    }
    
    private void swap(sortingRect lhsRect, sortingRect rhsRect, int i, int j){
        if(i == j) return;
        rectPane.getChildren().remove(j);
        rectPane.getChildren().set(i, rhsRect);
        rectPane.getChildren().add(j, lhsRect);
    }
    
    private Pane rectPane;
    private final Duration TIME = Duration.millis(10);
    private final Duration HLIGHT_TIME = Duration.millis(5);
}
