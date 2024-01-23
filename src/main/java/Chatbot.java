import exceptions.BluException;
import exceptions.IllegalParameterException;

public class Chatbot {
    private final String name;
    private TaskList taskList;
    private static final String LINESEP = "____________________________________________________________";

    public Chatbot(String name) {
        this.name = name;
        this.taskList = new TaskList();
    }

    private void print(String message) {
        System.out.println(LINESEP);
        System.out.println(message);
        System.out.println(LINESEP);
    }

    private void print(String[] messages) {
        System.out.println(LINESEP);
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(LINESEP);
    }

    public void greet() {
        String[] messages = {"Hello! I'm " + this.name, "What can I do for you?"};
        print(messages);
    }

    public void addTask(Task task) {
        this.taskList.addTask(task);
        String[] messages = {"I have added the task:", task.toString(), 
                            "You have " + this.taskList.getNumberOfTasks() +" tasks currently."};
        print(messages);
    }

    public void markTask(int taskIdx) throws BluException {
        try {
            Task task = this.taskList.getTask(taskIdx);
            if (task.getIsMarked()) {
                print( "Task number " + taskIdx + " is already marked as done");
            } else {
                task.setMarked();
                String[] messages = {"Marked task as done:", task.toString()};
                print(messages);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalParameterException("Failed to mark task. Task number " + taskIdx + " does not exist!\n" 
                                                + "Please use the list command to view task numbers.");
        }
    }

    public void unmarkTask(int taskIdx) throws BluException {
        try {
            Task task = this.taskList.getTask(taskIdx);
            if (!task.getIsMarked()) {
                print( "Task number" + taskIdx + "is already unmarked as not done");
            } else {
                task.setUnmarked();
                String[] messages = {"Unmarked task as not done:", task.toString()};
                print(messages);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalParameterException("Failed to unmark task. Task number " + taskIdx + " does not exist!\n" 
                                                + "Please use the list command to view task numbers.");
        }
    }

    public void deleteTask(int taskIdx) throws BluException {
        try {
            Task task = this.taskList.getTask(taskIdx);
            this.taskList.deleteTask(taskIdx);
            String[] messages = {"Deleted task from list:", task.toString(),
                                "You have " + this.taskList.getNumberOfTasks() + " tasks currently"};
            print(messages);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalParameterException("Failed to delete task. Task number " + taskIdx + " does not exist!\n" 
                                                + "Please use the list command to view task numbers.");
        }
    }

    public void displayTasks() {
       print(this.taskList.toString());
    }

    public void exit() {
        print("Goodbye!");
    }
}