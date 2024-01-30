public class Tasks {
    private static final int MAX_TASKS =100;
    private static Task[] tasks;
    private static int taskCount;

    public Tasks() {
        tasks = new Task[MAX_TASKS];
        taskCount = 0;
    }

    public static boolean isFull() {
        return taskCount >= MAX_TASKS;
    }

    public void markTask(String command) {
        String[] words = command.split(" ");
        int taskId = Integer.parseInt(words[1]) -1;
        Task t = tasks[taskId];
        tasks[taskId].markAsDone();
        System.out.println(" Nice ! I've marked this task as done: \n" + t.toString());
    }

    public  void unmarkTask(String command) {
        String[] words = command.split(" ");
        int taskId = Integer.parseInt(words[1]) - 1;
        Task t = tasks[taskId];
        t.markAsUndone();
        System.out.println(" Ok, I've marked this task as not done yet: \n" + t);
    }

    public void addTask(String command) {
        Task t;
        switch (getTaskType(command)) {
            case "todo":
                t = new Todo(command.substring(5));
                break;
            case "deadline":
                t = createDeadline(command.substring(9));
                break;
            case "event":
                t = createEvent(command.substring(6));
                break;
            default:
                System.out.println(" Invalid command. Please use 'todo', 'deadline' or 'event'. ");
                return;
        }

        if (! Tasks.isFull()) {
            tasks[taskCount++] = t;
            System.out.println(" Got it. I've added this task: \n" + t);
            System.out.println(" Now you have " + taskCount + " tasks in the list. ");
        } else {
            System.out.println(" Task list is full.");
        }
    }
    public void listOutTasks() {
        if (taskCount == 0) {
            System.out.println(" No task added");
        } else {
            System.out.println(" Here are the tasks in your list: ");
            for(int i = 0; i < taskCount; i++) {
                int seq = i+1;
                Task t = tasks[i];
                System.out.println(" " + seq + ". " + t.toString());
            }
        }
    }
    private String getTaskType(String command) {
        return command.trim().split("\\s+")[0].toLowerCase();
    }

    private static Deadline createDeadline(String command) {
        String[] parts = command.split("/by ");
        String taskDescription = parts[0].trim();
        String by = parts[1].trim();
        return new Deadline(taskDescription, by);
    }

    private static Event createEvent(String command) {
        String[] parts = command.split("/from ");
        String taskDescription = parts[0].trim();
        String[] eventDetails = parts[1].split("/to ");
        String from = eventDetails[0].trim();
        String to = eventDetails[1].trim();
        return  new Event(taskDescription, from, to);
    }

}
