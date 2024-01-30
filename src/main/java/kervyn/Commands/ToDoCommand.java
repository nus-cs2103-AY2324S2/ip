package kervyn.Commands;

import kervyn.Tasks.Task;
import kervyn.Tasks.TaskList;
import kervyn.Tasks.ToDo;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents the "ToDo" command in the application, used to create and add a ToDo task to the TaskList.
 */
public class ToDoCommand extends Command {
    private String userInput;

    /**
     * Constructs a ToDoCommand with the specified TaskList and user input.
     *
     * @param taskList The TaskList associated with this command.
     * @param userInput The user input string containing the ToDo task details.
     */
    public ToDoCommand(TaskList taskList, String userInput) {
        super("ToDo", taskList);
        this.userInput = userInput;
    }

    /**
     * Processes the user input and creates a ToDo task.
     * The method parses the input to construct a ToDo object.
     *
     * @param userInput The user input string to process.
     * @return A ToDo object, or null if the input format is invalid.
     */
    private ToDo getProcessedToDo(String userInput) {
        try {
            String[] toDoDescriptionArray = userInput.split(" ");

            if (Objects.equals(toDoDescriptionArray[1], "")) {
                System.out.println("\tThe description of a todo cannot be empty. Please try again.");
                return null;
            }

            StringBuilder toDoDescription = new StringBuilder();

            for (int i = 1; i < toDoDescriptionArray.length; i++) {
                toDoDescription.append(" ");
                toDoDescription.append(toDoDescriptionArray[i]);
            }

            taskAdded();
            return new ToDo(toDoDescription.toString(), false);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\tPlease provide the ToDo task in the required format.");
            return null;
        }
    }

    /**
     * Displays information about the newly added ToDo task.
     *
     * @param toDo The ToDo task that was added.
     * @param userTasks The current list of tasks, including the newly added ToDo task.
     */
    private void toDoTaskTextDisplay(ToDo toDo, ArrayList<Task> userTasks) {
        System.out.println("\t[" + toDo.getCapitalType() + "]" + "[ ]" + toDo.getDescription());
        System.out.println("\tNow you have " + userTasks.size() + " tasks in the list.");
    }

    /**
     * Executes the "ToDo" command.
     * This method processes the user input, creates a new ToDo task, adds it to the task list, and displays a confirmation message.
     */
    @Override
    public void executeCommand() {
        ToDo newToDo = getProcessedToDo(this.userInput);
        if (newToDo != null) {
            this.taskList.addTask(newToDo);
            toDoTaskTextDisplay(newToDo, this.taskList.getTaskList());
        }
    }
}
