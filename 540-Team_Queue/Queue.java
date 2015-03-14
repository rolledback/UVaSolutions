import java.util.*;

class Element {

    int value, team;
    Element next;
    
    public Element (int value, int team, Element next) {
        this.value = value;
        this.team = team;
        this.next = next;
    }
}

class Main {

    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int numTeams, teamSize, value;
        String line;
        HashMap<Integer, Element> elements = new HashMap<Integer, Element>();
        Element[] lastOcc = new Element[1000];
        
        int scenario = 1;
        while(true) {
            numTeams = Integer.parseInt(input.nextLine());
            if(numTeams == 0)
                return;
            for(int i = 0; i < numTeams; i++) {
                teamSize = input.nextInt();
                for(int j = 0; j < teamSize; j++) {
                    value = input.nextInt();
                    elements.put(new Integer(value), new Element(value, i, null));
                }
                lastOcc[i] = null;
            }
            input.nextLine();

            System.out.println("Scenario #" + scenario);
            scenario++;

            Element head = null;
            Element tail = null;
            while(true) {
                line = input.nextLine();
                String[] tokens = line.split(" ");
                if(tokens[0].equals("STOP")) {
                    break;
                }
                else if(tokens[0].equals("ENQUEUE")) {
                    value = Integer.parseInt(tokens[1]);
                    Element curr = elements.get(value);
                    int team = curr.team;

                    if(head == null && tail == null) {
                        head = curr;
                        tail = curr;
                        head.next = curr;
                        tail.next = curr;
                    }
                    else {
                        if(lastOcc[team] != null) {
                            Element insertPoint = lastOcc[team];
                            curr.next = insertPoint.next;
                            if(tail.equals(insertPoint)) {
                                tail = curr;
                            }
                            insertPoint.next = curr;
                        }
                        else {
                            tail.next = curr;
                            tail = curr;
                        }
                    }
                    lastOcc[team] = curr;
                }
                else {
                    System.out.println(head.value);
                    if(lastOcc[head.team].equals(head)) {
                        lastOcc[head.team] = null;
                    }

                    if(head == tail) {
                        head = null;
                        tail = null;
                    }
                    else {
                        head = head.next;
                    }
                }
            }
            System.out.println();
        }
        return;
    }            
}
