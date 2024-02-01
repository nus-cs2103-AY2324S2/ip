import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "\t  __   __    ____  ____  ____  ____\n"
                + "\t / _\\ (  )  (  __)(  _ \\(  __)(    \\\n"
                + "\t/    \\/ (_/\\ ) _)  )   / ) _)  ) D (\n"
                + "\t\\_/\\_/\\____/(__)  (__\\_)(____)(____/\n";

        System.out.println(logo);
        final String name = "Alfred";
        TaskList taskList = new TaskList();
        OutputMessage output = new OutputMessage(name);
        output.greet();
        command(taskList);
        output.leave();
    }
    public enum Command{
        list, todo, deadline, event, mark, unmark, delete, bye
    }
    public static void command(TaskList taskList) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals(Command.bye.toString())) {
            String[] cmds = input.split(" ");
            switch(Command.valueOf(cmds[0])) {
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
                OutputMessage.informInvalidCommand();
            }
            input = scanner.nextLine();
        }
    }
}
