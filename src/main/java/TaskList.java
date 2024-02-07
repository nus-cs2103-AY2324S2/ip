import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int pos) {
        return tasks.get(pos);
    }

    public void markTaskAsDone(int pos){
        tasks.get(pos - 1).markAsDone();
    }

    public void markTaskAsNotDone(int pos){
        tasks.get(pos - 1).markAsNotDone();
    }

    public void addTodoTask(String description) {
        tasks.add(new Todo(description, false));
    }

    public void addDeadlineTask(String description, String by) {
        tasks.add(new Deadline(description, by, false));
    }

    public void addEventTask(String description, String start, String end) {
        tasks.add(new Event(description, start, end, false));
    }

    public void deleteTask(int pos) {
        tasks.remove(pos - 1);
    }

    public void validateTaskPosition(int pos) throws LiaException {
        if (pos <= 0) {
            throw new LiaException("Enter number from 1 to " + tasks.size() + ".");
        }

        if (pos > tasks.size()) {
            throw new LiaException("There are only " + tasks.size() + " tasks in the list.");
        }
    }

    public Task getLastTask() {
        return tasks.get(tasks.size() - 1);
    }

    public int getSize() {
        return tasks.size();
    }
}