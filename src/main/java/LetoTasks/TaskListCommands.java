package LetoTasks;

import java.util.ArrayList;

public class TaskListCommands {
    private static final ArrayList<Task> list = new ArrayList<>(100);
//    private static int taskNextIndex = 0;

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
            TaskListCommands.list.add(t);
//            TaskListCommands.list[TaskListCommands.taskNextIndex] = t;
//            TaskListCommands.taskNextIndex++;
            System.out.println("  << Duke Leto >>\n  > Task added, [" + t.toString() +
                    "]\n  > You have " + TaskListCommands.list.size() + " tasks.");
        } catch (LetoInvalidTaskException e) {
            e.printException();
        }
    }

    public static void markTaskCompleted(String inputs) {
        Task temp;
        try {
            int index = getIndexFromInput(inputs);
//            temp = TaskListCommands.list[index];
            temp = TaskListCommands.list.get(index);
            if (temp == null) {
                // should never happen
                throw new LetoInvalidTaskException("WARNING Task is null, try creating a task first!");
            }
            if (temp.isCompleted()) {
                throw new LetoInvalidTaskException("Task already completed");
            } else {
                temp.markCompleted();
                System.out.println("  << Duke Leto >>\n  > Task marked as completed! Congratulations");
            }
        } catch (LetoInvalidTaskException e) {
            e.printException();
        }
    }

    public static void markTaskUncompleted(String inputs) {
        Task temp;
        try {
            int index = getIndexFromInput(inputs);
//            temp = TaskListCommands.list[index];
            temp = TaskListCommands.list.get(index);
            if (temp == null) {
                // should not happen btw
                throw new LetoInvalidTaskException("WARNING Task is null, try creating a task first!");
            }
            if (!temp.isCompleted()) {
                throw new IndexOutOfBoundsException("Task is already not completed (╬▔皿▔)╯");
            } else {
                temp.markUncompleted();
                System.out.println("  << Duke Leto >>\n  > Task marked as uncompleted! Things happen, dont worry we account for it");
            }
        } catch (LetoInvalidTaskException e) {
            e.printException();
        }
    }

    public static void deleteTask(String inputs) {
        try {
            int index = getIndexFromInput(inputs);
            Task t = TaskListCommands.list.get(index);
            TaskListCommands.list.remove(index);
            System.out.println("  << Duke Leto >>\n  > Task deleted, [" + t.toString() +
                    "]\n  > You have " + TaskListCommands.list.size() + " tasks.");
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
            if (TaskListCommands.list.isEmpty()) {
                throw new LetoInvalidTaskException("Good news at least, you have no task!");
            }
            if (i >= TaskListCommands.list.size() || i < 0) {
                throw new LetoBadTaskIndexException(TaskListCommands.list.size());
            }
            return i;
        } catch (NumberFormatException e) {
            throw new LetoInvalidTaskException("We cannot get task index from your input, it should be an integer, `(un)mark _int_`");
        }
    }

    public static void printList() {
        System.out.println("  << Duke Leto >> ");
        System.out.println("  ┌ < Task list >");
        for (int i = 0; i < TaskListCommands.list.size(); i++) {
            System.out.println("  ├ " + (i+1) + ": " + TaskListCommands.list.get(i).toString());
        }
        System.out.println("  └─ end of list");
    }
}
