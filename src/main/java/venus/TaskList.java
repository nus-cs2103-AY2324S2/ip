package venus;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * This is a TaskList class that is used to save tasks.
 *
 * @author peterXGD
 * @since 2024-02-05
 */
public class TaskList {
    /**
     * Enums that are used to identify Tasks
     */
    public enum Types { //Used for type of list encountered
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, ALL, INVALID, FIND;
    }
    private ArrayList<Task> tasks;
    public TaskList() {
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
     * Returns tasks stored as ArrayList in TaskList.
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
        Types type;
        try {
            type = Parser.findType(word);
        } catch (IllegalArgumentException e) {
            OldUi.formatResponse("Input task type invalid, please try again");
            type = Types.INVALID;
        }

        switch (type) {
        case ALL:
            OldUi.printList(this);
            break;
        case MARK:
            try {
                int index = Parser.findMarkIndex(word);
                data.get(index).mark();
                OldUi.formatMark(data.get(index));
            } catch (StringIndexOutOfBoundsException e) {
                OldUi.formatResponse("Incorrect name or spelling for mark, please check");
            } catch (NumberFormatException e) {
                OldUi.formatResponse("Incorrect arguments for mark, please check");
            }
            break;
        case UNMARK:
            try {
                int index = Parser.findUnmarkIndex(word);
                data.get(index).unmark();
                OldUi.formatUnmark(data.get(index));
            } catch (StringIndexOutOfBoundsException e) {
                OldUi.formatResponse("Incorrect name or spelling for unmark, please check");
            } catch (NumberFormatException e) {
                OldUi.formatResponse("Incorrect arguments for unmark, please check");
            }
            break;
        case TODO:
            try {
                String todoMessage = Parser.findTodoContent(word);
                Todo todo = new Todo(todoMessage);
                data.add(todo);
                OldUi.formatTask(todo, data.size());
            } catch (StringIndexOutOfBoundsException e) {
                OldUi.formatResponse("Incorrect arguments for todo, please check");
            }
            break;
        case DEADLINE:
            try {
                String[] deadlineParts = Parser.findDeadlineContent(word);
                Deadline deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                data.add(deadline);
                OldUi.formatTask(deadline, data.size());
            } catch (Exception e) {
                OldUi.formatResponse(e.getMessage());
            }
            break;
        case EVENT:
            try {
                String[] eventParts = Parser.findEventParts(word);
                Event event = new Event(eventParts[0], eventParts[1], eventParts[2]);
                data.add(event);
                OldUi.formatTask(event, data.size());
            } catch (DukeException e) {
                OldUi.formatResponse(e.getMessage());
            }
            break;
        case DELETE:
            try {
                int index = Parser.findDeleteIndex(word);
                OldUi.formatDelete(data.get(index), data.size());
                data.remove(index);
            } catch (StringIndexOutOfBoundsException e) {
                OldUi.formatResponse("Incorrect name or spelling for delete, please check");
            } catch (NumberFormatException e) {
                OldUi.formatResponse("Incorrect arguments for delete, please check");
            }
            break;
        case INVALID:
            break;
        case FIND:
            try {
                String keyword = Parser.findFindKeyword(word);
                ArrayList<String> items = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getItem().contains(keyword)) {
                        items.add(data.get(i).toString());
                    }
                }
                OldUi.printFind(items);
            } catch (StringIndexOutOfBoundsException e) {
                OldUi.formatResponse("Incorrect argument for find, please check.");
            }
            break;
        default:
            OldUi.formatResponse("Unknown command, please try again");
        }
    }

    /**
     * Set tasks like setTasks and return the message as string for GUI.
     * @param word Input string to be used to set Tasks in TaskList.
     * @return a string that is returned to the user.
     */
    public String setTasksReturnMessage(String word) {
        ArrayList<Task> data = this.tasks;
        Types type;
        String output = "";
        try {
            type = Parser.findType(word);
        } catch (IllegalArgumentException e) {
            output = Gui.formatResponse("Input task type invalid, please try again");
            return output;
        }
        switch (type) {
        case ALL:
            output = Gui.allTasksMessage(this);
            break;
        case MARK:
            try {
                int index = Parser.findMarkIndex(word);
                data.get(index).mark();
                output = Gui.markMessage(data.get(index));
            } catch (StringIndexOutOfBoundsException e) {
                output = Gui.formatResponse("Incorrect name or spelling for mark, please check");
            } catch (NumberFormatException e) {
                output = Gui.formatResponse("Incorrect arguments for mark, please check");
            }
            break;
        case UNMARK:
            try {
                int index = Parser.findUnmarkIndex(word);
                data.get(index).unmark();
                output = Gui.unmarkMessage(data.get(index));
            } catch (StringIndexOutOfBoundsException e) {
                output = Gui.formatResponse("Incorrect name or spelling for unmark, please check");
            } catch (NumberFormatException e) {
                output = Gui.formatResponse("Incorrect arguments for unmark, please check");
            }
            break;
        case TODO:
            try {
                String todoMessage = Parser.findTodoContent(word);
                Todo todo = new Todo(todoMessage);
                data.add(todo);
                output = Gui.addTaskMessage(todo, data.size());
            } catch (StringIndexOutOfBoundsException e) {
                output = Gui.formatResponse("Incorrect arguments for todo, please check");
            }
            break;
        case DEADLINE:
            try {
                String[] deadlineParts = Parser.findDeadlineContent(word);
                Deadline deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                data.add(deadline);
                output = Gui.addTaskMessage(deadline, data.size());
            } catch (Exception e) {
                output = Gui.formatResponse(e.getMessage());
            }
            break;
        case EVENT:
            try {
                String[] eventParts = Parser.findEventParts(word);
                Event event = new Event(eventParts[0], eventParts[1], eventParts[2]);
                data.add(event);
                output = Gui.addTaskMessage(event, data.size());
            } catch (DukeException e) {
                output = Gui.formatResponse(e.getMessage());
            }
            break;
        case DELETE:
            try {
                int index = Parser.findDeleteIndex(word);
                output = Gui.deleteTaskMessage(data.get(index), data.size());
                data.remove(index);
            } catch (StringIndexOutOfBoundsException e) {
                output = Gui.formatResponse("Incorrect name or spelling for delete, please check");
            } catch (NumberFormatException e) {
                output = Gui.formatResponse("Incorrect arguments for delete, please check");
            }
            break;
        case FIND:
            try {
                String keyword = Parser.findFindKeyword(word);
                ArrayList<String> items = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getItem().contains(keyword)) {
                        items.add(data.get(i).toString());
                    }
                }
                output = Gui.findMessage(items);
            } catch (StringIndexOutOfBoundsException e) {
                output = Gui.formatResponse("Incorrect argument for find, please check.");
            }
            break;
        default:
            output = Gui.formatResponse("Unknown command, please try again");
        }
        return output;
    }
}
