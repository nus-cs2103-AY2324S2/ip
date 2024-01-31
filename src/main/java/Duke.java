import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    static void greeting(String botName) {
        System.out.println("Hello! I'm " + botName + "\n"
                + "What can I do for you?");
    }

    static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    static void echo(String userInput, ArrayList<Task> TodoList) {
        System.out.println("added: " + userInput);
        Task t = new Task(userInput);
        TodoList.add(t);
    }

    static void printList(ArrayList<Task> TodoList) {
        System.out.println("Here are the tasks in your list:");
        int length = TodoList.size();
        for (int i = 0; i < length; i++) {
            String pos = String.valueOf(i + 1);
            System.out.println(pos + "." + TodoList.get(i));
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
            } else {
                echo(userInput, TodoList);
            }
        }

        exit();
    }
}
