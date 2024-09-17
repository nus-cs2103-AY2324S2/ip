package area;

import java.util.ArrayList;

/**
 * Represents a list of Task objects. Each Task object corresponds to a task set
 * for the bot by user.
 */
public class TaskList {

    private ArrayList<Task> tasks; // List of all the task objects in this list.
    private int count;
    private Ui ui;
    private Parser parser;

    /**
     * Creates class object of TaskList. 
     * Initializes an empty list.
     * Sets the number of tasks to 0.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.count = 0;
        ui = new Ui();
        parser = new Parser();
    }

    /**
     * Returns number of tasks.
     * 
     * @return Number of tasks.
     */
    public int getNumberOfTasks() {
        return count;
    }

    /**
     * Returns list of tasks.
     * 
     * @return Tasks.
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Assigns a list of tasks to this
     * 
     * @param t List of tasks.
     */
    public void setTaskList(ArrayList<Task> t) {
        this.tasks = t;
    }

    /**
     * Prints a list of tasks that contain the keyword used to search for them.
     * If there are no matching tasks, a line is printed to inform user there
     * is no matching tasks.
     * 
     * @param keyword Keyword.
     */
    public String findTask(String keyword) {
        ArrayList<Task> result = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                result.add(task);
            }
        }

        if (result.size() == 0) {
            return "There are no matching tasks.";
        } else {
            return ui.showList(result);
        }
    }

    /**
     * Adds the task to the list of tasks. If instruction is incomplete, an error
     * will be thrown to inform the user of the issue.
     * 
     * @param instruction User input.
     * @return  A string indicating whether or not the addition of the task was successful.
     */
    public String addTask(String instruction) {
        String command = parser.parseCommand(instruction);
        if (command.equals("todo")) {
            Todo todoTask = new Todo(parser.parseTodo(instruction));
            tasks.add(todoTask);
            count++; // keep track of number of tasks
            return ui.addTask(this);
        } else if (command.equals("deadline")) {
            String[] description = parser.parseDeadline(instruction);
            Deadline task = new Deadline(description[0], description[1]);
            tasks.add(task);
            count++; // keep track of number of tasks
            return ui.addTask(this);
        } else if (command.equals("event")) {
            String[] description = parser.parseEvent(instruction);
            Event task = new Event(description[0], description[1], description[2]);
            tasks.add(task);
            count++; // keeps track of tasks
            return ui.addTask(this);
        }
        return "";
    }

    /**
     * Modifies the task based on instruction. If instruction is incomplete, an error
     * message will be printed to notify user of the problem.
     * 
     * @param instruction User input.
     * @return Message indicating success or failure.
     */
    public String modifyTask(String instruction) {
        String command = parser.parseCommand(instruction);
        if (command.equals("mark")) {
            // marks a task as done
            int index = Integer.parseInt(parser.parseModify(instruction));
            tasks.get(index - 1).markTaskAsDone();
            return ui.taskDone(index, this);
        } else if (command.equals("unmark")) {
            // marks a specific task as undone
            int index = Integer.parseInt(parser.parseModify(instruction));
            tasks.get(index - 1).markTaskAsUndone();
            return ui.taskUndone(index, this);
        } else if (command.equals("delete")) {
            int index = Integer.parseInt(parser.parseModify(instruction));
            Task deletedTask = tasks.get(index - 1);
            tasks.remove(index - 1);
            count -= 1;
            return ui.deleteTask(deletedTask, this);
        } else if (command.equals("priority")) {
            int index = Integer.parseInt(parser.parseModify(instruction));
            int priority = Integer.parseInt(parser.parsePriority(instruction));
            Task prioritisedTask = tasks.get(index - 1);
            prioritisedTask.setPriority(priority);
            return ui.priorityMessage(prioritisedTask);
        }
        return "";
    }
}
