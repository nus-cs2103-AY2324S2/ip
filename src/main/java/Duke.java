import java.util.Scanner;
public class Duke {
    public static String printIntro() {
        return " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n" + "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
    }

    public static String printOutro() {
        return "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
    }

    public static String list(Task[] a, int counter) {
        String x = ("Here are the tasks in your list: " + "\n" +
                "____________________________________________________________\n");
        String result = "";
        for( int i = 0; i < counter; i++ ) {
            result += (i + 1) + ". " + a[i].toString() + "\n";
        }
        String y =("____________________________________________________________\n");
        return x + result + y;
    }

    public static String printMark(Task task) {
        if(task.getStatus()) {
            return "____________________________________________________________\n" +
                    "Nice! I've marked this task as done:" + "\n" + task.toString() + "\n" +
                    "____________________________________________________________\n" ;
        }else{
            return "____________________________________________________________\n" +
                    "OK, I've marked this task as not done yet-:" + "\n" + task.toString() + "\n" +
                    "____________________________________________________________\n";
        }
    }

    public static String added(Task task, int count) {
        return "____________________________________________________________\n" +
                "Got it.I've added this task:" + "\n" + task.toString() + "\n" +
                "Now you have " + count + " tasks in the list." + "\n" +
                "____________________________________________________________\n";
    }

    public static String getTask( String message ){
        int start = message.indexOf(" ") + 1;
        int end = message.indexOf("/");
        String task = message.substring(start, end);
        return task;
    }

    public static String getTime(String message) {
        int start = message.indexOf("/");
        String time = message.substring(start + 1);
        return time;
    }

    public static String replace(String message) {
        String newString = message.replaceAll("/(\\w+)", "$1:");
        return newString;
    }
    public static void main(String[] args) {
        System.out.println(printIntro());
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Message");

        final int ArraySize = 100;
        Task[] tasks = new Task[ArraySize];
        int counter = 0;

        while (true) {
            String message = input.nextLine();

            if (message.equals("bye")) {
                System.out.println(printOutro());
                break;

            } else if (message.equals("list")) {
                System.out.println(list(tasks, counter));

            } else if (message.startsWith("mark") || message.startsWith("unmark")) {
                String[] parts = message.split(" ");
                int num = Integer.parseInt(parts[1]);
                Task current = tasks[num - 1];

                if (message.startsWith("mark")) {
                    current.markAsDone();
                    System.out.println(printMark(current));
                } else {
                    current.unmark();
                    System.out.println(printMark(current));
                }
            } else {
                String time = getTime(message);
                if (message.startsWith("todo")) {
                    String[] parts = message.split(" ", 2);
                    String task = parts[1];
                    tasks[counter] = new Todo(task);
                    counter++;
                    System.out.println(added(tasks[counter - 1], counter ));
                } else if (message.startsWith("deadline")) {
                    String task = getTask(message);
                    String[] parts = time.split("by");
                    tasks[counter] = new Deadline(task, parts[1]);
                    counter++;
                    System.out.println(added(tasks[counter -1], counter ));
                } else {
                    String task = getTask(message);
                    String[] parts = time.split("from");
                    String[] dateParts = parts[1].split("/to");
                    tasks[counter] = new Event(task, dateParts[0], dateParts[1]);
                    counter++;
                    System.out.println(added(tasks[counter -1], counter ));
                }

            }
        }
    }
    }

