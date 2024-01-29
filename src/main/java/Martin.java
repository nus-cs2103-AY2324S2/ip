import java.util.ArrayList;
import java.util.Scanner;

public class Martin {
    static String NAME = "Martin";
    static ArrayList<Task> todoList = new ArrayList<>();

    public static void main(String[] args) {
        // stop gap solution to magic numbers for task numbering
        todoList.add(new Todo("dummy offset"));

        sayGreeting();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine().strip();
            String[] words = line.split(" ");
            String firstWord = words[0].toUpperCase();
            String remainingWords = line.substring(firstWord.length()).strip();
            try {
                ChatbotKeyword command = ChatbotKeyword.valueOf(firstWord);
                handleCommand(command, remainingWords);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command: " + firstWord);
                continue;
            }
        }
        sc.close();
    }

    public static void handleCommand(ChatbotKeyword command, String inputs) throws IllegalArgumentException {
        String[] inputsArray = inputs.split(" "); // second param as -1 might be a soln to bug
        // System.out.println("inputs: " + inputs);
        // System.out.println("inputsArray: " + Arrays.toString(inputsArray));
        // System.out.println(inputsArray.length);
        switch (command) {
            case BYE:
                sayBye();
                break;
            case LIST:
                printList();
                break;
            case MARK:
                if (inputsArray.length < 1) {
                    System.out.println("Please specify a task number to mark");
                    break;
                }
                int taskToMark = Integer.parseInt(inputsArray[0]);
                todoList.get(taskToMark).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(todoList.get(taskToMark));
                break;
            case UNMARK:
                if (inputsArray.length < 1) {
                    System.out.println("Please specify a task number to unmark");
                    break;
                }
                int taskToUnmark = Integer.parseInt(inputsArray[0]);
                todoList.get(taskToUnmark).unmarkAsDone();
                System.out.println("Okay, I've unmarked it");
                System.out.println(todoList.get(taskToUnmark));
                break;
            case TODO:
                if (inputsArray.length < 1) {
                    throw new IllegalArgumentException("Please specify a todo description");
                }
                String todoDescription = String.join(" ", inputs);
                Todo todo = new Todo(todoDescription);
                todoList.add(todo);
                System.out.println("Got it. I've added this todo: " + todoDescription);
                break;
            case EVENT:
                if (inputsArray.length < 3) {
                    throw new IllegalArgumentException("Please specify a event description, from, and to");
                }
                String eventDescription = String.join(" ", inputs);
                String from = inputsArray[1];
                String to = inputsArray[2];
                Event event = new Event(eventDescription, from, to);
                todoList.add(event);
                System.out.println("Got it. I've added this event: " + eventDescription);
                break;
            case DEADLINE:
                if (inputsArray.length < 2) {
                    System.out.println("Please specify a deadline description and deadline");
                    break;
                }
                String deadlineDescription = String.join(" ", inputs);
                String deadline = inputsArray[1];
                Deadline deadlineTask = new Deadline(deadlineDescription, deadline);
                todoList.add(deadlineTask);
                System.out.println("Got it. I've added this deadline: " + deadlineDescription);
                break;
            case DELETE:
                if (inputsArray.length < 1) {
                    throw new IllegalArgumentException("Please specify a task number to delete");
                }
                int taskToDelete = Integer.parseInt(inputsArray[0]);
                Task deletedTask = todoList.remove(taskToDelete);
                System.out.println("Noted. I've removed this task:" + deletedTask);
                break;
        }

    }

    public static void printList() {
        // 1-indexed todolist
        for (int i = 1; i < todoList.size(); i++) {
            System.out.println(i + "." + todoList.get(i));
        }
    }

    public static void sayGreeting() {
        System.out.println("Hello from " + NAME);
        System.out.println("What can I do for you?");
    }

    public static void sayBye() {
        System.out.println("Bye from " + NAME);
    }
}
