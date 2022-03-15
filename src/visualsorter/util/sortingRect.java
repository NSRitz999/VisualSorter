/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualsorter.util;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author msawchuk9
 */
public class sortingRect extends Rectangle{
        public sortingRect(int dat, int numRects){
            data = dat;
            currentIndex = 0;
            super.setWidth(5);
            super.setHeight(dat);
        }
        
        public int getData(){
            return data;
        }
        
        public void setIndex(int index){
            currentIndex = index;
        }
        
        public int getIndex(){
            return currentIndex;
        }
        
        private int currentIndex;
        private final int data;
    }
