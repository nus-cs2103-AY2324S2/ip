import javax.xml.crypto.Data;
import java.util.ArrayList;

public class TaskList {
    private Ui ui;
    private Database database;
    protected ArrayList<Task> taskList;

    public TaskList(Database database) {
        taskList = new ArrayList<>();
        ui = new Ui();
        this.database = database;
    }

    public Task get(int index) {
        return taskList.get(index -1);
    }

    public void setDone(int index) {
        Task curr = taskList.get(index);
        curr.isDone = true;
        ui.setDoneMessage(curr);
    }

    public void setNotDone(int index) {
        Task curr = taskList.get(index);
        curr.isDone = false;
        ui.setNotDoneMessage(curr);
    }

    public void addTask(Task task) throws ArrayIndexOutOfBoundsException {
        taskList.add(task);
        if (!Duke.initialize) {
            ui.addTask(task);
            getNumberOfTasks();
        }
    }

    public void removeTask(String description) {
        String[] tokens = description.split(" ");
        if (tokens.length == 1)
            throw new ArrayIndexOutOfBoundsException("Please enter the index of the task to be deleted.");
        int index = Integer.parseInt(tokens[1]);
        Task curr = taskList.remove(index-1);
        database.deleteLine(index);
        ui.removeTaskMessage(curr);
        getNumberOfTasks();
    }

    public void listTask() {
        for (int i = 0; i < taskList.size(); i++) {
            ui.listTaskMessage(i+1, taskList.get(i));
        }
    }

    public void getNumberOfTasks() {
        ui.getNumberOfTasksMessage(taskList.size());
    }
}
