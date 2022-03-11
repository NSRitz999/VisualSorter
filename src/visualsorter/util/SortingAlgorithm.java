/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualsorter.util;

import java.util.List;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import visualsorter.ui.AnimationController;
import visualsorter.ui.AnimationController.sortingRect;

/**
 *
 * @author Matthew
 */
public abstract class SortingAlgorithm {    
    public abstract List<ParallelTransition> beginSort();
    
    protected ParallelTransition startTransition(int leftChild, int rightChild){
        sortingRect lhsRect = (sortingRect) rectPane.getChildren().get(leftChild);
        sortingRect rhsRect = (sortingRect) rectPane.getChildren().get(rightChild);
        
        FillTransition ft1 = new FillTransition(TIME);
        FillTransition ft2 = new FillTransition(TIME);
        ft1.setShape(lhsRect);
        ft2.setShape(rhsRect);
        ft1.setToValue(Color.GREEN);
        ft2.setToValue(Color.GREEN);
        
        return new ParallelTransition(ft1, ft2);
    }
    
    protected ParallelTransition startSingleTransition(sortingRect rectChild){
        FillTransition ft1 = new FillTransition(TIME);
        ft1.setShape(rectChild);
        ft1.setToValue(Color.GREEN);
        
        return new ParallelTransition(ft1);
    }
    
    protected ParallelTransition midTransition(int leftChild, int rightChild){
        sortingRect lhsRect = (sortingRect) rectPane.getChildren().get(leftChild);
        sortingRect rhsRect = (sortingRect) rectPane.getChildren().get(rightChild);
        
        int distance = (rightChild * 6 + 50) - (leftChild * 6 + 50);
        
        TranslateTransition tt1 = new TranslateTransition(TIME);
        TranslateTransition tt2 = new TranslateTransition(TIME);
        tt1.setNode(lhsRect);
        tt2.setNode(rhsRect);
        tt1.setByX(distance);
        tt2.setByX(-distance);
        
        return new ParallelTransition(tt1, tt2);
    }
    
    protected ParallelTransition endTransition(int leftChild, int rightChild){
        AnimationController.sortingRect lhsRect = (AnimationController.sortingRect) rectPane.getChildren().get(leftChild);
        AnimationController.sortingRect rhsRect = (AnimationController.sortingRect) rectPane.getChildren().get(rightChild);
        
        FillTransition ft1 = new FillTransition(TIME);
        FillTransition ft2 = new FillTransition(TIME);
        ft1.setShape(lhsRect);
        ft2.setShape(rhsRect);
        ft1.setToValue(Color.BLACK);
        ft2.setToValue(Color.BLACK);
        
        return new ParallelTransition(ft1, ft2);
    }
    
    protected ParallelTransition endSingleTransition(sortingRect sortingRectChild){
        AnimationController.sortingRect rectChild = (AnimationController.sortingRect) rectPane.getChildren().get(rectPane.getChildren().indexOf(sortingRectChild));
        FillTransition ft1 = new FillTransition(TIME);
        ft1.setShape(rectChild);
        ft1.setToValue(Color.BLACK);
        
        return new ParallelTransition(ft1);
    }
    
    protected Pane rectPane;
    protected final Duration TIME = Duration.millis(10);
    protected final Duration HLIGHT_TIME = Duration.millis(5);
}
