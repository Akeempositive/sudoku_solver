package sudoku;

import java.util.ArrayList;

public class Solver{
    protected Sudoku sudoku, original;
    protected Integer[][] val;
    protected Integer []counter;
    
    public Solver(Sudoku s){
        sudoku = s;
    }
    
    protected boolean complete(){
        int sum=0;
        for(Integer a : counter){
            sum+=a;
        }
        
        boolean k= sum==counter.length*counter.length;
        if(k){
            System.out.println("Completed");
        }
        return k;
    }
    
    private void assignedValues() {
        setCounter(sudoku.getValues());
        int w=0;
       while(!complete()){
            w++;
            ArrayList<BestValue> v= new ArrayList<BestValue>();
            Integer values [][]=sudoku.getValues();
            int i=0;
            while(i<sudoku.getType()){
                int j=0;
                while(j<sudoku.getType()){
                   if(w%2==0||sudoku.getType()%2==0){
                        if(values[i][j]==0){
                            v.add(new BestValue(i,j, values));
                        }
                    }else {
                        if(values[j][i]==0){                          
                            v.add(new BestValue(j,i, values));
                        }
                    }
                    j++;
                }
                i++;
            }
            BestValue k = hasNone(v);
            if(k!=null){
                int x=k.getValues().get(0);
                values[k.getI()][k.getJ()]= x;
                counter[x-1]+=1;
            } else {
                int x= getLeastFilled();
                k = firstMerit(v, x);
                if(k==null){
                    val= sudoku.getValues();
                    System.out.println("It is filled after " +w+" iterations");
                    return;
                } else{
                    values[k.getI()][k.getJ()]=x;
                    counter[x-1]+=1;
                }
            }
            sudoku.setValues(values);
        }
        System.out.println("It is filled after " +w+" iterations");
        val=sudoku.getValues();
    }
    
    public void solve(){
        assignedValues();
    }
    
    public Sudoku solvedSudoku() throws NotASudokuFormatException{
        printSolved(val);
        return new Sudoku(sudoku.getType(), val);
    }
    
    protected void printSolved(Integer[][] val){
        int i=0;
        while(i<val.length){
            int j=0;
            while(j<val.length){
                System.out.printf("%3s", val[i][j]);
                j++;
            }
            System.out.println();
            i++;
        }
        System.out.println();
    }
    
    public static void main(String args[]) throws NotASudokuFormatException{
        int i=1;
        while(i<=9){
            Sudoku w= new Sudoku(i);
            Solver sm = new Solver(w);
            sm.solve();
            if(!sm.solvedSudoku().isSolved()){
              System.out.println(i + " not True");
            } else {
                System.out.println (i +" is True");
            }
            i++;
        }
       
    }

   public static  Integer[][] setValues(String a)throws NotASudokuFormatException{
       int x= a.length();
       int y= (int)Math.sqrt(x);
       if(y*y!=x){
           System.out.println("Using default");
           a="1000";
           x= a.length();
           y= (int)Math.sqrt(x);
       }
       System.out.println(x + " is the value of x and " + y + " is the value of y"  );
       Integer[][] values = new Integer[y][y];
       int i=0;
       int r=0;
       while(i<y*y){
               Integer p=Integer.parseInt(Character.toString(a.charAt(i)));
               if(p!=0){
                   r++;
               }
               values[i%y][i/y]=p;
               i++;
       }
       System.out.println("There are "+ r + " filled slot");
       return values;
   }
   
    protected static Integer[][] setTozero(Integer[][] a, int w) {
        int i=0, j;
        while(i<w){
            j=0;
            while(j<w){
                a[i][j]=0;
                j++;
            }
            i++;
        }
        return a;
    }

    protected BestValue hasNone(ArrayList<BestValue> v) {
        if(v.size()==1){
            return v.get(0);
        }
        for(BestValue b : v){
            if(b.getValueSize()==1){
                return b;
            }
        }
        return null;
    }

    protected void setCounter(Integer[][] values) {
        setToZero(values.length);
        int i =0;
        int j = 0;
        while(i<counter.length){
            while(j<counter.length){
                if(values[i][j]!=0){
                    counter[values[i][j]-1]++;
                }
                j++;
            }
            i++;
        }
    }

    protected void setToZero(int i) {
        counter = new Integer[i];
        int j =0;
        while(j<i){
            counter[j]=0;
            j++;
        }
    }

    protected int getLeastFilled() {
        int i= counter.length;
        int x= counter[0];
        int index=0;
        int j=1;
        while(j<i){
            if(counter[j]<x){
                x=counter[j];
                index=j;
            }
            j++;
        }
        return index+1;
    }

    protected BestValue firstMerit(ArrayList<BestValue> v, int x) {
        for(BestValue k: v){
            if(k.getValues().contains(x)){
                return k;
            }
        }
        return null;
    }

}