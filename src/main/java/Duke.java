import java.util.Scanner;
public class Duke {
    private final Scanner scanner = new Scanner(System.in);
    private final String[] taskList = new String[100];
    public static void main(String[] args) {
        Duke aurora1 = new Duke();
        aurora1.exeAurora();
    }

    /**
     * Method for execution. Handling 3 commands.
     */
    public void exeAurora() {
        printOpeningMessage();
        boolean exit = false;
        while(!exit) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                exit = true;
            } else if (command.equalsIgnoreCase("list")) {
                printTaskList();
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
        for(int i = 0; i < this.taskList.length; i++) {
            if (this.taskList[i] == null) {
                printALine();
                return;
            } else {
                String taskString = (i+1) + ". " + this.taskList[i];
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
            taskList[taskNumber] = task;
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

}
