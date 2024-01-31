import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static void greeting(String botName) {
        System.out.println("Hello! I'm " + botName + "\n"
                + "What can I do for you?");
    }

    static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    static void echo(String userInput, ArrayList<Task> TodoList) {
        String[] words = userInput.split("\\s+");
        if (words.length > 0) {
            String firstWord = words[0];
            int firstSpaceIndex = userInput.indexOf(' ');
            String description = userInput.substring(firstSpaceIndex + 1);
            switch (firstWord) {
                case "todo": {
                    if (words.length == 1) {
                        System.out.println("The description of a todo cannot be empty!");
                    } else {
                        Task t = new Todo(description);
                        TodoList.add(t);
                        listOverviewAfterAdding(t, TodoList);
                    }
                    break;
                }
                case "deadline": {
                    if (words.length == 1) {
                        System.out.println("The description of a deadline cannot be empty!");
                    } else {
                        String[] parts = description.split("\\\\by");
                        String ddl_description = parts.length > 0 ? parts[0].trim() : "";
                        String ddl_time = parts.length > 1 ? parts[1].trim() : "";
                        Task t = new Deadline(ddl_description, ddl_time);
                        TodoList.add(t);
                        listOverviewAfterAdding(t, TodoList);
                    }
                    break;
                }
                case "event": {
                    if (words.length == 1) {
                        System.out.println("The description of a todo cannot be empty!");
                    } else {
                        String[] parts = userInput.split("\\\\from|\\\\to");

                        // Collect words before "\from" into one string
                        String event_description = parts.length > 0 ? parts[0].trim() : "";

                        // Collect words between "\from" and "\to" into one string
                        String event_from = parts.length > 1 ? parts[1].trim() : "";

                        // Collect words after "\to" into one string
                        String event_to = parts.length > 2 ? parts[2].trim() : "";
                        Task t = new Event(event_description, event_from, event_to);
                        TodoList.add(t);
                        listOverviewAfterAdding(t, TodoList);
                    }
                    break;
                }
                default:
                    System.out.println("Sorry, I don't understand your command.");
                    break;
            }
        } else {
            System.out.println("Sorry, I don't understand your command.");
        }
    }

    static void printList(ArrayList<Task> TodoList) {
        System.out.println("Here are the tasks in your list:");
        int length = TodoList.size();
        for (int i = 0; i < length; i++) {
            String pos = String.valueOf(i + 1);
            System.out.println(pos + "." + TodoList.get(i));
        }
    }

    static void listOverviewAfterAdding(Task t, ArrayList<Task> TodoList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        int length = TodoList.size();
        if (length == 1) {
            System.out.println("Now you have " + length + " task in the list.");
        } else {
            System.out.println("Now you have " + length + " tasks in the list.");
        }
    }

    static boolean isMarkTask(String userInput) {
        String[] words = userInput.split("\\s+");
        if (words.length == 2) {
            if (words[0].equals("mark") || words[0].equals("unmark")) {
                try {
                    int number = Integer.parseInt(words[1]);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        return false;
    }

    static void changeMarkingOfTask(String userInput, ArrayList<Task> TodoList) {
        String[] words = userInput.split("\\s+");
        int number = Integer.parseInt(words[1]);
        Task t = TodoList.get(number - 1);
        if (words[0].equals("mark")) {
            t.markAsDone();
        } else {
            t.unmark();
        }
    }

    static boolean isDeleteTask(String userInput) {
        String[] words = userInput.split("\\s+");
        if (words.length == 2) {
            if (words[0].equals("delete")) {
                try {
                    int number = Integer.parseInt(words[1]);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        return false;
    }

    static void deleteTask(String userInput, ArrayList<Task> TodoList) {
        String[] words = userInput.split("\\s+");
        int number = Integer.parseInt(words[1]);
        Task t = TodoList.remove(number - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        int length = TodoList.size();
        if (length == 1) {
            System.out.println("Now you have " + length + " task in the list.");
        } else {
            System.out.println("Now you have " + length + " tasks in the list.");
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String botName = "Zizhen";
        greeting(botName);

        ArrayList<Task> TodoList = new ArrayList<>();

        boolean isExit = false;
        while (!isExit) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                isExit = true;
            } else if (userInput.equals("list")) {
                printList(TodoList);
            } else if (isMarkTask(userInput)) {
                changeMarkingOfTask(userInput, TodoList);
            } else if (isDeleteTask(userInput)) {
                deleteTask(userInput, TodoList);
            } else {
                echo(userInput, TodoList);
            }
        }

        exit();
    }
}
