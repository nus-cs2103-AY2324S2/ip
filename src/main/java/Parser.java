import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
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
        String[] cmds = input.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        try {
            switch (Cmd.valueOf(cmds[0])) {
                case bye:
                    command = new Bye(Cmd.bye);
                    break;
                case list:
                    command = new ListTask(Cmd.list);
                    break;
                case mark:
                    command = new ToggleMarkTask(Cmd.mark, true, Integer.parseInt(cmds[1]));
                    break;
                case unmark:
                    command = new ToggleMarkTask(Cmd.unmark, false, Integer.parseInt(cmds[1]));
                    break;
                case todo:
                    command = new AddTodo(Cmd.todo, cmds[1]);
                    break;
                case deadline:
                    LocalDateTime date = LocalDateTime.parse(cmds[3] + " " + cmds[4], formatter);
                    command = new AddDeadline(Cmd.deadline, cmds[1], date);
                    break;
                case event:
                    LocalDateTime fromDate = LocalDateTime.parse(cmds[3] + " " + cmds[4], formatter);
                    LocalDateTime toDate = LocalDateTime.parse(cmds[6] + " " + cmds[7], formatter);
                    command = new AddEvent(Cmd.event, cmds[1], fromDate, toDate);
                    break;
                case delete:
                    command = new DeleteTask(Cmd.delete, Integer.parseInt(cmds[1]));
                    break;
                default:
                    break;
            }
        }catch (IllegalArgumentException e){
            OutputMessage.informInvalidCommand();
        }
        return command;
    }
}
