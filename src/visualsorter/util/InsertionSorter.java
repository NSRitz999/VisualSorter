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
import visualsorter.ui.AnimationController.sortingRect;

/**
 *
 * @author msawchuk9
 */
public class InsertionSorter {
    public InsertionSorter(Pane rPane){
        rectPane = rPane;
    }
    
    public List<ParallelTransition> beginSort(){
        List<ParallelTransition> pList = new ArrayList();
        
        for(int i = 1; i < rectPane.getChildren().size(); i++){
            int currIndex = i - 1;
            sortingRect saveRect = (sortingRect) rectPane.getChildren().get(i);
            rectPane.getChildren().remove(i);
            
            while(currIndex >= 0 && ((sortingRect) rectPane.getChildren().get(currIndex)).getData() > saveRect.getData()){
                sortingRect temp = (sortingRect) rectPane.getChildren().get(currIndex);
                rectPane.getChildren().remove(currIndex);
                rectPane.getChildren().add(currIndex, temp);
                pList.add(moveRectangle(temp, 6));
                currIndex--;
            }
            
            if(currIndex < rectPane.getChildren().size())
                rectPane.getChildren().add(currIndex + 1, saveRect);
            else
                rectPane.getChildren().add(saveRect);
            pList.add(moveRectangle(saveRect, (i - (currIndex + 1)) * -6));
        }
        
        for(int i = 0; i < rectPane.getChildren().size(); i++){
            sortingRect rect = (sortingRect) rectPane.getChildren().get(i);
            ParallelTransition pt = new ParallelTransition();
            FillTransition ft1 = new FillTransition(HLIGHT_TIME, rect, Color.RED, Color.BLUE);
            pt.getChildren().add(ft1);
            pList.add(pt);
        }
        
        return pList;
    }
    
    public ParallelTransition moveRectangle(sortingRect rect, double newPos){
        TranslateTransition TT = new TranslateTransition(TIME);
        ParallelTransition PT = new ParallelTransition();
        TT.setNode(rect);
        TT.setByX(newPos);
        PT.getChildren().add(TT);
        return PT;
    }
    
    private Pane rectPane;
    private final Duration TIME = Duration.millis(10);
    private final Duration HLIGHT_TIME = Duration.millis(5);
}
