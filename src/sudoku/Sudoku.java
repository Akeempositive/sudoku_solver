package sudoku;

public class Sudoku{
    private Integer [][] values;
    private int type;
    
    private static Integer[][] partition(int i,Integer[][] value) {
        Integer [][] va= new Integer[i][i];
        int row, column ;
        if(i>3){
            if(i%2==0){
                row=i/2;
                column = 2;
            } else if(i%3==0){
                row=i/3;
                column=3;
            } else {
                return value;
            }
        } else {
            return value;
        }
        int i1=0;
        while(i1<row){
            int j=0;
            while(j<column){
                va[i1 *column + j]=value[i1+j*row];
                j++;
            }
            i1++;
        }
        return va;
    }

    public Sudoku(int t){
        type=t;
        setToZero();
    }
    
    public static Sudoku sudoku(int i) throws NotASudokuFormatException{
        Integer [][] value = new Integer[i][i];
        int i1=0;
        int w=0;
        while(i1<i){
            int j=0;
            while(j<i){
                value[i1][j]=((w+j)%i)+1;
                j++;
            }
            w++;
            i1++;
        }
        value= partition(i,value);
        printSolved(value);
        return new Sudoku(i, value);
    }
    
    private static void printSolved(Integer[][] val){
        int i=0;
        while(i<val.length){
            int j=0;
            while(j<val.length){
                System.out.print(val[i][j] + "  ");
                j++;
            }
            System.out.println();
            i++;
        }
        System.out.println();
    }
    
    public Sudoku(int t, Integer [][] a)throws NotASudokuFormatException{
        type=t;
        if(t!=a.length){
            throw new NotASudokuFormatException();
        }
        values=a;
    }
    
    public Sudoku(Integer[][] a) throws NotASudokuFormatException{
        values=a;
        if(a.length==a[0].length){
                type=a.length;
        } else {
            throw new NotASudokuFormatException();
        }
    }
    
    private void setToZero(){
        int i=0;
        values=new Integer[type][type];
        while(i<type){
            int j=0;
            while(j<type){
                values[i][j]=0;
                j++;
            }
            i++;
        }
    }
    
    boolean isSolved(){
        int i=0;
        while(i<type){
            int j=0;
            int sum=0, sum1=0;
            while(j<type){
                sum+=values[i][j];
                sum1+=values[j][i];
                j++;
            }
            if(sum!=expectedSum(type)){
               return false;
            } else {
                if(sum1!=sum){
                    return false;
                }
            }
            i++;
        }
        return true;
    }
    
    private int expectedSum(int t){
        return (t *(t+1))/2;
    }

    Integer[][] getValues() {
        return values;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    void setValues(Integer[][]v){
        values=v;
    }
}