package tasks;

import exceptions.DukeException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static constants.ExceptionErrorMessages.*;
import static constants.FilePaths.RELATIVE_OUTPUT_TXT_FILE_PATH;
import static constants.Formats.DEADLINE_FORMAT;
import static constants.Formats.EVENT_FORMAT;
import static constants.Information.solidLineBreak;
import static storage.Storage.writeToFile;
import static ui.Ui.printWithSolidLineBreak;

public class TaskList {
    private ArrayList<Task> taskList;
    // private static HashMap<String, Task> taskHashMap;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public int size() {
        return taskList.size();
    }

    public void clear() {
        taskList.clear();
    }

    public boolean contains(Task t) {
        return taskList.contains(t);
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public boolean add(Task t) {
        return taskList.add(t);
    }

    public Task remove(int index) {
        return taskList.remove(index);
    }

    public List<String> splitStringWithTrim(String info, String separator, int maxTokens) {
        return Arrays.stream(info.split(separator, maxTokens)).map(String::trim).collect(Collectors.toList());
    }

    public void mark(String info) throws DukeException {
        try {
            int markIndex = Integer.parseInt(info.trim()) - 1;
            if (markIndex < 0 || markIndex >= taskList.size()) {
                throw new DukeException(NO_SUCH_TASK_NUMBER_ERROR_MESSAGE);
            }
            String markToPrint = taskList.get(markIndex).markAsDone();
            printWithSolidLineBreak(markToPrint);
            writeToFile(RELATIVE_OUTPUT_TXT_FILE_PATH, TaskListFormattedStringOutput(taskList));
        } catch (NumberFormatException e) {
            throw new DukeException(NUMBER_FORMAT_ERROR_MESSAGE);
        } catch (IOException e) {
            throw new DukeException(FAILED_WRITE_TO_FILE_ERROR_MESSAGE);
        }

    }

    public void unmark(String info) throws DukeException {
        try {
            int unmarkIndex = Integer.parseInt(info.trim()) - 1;
            if (unmarkIndex < 0 || unmarkIndex >= taskList.size()) {
                throw new DukeException(NO_SUCH_TASK_NUMBER_ERROR_MESSAGE);
            }
            String unmarkToPrint = taskList.get(unmarkIndex).unmarkAsDone();
            printWithSolidLineBreak(unmarkToPrint);
            writeToFile(RELATIVE_OUTPUT_TXT_FILE_PATH, TaskListFormattedStringOutput(taskList));
        } catch (NumberFormatException e) {
            throw new DukeException(NUMBER_FORMAT_ERROR_MESSAGE);
        } catch (IOException e) {
            throw new DukeException(FAILED_WRITE_TO_FILE_ERROR_MESSAGE);
        }
    }

    public void delete(String info) throws DukeException {
        try {
            int deleteIndex = Integer.parseInt(info.trim()) - 1;
            if (deleteIndex < 0 || deleteIndex >= taskList.size()) {
                throw new DukeException(NO_SUCH_TASK_NUMBER_ERROR_MESSAGE);
            }
            Task removed = taskList.remove(deleteIndex);
            printTaskRemovedWithSolidLineBreak(removed);
            writeToFile(RELATIVE_OUTPUT_TXT_FILE_PATH, TaskListFormattedStringOutput(taskList));
        } catch (NumberFormatException e) {
            throw new DukeException(NUMBER_FORMAT_ERROR_MESSAGE);
        } catch (IOException e) {
            throw new DukeException(FAILED_WRITE_TO_FILE_ERROR_MESSAGE);
        }
    }

    public void todo(String info) throws DukeException {
        try {
            if (info.isEmpty()) {
                throw new DukeException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
            }
            Task todo = new Todo(info);
            taskList.add(todo);
            printTaskAddedWithSolidLineBreak(todo);

            writeToFile(RELATIVE_OUTPUT_TXT_FILE_PATH, TaskListFormattedStringOutput(taskList));
        } catch (IOException e) {
            throw new DukeException(FAILED_WRITE_TO_FILE_ERROR_MESSAGE);
        }
    }

    public void deadline(String info) throws DukeException {
        try {
            if (info.isEmpty()) {
                throw new DukeException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
            }
            List<String> deadlineInfo = splitStringWithTrim(info, "/by", 2);
            Task deadline = new Deadline(deadlineInfo.get(0), deadlineInfo.get(1));
            taskList.add(deadline);
            printTaskAddedWithSolidLineBreak(deadline);

            writeToFile(RELATIVE_OUTPUT_TXT_FILE_PATH, TaskListFormattedStringOutput(taskList));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The correct usage is: " + DEADLINE_FORMAT);
        } catch (IOException e) {
            throw new DukeException(FAILED_WRITE_TO_FILE_ERROR_MESSAGE);
        }
    }

    public void event(String info) throws DukeException {
        try {
            if (info.isEmpty()) {
                throw new DukeException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
            }

            List<String> eventInfo = splitStringWithTrim(info, "/from|/to", 3);
            Task event = new Event(eventInfo.get(0), eventInfo.get(1), eventInfo.get(2));
            taskList.add(event);
            printTaskAddedWithSolidLineBreak(event);

            writeToFile(RELATIVE_OUTPUT_TXT_FILE_PATH, TaskListFormattedStringOutput(taskList));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The correct usage is: " + EVENT_FORMAT);
        } catch (IOException e) {
            throw new DukeException(FAILED_WRITE_TO_FILE_ERROR_MESSAGE);
        }
    }

    public void printTaskList() {
        System.out.println("\t" + solidLineBreak);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t " + (i+1) + "." + taskList.get(i));
        }
        System.out.println("\t" + solidLineBreak);
    }

    public String TaskListFormattedStringOutput (ArrayList<Task> taskList) {
        StringBuilder s = new StringBuilder();
        for (Task task : taskList) {
            s.append(task.textFormattedOutput()).append("\n");
        }
        return s.toString();
    }

    public void printTaskAddedWithSolidLineBreak(Task task) {
        System.out.println("\t" + solidLineBreak);
        System.out.println("\t Got it. I've Added this task:");
        System.out.println("\t\t " + task);
        System.out.println("\t Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("\t" + solidLineBreak);
    }

    public void printTaskRemovedWithSolidLineBreak(Task task) {
        System.out.println("\t" + solidLineBreak);
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t\t " + task);
        System.out.println("\t Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("\t" + solidLineBreak);
    }

}
