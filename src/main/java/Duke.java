import task.Task;

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

        while (isRunning) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                isRunning = false;
            } else if (userInput.equals("list")) {
                printList();
            } else if (userInput.startsWith("mark")) {
                try{
                    int listIndex = Integer.parseInt(userInput.substring(5));
                    if (listIndex <= 0 || listIndex > todoList.size()) {
                        printMessage("Sorry, there's no task at that index!");
                    } else {
                        markTask(listIndex);
                    }
                } catch (NumberFormatException e) {
                    printMessage("Error. Please mark as done using: mark list_index");
                }
            } else if (userInput.startsWith("unmark")) {
                try{
                    int listIndex = Integer.parseInt(userInput.substring(7));
                    if (listIndex <= 0 || listIndex > todoList.size()) {
                        printMessage("Sorry, there's no task at that index!");
                    } else {
                        unmarkTask(listIndex);
                    }
                } catch (NumberFormatException e) {
                    printMessage("Sorry, please mark as done using: mark list_index");
                }
            } else {
                addToList(userInput);
            }
        }

        printMessage("Bye. Purr-lease chat again soon!");
    }

    public void printMessage(String message) {
        String line = "\t____________________________________________________________";
        System.out.println(line);
        System.out.println("\t" + message);
        System.out.println(line);
    }

    public void markTask(int index) {
        Task task = todoList.get(index - 1);
        task.markAsDone();
        printMessage("Ameowzing! I've marked this task as done:\n\t" + task.toListString());
    }

    public void unmarkTask(int index) {
        Task task = todoList.get(index - 1);
        task.unmarkAsDone();
        printMessage("OK, I've marked this task as not done yet:\n\t" + task.toListString());
    }

    public void addToList(String taskDescription) {
        Task newTask = new Task(taskDescription);
        todoList.add(newTask);
        printMessage("added: " + newTask);
    }

    public void printList() {
        String listString = "";
        for (int i = 1; i <= todoList.size(); i++) {
            if (i > 1) {
                listString += "\t";
            }
            listString += i + ". " + todoList.get(i - 1).toListString();
            if (i < todoList.size()) {
                listString += "\n";
            }
        }
        printMessage(listString);
    }
}
