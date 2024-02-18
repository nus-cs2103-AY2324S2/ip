package lite;

import lite.task.Deadline;
import lite.task.Event;
import lite.task.Todo;
import lite.task.Task;
import lite.task.TaskList;

import lite.util.Parser;
import lite.util.Printer;
import lite.util.LiteException;

public class Ui {
    private String input;

    public Ui (String input) {
        this.input = input;
    }

    /**
     * Returns a command based on the input
     * @param tasks List of tasks
     * @return True if it is a bye (terminating condition)
     */
    public String executeCommand(TaskList tasks) {
        return Parser.parse(this.input, tasks, this);
    }

    public String findTask(String instruction[], TaskList tasks) {
        String description = instruction[1];
        TaskList availableTasks = new TaskList();
        boolean isContains = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.contains(i, description)) {
                availableTasks.add(tasks.get(i));
                isContains = true;
            }
        }

        if (isContains) {
            return Printer.printFound(availableTasks);
        } else {
            return Printer.printNotFound();
        }
    }


    /**
     * Deletes a task from the taskList
     * @param instruction Parsed input
     * @param tasks List of tasks
     */
    public String deleteTask(String instruction[], TaskList tasks) {
        try {
            int index = Integer.parseInt(instruction[1]) - 1;
            String output = Printer.printRemoveTask(tasks, index);
            int size = tasks.size();
            tasks.remove(index);
            assert tasks.size() == size - 1 : "size of array decreased by 1";
            return output;
        } catch (NullPointerException | IndexOutOfBoundsException e){
            return LiteException.deleteException(tasks);
        }
    }

    /**
     * Creates a new ToDo Event
     * @param instruction Parsed input
     * @param tasks List of tasks
     */
    public String addToDoTask(String instruction[], TaskList tasks) {
        try {
            String description = instruction[1];
            Task todo = new Todo(description);
            int size = tasks.size();
            boolean isDuplicate = tasks.add(todo);
            if (isDuplicate) {
                return Printer.printDuplicateFound();
            }
            assert tasks.size() == size + 1 : "size of array increased by 1";
            return Printer.printTask(tasks, todo);
        } catch (IndexOutOfBoundsException e) {
            return LiteException.toDoException();
        }
    }

    /**
     * Creates a new Deadline Task
     * @param instruction Parsed input
     * @param tasks List of tasks
     */
    public String addDeadlineTask(String instruction[], TaskList tasks) {
        try {
            String splits[] = instruction[1].split("/");
            String description = splits[0];
            String due = splits[1];
            Task deadline = new Deadline(description, due);
            int size = tasks.size();
            boolean isDuplicate = tasks.add(deadline);
            if (isDuplicate) {
                return Printer.printDuplicateFound();
            }
            assert tasks.size() == size + 1 : "size of array increased by 1";
            return Printer.printTask(tasks, deadline);
        } catch (IndexOutOfBoundsException e) {
            return LiteException.deadlineException();
        }
    }

    /**
     * Creates a new Event Task
     * @param instruction Parsed input
     * @param tasks List of tasks
     */
    public String addEventTask(String[] instruction, TaskList tasks) {
        try {
            String splits[] = instruction[1].split("/");
            String description = splits[0];
            String start = splits[1];
            String end = splits[2];
            Task event = new Event(description, start, end);
            int size = tasks.size();
            boolean isDuplicate = tasks.add(event);
            if (isDuplicate) {
                return Printer.printDuplicateFound();
            }
            assert tasks.size() == size + 1 : "size of array increased by 1";
            return Printer.printTask(tasks, event);
        } catch (IndexOutOfBoundsException e) {
            return LiteException.eventException();
        }
    }

    /**
     * Unmarks a task to be undone
     * @param instruction Parsed input
     * @param tasks List of tasks
     */
    public String unmarkTask(String instruction[], TaskList tasks) {
        try {
            int index = Integer.parseInt(instruction[1]) - 1;
            return Printer.printUnmark(tasks.get(index));
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            return LiteException.unmarkException(tasks);
        }
    }

    /**
     * Marks a task to be done
     * @param instruction Parsed input
     * @param tasks List of tasks
     */
    public String markTask(String instruction[], TaskList tasks) {
        try {
            int index = Integer.parseInt(instruction[1]) - 1;
            return Printer.printMark(tasks.get(index));
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            return LiteException.markException(tasks);
        }
    }
}
