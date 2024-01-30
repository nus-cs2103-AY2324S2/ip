package duke;

import java.time.format.DateTimeParseException;

public class Parser {
    String command = "";
    String secondaryInput = "";

    public Parser() {

    }
    public void input(String commandInput, Duke duke, TaskList taskList) {
        boolean isCommandValid = false;
        this.command ="";
        this.secondaryInput ="";

        if (commandInput.equals("bye")) {
            this.command = commandInput;
            isCommandValid = true;
            duke.exit();
        } else if (commandInput.equals("list")) {
            this.command = commandInput;
            isCommandValid = true;
            taskList.listTask();
        } else {
            String[] inputSplit = commandInput.split(" ", 2);
            this.command = inputSplit[0];

            try {
                if (this.command.equals("mark")) {
                    isCommandValid = true;
                    taskList.markTask(Integer.valueOf(inputSplit[1]) - 1);
                } else if (this.command.equals("unmark")) {
                    isCommandValid = true;
                    taskList.unmarkTask(Integer.valueOf((inputSplit[1])) - 1);
                } else if (this.command.equals("delete")) {
                    isCommandValid = true;
                    taskList.deleteTask(Integer.valueOf((inputSplit[1])) - 1);
                }
            } catch(IndexOutOfBoundsException e) {
                if (taskList.size() == 0) {
                    System.out.println("\tYou have no task to mark,unmark or delete!");
                } else {
                    System.out.println("\tYou only have " + taskList.size() +" tasks!");
                    System.out.println("\tSelect a number from 1 to " + taskList.size() + ".");
                }
            } catch(NumberFormatException e) {
                System.out.println("\tPlease input a number.");
            }

            try {
                if ((this.command.equals("todo")) || (this.command.equals("deadline"))
                        || (this.command.equals("event"))) {
                    this.secondaryInput = inputSplit[1];
                    isCommandValid = true;
                    taskList.addTask(this.command, this.secondaryInput);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                if (this.command.equals("deadline")) {
                    System.out.println("\tPlease input a date or time with a / in front.");
                } else if (this.command.equals("event")) {
                    System.out.println("\tPlease input a start and end time or date with a / in front of both periods.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("\tInvalid DateTime Format. Please input as follows:");
                System.out.println("\tdd-mm-yyyy hh:mm");
            }
        }

        if (!isCommandValid) {
            System.out.println("\tNo such command or too many parameters. Please try again");
        }
    }
}
