package fishstock;

import java.util.ArrayList;
import fishstock.FishStock.Keyword;

class TaskList {
    protected final ArrayList<Task> list;
    protected TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    protected TaskList() {
        this.list = new ArrayList<>();
    }

    protected int getSize() {
        return list.size();
    }

    protected void printTasks() {
        for (int i = 0; i < list.size(); i++) {
            Ui.printMsg((i + 1) + "." + list.get(i));
        }
    }

    protected Task changeMark(Keyword keyword, String input) throws FishStockException {
        Integer idx = Parser.getTaskFromIndex(input);
        try {
            Task task = list.get(idx);
            if (keyword == Keyword.MARK) {
                task.markAsDone();
            } else if (keyword == Keyword.UNMARK) {
                task.markAsUndone();
            }
            return task;

        } catch (IndexOutOfBoundsException e) {
            throw new FishStockException("OH NOSE! Task number must be in valid range..");
        }
    }

    protected Task deleteTask(String input) throws FishStockException {
        Integer idx = Parser.getTaskFromIndex(input);
        try {
            Task task = list.get(idx);
            list.remove(task);
            return task;

        } catch (IndexOutOfBoundsException e) {
            throw new FishStockException("OH NOSE! Task number must be in valid range..");
        }
    }

    protected Task addTask(Keyword keyword, String input) throws FishStockException {
        Task task = null;
        switch (keyword) {
        case TODO:
            task = Todo.of(input);
            break;
        case DEADLINE:
            task = Deadline.of(input);
            break;
        case EVENT:
            task = Event.of(input);
            break;
        }
        list.add(task);
        return task;
    }
}
