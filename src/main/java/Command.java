import java.util.Scanner;

public class Command {
    private enum cmd{
        list, todo, deadline, event, mark, unmark, delete, bye;
    }
    public static void command(TaskList taskList){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals(cmd.bye.toString())) {
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
                    OutputMessage.informInvalidCommand();
            }
            input = scanner.nextLine();
        }
    }
}
