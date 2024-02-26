import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hi, I'm Lighthouse.\nHow can I help you?\n";
        System.out.println(greeting);
        Scanner scan = new Scanner(System.in);
        boolean continueRun = true;
        ArrayList<Task> taskList = new ArrayList<>();

        while(continueRun) {
            String info = scan.nextLine();
            if (info.equals("bye")) {
                continueRun = false;
                System.out.println("____________________________________________________________");
                System.out.println("Goodbye! See you next time!");
                System.out.println("____________________________________________________________");
                break;
            } else if (info.contains("mark")) {
                int itemToMark = 0;
                int index = info.indexOf(" ");
                String argVal = info.substring(index+1);
                int itemNo = Integer.parseInt(argVal) - 1;
                Task task = taskList.get(itemNo);
                System.out.println("____________________________________________________________");
                if (info.startsWith("mark")) {
                    task.setMarked(true);
                    System.out.println("Nice! I've marked this task as done:");
                } else {
                    task.setMarked(false);
                    System.out.println("OK, I've marked this task as note done yet:");
                }
                System.out.println(task.printOutput());
                System.out.println("____________________________________________________________");
                taskList.set(itemNo, task);
            } else if (info.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:\n");
                for(int i=0; i < taskList.size(); i++) {
                    System.out.println(taskList.get(i).printOutput());
                }
                System.out.println("____________________________________________________________");
            } else {
                int itemNo;
                if (taskList.size() > 0) {
                    itemNo = taskList.size()+1;
                } else {
                    itemNo = 1;
                }
                Task task = new Task(info, false, itemNo);
                taskList.add(task);
                System.out.println("____________________________________________________________");
                System.out.println("added: "+info);
                System.out.println("____________________________________________________________");
            }
        }
    }
}