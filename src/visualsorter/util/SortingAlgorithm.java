/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualsorter.util;

import java.util.List;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
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
    
    protected ParallelTransition startTransition(int i, int j){
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
    
    protected ParallelTransition endTransition(int i, int j){
        AnimationController.sortingRect lhsRect = (AnimationController.sortingRect) rectPane.getChildren().get(i);
        AnimationController.sortingRect rhsRect = (AnimationController.sortingRect) rectPane.getChildren().get(j);
        
        FillTransition ft1 = new FillTransition(TIME);
        FillTransition ft2 = new FillTransition(TIME);
        ft1.setShape(lhsRect);
        ft2.setShape(rhsRect);
        ft1.setToValue(Color.BLACK);
        ft2.setToValue(Color.BLACK);
        
        return new ParallelTransition(ft1, ft2);
    }
    
    protected Pane rectPane;
    protected final Duration TIME = Duration.millis(10);
    protected final Duration HLIGHT_TIME = Duration.millis(5);
}
