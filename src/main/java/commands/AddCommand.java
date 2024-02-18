package commands;

import exceptions.WeiException;
import storage.Storage;
import taskList.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;
import ui.Ui;

/**
 * Adds a tasks into the TaskList.
 */
public class AddCommand extends Command {
    private String input;

    /**
     * Creates an AddCommand object.
     *
     * @param input User Input.
     */
    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Adds the task into the TaskList and inform the user.
     *
     * @param tasks All the tasks of the user.
     * @param ui Gives reply to the user.
     * @throws WeiException If the command is incomplete.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WeiException {
        String taskAdded = "";
        if (input.startsWith("todo")) {
            taskAdded = addToDo(tasks, input);
        } else if (input.startsWith("deadline")) {
            taskAdded = addDeadline(tasks, input);
        } else if (input.startsWith("event")) {
            taskAdded = addEvent(tasks, input);
        } else {
            assert false : "not add task command";
        }
        int size = tasks.getSize();
        return ui.showAddMessage(taskAdded) + ui.showNumberOfRemainingTasks(size);
    }

    /**
     * {@inheritDoc}
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    private String addToDo(TaskList tasks, String input) throws WeiException {
        if (input.length() < 5) {
            throw new WeiException("please tell me what is your task about");
        }
        ToDo todo = new ToDo(input.substring(5));
        return tasks.add(todo);
    }

    private String addDeadline(TaskList tasks, String input) throws WeiException {
        try {
            int index = input.indexOf("/");
            String task = input.substring(9, index - 1);
            String date = input.substring(index + 4);
            Deadline deadline = new Deadline(task, date);
            return tasks.add(deadline);
        } catch (IndexOutOfBoundsException e) {
            throw new WeiException("please tell me when is the deadline");
        }
    }

    private String addEvent(TaskList tasks, String input) throws WeiException {
        try {
            int firstIndex = input.indexOf("/");
            int secondIndex = input.lastIndexOf("/");
            String start = input.substring(firstIndex + 7, secondIndex - 1);
            String end = input.substring(secondIndex + 5);
            String task = input.substring(6, firstIndex - 1);
            Event event = new Event(task, start, end);
            return tasks.add(event);
        } catch (IndexOutOfBoundsException e) {
            throw new WeiException("please tell me when is the event occurring");
        }
    }

}
