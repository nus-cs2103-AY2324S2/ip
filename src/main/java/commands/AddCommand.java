package commands;

import exceptions.InvalidInputFormatException;
import helpers.TaskList;
import helpers.Ui;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class AddCommand extends Command {
    private final CommandType commandType;
    private final String[] input;

    public AddCommand(CommandType commandType, String[] input) {
        this.commandType = commandType;
        this.input = input;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws InvalidInputFormatException {
        switch (commandType) {
            case TODO:
                addTodo(input[1], taskList, ui);
                break;
            case DEADLINE:
                addDeadline(input[1].split("/", 2), taskList, ui);
                break;
            case EVENT:
                addEvent(input[1].split("/", 2), taskList, ui);
                break;
        }
    }

    private void addTodo(String taskDescription, TaskList taskList, Ui ui) throws InvalidInputFormatException {
        if (taskDescription.trim().isEmpty()) {
            throw new InvalidInputFormatException("todo");
        }
        Task newTask = new Todo(taskDescription);
        taskList.addTask(newTask);
        ui.displayAddedTask(newTask, taskList.getLength());
    }

    private void addDeadline(String[] deadlineDetails, TaskList taskList, Ui ui) throws InvalidInputFormatException {
        if (deadlineDetails.length < 2 || deadlineDetails[1].split(" ", 2).length < 2) {
            throw new InvalidInputFormatException("deadline");
        }
        String deadlineDescription = deadlineDetails[0];
        String deadline = deadlineDetails[1].split(" ", 2)[1];
        Task newTask = new Deadline(deadlineDescription, deadline);
        taskList.addTask(newTask);
        ui.displayAddedTask(newTask, taskList.getLength());
    }

    private void addEvent(String[] eventDetails, TaskList taskList, Ui ui) throws InvalidInputFormatException {
        if (eventDetails.length < 2) {
            throw new InvalidInputFormatException("event");
        }
        String eventDescription = eventDetails[0];
        String[] fromToDetails = eventDetails[1].split("/", 2);
        if (fromToDetails.length < 2
                || fromToDetails[0].split(" ", 2).length < 2
                || fromToDetails[1].split(" ", 2).length < 2) {
            throw new InvalidInputFormatException("event");
        }
        String from = fromToDetails[0].split(" ", 2)[1];
        String to = fromToDetails[1].split(" ", 2)[1];
        Task newTask = new Event(eventDescription, from, to);
        taskList.addTask(newTask);
        ui.displayAddedTask(newTask, taskList.getLength());
    }
}
