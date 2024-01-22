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
            } else if (mainC.equalsIgnoreCase("todo")) {
                String[] splitToDo = command.split(" ", 2);
                addTodo(splitToDo[1]);
            } else if (mainC.equalsIgnoreCase("deadline")) {
                String[] descriptionAndDateSplit = command.split(" ", 2);
                String descriptionAndDate = descriptionAndDateSplit[1];
                String[] splitVariables = descriptionAndDate.split(" /by ", 2);
                String description = splitVariables[0];
                String date = splitVariables[1];
                addDeadline(description, date);
            } else if (mainC.equalsIgnoreCase("event")) {
                String[] descriptionAndDateSplit = command.split(" ", 2);
                String descriptionAndDate = descriptionAndDateSplit[1];
                String[] descriptionSplit = descriptionAndDate.split(" /from ");
                String description = descriptionSplit[0];
                String startEnd = descriptionSplit[1];
                String[] startEndSplit = startEnd.split(" /to ");
                String start = startEndSplit[0];
                String end = startEndSplit[1];
                addEvent(description, start, end);
            } else {
                System.out.println("Please enter an alternative command.");
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
    public void echoAddTask(Task task, int taskNumber) {
        printALine();
        String echo = "Added this task: \n" + task.toString() + "\nNumber of tasks in list: " + taskNumber;
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
     * Method to add a ToDo to the taskList.
     */
    public void addTodo(String description) {
        int taskNumber = nextTaskNumber();
        if(nextTaskNumber() != -1) {
            ToDo newTask = new ToDo(description);
            taskList[taskNumber] = newTask;
            System.out.println(taskNumber);
            echoAddTask(newTask, taskNumber + 1);
        }
    }

    /**
     * Method to add a Deadline to the taskList.
     */
    public void addDeadline(String description, String date) {
        int taskNumber = nextTaskNumber();
        if(nextTaskNumber() != -1) {
            Deadline newTask = new Deadline(description, date);
            taskList[taskNumber] = newTask;
            echoAddTask(newTask, taskNumber + 1);
        }
    }

    /**
     * Method to add an Event to the tasklist.
     */
    public void addEvent(String description, String start, String end) {
        int taskNumber = nextTaskNumber();
        if(nextTaskNumber() != -1) {
            Event newTask = new Event(description, start, end);
            taskList[taskNumber] = newTask;
            echoAddTask(newTask, taskNumber + 1);
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
