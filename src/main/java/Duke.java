import java.util.Scanner;

public class Duke {
    static String botName = "Corgi";
    static Task[] tasks = new Task[100];
    static int numOfTasks = 0;
    public static void greet(){
        String greetMessage = String.format(
                "____________________________________________________________\n" +
                        " Hello! I'm %s\n" +
                        " What can I do for you?\n" +
                        "____________________________________________________________\n", botName);
        System.out.println(greetMessage);
    }
    public static void goodbye(){
        String exitMessage = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(exitMessage);
    }
    public static void echo(String input){
        System.out.println("____________________________________________________________\n" +
                input +
                "\n____________________________________________________________\n");
    }
    public static void addToList(String input){
        Task addedTask = new Task(input);
        tasks[numOfTasks++] = addedTask;
        String message = String.format("____________________________________________________________\n" +
                " added: %s\n" +
                "____________________________________________________________\n", addedTask.getDescription());
        System.out.println(message);
    }
    public static void printList(){
        System.out.println("____________________________________________________________\n");
        for (int i = 0; i < numOfTasks; i++) {
            String currentTask = String.format("%d.[%s] %s",
                    i + 1, tasks[i].getStatusIcon(), tasks[i].getDescription());
            System.out.println(currentTask);
        }
        System.out.println("____________________________________________________________\n");
    }
    public static void markAsDoneOrUndone(int taskNum, boolean markDone){
        if(taskNum < 1 || taskNum > 100){
            System.out.println("Invalid task number entered.");
            return;
        }
        Task targetTask = tasks[taskNum - 1];
        if (markDone) {
            targetTask.setAsDone();
            String message = String.format(
                    "____________________________________________________________\n" +
                            " Nice! I've marked this task as done:\n" +
                            "  [%s] %s\n" +
                    "____________________________________________________________\n",
                    targetTask.getStatusIcon(), targetTask.getDescription());
            System.out.println(message);
        } else {
            targetTask.setAsNotDone();
            String message = String.format(
                    "____________________________________________________________\n" +
                            " OK, I've marked this task as not done yet:\n" +
                            "  [%s] %s\n" +
                            "____________________________________________________________\n",
                    targetTask.getStatusIcon(), targetTask.getDescription());
            System.out.println(message);
        }
    }
    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        String input = "";
        while (true){
            input = sc.nextLine();
            if(input.toLowerCase().equals("bye")){
                break;
            } else if (input.toLowerCase().equals("list")) {
                printList();
            } else if (input.length() >= 6 && input.substring(0,4).toLowerCase().equals("mark")) {
                markAsDoneOrUndone(Character.getNumericValue(input.charAt(5)), true);
            } else if (input.length() >= 8 && input.substring(0,6).toLowerCase().equals("unmark")) {
                markAsDoneOrUndone(Character.getNumericValue(input.charAt(7)), false);
            } else {
                addToList(input);
            }
        }
        goodbye();
    }
}
