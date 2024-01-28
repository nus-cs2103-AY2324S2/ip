import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
//    private Storage database;
//    private String filepath = Duke.DBPATH;
    public ArrayList<Task> taskList;

    // Todo representation - 0 means not done, 1 means done
    // T | done? | desc

    // Deadline representation
    // D | done? | desc | by

    // Event representation
    // E | done? | desc | from | to

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<String> stringTasksList) throws DukeException.UnknownCommandException { // initialise the TaskList with a List<String>
        this.taskList = new ArrayList<>();
        for (String s : stringTasksList) {
            this.taskList.add(db2Task(s));
        }
    }


    /**
     * Adds a task to the current taskList
     * @param task to be added
     */
    public void addTask(Task task) {
        // Based on task type, extract traits
        taskList.add(task);
    }

    /**
     * Gets a task from the current taskList
     * @param task to be added
     */
    public Task getTask(int index) {
        // Check if the index is within the valid range
        if (index >= 1 && index <= taskList.size()) {
            return taskList.get(index - 1);
        } else {
            // Handle the case when the index is out of bounds
            System.out.println("Invalid index. Please provide a valid index.");
            return null;  // or throw an exception or handle it as needed
        }
    }

    /**
     * Deletes a task from the TaskList, given its index
     * @param index
     */
    public void deleteTask(int index) {
        taskList.remove(index - 1);
    }

    /**
     * markTask marks a task as completed by line number
     *
     * @param index
     */
    public void markTask(int index) {
        Task t =  this.getTask(index);
        t.markAsDone();
    }

    /**
     * unmarkTask unmarks a task as completed by line number
     *
     * @param index
     */
    public void unmarkTask(int index) {
        Task t =  this.getTask(index);
        t.unmarkAsDone();
    }

    /**
     * Prints out the contents of the tasklist
     */
    public void printTasks() {
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println((i + 1) + ". " + this.taskList.get(i));
        }
    }

    public int size() {
        return this.taskList.size();
    }

    /**
     * Converts the database representation of a Task to the Task object
     *
     * @param dbTask the string rep of the Task in the database
     * @return Task the Task object
     */
    public static Task db2Task(String dbTask) throws DukeException.UnknownCommandException {
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
                System.out.println("Failed to convert string to task!");
                throw new DukeException.UnknownCommandException();
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
            System.out.println("Failed to add task: " + task);
            return null;
        }
    }

    public static void main(String[] args) {
        Storage storage = new Storage("data/duketest.txt");
        TaskList testTaskList;
        try {
            testTaskList = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui ui = new Ui();
            ui.showLoadingError();
            testTaskList = new TaskList();
        }

        // printTaskStatus
        testTaskList.addTask(new Todo("Buy Bread"));
        testTaskList.addTask(new Deadline("Return Bread", "today"));
        testTaskList.addTask(new Event("project meeting", "Mon 4pm", "6pm"));
        testTaskList.addTask(new Event("project meeting 2", "Tues 10pm", "6pm"));
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