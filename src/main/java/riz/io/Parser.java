package riz.io;

import riz.data.*;
import riz.data.*;

import java.util.Scanner;

/**
 * Parser class takes in the inputs and processes them
 * to call the appropriate functions in order.
 */
public class Parser {
    /**
     * The main parser class that takes in the input from the user and separates
     * the commands from the data, and create the appropriate task types.
     *
     * @param taskList List of all the Tasks at hand currently.
     * @param storage  The Storage object that loads and writes to save the data.
     * @param input    The user input that
     */
    public static String parse(TaskList taskList, Storage storage, String input) {
        assert input != null && !input.isEmpty() : "Input string cannot be null or empty";
        StringBuilder sb = new StringBuilder();
        String[] token = input.split(" ", 2);
        String cmd = token[0];
        switch (cmd) {
            case "clear":
                clearTasks(taskList, storage, sb);
                break;
            case "find":
                findTask(taskList, sb, token);
                break;
            case "mark":
                markTask(taskList, storage, sb, token);
                break;
            case "unmark":
                unmarkTask(taskList, storage, sb, token);
                break;
            case "list":
                listTasks(storage, sb);
                break;
            case "delete":
                deleteTask(taskList, storage, sb, token);
                break;
            case "todo":
                addToDo(taskList, storage, sb, token);
                break;
            case "deadline":
                addDeadline(taskList, storage, sb, token);
                break;
            case "event":
                addEvent(taskList, storage, sb, token);
                break;
            default:
                sb.append(Ui.yapError());

        }
        int size = storage.countFromFile();
        if (size == 0) {
            sb.append(" You currently have nothing to do... yay...");
        } else if (size == 1) {
            sb.append(" You currently have only one thing to do... just get it done with...");
        } else {
            sb.append(" You currently have ").append(size).append(" things to do...");
        }
        sb.append("\n\n");
        return sb.toString();
    }

    private static void clearTasks(TaskList taskList, Storage storage, StringBuilder sb) {
        taskList.clearList();
        storage.writeToFile(taskList.getTaskList());
        sb.append(Ui.printAllCleared());
    }

    private static void findTask(TaskList taskList, StringBuilder sb, String[] token) {
        if (token.length != 2) {
            sb.append(Ui.findError());
        } else {
            String word = token[1];
            sb.append(taskList.find(word));
        }
    }

    private static void markTask(TaskList taskList, Storage storage, StringBuilder sb, String[] token) {
        boolean isNumber = true;
        if (token.length != 2) {
            sb.append(Ui.markError());
        }
        for (char c : token[1].toCharArray()) {
            if (!Character.isDigit(c)) {
                isNumber = false;
            }
        }
        if (!isNumber) {
            sb.append(Ui.markError());
        }
        int curr = Integer.parseInt(token[1]) - 1;
        Task task = taskList.get(curr);
        task.mark();
        storage.writeToFile(taskList.getTaskList());
        sb.append("Awesome..., I've marked this task as completed...").append("\n");
        sb.append(task.toString()).append("\n\n");
    }

    private static void unmarkTask(TaskList taskList, Storage storage, StringBuilder sb, String[] token) {
        boolean isNumber = true;
        if (token.length != 2) {
            sb.append(Ui.unmarkError());
        }
        for (char c : token[1].toCharArray()) {
            if (!Character.isDigit(c)) {
                isNumber = false;
            }
        }
        if (!isNumber) {
            sb.append(Ui.unmarkError());
        }
        int curr = Integer.parseInt(token[1]) - 1;
        Task task = taskList.get(curr);
        task.unmark();
        storage.writeToFile(taskList.getTaskList());
        sb.append("Oops... Guess it's not done yet...").append("\n");
        sb.append(task).append("\n\n");
    }

    private static void listTasks(Storage storage, StringBuilder sb) {
        sb.append("Here are the items in your To-Do List...\n");
        sb.append(storage.printFromFile()).append("\n\n");
    }

    private static void deleteTask(TaskList taskList, Storage storage, StringBuilder sb, String[] token) {
        boolean isNumber = true;
        if (token.length != 2) {
            sb.append(Ui.deleteError());
        }
        for (char c : token[1].toCharArray()) {
            if (!Character.isDigit(c)) {
                isNumber = false;
            }
        }
        if (!isNumber) {
            sb.append(Ui.deleteError());
        }
        int curr = Integer.parseInt(token[1]) - 1;
        Task task = taskList.get(curr);
        taskList.remove(curr);
        storage.writeToFile(taskList.getTaskList());
        sb.append("Boo... planning to slack off?").append("\n");
        sb.append("Removed: ").append(task.toString()).append("...").append("\n\n");
    }

    private static void addToDo(TaskList taskList, Storage storage, StringBuilder sb, String[] token) {
        if (token[0].equals("todo")) {
            if (token.length != 2) {
                sb.append(Ui.todoError());
            } else {
                Task task = new ToDo(token[1]);
                taskList.add(task);
                storage.writeToFile(taskList.getTaskList());
                sb.append("added: ").append(task.toString()).append("...").append("\n\n");
            }
        }
    }

    private static void addDeadline(TaskList taskList, Storage storage, StringBuilder sb, String[] token) {
        if (token.length != 2) {
            sb.append(Ui.deadlineError());
        }
        String[] details = token[1].split(" /by ");
        if (details.length < 2) {
            sb.append(Ui.deadlineError());
        } else {
            Task task = new Deadline(details[0], details[1]);
            taskList.add(task);
            storage.writeToFile(taskList.getTaskList());
            sb.append("added: ").append(task.toString()).append("...").append("\n\n");
        }
    }

    private static void addEvent(TaskList taskList, Storage storage, StringBuilder sb, String[] token) {
        if (token.length != 2) {
            sb.append(Ui.eventError());
        }
        String[] details = token[1].split(" /from |\\ /to ");
        if (details.length != 3) {
            sb.append(Ui.eventError());
        } else {
            Task task = new Event(details[0], details[1], details[2]);
            taskList.add(task);
            storage.writeToFile(taskList.getTaskList());
            sb.append("added: ").append(task).append("...");
        }
    }
}