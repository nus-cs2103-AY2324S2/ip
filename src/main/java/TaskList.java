import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> taskList = new ArrayList<>();

    @Override
    public String toString() {
        String out = "";
        int count = 1;

        for (Task currentItem : this.taskList) {
            out += count + "." + currentItem + "\n";
            count++;
        }
        return out.equals("") ? "Looks like you have nothing to do! Yay!\n" : out;
    }

    public void add(Task taskName) {
        this.taskList.add(taskName);
        System.out.println("added: " + taskName);
        System.out.println("Looks like you have " + taskList.size() + " things left to do!");
    }

    public Task getTask(int index) throws DukeException.IllegalParamException {
        try {
            return this.taskList.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException.IllegalParamException("I cant do that! The task does not exist!");
        }

    }

    public void deleteTask(int index) throws DukeException.IllegalParamException {
        try {
            this.taskList.remove(index - 1);
            System.out.println("Looks like you have " + taskList.size() + " things left to do!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException.IllegalParamException("I cant delete that task! It does not exist!");
        }
    }
}
