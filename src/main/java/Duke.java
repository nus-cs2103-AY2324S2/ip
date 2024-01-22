import java.util.Scanner;
public class Duke {
    private final Scanner scanner = new Scanner(System.in);
    private final Task[] taskList = new Task[100];
    public static void main(String[] args) {
        Duke aurora1 = new Duke();
        aurora1.exeAurora();
    }

    /**
     * Method for execution.
     */
    public void exeAurora() {
        printOpeningMessage();
        boolean exit = false;
        while(!exit) {
            String command = scanner.nextLine();
            String[] splitCommands = command.split(" ");
            String mainC = splitCommands[0];
            if (mainC.equalsIgnoreCase("bye")) {
                exit = true;
            } else if (mainC.equalsIgnoreCase("list")) {
                printTaskList();
            } else if (mainC.equalsIgnoreCase("mark")) {
                int taskIndex = Integer.parseInt(splitCommands[1]);
                markTask(taskIndex - 1);
            } else if (mainC.equalsIgnoreCase("unmark")) {
                int taskIndex = Integer.parseInt(splitCommands[1]);
                unmarkTask(taskIndex - 1);
            } else {
                addTask(command);
            }
        }
        printExitMessage();
    }

    /**
     * Method to print the greeting message.
     */
    public void printOpeningMessage() {
        printALine();
        String openingMessage = "How are you feeling? I'm Aurora, your personal schedule assistant. \n"
                + "What can I do for you?";
        System.out.println(openingMessage);
        printALine();
    }

    /**
     * Method to print the exit message.
     */
    public void printExitMessage() {
        printALine();
        String exitMessage = "Thank you for consulting with me, have a good day.";
        System.out.println(exitMessage);
        printALine();
    }

    /**
     * Method to print the task list.
     */
    public void printTaskList() {
        printALine();
        System.out.println("These are the tasks in your list:");
        for(int i = 0; i < this.taskList.length; i++) {
            if (this.taskList[i] == null) {
                printALine();
                return;
            } else {
                String taskString = (i+1) + ". " + this.taskList[i].toString();
                System.out.println(taskString);
            }
        }
    }

    /**
     * Method to echo an add command.
     */
    public void echoAddTask(String task) {
        printALine();
        String echo = "added: " + task;
        System.out.println(echo);
        printALine();
    }

    /**
     * Method to print a line for separation.
     */
    public void printALine() {
        for (int i = 0; i < 100; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    /**
     * Method to add a task to the taskList.
     */
    public void addTask(String task) {
        int taskNumber = nextTaskNumber();
        if(nextTaskNumber() != -1) {
            Task newTask = new Task(task);
            taskList[taskNumber] = newTask;
            echoAddTask(task);
        }
    }

    /**
     * Method to find the first empty entry in the array
     *
     * @return -1 if there are no more empty indexes in the array, the next empty index if there are empty indexes
     * left in the array.
     */
    public int nextTaskNumber() {
        int nextNumber = -1;
        for (int i = 0; i < this.taskList.length; i++) {
            if (this.taskList[i] == null) {
                return i;
            }
        }
        return nextNumber;
    }

    /**
     * Method to mark a task in the taskList as done.
     */
    public void markTask(int taskIndex) {
        this.taskList[taskIndex].setDone();
        printALine();
        System.out.println("I've marked this task as done: \n" +
                this.taskList[taskIndex].toString());
        printALine();
    }

    /**
     * Method to unmark a task in the taskList.
     */
    public void unmarkTask(int taskIndex) {
        this.taskList[taskIndex].setNotDone();
        printALine();
        System.out.println("I've marked this task as not done yet: \n" +
                this.taskList[taskIndex].toString());
        printALine();
    }

}
