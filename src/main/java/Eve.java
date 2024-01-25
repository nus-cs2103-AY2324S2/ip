import java.util.Scanner;
import java.util.*;

public class Eve {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" Hello! I'm Eve");
        System.out.println(" What can I do for you?");

        String input = "";
        ArrayList<Task> list = new ArrayList<>();

        while(!input.equals("bye")){
            input = sc.nextLine();
            if (input.equals("bye")){
                System.out.println(" Bye. Hope to see you again soon !");
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++ ){
                    int j = i + 1;
                    Task temp = list.get(i);
                    System.out.println(j + ".[" + temp.getStatusIcon() + "] " + temp.toString());
                }
                
            } else if (input.substring(0,4).equals("mark")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                Task temp = list.get(index);
                temp.markAsDone();
                System.out.println(" Nice! I've marked this task as done: ");
                System.out.println(" [" + temp.getStatusIcon() + "]" + temp.toString());
                
            // } else if (input.substring(0,7).equals("ummark")) {
            //     int index = Integer.parseInt(input.substring(5)) - 1;
            //     Task temp = list.get(index);
            //     temp.markAsNotDone();
            //     System.out.println(" Nice! I've marked this task as done: ");
            //     System.out.println(" [" + temp.getStatusIcon() + "]" + temp.toString());
                
            // } else {
                Task t = new Task(input);
                list.add(t);
                System.out.println("added: " + input);
            }
        }
            

        sc.close();
    }
}
