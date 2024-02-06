package duke;

import java.time.format.DateTimeParseException;

/**
 * Parser Class is responsible for interpreting the commands entered by the user. Upon invalid commands, exceptions
 * will be thrown and handled.
 */
public class Parser {
    private String command = "";
    private String secondaryInput = "";

    /**
     * Constructor for the Parser Class.
     */
    public Parser() {

    }

    /**
     * Method is responsible for interpreting commands send by the user and handling invalid commands.
     * @param commandInput
     * @param duke
     * @param taskList
     */
    public String input(String commandInput, Duke duke, TaskList taskList) {
        boolean isCommandValid = false;
        this.command = "";
        this.secondaryInput = "";

        if (commandInput.equals("save")) {
            this.command = commandInput;
            isCommandValid = true;
            return duke.exit();
        } else if (commandInput.equals("date")) {
            return "Maybe another time ><\n";
        } else if(commandInput.equals("whoami")) {
            return "You are ZGMF X10A Freedom!\n"
                    + "How could you forget? O_o\n";
        } else if(commandInput.equals("hello")) {
            return "HELLO FREEDOM!! c:";
        } else if (commandInput.equals("list")) {
            this.command = commandInput;
            isCommandValid = true;
            return taskList.listTask();
        } else {
            String[] inputSplit = commandInput.split(" ", 2);
            this.command = inputSplit[0];

            if (this.command.equals("find")) {
                isCommandValid = true;
                return taskList.findTask(inputSplit[1]);
            }

            try {
                if (this.command.equals("mark")) {
                    isCommandValid = true;
                    return taskList.markTask(Integer.valueOf(inputSplit[1]) - 1);
                } else if (this.command.equals("unmark")) {
                    isCommandValid = true;
                    return taskList.unmarkTask(Integer.valueOf((inputSplit[1])) - 1);
                } else if (this.command.equals("delete")) {
                    isCommandValid = true;
                    return taskList.deleteTask(Integer.valueOf((inputSplit[1])) - 1);
                }
            } catch (IndexOutOfBoundsException e) {
                if (taskList.getSize() == 0) {
                    return "You have no task to mark,unmark or delete!\n";
                } else {
                    return "You only have " + taskList.getSize() + " tasks!\n"
                            + "Select a number from 1 to " + taskList.getSize() + ".\n";
                }
            } catch (NumberFormatException e) {
                return "Please input a number.\n";
            }

            try {
                if ((this.command.equals("todo")) || (this.command.equals("deadline"))
                        || (this.command.equals("event"))) {
                    this.secondaryInput = inputSplit[1];
                    isCommandValid = true;
                    return taskList.addTask(this.command, this.secondaryInput);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                if (this.command.equals("deadline")) {
                    return "Please input a date or time with a / in front.\n";
                } else if (this.command.equals("event")) {
                    return "Please input a start and end time "
                            + "or date with a / in front of both periods.\n";
                }
            } catch (DateTimeParseException e) {
                return "Invalid DateTime Format. Please input as follows:\n"
                        + "dd-mm-yyyy hh:mm";
            }
        }

        if (!isCommandValid) {
            return "No such command or too many parameters. Please try again\n";
        }
        return "";
    }
}
