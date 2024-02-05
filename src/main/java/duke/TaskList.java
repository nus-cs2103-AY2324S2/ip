package duke;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private Storage storage;
    private Parser parser = new Parser();
    private Ui ui = new Ui();

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    public TaskList(Storage storage) throws DukeException {
        this.tasks = new ArrayList<>();
        this.storage = storage;
        try {
            this.tasks = storage.load();
        } catch (Exception e) {
            throw new DukeException("Error loading tasks\n");
        }
    }

    public void addTask(String task) throws DukeException {
        String command = parser.parseCommand(task);
        Task newTask = null;
        if (command.equals("todo")) {
            String description = parser.parseDescription(task);
            if (description.equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.\n");
            } else {
                newTask = new ToDo(description);
            }
        } else if (command.equals("deadline")) {
            String[] deadline = parser.parseDeadline(task);
            newTask = new Deadline(deadline[0], deadline[1]);
        } else if (command.equals("event")) {
            String[] event = parser.parseEvent(task);
            newTask = new Event(event[0], event[1], event[2]);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n");
        }

        tasks.add(newTask);
        ui.printMessage("Got it. I've added this task: \n" + newTask.toString() + "\nNow you have " + tasks.size()
                + " tasks in the list.\n");

        updateStorage();
    }

    public void displayTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            ui.printMessage((i + 1) + ". " + tasks.get(i).toString());
        }
        ui.printMessage("");
    }

    public void markTaskAsDone(int index) throws DukeException {
        tasks.get(index - 1).markAsDone();
        try {
            updateStorage();
        } catch (DukeException e) {
            throw new DukeException("Error updating storage\n");
        }
    }

    public void unmarkTaskAsDone(int index) throws DukeException {
        tasks.get(index - 1).unmarkDone();
        try {
            updateStorage();
        } catch (DukeException e) {
            throw new DukeException("Error updating storage\n");
        }
    }

    public void deleteTask(int index) throws DukeException {
        ui.printMessage("Noted. I've removed this task: \n" + tasks.get(index - 1).toString() + "\nNow you have "
                + (tasks.size() - 1) + " tasks in the list.\n");
        tasks.remove(index - 1);

        try {
            updateStorage();
        } catch (DukeException e) {
            throw new DukeException("Error updating storage\n");
        }
    }

    public void updateStorage() throws DukeException {
        try {
            storage.save(tasks);
            ui.printMessage("Tasks updated in storage\n");
        } catch (Exception e) {
            throw new DukeException("Error updating storage\n");
        }
    }
}