package bit;

import java.util.ArrayList;

/**
 * The list containing all tasks
 */
public class Tasklist {
    private ArrayList<Task> taskList = new ArrayList<>();
    private Ui ui;
    private Storage storage;
    /**
     * Initializes a tasklist
     */
    public Tasklist() {
        this.ui = new Ui();
        storage = new Storage();
    }

    /**
     * Returns current size of taskList
     *
     * @return size of tasklist.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns task stored at index i of list
     *
     * @param i the index of the task to be retrieved.
     * @return task stored at i.
     */

    public Task getTask(int i) {
        return taskList.get(i);
    }

    /**
     * Adds tasks to the list. Takes in String that it parses. Will throw various errors based on input.
     * It accepts todos, deadlines and events.
     *
     * @param input String command from user.
     * @throws DukeException
     */
    public String addTo(String input) throws DukeException {
        if (input.startsWith("todo")) {
            return addTodo(input);
        } else if (input.contains("event ")) {
            return addEvent(input);
        } else if (input.startsWith("deadline")) {
            return addDeadline(input);
        } else {
            return ui.handleErrorMessage("");
        }
    }
    private String addTodo(String input) throws DukeException {
        try {
            String[] parts = input.split(" ", 2);
            if (!parts[0].equals("todo")) {
                throw new DukeException("I have no idea what that means!");
            }
            if (parts[1].trim().isEmpty()) {
                throw new DukeException("Hmmm, that todo is empty!");
            }
            taskList.add(new Todo(parts[1]));
            int i = taskList.size();
            Task t = taskList.get(i - 1);
            storage.cleanList();
            storage.saveAll(this);
            return ui.sayAdded(i, "todo", t);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Hmmm, that todo is empty!");
        }
    }
    private String addEvent(String input) throws DukeException {
        try {
            String[] parts = input.split(" ", 2);
            if (!parts[0].equals("event")) {
                throw new DukeException("What is that?");
            }
            String[] components = parts[1].split("/to");
            String end = components[1];
            String[] compo = components[0].split("/from");
            String start = compo[1];
            if (compo[0].trim().isEmpty() || compo[1].trim().isEmpty() || end.trim().isEmpty()) {
                throw new DukeException("Missing something?");
            }
            taskList.add(new Event(compo[0], start, end));
            int i = taskList.size();
            Event e = (Event) taskList.get(i - 1);
            storage.cleanList();
            storage.saveAll(this);
            return ui.sayAdded(i, "event", taskList.get(i - 1));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Did you make a mistake?");
        }
    }
    private String addDeadline(String input) throws DukeException {
        try {
            String[] parts = input.split(" ", 2);
            if (parts[0].equals("deadline")) {
                String[] compo = parts[1].split("/by");
                if (compo[0].trim().isEmpty() || compo[1].trim().isEmpty()) {
                    throw new DukeException("Did you miss something?");
                }
                Deadline d = new Deadline(compo[0], compo[1]);
                if (!d.getValid()) {
                    return ui.handleErrorMessage("NotaDate");
                }
                taskList.add(d);
                int i = taskList.size();
                storage.cleanList();
                storage.saveAll(this);
                return ui.sayAdded(i, "deadline", taskList.get(i - 1));
            }
        } catch (ArrayIndexOutOfBoundsException x) {
            throw new DukeException("Did you miss something?");
        }
        throw new DukeException("Is that a typo I see?");
    }
    /**
     * Deletes task found at i - 1. This means the very first task is deleted when i = 1.
     * @param i index number of task to be deleted.
     */
    public String delete(int i) {
        try {
            i -= 1;
            String s = taskList.get(i).toString();
            taskList.remove(i);
            storage.cleanList();
            storage.saveAll(this);
            return ui.sayDeleted(s);
        } catch (IndexOutOfBoundsException x) {
            return ui.handleErrorMessage("absent");
        }
    }

    /**
     * Marks task found at i - 1 as complete.
     *
     * @param i index of task to be marked.
     */
    public String mark(int i) {
        i -= 1;
        try {
            taskList.get(i).complete();
            storage.cleanList();
            storage.saveAll(this);
            return ui.sayMarked(taskList.get(i));
        } catch (IndexOutOfBoundsException x) {
            return ui.handleErrorMessage("absent");
        }
    }

    /**
     * Removes mark from task found at i - 1
     *
     * @param i index of task to be unmarked.
     */
    public String unmark(int i) {
        i -= 1;
        try {
            taskList.get(i).incomplete();
            storage.cleanList();
            storage.saveAll(this);
            return ui.sayUnmarked(taskList.get(i));
        } catch (IndexOutOfBoundsException x) {
            return ui.handleErrorMessage("absent");
        }
    }

    /**
     *  adds tasks from harddisk to this list
     *
     * @param task The task being added from harddisk.
     */
    public void addFromStorage(Task task) {
        taskList.add(task);
    }
}
