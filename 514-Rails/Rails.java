import java.util.*;

class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String args[]) {
        while(true) {
            int trainLength = Integer.parseInt(input.nextLine());
            if(trainLength == 0)
                break;
            String trainLine;
            while(!(trainLine = input.nextLine()).equals("0")) {
                int[] config = cvrtStrArr(trainLine.split(" "));
                if(checkConfig(config))
                    System.out.println("Yes");
                else
                    System.out.println("No");
            }
            System.out.println();
        }
        System.exit(0);
    }

    public static boolean checkConfig(int[] config) {
        if(config.length <= 2)
            return true;

        Queue<Integer> incoming = new LinkedList<Integer>();
        for(int i = 0; i < config.length; i++)
            incoming.offer(new Integer(i + 1));

        int x = 0;
        Stack<Integer> station = new Stack<Integer>();
        while(incoming.size() > 0) {
            int car = incoming.poll();
            station.push(car);
            while(station.size() > 0 && station.peek() == config[x]) {
                station.pop();
                x++;
            }
        }
        if(station.size() == 0)
            return true;
        return false;
    }

    public static int[] cvrtStrArr(String[] array) {
        int[] ret = new int[array.length];
        for(int i = 0; i < array.length; i++) {
            ret[i] = Integer.parseInt(array[i]);
        }
        return ret;
    }
}
