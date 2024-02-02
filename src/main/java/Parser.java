import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class Parser {
    private enum cmd{
        list, todo, deadline, event, mark, unmark, delete, bye;
    }
    public static void initializeTask(String input, ArrayList<Task> taskList) {
        String[] data = input.split(" \\| ");
        Task task = new Task("");
        switch (data[0]) {
            case "T":
                task = new Todo(data[2]);
                break;
            case "D":
                task = new Deadline(data[2], data[3]);
                break;
            case "E":
                task = new Event(data[2], data[3], data[4]);
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
        Command command = null;
        String[] cmds = input.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        switch(cmd.valueOf(cmds[0])) {
        case list:
            command = new ListTask();
            break;
        case mark:
            command = new ToggleMarkTask(true, Integer.parseInt(cmds[1]));
            break;
        case unmark:
            command = new ToggleMarkTask(false,Integer.parseInt(cmds[1]));
            break;
        case todo:
            command = new AddTodo(cmds[1]);
            break;
        case deadline:
            String[] data = cmds[1].split(" by ", 2);
            LocalDateTime date = LocalDateTime.parse(data[1], formatter);
            command = new AddDeadline(data[0], date);
            break;
        case event:
            String[] eventData1 = cmds[1].split(" /from ", 2);
            String[] eventData2 = eventData1[1].split(" /to ", 2);
            LocalDateTime fromDate = LocalDateTime.parse(eventData2[0], formatter);
            LocalDateTime toDate = LocalDateTime.parse(eventData2[1], formatter);
            command = new AddEvent(eventData1[0], fromDate, toDate);
            break;
        case delete:
            command = new DeleteTask(Integer.parseInt(cmds[1]));
            break;
        default:
            break;
        }
        return command;
    }
}
