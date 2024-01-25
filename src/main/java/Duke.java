import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        String greeting = "___________________________________\n"
            + "Hello! I'm Jinni\n"
            + "What can I do for you?\n"
            + "___________________________________" ;
        
        System.out.println(greeting);
        
        while(true) {
            String inputFromUser = sc.nextLine();
            
            if ("bye".equalsIgnoreCase(inputFromUser)) {
                String bye = "___________________________________\n"
                    + "Bye. Hope to see you again soon!\n"
                    + "___________________________________";
                System.out.println(bye);
                break;
            }
            else if ("list".equalsIgnoreCase(inputFromUser)){
                System.out.println("Here are the tasks in your list\n");
                if(list.size() == 0) {
                    System.out.println("\n___________________________________");
                } else {
                    int num = 1;
                    for(Task t: list) {
                        String toPrint = num + "." + t.toString() + "\n";
                        num++;
                        System.out.println(toPrint);
                    }
                    System.out.println("___________________________________");
                }
            } else if (inputFromUser.toLowerCase().startsWith("mark")) {
                int num = Integer.parseInt(inputFromUser.substring(5));
                Task taskToBeMarked = list.get(num - 1);
                taskToBeMarked.markDone();
                System.out.println("Nice! I have marked this task as done\n");
                System.out.println(taskToBeMarked.toString());
                System.out.println("___________________________________");
            } else if (inputFromUser.toLowerCase().startsWith("unmark")) {
                int num = Integer.parseInt(inputFromUser.substring(7));
                Task taskToBeUnmarked = list.get(num - 1);
                taskToBeUnmarked.markUndone();
                System.out.println("Ok, I've marked this task as not done yet\n");
                System.out.println(taskToBeUnmarked.toString());
                System.out.println("___________________________________");
            }
            else {
                String echo = "added: " + inputFromUser + "\n___________________________________" ;
                System.out.println(echo);
                list.add(new Task(inputFromUser));
            }
        }
        sc.close();
    }
}
