import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private boolean isRunning;
    private ArrayList<Task> todoList = new ArrayList<>();

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        this.isRunning = true;

        String introMessage = " /\\_/\\\n" +
                "\t( o.o )\n" +
                "\t > ^ <\n" +
                "\tNya-ice to meet you! I'm Toothless :D\n" +
                "\tWhat can I do for you?";
        printMessage(introMessage);

        while (this.isRunning) {
            String userInput = scanner.nextLine();
            parseInput(userInput);
        }

        printMessage("Bye. Purr-lease chat again soon!");
    }

    public void parseInput(String userInput) {
        if (userInput.equals("bye")) {
            this.isRunning = false;
        } else if (userInput.equals("list")) {
            printList();
        } else if (userInput.startsWith("mark ") || userInput.startsWith("unmark ")) {
            // split string by spaces
            String[] userInputSplit = userInput.strip().split("\\s+");
            try {
                // try parsing integer
                int listIndex = Integer.parseInt(userInputSplit[1]);
                // check index bounds
                if (listIndex <= 0 || listIndex > todoList.size()) {
                    printMessage("Sorry, there's no task at that index!");
                } else {
                    if (userInputSplit[0].equals("mark")) { markTask(listIndex); }
                    else { unmarkTask(listIndex); }
                }
            } catch (NumberFormatException e) {
                printMessage("Error. Please user a numeric list index.");
            } catch (ArrayIndexOutOfBoundsException e) {
                printMessage("Error. Please state a list index to mark.");
            }
        } else if (userInput.startsWith("todo ")) {
            String taskDescription = userInput.replace("todo ", "");
            addToDoToList(taskDescription);
        } else if (userInput.startsWith("deadline ")) {
            String[] taskAttributes = userInput.replace("deadline ", "").split("/by");
            try {
                addDeadlineToList(taskAttributes[0].strip(), taskAttributes[1].strip());
            } catch (ArrayIndexOutOfBoundsException e) {
                printMessage("Error. Missing /by");
            }

        } else if (userInput.startsWith("event ")) {
            // Assume /from is before /to
            String[] taskAttributes = userInput.replace("event ", "").split("/from|/to");
            try {
                addEventToList(taskAttributes[0].strip(), taskAttributes[1].strip(), taskAttributes[2].strip());
            } catch (ArrayIndexOutOfBoundsException e) {
                printMessage("Error. Missing /from or /to");
            }
        } else {
            printMessage("Sorry, I don't understand what that means D:");
        }
    }

    public void markTask(int index) {
        Task task = todoList.get(index - 1);
        task.markAsDone();
        printMessage("Ameowzing! I've marked this task as done:\n\t" + task);
    }

    public void unmarkTask(int index) {
        Task task = todoList.get(index - 1);
        task.unmarkAsDone();
        printMessage("OK, I've marked this task as not done yet:\n\t" + task);
    }

    public void addToDoToList(String taskDescription) {
        Task newTask = new ToDo(taskDescription);
        todoList.add(newTask);
        printNewTask(newTask);
    }

    public void addDeadlineToList(String taskDescription, String by) {
        Task newTask = new Deadline(taskDescription, by);
        todoList.add(newTask);
        printNewTask(newTask);
    }

    public void addEventToList(String taskDescription, String from, String to) {
        Task newTask = new Event(taskDescription, from, to);
        todoList.add(newTask);
        printNewTask(newTask);
    }

    public void printNewTask(Task newTask) {
        String message = String.format("Got it. I've added this task:\n\t\t%s\n\tNow you have %d tasks in the list.",
                newTask, todoList.size());

        printMessage(message);
    }

    public void printList() {
        String listString = "";
        for (int i = 1; i <= todoList.size(); i++) {
            if (i > 1) {
                listString += "\t";
            }
            listString += i + ". " + todoList.get(i - 1);
            if (i < todoList.size()) {
                listString += "\n";
            }
        }
        printMessage(listString);
    }

    public void printMessage(String message) {
        String line = "\t____________________________________________________________";
        System.out.println(line);
        System.out.println("\t" + message);
        System.out.println(line);
    }
}
