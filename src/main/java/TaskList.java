import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void saveToStorage(Storage storage) throws DukeException {
        storage.saveTasks(tasks);
    }


    public String addToDo(Task t){
        tasks.add(t);
        return "Got it. I've added this task:\n" + "   " + t + "\n"
                + " Now you have " + tasks.size() + " tasks in the list.";
    }

    public String deleteToDo(int taskIndex) throws DukeException{
        if (taskIndex >= 0 && taskIndex < tasks.size()) {

            Task t = this.tasks.remove(taskIndex);

            return "Noted. I've removed this task:\n" + "   " + t + "\n"
                    + " Now you have " + tasks.size() + " tasks in the list.";
        } else {
            throw new DukeException("Task not found.");
        }
    }

    public String toDoList() {
        StringBuilder str;
        if (tasks.isEmpty()) {

            str = new StringBuilder("There are no tasks in the list.");

        } else {

            str = new StringBuilder("Here are the tasks in your list:");
            int taskCount = 1;

            for (Task task : tasks) {
                str.append("\n ").append(taskCount).append(".").append(task);
                taskCount++;
            }
        }

        return str.toString();
    }

    public String markToDo(int taskIndex) throws DukeException{

        if (taskIndex >= 0 && taskIndex < tasks.size()) {

            Task t = this.tasks.get(taskIndex);
            t.setDone(true);

            return "Nice! I've marked this task as done:\n" + "   " + t;
        } else {
            throw new DukeException("Task not found.");
        }
    }

    public String unMarkToDo(int taskIndex) throws DukeException{
        if (taskIndex >= 0 && taskIndex < tasks.size()) {

            Task t = this.tasks.get(taskIndex);
            t.setDone(false);
            return "OK, I've marked this task as not done yet:\n" + "   " + t;

        } else {
            throw new DukeException("Task not found.");
        }
    }

}
