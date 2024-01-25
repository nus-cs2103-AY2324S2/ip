import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>(100);
        String message = "____________________________________________________________\n" +
                "Hello! I'm Jux\n" +
                "What can I do for you?\n";
        String end = "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________"
                ;
        System.out.println(message);
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if(input.equals("list")) {
                System.out.println("Here are the tasks in your list!");
                for (int i = 0; i < list.size();i++) {
                    int j = i + 1;
                    String listMessage = j + "." + "["+
                            list.get(i).getStatusIcon() + "]" + list.get(i);
                    System.out.println(listMessage + "\n");
                }
            } else if(input.startsWith("mark")) {
                if (input.length() > 5 ) {
                    String listStringNumber =  input.substring(5);
                    int convertedToNumber = Integer.parseInt(listStringNumber) - 1;
                    System.out.println(convertedToNumber);
                    String doneness = list.get(convertedToNumber).toggleIsDone();
                    // check if already marked then return error
                    System.out.println("Nice! I've marked this task as done:" + "\n");
                    System.out.println("[" + doneness + "] " + list.get(convertedToNumber));
                }
            } else if (input.startsWith("unmark")) {
                String listStringNumber =  input.substring(7);
                int convertedToNumber = Integer.parseInt(listStringNumber) - 1;
                System.out.println(convertedToNumber);
                String doneness = list.get(convertedToNumber).toggleIsDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + doneness + "] " + list.get(convertedToNumber));
            } else {
                Task task = new Task(input);
                list.add(task);
                System.out.println("You entered: " + task);
            }

        }
        System.out.println(end);


    }
}

