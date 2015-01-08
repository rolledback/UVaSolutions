import java.util.Scanner;

class Main {
    
    static boolean onLeft = true;

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        while(input.hasNextLine()) {
            System.out.println(replaceQuotes(input.nextLine()));
        }
    }

    public static String replaceQuotes(String line) {
        String formattedString = "";
        for(int i = 0; i < line.length(); i++) {
            char curr = line.charAt(i);
            if(curr == '"') {
                if(onLeft)
                    formattedString += "``";
                else
                    formattedString += "''";
                onLeft = !onLeft;
            }
            else
                formattedString += curr;
        }
        return formattedString;
    }
}
