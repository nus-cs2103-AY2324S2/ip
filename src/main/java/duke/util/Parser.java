package duke.util;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {
    public enum Cmd{
        list, todo, deadline, event, mark, unmark, delete, bye, none;
    }
    public static void initializeTask(String input, ArrayList<Task> taskList) {
        String[] data = input.split(" \\| ");
        Task task = new Task("");
        switch (data[0]) {
            case "T":
                task = new Todo(data[2]);
                break;
            case "D":
                LocalDateTime date = LocalDateTime.parse(data[3], DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
                task = new Deadline(data[2], date);
                break;
            case "E":
                LocalDateTime fromDate = LocalDateTime.parse(data[3], DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
                LocalDateTime toDate = LocalDateTime.parse(data[4], DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
                task = new Event(data[2], fromDate, toDate);
                break;
            default:
                System.out.println("PROBLEM encountered with the saved data while loading");
                break;
        }
        if (data[1].equals("1")) {
            task.mark();
        }
        taskList.add(task);
    }
    public Command parse(String input){
        Command command = new NoAction(Cmd.none);
        String[] cmds = input.split(" ", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        try {
            switch (Cmd.valueOf(cmds[0])) {
                case bye:
                    command = new ByeCommand(Cmd.bye);
                    break;
                case list:
                    command = new ListTask(Cmd.list);
                    break;
                case mark:
                    command = new ToggleMarkTask(Cmd.mark, Integer.parseInt(cmds[1]));
                    break;
                case unmark:
                    command = new ToggleMarkTask(Cmd.unmark, Integer.parseInt(cmds[1]));
                    break;
                case todo:
                    command = new AddTodo(Cmd.todo, cmds[1]);
                    break;
                case deadline:
                    String[] deadlineData = cmds[1].split(" /by ", 2);
                    LocalDateTime date = LocalDateTime.parse(deadlineData[1], formatter);
                    command = new AddDeadline(Cmd.deadline, deadlineData[0], date);
                    break;
                case event:
                    String[] eventData = cmds[1].split(" /from ", 2);
                    String[] eventData2 = eventData[1].split(" /to ", 2);
                    LocalDateTime fromDate = LocalDateTime.parse(eventData2[0], formatter);
                    LocalDateTime toDate = LocalDateTime.parse(eventData2[1], formatter);
                    command = new AddEvent(Cmd.event, eventData[0], fromDate, toDate);
                    break;
                case delete:
                    command = new DeleteTask(Cmd.delete, Integer.parseInt(cmds[1]));
                    break;
                default:
                    break;
            }
        }catch (IllegalArgumentException e){
            Ui.informInvalidCommand();
        }catch (DateTimeParseException e){
            Ui.informWrongDateFormat();
        }catch (ArrayIndexOutOfBoundsException e){
            Ui.informWrongInputFormat();
        }
        return command;
    }
}
