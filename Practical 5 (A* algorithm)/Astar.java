import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

class Astar {
    /* 
     * Driver Function
     */
    public static void main(String[] args) throws Exception {

        int graph[][] = {
                { -1, 5, 3, -1, -1 },
                { -1, -1, -1, 6, -1 },
                { -1, 1, -1, -1, 2 },
                { -1, -1, -1, -1, 1 },
                { -1, -1, -1, -1, -1 }
        };
        int coordinates[][] = {
                { 6, 1 },
                { 6, 5 },
                { 3, 1 },
                { 3, 5 },
                { 3, 0 }
        };

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int start = 0, end = 5;
        System.out.print("Enter Start Node :- ");
        start = Integer.parseInt(br.readLine()) - 1;
        System.out.print("Enter End Node :- ");
        end = Integer.parseInt(br.readLine()) - 1;

        Node startNode = aStar(graph, coordinates, start, end);
        Stack<Node> path = new Stack<Node>();
        int totalCost = getCost(startNode, path);
        printPath(path);
        System.out.println("Total Cost :- " + totalCost);
        br.close();
    }

    /*
     * A* algoritham to finding path 
     */
    static Node aStar(int collection[][], int coordinate[][], int start, int end) {
        int i = 0, j = 0;
        ExtraData extraData = new ExtraData(cordinate[start]);
        Node head = new Node(start, null, 0, extraData);
        head.setHuristicCost(0);

        Node tmp = null;
        PriorityQueue<Node> childQueue = new PriorityQueue<>(new The_Comparator());
        childQueue.add(head);

        while (!childQueue.isEmpty()) {
            tmp = childQueue.remove();
            i = tmp.getIndex();
            if (i == end) {
                return tmp;
            }
            for (j = 0; j < collection.length; j++) {
                if (collection[i][j] != -1) {
                    extraData = new ExtraData(cordinate[j]);
                    Node child = new Node(j, tmp, collection[i][j], extraData);
                    child.setHuristicCost(getHeuristic(child, coordinate[end]));
                    tmp.addChild(child);
                    childQueue.add(child);
                }
            }
        }
        return head;
    }

    /*
     * Heuristic Function for A* algoritham
     */
    static int getHeuristic(Node child, int endNode[]) {
        int ans = 0;
        Node tmp = child;
        while (tmp != null) {
            ans = ans + tmp.getCost();
            tmp = tmp.getParent();
        }
        ans = ans + getDistance(((ExtraData) child.data).getCoordinate(), endNode);
        return ans;
    }

    /*
     * Helper function to calculate Cartesian distance
     */
    static int getDistance(int a[], int b[]) {
        int ans = 0;
        float tmpX = a[0] - b[0];
        float tmpy = a[1] - b[1];
        ans = Math.abs((int) Math.sqrt((tmpX * tmpX) + (tmpy * tmpy)));
        return ans;
    }

    /*
     * Helper Functions
     */

    /*
     * calculate Total Cost from starting node to end node
     */
    static int getCost(Node n, Stack<Node> path) {
        int ans = 0;
        Node tmp = n;
        System.out.println("");
        while (tmp != null) {
            path.add(tmp);
            ans = ans + tmp.getCost();
            tmp = tmp.getParent();
        }
        return ans;
    }

    /*
     * Helper function to print sequence of node for travel to reach goal node
     */
    static void printPath(Stack<Node> path) {
        Node tmp = path.pop();
        System.out.print(tmp.getName());
        while (!path.empty()) {
            tmp = path.pop();
            System.out.print("->" + tmp.getName());
        }
        System.out.print("\n");
    }
}

// Problem Specific Data to extends State space graph Functionality
class ExtraData {
    public int axiX;
    public int axiY;

    ExtraData(int[] coordinate) {
        this.axiX = coordinate[0];
        this.axiY = coordinate[1];
    }
    int[] getCoordinate() {
        return new int[] { this.axiX, this.axiY };
    }
}

// General graph structure - Create State Space Tree Graph Structure to track
class Node {
    public char name;
    Node parent;
    LinkedList<Node> child = new LinkedList<>();
    int chlidCount = 0;
    int cost;
    int heuristicCost;
    public Object data;

    Node(int nodeName, Node p, int cost, Object data) {
        this.name = getNameFromIndex(nodeName);
        this.parent = p;
        this.cost = cost;
        this.data = data;
    }
    Node getParent() {
        return this.parent;
    }
    char getName() {
        return this.name;
    }
    void setHeuristicCost(int c) {
        this.heuristicCost = c;
    }
    int getHeuristicCost() {
        return this.heuristicCost;
    }
    int getCost() {
        return cost;
    }
    char getNameFromIndex(int index) {
        char name = 'A';
        name = (char) ((int) name + index);
        return name;
    }
    int getIndex() {
        return (char) this.name - 65;
    }
    void addChild(Node childNode) {
        child.add(childNode);
        chlidCount++;
    }
    int getChildCount() {
        return chlidCount;
    }
    Node getChild(int index) {
        return child.get(index);
    }
}
// Comparator Class to Implement Priority Queue
class The_Comparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        // TODO Auto-generated method stub
        int cost1 = o1.getHeuristicCost();
        int cost2 = o2.getHeuristicCost();
        if (cost1 == cost2) {
            return 0;
        } else if (cost1 > cost2) {
            return 1;
        }
        return -1;
    }
}
