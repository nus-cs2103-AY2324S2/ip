package venus;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TaskList {

    public enum Types { //Used for type of list encountered
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, ALL, INVALID, FIND;
    }
    private ArrayList<Task> tasks;
    public TaskList () {
        this.tasks = new ArrayList<>();
    }

    public void loadTasks(Storage storage) throws FileNotFoundException {
        storage.loadFile(tasks);
    }

    public void saveTasks(Storage storage) throws FileNotFoundException {
        storage.saveAllFile(tasks);
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(String word) {
        ArrayList<Task> data = this.tasks;
        Types type;
        try {
            type = Parser.findType(word);
        } catch (IllegalArgumentException e) {
            Ui.formatResponse("Input task type invalid, please try again");
            type = Types.INVALID;
        }

        switch (type) {
        case ALL:
            Ui.printList(this);
            break;
        case MARK:
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
        case UNMARK:
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
        case TODO:
            try {
                String todoMessage = Parser.findTodoContent(word);
                Todo todo = new Todo(todoMessage);
                data.add(todo);
                Ui.formatTask(todo, data.size());
            } catch (StringIndexOutOfBoundsException e) {
                Ui.formatResponse("Incorrect arguments for todo, please check");
            }
            break;
        case DEADLINE:
            try {
                String[] deadlineParts = Parser.findDeadlineContent(word);
                Deadline deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                data.add(deadline);
                Ui.formatTask(deadline, data.size());
            } catch (Exception e) {
                Ui.formatResponse(e.getMessage());
            }
            break;
        case EVENT:
            try {
                String[] eventParts = Parser.findEventParts(word);
                Event event = new Event(eventParts[0], eventParts[1], eventParts[2]);
                data.add(event);
                Ui.formatTask(event, data.size());
            } catch (DukeException e) {
                Ui.formatResponse(e.getMessage());
            }
            break;
        case DELETE:
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
                Ui.printFind(items);
            } catch (StringIndexOutOfBoundsException e) {
                Ui.formatResponse("Incorrect argument for find, please check.");
            }
            break;
        default:
            Ui.formatResponse("Unknown command, please try again");
        }

    }
}
