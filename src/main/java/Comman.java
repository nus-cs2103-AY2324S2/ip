import java.util.ArrayList;
import java.util.Scanner;

public class Comman {
    private cmd type;
    private enum cmd{
        list, todo, deadline, event, mark, unmark, delete, bye;
    }
    public static void start(TaskList taskList){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals(cmd.bye.toString())) {
            try {
                run(input, taskList);
            }catch(IllegalArgumentException e){
                OutputMessage.informInvalidCommand();
            }
            input = scanner.nextLine();
        }
    }
    private static void run(String input, TaskList taskList){
        String[] cmds = input.split(" ");
        switch(cmd.valueOf(cmds[0])) {
            case list:
                OutputMessage.displayFullList(taskList);
                break;
            case mark:
                taskList.markList(Integer.parseInt(cmds[1]));
                break;
            case unmark:
                taskList.unmarkList(Integer.parseInt(cmds[1]));
                break;
            case todo:
                taskList.addTodo(input);
                break;
            case deadline:
                taskList.addDeadline(input);
                break;
            case event:
                taskList.addEvent(input);
                break;
            case delete:
                taskList.deleteList(Integer.parseInt(cmds[1]));
                break;
            default:
                break;
        }
    }
    public static void initialize(String input, ArrayList<Task> taskList){
        String[] data = input.split(" \\| ");
        Task task = new Task("");
        switch(data[0]){
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
        if(data[1].equals("1")) {
            task.mark();
        }
        taskList.add(task);
    }


}
