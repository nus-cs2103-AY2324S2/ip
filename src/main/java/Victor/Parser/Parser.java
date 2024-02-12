package victor.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import victor.tasklist.TaskList;
import victor.tasktype.Deadline;
import victor.tasktype.Event;
import victor.tasktype.Task;
import victor.tasktype.Todo;
import victor.ui.Ui;


/**
 * The Parser class is the portion of the code that takes in the user command,
 * interprets what the user wants to do from the command and does it.
 *
 * @author Dominic Fu Ming Jun
 */
public class Parser {

    /**
     * The TaskName enum is a class that holds all the names of the various tasks.
     */
    enum TaskName {
        list,
        mark,
        unmark,
        todo,
        deadline,
        event,
        delete
    }


    /**
     * Ui class that is used to display certain information for this class.
     */
    private final Ui ui;
    /**
     * The currentTasks variable is used to hold the current data from the Victor.txt data file.
     */
    private final TaskList currentTasks;

    /**
     * The Parser constructor takes in the ui and currentTasks
     *
     * @param ui           The ui class that is needed to perform ui methods.
     * @param currentTasks The currentTasks that is used to get the current task list.
     */
    public Parser(Ui ui, TaskList currentTasks) {
        this.ui = ui;
        this.currentTasks = currentTasks;
    }

    /**
     * The parse method is used to decode the commandLine String and perform
     * the actions that is indicated in the commandLine.
     *
     * @param commandLine The commandLine that is to be decoded to determine what task to perform.
     * @throws IndexOutOfBoundsException Normally used if the command line tries to access an item
     *                                   position outside the range of the ArrayList.
     * @throws NumberFormatException     Used for when the commandLine action requires a number,
     *                                   but has something else instead
     * @throws DateTimeParseException    Used for deadline, when the input for the by variable is unable
     *                                   to be converted from String to a LocalDate,
     *                                   indicating that it is in the wrong format.
     */
    public void parse(String commandLine) {
        String[] inputList = commandLine.split(" ", 2);
        switch (inputList[0]) {
        case "list" -> currentTasks.printList();
        case "mark" -> {
            try {
                int position = Integer.parseInt(inputList[1]);
                Task currentTask = currentTasks.getPosValue(position - 1);
                System.out.println();
                System.out.println("Nice! I've marked this task as done:");
                currentTask.markAsDone();
                System.out.println(currentTask);
                ui.displayBarrier();
            } catch (IndexOutOfBoundsException e) {
                ui.displayBarrier();
                System.out.println("Check how many items are in the list again. "
                        + "The number you gave is too high");
                System.out.println("Can't mark an item not in the list");
                System.out.println("The format to mark a task is: mark (task list number)");
                ui.displayBarrier();
            } catch (NumberFormatException e) {
                ui.displayBarrier();
                System.out.println("Sorry, I'm only smart enough to find the task based on numbers.");
                System.out.println("Please give a number. If you refuse, too bad, "
                        + "this is all I can do.");
                ui.displayBarrier();
            }
        }
        case "unmark" -> {
            try {
                int position = Integer.parseInt(inputList[1]);
                Task currentTask = currentTasks.getPosValue(position - 1);
                ui.displayBarrier();
                System.out.println("OK, I've marked this task as not done yet:");
                currentTask.unmarkAsDone();
                System.out.println(currentTask);
                ui.displayBarrier();
            } catch (IndexOutOfBoundsException e) {
                ui.displayBarrier();
                System.out.println("Check how many items are in the list again. "
                        + "The number you gave is too high.");
                System.out.println("Can't unmark an item not in the list");
                System.out.println("The format to unmark a task is: unmark (task list number)");
                ui.displayBarrier();
            } catch (NumberFormatException e) {
                ui.displayBarrier();
                System.out.println("Sorry, I'm only smart enough to find the task based on numbers.");
                System.out.println("Please give a number. If you refuse, too bad, "
                        + "this is all I can do.");
                ui.displayBarrier();
            }
        }
        case "todo" -> {
            try {
                Todo userTask = new Todo(inputList[1], false);
                currentTasks.addTask(userTask);
                ui.displayBarrier();
                System.out.println(userTask);
                System.out.println("Now you have " + currentTasks.getSize() + " tasks in the list.");
                ui.displayBarrier();
            } catch (IndexOutOfBoundsException e) {
                ui.displayBarrier();
                System.out.println("Sorry pal, but your description is empty.");
                System.out.println("Please redo the command and remember to "
                        + "add a description of the action");
                System.out.println("The format to schedule a todo task is: " + TaskName.todo
                        + " (Description)");
                ui.displayBarrier();
            }
        }
        case "deadline" -> {
            try {
                String[] differentParts = inputList[1].split("/", 2);
                String[] deadLine = differentParts[1].split(" ", 2);
                LocalDate deadDate = LocalDate.parse(deadLine[1]);
                Deadline userTask = new Deadline(differentParts[0], false, deadLine[1]);
                currentTasks.addTask(userTask);
                ui.displayBarrier();
                System.out.println(userTask.toString());
                System.out.println("Now you have " + currentTasks.getSize() + " tasks in the list.");
                ui.displayBarrier();
            } catch (IndexOutOfBoundsException e) {
                ui.displayBarrier();
                System.out.println("Oh, you forgot to indicate when is the deadline "
                        + "or maybe you forgot the description.");
                System.out.println("Please redo the command and remember to "
                        + "add the necessary information.");
                System.out.println("The format to schedule a deadline is: " + TaskName.deadline
                        + " (Description) /by (Deadline Date + time)");
                ui.displayBarrier();
            } catch (DateTimeParseException e) {
                ui.displayBarrier();
                System.out.println("Incorrect Date format. Please insert format using yyyy-mm-dd.");
                System.out.println("E.g. 2019-02-15 is 15 February 2019");
                ui.displayBarrier();
            }
        }
        case "event" -> {
            try {
                String[] differentParts = inputList[1].split("/");
                String[] startDate = differentParts[1].split(" ", 2);
                String[] endDate = differentParts[2].split(" ", 2);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String trimStartDate = startDate[1].trim();
                String trimEndDate = endDate[1].trim();
                LocalDateTime startDT = LocalDateTime.parse(trimStartDate, formatter);
                LocalDateTime endDT = LocalDateTime.parse(trimEndDate, formatter);
                Event userTask = new Event(differentParts[0], false, trimStartDate, trimEndDate);
                currentTasks.addTask(userTask);
                ui.displayBarrier();
                System.out.println(userTask.toString());
                System.out.println("Now you have " + currentTasks.getSize() + " tasks in the list.");
                ui.displayBarrier();
            } catch (IndexOutOfBoundsException e) {
                ui.displayBarrier();
                System.out.println("You forgot to provide either a description, "
                        + "an start date or an end date for this event.");
                System.out.println("Sorry, but mind reading is not installed in me yet.");
                System.out.println("Please redo the command and remember to "
                        + "add the necessary information.");
                System.out.println("The format to schedule a event is: " + TaskName.event
                        + " (Description) /from (Start date + time) /to (End date + time)");
                ui.displayBarrier();
            } catch (DateTimeParseException e) {
                ui.displayBarrier();
                System.out.println(e);
                System.out.println("Incorrect Date format. Please insert format using yyyy-MM-dd HHmm.");
                System.out.println("E.g. 2019-02-15 10:40 is 15 February 2019 10:40 am");
                ui.displayBarrier();
            }
        }
        case "delete" -> {
            try {
                int position = Integer.parseInt(inputList[1]);
                Task chosenTask = currentTasks.getPosValue(position - 1);
                ui.displayBarrier();
                System.out.println("Noted. I've removed this task:");
                System.out.println(chosenTask.toString());
                currentTasks.removeTask(position - 1);
                System.out.println("Now you have " + currentTasks.getSize() + " tasks in the list.");
                ui.displayBarrier();
            } catch (IndexOutOfBoundsException e) {
                ui.displayBarrier();
                System.out.println("The number you gave exceeds how many items is in the list.");
                System.out.println("Can't " + TaskName.delete + " an item not in the list. "
                        + "Please try again.");
                System.out.println("The format to delete a task is: " + TaskName.delete
                        + " (task list number)");
                ui.displayBarrier();
            } catch (NumberFormatException e) {
                ui.displayBarrier();
                System.out.println("Sorry, I'm only smart enough to find the task based on numbers.");
                System.out.println("Please give a number. If you refuse, too bad, "
                        + "this is all I can do.");
                System.out.println("The format to delete a task is: " + TaskName.delete
                        + " (task list number)");
                ui.displayBarrier();
            }
        }
        case "find" -> {
            try {
                ui.displayBarrier();
                System.out.println("Here are the matching tasks in your list:");
                currentTasks.findTask(inputList[1]);
                ui.displayBarrier();
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.displayBarrier();
                System.out.println("Sorry, you didn't add anything after the keyword find.");
                System.out.println("I'm gonna need you to indicate what you want to search for "
                        + "in the list. Thanks");
                ui.displayBarrier();
            }
        }
        default -> {
            System.out.println("Command not recognized. Please try again.");
        }
        }
    }
}
