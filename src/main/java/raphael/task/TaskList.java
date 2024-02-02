package raphael.task;
import java.util.ArrayList;
import raphael.format.FileFormattable;
import raphael.exception.DukeException;
import raphael.format.Formatter;
public class TaskList implements FileFormattable {
    private final ArrayList<Task> tasks;
    public TaskList(String tasks) throws DukeException {
        this.tasks = new ArrayList<>();
        final String[] tasksArr = tasks.split("\n");
        for (String task : tasksArr) {
            final String[] taskArr = task.split(" \\|&\\| ");
            switch (taskArr[0]) {
                case "T":
                    this.tasks.add(new Todo(taskArr[2], taskArr[1].equals("1")));
                    break;
                case "D":
                    this.tasks.add(new Deadline(taskArr[2], taskArr[3], taskArr[1].equals("1")));
                    break;
                case "E":
                    this.tasks.add(new Event(taskArr[2], taskArr[3],
                            taskArr[4], taskArr[1].equals("1")));
                    break;
            }
        }
    }
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public String getSize() {
        return String.format("You now have %d tasks in your list!", this.tasks.size());
    }
    public int checkTask(int idx) throws DukeException {
        if (idx < 0 || idx >= this.tasks.size()) {
            throw new DukeException(DukeException.INVALID_TASK_INDEX);
        } else {
            if(this.tasks.get(idx).check() == 0) {
                System.out.printf("Hooray! Congrats on completing the following task!:\n"
                        + "\t%s\n", this.tasks.get(idx));
                return 0;
            } else {
                System.out.printf("Hmm. You seems to have completed the task:\n"
                        + "\t%s\n", this.tasks.get(idx));
                return -1;
            }
        }
    }
    public int uncheckTask(int idx) throws DukeException {
        if (idx < 0 || idx >= this.tasks.size()) {
            throw new DukeException(DukeException.INVALID_TASK_INDEX);
        } else {
            if(this.tasks.get(idx).uncheck() == 0) {
                System.out.printf("Uh oh! Workload + 1 by having the following task:\n"
                        + "\t%s\n", this.tasks.get(idx));
                return 0;
            } else {
                System.out.printf("Hmm. You seems to have not complete the task before:\n"
                        + "\t%s\n", this.tasks.get(idx));
                return -1;
            }
        }
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }
    public int deleteTask(int idx) throws DukeException {
        if (idx < 0 || idx >= this.tasks.size()) {
            throw new DukeException(DukeException.INVALID_TASK_INDEX);
        } else {
            Task temp = this.tasks.get(idx);
            this.tasks.remove(idx);
            System.out.printf("Alrigthy! I have deleted the following task for you:\n"
                    + "\t%s\n", temp);
            System.out.println(this.getSize());
            return 0;
        }
    }
    public void listTasks() {
        if (this.tasks.isEmpty()) {
            System.out.println("YAY! You have no tasks ongoing ^_^");
        } else {
            System.out.println("Here are the tasks in your list:");
            System.out.println(this);
        }
    }
    @Override
    public String toString() {
        String res = "";
        for(int i = 0; i < this.tasks.size(); ++i) {
            if (i == 0) {
                res = Formatter.addIndex(this.tasks.get(i), i + 1);
            } else {
                res = String.format("%s\n%s", res, Formatter.addIndex(this.tasks.get(i), i + 1));
            }
        }
        return res;
    }

    @Override
    public String toFileFormat() {
        String fileFormat = "";
        for(Task task : this.tasks) {
            if (fileFormat.isEmpty()) {
                fileFormat = task.toFileFormat();
            } else {
                fileFormat = String.format("%s\n%s", fileFormat, task.toFileFormat());
            }
        }
        return fileFormat;
    }
}