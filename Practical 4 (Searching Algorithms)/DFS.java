import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class P2{
    public static void main(String arg[]) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int graph[][] = {
            {1,1,1,0,0},
            {0,1,0,1,0},
            {0,1,1,0,1},
            {0,0,0,1,1},
            {1,0,0,0,1}
        };
        int visited[] = {0,0,0,0,0};
        Stack<Integer> stack = new Stack<Integer>();
        System.out.print("Enter Source Node :- ");
        int source = Integer.parseInt(br.readLine())-1;
        
        System.out.print("Enter destination Node :- ");
        int destination = Integer.parseInt(br.readLine())-1;

        int ans = DFS(graph, visited, source, destination,stack);
        int tmp = stack.size();
        if(ans == 1){
            System.out.println("===================================");
            System.out.println("Path Exist :- ");
            printStack(stack);
            System.out.print("end");
        }
        else{
            System.out.println("Path Does not Exist");
        }
    }
    static int DFS(int[][] arr,int[] visited,int nextX,int node, Stack<Integer> s){
        if(visited[nextX] == 0){
            visited[nextX] = 1;
            s.add(nextX);
            if(nextX != node){
                for(int i=0;i<5;i++){
                    if(arr[nextX][i] != 0){
                        int re = DFS(arr, visited, i, node,s);
                        if(re == 1){
                            return 1;
                        }
                    }
                }
                int tmp = s.pop();
            }
            else{
                return 1;
            }
        }
        return 0;
    }
    static void printStack(Stack<Integer> s){
        int tmp;
        if(s.size() != 0){
            tmp = s.pop()+1;
            printStack(s);
            System.out.print(tmp+"->");
        }
    }
}
