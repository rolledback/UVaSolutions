import java.util.*;

class Main {
 
    static Scanner input = new Scanner(System.in);    

    public static void main(String args[]) {
        while(input.hasNext()) {
            boolean corrupt = false;
            int mSize = Integer.parseInt(input.nextLine());
            if(mSize == 0)
                break;
            int oddRow = -1;
            int oddCol = -1;
            int[] colSums = new int[mSize];

            for(int i = 0; i < mSize; i++) {
                String[] keys = input.nextLine().split(" ");
                int sum = 0;
                for(int j = 0; j < keys.length; j++) {
                    int value = Integer.parseInt(keys[j]);
                    sum += value;
                    colSums[j] += value;
                }
                if(sum % 2 == 1) {
                    if(oddRow != -1) {
                        corrupt = true;
                    }
                    oddRow = i + 1;
                }
            }
            if(corrupt) {
                System.out.println("Corrupt");
                continue;
            }
            
            for(int c = 0; c < mSize; c++) {
                if(colSums[c] % 2 == 1) {
                    if(oddCol != - 1) {
                        corrupt = true;
                        break;
                    }
                    oddCol = c + 1;
                }
            }
            if(corrupt) {
                System.out.println("Corrupt");
                continue;
            }

            if(oddRow != -1 && oddCol != -1)
                System.out.println("Change bit (" + oddRow + "," + oddCol + ")");
            else if((oddRow != -1 && oddCol == -1) || (oddRow == -1 && oddCol != -1))
                System.out.println("Corrupt");
            else
                System.out.println("OK");
        }
    }
}
