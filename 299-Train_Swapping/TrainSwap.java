import java.util.*;

class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String args[]) {
        int numCases = input.nextInt();
        for(int currCase = 0; currCase < numCases; currCase++) {
            int numCars = input.nextInt();
            List<Integer> cars = new ArrayList<Integer>();
            for(int currCar = 0; currCar < numCars; currCar++) {
                cars.add(new Integer(input.nextInt()));        
            }
            int optimal = findMinSwaps(cars);
            System.out.println("Optimal train swapping takes " + optimal + " swaps.");
        }
    }
    
    public static int findMinSwaps(List<Integer> train) {
        int numSwaps = 0;
        for(int i = 0; i < train.size() - 1; i++) {
            for(int j = i + 1; j < train.size(); j++)
                if(train.get(j) < train.get(i))
                    numSwaps++;
        }
        return numSwaps;
    }
}
