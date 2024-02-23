package alfred.util;

import alfred.task.Task;
import alfred.task.TaskException;
import alfred.task.TaskList;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Parses user input and executes commands based on the input.
 * This class handles the interpretation of user commands, creating tasks,
 * and managing user interactions through the Ui class.
 */
public class Parser {
    private TaskList tasks;
    private Ui ui;
    private CommandManager commander;

    /**
     * Constructs a Parser with references to the current task list and the UI for user interaction.
     *
     * @param tasks The current list of tasks.
     * @param ui    The UI instance for displaying messages to the user.
     */
    public Parser(TaskList tasks, Ui ui){
        this.tasks = tasks;
        this.ui = ui;
        this.commander = new CommandManager(this.ui, this.tasks);
    }

    /**
     * Identifies the type of task based on the user's request and creates the appropriate task object.
     * Throws TaskException if the input format is incorrect or missing necessary information.
     *
     * @param request The user's command input.
     * @return A Task object corresponding to the user's request.
     * @throws TaskException If the request format is invalid or lacks necessary information.
     */
    public Task identify(String request) throws TaskException {
        assert request != null && !request.isEmpty() : "request invalid";
        if (request.startsWith("todo")) {
            String[] reqParts = request.split(" ");
            return commander.createTodoCommand(reqParts);
        } else if (request.startsWith("deadline")) {
            String[] reqList = request.split(" ");
            return commander.createDeadlineCommand(reqList);
        } else if (request.startsWith("event")) {
            String[] reqList = request.split(" ");
            return commander.createEventCommand(reqList);
        } else {
            throw new TaskException("Apologies, I don't understand you. Please try again");
        }
    }

    /**
     * Processes the user's input command and performs actions such as adding, deleting, or listing tasks.
     * Uses the Ui class to interact with the user based on the processed commands.
     *
     * @param current The user's input command.
     */
    public ArrayList<String> read(String current) throws TaskException {
        if(current.equals("bye")) {
            return commander.byeCommand();
        } else if(current.equals("list")) {
            return commander.showListCommand();
        } else if (current.startsWith("mark")) {
            String[] marking = current.split(" ");
            int position = Integer.parseInt(marking[1]) - 1;
            return commander.markTaskCommand(position);
        } else if (current.startsWith("unmark")) {
            String[] marking = current.split(" ");
            int position = Integer.parseInt(marking[1]) - 1;
            return commander.unmarkTaskCommand(position);
        } else if (current.startsWith("delete")){
            String[] marking = current.split(" ");
            int position = Integer.parseInt(marking[1]) - 1;
            return commander.deleteTaskCommand(position);
        } else if (current.startsWith("find")) {
            String[] reqList = current.split(" ");
            return commander.findTaskCommand(reqList);
        } else if (current.startsWith("snooze")) {
            String[] marking = current.split(" ");
            int position = Integer.parseInt(marking[1]) - 1;
            return commander.snoozeCommand(position);
        } else if (current.startsWith("postpone")) {
            String[] marking = current.split(" ");
            int position = Integer.parseInt(marking[1]) - 1;
            int days = Integer.parseInt(marking[2]);
            return commander.postponeCommand(position, days);
        } else if (current.startsWith("reschedule")) {
            String[] marking = current.split(" ");
            String[] input = Arrays.copyOfRange(marking, 1, marking.length);
            return commander.rescheduleCommand(input);
        }
        try {
            Task curr = identify(current);
            tasks.addTask(curr);
            return ui.addTask(curr, tasks);
        } catch (TaskException e) {
            return ui.showError(e.getMessage());
        }
    }
}
