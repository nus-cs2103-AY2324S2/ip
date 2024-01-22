import task.Task;
import task.taskList;
import task.Event;
import task.Todo;
import task.Deadline;

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
        } else if (inputCommand.startsWith("todo")) {
            String des = inputCommand.substring(5);
            Task todo = new Todo(des);
            tasklist.addTask(todo);
        } else if (inputCommand.startsWith("deadline")) {
            String[] separate = inputCommand.substring(9).split("/");
            for (int i = 0; i < separate.length; i++) {
                separate[i] = separate[i].replaceAll("by", " ").trim();
            }
            Task deadline = new Deadline(separate[0], separate[1]);
            tasklist.addTask(deadline);
        } else if (inputCommand.startsWith("event")) {
            String[] separate = inputCommand.substring(6).split("/");
            for (int i = 0; i < separate.length; i++) {
                separate[i] = separate[i].replaceAll("from", " ").replaceAll("to", " ").trim();
            }
            Task event = new Event(separate[0], separate[1], separate[2]);
            tasklist.addTask(event);
        } else {
            Task task = new Task(inputCommand);
            tasklist.addTask(task);
        }
    }
}
