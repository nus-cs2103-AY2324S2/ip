package LetoTasks;

public class TaskListCommands {
    private static final Task[] list = new Task[100];
    private static int taskNextIndex = 0;

    public TaskListCommands() {}

    public static void addToList(String inputs) {
        try {
            Task t = null;
            String typeOfTask = inputs.split(" ")[0];
            switch (typeOfTask.toLowerCase()) {
                case "event":
                    t = Event.EventFactory(inputs);
                    break;
                case "deadline":
                    t = Deadline.DeadlineFactory(inputs);
                    break;
                case "todo":
                    t = new Todo(inputs);
                    break;
            } // end switch for type of task
            if (t == null) {
                throw new LetoInvalidTaskException("This task does not fit known tasks (event, deadline, todo)");
            }
            TaskListCommands.list[TaskListCommands.taskNextIndex] = t;
            TaskListCommands.taskNextIndex++;
            System.out.println("  << Duke Leto >>\n  > LetoTasks.Task added, " + t.toString() + "\n  > You have " + TaskListCommands.taskNextIndex + " tasks.");
        } catch (LetoInvalidTaskException e) {
            e.printException();
        }
    }

    public static void markTaskCompleted(String inputs) {
        Task temp;
        try {
            int index = getIndexFromInput(inputs);
            temp = TaskListCommands.list[index];
            if (temp == null) {
                throw new LetoInvalidTaskException("Task is null, try creating a task first!");
            }
            if (temp.isCompleted()) {
                throw new LetoInvalidTaskException("Task already completed");
            } else {
                temp.markCompleted();
                System.out.println("  << Duke Leto >>\n  > LetoTasks.Task marked as completed! Congratulations");
            }
        } catch (LetoInvalidTaskException e) {
            e.printException();
        }
    }

    public static void markTaskUncompleted(String inputs) {
        Task temp;
        try {
            int index = getIndexFromInput(inputs);
            temp = TaskListCommands.list[index];
            if (temp == null) {
                throw new LetoInvalidTaskException("Task is null, try creating a task first!");
            }
            if (!temp.isCompleted()) {
                throw new IndexOutOfBoundsException("Task is already not completed (╬▔皿▔)╯");
            } else {
                temp.markUncompleted();
                System.out.println("  << Duke Leto >>\n  > LetoTasks.Task marked as uncompleted! Things happen, dont worry we account for it");
            }
        } catch (LetoInvalidTaskException e) {
            e.printException();
        }
    }

    private static int getIndexFromInput(String input) throws LetoInvalidTaskException {
        try {
            String[] inputs = input.split(" ");
            if (inputs.length != 2) {
                throw new LetoInvalidTaskException("You need to enter a task index number");
            }
            int i = Integer.parseInt(inputs[1]) - 1;
            if (TaskListCommands.taskNextIndex == 0) {
                throw new LetoInvalidTaskException("Good news at least, you have no task!");
            }
            if (i >= TaskListCommands.taskNextIndex || i < 0) {
                throw new LetoBadTaskIndexException(TaskListCommands.taskNextIndex);
            }
            return i;
        } catch (NumberFormatException e) {
            throw new LetoInvalidTaskException("We cannot get task index from input");
        }
    }

    public static void printList() {
        System.out.println("  << Duke Leto >> ");
        System.out.println("  ┌ < LetoTasks.Task list >");
        for (int i = 0; i < taskNextIndex; i++) {
            System.out.println("  ├ " + (i+1) + ": " + TaskListCommands.list[i].toString());
        }
        System.out.println("  └─ end of list");
    }
}
