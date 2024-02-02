import java.io.*;
import java.util.*;

public class Duke {
    enum Command {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE
    }
    public static void main(String[] args) throws IOException {

        ArrayList<Task> list = new ArrayList<>();

        // Start-up introduction
        printBreak();
        System.out.println("Hello! I'm Klara");
        System.out.println("What can I do for you?");
        printBreak();

        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        while (!word.equals("bye")) {
            // print list on demand
            if (word.equals("list")) {
                printList(list);
                word = sc.nextLine();
            // implementation of mark
            } else if (word.startsWith("mark")) {
                String[] split = word.split(" ");
                Task extractedTask = list.get(Integer.valueOf(split[1]) - 1);
                extractedTask.markAsDone();
                printBreak();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(extractedTask);
                printBreak();
                word = sc.nextLine();
            } else if (word.startsWith("unmark")) {
                String[] split = word.split(" ");
                Task extractedTask = list.get(Integer.valueOf(split[1]) - 1);
                extractedTask.markAsUndone();
                printBreak();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(extractedTask);
                printBreak();
                word = sc.nextLine();
            } else if (word.startsWith("deadline")) {
                String[] split = word.split(" /by ");
                try {
                    if (split.length < 2) {
                        throw new DukeException("Where is the rest of your description?");
                    }
                    String dateline = split[1];
                    String[] splitAgain = split[0].split(" ", 2);
                    Deadline deadline = new Deadline(splitAgain[1], dateline);
                    list.add(deadline);
                    printBreak();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + deadline);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    printBreak();
                } catch (DukeException d) {
                    printBreak();
                    System.out.println(d.getMessage());
                    printBreak();
                }
                word = sc.nextLine();
            } else if (word.startsWith("todo")) {
                String[] split = word.split(" ", 2);
                try {
                    if (split.length < 2) {
                        throw new DukeException("Where is the rest of your description?");
                    }
                    String description = split[1];
                    Todo todo = new Todo(description);
                    list.add(todo);
                    printBreak();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + todo);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    printBreak();
                } catch (DukeException d){
                    printBreak();
                    System.out.println(d.getMessage());
                    printBreak();
                }
                word = sc.nextLine();
            } else if (word.startsWith("event")) {
                String[] split = word.split(" /from ");
                try {
                    if (split.length < 2) {
                        throw new DukeException("Where is the rest of your description?");
                    }
                    String description = split[0].split(" ", 2)[1];
                    String from = split[1].split(" /to ")[0];
                    String to = split[1].split(" /to ")[1];
                    Event event = new Event(description, from, to);
                    list.add(event);
                    printBreak();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + event);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    printBreak();
                } catch (DukeException d) {
                    printBreak();
                    System.out.println(d.getMessage());
                    printBreak();
                }
                word = sc.nextLine();
            } else if (word.startsWith("delete")) {
                String[] split = word.split(" ");
                try {
                    if (split.length < 2) {
                        throw new DukeException("Which task do you want to delete?");
                    }
                    int number = Integer.parseInt(split[1]);
                    Task task = list.get(number -  1);
                    printBreak();
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(" " + task);
                    list.remove(number - 1);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    printBreak();
                } catch (DukeException d) {
                    printBreak();
                    System.out.println(d.getMessage());
                    printBreak();
                }
                word = sc.nextLine();
            } else {
                try {
                    throw new DukeException("I don't understand bro");
                } catch (DukeException d) {
                    printBreak();
                    System.out.println(d.getMessage());
                    printBreak();
                    word = sc.nextLine();
                }
            }
        }

        // Logging off upon "bye" command
        printBreak();
        System.out.println("Bye. Hope to see you again soon!");
        printBreak();
    }


    private static void printBreak() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    /**
     * Print all elements in the list
      * @param list
     */
    private static void printList(ArrayList<Task> list) {
        printBreak();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isDone) {
                System.out.println(Integer.toString(i + 1) + "." + list.get(i));
            }
            else {
                System.out.println(Integer.toString(i + 1) + "." + list.get(i));
            }
        }
        printBreak();
    }


}
