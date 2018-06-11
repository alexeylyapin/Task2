/**
 * Created by Alexey Lyapin on 01.06.2018.
 */

/* Class works with matrix*/
public class Matrix {

    int[][] costArray;
//Value of "INFINITY"
    public final int INF = Integer.MAX_VALUE ;

    //Method set size of matrix
    public void setSize(int size) {
        costArray = new int[size + 1][size + 1];
    }

    //Method fills the matrix with values of cost
    public void setCost(int firstIndex, int secondIndex, int cost) {
        costArray[firstIndex][secondIndex] = cost;
    }



}
