import java.util.Scanner;
import java.util.ArrayList;

public class Shirmin {
    public static void displayLine() {
        System.out.println(gap() + "_".repeat(50));
    }
    public static String gap() {
        return "    ";
    }
    public static void wrapInLines(String line){
        displayLine();
        System.out.println(line);
        displayLine();
    }
    public static void echo(String line) {
        wrapInLines(line);
    }
    public static void greet() {
        wrapInLines(gap() + "Hello! I'm Shirmin" + "\n"
                + gap() + "What can I do for you?");
    }
    public static void exit() {
        wrapInLines(gap() +"Bye. Hope to see you again soon!");
    }


    //static Task[] taskList = new Task[100];
    static ArrayList<Task> taskList = new ArrayList<>();
    static int currIndex = 0;
    enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID;
        static CommandType getCommandType(String command) {
            try {
                return valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                return INVALID;
            }
        }
    }
    public static void listFunction(String line){
        String[] command = line.split(" ", 2);
        CommandType commandType = CommandType.getCommandType(command[0]);

        switch (commandType) {
        case LIST:
            displayList(taskList);
            break;

        case MARK:
            try {
                int taskIndex = Integer.parseInt(command[1]) - 1;
                if (taskIndex < currIndex) {
                    taskList.get(taskIndex).markDone();
                    // displayLine();
                    System.out.println(gap() + "Nice! I've marked this task as done:");
                    System.out.println(gap() + gap() + taskList.get(taskIndex));
                    displayLine();
                } else { // out of range
                    System.out.println("invalid, out of range");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid task number: " + command[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("There are " + currIndex + "numbers, please enter a number from 1 to " + currIndex);
            }
            break;
        case UNMARK:
            try {
                int taskIndex = Integer.parseInt(command[1]) - 1;
                if (taskIndex < currIndex) {
                    taskList.get(taskIndex).markUndone();
                    displayLine();
                    System.out.println(gap() + "OK, I've marked this task as not done yet:");
                    System.out.println(gap() + gap() + taskList.get(taskIndex));
                    displayLine();
                } else { // out of range
                    System.out.println("invalid, out of range");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid task number: " + command[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("There are " + currIndex + " items, please enter a number from 1 to " + currIndex);
            }
            break;
        case TODO:
            try {
                Task newTodo = new Todo(command[1]);
                taskList.add(newTodo);
                // taskList[currIndex] = newTodo;
                currIndex++;
                addMessage(newTodo, currIndex);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("oopsy doopsy you made a -ucky wucky! The description of a todo cannot be empty.");
            }
            break;
        case DEADLINE:
            try {
                String[] details = command[1].split(" /by ");
                String description = details[0];
                String by = details[1];

                Task newDeadline = new Deadline(description, by);
                taskList.add(newDeadline);
                currIndex++;
                addMessage(newDeadline, currIndex);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("oopsy doopsy you made a -ucky wucky! The description of a deadline" +
                        " must be in the format 'deadline [task] /by [time]'.");
            }
            break;
        case EVENT:
            try {
                String[] details = command[1].split(" /from ");
                String description = details[0];
                String[] fromTo = details[1].split(" /to ");
                String from = fromTo[0];
                String to = fromTo[1];

                Task newEvent = new Event(description, from, to);
                taskList.add(newEvent);
                currIndex++;
                addMessage(newEvent, currIndex);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("oopsy doopsy you made a -ucky wucky! The description of a deadline" +
                        " must be in the format 'deadline [task] /from [time]' /to [time].");
            }
            break;
        case DELETE:
            try {
                int taskIndex = Integer.parseInt(command[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskList.size()) {
                    Task removedTask = taskList.remove(taskIndex);
                    displayLine();
                    System.out.println(gap() + "Ok, I've removed the task:");
                    System.out.println(gap() + gap() + removedTask);
                    System.out.println(gap() + "You have " + taskList.size() + " tasks remaining in the list.");
                    displayLine();
                } else {
                    System.out.println("Invalid task number: " + (taskIndex + 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid task number: " + command[1]);
            }
            break;
        default:
            System.out.println("OH NO I'm not sure what that command is. You may use the commands " +
                    "todo, deadline, list, event, mark and unmark");
            break;
        }
    }

    public static <T extends Task> void addMessage(T task, Integer number){
        displayLine();
        System.out.println(gap() + "Got it. I've added this task:");
        System.out.println(gap() + gap() + task.toString());
        System.out.println(gap() + "Now you have " + number.toString() + " tasks in the list.");
        displayLine();
    }

    public static void displayList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            System.out.println(gap() + "There are no tasks in your list.");
        } else {
            System.out.println(gap() + "Here are the tasks in your list:");
            int i = 1;
            for (Task t : list) {
                System.out.println(gap() + i + "." + t.toString());
                i++;
            }
        }
        displayLine();
    }


    public static void main(String[] args){
        greet();
        Scanner scanner = new Scanner(System.in);
        String currLine = scanner.nextLine();
        while(!currLine.equals("bye")) {
            listFunction(currLine);
            currLine = scanner.nextLine();
        }
        exit();
    }
}
