import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    static String name = "Lunaris";
    static String indentation = "  ";
    static String indentedLine = "  _________________________________________________________";
    // Just for convenience of copy paste.
    // System.out.println(indentedLine);

    static ArrayList<Task> inputList = new ArrayList<>();

    public enum Command {
        BYE, LIST, UNMARK, MARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN
    }

    public static void main(String[] args) {

        // Print out greeting message
        System.out.println(indentedLine);
        System.out.println(indentation + "Hey! I'm " + name + "\n"
                + indentation + "Is there anything I can do for you?");
        System.out.println(indentedLine);

        Scanner sc = new Scanner(System.in);

        while (true) {
            Command category = getCommand(sc.next());
            switch (category) {
                case BYE:
                    System.out.println(indentedLine);
                    System.out.println(indentation +
                            "Leaving so soon? Alright, have a great day ahead!");
                    System.out.println(indentedLine);
                    return;
                case LIST:
                    System.out.println(indentedLine);
                    System.out.println(indentation + "Here are the tasks in your list:");
                    for (int i = 0; i < inputList.size(); i++) {
                        Task currTask = inputList.get(i);
                        System.out.println(indentation + (i + 1) + "." + currTask.toString());
                    }
                    System.out.println(indentedLine);
                    break;
                default:
                    addTask(inputList, category, sc);
            }
        }
    }

    public static Command getCommand(String input) {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    public static void addTask(ArrayList<Task> inputList, Command category, Scanner sc) {
        switch (category) {
            case UNMARK:
                if (sc.hasNextInt()) {
                    int taskId = sc.nextInt();
                    inputList.get(taskId - 1).markNotDone();
                    System.out.println(indentedLine);
                    System.out.println(indentation + "Ok, I've marked this task as not done yet:");
                    System.out.println(indentation + inputList.get(taskId - 1).toString());
                    System.out.println(indentedLine);
                    break;
                } else {
                    try {
                        System.out.println(indentedLine);
                        throw new DukeException("Sorry, but which task do you want me to unmark?");
                    } catch (DukeException e) {
                        System.out.println(indentation + e.getMessage());
                        System.out.println(indentedLine);
                    }
                }
                break;
            case MARK:
                if (sc.hasNextInt()) {
                    int taskId = sc.nextInt();
                    inputList.get(taskId - 1).markDone();
                    System.out.println(indentedLine);
                    System.out.println(indentation + "Nice! I've marked this task as done:");
                    System.out.println(indentation + inputList.get(taskId - 1).toString());
                    System.out.println(indentedLine);
                    break;
                } else {
                    try {
                        System.out.println(indentedLine);
                        throw new DukeException("Sorry, but which task do you want me to mark?");
                    } catch (DukeException e) {
                        System.out.println(indentation + e.getMessage());
                        System.out.println(indentedLine);
                        sc.next();
                    }
                }
                break;
            case DELETE:
                if (sc.hasNextInt()) {
                    int taskId = sc.nextInt();
                    System.out.println(indentedLine);
                    System.out.println(indentation + "Noted. I've removed this task:");
                    System.out.println(indentation + inputList.get(taskId - 1).toString());
                    inputList.remove(taskId - 1);
                    System.out.println(indentation + "Now you have " + inputList.size() + " tasks in the list.");
                    System.out.println(indentedLine);
                    break;
                } else {
                    try {
                        System.out.println(indentedLine);
                        throw new DukeException("Sorry, but which task do you want me to delete?");
                    } catch (DukeException e) {
                        System.out.println(indentation + e.getMessage());
                        System.out.println(indentedLine);
                    }
                }
            case TODO:
                String toDoDescription = sc.nextLine();
                if (toDoDescription.isEmpty()) {
                    try {
                        System.out.println(indentedLine);
                        throw new DukeException("Sorry, please give me a description of the todo as well! >.<\n" +
                                indentation + "Format should be todo (description)!");
                    } catch (DukeException e) {
                        System.out.println(indentation + e.getMessage());
                        System.out.println(indentedLine);
                        return;
                    }
                }
                ToDo toDo = new ToDo(toDoDescription);
                inputList.add(toDo);
                System.out.println(indentation + toDo);
                System.out.println(indentation + "Now you have " + inputList.size() + " tasks in the list.");
                System.out.println(indentedLine);
                break;
            case DEADLINE:
                String deadlineDescription = sc.nextLine();
                if (!deadlineDescription.contains(" /by ")) {
                    try {
                        System.out.println(indentedLine);
                        throw new DukeException("Sorry, please give me a description of the deadline as well! >.<\n" +
                                indentation + "Format should be deadline (description) /by (date)!");
                    } catch (DukeException e) {
                        System.out.println(indentation + e.getMessage());
                        System.out.println(indentedLine);
                        return;
                    }
                }
                String[] deadlineArguments = deadlineDescription.split(" /by ");
                Deadline deadline = new Deadline(deadlineArguments[0], deadlineArguments[1]);
                inputList.add(deadline);
                System.out.println(indentation + deadline);
                System.out.println(indentation + "Now you have " + inputList.size() + " tasks in the list.");
                System.out.println(indentedLine);
                break;
            case EVENT:
                String eventDescription = sc.nextLine();
                if (!eventDescription.contains(" /from ") || !eventDescription.contains(" /to ")) {
                    try {
                        System.out.println(indentedLine);
                        throw new DukeException("Sorry, please give me a description of the event as well! >.<\n" +
                                indentation + "Format should be event (description) /from (time) /to (time)!");
                    } catch (DukeException e) {
                        System.out.println(indentation + e.getMessage());
                        System.out.println(indentedLine);
                        return;
                    }
                }
                String[] eventArguments = eventDescription.split(" /from ");
                String[] eventDuration = eventArguments[1].split(" /to ");
                Event event = new Event(eventArguments[0], eventDuration[0], eventDuration[1]);
                inputList.add(event);
                System.out.println(indentation + event);
                System.out.println(indentation + "Now you have " + inputList.size() + " tasks in the list.");
                System.out.println(indentedLine);
                break;
            default:
                try {
                    System.out.println(indentedLine);
                    throw new DukeException("Sorry, I cannot understand what this is!");
                } catch (DukeException e) {
                    System.out.println(indentation + e.getMessage());
                    System.out.println(indentedLine);
                }
        }
    }
}
