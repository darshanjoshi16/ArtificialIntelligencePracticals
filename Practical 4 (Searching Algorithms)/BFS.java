import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class P1{
    public static void main(String Arg[]) throws Exception
{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int graph[][] = {
            {1,1,1,0,0},
            {0,1,0,1,0},
            {0,1,1,0,1},
            {0,1,0,1,1},
            {0,0,0,0,1}
        };

        int start = -1, end;
        System.out.print("Enter Start Node :- ");
        start = Integer.parseInt(br.readLine()) -1 ;

        System.out.print("Enter Node To find  :- ");
        end = Integer.parseInt(br.readLine()) -1;

        Stack<Integer> ans = BFS(graph, start, end);
        if(ans == null){
            System.out.println("Node not Found");
        }
        else{
            System.out.println("================================================");
            System.out.println("Path Found");
            int tmp = ans.size();
            while(tmp>0){
                System.out.print(ans.pop()+"=>");
                tmp--;
            }
            System.out.print("End");
        }
    }

    

static Stack<Integer> BFS(int arr[][],int start,int end){
        int i,current;
        boolean flag = false;
        Queue<Integer> quea = new LinkedList<>();
        Stack<Integer> stack = new Stack<Integer>();

        boolean visited[] = new boolean[arr.length];
        quea.add(start);
        stack.push(start);

        visited[start] = true;
   
        while(quea.size() != 0){
            current = quea.remove();
            for(i=0;i<arr.length;i++){
                if(arr[current][i] != 0 && visited[i] == false){
                    if(i == end){
                        stack.push(current);
                        flag = true;
                    }
                    visited[i] = true;
                    quea.add(i);
                }
            }
        }
        int child =end;
        if(flag){
            Stack<Integer> ans = new Stack<Integer>();
            ans.add(end+1);
            while(child != start){
                current = stack.pop();
                if(arr[current][child] != 0){
                    ans.add(current+1);
                    child = current;
                }
            }
            return ans;
        }
        return null;
    }
}
