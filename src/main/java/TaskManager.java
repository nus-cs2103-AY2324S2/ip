import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> items;

    TaskManager() {
        this.items = new ArrayList<>();
    }

    public Task addTask(Tasks options, String instructions) throws DukeException {
        Task item;
        String name;
        String by;
        String from;
        switch (options) {
            case TODO:
                //Add the todo task
                name = instructions.replaceAll("todo", "").trim();
                if (name.isBlank()) {
                    throw new DukeException("description");
                }
                item = new Todo(name);
                items.add(item);
                return item;
            case DEADLINE:
                //add the deadline task
                if (!instructions.contains("/") || !instructions.contains("by")) {
                    throw new DukeException("dateError");
                }
                by = instructions.split("/")[1].replaceAll("by", "").trim();
                name = instructions.split("/")[0].replaceAll("deadline", "").trim();
                if (by.isBlank()) {
                    throw new DukeException("by");
                } else if (name.isBlank()) {
                    throw new DukeException("description");
                }

                item = new Deadline(name, by);
                items.add(item);
                return item;
            case EVENT:
                if (!instructions.contains("/") || !(instructions.contains("by") && instructions.contains("from"))) {
                    throw new DukeException("dateError");
                }
                from = instructions.split("/")[1].replaceAll("from", "").trim();
                by = instructions.split("/")[2].replaceAll("to", "").trim();
                name = instructions.split("/")[0].replaceAll("event", "").trim();
                if (from.isBlank()) {
                    throw new DukeException("from");
                } else if (by.isBlank()) {
                    throw new DukeException("by");
                } else if (name.isBlank()) {
                    throw new DukeException("description");
                }
                item = new Event(name, from, by);
                items.add(item);
                return item;
            default:
                //old code
                throw new DukeException("Invalid");
        }

    }

    public Task mangeTask(Actions act, String instructions) throws DukeException {
        String[] getNumber = instructions.split(" ");
        if (getNumber.length <= 1 || getNumber[1].isBlank()) {
            throw new DukeException("number");
        }
        int id = Integer.parseInt(getNumber[1]) - 1; //Index 0 based
        Task item = items.get(id);
        switch (act) {
            case UNMARK:
                item.unmark();
                return item;
            case MARK:
                item.markAsDone();
                return item;
            case DELETE:
                //do nothing for now
                return item;
            default:
                //old code
                return item;
        }
    }

    public String numOfTask() {
        return "Now you have " + items.size() + " tasks in the list.";
    }

    public ArrayList<String> ListItems() {
        int i = 1;
        ArrayList<String> ret = new ArrayList<>();
        for (Task item : items) {
            ret.add(" " + i + "." + item);
            i++;
        }
        return ret;
    }


}
