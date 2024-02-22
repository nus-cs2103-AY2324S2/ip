package duke.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import javax.swing.text.DateFormatter;

/**
 *  Represents the parser of the program that converts user inputs into Commands.
 */
public class Parser {

    /**
     * The enumeration of command.
     */
    public enum Cmd {
        list, todo, deadline, event, mark, unmark, delete, find, viewSchedule, bye, none, help
    }

    private Ui ui;
    public Parser(Ui ui){
        this.ui = ui;
    }
    /**
     * Initializes the given Task ArrayList with given input,
     * by adding the tasks from each line of input into the ArrayList.
     * The different data in each line of a stored task is splitted by a "|" sign.
     * THe method processes these data, creates corresponding task and add the ArrayList accordingly.
     *
     * @param input the input for each task, the input typically comes from a stored data,
     *              accessed by the Storage class.
     * @param taskList the given Task ArrayList to be initialized.
     */
    public static void initializeTask(String input, ArrayList<Task> taskList) {
        String[] commandComponents = input.split(" \\| ");
        Task task = new Task("");
        assert(commandComponents[0].length() == 1);
        switch (commandComponents[0]) {
        case "T":
            task = new Todo(commandComponents[2]);
            break;
        case "D":
            LocalDateTime date = LocalDateTime.parse(commandComponents[3], DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
            task = new Deadline(commandComponents[2], date);
            break;
        case "E":
            LocalDateTime fromDate = LocalDateTime.parse(commandComponents[3], DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
            LocalDateTime toDate = LocalDateTime.parse(commandComponents[4], DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
            task = new Event(commandComponents[2], fromDate, toDate);
            break;
        default:
            System.out.println("PROBLEM encountered with the saved data while loading");
            break;
        }
        if (commandComponents[1].equals("1")) {
            task.mark();
        }
        taskList.add(task);
    }

    /**
     * Parse the given input into Commands that can be run inside the program.
     *
     * @param input the String input by the user. The different type of input are as follow:
     *              todo |description|: to add a todo with the given description.
     *              deadline |description| |date|: to add a deadline with the given information.
     *              event |description| |start date| |end date|: to add an event with the given information.
     *              list : to list the full list
     *              tmark |index|: to mark the task at index no |index| in the list as complete
     *              tunmark |index|: to mark the task at index no |index| in the last as incomplete
     *              delete |index|: to remove the taks at index no |index| in the last
     *              bye: to leave the program
     * @return the Command that correspond to the input
     */
    public Command parse(String input) {
        Command command = new NoActionCommand(Cmd.none);
        String[] commandComponents = input.split(" ", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        try {
            switch (Cmd.valueOf(commandComponents[0])) {
            case bye:
                command = new ByeCommand(Cmd.bye);
                break;
            case list:
                command = new ListTaskCommand(Cmd.list);
                break;
            case mark:
                command = new ToggleMarkTaskCommand(Cmd.mark, Integer.parseInt(commandComponents[1]));
                break;
            case unmark:
                command = new ToggleMarkTaskCommand(Cmd.unmark, Integer.parseInt(commandComponents[1]));
                break;
            case todo:
                command = new AddTodoCommand(Cmd.todo, commandComponents[1]);
                break;
            case deadline:
                String[] deadlineData = commandComponents[1].split(" /by ", 2);
                LocalDateTime date = LocalDateTime.parse(deadlineData[1], formatter);
                command = new AddDeadlineCommand(Cmd.deadline, deadlineData[0], date);
                break;
            case event:
                String[] eventData = commandComponents[1].split(" /from ", 2);
                String[] eventData2 = eventData[1].split(" /to ", 2);
                LocalDateTime fromDate = LocalDateTime.parse(eventData2[0], formatter);
                LocalDateTime toDate = LocalDateTime.parse(eventData2[1], formatter);
                command = new AddEventCommand(Cmd.event, eventData[0], fromDate, toDate);
                break;
            case find:
                command = new FindCommand(Cmd.find, commandComponents[1]);
                break;
            case viewSchedule:
                LocalDate targetDate = LocalDate.parse(commandComponents[1], dateFormatter);
                command = new ViewScheduleCommand(Cmd.viewSchedule, targetDate);
            case delete:
                command = new DeleteTaskCommand(Cmd.delete, Integer.parseInt(commandComponents[1]));
                break;
            case help:
                command = new HelpCommand(Cmd.help);
                break;
            default:
                break;
            }
        } catch (IllegalArgumentException e) {
            ui.informInvalidCommand();
        } catch (DateTimeParseException e) {
            ui.informWrongDateFormat();
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.informWrongInputFormat();
        }
        return command;
    }
}
