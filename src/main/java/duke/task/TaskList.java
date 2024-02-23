package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import duke.exceptions.DukeException;
import duke.exceptions.IllegalDateFormatException;
import duke.exceptions.SemanticException;
import duke.exceptions.SyntaxException;



/**
 * Manages all the operations of tasks on the list.
 */
public class TaskList {
    private List<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    public TaskList(List<Task> t) {
        this.listOfTasks = t;
    }

    public void addTask(Task t) {
        this.listOfTasks.add(t);
    }

    public Task removeIndex(int index) {
        return this.listOfTasks.remove(index);
    }

    public void markTask(int index) {
        this.listOfTasks.get(index).markAsDone();
    }

    public void unmarkTask(int index) {
        this.listOfTasks.get(index).unmarkAsDone();
    }

    public int size() {
        return this.listOfTasks.size();
    }

    public Task get(int index) {

        assert index >= 0 : "index should be positive or zero";
        assert index < this.listOfTasks.size() : "index should be within size of tasklist";
        return this.listOfTasks.get(index);
    }

    public Deadline setDeadline(String str) throws IllegalDateFormatException, SyntaxException {
        String[] arr = str.split("/by ");
        try {
            if (arr.length != 2) {
                throw new SyntaxException("Please check the command syntax");
            }
            return new Deadline(arr[0], parseDateTime(arr[1]));
        } catch (DateTimeParseException e) {
            throw new IllegalDateFormatException("Wrong Format for the date kindly put in \nyyyy-MM-dd HHmm.", str);
        }
    }

    public Event setEvent(String str) throws IllegalDateFormatException, SyntaxException {
        try {
            String[] event = str.split("/from | /to ");
            if (event.length != 3) {
                throw new SyntaxException("Please check the command syntax");
            }
            return new Event(event[0], LocalDateTime.parse(event[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                    LocalDateTime.parse(event[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
        } catch (DateTimeParseException e) {
            throw new IllegalDateFormatException("Wrong Format for the date kindly put in \nyyyy-MM-dd HHmm.", str);
        }
    }

    private LocalDateTime parseDateTime(String dateTime) throws IllegalDateFormatException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new IllegalDateFormatException("Incorrect format", dateTime);
        }
    }


    /**
     * Deletes a task from the task list based on the specified index.
     *
     * @param str       The input string containing the index of the task to be deleted.
     * @param taskList  The task list from which the task will be removed.
     * @return          The task that was removed from the list.
     * @throws SyntaxException   If the input string does not contain exactly one index number after the delete command.
     * @throws SemanticException  If the specified index is out of bounds.
     */
    public Task deleteTask(String str, TaskList taskList) throws SyntaxException, SemanticException {
        try {

            String[] string = str.split(" ");
            if (string.length != 2) {
                throw new SyntaxException("Need only index number after delete");
            }
            int index = Integer.parseInt(string[1]) - 1;
            return taskList.removeIndex(index);
        } catch (NumberFormatException e) {
            throw new SyntaxException("Need only index number after delete");
        } catch (IndexOutOfBoundsException e) {
            throw new SemanticException("Index is out of bounds, please write correct index number");
        }
    }

    private int getIndexOfMark(String str) {

        assert str.length() >= 6 : "Index should be there";
        int index = Integer.parseInt(str.substring(5));
        return index;
    }

    public Task setDone(String str, TaskList taskList) throws SyntaxException, SemanticException {
        try {
            int index = getIndexOfMark(str);
            taskList.markTask(index);
            return taskList.get(index);
        } catch (NumberFormatException e) {
            throw new SyntaxException("Need only index number to mark as done");
        } catch (IndexOutOfBoundsException e) {
            throw new SemanticException("Index is out of bounds, please write correct index number");
        }
    }

    public Task setUndone(String str, TaskList taskList) throws SyntaxException, SemanticException {
        try {
            int index = getIndexOfUnmark(str);
            taskList.unmarkTask(index);
            return taskList.get(index);
        } catch (NumberFormatException e) {
            throw new SyntaxException("Need only index number to mark as undone");
        } catch (IndexOutOfBoundsException e) {
            throw new SemanticException("Index is out of bounds, please write correct index number");
        }

    }

    private int getIndexOfUnmark(String str) {
        return Integer.parseInt(str.substring(7));
    }

    public Todo setToDo(String str) throws DukeException {
        String[] todo = str.strip().split("todo ?+");
        if (todo.length > 0 && !todo[0].isBlank()) {
            return new Todo(todo[0]);
        } else {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Finds tasks in the task list that contain the specified keyword.
     *
     * @param command The keyword to search for in the task descriptions.
     * @return An ArrayList of tasks that contain the specified keyword in their descriptions.
     */
    public ArrayList<Task> find(String command) {
        ArrayList<Task> searchedTasks = new ArrayList<>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            if (listOfTasks.get(i).getDescription().contains(command)) {
                searchedTasks.add(listOfTasks.get(i));
            }
        }
        return searchedTasks;
    }


    /**
     * Checks the schedule for tasks on a specific date.
     *
     * @param cmd The date to search for in the format "yyyy-MM-dd".
     * @return    An ArrayList of tasks scheduled on the specified date.
     * @throws IllegalDateFormatException If the provided date format is invalid.
     */
    public ArrayList<Task> checkSchedule(String cmd) {
        ArrayList<Task> searchTaskOnSpecificDate = new ArrayList<>();
        try {
            LocalDate target = LocalDate.parse(cmd, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            for (int i = 0; i < listOfTasks.size(); i++) {
                Task task = listOfTasks.get(i);
                if (task instanceof Event) {
                    LocalDate date = ((Event) task).getRawDate();
                    if (target.equals(date)) {
                        searchTaskOnSpecificDate.add(task);
                    }
                } else if (task instanceof Deadline) {
                    LocalDate date = ((Deadline) task).getRawDate();
                    if (target.equals(date)) {
                        searchTaskOnSpecificDate.add(task);
                    }
                }
            }
        } catch (DateTimeParseException e) {
            throw new IllegalDateFormatException("Invalid Date Format", cmd);
        }
        return searchTaskOnSpecificDate;
    }

}
