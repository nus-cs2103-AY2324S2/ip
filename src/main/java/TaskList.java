public class TaskList extends Storage<Task> {
    public TaskList(String taskListFilePath) {
        super();
        FileProcess.fileOpen(taskListFilePath);
        final String[] content = FileProcess.fileRead().split(" ");
        for(int i = 0; i < content.length; ++i) {
            switch(content[i]) {
                case "D":
                    super.storeItem(new Deadline(content[i + 2], content[i + 3], content[i + 1].equals("1")));
                    i += 3;
                    break;
                case "E":
                    super.storeItem(
                            new Event(content[i + 2], content[i + 3], content[i + 4], content[i + 1].equals("1")));
                    i += 4;
                    break;
                case "T":
                    super.storeItem(new Todo(content[i + 2], content[i + 1].equals("1")));
                    i += 2;
                    break;
            }
        }
        System.out.println("Data loaded successfully!");
    }
    public TaskList() {
        super();
    }
    private void updateFile() {
        if (FileProcess.fileWrite(super.toFileFormat()) == -1)  {
            System.out.println("Failed to update Task List file!");
        }
    }
    public void checkTask(int idx) {
        Task toCheck = super.getItem(idx);
        if (toCheck != null) {
            toCheck.check();
            final String output = String.format("Nice! I've marked this task as done:\n"+
                    "%s", super.getItem(idx).toString());
            System.out.println(output);
            this.updateFile();
        } else {
            System.out.println("Invalid task index!");
        }
    }
    public void uncheckTask(int idx) {
        Task toUncheck = super.getItem(idx);
        if (toUncheck != null) {
            toUncheck.uncheck();
            final String output = String.format("OK, I've marked this task as not done yet:\n"+
                    "%s", super.getItem(idx).toString());
            System.out.println(output);
            this.updateFile();
        } else {
            System.out.println("Invalid task index!");
        }
    }
    public void listItem() {
        if (super.getSize() == 0) {
            System.out.println("YAY! You have no tasks ongoing ^_^");
        } else {
            System.out.println("Here are the tasks in your list:");
            System.out.println(super.toString());
        }
    }
    @Override
    public int deleteItem(int idx) {
        Task toBeDeleted = super.getItem(idx);
        if (toBeDeleted == null) {
            return -1;
        } else {
            if (super.deleteItem(idx) == 0) {
                System.out.printf("Noted. I've removed this task:\n%s\n", toBeDeleted);
                this.updateFile();
                return 0;
            } else {
                System.out.println("Invalid task index!");
                return -1;
            }
        }
    }
    @Override
    public void storeItem(Task task) {
        super.storeItem(task);
        final String output = String.format("Got it. I've added this task:\n"
                + "    %s\n"
                + "Now you have %d tasks in the list.", task.toString(), super.getSize());
        System.out.println(output);
        this.updateFile();
    }
}
