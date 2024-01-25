import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke_Level3 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("read book");
        list.add("return book");
        list.add("buy bread");
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        String[] done = new String[100];
        while(true){
            if(str.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 1; i <= list.size(); i++){
                    System.out.println(done[i] + list.get(i - 1));
                }
            }
            else{
                if(str.substring(0, 4).equals("mark")){
                    int temp = Integer.parseInt(str.substring(5));
                    done[temp] = "[X] ";
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(done[temp] + list.get(temp - 1));
                }
                else{
                    int temp = Integer.parseInt(str.substring(7));
                    done[temp] = "[] ";
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(done[temp] + list.get(temp - 1));
                }
            }
            str = s.nextLine();
        }
    }
}
