public class AddTask implements Command {
    public void execute(ChatSession session, String commandArgs) {
        session.taskList.add(new Task(commandArgs));
        session.printMessage(String.format("added: %s", commandArgs));
    }
}
