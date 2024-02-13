package duke.util;

import duke.task.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Parses user input and executes commands based on the input.
 * This class handles the interpretation of user commands, creating tasks,
 * and managing user interactions through the Ui class.
 */
public class Parser {
    private boolean isExit;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Parser with references to the current task list and the UI for user interaction.
     *
     * @param tasks The current list of tasks.
     * @param ui    The UI instance for displaying messages to the user.
     */
    public Parser(TaskList tasks, Ui ui){
        this.isExit = false;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Searches for a specific string within an array of strings and returns its index.
     *
     * @param checker The string to find within the array.
     * @param list    The array of strings to search through.
     * @return The index of the string if found, or -1 if not found.
     */
    public int finder(String checker, String[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(checker)) {
                return i;
            }
        }
        return -1;
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
            String[] reqList = request.split(" ");
            if (reqList.length < 2) {
                throw new TaskException("What do you want to do? Description of todo cannot be empty.");
            }
            String desc = String.join(" ", Arrays.copyOfRange(reqList, 1, reqList.length));
            Todo current = new Todo(desc);
            return current;

        } else if (request.startsWith("deadline")) {
            String[] reqList = request.split(" ");
            if (Arrays.asList(reqList).contains("/by")) {
                int byIndex = finder("/by", reqList);
                String desc = String.join(" ", Arrays.copyOfRange(reqList, 1, byIndex));
                String time = String.join(" ", Arrays.copyOfRange(reqList, byIndex + 1, reqList.length));
                Deadline current = new Deadline(desc, time);
                return current;
            } else{
                throw new TaskException("Please specify when is the deadline.");
            }

        } else if (request.startsWith("event")) {
            String[] reqList = request.split(" ");
            if (Arrays.asList(reqList).contains("/from") && Arrays.asList(reqList).contains("/to")){
                int fromIndex = finder("/from", reqList);
                int toIndex = finder("/to", reqList);
                String desc = String.join(" ", Arrays.copyOfRange(reqList, 1, fromIndex));
                String start = String.join(" ", Arrays.copyOfRange(reqList, fromIndex + 1, toIndex));
                String end = String.join(" ", Arrays.copyOfRange(reqList, toIndex + 1, reqList.length));

                Event current = new Event(desc, start, end);
                return current;
            } else if (Arrays.asList(reqList).contains("/from")){
                throw new TaskException("Please specify when the event ends.");
            } else if (Arrays.asList(reqList).contains("/to")){
                throw new TaskException("Please specify when the event starts.");
            } else {
                throw new TaskException("Please specify the event timeframe.");
            }

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
            this.isExit = true;
            return ui.bye();
        } else if(current.equals("list")) {
            return this.ui.showList(tasks.showList());
        } else if (current.startsWith("mark")) {
            String[] marking = current.split(" ");
            int position = Integer.parseInt(marking[1]) - 1;
            Task curr = tasks.getTask(position);
            curr.makeDone();
            return ui.markTask(curr);
        } else if (current.startsWith("unmark")) {
            String[] marking = current.split(" ");
            int position = Integer.parseInt(marking[1]) - 1;
            Task curr = tasks.getTask(position);
            curr.makeUndone();
            return ui.unmarkTask(curr);
        } else if (current.startsWith("delete")){
            String[] marking = current.split(" ");
            int position = Integer.parseInt(marking[1]) - 1;
            return ui.delete(position, tasks);
        } else if (current.startsWith("find")) {
            String[] reqList = current.split(" ");
            if (reqList.length < 2) {
                throw new TaskException("What do you want me to find? Please specify");
            }
            String keyword = String.join(" ", Arrays.copyOfRange(reqList, 1, reqList.length));
            return ui.showFilteredList(keyword, tasks);

        } else {
            try {
                Task newTask = identify(current);
                int initial = tasks.size();
                tasks.addTask(newTask);
                assert tasks.size() == initial + 1 : "tasks size should increase by 1";
                return ui.addTask(newTask, tasks);
            } catch (TaskException e) {
                return ui.showError(e.getMessage());
            }
        }
    }

    public boolean isExit(){
        return this.isExit;
    }
}
