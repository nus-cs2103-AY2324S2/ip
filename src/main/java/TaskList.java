import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(String tasks) {
        this.tasks = new ArrayList<>();
        final String[] tasksArr = tasks.split(",");
        for(int i = 0; i < tasksArr.length; ++i) {
            switch (tasksArr[i]) {
                case "T":
                    this.tasks.add(new Todo(tasksArr[i + 2], tasksArr[i + 1].equals("1")));
                    i += 2;
                    break;
                case "D":
                    this.tasks.add(new Deadline(tasksArr[i + 2], tasksArr[i +3], tasksArr[i + 1].equals("1")));
                    i += 3;
                    break;
                case "E":
                    this.tasks.add(new Event(tasksArr[i + 2], tasksArr[i + 3],
                            tasksArr[i + 4], tasksArr[i + 1].equals("1")));
                    i += 4;
            }
        }
    }
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    private String getSize() {
        return String.format("You now have %d tasks in your list!", this.tasks.size());
    }
//    private void updateFile() {
//        if (FileProcess.fileWrite(super.toFileFormat()) == -1)  {
//            System.out.println("Failed to update Task List file!");
//        }
//    }
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
            return this.tasks.get(idx).uncheck();
        }
    }

    public void addTask(Task t) {
        this.tasks.add(t);
        System.out.printf("Roger that! I have added the following task into your list:\n" +
                "\t%s\n", t);
        System.out.println(this.getSize());
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
            System.out.println(this.toString());
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
}