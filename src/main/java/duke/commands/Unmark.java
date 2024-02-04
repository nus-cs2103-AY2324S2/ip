package duke.commands;
import duke.ChatSession;
import duke.tasks.Task;

public class Unmark implements NamedCommand {
    public String getName() { return "unmark"; }
    public void execute(ChatSession session, String commandArgs) {
        int index = Integer.valueOf(commandArgs);
        Task t = session.taskList.getTask(index);
        t.unmark();
        session.printMessage("OK, I've marked this task as not done yet:" + System.lineSeparator() + t.getName());
    }
}
