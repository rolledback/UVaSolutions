import java.util.*;

class Main {

    static Scanner input = new Scanner(System.in);
    static Node[] network;

    public static void main(String[] args) {
        while(input.hasNext()) {
            System.out.println("-----");
            network = new Node[300];
            int numNodes = Integer.parseInt(input.nextLine());
            String line = "";

            for(int i = 0; i < numNodes; i++) {
                network[i] = new Node(i + 1);
                network[i].addNeighbors(decode(input.nextLine()));
            }

            int numCases = Integer.parseInt(input.nextLine());
            for(int i = 0; i < numCases; i++) {
                String[] tokens = input.nextLine().split(" ");
                int start = Integer.parseInt(tokens[0]);
                int goal = Integer.parseInt(tokens[1]);
                int[] path = findPath(start, goal);
                if(path == null)
                    System.out.println("connection impossible");
                else {
                    int curr = goal;
                    String output =  "";
                    while(curr != start) {
                        output = " " + curr + output;
                        curr = path[curr - 1];
                    }
                    output = start + output;
                    System.out.println(output);
                }
            }
        }
    }

    public static int[] decode(String line) {
        if(line.indexOf("-") == line.length() - 1)
            return new int[0];
        String[] nIdTokens = line.split("-")[1].split(",");
        int[] nIds = new int[nIdTokens.length];
        for(int i = 0; i < nIds.length; i++) {
            nIds[i] = Integer.parseInt(nIdTokens[i]);
        }
        return nIds;
    }

    public static int[] findPath(int start, int goal) {
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[300];
        int[] discoveredBy = new int[300];

        queue.offer(new Integer(start));
        visited[start - 1] = true;

        while(queue.size() > 0) {
            int currId = queue.poll();
            if(currId == goal)
                return discoveredBy;
            else {
                int[] neighbors = network[currId - 1].neighbors;
                int numNeighbors = network[currId - 1].numNeighbors;
                for(int n = 0; n < numNeighbors; n++) {
                    if(!visited[neighbors[n] - 1]) {
                        discoveredBy[neighbors[n] - 1] = currId;
                        visited[neighbors[n] - 1] = true;
                        queue.offer(new Integer(neighbors[n]));
                    }
                }
            }
        }
        return null;
    }
}

class Node {

    int[] neighbors;
    int numNeighbors;
    int id;

    public Node(int id) {
        this.id = id;
        this.neighbors = new int[50];
        this.numNeighbors = 0;
    }

    public void addNeighbors(int[] nIds) {
        for(int i = 0; i < nIds.length; i++)
            this.addNeighbor(nIds[i]);
    }

    public void addNeighbor(int nId) {
        neighbors[numNeighbors] = nId;
        numNeighbors++;
    }
}
