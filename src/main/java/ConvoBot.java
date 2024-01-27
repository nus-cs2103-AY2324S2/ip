import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class ConvoBot {
    private static final String LEFT_PADDING = "    ";

    private static void printHorizontalLine(boolean newline) {
        System.out.println(LEFT_PADDING + "____________________________________________________________");
        if (newline) System.out.println();
    }

    private static void printWelcomeMsg() {
        printHorizontalLine(false);
        System.out.println(LEFT_PADDING + " Hello! I'm ConvoBot");
        System.out.println(LEFT_PADDING + " What can I do for you?");
        printHorizontalLine(true);
    }

    private static void printExitMsg() {
        printHorizontalLine(false);
        System.out.println(LEFT_PADDING + " Bye. Hope to see you again soon!");
        printHorizontalLine(true);
    }

    public static void main(String[] args) {
        printWelcomeMsg();

        ArrayList<Task> taskList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            printHorizontalLine(false);
            ArrayList<String> inputList = new ArrayList<>(Arrays.asList(userInput.split(" ")));
            try {
                String command = inputList.get(0);
                if (command.equals("list")) {
                    System.out.println(LEFT_PADDING + " " + "Here are the tasks in your list:"); 
                    for (int i = 0; i < taskList.size(); i++) {
                        int index = i+1;
                        System.out.println(LEFT_PADDING + " " + Integer.toString(index)
                            + "." + taskList.get(i).toString());
                    }
                } else if (command.equals("mark")) {
                    int i = -1;
                    try {
                        i = Integer.parseInt(inputList.get(1)) - 1;
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    }

                    if (inputList.size() != 2 || i < 0 || i >= taskList.size()) {
                        throw new ConvoBotException("Invalid input. You must specify a valid index to mark as done.");
                    }
                    taskList.get(i).markAsDone();
                    System.out.println(LEFT_PADDING + " " + "Nice! I've marked this task as done:");
                    System.out.println(LEFT_PADDING + " " + taskList.get(i).toString());
                } else if (command.equals("unmark")) {
                    int i = -1;
                    try {
                        i = Integer.parseInt(inputList.get(1)) - 1;
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    }
                    
                    if (inputList.size() != 2 || i < 0 || i >= taskList.size()) {
                        throw new ConvoBotException("Invalid input. You must specify a valid index to mark as done.");
                    }
                    taskList.get(i).markAsNotDone();
                    System.out.println(LEFT_PADDING + " " + "OK, I've marked this task as not done yet:");
                    System.out.println(LEFT_PADDING + " " + taskList.get(i).toString());
                } else if (command.equals("delete")) {
                    int i = -1;
                    try {
                        i = Integer.parseInt(inputList.get(1)) - 1;
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    }

                    if (inputList.size() != 2 || i < 0 || i >= taskList.size()) {
                        throw new ConvoBotException("Invalid input. You must specify a valid index to delete.");
                    }
                    String deletedTaskString = taskList.get(i).toString();
                    taskList.remove(i);
                    System.out.println(LEFT_PADDING + " " + "Noted. I've removed this task:");
                    System.out.println(LEFT_PADDING + "   " + deletedTaskString);
                    System.out.println(LEFT_PADDING + " Now you have " + Integer.toString(taskList.size()) + " tasks in the list.");
                } else {
                    Task task = null;
                    if (command.equals("todo")) {
                        if (userInput.length() == 4) {
                            throw new ConvoBotException("Invalid input. The description of a todo cannot be empty.");
                        }
                        task = new ToDo(userInput.substring(5));
                    } else if (command.equals("deadline")) {
                        int j = inputList.indexOf("/by");
                        if (j == 1) {
                            throw new ConvoBotException("Invalid input. The description of a deadline cannot be empty.");
                        }
                        if (j == -1 || j == inputList.size() - 1) {
                            throw new ConvoBotException("Invalid input. Start date missing.");
                        }
                        String description = String.join(" ", inputList.subList(1, j));
                        String by = String.join(" ", inputList.subList(j+1, inputList.size()));
                        task = new Deadline(description, by);
                    } else if (command.equals("event")) {
                        int j = inputList.indexOf("/from");
                        int k = inputList.indexOf("/to");
                        if (j == 1 || k == 1) {
                            throw new ConvoBotException("Invalid input. The description of an event cannot be empty.");
                        }
                        if (j == -1 || j == inputList.size() - 1 || inputList.get(j+1).equals("/to")) {
                            throw new ConvoBotException("Invalid input. Start date missing.");
                        }
                        if (k == -1 || k == inputList.size() - 1) {
                            throw new ConvoBotException("Invalid input. End date missing.");
                        }
                        String description = String.join(" ", inputList.subList(1, j));
                        String from = String.join(" ", inputList.subList(j+1, k));
                        String to = String.join(" ", inputList.subList(k+1, inputList.size()));
                        task = new Event(description, from, to);
                    } else {
                        throw new ConvoBotException("Invalid input. Input must start with list, mark, unmark, todo, deadline, event or delete.");
                    }
                    taskList.add(task);
                    System.out.println(LEFT_PADDING + " " + "Got it. I've added this task:");
                    System.out.println(LEFT_PADDING + "   " + task.toString());
                    System.out.println(LEFT_PADDING + " Now you have " + Integer.toString(taskList.size()) + " tasks in the list.");
                }
            } catch (ConvoBotException e) {
                System.out.println(LEFT_PADDING + " " + e.toString());
            }
            printHorizontalLine(true);
            userInput = scanner.nextLine();
        }

        printExitMsg();
    }
}
