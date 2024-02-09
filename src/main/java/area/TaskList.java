package area;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks; // List of all the task objects in this list.
    private int count;
    private Ui ui;
    private Parser parser;

    public TaskList(ArrayList<Task> tasks) throws DukeException {
        this.tasks = tasks;
        this.count = 0;
        ui = new Ui();
        parser = new Parser();
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.count = 0;
        ui = new Ui();
        parser = new Parser();
    }

    /**
     * get number of tasks
     * 
     * @return int
     */
    public int getNumberOfTasks() {
        return count;
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
     * add the task to the list of tasks
     * 
     * @param instruction
     */
    public void addTask(String instruction) {
        String command = parser.parseCommand(instruction);
        if (command.equals("todo")) {
            Todo todoTask = new Todo(parser.parseTodo(instruction));
            System.out.println("correct");
            tasks.add(todoTask);
            count++; // keep track of number of tasks
            ui.addTask(this);
        } else if (command.equals("deadline")) {
            String[] description = parser.parseDeadline(instruction);
            Deadline task = new Deadline(description[0], description[1]);
            tasks.add(task);
            count++; // keep track of number of tasks
            ui.addTask(this);
        } else if (command.equals("event")) {
            String[] description = parser.parseEvent(instruction);
            Event task = new Event(description[0], description[1], description[2]);
            tasks.add(task);
            count++; // keeps track of tasks
            ui.addTask(this);
        }
    }

    /**
     * Modify the task based on instruction
     * 
     * @param instruction
     */
    public void modifyTask(String instruction) {
        String command = parser.parseCommand(instruction);
        if (command.equals("mark")) {
            // marks a task as done
            int index = Integer.parseInt(parser.parseModify(instruction));
            System.out.println("hello");
            tasks.get(index - 1).taskDone();
            ui.taskDone(index, this);
        } else if (command.equals("unmark")) {
            // marks a specific task as undone
            int index = Integer.parseInt(parser.parseModify(instruction));
            tasks.get(index - 1).taskUndone();
            ui.taskUndone(index, this);
        } else if (command.equals("delete")) {
            int index = Integer.parseInt(parser.parseModify(instruction));
            Task deletedTask = tasks.get(index - 1);
            tasks.remove(index - 1);
            count -= 1;
            ui.deleteTask(deletedTask, this);
        }
    }
}
