package riz.io;

import riz.data.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        String[] token = input.trim().split(" ", 2);
        String cmd = token[0];
        try {
            switch (cmd) {
                case "help":
                    String helpMessage = getHelp();
                    sb.append(helpMessage);
                    return sb.toString();
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
                case "bye":
                    String exitMessage = exitProgram();
                    sb.append(exitMessage);
                    return sb.toString();
                default:
                    sb.append(Ui.yapError());
                    break;

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
        } catch (RizException e) {
            sb.append(e.getMessage());
        }
        return sb.toString();
    }

    private static void clearTasks(TaskList taskList, Storage storage, StringBuilder sb) {
        taskList.clearList();
        storage.writeToFile(taskList.getTaskList());
        sb.append(Ui.printAllCleared());
    }

    private static void findTask(TaskList taskList, StringBuilder sb, String[] token) throws RizException {
        if (token.length != 2) {
            throw new RizException(Ui.findError());
        } else {
            String word = token[1];
            sb.append(taskList.find(word));
        }
    }

    private static void markTask(TaskList taskList, Storage storage, StringBuilder sb, String[] token) throws RizException {
        if (!isValid(token, taskList)) {
            throw new RizException(Ui.markError());
        }
        int curr = Integer.parseInt(token[1]) - 1;
        Task task = taskList.get(curr);
        task.mark();
        storage.writeToFile(taskList.getTaskList());
        sb.append("Awesome..., I've marked this task as completed...").append("\n");
        sb.append(task.toString()).append("\n\n");
    }

    private static void unmarkTask(TaskList taskList, Storage storage, StringBuilder sb, String[] token) throws RizException {
        if (!isValid(token, taskList)) {
            throw new RizException(Ui.unmarkError());
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

    private static void deleteTask(TaskList taskList, Storage storage, StringBuilder sb, String[] token) throws RizException {
        if (!isValid(token, taskList)) {
            throw new RizException(Ui.deleteError());
        }
        int curr = Integer.parseInt(token[1]) - 1;
        Task task = taskList.get(curr);
        taskList.remove(curr);
        storage.writeToFile(taskList.getTaskList());
        sb.append("Boo... planning to slack off?").append("\n");
        sb.append("Removed: ").append(task.toString()).append("...").append("\n\n");
    }

    private static void addToDo(TaskList taskList, Storage storage, StringBuilder sb, String[] token) throws RizException {
         if (!isValidTask(token)) {
            throw new RizException(Ui.todoError());
        }
        Task task = new ToDo(token[1]);
        taskList.add(task);
        storage.writeToFile(taskList.getTaskList());
        sb.append("added: ").append(task.toString()).append("...").append("\n\n");
    }

    private static void addDeadline(TaskList taskList, Storage storage, StringBuilder sb, String[] token) throws RizException {
        if (!isValidTask(token)) {
            throw new RizException(Ui.deadlineError());
        }
        else {
            String[] details = token[1].split(" /by ");
            Task task = new Deadline(details[0], details[1]);
            taskList.add(task);
            storage.writeToFile(taskList.getTaskList());
            sb.append("added: ").append(task.toString()).append("...").append("\n\n");
        }
    }

    private static void addEvent(TaskList taskList, Storage storage, StringBuilder sb, String[] token) throws RizException {
        if (!isValidTask(token)) {
            throw new RizException(Ui.eventError());
        } else {
            String[] details = token[1].split(" /from | /to ");
            Task task = new Event(details[0], details[1], details[2]);
            taskList.add(task);
            storage.writeToFile(taskList.getTaskList());
            sb.append("added: ").append(task).append("...");
        }
    }

    private static String getHelp() {
        String helpMessage = "Hello... here are some tips on how to use the bot...\n"
                + "You may find it useful to use the buttons provided...\n\n"
                + "Here is how you should format your tasks to be added...\n"
                + "Make sure the time is in 24h format...\n\n"
                + "Todo (No DeadLine): todo <INSERT TASK HERE>\n\n"
                + "Deadline: deadline <INSET TASK HERE> /by dd/mm/yyyy hhmm\n\n"
                + "Event: event <INSERT TASK HERE> /from dd/mm/yyyy hhmm /to dd/mm/yyyy hhmm\n\n\n"
                + "And here are what the other buttons do...\n\n"
                + "List: Lists out all the tasks present in the tasklist\n"
                + "Clear List: Clears the entire tasklist of all tasks\n\n"
                + "Delete: Deletes the task specified by its numerical index in the list as completed\n"
                + "Format: Delete <INSERT TASK INDEX HERE>\n\n"
                + "Mark: Marks the task specified by its numerical index in the list as completed\n"
                + "Format: mark <INSERT TASK INDEX HERE>\n\n"
                + "Unmark: Marks the task specified by its numerical index in the list as uncompleted\n"
                + "Format: unmark <INSERT TASK INDEX HERE>\n\n"
                + "Find: Returns all task based on what you searched for, even if it only matches partially\n"
                + "Format: Find <INSERT TASK NAME HERE>\n\n";
        return helpMessage;
    }

    private static String exitProgram() {
        return "Thanks for using me, see you soon...";
    }

    private static boolean isValid(String[] token, TaskList taskList) {
        boolean isValid = true;
        if (token.length != 2) {
            isValid = false;
        }
        for (char c : token[1].toCharArray()) {
            if (!Character.isDigit(c)) {
                isValid = false;
            }
        }
        int curr = Integer.parseInt(token[1]);
        isValid = isValid && taskList.size() >= curr;
        return isValid;
    }

    private static boolean isValidTask(String[] token) {
        boolean isValid = true;
        String format = "dd/MM/yyyy HHmm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        String taskType = token[0];
        if (taskType.equals("todo")) {
            if (token.length != 2) {
                isValid = false;
            }
        } else if (taskType.equals("deadline")) {
            if (token.length != 2) {
                isValid = false;
            }
            String[] details = token[1].split(" /by ");
            if (details.length != 2) {
                isValid = false;
            }
            try {
                formatter.parse(details[1]);
            } catch (DateTimeParseException e) {
                isValid = false;
            }
        } else if (taskType.equals("event")) {
            if (token.length != 2) {
                isValid = false;
            }
            String[] details = token[1].split(" /from | /to ");
            details[1] = details[1].trim();
            for(String each : details) {
                System.out.println(each);
            }
            if (details.length != 3) {
                isValid = false;
            }
            try {
                formatter.parse(details[1].trim());
                formatter.parse(details[2]);
            } catch (DateTimeParseException e) {
                isValid = false;
            }
        } else {
            isValid = false;
        }
        return isValid;
    }
}