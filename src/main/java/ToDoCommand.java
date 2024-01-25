import java.util.List;

public class ToDoCommand implements NamedCommand {
    public String getName() { return "todo"; }
    public void execute(ChatSession session, String commandArgs) {
        ToDo t = new ToDo(commandArgs);
        session.taskList.add(t);

        session.printMessage(String.format("Got it. I've added the following task: \n %s", t.getName()));
    }
}
