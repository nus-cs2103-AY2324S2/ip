package bytebuddy.tasks;

import static bytebuddy.constants.ExceptionErrorMessages.DUPLICATE_TASK_ERROR_MESSAGE;
import static bytebuddy.constants.ExceptionErrorMessages.EMPTY_DESCRIPTION_ERROR_MESSAGE;
import static bytebuddy.constants.ExceptionErrorMessages.EMPTY_KEYWORD_ERROR_MESSAGE;
import static bytebuddy.constants.ExceptionErrorMessages.FAILED_WRITE_TO_FILE_ERROR_MESSAGE;
import static bytebuddy.constants.ExceptionErrorMessages.NO_SUCH_TASK_NUMBER_ERROR_MESSAGE;
import static bytebuddy.constants.ExceptionErrorMessages.NUMBER_FORMAT_ERROR_MESSAGE;
import static bytebuddy.constants.FilePaths.RELATIVE_OUTPUT_TXT_FILE_PATH;
import static bytebuddy.constants.Formats.DEADLINE_FORMAT;
import static bytebuddy.constants.Formats.EVENT_FORMAT;
import static bytebuddy.constants.Formats.FIND_FORMAT;
import static bytebuddy.parser.Parser.splitStringWithTrim;
import static bytebuddy.storage.Storage.writeToFile;
import static bytebuddy.ui.Ui.printWithSolidLineBreak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import bytebuddy.exceptions.ByteBuddyException;
import bytebuddy.ui.Ui;
import javafx.util.Pair;

/**
 * The TaskList class represents a collection of tasks and
 * provides methods to manipulate and interact with the task list.
 */
public class TaskList {
    private HashSet<Task> taskSet;
    private ArrayList<Task> taskList;

    /**
     * Constructs a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        taskList = new ArrayList<>();
        taskSet = new HashSet<>();
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
        taskSet.clear();
    }

    /**
     * Checks if the task list contains a specific task.
     *
     * @param t The task to check for.
     * @return true if the task list contains the specified task, false otherwise.
     */
    public boolean contains(Task t) {
        // return taskList.contains(t);
        return taskSet.contains(t);
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
    public boolean add(Task t) throws ByteBuddyException {
        if (this.contains(t)) {
            throw new ByteBuddyException(DUPLICATE_TASK_ERROR_MESSAGE);
        }

        taskSet.add(t);
        return taskList.add(t);
    }

    /**
     * Removes a task at a specified index from the task list.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     */
    public Task remove(int index) {
        taskSet.remove(this.get(index));
        return taskList.remove(index);
    }

    private boolean isValidIndex(int markIndex) {
        return markIndex < 0 || markIndex >= taskList.size();
    }

    /**
     * Marks a task as done in the task list based on user input.
     *
     * @param info The user input containing task information.
     * @throws ByteBuddyException If an error occurs during the marking process.
     */
    public String mark(String info) throws ByteBuddyException {
        try {
            int markIndex = Integer.parseInt(info.trim()) - 1;
            if (isValidIndex(markIndex)) {
                // throw new ByteBuddyException(NO_SUCH_TASK_NUMBER_ERROR_MESSAGE);
                return "\t " + NO_SUCH_TASK_NUMBER_ERROR_MESSAGE;
            }
            String markToPrint = taskList.get(markIndex).markAsDone();
            printWithSolidLineBreak("\t " + markToPrint);

            writeToFile(RELATIVE_OUTPUT_TXT_FILE_PATH, getTaskListFormattedStringOutput(taskList));

            return "\t " + markToPrint;
        } catch (NumberFormatException e) {
            // throw new ByteBuddyException(NUMBER_FORMAT_ERROR_MESSAGE);
            return "\t " + NUMBER_FORMAT_ERROR_MESSAGE;
        } catch (IOException e) {
            // throw new ByteBuddyException(FAILED_WRITE_TO_FILE_ERROR_MESSAGE);
            return "\t " + FAILED_WRITE_TO_FILE_ERROR_MESSAGE;
        }

    }

    /**
     * Marks a task as not done in the task list based on user input.
     *
     * @param info The user input containing task information.
     * @throws ByteBuddyException If an error occurs during the unmarking process.
     */
    public String unmark(String info) throws ByteBuddyException {
        try {
            int unmarkIndex = Integer.parseInt(info.trim()) - 1;
            if (isValidIndex(unmarkIndex)) {
                // throw new ByteBuddyException(NO_SUCH_TASK_NUMBER_ERROR_MESSAGE);
                return "\t " + NO_SUCH_TASK_NUMBER_ERROR_MESSAGE;
            }
            String unmarkToPrint = taskList.get(unmarkIndex).unmarkAsDone();
            printWithSolidLineBreak("\t " + unmarkToPrint);

            writeToFile(RELATIVE_OUTPUT_TXT_FILE_PATH, getTaskListFormattedStringOutput(taskList));

            return "\t " + unmarkToPrint;
        } catch (NumberFormatException e) {
            // throw new ByteBuddyException(NUMBER_FORMAT_ERROR_MESSAGE);
            return "\t " + NUMBER_FORMAT_ERROR_MESSAGE;
        } catch (IOException e) {
            // throw new ByteBuddyException(FAILED_WRITE_TO_FILE_ERROR_MESSAGE);
            return "\t " + FAILED_WRITE_TO_FILE_ERROR_MESSAGE;
        }
    }

    /**
     * Deletes a task from the task list based on user input.
     *
     * @param info The user input containing task information.
     * @throws ByteBuddyException If an error occurs during the deletion process.
     */
    public String delete(String info) throws ByteBuddyException {
        try {
            int deleteIndex = Integer.parseInt(info.trim()) - 1;
            if (isValidIndex(deleteIndex)) {
                // throw new ByteBuddyException(NO_SUCH_TASK_NUMBER_ERROR_MESSAGE);
                return "\t " + NO_SUCH_TASK_NUMBER_ERROR_MESSAGE;
            }

            taskSet.remove(this.get(deleteIndex));
            Task removed = taskList.remove(deleteIndex);
            printTaskRemovedWithSolidLineBreak(removed);

            writeToFile(RELATIVE_OUTPUT_TXT_FILE_PATH, getTaskListFormattedStringOutput(taskList));

            return returnTaskRemovedString(removed);
        } catch (NumberFormatException e) {
            // throw new ByteBuddyException(NUMBER_FORMAT_ERROR_MESSAGE);
            return "\t " + NUMBER_FORMAT_ERROR_MESSAGE;
        } catch (IOException e) {
            // throw new ByteBuddyException(FAILED_WRITE_TO_FILE_ERROR_MESSAGE);
            return "\t " + FAILED_WRITE_TO_FILE_ERROR_MESSAGE;
        }
    }

    /**
     * Adds a new Todo task to the task list based on user input.
     *
     * @param info The user input containing task information.
     * @throws ByteBuddyException If an error occurs during the task creation process.
     */
    public String todo(String info) throws ByteBuddyException {
        try {
            if (info.isEmpty()) {
                // throw new ByteBuddyException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
                return "\t " + EMPTY_DESCRIPTION_ERROR_MESSAGE;
            }
            Task todo = new Todo(info);
            this.add(todo);
            printTaskAddedWithSolidLineBreak(todo);

            writeToFile(RELATIVE_OUTPUT_TXT_FILE_PATH, getTaskListFormattedStringOutput(taskList));

            return returnTaskAddedString(todo);
        } catch (IOException e) {
            // throw new ByteBuddyException(FAILED_WRITE_TO_FILE_ERROR_MESSAGE);
            return "\t " + FAILED_WRITE_TO_FILE_ERROR_MESSAGE;
        } catch (ByteBuddyException e) {
            return "\t " + e.getMessage();
        }
    }

    /**
     * Adds a new Deadline task to the task list based on user input.
     *
     * @param info The user input containing task information.
     * @throws ByteBuddyException If an error occurs during the task creation process.
     */
    public String deadline(String info) throws ByteBuddyException {
        try {
            if (info.isEmpty()) {
                // throw new ByteBuddyException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
                return "\t " + EMPTY_DESCRIPTION_ERROR_MESSAGE;
            }
            List<String> deadlineInfo = splitStringWithTrim(info, "/by", 2);
            Task deadline = new Deadline(deadlineInfo.get(0), deadlineInfo.get(1));
            this.add(deadline);
            printTaskAddedWithSolidLineBreak(deadline);

            writeToFile(RELATIVE_OUTPUT_TXT_FILE_PATH, getTaskListFormattedStringOutput(taskList));

            return returnTaskAddedString(deadline);
        } catch (IndexOutOfBoundsException e) {
            // throw new ByteBuddyException("The correct usage is: " + DEADLINE_FORMAT);
            return "\t The correct usage is: " + DEADLINE_FORMAT;
        } catch (IOException e) {
            // throw new ByteBuddyException(FAILED_WRITE_TO_FILE_ERROR_MESSAGE);
            return "\t " + FAILED_WRITE_TO_FILE_ERROR_MESSAGE;
        } catch (ByteBuddyException e) {
            return "\t " + e.getMessage();
        }
    }

    /**
     * Returns String output to inform user that event has been added
     * Adds a new Event task to the task list based on user input.
     *
     * @param info The user input containing task information.
     * @throws ByteBuddyException If an error occurs during the task creation process.
     */
    public String event(String info) throws ByteBuddyException {
        try {
            if (info.isEmpty()) {
                // throw new ByteBuddyException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
                return "\t " + EMPTY_DESCRIPTION_ERROR_MESSAGE;
            }

            List<String> eventInfo = splitStringWithTrim(info, "/from|/to", 3);
            Task event = new Event(eventInfo.get(0), eventInfo.get(1), eventInfo.get(2));
            this.add(event);
            printTaskAddedWithSolidLineBreak(event);

            writeToFile(RELATIVE_OUTPUT_TXT_FILE_PATH, getTaskListFormattedStringOutput(taskList));

            return returnTaskAddedString(event);
        } catch (IndexOutOfBoundsException e) {
            // throw new ByteBuddyException("The correct usage is: " + EVENT_FORMAT);
            return "The correct usage is: " + EVENT_FORMAT;
        } catch (IOException e) {
            // throw new ByteBuddyException(FAILED_WRITE_TO_FILE_ERROR_MESSAGE);
            return FAILED_WRITE_TO_FILE_ERROR_MESSAGE;
        } catch (ByteBuddyException e) {
            return "\t " + e.getMessage();
        }
    }

    /**
     * Returns the entire task list.
     * Each task is numbered, and its details are displayed in the format "[task_number].[task_details]".
     */
    public String getFormattedTaskList() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            if (i > 0) {
                str.append("\n");
            }
            str.append("\t ").append(i + 1).append(".").append(taskList.get(i));
        }
        return str.toString();
    }

    /**
     * Prints the entire task list with a solid line break above and below the list.
     * Each task is numbered, and its details are displayed in the format "[task_number].[task_details]".
     */
    public void printTaskList() {
        Ui.printWithSolidLineBreak(getFormattedTaskList());
    }


    /**
     * Formats the task list into a string with each task's formatted output on a new line
     * for writing into output file.
     *
     * @param taskList The list of tasks to format.
     * @return A string containing the formatted output of each task in the list.
     */
    public String getTaskListFormattedStringOutput(ArrayList<Task> taskList) {
        StringBuilder s = new StringBuilder();
        for (Task task : taskList) {
            s.append(task.getTextFormattedOutput()).append("\n");
        }
        return s.toString();
    }

    /**
     * Returns a confirmation message for the added task,
     * and the updated total number of tasks in the list.
     *
     * @param task The task that was added to the list.
     */
    public String returnTaskAddedString(Task task) {
        StringBuilder str = new StringBuilder();

        str.append("\t Got it. I've Added this task:\n")
                .append("\t\t ").append(task).append("\n")
                .append("\t Now you have ").append(taskList.size()).append(" tasks in the list.");

        return str.toString();
    }

    /**
     * Prints a solid line break, followed by a confirmation message for the added task,
     * and the updated total number of tasks in the list.
     *
     * @param task The task that was added to the list.
     */
    public void printTaskAddedWithSolidLineBreak(Task task) {
        Ui.printWithSolidLineBreak(returnTaskAddedString(task));
    }

    /**
     * Returns a confirmation message for the removed task,
     * and the updated total number of tasks in the list.
     *
     * @param task The task that was removed from the list.
     */
    public String returnTaskRemovedString(Task task) {
        StringBuilder str = new StringBuilder();

        str.append("\t Noted. I've removed this task:\n")
                .append("\t\t ").append(task).append("\n")
                .append("\t Now you have ").append(taskList.size()).append(" tasks in the list.");

        return str.toString();
    }


    /**
     * Prints a solid line break, followed by a confirmation message for the removed task,
     * and the updated total number of tasks in the list.
     *
     * @param task The task that was removed from the list.
     */
    public void printTaskRemovedWithSolidLineBreak(Task task) {
        Ui.printWithSolidLineBreak(returnTaskRemovedString(task));
    }

    /**
     * Finds tasks in the task list that match a specified keyword in their descriptions.
     * The method searches for tasks containing the specified text in their descriptions
     * and prints the matching tasks to the console.
     * Returns the matching tasks
     *
     * @param keywords The keyword or text to search for among all the tasks in the task list.
     * @throws ByteBuddyException If there is an issue with the search operation, such as an empty keyword.
     */
    public String findTaskWithKeywordInTaskList(List<String> keywords) throws ByteBuddyException {
        if (keywords.isEmpty()) {
            throw new ByteBuddyException(EMPTY_KEYWORD_ERROR_MESSAGE + "\n\t Usage: " + FIND_FORMAT);
        }

        boolean foundTask = false;
        StringBuilder str = new StringBuilder();
        for (String keyword: keywords) {
            for (int i = 0; i < taskList.size(); i++) {
                String description = taskList.get(i).getDescription().toLowerCase();
                Pair<StringBuilder, Boolean> pair =
                        processMatchingTask(keyword.toLowerCase(), str, foundTask, description, i);
                str = pair.getKey();
                foundTask = pair.getValue();
            }
        }

        if (!foundTask) {
            Ui.printWithSolidLineBreak("\t There are no matching tasks in your list :(");
            return "There are no matching tasks in your list :(";
        } else {
            Ui.printWithSolidLineBreak("\t " + str.toString());
            return str.toString();
        }
    }


    private Pair<StringBuilder, Boolean> processMatchingTask(String keyword, StringBuilder str,
            boolean foundTask, String description, int i) {
        if (description.contains(keyword)) {
            if (!foundTask) {
                str.append("Here are the matching tasks in your list:");
                foundTask = true;
            }
            str.append("\n\t\t ").append(i + 1).append(".").append(taskList.get(i));
        }
        return new Pair<>(str, foundTask);
    }
}
