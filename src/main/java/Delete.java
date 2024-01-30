public class Delete implements NamedCommand {
    public String getName() { return "delete"; }
    public void execute(ChatSession session, String commandArgs) {
        int index = Integer.valueOf(commandArgs);
        Task t = session.taskList.pop(index);
        session.printMessage("Nice! I've deleted this task:" + System.lineSeparator() + t.getName());
    }
}
