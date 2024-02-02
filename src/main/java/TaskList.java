import java.util.ArrayList;

public class TaskList {
    private static final int MAX_TASKS = 100;
    private ArrayList<Task> tasks;
    private int taskCount;

    //Constructor for entirely new list
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskCount = 0;
    }

    //Add a ToDo to the task list
    public void addToDo(String userInput) throws DuchessException {
        String[] toDoTokens = userInput.split("todo"); //Split to find description
        if (toDoTokens.length > 1) {
            String description = toDoTokens[1].trim(); //Trim to only keep description
            ToDo newToDo = new ToDo(description);
            addTask(newToDo, TaskType.TODO);
        } else {
            throw new DuchessException("Oh dear! That is an invalid command. Try: todo <description>");
        }
    }

    //Add a Deadline to the taskList
    public void addDeadline(String userInput) throws DuchessException {
        String[] deadlineTokens = userInput.split("deadline");

        if (deadlineTokens.length > 1) {
            // Split further to extract description and deadline details
            String[] details = deadlineTokens[1].trim().split("/by");

            if (details.length > 1) {
                String description = details[0].trim();
                String by = details[1].trim(); // by is everything after
                Deadline newDeadline = new Deadline(description, by);
                addTask(newDeadline, TaskType.DEADLINE);
            } else {
                throw new DuchessException("Oh dear! That is an invalid command. Try: deadline <description> /by <deadline>");
            }
        } else {
            throw new DuchessException("Oh dear! That is an invalid command. Try: deadline <description> /by <deadline>");
        }
    }

    //Add an Event to the task list
    public void addEvent(String userInput) throws DuchessException {
        String[] eventTokens = userInput.split("event");

        if (eventTokens.length > 1) {
            // Split further to extract description and event details
            String[] details = eventTokens[1].trim().split("/from|/to"); // Means can use either /from or /to as delimiter

            if (details.length > 2) {
                String description = details[0].trim();
                String from = details[1].trim(); // from is everything after
                String to = details[2].trim();   // to is everything after

                Event newEvent = new Event(description, from, to);
                addTask(newEvent, TaskType.EVENT);
            } else {
                throw new DuchessException("Oh dear! That is an invalid command. Try: event <description> /from <start> /to <end>");
            }
        } else {
            throw new DuchessException("Oh dear! That is an invalid command. Try: event <description> /from <start> /to <end>");
        }
    }

    public void printTaskList() {
        //printHorizontalLine();
        if (this.taskCount == 0) {
            System.out.println(" No tasks have been added yet.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < this.taskCount; i++) {
                System.out.println(" " + (i + 1) + "." + this.tasks.get(i).toString());
            }
        }
        //printHorizontalLine();
    }

    //Add a task to task list
    private void addTask(Task task, TaskType taskType) throws DuchessException {
        if (this.taskCount < MAX_TASKS) {
            this.tasks.add(task);
            this.taskCount++;
            //saveDataToFile();

            //printHorizontalLine();
            System.out.println(" Understood. I've added this " + taskType + " task:");
            System.out.println(task.toString());
            System.out.println("Now you have " + this.taskCount + " tasks in the list.");
            //printHorizontalLine();
        } else {
            throw new DuchessException("The task list is full. I cannot add more tasks.");
        }
    }

    public void deleteTask(int taskIndex) throws DuchessException {
        if (isValidTaskIndex(taskIndex)) {
            Task deletedTask = this.tasks.remove(taskIndex);
            this.taskCount--;
            //saveDataToFile();

            //printHorizontalLine();
            System.out.println(" Understood. I've deleted this task:");
            System.out.println(deletedTask.toString());
            System.out.println(" Now you have " + this.taskCount + " tasks in the list.");
            //printHorizontalLine();
        } else {
            throw new DuchessException("Invalid task index.");
        }
    }

    // Mark a task as done
    public void markTaskAsDone(int taskIndex) throws DuchessException {
        if (isValidTaskIndex(taskIndex)) {
            this.tasks.get(taskIndex).markAsDone();
            //saveDataToFile();
            //printHorizontalLine();
            System.out.println(" Perfect! I've marked this task as done:");
            System.out.println(this.tasks.get(taskIndex).toString());
            //printHorizontalLine();
        } else {
            throw new DuchessException("Invalid task index.");
        }
    }

    // Unmark a task as done
    public void unmarkTaskAsDone(int taskIndex) throws DuchessException {
        if (isValidTaskIndex(taskIndex)) {
            this.tasks.get(taskIndex).unmarkAsDone();
            //saveDataToFile();
            //printHorizontalLine();
            System.out.println(" Understood, I've marked this task as not done yet:");
            System.out.println(this.tasks.get(taskIndex).toString());
            //printHorizontalLine();
        } else {
            throw new DuchessException("Invalid task index.");
        }
    }

    // Check if the task index is valid
    private boolean isValidTaskIndex(int taskIndex) {
        return taskIndex >= 0 && taskIndex < this.taskCount;
    }

    public void increaseTaskCount() {
        this.taskCount++;
    }

    public void decreaseTaskCount() {
        this.taskCount--;
    }
}
