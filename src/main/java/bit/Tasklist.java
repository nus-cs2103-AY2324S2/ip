package bit;

import java.util.ArrayList;
public class Tasklist {
    private ArrayList<Task> taskList = new ArrayList<>();
    private Ui UI;


    public Tasklist() {
        this.UI = new Ui();
    }

    /**
     * Return current size of taskList
     * @return size of tasklist
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Return task stored at index i of list
     * @param i the index of the task to be retrieved
     * @return task stored at i
     */

    public Task getTask(int i) {
        return taskList.get(i);
    }

    /**
     * Adds tasks to the list. Takes in String that it parses. Will throw various errors based on input.
     * It accepts todds, deadlines and events.
     * @param input String commmand from user
     * @throws DukeException
     */
    public  void addTo(String input) throws DukeException {
        if(input.startsWith("todo")) {

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
                UI.sayAdded(i,"todo", t);


            } catch(ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Hmmm, that todo is empty!");
            }


        } else if (input.contains("event ")) {
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
                UI.sayAdded(i, "event", taskList.get(i - 1));



            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Did you make a mistake?");

            }

        } else if (input.startsWith("deadline")) {
            try {
                String[] parts = input.split(" ", 2);
                if (!(parts[0].equals("deadline"))) {
                    throw new DukeException("Is that a typo I see?");
                }
                if (parts[0].equals("deadline")) {
                    String[] compo = parts[1].split("/by");
                    if (compo[0].trim().isEmpty() || compo[1].trim().isEmpty()) {
                        throw new DukeException("Did you miss something?");
                    }
                    Deadline d = new Deadline(compo[0], compo[1]);
                    if (!d.getValid()) {
                        UI.handleErrorMessage("NotaDate");
                        return;
                    }
                    taskList.add(d);
                    int i = taskList.size();
                    UI.sayAdded(i, "deadline", taskList.get(i - 1));
                }
            } catch (ArrayIndexOutOfBoundsException x) {
                throw new DukeException("Did you miss something?");
            }

        } else {
            UI.handleErrorMessage("");
            return;
        }




    }

    /**
     * Deletes task found at i - 1. This means the very first task is deleted when i = 1.
     * @param i index number of task to be deleted.
     */
    public  void delete(int i) {
        try {
            i -= 1;
            String s = taskList.get(i).toString();
            taskList.remove(i);
            UI.sayDeleted(s);
        } catch (IndexOutOfBoundsException x) {
            UI.handleErrorMessage("absent");
        }
    }

    /**
     * Mark task found at i - 1 as complete.
     * @param i index of task to be marked.
     */
    public void mark(int i) {
        i -= 1;
        try {
            taskList.get(i).complete();
            UI.sayMarked(taskList.get(i));
        } catch (IndexOutOfBoundsException x) {
            UI.handleErrorMessage("absent");
        }
    }

    /**
     *Remove mark from task found at i - 1
     * @param i index of task to be unmarked.
     */
    public  void unmark(int i) {
        i -= 1;
        try {
            taskList.get(i).incomplete();
            UI.sayUnmarked(taskList.get(i));
        } catch (IndexOutOfBoundsException x) {
            UI.handleErrorMessage("absent");
        }
    }

    /**
     * Used by main to add tasks from harddisk to this list
     * @param task The task being added from harddisk.
     */
    public void  addFromStorage(Task task) {
        taskList.add(task);
    }

}
