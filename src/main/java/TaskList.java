import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;
    private UI ui;
    private Parser parser;
    public TaskList(ArrayList<Task> list) {
        ui = new UI();
        parser = new Parser();
        this.list = list;
    }

    public TaskList() {
        ui = new UI();
        this.list = new ArrayList<>();
    }

    public void addItem(String[] cmd) throws DukeException {
        Task t;

        if (cmd[0].equals("todo")) {
            t = new Todo(cmd[1]);
            list.add(t);
        } else if (cmd[0].equals("deadline")) {
            t = new Deadline(cmd[1], parser.validDate(cmd[2]));
            list.add(t);
        } else {
            t = new Event(cmd[1], cmd[2], cmd[3]);
            list.add(t);
        }

        ui.showAddMsg(t, list.size());
    }

    public void deleteItem(String[] str) throws DukeException {
        int index = Integer.parseInt(str[1]) - 1;

        try {
            list.get(index);
        } catch (IndexOutOfBoundsException  e) {
            throw new DukeException("Index does not exist!");
        }

        ui.showDeleteMsg(list.get(index), list.size());

        list.remove(index);
    }

    public void marked(String[] cmd) throws DukeException {
        int index = Integer.parseInt(cmd[1]);

        index--;

        try {
            list.get(index);
        } catch (IndexOutOfBoundsException  e) {
            throw new DukeException("Index does not exist!");
        }

        if (cmd[0].equals("mark")) {
            ui.showMark(list.get(index).getDesc());
            list.get(index).setCheck(true);
        } else {
            ui.showUnmark(list.get(index).getDesc());
            list.get(index).setCheck(false);
        }
    }

    public ArrayList<Task> get() {
        return list;
    }
}
