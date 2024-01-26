import task.Task;
import task.taskList;
import task.Event;
import task.Todo;
import task.Deadline;
import util.DukeException;

public class Command {
    public static void process(String inputCommand, taskList tasklist) {
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
            try {
                String des = inputCommand.substring(5);
                Task todo = new Todo(des);
                tasklist.addTask(todo);
            } catch (StringIndexOutOfBoundsException e) {
                DukeException error = new DukeException(e);
                System.out.println(error);
            }
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
        } else if(inputCommand.startsWith("delete")) {
            char pos = inputCommand.charAt(7);
            int index = Integer.parseInt(String.valueOf(pos));
            tasklist.deleteTask(index);
        } else {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
