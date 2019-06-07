package sudoku;

import java.util.ArrayList;

public class Solver2 extends Solver{
    
    public Solver2(Sudoku s){
       super(s);
    }
    
    @Override
    public void solve(){
        assignedValues();
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
                   if(w%2==0||sudoku.getType()%3==0){
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
    
}