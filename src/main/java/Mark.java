public class Mark implements NamedCommand {
    public String getName() { return "mark"; }
    public void execute(ChatSession session, String commandArgs) {
        int index = Integer.valueOf(commandArgs);
        Task t = session.taskList.getTask(index);
        t.mark();
        session.printMessage("Nice! I've marked this task as done:" + System.lineSeparator() + t.getName());
    }
}