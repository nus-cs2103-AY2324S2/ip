package duke;

import java.time.format.DateTimeParseException;

/**
 * Parser Class is responsible for interpreting the commands entered by the user. Upon invalid commands, exceptions
 * will be thrown and handled.
 */
public class Parser {
    private String command = "";
    private TaskList taskList;

    /**
     * Constructor for the Parser Class.
     */
    public Parser() {

    }

    /**
     * Interprets commands send by the user and handles invalid commands.
     * @param commandInput
     * @param duke
     * @param taskListInput
     */
    public String checkInput(String commandInput, Duke duke, TaskList taskListInput) {
        this.command = commandInput;
        this.taskList = taskListInput;

        if (commandInput.equals("save")) {
            return duke.save();
        } else if (commandInput.equals("date") || (commandInput.equals("hello"))
                || (commandInput.equals("whoami"))) {
            return duke.ui.miscCommands(commandInput);
        } else if (commandInput.equals("bye")) {
            return duke.ui.bye();
        } else if (commandInput.equals("list")) {
            return taskList.listTask();
        } else if (commandInput.equals("clear")) {
            return taskList.clear();
        } else if (commandInput.startsWith("find")) {
            return checkFind();
        } else if (commandInput.startsWith("mark")) {
            return checkMark();
        } else if (commandInput.startsWith("unmark")) {
            return checkUnmark();
        } else if (commandInput.startsWith("delete")) {
            return checkDelete();
        } else if (commandInput.startsWith("todo")) {
            return checkTodo();
        } else if (commandInput.startsWith("deadline")) {
            return checkDeadline();
        } else if (commandInput.startsWith("event")) {
            return checkEvent();
        } else if (commandInput.startsWith("postpone")) {
            return checkPostpone();
        } else {
            return "I don't understand :/";
        }
    }

    private String checkFind() {
        String[] inputSplit = this.command.split(" ", 2);
        try {
            String taskdesc = inputSplit[1];
            return taskList.findTask(taskdesc);
        } catch (IndexOutOfBoundsException e) {
            return "Please input a Task Description to find";
        }
    }

    private String checkMark() {
        try {
            String[] inputSplit = this.command.split(" ", 2);
            Integer taskIndex = Integer.valueOf(inputSplit[1]) - 1;
            return taskList.markTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            if (taskList.getSize() == 0) {
                return "You have no task to mark!\n";
            } else {
                return "You only have " + taskList.getSize() + " tasks!\n"
                        + "Select a number from 1 to " + taskList.getSize() + ".\n";
            }
        } catch (NumberFormatException e) {
            return "Please input a number.\n";
        }
    }

    private String checkUnmark() {
        try {
            String[] inputSplit = this.command.split(" ", 2);
            Integer taskIndex = Integer.valueOf(inputSplit[1]) - 1;
            return taskList.unmarkTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            if (taskList.getSize() == 0) {
                return "You have no task to unmark!\n";
            } else {
                return "You only have " + taskList.getSize() + " tasks!\n"
                        + "Select a number from 1 to " + taskList.getSize() + ".\n";
            }
        } catch (NumberFormatException e) {
            return "Please input a number.\n";
        }
    }

    private String checkDelete() {
        try {
            String[] inputSplit = this.command.split(" ", 2);
            Integer taskIndex = Integer.valueOf(inputSplit[1]) - 1;
            return taskList.deleteTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            if (taskList.getSize() == 0) {
                return "You have no task to delete!\n";
            } else {
                return "You only have " + taskList.getSize() + " tasks!\n"
                        + "Select a number from 1 to " + taskList.getSize() + ".\n";
            }
        } catch (NumberFormatException e) {
            return "Please input a number.\n";
        } catch (NullPointerException e) {
            return "Please input a Task Index to delete.";
        }
    }

    private String checkTodo() {
        String[] inputSplit = this.command.split(" ", 2);
        try {
            String taskdesc = inputSplit[1];
            return taskList.addTask("todo", taskdesc);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please input a Task Description.\n";
        } catch (DateTimeParseException e) {
            return "Invalid DateTime Format. Please input as follows:\n"
                    + "dd-mm-yyyy hh:mm";
        }
    }

    private String checkDeadline() {
        String[] inputSplit = this.command.split(" ", 2);
        try {
            String taskdesc = inputSplit[1];
            assert taskdesc.contains("/") : "input should contain /";
            return taskList.addTask("deadline", taskdesc);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please input a date with a / in front.\n";
        } catch (DateTimeParseException e) {
            return "Invalid DateTime Format. Please input as follows:\n"
                    + "dd-mm-yyyy hh:mm\n";
        }
    }

    private String checkEvent() {
        String[] inputSplit = this.command.split(" ", 2);
        try {
            String taskdesc = inputSplit[1];
            assert taskdesc.contains("/") : "input should contain /";
            return taskList.addTask("event", taskdesc);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please input a start and end date with a / in front.\n";
        } catch (DateTimeParseException e) {
            return "Invalid DateTime Format. Please input as follows:\n"
                    + "dd-mm-yyyy hh:mm\n";
        }
    }

    private String checkPostpone() {
        try {
            String[] inputSplit = this.command.split(" ", 2);
            String taskdesc = inputSplit[1];
            return this.taskList.postponeTask(taskdesc);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please enter a task description.";
        }
    }
}
