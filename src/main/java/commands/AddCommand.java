package commands;

import excceptions.WeiException;
import taskList.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;
import ui.Ui;

public class AddCommand extends Command {
    private String input;

    public AddCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) throws WeiException {
        String taskAdded = "";
        if (input.startsWith("todo")) {
            taskAdded = addToDo(tasks, input);
        }
        else if (input.startsWith("deadline")) {
            taskAdded = addDeadline(tasks, input);
        }
        else if (input.startsWith("event")) {
            taskAdded = addEvent(tasks, input);
        }
        int size = tasks.getSize();
        ui.showAddMessage(taskAdded);
        ui.showNumberOfRemainingTasks(size);
    }

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
        }
        catch (IndexOutOfBoundsException e) {
            throw new WeiException("please tell me when is the deadline");
        }
    }

    private String addEvent(TaskList tasks, String input) throws WeiException {
        try {
            int firstIndex = input.indexOf("/");
            int secondIndex = input.lastIndexOf("/");
            String start = input.substring(firstIndex + 6, secondIndex);
            String end = input.substring(secondIndex + 4);
            String task = input.substring(6, firstIndex - 1);
            Event event = new Event(task, start, end);
            return tasks.add(event);
        }
        catch (IndexOutOfBoundsException e) {
            throw new WeiException("please tell me when is the event occurring");
        }
    }

}
