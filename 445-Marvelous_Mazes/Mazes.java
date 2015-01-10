import java.util.*;

class Main {

    static Scanner input = new Scanner(System.in);
    
    public static void main(String args[]) {
        String line = "";

        while(input.hasNext()) {
            line = input.nextLine();
            if(line.equals(""))
                System.out.println();
            else {
                translateLine(line);
                System.out.println();
            }
        }
    }

    public static void translateLine(String line) {
        for(int i = 0; i < line.length(); i++) {
           if(line.charAt(i) == '!')
                System.out.println();
            else {
                int numTimes = 0;
                while((int)(line.charAt(i)) >= 48 && (int)(line.charAt(i)) <= 57) {
                    numTimes += (int)(line.charAt(i)) - 48;
                    i++;
                }
                char toPrint = line.charAt(i);
                if(toPrint == 'b')
                    toPrint = ' ';
                for(int j = 0; j < numTimes; j++)
                    System.out.print(toPrint);
            }
        }
    }
}
