import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private final Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> taskList;
    private Duke() {
        this.taskList  = new ArrayList<>();
    }
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
            try {
                if (mainC.equalsIgnoreCase("bye")) {
                    exit = true;
                } else if (mainC.equalsIgnoreCase("list")) {
                    printTaskList();
                } else if (mainC.equalsIgnoreCase("mark")) {
                    handleMark(splitCommands);
                } else if (mainC.equalsIgnoreCase("unmark")) {
                    handleUnmark(splitCommands);
                } else if (mainC.equalsIgnoreCase("todo")) {
                    handleToDo(command);
                } else if (mainC.equalsIgnoreCase("deadline")) {
                    handleDeadline(command);
                } else if (mainC.equalsIgnoreCase("event")) {
                    handleEvent(command);
                } else if (mainC.equalsIgnoreCase("delete")) {
                    handleDelete(splitCommands);
                } else {
                    handleInvalidCommand();
                }
            } catch (DukeException exception) {
                String exceptionMessage = exception.getExceptionMessage();
                printALine();
                System.out.println(exceptionMessage);
                printALine();
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
        for(int i = 0; i < this.taskList.size(); i++) {
            String taskString = (i+1) + ". " + this.taskList.get(i).toString();
            System.out.println(taskString);
        }
        printALine();
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
        ToDo newTask = new ToDo(description);
        taskList.add(newTask);
        echoAddTask(newTask, taskList.size());
    }

    /**
     * Method to add a Deadline to the taskList.
     */
    public void addDeadline(String description, String date) {
        Deadline newTask = new Deadline(description, date);
        taskList.add(newTask);
        echoAddTask(newTask, taskList.size());
    }

    /**
     * Method to add an Event to the tasklist.
     */
    public void addEvent(String description, String start, String end) {
        Event newTask = new Event(description, start, end);
        taskList.add(newTask);
        echoAddTask(newTask, taskList.size());
    }


    /**
     * Method to mark a task in the taskList as done.
     */
    public void markTask(int taskIndex) {
        this.taskList.get(taskIndex).setDone();
        printALine();
        System.out.println("I've marked this task as done: \n" +
                this.taskList.get(taskIndex).toString());
        printALine();
    }

    /**
     * Method to unmark a task in the taskList.
     */
    public void unmarkTask(int taskIndex) {
        this.taskList.get(taskIndex).setNotDone();
        printALine();
        System.out.println("I've marked this task as not done yet: \n" +
                this.taskList.get(taskIndex).toString());
        printALine();
    }

    /**
     * Method to delete a task in the taskList.
     */
    public void deleteTask(int taskIndex) {
        String taskString = this.taskList.get(taskIndex).toString();
        this.taskList.remove(taskIndex);
        printALine();
        System.out.println("I've removed this task as you instructed: \n" +
                taskString + "\nNumber of tasks in the list: " + taskList.size());
        printALine();
    }

    /**
     * Method to handle a mark command
     */
    public void handleMark(String[] splitCommands) throws DukeException {
        if (splitCommands.length != 2) {
            throw new DukeException("Invalid number of arguments!\n" +
                    "Make sure to enter mark, then the number of the task you want to mark as done.");
        // Solution adapted from https://www.baeldung.com/java-check-string-number
        } else if (!splitCommands[1].matches("-?\\d+(\\.\\d+)?")) {
            throw new DukeException("Please enter an integer as the second input.");
        } else if (Integer.parseInt(splitCommands[1]) <= 0) {
            throw new DukeException("Please enter an integer greater than 0 as the second input.");
        } else if (Integer.parseInt(splitCommands[1]) > taskList.size()) {
            throw new DukeException("Please enter an integer representing a task within the list.");
        } else if (taskList.get(Integer.parseInt(splitCommands[1]) - 1).getStatus()) {
            throw new DukeException("Task already marked as done.");
        } else {
            int taskIndex = Integer.parseInt(splitCommands[1]);
            markTask(taskIndex - 1);
        }
    }

    /**
     * Method to handle an unmark command
     */
    public void handleUnmark(String[] splitCommands) throws DukeException {
        if (splitCommands.length != 2) {
            throw new DukeException("Invalid number of arguments!\n" +
                    "Make sure to enter unmark, then the number of the task you want to unmark.");
            // Solution adapted from https://www.baeldung.com/java-check-string-number
        } else if (!splitCommands[1].matches("-?\\d+(\\.\\d+)?")) {
            throw new DukeException("Please enter an integer as the second input.");
        } else if (Integer.parseInt(splitCommands[1]) <= 0) {
            throw new DukeException("Please enter an integer greater than 0 as the second input.");
        } else if (Integer.parseInt(splitCommands[1]) > taskList.size()) {
            throw new DukeException("Please enter an integer representing a task within the list.");
        } else if (!taskList.get(Integer.parseInt(splitCommands[1]) - 1).getStatus()) {
            throw new DukeException("Task already unmarked.");
        } else {
            int taskIndex = Integer.parseInt(splitCommands[1]);
            unmarkTask(taskIndex - 1);
        }
    }

    /**
     * Method to handle a delete command
     */
    public void handleDelete(String[] splitCommands) throws DukeException {
        if (splitCommands.length != 2) {
            throw new DukeException("Invalid number of arguments!\n" +
                    "Make sure to enter unmark, then the number of the task you want to delete.");
            // Solution adapted from https://www.baeldung.com/java-check-string-number
        } else if (!splitCommands[1].matches("-?\\d+(\\.\\d+)?")) {
            throw new DukeException("Please enter an integer as the second input.");
        } else if (Integer.parseInt(splitCommands[1]) <= 0) {
            throw new DukeException("Please enter an integer greater than 0 as the second input.");
        } else if (Integer.parseInt(splitCommands[1]) > taskList.size()) {
            throw new DukeException("Please enter an integer representing a task within the list.");
        } else {
            int taskIndex = Integer.parseInt(splitCommands[1]);
            deleteTask(taskIndex - 1);
        }
    }

    /**
     * Method to handle a todo command
     */
    public void handleToDo(String command) throws DukeException {
        String[] descriptionSplit = command.split(" ", 2);
        if (descriptionSplit.length < 2) {
            throw new DukeException("Invalid number of arguments!\n" +
                    "Make sure to enter todo, then specify the task.");
        } else {
            addTodo(descriptionSplit[1]);
        }
    }

    /**
     * Method to handle a deadline command
     */
    public void handleDeadline(String command) throws DukeException {
        String[] descriptionAndDateSplit = command.split(" ", 2);
        if (descriptionAndDateSplit.length < 2) {
            throw new DukeException("Invalid number of arguments!\n" +
                    "Make sure to enter deadline, then specify the description of the task followed by the deadline.\n" +
                    "These two fields should be separated with /by.");
        }
        String descriptionAndDate = descriptionAndDateSplit[1];
        String[] splitVariables = descriptionAndDate.split(" /by ", 2);
        if (splitVariables.length < 2) {
            throw new DukeException("Invalid number of arguments!\n" +
                    "Make sure to enter deadline, then specify the description of the task followed by the deadline.\n" +
                    "These two fields should be separated with /by.");
        } else {
            String description = splitVariables[0];
            String date = splitVariables[1];
            addDeadline(description, date);
        }
    }

    /**
     * Method to handle an event command
     */
    public void handleEvent(String command) throws DukeException {
        String[] descriptionAndDateSplit = command.split(" ", 2);
        if (descriptionAndDateSplit.length < 2) {
            throw new DukeException("Invalid number of arguments!\n" +
                    "Make sure to enter event, then specify the description of the task followed by the start and end dates.\n" +
                    "The start date should be preceded with /from, while the end date should be preceded with /to.");
        }
        String descriptionAndDate = descriptionAndDateSplit[1];
        String[] descriptionSplit = descriptionAndDate.split(" /from ");
        if (descriptionSplit.length != 2) {
            throw new DukeException("Invalid number of arguments!\n" +
                    "Make sure to enter event, then specify the description of the task followed by the start and end dates.\n" +
                    "The start date should be preceded with /from, while the end date should be preceded with /to.");
        }
        String description = descriptionSplit[0];
        String startEnd = descriptionSplit[1];
        String[] startEndSplit = startEnd.split(" /to ");
        if (startEndSplit.length != 2) {
            throw new DukeException("Invalid number of arguments!\n" +
                    "Make sure to enter event, then specify the description of the task followed by the start and end dates.\n" +
                    "The start date should be preceded with /from, while the end date should be preceded with /to.");
        } else {
            String start = startEndSplit[0];
            String end = startEndSplit[1];
            addEvent(description, start, end);
        }
    }

    /**
     * Method to handle invalid commands
     */
    public void handleInvalidCommand() throws DukeException {
        throw new DukeException("I am unable to understand this command, please kindly try again.");
    }

}
