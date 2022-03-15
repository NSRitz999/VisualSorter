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

/**
 *
 * @author Matthew Sawchuk
 */
public class MergeSorter extends SortingAlgorithm {
    public MergeSorter(Pane rPane){
        super.rectPane = rPane;
    }
    
    @Override
    public List<ParallelTransition> beginSort(){
        pList = new ArrayList();
 
        //Sets up the indexes of each rectangle
        for(int i = 0; i < rectPane.getChildren().size(); i++){
            ((sortingRect) rectPane.getChildren().get(i)).setIndex(i);
        }

        mergeSort(0, rectPane.getChildren().size() - 1);
        
        //Highlights all rects to show completion
        for(int i = 0; i < rectPane.getChildren().size(); i++){
            sortingRect rect = (sortingRect) rectPane.getChildren().get(i);
            
            ParallelTransition pt = new ParallelTransition();
            FillTransition ft1 = new FillTransition(super.HLIGHT_TIME, rect, Color.RED, Color.BLUE);
            
            pt.getChildren().add(ft1);
            pList.add(pt);
        }
        return pList;
    }
    
    /**
     * Sorts a list of rectangles passed through the constructor with the merge sort algorithm.
     * @param leftIndex
     * @param rightIndex 
     */
    private void mergeSort(int leftIndex, int rightIndex){
        if(leftIndex < rightIndex){
            int mid = leftIndex + (rightIndex - leftIndex) / 2;
            mergeSort(leftIndex, mid);
            mergeSort(mid + 1, rightIndex);
            merge(leftIndex, mid, rightIndex);
        }
    }
    
    /**
     * Recursive function that performs all of the sorting in merge sort.
     * @param left
     * @param mid
     * @param right 
     */
    private void merge(int left, int mid, int right){
        int sizeLeft = mid - left + 1;
        int sizeRight = right - mid;
        
        ArrayList tempL = new ArrayList();
        ArrayList tempR = new ArrayList();
        
        //Initializes both tempL and tempR with the rectPane children
        for(int i = 0; i < sizeLeft; i++){
            tempL.add(rectPane.getChildren().get(left + i));
        }
        for(int j = 0; j < sizeRight; j++){
            tempR.add(rectPane.getChildren().get(mid + 1 + j));
        }
        
        int i = 0, j = 0, k = left;
        while(i < sizeLeft && j < sizeRight){
            if( (((sortingRect) tempL.get(i)).getData() < ((sortingRect) tempR.get(j)).getData())){
                addNewTransitionSet(k, (sortingRect) tempL.get(i));
                swap(k, (sortingRect) tempL.get(i));
                i++;
            } else {
                addNewTransitionSet(k, (sortingRect) tempR.get(j));
                swap(k, (sortingRect) tempR.get(j));
                j++;
            }
            
            k++;
        }
        
        while(i < sizeLeft){
            addNewTransitionSet(k, (sortingRect) tempL.get(i));
            swap(k, (sortingRect) tempL.get(i));
            i++;
            k++;
        }
        
        while(j < sizeRight){
            addNewTransitionSet(k, (sortingRect) tempR.get(j));
            swap(k, (sortingRect) tempR.get(j));
            j++;
            k++;
        }
    }
    
    private void addNewTransitionSet(int leftChild, sortingRect rightChild){
        pList.add(super.startSingleTransition(rightChild));
        pList.add(midSingleTransition(leftChild, rightChild));
        pList.add(super.endSingleTransition(rightChild));
    }
    
    private void swap(int leftChild, sortingRect rightChild){
        rectPane.getChildren().remove(rightChild);
        rectPane.getChildren().add(leftChild, rightChild);
        rightChild.setIndex(leftChild);
    }
    
    public ParallelTransition midSingleTransition(int index, sortingRect rect){
        double distance = getDistance(index, rect);
        
        TranslateTransition TT = new TranslateTransition(TIME);
        ParallelTransition PT = new ParallelTransition();
        TT.setNode(rect);
        System.out.println("Translation distance: " + distance);
        TT.setByX(distance);
        PT.getChildren().add(TT);
        return PT;
    }
    
    private double getDistance(int index, sortingRect rectChild){
        int childIndex = rectPane.getChildren().indexOf(rectChild);
        System.out.println("\nData item: " + rectChild.getData());
        System.out.println("leftIndex: " + index + "; rightIndex: " + childIndex);
        double distance = (index * 6.0 + 50) - (rectChild.getIndex() * 6.0 + 50);
        return distance;
    }
    
    private List<ParallelTransition> pList;
}
