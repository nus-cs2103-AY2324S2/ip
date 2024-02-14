package lite;

import lite.task.Deadline;
import lite.task.Event;
import lite.task.Todo;
import lite.task.Task;
import lite.task.TaskList;

import lite.util.Printer;
import lite.util.LiteException;

import java.lang.reflect.Array;

public class Ui {
    private String input;

    public Ui (String input) {
        this.input = input;
    }

    /**
     * Parse the input
     * @param tasks List of tasks
     * @return True if it is a bye (terminating condition)
     */
    public boolean executeCommand(TaskList tasks) {
        if (this.input.equals("bye")) {
            return true;
        }
        if (this.input.equals("list")) {
            tasks.outputTasks();
            return false;
        }

        String instruction[] = this.input.split(" ", 2); // It only splits the first " "
        if (instruction[0].equals("mark")) {
            this.markTask(instruction, tasks);
        } else if (instruction[0].equals("unmark")){
            this.unmarkTask(instruction, tasks);
        } else if (instruction[0].equals("delete")) {
            this.deleteTask(instruction, tasks);
        } else if (instruction[0].equals("todo")) {
            this.addToDoTask(instruction, tasks);
        } else if (instruction[0].equals("deadline")) {
            this.addDeadlineTask(instruction, tasks);
        } else if (instruction[0].equals("event")) {
            this.addEventTask(instruction, tasks);
        } else if (instruction[0].equals("find"))  {
            this.findTask(instruction, tasks);
        } else {
            LiteException.invalidInput();
        }
        return false;
    }

    private void findTask(String instruction[], TaskList tasks) {
        String description = instruction[1];
        TaskList availableTasks = new TaskList();
        boolean isContains = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.contains(i, description)) {
                availableTasks.add(tasks.get(i));
                isContains = true;
            }
        }
        Printer.printHorizontalLine();
        if (isContains) {
            Printer.printFound(availableTasks);
        } else {
            Printer.printNotFound();
        }
        Printer.printHorizontalLine();
    }


    /**
     * Deletes a task from the taskList
     * @param instruction Parsed input
     * @param tasks List of tasks
     */
    private void deleteTask(String instruction[], TaskList tasks) {
        try {
            int index = Integer.parseInt(instruction[1]) - 1;
            Printer.printHorizontalLine();
            Printer.printRemoveTask(tasks, index);
            tasks.remove(index);
            Printer.printHorizontalLine();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e){
            Printer.printHorizontalLine();
            LiteException.deleteException(tasks);
            Printer.printHorizontalLine();
        }
    }

    /**
     * Creates a new ToDo Event
     * @param instruction Parsed input
     * @param tasks List of tasks
     */
    private void addToDoTask(String instruction[], TaskList tasks) {
        try {
            String description = instruction[1];
            Printer.printHorizontalLine();
            Task todo = new Todo(description);
            tasks.add(todo);
            Printer.printTask(tasks, todo);
            Printer.printHorizontalLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            Printer.printHorizontalLine();
            LiteException.toDoException();
            Printer.printHorizontalLine();
        }
    }

    /**
     * Creates a new Deadline Task
     * @param instruction Parsed input
     * @param tasks List of tasks
     */
    private void addDeadlineTask(String instruction[], TaskList tasks) {
        try {
            String splits[] = instruction[1].split("/");
            String description = splits[0];
            String due = splits[1];
            Printer.printHorizontalLine();
            Task deadline = new Deadline(description, due);
            tasks.add(deadline);
            Printer.printTask(tasks, deadline);
            Printer.printHorizontalLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            Printer.printHorizontalLine();
            LiteException.deadlineException();
            Printer.printHorizontalLine();
        }
    }

    /**
     * Creates a new Event Task
     * @param instruction Parsed input
     * @param tasks List of tasks
     */
    private void addEventTask(String[] instruction, TaskList tasks) {
        try {
            String splits[] = instruction[1].split("/");
            String description = splits[0];
            String start = splits[1];
            String end = splits[2];
            Printer.printHorizontalLine();
            Task event = new Event(description, start, end);
            tasks.add(event);
            Printer.printTask(tasks, event);
            Printer.printHorizontalLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            Printer.printHorizontalLine();
            LiteException.eventException();
            Printer.printHorizontalLine();
        }
    }

    /**
     * Unmarks a task to be undone
     * @param instruction Parsed input
     * @param tasks List of tasks
     */
    private void unmarkTask(String instruction[], TaskList tasks) {
        try {
            int index = Integer.parseInt(instruction[1]) - 1;
            Printer.printHorizontalLine();
            Printer.printUnmark(tasks.get(index));
            Printer.printHorizontalLine();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            Printer.printHorizontalLine();
            LiteException.unmarkException(tasks);
            Printer.printHorizontalLine();
        }
    }

    /**
     * Marks a task to be done
     * @param instruction Parsed input
     * @param tasks List of tasks
     */
    private void markTask(String instruction[], TaskList tasks) {
        try {
            int index = Integer.parseInt(instruction[1]) - 1;
            Printer.printHorizontalLine();
            Printer.printMark(tasks.get(index));
            Printer.printHorizontalLine();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            Printer.printHorizontalLine();
            LiteException.markException(tasks);
            Printer.printHorizontalLine();
        }
    }
}
