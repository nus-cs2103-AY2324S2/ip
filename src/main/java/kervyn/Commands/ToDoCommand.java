package kervyn.Commands;

import kervyn.Tasks.Task;
import kervyn.Tasks.TaskList;
import kervyn.Tasks.ToDo;

import java.util.ArrayList;
import java.util.Objects;

public class ToDoCommand extends Command {
    private String userInput;
    public ToDoCommand(TaskList taskList, String userInput) {
        super("ToDo", taskList);
        this.userInput = userInput;
    }

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

    private void toDoTaskTextDisplay(ToDo toDo, ArrayList<Task> userTasks) {
        System.out.println("\t[" + toDo.getCapitalType() + "]" + "[ ]" + toDo.getDescription());
        System.out.println("\tNow you have " + userTasks.size() + " tasks in the list.");
    }

    @Override
    public void executeCommand() {
        ToDo newToDo = getProcessedToDo(this.userInput);
        if (newToDo != null) {
            this.taskList.addTask(newToDo);
            toDoTaskTextDisplay(newToDo, this.taskList.getTaskList());
        }
    }
}
