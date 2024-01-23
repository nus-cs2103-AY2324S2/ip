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
        Task addedTask = null;
        if (input.length() >= 6 && input.substring(0,4).toLowerCase().equals("todo")){
            addedTask = new Todo(input.substring(5,input.length()));
        } else if (input.length() >= 7 && input.substring(0,5).toLowerCase().equals("event")) {
            String[] arr = input.split(" /");
            addedTask = new Event(arr[0].substring(6, arr[0].length()), arr[1], arr[2]);
        } else if (input.length() >= 10 && input.substring(0,8).toLowerCase().equals("deadline")) {
            String[] arr = input.split(" /");
            addedTask = new Deadline(arr[0].substring(9, arr[0].length()), arr[1]);
        } else {
            addedTask = new Task(input);
        }
        tasks[numOfTasks++] = addedTask;
        String message = String.format(
                "____________________________________________________________\n" +
                " Got it. I've added this task:\n" +
                "  [%s][%s] %s\n" +
                " Now you have %d tasks in the list.\n" +
                "____________________________________________________________\n",
                addedTask.getTaskType(), addedTask.getStatusIcon(),
                addedTask.toString(), numOfTasks);
        System.out.println(message);
    }
    public static void printList(){
        System.out.println("____________________________________________________________\n" +
                " Here are the tasks in your list:\n");
        for (int i = 0; i < numOfTasks; i++) {
            String currentTask = String.format("%d.[%s][%s] %s",
                    i + 1, tasks[i].getTaskType(),
                    tasks[i].getStatusIcon(), tasks[i].toString());
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
                            "  [%s][%s] %s\n" +
                    "____________________________________________________________\n",
                    targetTask.getTaskType(), targetTask.getStatusIcon(),
                    targetTask.getDescription());
            System.out.println(message);
        } else {
            targetTask.setAsNotDone();
            String message = String.format(
                    "____________________________________________________________\n" +
                            " OK, I've marked this task as not done yet:\n" +
                            "  [%s][%s] %s\n" +
                            "____________________________________________________________\n",
                    targetTask.getTaskType(), targetTask.getStatusIcon(),
                    targetTask.getDescription());
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
