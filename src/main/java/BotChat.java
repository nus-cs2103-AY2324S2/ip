import exception.IncompleteCommandException;
import exception.InvalidCommandException;
import exception.InvalidTaskNumberException;
import parser.Parser;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BotChat {
    private static boolean isTerminate = false;
    private static final String FILEPATH = "./././data/botchat.txt";
    private static Storage storage;
    private static TaskList taskArrayList;
    private static Parser parser;
    private static Pattern markPattern = Pattern.compile("mark \\d+");
    private static Pattern unmarkPattern = Pattern.compile("unmark \\d+");

    /**
     * Adds a new task based on the user input command.
     *
     * @param s The user input command containing the task details.
     * @throws Exception If the command is invalid or incomplete.
     */
    public static void addTask(String s) throws Exception {
        String taskDescription;
        switch (parser.extractCommand(s)) {
            case "todo":
                taskDescription = parser.extractDescription(s);
                Task newDescriptionTask = new ToDo(taskDescription);
                storage.addToDataStore(newDescriptionTask);
                taskArrayList.addTask(newDescriptionTask);
                break;
            case "deadline":
                taskDescription = parser.extractDescription(s);
                String[] deadlineStringParts = taskDescription.split("/by ");
                if (deadlineStringParts.length < 2) {
                    throw new IncompleteCommandException("task.Deadline command incomplete. It should be in the " +
                            "form of deadline description /by datetime.");
                } else {
                    Task newDeadlineTask = new Deadline(deadlineStringParts[0], deadlineStringParts[1]);
                    storage.addToDataStore(newDeadlineTask);
                    taskArrayList.addTask(newDeadlineTask);
                }
                break;
            case "event":
                taskDescription = parser.extractDescription(s);
                String[] eventStringParts = taskDescription.split("/from |/to ");
                if (eventStringParts.length < 3) {
                    throw new IncompleteCommandException("task.Event command incomplete. It should be in the " +
                            "form of event description /from datetime /to datetime.");
                } else {
                    Task newEventTask = new Event(eventStringParts[0], eventStringParts[1], eventStringParts[2]);
                    storage.addToDataStore(newEventTask);
                    taskArrayList.addTask(newEventTask);
                }
                break;
            default:
                throw new InvalidCommandException(s);
        }
    }

    /**
     * Generates a response based on the user input command.
     *
     * @param s The user input command.
     * @return The response message.
     */
    public static String response(String s) {
        Matcher markMatcher = markPattern.matcher(s);
        Matcher unmarkMatcher = unmarkPattern.matcher(s);
        String command = parser.extractCommand(s);
        if (command.equals("bye")) {
            isTerminate = true;
            return Ui.byeMessage();
        } else if (command.equals("list")) {
            StringBuilder stringBuilder = new StringBuilder("Here are the tasks in your list: \n");
            for (int i = 1; i <= taskArrayList.getLastIdx(); i++) {
                stringBuilder.append(i);
                stringBuilder.append(". ");
                stringBuilder.append(taskArrayList.getTaskByIdx(i - 1).toString());
                stringBuilder.append("\n ");
            }
            return stringBuilder.toString();
        } else if (markMatcher.matches()) {
            try {
                int taskNum = convertTaskNumStringToInt(s);
                storage.editDataStoreTaskAsDone(taskNum);
                return markTaskAsDone(taskNum);
            } catch (InvalidTaskNumberException e) {
                return e.toString();
            }
        } else if (unmarkMatcher.matches()) {
            try {
                int taskNum = convertTaskNumStringToInt(s);
                storage.editDataStoreTaskAsUndone(taskNum);
                return unmarkTaskAsDone(taskNum);
            } catch (InvalidTaskNumberException e) {
                return e.toString();
            }
        } else if (command.equals("delete")) {
            String requestedDeletion = s.substring(7);
            try {
                return deleteTask(requestedDeletion);
            } catch (InvalidTaskNumberException e) {
                return e.toString();
            }
        } else {
            try {
                addTask(s);
                return Ui.addTaskMessage(taskArrayList.getTaskByIdx(taskArrayList.getLastIdx() - 1).toString(),
                        taskArrayList.getLastIdx());
            } catch (Exception e) {
                return e.toString();
            }
        }
    }

    /**
     * Marks the specified task as done.
     *
     * @param taskNum The number of the task to mark as done.
     * @return The message indicating the task was marked as done.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public static String markTaskAsDone(int taskNum) throws InvalidTaskNumberException {
        taskArrayList.getTaskByIdx(taskNum - 1).markAsDone();
        return Ui.markTaskAsDoneMessage(taskArrayList.getTaskByIdx(taskNum - 1).toString());
    }

    /**
     * Converts the task number string to an integer.
     *
     * @param s The task number string.
     * @return The integer representation of the task number.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    private static int convertTaskNumStringToInt(String s) throws InvalidTaskNumberException {
        String taskNumString = s.split("\\s+")[1];
        int taskNum = Integer.parseInt(taskNumString);
        if (taskNum > taskArrayList.getLastIdx()) {
            throw new InvalidTaskNumberException(taskNumString);
        }
        return taskNum;
    }

    /**
     * Marks the specified task as undone.
     *
     * @param taskNum The number of the task to mark as undone.
     * @return The message indicating the task was marked as undone.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public static String unmarkTaskAsDone(int taskNum) throws InvalidTaskNumberException {
        taskArrayList.getTaskByIdx(taskNum - 1).markAsUndone();
        return Ui.markTaskAsUndoneMessage(taskArrayList.getTaskByIdx(taskNum - 1).toString());
    }

    /**
     * Deletes the specified task.
     *
     * @param requestedDeletion The number of the task to delete.
     * @return The message indicating the task was deleted.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public static String deleteTask(String requestedDeletion) throws InvalidTaskNumberException {
        try {
            int taskNum = Integer.parseInt(requestedDeletion);
            if (taskNum > taskArrayList.getLastIdx()) {
                throw new InvalidTaskNumberException(requestedDeletion);
            }
            String deletedTaskString = taskArrayList.getTaskByIdx(taskNum - 1).toString();
            storage.removeFromDataStore(taskNum - 1);
            taskArrayList.removeTask(taskNum - 1);
            storage = new Storage(FILEPATH);
            return Ui.taskRemovalMessage(deletedTaskString, taskArrayList.getLastIdx());
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException(requestedDeletion);
        }
    }


    public static void main(String[] args) {
        parser = new Parser();
        storage = new Storage(FILEPATH);
        taskArrayList = new TaskList(storage.readDataStore());
        System.out.println(Ui.hiMessage());

        Scanner userInput = new Scanner(System.in);

        while (!isTerminate) {
            String command = userInput.nextLine();
            System.out.println(response(command));
        }
    }
}
