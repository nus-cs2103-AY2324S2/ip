import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke_Level2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        System.out.println("Hello! I'm SamuelBot");
        System.out.println("What can I do for you?");
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        while(! str.equals("bye")) {
            if(str.equals("list")){
                for(int i = 0; i < list.size(); i++){
                    System.out.println((i+1) + ". " + list.get(i));
                }
            }
            else {
                list.add(str);
                System.out.println("added: " + str);
            }
            str = s.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
