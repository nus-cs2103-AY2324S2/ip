package venus;

/**
 * This is a TaskList class that is used to save tasks.
 *
 * @author peterXGD
 * @since 2024-02-05
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TaskList {

    /**
     * Enums that are used to identify Tasks
     */
    public enum TYPES { //Used for type of list encountered
        List, Todo, Deadline, Event, Mark, Unmark, Delete, All, Invalid;
    }
    private ArrayList<Task> tasks;
    public TaskList () {
        this.tasks = new ArrayList<>();
    }

    /**
     * Loads task into TaskList instance with a Storage with the right path.
     *
     * @param storage Storage with the correct relative data path.
     * @throws FileNotFoundException Throws exception when the file cannot be found.
     */
    public void loadTasks(Storage storage) throws FileNotFoundException {
        storage.loadFile(tasks);
    }

    /**
     * Save task into data file with a Storage with the right path.
     *
     * @param storage Storage with the correct relative data path.
     * @throws FileNotFoundException Throws exception when the file cannot be found.
     */
    public void saveTasks(Storage storage) throws FileNotFoundException {
        storage.saveAllFile(tasks);
    }

    /**
     * Returns tasks stored as ArrayList<Task> in TaskList.
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Sets the tasks in TaskList according to input in form of string.
     *
     * @param word Input string to be used to set Tasks in TaskList.
     */
    public void setTasks(String word) {
        ArrayList<Task> data = this.tasks;
        TaskList.TYPES type;
        try {
            type = Parser.findType(word);
        } catch (IllegalArgumentException e) {
            Ui.formatResponse("Input task type invalid, please try again");
            type = TYPES.Invalid;
        }

        switch (type) {
            case All:
                Ui.printList(this);
                break;
            case Mark:
                try {
                    int index = Parser.findMarkIndex(word);
                    data.get(index).mark();
                    Ui.formatMark(data.get(index));
                } catch (StringIndexOutOfBoundsException e) {
                    Ui.formatResponse("Incorrect name or spelling for mark, please check");
                } catch (NumberFormatException e) {
                    Ui.formatResponse("Incorrect arguments for mark, please check");
                }
                break;
            case Unmark:
                try {
                    int index = Parser.findUnmarkIndex(word);
                    data.get(index).unmark();
                    Ui.formatUnmark(data.get(index));
                } catch (StringIndexOutOfBoundsException e) {
                    Ui.formatResponse("Incorrect name or spelling for unmark, please check");
                } catch (NumberFormatException e) {
                    Ui.formatResponse("Incorrect arguments for unmark, please check");
                }
                break;
            case Todo:
                try {
                    String todoMessage = Parser.findTodoContent(word);
                    Todo todo = new Todo(todoMessage);
                    data.add(todo);
                    Ui.formatTask(todo, data.size());
                } catch (StringIndexOutOfBoundsException e) {
                    Ui.formatResponse("Incorrect arguments for todo, please check");
                }
                break;
            case Deadline:
                try {
                    String[] deadlineParts = Parser.findDeadlineContent(word);
                    Deadline deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                    data.add(deadline);
                    Ui.formatTask(deadline, data.size());
                } catch (Exception e) {
                    Ui.formatResponse(e.getMessage());
                }
                break;
            case Event:
                try {
                    String[] eventParts = Parser.findEventParts(word);
                    Event event = new Event(eventParts[0], eventParts[1], eventParts[2]);
                    data.add(event);
                    Ui.formatTask(event, data.size());
                } catch (DukeException e) {
                    Ui.formatResponse(e.getMessage());
                }
                break;
            case Delete:
                try {
                    int index = Parser.findDeleteIndex(word);
                    Ui.formatDelete(data.get(index), data.size());
                    data.remove(index);
                } catch (StringIndexOutOfBoundsException e) {
                    Ui.formatResponse("Incorrect name or spelling for delete, please check");
                } catch (NumberFormatException e) {
                    Ui.formatResponse("Incorrect arguments for delete, please check");
                }
                break;
            case Invalid:
                break;
            default:
                Ui.formatResponse("Unknown command, please try again");
            }
    }
}
