package area;

import java.util.ArrayList;

/**
 * Represents a list of Task objects. Each Task object corresponds to a task set
 * for the bot by user.
 */
public class TaskList {

    private ArrayList<Task> tasks; // List of all the task objects in this list.
    private int taskCount;
    private Ui ui;
    private Parser parser;

    public TaskList(ArrayList<Task> tasks) throws Exception {
        this.tasks = tasks;
        this.taskCount = 0;
        ui = new Ui();
        parser = new Parser();
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.taskCount = 0;
        ui = new Ui();
        parser = new Parser();
    }

    /**
     * get number of tasks
     * 
     * @return int
     */
    public int getNumberOfTasks() {
        return taskCount;
    }

    /**
     * returns list of tasks
     * 
     * @return ArrayList<Task>
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * assign a list of tasks to this
     * 
     * @param t
     */
    public void setTaskList(ArrayList<Task> t) {
        this.tasks = t;
    }

    /**
     * Prints a list of tasks that contain the keyword used to search for them.
     * If there are no matching tasks, a line is printed to inform user there
     * is no matching tasks.
     * 
     * @param keyword
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
            return "Here are the matching tasks in your list:" + "\n" + ui.showList(result);
        }
    }

    /**
     * add the task to the list of tasks. If instruction is incomplete, an error
     * will be thrown to inform the user of the issue.
     * 
     * @param instruction
     */
    public String addTask(String instruction) {
        String command = parser.parseCommand(instruction);
        if (command.equals("todo")) {
            Todo task = new Todo(parser.parseTodo(instruction));
            System.out.println("correct");
            tasks.add(task);
            taskCount++; // keep track of number of tasks
            return ui.addTask(this);
        } else if (command.equals("deadline")) {
            String[] description = parser.parseDeadline(instruction);
            Deadline task = new Deadline(description[0], description[1]);
            tasks.add(task);
            taskCount++; // keep track of number of tasks
            return ui.addTask(this);
        } else if (command.equals("event")) {
            String[] description = parser.parseEvent(instruction);
            Event task = new Event(description[0], description[1], description[2]);
            tasks.add(task);
            taskCount++; // keeps track of tasks
            return ui.addTask(this);
        }
        return "";
    }

    /**
     * Modify the task based on instruction. If instruction is incomplete, an error
     * message will be printed to notify user of the problem.
     * 
     * @param instruction
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
            taskCount -= 1;
            assert taskCount >= 0 : "count cannot be negative";
            return ui.deleteTask(deletedTask, this);
        }
        return "";
    }
}
