import task.Task;
import task.taskList;

public class Command {
    private String inputCommand;
    private taskList tasklist;

    public Command(String inputCommand, taskList tasklist) {
        this.inputCommand = inputCommand;
        this.tasklist = tasklist;
    }

    public String getInputCommand() {
        return this.inputCommand;
    }

    public void process() {
        if (inputCommand.equals("list")) {
            tasklist.listDownTask();
        } else if (inputCommand.startsWith("mark")) {
            char pos = inputCommand.charAt(5);
            int index = Integer.parseInt(String.valueOf(pos));
            tasklist.markTask(index);
        } else if (inputCommand.startsWith("unmark")) {
            char pos = inputCommand.charAt(7);
            int index = Integer.parseInt(String.valueOf(pos));
            tasklist.unmarkTask(index);
        } else {
            Task task = new Task(inputCommand);
            tasklist.addTask(task);
        }
    }
}
