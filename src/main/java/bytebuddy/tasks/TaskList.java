package bytebuddy.tasks;

import bytebuddy.ui.Ui;
import bytebuddy.exceptions.ByteBuddyException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static bytebuddy.constants.ExceptionErrorMessages.*;
import static bytebuddy.constants.FilePaths.RELATIVE_OUTPUT_TXT_FILE_PATH;
import static bytebuddy.constants.Formats.DEADLINE_FORMAT;
import static bytebuddy.constants.Formats.EVENT_FORMAT;
import static bytebuddy.constants.Information.solidLineBreak;
import static bytebuddy.storage.Storage.writeToFile;
import static bytebuddy.ui.Ui.printWithSolidLineBreak;

/**
 * The TaskList class represents a collection of tasks and provides methods to manipulate and interact with the task list.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    // private static HashMap<String, Task> taskHashMap;

    /**
     * Constructs a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns the size of the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Clears all tasks from the task list.
     */
    public void clear() {
        taskList.clear();
    }

    /**
     * Checks if the task list contains a specific task.
     *
     * @param t The task to check for.
     * @return true if the task list contains the specified task, false otherwise.
     */
    public boolean contains(Task t) {
        return taskList.contains(t);
    }

    /**
     * Gets a task at a specified index in the task list.
     *
     * @param i The index of the task.
     * @return The task at the specified index.
     */
    public Task get(int i) {
        return taskList.get(i);
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task to add.
     * @return true if the task was added successfully, false otherwise.
     */
    public boolean add(Task t) {
        return taskList.add(t);
    }

    /**
     * Removes a task at a specified index from the task list.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     */
    public Task remove(int index) {
        return taskList.remove(index);
    }

    /**
     * Splits a string into a list of trimmed substrings using a specified separator and maximum number of tokens.
     *
     * @param info       The input string to split.
     * @param separator  The separator to use.
     * @param maxTokens  The maximum number of tokens to split the string into.
     * @return A list of trimmed substrings.
     */
    public List<String> splitStringWithTrim(String info, String separator, int maxTokens) {
        return Arrays.stream(info.split(separator, maxTokens)).map(String::trim).collect(Collectors.toList());
    }

    /**
     * Marks a task as done in the task list based on user input.
     *
     * @param info The user input containing task information.
     * @throws ByteBuddyException If an error occurs during the marking process.
     */
    public void mark(String info) throws ByteBuddyException {
        try {
            int markIndex = Integer.parseInt(info.trim()) - 1;
            if (markIndex < 0 || markIndex >= taskList.size()) {
                throw new ByteBuddyException(NO_SUCH_TASK_NUMBER_ERROR_MESSAGE);
            }
            String markToPrint = taskList.get(markIndex).markAsDone();
            printWithSolidLineBreak(markToPrint);
            writeToFile(RELATIVE_OUTPUT_TXT_FILE_PATH, getTaskListFormattedStringOutput(taskList));
        } catch (NumberFormatException e) {
            throw new ByteBuddyException(NUMBER_FORMAT_ERROR_MESSAGE);
        } catch (IOException e) {
            throw new ByteBuddyException(FAILED_WRITE_TO_FILE_ERROR_MESSAGE);
        }

    }

    /**
     * Marks a task as not done in the task list based on user input.
     *
     * @param info The user input containing task information.
     * @throws ByteBuddyException If an error occurs during the unmarking process.
     */
    public void unmark(String info) throws ByteBuddyException {
        try {
            int unmarkIndex = Integer.parseInt(info.trim()) - 1;
            if (unmarkIndex < 0 || unmarkIndex >= taskList.size()) {
                throw new ByteBuddyException(NO_SUCH_TASK_NUMBER_ERROR_MESSAGE);
            }
            String unmarkToPrint = taskList.get(unmarkIndex).unmarkAsDone();
            printWithSolidLineBreak(unmarkToPrint);
            writeToFile(RELATIVE_OUTPUT_TXT_FILE_PATH, getTaskListFormattedStringOutput(taskList));
        } catch (NumberFormatException e) {
            throw new ByteBuddyException(NUMBER_FORMAT_ERROR_MESSAGE);
        } catch (IOException e) {
            throw new ByteBuddyException(FAILED_WRITE_TO_FILE_ERROR_MESSAGE);
        }
    }

    /**
     * Deletes a task from the task list based on user input.
     *
     * @param info The user input containing task information.
     * @throws ByteBuddyException If an error occurs during the deletion process.
     */
    public void delete(String info) throws ByteBuddyException {
        try {
            int deleteIndex = Integer.parseInt(info.trim()) - 1;
            if (deleteIndex < 0 || deleteIndex >= taskList.size()) {
                throw new ByteBuddyException(NO_SUCH_TASK_NUMBER_ERROR_MESSAGE);
            }
            Task removed = taskList.remove(deleteIndex);
            printTaskRemovedWithSolidLineBreak(removed);
            writeToFile(RELATIVE_OUTPUT_TXT_FILE_PATH, getTaskListFormattedStringOutput(taskList));
        } catch (NumberFormatException e) {
            throw new ByteBuddyException(NUMBER_FORMAT_ERROR_MESSAGE);
        } catch (IOException e) {
            throw new ByteBuddyException(FAILED_WRITE_TO_FILE_ERROR_MESSAGE);
        }
    }

    /**
     * Adds a new Todo task to the task list based on user input.
     *
     * @param info The user input containing task information.
     * @throws ByteBuddyException If an error occurs during the task creation process.
     */
    public void todo(String info) throws ByteBuddyException {
        try {
            if (info.isEmpty()) {
                throw new ByteBuddyException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
            }
            Task todo = new Todo(info);
            taskList.add(todo);
            printTaskAddedWithSolidLineBreak(todo);

            writeToFile(RELATIVE_OUTPUT_TXT_FILE_PATH, getTaskListFormattedStringOutput(taskList));
        } catch (IOException e) {
            throw new ByteBuddyException(FAILED_WRITE_TO_FILE_ERROR_MESSAGE);
        }
    }

    /**
     * Adds a new Deadline task to the task list based on user input.
     *
     * @param info The user input containing task information.
     * @throws ByteBuddyException If an error occurs during the task creation process.
     */
    public void deadline(String info) throws ByteBuddyException {
        try {
            if (info.isEmpty()) {
                throw new ByteBuddyException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
            }
            List<String> deadlineInfo = splitStringWithTrim(info, "/by", 2);
            Task deadline = new Deadline(deadlineInfo.get(0), deadlineInfo.get(1));
            taskList.add(deadline);
            printTaskAddedWithSolidLineBreak(deadline);

            writeToFile(RELATIVE_OUTPUT_TXT_FILE_PATH, getTaskListFormattedStringOutput(taskList));
        } catch (IndexOutOfBoundsException e) {
            throw new ByteBuddyException("The correct usage is: " + DEADLINE_FORMAT);
        } catch (IOException e) {
            throw new ByteBuddyException(FAILED_WRITE_TO_FILE_ERROR_MESSAGE);
        }
    }

    /**
     * Adds a new Event task to the task list based on user input.
     *
     * @param info The user input containing task information.
     * @throws ByteBuddyException If an error occurs during the task creation process.
     */
    public void event(String info) throws ByteBuddyException {
        try {
            if (info.isEmpty()) {
                throw new ByteBuddyException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
            }

            List<String> eventInfo = splitStringWithTrim(info, "/from|/to", 3);
            Task event = new Event(eventInfo.get(0), eventInfo.get(1), eventInfo.get(2));
            taskList.add(event);
            printTaskAddedWithSolidLineBreak(event);

            writeToFile(RELATIVE_OUTPUT_TXT_FILE_PATH, getTaskListFormattedStringOutput(taskList));
        } catch (IndexOutOfBoundsException e) {
            throw new ByteBuddyException("The correct usage is: " + EVENT_FORMAT);
        } catch (IOException e) {
            throw new ByteBuddyException(FAILED_WRITE_TO_FILE_ERROR_MESSAGE);
        }
    }

    /**
     * Prints the entire task list with a solid line break above and below the list.
     * Each task is numbered, and its details are displayed in the format "[task_number].[task_details]".
     */
    public void printTaskList() {
        System.out.println("\t" + solidLineBreak);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t " + (i+1) + "." + taskList.get(i));
        }
        System.out.println("\t" + solidLineBreak);
    }


    /**
     * Formats the task list into a string with each task's formatted output on a new line
     * for writing into output file.
     *
     * @param taskList The list of tasks to format.
     * @return A string containing the formatted output of each task in the list.
     */
    public String getTaskListFormattedStringOutput (ArrayList<Task> taskList) {
        StringBuilder s = new StringBuilder();
        for (Task task : taskList) {
            s.append(task.getTextFormattedOutput()).append("\n");
        }
        return s.toString();
    }

    /**
     * Prints a solid line break, followed by a confirmation message for the added task,
     * and the updated total number of tasks in the list.
     *
     * @param task The task that was added to the list.
     */
    public void printTaskAddedWithSolidLineBreak(Task task) {
        System.out.println("\t" + solidLineBreak);
        System.out.println("\t Got it. I've Added this task:");
        System.out.println("\t\t " + task);
        System.out.println("\t Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("\t" + solidLineBreak);
    }

    /**
     * Prints a solid line break, followed by a confirmation message for the removed task,
     * and the updated total number of tasks in the list.
     *
     * @param task The task that was removed from the list.
     */
    public void printTaskRemovedWithSolidLineBreak(Task task) {
        System.out.println("\t" + solidLineBreak);
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t\t " + task);
        System.out.println("\t Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("\t" + solidLineBreak);
    }

    /**
     * Finds tasks in the task list that match a specified keyword in their descriptions.
     * The method searches for tasks containing the specified text in their descriptions
     * and prints the matching tasks to the console.
     *
     * @param keyword The keyword or text to search for among all the tasks in the task list.
     * @throws ByteBuddyException If there is an issue with the search operation, such as an empty keyword.
     */
    public void findTaskWithKeywordInTaskList(String keyword) throws ByteBuddyException {
        if (keyword.isEmpty()) {
            throw new ByteBuddyException(EMPTY_KEYWORD_ERROR_MESSAGE);
        }

        keyword = keyword.toLowerCase();
        boolean foundTask = false;
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            String description = taskList.get(i).getDescription().toLowerCase();
            if (description.contains(keyword)) {
                if (!foundTask) {
                    str.append("Here are the matching tasks in your list:");
                    foundTask = true;
                }
                str.append("\n\t ").append(i + 1).append(".").append(taskList.get(i));
            }
        }

        if (!foundTask) {
            Ui.printWithSolidLineBreak("There are no matching tasks in your list :(");
        } else {
            Ui.printWithSolidLineBreak(str.toString());
        }
    }

}
