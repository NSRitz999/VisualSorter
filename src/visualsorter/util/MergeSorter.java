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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import visualsorter.ui.AnimationController;
import visualsorter.ui.AnimationController.sortingRect;

/**
 *
 * @author Matthew
 */
public class MergeSorter extends SortingAlgorithm {
    public MergeSorter(Pane rPane){
        super.rectPane = rPane;
    }
    
    @Override
    public List<ParallelTransition> beginSort(){
    List<ParallelTransition> pList = new ArrayList();
        for(int i = 0; i < rectPane.getChildren().size(); i++){
            AnimationController.sortingRect rect = (AnimationController.sortingRect) rectPane.getChildren().get(i);
            ParallelTransition pt = new ParallelTransition();
            FillTransition ft1 = new FillTransition(super.HLIGHT_TIME, rect, Color.RED, Color.BLUE);
            pt.getChildren().add(ft1);
            pList.add(pt);
        }
        return pList;
    }
    
    private void mergeSort(int leftIndex, int rightIndex){
        if(leftIndex < rightIndex){
            int mid = leftIndex + (rightIndex - leftIndex) / 2;
            mergeSort(leftIndex, mid);
            mergeSort(mid + 1, rightIndex);
            merge(leftIndex, mid, rightIndex);
        }
    }
    
    private void merge(int left, int mid, int right){
        int size1 = mid - left + 1;
        int size2 = right - mid;
        
        ArrayList tempL = new ArrayList();
        ArrayList tempR = new ArrayList();
        
        for(int i = 0; i < size1; i++){
            tempL.add(rectPane.getChildren().get(left + i));
        }
        for(int j = 0; j < size2; j++){
            tempR.add(rectPane.getChildren().get(mid + 1 + j));
        }
        
        int i = 0, j = 0, k = left;
        
        while(i < size1 && j < size2){
            if(((sortingRect) tempL.get(i)).getData() < ((sortingRect) tempL.get(j)).getData()){
                
            } else {
            
            }
        }
    }
}
