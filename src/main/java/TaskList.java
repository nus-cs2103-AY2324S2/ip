import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private Ui ui;
    private int taskIndex;
    private Storage storage;

    public TaskList(Storage storage) {
        this.taskList = new ArrayList<>();
        this.ui = new Ui();
        this.storage = storage;
        storage.loadTaskList();
    }

    /**
     * Adds task to taskList
     * 
     * @param task task to be added
     */
    private void addTask(String task) {
        TaskType taskType = getTaskType(task);

        switch (taskType) {
        case TODO:
            addTodoTask(task);
            break;
        case DEADLINE:
            addDeadline(task);
            break;
        case EVENT:
            addEvent(task);
            break;
        case HELP:
            ui.showHelp();
            break;
        case DELETE:
            try {
                int index = Integer.parseInt(task.substring(6).trim()) - 1;
                deleteTask(index);
            } catch (NumberFormatException e) {
                System.out.println("\t" + "Invalid input. Please enter a valid task index.");
            }
            break;
        default:
            System.out.println("\t" + "Sorry, that's not a command :( Enter 'help' for instructions.");
        }
        ui.printLine();
    }

    /**
     * Adds todoTask to taskList
     * 
     * @param task task to be added
     */
    private void addTodoTask(String task) {
    
        String todoDescription = task.substring(4).trim();
        if (todoDescription.isEmpty()) {
            System.out.println("\t" + "Invalid input. Please enter a valid todo task.");
        } else {
            Todo newTodo = new Todo (todoDescription);
            this.taskList.add(newTodo);
    
            System.out.println("\t" + "Added todo: " + todoDescription);
            storage.saveTaskListToFile();
        }
    }

    /** 
     * Adds deadline to taskList
     * 
     * @param task task to be added
     */
    private void addDeadline(String task) {
     
        String[] deadlineDescription = task.substring(8).trim().split("/by", 2);
        if (deadlineDescription.length != 2 || deadlineDescription[0].trim().isEmpty() 
            || deadlineDescription[1].trim().isEmpty()) {
            System.out.println("\t" + "Invalid input. Enter 'deadline <task> /by <DEADLINE>'");
        } else {
            String description = deadlineDescription[0].trim();
            String by = deadlineDescription[1].trim();
    
            Deadline newDeadline = new Deadline(description, by);
            this.taskList.add(newDeadline);
               
            System.out.println("\t" + "Added deadline: " + newDeadline.toString());
            storage.saveTaskListToFile();
        }
    }

    /** 
     * Adds eventTask to taskList
     * 
     * @param task task to be added
     */
    private void addEvent(String task) {
    
        String[] eventParts = task.substring(6).trim().split("/from");
        if (eventParts.length == 2) {
            String[] durationParts = eventParts[1].trim().split("/to");
            if (durationParts.length == 2) {
                String desc = eventParts[0].trim();
                String from = durationParts[0].trim();
                String to = durationParts[1].trim();
                Events newEvent = new Events(desc, from, to);
                this.taskList.add(newEvent);
                   
                System.out.println("\t" + "Added event: " + desc + " (from: " + from + ", to: " + to + ")");
                storage.saveTaskListToFile();
    
            } else {
                System.out.println("\t" + "Invalid input for event. Please use the format: event <task> /from <start time> /to <end time>");
            }
        } else {
            System.out.println("\t" + "Invalid input for event. Please use the format: event <task> /from <start time> /to <end time>");
        }
    }

    /** 
     * Deletes task from taskList
     * 
     * @param index index of task to be deleted
     * @throws IndexOutOfBoundsException if index is out of bounds
     * @throws NumberFormatException if input is not a number
     */
    private void deleteTask(int index) {
        try {
            System.out.println("\t" + "Noted. I've removed this task:" + "\n" + "\t" 
                    + "[ " + this.taskList.get(index) + " ]");
            this.taskList.remove(index);
            System.out.println("\t" + "There are " + this.taskList.size() + " tasks in your list.");
            storage.saveTaskListToFile();
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidTaskIndex();
        } catch (NumberFormatException e) {
            ui.printInvalidTaskIndex();
        }
    }

    /**
     * Returns task type
     * 
     * @param task task to be added
     * @return TaskType
     */
    private TaskType getTaskType(String task) {
        if (task.startsWith("todo")) {
            return TaskType.TODO;
        } else if (task.startsWith("deadline")) {
            return TaskType.DEADLINE;
        } else if (task.startsWith("event")) {
            return TaskType.EVENT;
        } else if (task.equals("help")) {
            return TaskType.HELP;
        } else if (task.startsWith("delete")) {
            return TaskType.DELETE;
        } else {
            return TaskType.UNKNOWN;
        }
    }

    /** 
     * Marks task as done
     * If task is already done, prints error message
     * If task is undone, marks as done
     * If task does not exist, prints error message
     * If input is not a number, prints error message
     * 
     * @param index index of task to be marked as done
     * @throws IndexOutOfBoundsException if index is out of bounds
     * @throws NumberFormatException if input is not a number
     */
    private void markDone(int index) {
        try {
            if (this.taskList.get(index).isDone()) {
                System.out.println("\t" + "You completed this task already!");
                ui.printLine();
            } else {
                this.taskList.get(index).markDone();
                System.out.println("\t" + "Good job completing the task!");
                storage.saveTaskListToFile();
                printList();
            }
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidTaskIndex();
        } catch (NumberFormatException e) {
            ui.printInvalidTaskIndex();
        }
    }

    /** 
     * Marks task as undone
     * If task is already undone, prints error message
     * If task is done, marks as undone
     * If task does not exist, prints error message
     * If input is not a number, prints error message
     * 
     * @param index index of task to be marked as undone
     * @throws IndexOutOfBoundsException if index is out of bounds
     * @throws NumberFormatException if input is not a number
     */
    private void markUndone(int index) {
        try {
            if (!this.taskList.get(index).isDone()) {
                System.out.println("\t" + "Oops! You still haven't done this task!");
            } else {
                this.taskList.get(index).markUndone();
                System.out.println("\t" + "Better get to it soon!");
                storage.saveTaskListToFile();
                printList();
                ui.printLine();
            }
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidTaskIndex();
        } catch (NumberFormatException e) {
            ui.printInvalidTaskIndex();
        }
    }    

    /** 
     * Prints taskList
     */
    private void printList() {
        if (this.taskList.isEmpty()) {
            System.out.println("\t" + "Your tasklist is empty");
        } else {
            System.out.println("\t" + "Here is your to-do list:");
            this.taskIndex = 1;
            for (Task task : this.taskList) {
                System.out.println("\t" + this.taskIndex + ". " + task);
                this.taskIndex++;
            }
        }
        ui.printLine();
    }
}
