import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

class EightPuzzle {
    public static void main(String[] args) throws Exception {

        int[][] startState = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 0 }
        }
        int[][] endState = {
                { 0, 1, 2 },
                { 4, 5, 3 },
                { 7, 8, 6 }
        };

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node startNode = aStar(startState, endState);
        Stack<Node> path = new Stack<Node>();
        int totalCost = getCost(startNode, path);        
        System.out.println("================================================");
        System.out.println("Goal State Name :- " + startNode.name);
        System.out.println("Total Cost :- " + totalCost);
        printPath(path);
        br.close();
    }

    static Node aStar(int start[][], int end[][]) {
        int i = 0, j = 0;
        int count = 0;
        int[] tmpXY = getXY(start, 0);

        ExtraData extraData = new ExtraData(start, tmpXY[0], tmpXY[1]);
        Node head = new Node(count, null, 0, extraData);
        System.out.println("Starting State :- ");
        printBoard(start);
        System.out.println("*************************************************");
        System.out.println("Goal State :- ");
        printBoard(start);
        int approxCost = getHeuristic(head, end);
        head.setHeuristicCost(approxCost);
        if (approxCost == 0) {
            return head;
        }
        count++;
        Node tmp = null;
        PriorityQueue<Node> childQueue = new PriorityQueue<>(new The_Comparator());
        childQueue.add(head);
        int zeroX;
        int zeroY;
        int newState[][];

        while (!childQueue.isEmpty()) {

            tmp = childQueue.remove();
            if (tmp.getHuristicCost() == 0) {
                return tmp;
            }
            zeroX = ((ExtraData) tmp.data).axiX;
            zeroY = ((ExtraData) tmp.data).axiY;

            if (zeroY == 2) {
                // left Move Possible
                newState = new int[3][3];
                Node child = moveX(((ExtraData) tmp.data).state, newState, zeroX, zeroY, tmp,
                        true, count);
                int tmpHeuristic = getDistention(newState, end);
                if (tmpHeuristic == 0) {
                    return child;
                }
                child.setHeuristicCost(tmpHeuristic + getFx(child));
                childQueue.add(child);
                count++;

            } else if (zeroY == 1) {
                // Left Move Possible
                newState = new int[3][3];
                Node child = moveX(((ExtraData) tmp.data).state, newState, zeroX, zeroY, tmp,
                        true, count);
                int tmpHurustic = getDistance(newState, end);
                if (tmpHurustic == 0) {
                    return child;
                }
                child.setHeuristicCost(tmpHeuristic + getFx(child));
                childQueue.add(child);
                count++;

                // Right Move Possible
                newState = new int[3][3];
                child = moveX(((ExtraData) tmp.data).state, newState, zeroX, zeroY, tmp,
                        false, count);
                tmpHurustic = getDistance(newState, end);
                if (tmpHeuristic == 0) {
                    return child;
                }
                child.setHeuristicCost(tmpHeuristic + getFx(child));
                childQueue.add(child);
                count++;

            } else if (zeroY == 0) {
                // Right Move Possible
                newState = new int[3][3];
                Node child = moveX(((ExtraData) tmp.data).state, newState, zeroX, zeroY, tmp,
                        false, count);
                int tmpHeuristic = getDistance(newState, end);
                if (tmpHeuristic == 0) {
                    return child;
                }
                child.setHeuristicCost(tmpHeuristic + getFx(child));
                childQueue.add(child);
                count++;
            }

            /*
             * Check For Vertical Change Possibilities
             */
            if (zeroX == 2) {
                // Up Move Possible
                newState = new int[3][3];
                Node child = moveY(((ExtraData) tmp.data).state, newState, zeroX, zeroY, tmp,
                        true, count);
                int tmpHeuristic = getDistance(newState, end);

                if (tmpHeuristic == 0) {
                    return child;
                }
                child.setHeuristicCost(tmpHeuristic + getFx(child));
                childQueue.add(child);
                count++;
            } else if (zeroX == 1) {
                // Up and Down Possible
                newState = new int[3][3];
                Node child = moveY(((ExtraData) tmp.data).state, newState, zeroX, zeroY, tmp,
                        true, count);
                int tmpHeurustic = getDistention(newState, end);
                if (tmpHurustic == 0) {
                    return child;
                }
                child.setHuristicCost(tmpHurustic + getFx(child));
                childQueue.add(child);
                count++;

                // Down Move Possible
                newState = new int[3][3];
                child = moveY(((ExtraData) tmp.data).state, newState, zeroX, zeroY, tmp,
                        false, count);
                tmpHurustic = getDistention(newState, end);
                if (tmpHurustic == 0) {
                    return child;
                }
                child.setHuristicCost(tmpHurustic + getFx(child));
                childQueue.add(child);
                count++;

            } else if (zeroX == 0) {
                // Down Move Possible
                newState = new int[3][3];
                Node child = moveY(((ExtraData) tmp.data).state, newState, zeroX, zeroY, tmp,
                        false, count);
                int tmpHeurustic = getDistance(newState, end);
                    return child;
                }
                child.setHeuristicCost(tmpHeuristic + getFx(child));
                childQueue.add(child);
                count++;
            }

            if (count > 20) {
                System.out.println("Out of Limit ");
                break;
            }
        }
        return head;
    }

    /*
     * calculate Heuristic Value for state
     */
    static int getHeuristic(Node child, int[][] endNode) {
        int[][] state = ((ExtraData) child.data).state;
        return getFx(child) + getDistention(state, endNode);
    }

    /*
     * Calculate cost of reach to current state from root state
     */
    static int getFx(Node child) {
        int ans = 0;
        while (child != null) {
            ans = ans + child.getCost();
            child = child.getParent();
        }
        return ans;
    }

    /*
     * Give sum of all misplace tiles' distance from it's goal state
     */
    static int getDistention(int a[][], int b[][]) {
        int ans = 0;
        int i, j;
        int[] tmp;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                tmp = getXY(a, b[i][j]);
                ans = ans + Math.abs(tmp[0] - i) + Math.abs(tmp[1] - j);
            }
        }
        return ans;
    }

    /*
     * Back Trace path from child to Root and store it in Stack
     */
    static int getCost(Node n, Stack<Node> path) {
        int ans = n.getCost();
        Node tmp = n;
        while (tmp != null) {
            path.add(tmp);
            tmp = tmp.getParent();
        }
        return ans;
    }

    static void printPath(Stack<Node> path) {
        int step = 1;
        Node tmp = path.pop();
        System.out.println("#################################");
        System.out.println(tmp.getName());
        printBoard(((ExtraData) tmp.data).state);
        while (!path.empty()) {
            tmp = path.pop();
            System.out.println("#################################");
            System.out.println(" Step :- " + step + "(" + tmp.getName() + ")");
            printBoard(((ExtraData) tmp.data).state);
            step++;
        }
        System.out.print("\n");
    }
    /*
     * Find row-number and column-number of specific value in bord;
     */
    static int[] getXY(int state[][], int num) {
        int i, j;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (state[i][j] == num) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[2];
    }

    /*
     * Print Board
     */
    static void printBoard(int[][] state) {
        int i, j;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                System.out.print(state[i][j] + " ");
            }
            System.out.println("");
        }
    }

    /*
     * Move Blank space to Left or Right
     */
    static Node moveX(int[][] intial, int[][] newState, int x, int y, Node pNode, boolean isLeft, int count) {
        int i, j;
        int newx = 0;
        if (isLeft) {
            newx = y - 1;
        } else {
            newx = y + 1;
        }
        if (isLeft) {
            y--;
        }
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (j == y && i == x) {
                    newState[i][j] = intial[i][j + 1];
                    newState[i][j + 1] = intial[i][j];
                    j++;
                } else {
                    newState[i][j] = intial[i][j];
                }
            }
        }

        ExtraData extraData = new ExtraData(newState, x, newx);
        Node child = new Node(count, pNode, pNode.getCost() + 1, extraData);
        pNode.addChild(child);

        return child;
    }

    /*
     * Move Blank space to up or down
     */
    static Node moveY(int[][] intial, int[][] newState, int x, int y, Node pNode, boolean isUp, int count) {
        int i, j;
        int newX;
        if (isUp) {
            newX = x - 1;
        } else {
            newX = x + 1;
        }
        if (isUp) {
            x--;
        }
        int tmpHold = 0;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (i == x && j == y) {
                    tmpHold = intial[i][j];
                    newState[i][j] = intial[i + 1][j];
                } else {
                    newState[i][j] = intial[i][j];
                }
            }
        }
        newState[x + 1][y] = tmpHold;

        ExtraData extraData = new ExtraData(newState, newX, y);
        Node child = new Node(count, pNode, pNode.getCost() + 1, extraData);
        pNode.addChild(child);

        return child;
    }
}

// Problem Specific Data to extends State space graph Functionality
class ExtraData {
    public int axiX;
    public int axiY;
    public int[][] state;

    ExtraData(int[][] state) {
        this.state = state;
        this.getXY();
    }

    ExtraData(int[][] state, int x, int j) {
        this.state = state;
        this.axiX = x;
        this.axiY = j;
    }

    void getXY() {
        int i, j;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    this.axiX = i;
                    this.axiY = j;
                    return;
                }
            }
        }

    }

    int[][] getStateBord() {
        return this.state;
    }
}

// General graph structure - Create State Space Tree Graph Structure to track
// Nodes
class Node {
    public char name;
    Node parent;
    LinkedList<Node> child = new LinkedList<>();
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
        this.huristicCost = c;
    }

    int getHeuristicCost() {
        return this.huristicCost;
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
