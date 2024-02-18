import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static DataManager file = new DataManager("./data/duke.txt");
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

    public static String list(ArrayList<Task> a, int counter) {
        String x = ("Here are the tasks in your list: " + "\n" +
                "____________________________________________________________\n");
        String result = "";
        for (int i = 0; i < counter; i++) {
            result += (i + 1) + ". " + a.get(i).toString() + "\n";
        }
        String y = ("____________________________________________________________\n");
        return x + result + y;
    }

    public static String printMark(Task task) {
        if (task.getStatus()) {
            return "____________________________________________________________\n" +
                    "Nice! I've marked this task as done:" + "\n" + task.toString() + "\n" +
                    "____________________________________________________________\n";
        } else {
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

    public static String getTask(String message) {
        int start = message.indexOf(" ") + 1;
        int end = message.indexOf("/");
        if (end == -1) {
            // "/" not found, handle the case, e.g., throw an exception or return the rest of the string
            end = message.length();
        }
        String task = message.substring(start, end);
        return task.trim();
    }


    public static String getTime(String message) {
        int start = message.indexOf("/");
        String time = message.substring(start + 1);
        return time;
    }

    public static String deleteMessage(Task task, int count) {
        return "____________________________________________________________\n" +
                "Noted. I've removed this task:" + "\n" + task.toString() + "\n" +
                "Now you have " + count + " tasks in the list." + "\n" +
                "____________________________________________________________\n";
    }


    public static String replace(String message) {
        String newString = message.replaceAll("/(\\w+)", "$1:");
        return newString;
    }

    public static LocalDateTime parseToLocalDate(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(time,formatter);
    }

    public static void main(String[] args) {
        System.out.println(printIntro());
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Message");

        final int ArraySize = 100;
        ArrayList<Task> tasks = file.retrieveTasks();
        int counter = tasks.size();

        while (true) {
            String message = input.nextLine();

            try {
                if (message.equals("bye")) {
                    System.out.println(printOutro());
                    break;

                } else if (message.equals("list")) {
                    System.out.println(list(tasks, tasks.size()));

                } else if (message.startsWith("mark") || message.startsWith("unmark") || message.startsWith("delete")) {
                    String[] parts = message.split(" ");
                    int num = Integer.parseInt(parts[1]);
                    Task current = tasks.get(num - 1);

                    if (message.startsWith("mark")) {
                        current.markAsDone();
                        System.out.println(printMark(current));
                        file.saveTasks(tasks);
                    } else if (message.startsWith("unmark")){
                        current.unmark();
                        System.out.println(printMark(current));
                        file.saveTasks(tasks);
                    }else{
                        tasks.remove(num - 1);
                        System.out.println(deleteMessage(current, tasks.size()));
                        file.saveTasks(tasks);
                    }
                } else if (message.equals("todo") || message.equals("deadline") || message.equals("event")){
                    throw new DukeExceptions("Don't forget the description !");
                } else if(message.startsWith("todo") || message.startsWith("deadline") || message.startsWith("event")) {
                    String time = getTime(message);
                    if (message.startsWith("todo")) {
                        String[] parts = message.split(" ", 2);
                        String task = parts[1];
//                        tasks[counter] = new Todo(task);
                        tasks.add(new Todo(task, false));
                        counter++;
                        System.out.println(added(tasks.get(counter - 1), counter));
                        file.saveTasks(tasks);
                    } else if (message.startsWith("deadline")) {
                        String task = getTask(message);
                        String[] parts = time.split("by");
                        LocalDateTime by = parseToLocalDate(parts[1].trim());
                        tasks.add(new Deadline(task, false, by) );
                        counter++;
                        System.out.println(added(tasks.get(counter - 1), counter));
                        file.saveTasks(tasks);
                    } else {
                        String task = getTask(message);
                        String[] parts = time.split("from");
                        String[] dateParts = parts[1].trim().split("/to");
                        LocalDateTime from = parseToLocalDate(dateParts[0].trim());
                        LocalDateTime to = parseToLocalDate(dateParts[1].trim());
                        tasks.add(new Event(task, false, from, to));
                        counter++;
                        System.out.println(added(tasks.get(counter - 1), counter));
                        file.saveTasks(tasks);
                    }

                } else {
                    throw new DukeExceptions("Sorry, I'm not sure what you mean");
                }


            } catch (DukeExceptions e) {
                System.out.println(e.getMessage());
            }
        }
    }
}