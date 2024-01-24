public class ListTasks implements NamedCommand {
    public String getName() {return "list";}
    public void execute(ChatSession session, String commandArgs) {
        session.printMessage("Here are the tasks in your list:"
        + System.lineSeparator()
        + session.taskList.generateName());
    }
}
