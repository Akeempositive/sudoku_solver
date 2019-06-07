package sudoku;

import java.util.*;

public class BestValue{

    private static void printArray(ArrayList<Integer> bestValues) {
        if(bestValues.isEmpty()){
           // System.out.println("No valid value can be added");
            return;
        }
        for(Integer a : bestValues){
           // System.out.println(a);
        }
    }
    
    private int i, j;
    private ArrayList<Integer> bestValues;
    
    public BestValue(int i, int j, Integer[][]v){
        this.i=i;
        this.j=j;
        bestValues=  getBestValues(i,j, v, true);
    }
    
    public BestValue(){}
    
    public int getI(){
        return i;
    }
    
    public ArrayList<Integer>getValues(){
        return bestValues;
    }
    
    public int getValueSize(){
        return bestValues.size();
    }
    
    public int getJ(){
        return j;
    }
    
    private ArrayList<Integer> getBestValues(int i, int j, Integer [][]values, boolean look){
         if(values.length==1){
             ArrayList<Integer> a = new ArrayList<Integer>();
             a.add(1);
             return a;
         }
        ArrayList<Integer> rowPossible = getRowPos(values, i);
        ArrayList<Integer>colPossible=getColPos(values, j);
        int l = values.length;
        if(l<=3||l==7||l==5){
            if(look){
                return lookAhead(i,j,getIntersection(colPossible, rowPossible), values, values.length);
            }
            return getIntersection(colPossible, rowPossible);
        } else {
            ArrayList<Integer> cellPossible=getCellPossible(values, i, j, values.length);
            cellPossible= getIntersection(colPossible, rowPossible, cellPossible);
            if(look){
                return lookAhead(i, j, cellPossible,values, values.length);
            } else {
                return cellPossible;
            }
        }
    }
     
    private ArrayList<Integer> getRowPos(Integer [][]values , int i){
        ArrayList<Integer> pos= new ArrayList<Integer>();
        Integer []row1=values[i];
        ArrayList<Integer> row= new ArrayList<Integer>();
        Collections.addAll(row,row1); 
        int i1=1;
        while(i1<values.length+1){
            if(!row.contains(i1)){
                pos.add(i1);
            } 
            i1++;
        }
        return pos;
    }

    private ArrayList<Integer> getColPos(Integer[][] values, int j) {
        ArrayList<Integer> pos = new ArrayList<Integer>();
        int i1=0;
        ArrayList<Integer>col = new ArrayList<Integer>();
        while(i1<values.length){
            if(values[i1][j]!=0){
                col.add(values[i1][j]);
            }
            i1++;
        }
        i1=1;
        while(i1<values.length+1){
            if(!col.contains(i1)){
                pos.add(i1);
            }
            i1++;
        }
        //System.out.println("Column possible");
        return pos;
    }

    private ArrayList<Integer> getIntersection(ArrayList<Integer>colPossible, ArrayList<Integer> rowPossible) {
        Set<Integer> inte= new HashSet<Integer>();
        for(Integer k : colPossible){
            for(Integer s: rowPossible){
               if(s==k){
                    if(!inte.contains(s)){
                        inte.add(s);
                    }
                }
            }
        }
        ArrayList<Integer> k = new ArrayList<Integer>(inte);
       return k;
    }
    
    private ArrayList<Integer> getIntersection(ArrayList<Integer>colPossible, ArrayList<Integer> rowPossible, ArrayList<Integer> a) {
        Set<Integer> inte= new HashSet<Integer>();
        for(Integer k : colPossible){
            for(Integer s: rowPossible){
                if(s==k){
                   for(Integer b: a){
                     if(b==s){
                            inte.add(s);
                        }
                    }
                }
            }
        }
        ArrayList<Integer> k = new ArrayList<Integer>(inte);
       return k;
    }

    private Integer [][] array;
    
    void setArray(){
        array= new Integer[2][2];
        array[0][0]=1;
        array[0][1]=2;
        array[1][0]=0;
        array[1][1]=1;
    }

    private ArrayList<Integer> getCellPossible(Integer[][] values, int i, int j, int length) {
        ArrayList<Integer> pos = new ArrayList<Integer>();
        int row, column;
        if(length%2==0){
            row=2;
            column=length/2;
        } else {
            row=3;
            column=3;
        }
        int rowStart = (row* (i/row));
        int columnStart= (column * (j/column));
        int i1=0;
        while(i1<row){
            int j1=0;
            while(j1<column){
                if(values[rowStart+i1][columnStart+j1]!=0){
                    pos.add(values[rowStart+i1][columnStart+j1]);
                }
                j1++;
            }
            i1++;
        }
        i1=1;
        while(i1<=length){
            if(pos.contains(i1)){
                pos.remove(new Integer(i1));
            } else {
                pos.add(i1);
            }
            i1++;
        }
        return pos;
    }
    
    public static void main(String args[]){
        int i=0, j=0;
        while(i<5){
            j=0;
            while(j<5){
                j++;
                break;
            }
            System.out.println(i);
            i++;
        }
    }
    
    private ArrayList<Integer> lookAhead(int i, int j, ArrayList<Integer> k, Integer[][] values, int l){
        int q=0;
        while(q<k.size()){
            Integer p =k.get(q);
            values[i][j]=p;
            int i1=0;
            while(i1<l){
                int j1=0;
                while(j1<l){
                    if(values[i1][j1]==0){
                        if(getBestValues(i1,j1, values, false).isEmpty()&&k.contains(p)){
                            int s= k.remove(q);
                            q--;
                        }
                    }
                    j1++;
                }
                i1++;
            }
            q++;
            values[i][j]=0;
        }
       return k;
    }

}