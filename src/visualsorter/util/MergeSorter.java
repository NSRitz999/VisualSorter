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
        pList = new ArrayList();
//        dataList = new ArrayList();
//        
//        for(int i = 0; i < rectPane.getChildren().size(); i++){
//            dataList.add(i, ((sortingRect) rectPane.getChildren().get(i)).getData());
//        }
        
//        displayList(dataList);
        
        for(int i = 0; i < rectPane.getChildren().size(); i++){
            ((sortingRect) rectPane.getChildren().get(i)).setIndex(i);
        }

        mergeSort(0, rectPane.getChildren().size() - 1);
        
//        displayList(dataList);
    
        //Highlights all rects to show completion
        for(int i = 0; i < rectPane.getChildren().size(); i++){
            AnimationController.sortingRect rect = (AnimationController.sortingRect) rectPane.getChildren().get(i);
            
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
//            tempL.add(dataList.get(left + i));
        }
        System.out.println("Size of rectPane: " + rectPane.getChildren().size() + "; Size of right: " + sizeRight);
        for(int j = 0; j < sizeRight; j++){
//            tempR.add(dataList.get(mid + 1 + j));
            tempR.add(rectPane.getChildren().get(mid + 1 + j));
        }
        
        System.out.print("New Left Array: ");
        displaySortingList(tempL);
        System.out.print("New Right Array: ");
        displaySortingList(tempR);
        
        int i = 0, j = 0, k = left;
//        System.out.println("Entering while loop!");
        while(i < sizeLeft && j < sizeRight){
            if( (((sortingRect) tempL.get(i)).getData() < ((sortingRect) tempR.get(j)).getData()) 
                /* (int) tempL.get(i) < (int) tempR.get(j) */){
                System.out.print("Condition 1 passed: " + ((sortingRect) tempL.get(i)).getData() + " < " + ((sortingRect) tempR.get(j)).getData() + "\n");
                addNewTransitionSet(k, (sortingRect) tempL.get(i));
                swap(k, (sortingRect) tempL.get(i));
                i++;
            } else {
                System.out.print("Condition 2 passed: " + ((sortingRect) tempL.get(i)).getData() + " > " + ((sortingRect) tempR.get(j)).getData() + "\n");
                addNewTransitionSet(k, (sortingRect) tempR.get(j));
                swap(k, (sortingRect) tempR.get(j));
                j++;
            }
            
            k++;
        }
        System.out.println("Exiting while loop!");
        
        while(i < sizeLeft){
            System.out.println("While i < sizeLeft");
            addNewTransitionSet(k, (sortingRect) tempL.get(i));
            swap(k, (sortingRect) tempL.get(i));
            i++;
            k++;
        }
        
        while(j < sizeRight){
            System.out.println("While j < sizeRight");
            addNewTransitionSet(k, (sortingRect) tempR.get(j));
            swap(k, (sortingRect) tempR.get(j));
            j++;
            k++;
        }
        displayRectPaneList();
    }
    
    private void addNewTransitionSet(int leftChild, sortingRect rightChild){
        //Create transitions
        pList.add(super.startSingleTransition(rightChild));
        System.out.println("Distance: " + getDistance(leftChild, rightChild));
        pList.add(midSingleTransition(leftChild, rightChild));
        pList.add(super.endSingleTransition(rightChild));
    }
    
    private void swapRectangles(int leftChild, int rightChild){
        sortingRect saveLeft = (sortingRect) rectPane.getChildren().get(leftChild);
        sortingRect saveRight = (sortingRect) rectPane.getChildren().get(rightChild);
        
        System.out.println("\nPerforming swap between: " + saveLeft.getData() + " and " + saveRight.getData());
        System.out.println("Idexes to be swapped: " + leftChild + ", " + rightChild);
        
        //Prevent adding duplicate nodes into rectPane
        rectPane.getChildren().remove(leftChild);
        if(rightChild != 0)
            rectPane.getChildren().remove(rightChild - 1);
        else
            rectPane.getChildren().remove(rightChild);
            
        //Adding saved nodes into rectPane
        rectPane.getChildren().add(leftChild, saveRight);
        rectPane.getChildren().add(rightChild, saveLeft);
    }
    
    private void displayList(ArrayList arrList){
        System.out.print("Data{");
        for(int i = 0; i < arrList.size(); i++){
            System.out.print(arrList.get(i));
            if(i != arrList.size() - 1)
                System.out.print(", ");
        }
        System.out.print("}\n");
    }
    
    private void swap(int leftChild, sortingRect rightChild){
        rectPane.getChildren().remove(rightChild);
        rectPane.getChildren().add(leftChild, rightChild);
        rightChild.setIndex(leftChild);
    }
    
    private void displaySortingList(ArrayList arrList){
        System.out.print("Data{");
        for(int i = 0; i < arrList.size(); i++){
            System.out.print(((sortingRect) arrList.get(i)).getData());
            if(i != arrList.size() - 1)
                System.out.print(", ");
        }
        System.out.print("}\n");
    }
    
    private void displayRectPaneList(){
        System.out.print("Data<rectPane>{");
        for(int i = 0; i < rectPane.getChildren().size(); i++){
            System.out.print(((sortingRect) rectPane.getChildren().get(i)).getData());
            if(i != rectPane.getChildren().size() - 1){
                System.out.print(", ");
            }
        }
        System.out.print("}\n");
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
    
    //temp variables
    private ArrayList dataList;
}
