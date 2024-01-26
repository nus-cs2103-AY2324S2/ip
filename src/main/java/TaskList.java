import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private TaskListFileHandler database;
    private String filepath = Duke.DBPATH;

    // Todo representation - 0 means not done, 1 means done
    // T | done? | desc

    // Deadline representation
    // D | done? | desc | by

    // Event representation
    // E | done? | desc | from | to

    public TaskList() {
//        String projectRoot = System.getProperty("user.dir");
//        this.filepath = projectRoot + "/data/duke.txt";
        this.database = new TaskListFileHandler(filepath);
    }

    // Add a task to the task list
    public void addTask(Task task) {
        // Based on task type, extract traits
        String dbTask = TaskList.task2Db(task);
        try {
            this.database.appendToFile(dbTask);
        } catch (IOException e) {
            System.out.println("Failed to add task: " + e.getMessage());
        }
    }

    // Get a task from the task list, given its line number index
    public Task getTask(int index) {
        try {
            String line = this.database.readLineFromFile(index);
            return TaskList.db2Task(line);
        } catch (IOException e) {
            System.out.println("Failed to get task: " + e.getMessage());
        }
        return null;
    }

    // Delete a task from the task list, given its index
    public void deleteTask(int index) {
        try {
            this.database.deleteLineFromFile(index);
        } catch (IOException e) {
            System.out.println("Failed to delete task: " + e.getMessage());
        }
    }

    /**
     * markTask marks a task as completed by line number
     *
     * @param index
     */
    public void markTask(int index) {
        try {
            String line = this.database.readLineFromFile(index);
            Task t = TaskList.db2Task(line);
            t.markAsDone();
            String newDbTask = TaskList.task2Db(t);
            this.database.writeLineToFile(index, newDbTask);
        } catch (IOException e) {
            System.out.println("Failed to mark task as complete: " + e.getMessage());
        }
    }

    /**
     * unmarkTask unmarks a task as completed by line number
     *
     * @param index
     */
    public void unmarkTask(int index) {
        try {
            String line = this.database.readLineFromFile(index);
            Task t = TaskList.db2Task(line);
            t.unmarkAsDone();
            String newDbTask = TaskList.task2Db(t);
            this.database.writeLineToFile(index, newDbTask);
        } catch (IOException e) {
            System.out.println("Failed to unmark task as completed: " + e.getMessage());
        }
    }

    /**
     * Prints out the contents of the tasklist
     */
    public void printTasks() {
        try {
            List<String> tasks = this.database.readLinesFromFile();
            for (int i = 0; i < tasks.size(); i++) {
                String line = tasks.get(i);
                Task t = db2Task(line);
                System.out.println((i + 1) + ". " + t);
            }
        } catch (IOException e) {
            System.out.println("Failed to print tasklist: " + e.getMessage());
        }
    }

    public int size() {
        try {
            return this.database.readLinesFromFile().size();
        } catch (IOException e) {
            System.out.println("Failed to get size: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Clears your database file
     */
    public void clear() {
        this.database.clearFile();
    }

    /**
     * Converts the database representation of a Task to the Task object
     *
     * @param dbTask the string rep of the Task in the database
     * @return Task the Task object
     */
    public static Task db2Task(String dbTask) {
        String[] params = dbTask.split(" \\| ");
        String type = params[0];
        switch (type) {
            case "T": //  To do
                Todo todoTask = Todo.db2Todo(dbTask);
                return todoTask;
            case "D": // Deadline
                Deadline deadlineTask = Deadline.db2Deadline(dbTask);
                return deadlineTask;
            case "E": // Event
                Event eventTask = Event.db2Event(dbTask);
                return eventTask;
            default:
                return null;
        }
    }

    /**
     * Converts a Task to their database representation
     *
     * @param task the Task object
     * @return String the string rep of Task in the database
     */
    public static String task2Db(Task task) {
        // Based on task type, extract traits
        if (task instanceof Todo) {
            Todo todoTask = (Todo) task;
            return Todo.todo2Db(todoTask);
        } else if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            return Deadline.deadline2Db(deadlineTask);
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            return Event.event2Db(eventTask);
        } else {
            return null;
        }
    }


    public static void main(String[] args) {
        TaskList testTaskList = new TaskList();
        testTaskList.database.clearFile();

        // printTaskStatus
        System.out.println("Current Task list: ");
        testTaskList.printTasks();
        System.out.println();

        // Get Todo Task
        System.out.println("Successfully got todo task: " + testTaskList.getTask(1));

        // Get Event Task
        System.out.println("Successfully got event task: " + testTaskList.getTask(2));

        // Get Deadline Task
        System.out.println("Successfully got deadline task: " + testTaskList.getTask(3));

        // printTasks
        System.out.println();
        System.out.println("Task list before deletion: ");
        testTaskList.printTasks();

        // Delete Event Task
        testTaskList.deleteTask(2);

        // Delete Deadline Task
        testTaskList.deleteTask(2);

        // printTasks
        System.out.println();
        System.out.println("Task list after deletion: ");
        testTaskList.printTasks();

        // mark task 1
        System.out.println();
        System.out.println("Marking task 1");
        testTaskList.markTask(1);

        // printTasks
        System.out.println("Tasks after marking T1: ");
        testTaskList.printTasks();
        System.out.println();

        // unmark task 1
        System.out.println("Unmarking task 1");
        testTaskList.unmarkTask(1);

        // printTasks
        System.out.println("Tasks after unmarking T1: ");
        testTaskList.printTasks();
    }
}