package raphael.task;
import java.util.ArrayList;
import raphael.format.FileFormattable;
import raphael.format.Formatter;
import raphael.exception.RaphaelException;
public class TaskList implements FileFormattable {
    private final ArrayList<Task> tasks;
    public TaskList(String tasks) throws RaphaelException {
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
    public String checkTask(int idx) throws RaphaelException {
        if (idx < 0 || idx >= this.tasks.size()) {
            throw new RaphaelException(RaphaelException.TYPE.INVALID_TASK_INDEX);
        } else {
            if(this.tasks.get(idx).check() == 0) {
                return String.format("Hooray! Congrats on completing the following task!:\n"
                        + "\t%s\n", this.tasks.get(idx));
            } else {
                return String.format("Hmm. You seems to have completed the task:\n"
                        + "\t%s\n", this.tasks.get(idx));
            }
        }
    }
    public String uncheckTask(int idx) throws RaphaelException {
        if (idx < 0 || idx >= this.tasks.size()) {
            throw new RaphaelException(RaphaelException.TYPE.INVALID_TASK_INDEX);
        } else {
            if(this.tasks.get(idx).uncheck() == 0) {
                return String.format("Uh oh! Workload + 1 by having the following task:\n"
                        + "\t%s\n", this.tasks.get(idx));
            } else {
                return String.format("Hmm. You seems to have not complete the task before:\n"
                        + "\t%s\n", this.tasks.get(idx));
            }
        }
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }
    public Task deleteTask(int idx) throws RaphaelException {
        if (idx < 0 || idx >= this.tasks.size()) {
            throw new RaphaelException(RaphaelException.TYPE.INVALID_TASK_INDEX);
        } else {
            Task temp = this.tasks.get(idx);
            this.tasks.remove(idx);
            return temp;
        }
    }
    public String listTasks() {
        if (this.tasks.isEmpty()) {
            return "YAY! You have no tasks ongoing ^_^";
        } else {
            return String.format("Here are the tasks in your list:\n%s", this);
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