import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alexey Lyapin on 01.06.2018.
 */
/*Class with logic of program*/

public class Logic {

    //Method for reading data from keyboard
    public void Reader()  {
        List<String> namesOfCities = new ArrayList<String>();

        namesOfCities.add(null);
        Scanner scanner = new Scanner(System.in);
        String string;
        System.out.print("Input number of tests: ");
        string = scanner.nextLine();
        int numberOfTest = Integer.parseInt(string);
        if(numberOfTest > 10){
            System.out.println("Number of tests must be < 10");
            System.out.print("Input number of tests: ");
            string = scanner.nextLine();
            numberOfTest = Integer.parseInt(string);
        }
        for(int i = 0; i < numberOfTest; i++){
             System.out.print("Input number of cities: ");
            string = scanner.nextLine();
            int numberOfCities = Integer.parseInt(string);
            Matrix matrixOfCost = new Matrix();
            matrixOfCost.setSize(numberOfCities);
            for(  i = 1; i <= numberOfCities; i++){
                 System.out.print("Input the name of city " + (i) + ": ");
                string = scanner.nextLine();
                namesOfCities.add(string);
                 System.out.print("Input the number of neighbours of city " + (i) + ": ");
                string = scanner.nextLine();
                int numberOfNeighbours = Integer.parseInt(string);
                for( int j = 0; j < numberOfNeighbours; j++){
                       System.out.print("Input the index of city and cost of this connection: (index cost) ");
                    string =  scanner.nextLine();
                    String [] brokenLine = string.split(" ");
                    int indexOfCity = Integer.parseInt(brokenLine[0]);
                    int costOfConnection = Integer.parseInt(brokenLine[1]);
                    matrixOfCost.setCost(i, indexOfCity,costOfConnection);
                }
            }

            System.out.print("Input the number of paths: ");
            string = scanner.nextLine();
            int numberOfPaths = Integer.parseInt(string);
            for (int j = 0; j < numberOfPaths; j++){
                 System.out.print("Input name of path: (NAME NAME)");
                string = scanner.nextLine();
                String [] nameOfPath = string.split(" ");
                String startCity = nameOfPath[0];
                String finishCity = nameOfPath[1];
                int startIndex = 0;
                int finishIndex = 0;
                for( int k = 0; k < namesOfCities.size(); k++){
                    if(startCity.equals(namesOfCities.get(k))){
                        startIndex = k;
                    }
                }
                for ( int l = 0; l < namesOfCities.size(); l++){
                    if(finishCity.equals(namesOfCities.get(l))){
                        finishIndex = l;
                    }
                }
                int path = MinimalCostFinder(matrixOfCost.costArray, startIndex, finishIndex);
                System.out.println("Minimal cost from city " + nameOfPath[0] + " to city " + nameOfPath[1] + " is: " +path);
            }
        }
    }
    /*
    The method finds the minimum cost of a path using the Dijkstra's algorithm
     */
    private int  MinimalCostFinder(int [][]Array, int startIndex, int finishIndex) {


        //"INFINITY"
        final int INF = Integer.MAX_VALUE;
        int [] distance = new int [Array.length];
        boolean [] used = new boolean[Array.length];
        int bestCost;


        for (int i = 0; i < Array.length; i ++){
            for (int j = 0; j < Array.length; j++){
                if (Array[i][j] == 0){
                    Array[i][j] = INF;
                }
            }
        }

         for (int  i = 0; i < distance.length; i++){
             distance[i] = INF;
         }
        distance[startIndex] = 0;
        for (int i = 0; i < used.length;  i++){
            used[i] = false;
        }

        while(true){
            bestCost = INF;
            int indexOfCity = -1;
            for (int i = 0; i < Array.length; i++)
            {
                if (!used[i]&& distance[i]< bestCost){
                    indexOfCity = i;
                    bestCost = distance[i];
                }
            }

            if (bestCost == INF){
                break;
            }

            for (int i = 0; i < Array.length; i++){
                if(!used[i] && Array[indexOfCity][i] !=INF){
                    if(distance[i] > distance[indexOfCity] + Array[indexOfCity][i]){
                        distance[i] = distance[indexOfCity] + Array[indexOfCity][i];
                    }
                }
            }
            used[indexOfCity] = true;
        }
        return distance[finishIndex];
    }
}
