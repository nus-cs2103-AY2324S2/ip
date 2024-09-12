package someboty.managers;

import java.util.ArrayList;

import someboty.exceptions.InputException;
import someboty.exceptions.TerminateException;
import someboty.expenses.Expense;
import someboty.tasks.Task;

/**
 * commandManager acts a command center to process and coordinate the different 
 * command inputs with other managers. It also formats the results of each action
 * into a string before forwarding it to the ResponseManager.
 */
public class CommandManager {

    private static final String[] COMMANDS = {
        "help",
        "list",
        "dateFormats",
        "find",
        "mark", 
        "unmark", 
        "delete",
        "clear",
        "deadline", 
        "event", 
        "todo",
        "expense"
    };

    private TaskManager taskManager;
    private ExpenseManager expenseManager;

    /**
     * Constructor for commandManager
     * @param taskList The taskManager to coordinate with.
     */
    public CommandManager(TaskManager task, ExpenseManager expense) {
        this.taskManager = task;
        this.expenseManager = expense;
    }

    /**
     * Parses user input and forwards desription (if any) to relevant managers to execute command.
     * @param input Command and description to be executed.
     * @return Response output after executing the command.
     * @throws TerminateException Thrown only when command input is "bye", which closes the application.
     */
    public String parse(String input) throws TerminateException{
        String command = input.split(" ")[0];

        // DO. NOT. JUDGE.
        if (input.trim().toLowerCase().equals("good job")) {
            return "Thank you for praising Izuna.\nNin Nin!";
        }
        
        try {
            switch (command) {

            case "bye":
                taskManager.update();
                expenseManager.update();
                throw new TerminateException("Izuna has saved your lists!\n");
            
            case "list":
                return printList(getDescription(input));

            case "help":
                return listCommands();

            case "dateFormats":
                return DateManager.PrintDateFormats();

            case "save":
                taskManager.update();
                expenseManager.update();
                return "Izuna has successfully saved your lists!";

            case "find":
                return findTasks(getDescription(input));

            case "mark":
                return setTaskStatus(getDescription(input), true);

            case "unmark":
                return setTaskStatus(getDescription(input), false);

            case "delete":
                return deleteTask(getDescription(input));

            case "clear":
                return clearList(getDescription(input));

            case "todo":
                return addTask('T', getDescription(input));

            case "deadline":
                return addTask('D', getDescription(input));

            case "event":
                return addTask('E', getDescription(input));

            case "expense":
                return addExpense(getDescription(input));

            default:
                return "Izuna does not understand your command.\n"
                     + "Type 'help' to get the list of valid commands.";
            }

        } catch (InputException e) {
            return e.getMessage();
        }
    }

    private static String listCommands() {
        String response = "Here are the list of commands:\n";

        for (String command : COMMANDS) {
            response += String.format(" - %s\n", command);
        }

        return response;
    }

    /**
     * Parses input to extract the description.
     * This method is called only when a command requires description to execute.
     * @param input String input to be parsed.
     * @return Description of the input.
     * @throws InputException Thrown when input has no description.
     */
    private static String getDescription(String input) throws InputException {
        String[] listOfStrings = input.trim().split(" ");
        String description = "";

        if (listOfStrings.length <= 1) {
            throw new InputException("Izuna does not recognize the task description.");
        }
        
        // Join the split words together.
        for (int i = 1; i < listOfStrings.length; i++) {
            description += listOfStrings[i] + " ";
        }

        return description.trim();
    }

    private String clearList(String listType) {
        listType = listType.toLowerCase();

        if (listType.startsWith("task")) {
            this.taskManager.clear();
            return "Fufu, Izuna is glad that master can bring her out to the beach again!";

        } else if (listType.startsWith("expense")) {
            this.expenseManager.clear();
            return "Izuna is confused why master is burning all the receipts.";
        } 

        throw new InputException("Izuna does not understand what did you want me to clear...");
    }

    private String printList(String listType) {
        listType = listType.toLowerCase();

        if (listType.startsWith("task")) {
            return this.taskManager.printList();

        } else if (listType.startsWith("expense")) {
            return this.expenseManager.printList();
        } 

        throw new InputException("Izuna does not understand what did you want me to clear...");
    }

    /**
     * Parses description into an integer and forwards it to taskManager to mark/unmark a task.
     * @param description Index of the task in the list to be marked/unmarked.
     * @param isCompleted Completion status of the task (true if completed, false otherwise).
     * @return String response after marking/unmarking the task.
     * @throws InputException Given index is out of the list's range.
     */
    private String setTaskStatus(String description, boolean isCompleted) throws InputException{
        int index;

        try {
            index = Integer.parseInt(description) - 1;

        } catch(NumberFormatException e) {
            throw new InputException(
                "Izuna is unable to determine which task to mark or unmark.\n"
                + "Please follow the format: 'mark TASK_NUMBER' or 'unmark TASK_NUMBER'"
                );
        }

        Task task = this.taskManager.setTaskStatus(index, isCompleted);
        return isCompleted
                ? "As expected of master, you made quick work of it!\n " + task
                : "Huh, there's more to it?\n " + task;
    }

    /**
     * Parses description into an integer and forwards it to taskManager to delete a task.
     * @param description Index of the task in the list to be deleted.
     * @return String response after deleting the task.
     * @throws InputException Description is not an integer.
     */
    private String deleteTask(String description) throws InputException {
        int index;

        try {
            index = Integer.parseInt(description) - 1;

        } catch(NumberFormatException e) {
            throw new InputException(
                "Izuna is unable to determine which task master wants to delete.\n"
                + "Please follow the format: 'mark TASK_NUMBER' or 'unmark TASK_NUMBER'"
                );
        }

        Task deletedTask = this.taskManager.deleteTask(index);
        return "As master has ordered, Izuna has removed this task:\n"
            + String.format("  %s\n", deletedTask)
            + String.format("Now you have %d tasks in the list.", this.taskManager.getListSize());
    }

    /**
     * Forwards description to taskManager to create a new task and add to task list.
     * @param type Type of the new task, represented as the first character of the actual type.
     * @param description Details relevant to the new task.
     * @return String response after adding the new task.
     */
    private String addTask(char type, String description) {
        Task newTask = this.taskManager.addTask(type, description);

        return "Got it. I've added this task:\n"
            + String.format("  %s\n", newTask)
            + String.format("Now you have %d tasks in the list.\n", this.taskManager.getListSize())
            +"(Type 'list' to see the full list of tasks)";
    }

    /**
     * Forwards description to taskManager to find a new list of tasks matching it.
     * Receives the matching list and formats it into a reader friendly format.
     * @param description substring used to find matching tasks.
     * @return String response of the list of matched tasks.
     */
    private String findTasks(String description) {
        ArrayList<Task> taskList = taskManager.findTasks(description);

        if (taskList.size() == 0) { // special case for when no matches found
            return "Huh. I couldn't find anything in this deep lonely abyss...";
        }

        int index = 1;
        String response = "Here are the matching tasks Izuna could find:";

        for (Task task : taskList) {
            response += String.format("\n%d %s", index, task);
            index++;
        }

        return response;
    }

    private String addExpense(String description) {
        Expense newExpense = this.expenseManager.addExpense(description);

        return "Got it. I've added this expense:\n"
            + String.format("  %s\n", newExpense)
            + String.format("Now you have %d expenses in the list.\n", this.expenseManager.getListSize())
            +"(Type 'list' to see the full list of expenses)";
    }
}
