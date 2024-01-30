public class Task {
    String taskName;
    Boolean done = false;
    public static Task EMPTY_TASK = new Task("");

    public Task(String task) {
        this.taskName = task;
    }
    public String getName() {
        return this.taskName;
    }
    public void mark() {
        this.done = true;
    }
    public void unmark() { this.done = false; }
    public String getRep() {
        // Returns representation of the task (including done)
        String doneChar = (this.done) ? "X" : " ";
        return String.format("[%s] %s", doneChar, this.taskName);
    }
    public String fileRep() {
        return String.format("%s|%d", taskName, done ? 1 : 0);
    }
    public static Task parseTask(String s) {
        String[] arr = s.split("\\|", 3);
        Task task = EMPTY_TASK;
        switch(arr[0]) {
            case "T":
                task = new ToDo(arr[1]);
                break;
            case "D":
                task = new Deadline(arr[1], arr[2].split("\\|", 2)[1]);
                break;
            case "E":
                task = new Event(arr[1], arr[2].split("\\|", 3)[1], arr[2].split("\\|", 3)[2]);
                break;
        }
        if (arr[2].startsWith("1")) {
            task.mark();
        }
        return task;
    }
}
