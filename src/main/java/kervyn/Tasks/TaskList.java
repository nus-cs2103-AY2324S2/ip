package kervyn.Tasks;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import kervyn.FXControls.DialogBox;

import java.util.ArrayList;


/**
 * Represents a list of tasks and provides operations for managing tasks.
 */
public class TaskList {
    // Contains task-related operations
    private ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }


    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param taskList The ArrayList of Task objects to initialize the TaskList.
     */
    public TaskList (ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the list of tasks.
     *
     * @return The ArrayList of Task objects.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param userTasks The ArrayList of Task objects to be listed.
     * @return Returns 1 if the list operation was successful, 0 otherwise.
     */
    public short listTasks(ArrayList<Task> userTasks, Image kervynImage, VBox dialogContainer) {
        StringBuilder textToOutput = new StringBuilder();
        textToOutput.append("\tHere are the tasks on your list:\n");
        for (int i = 0; i < userTasks.size(); i++) {
            Task task = userTasks.get(i);
            char check = task.getCompleted() ? 'X' : ' ';
            char type = task.getCapitalType();
            switch (type) {
                case 'T':
                    textToOutput.append("\t" + (i + 1) + "." + "[" + type + "] " +  "[" + check + "] " + task.getDescription() + "\n");
                    break;
                case 'D':
                    Deadline deadlineTask = (Deadline) task;
                    if (deadlineTask == null) {
                        return 0;
                    }
                    textToOutput.append("\t" + (i + 1) + "." + "[" + type + "] " + "[" + check + "] " + deadlineTask.getDescription() + " (by: " + deadlineTask.getDeadline() + ")\n");
                    break;
                case 'E':
                    Event eventTask = (Event) task;
                    if (eventTask == null) {
                        return 0;
                    }
                    textToOutput.append("\t" + (i + 1) + "." + "[" + type + "] " + "[" + check + "] "  + eventTask.getDescription() + " (from: " + eventTask.getStartDate() + " to: " + eventTask.getEndDate() + ")\n");
                    break;
                default:
                    textToOutput.append("\tNo tasks to display :(");
                    break;
            }
        }

        dialogContainer.getChildren().add(
                DialogBox.getKervynDialog(textToOutput.toString(), kervynImage)
        );
        return 1;
    }

    /**
     * Marks a task as completed in the task list.
     *
     * @param userTasks          The ArrayList of Task objects.
     * @param processedUserInput The user input processed into an array of Strings.
     * @return Returns 1 if the mark operation was successful, 0 otherwise.
     */
    public short markTask(ArrayList<Task> userTasks, String[] processedUserInput, Image kervynImage, VBox dialogContainer) {
        try {
            Task task = userTasks.get(Integer.parseInt(processedUserInput[1]) - 1);
            if (task.getCompleted()) {
                taskAlreadyMarked(dialogContainer, kervynImage);
            } else {
                dialogContainer.getChildren().add(
                        DialogBox.getKervynDialog("\tNice! I've marked this task as done:", kervynImage)
                );
                task.updateStatus();
                dialogContainer.getChildren().add(
                        DialogBox.getKervynDialog(task.toString(), kervynImage)
                );
            }

            return 1;
        }
        catch (IndexOutOfBoundsException e) {
            // Need to account for trying to mark a task that doesn't exist
            dialogContainer.getChildren().add(
                    DialogBox.getKervynDialog("\tTask number provided doesn't exist. Please try again.", kervynImage)
            );
        }
        return 0;
    }

    /**
     * Unmarks a task as not completed in the task list.
     *
     * @param userTasks The ArrayList of Task objects.
     * @param processedUserInput The user input processed into an array of Strings.
     * @return Returns 1 if the unmark operation was successful, 0 otherwise.
     */
    public short unMarkTask(ArrayList<Task> userTasks, String[] processedUserInput, Image kervynImage, VBox dialogContainer) {
        try {
            Task task = userTasks.get(Integer.parseInt(processedUserInput[1]) - 1);
            if (!task.getCompleted()) {
                taskAlreadyUnMarked(dialogContainer, kervynImage);
            } else {
                dialogContainer.getChildren().add(
                        DialogBox.getKervynDialog("\tOK, I've marked this task as not done yet:", kervynImage)
                );
                task.updateStatus();
                dialogContainer.getChildren().add(
                        DialogBox.getKervynDialog(task.toString(), kervynImage)
                );
            }

            return 1;
        }
        catch (IndexOutOfBoundsException e) {
            // Need to account for trying to unmark a task that doesn't exist
            dialogContainer.getChildren().add(
                    DialogBox.getKervynDialog("\tTask number provided doesn't exist. Please try again.", kervynImage)
            );
        }
        return 0;
    }

    /**
     * Private method to handle the scenario when a task is already marked.
     */
    private void taskAlreadyMarked(VBox dialogContainer, Image kervynImage) {
        dialogContainer.getChildren().add(
                DialogBox.getKervynDialog("\tUh oh! It looks like this task is already marked as done, please try again with another task!", kervynImage)
        );
    }

    /**
     * Private method to handle the scenario when a task is already unmarked.
     */
    private static void taskAlreadyUnMarked(VBox dialogContainer, Image kervynImage) {
        dialogContainer.getChildren().add(
                DialogBox.getKervynDialog("\tUh oh! It looks like this task is already marked as not done, please try again with another task!", kervynImage)
        );
    }

    /**
     * Removes a task from the task list.
     *
     * @param userTasks The ArrayList of Task objects.
     * @param processedUserInput The user input processed into an array of Strings.
     */
    public void removeTask(ArrayList<Task> userTasks, String[] processedUserInput, Image kervynImage, VBox dialogContainer) {
        try {
            Task task = userTasks.get(Integer.parseInt(processedUserInput[1]) - 1);
            dialogContainer.getChildren().add(
                    DialogBox.getKervynDialog("\tOK, I've removed this task as per your request:", kervynImage)
            );
            dialogContainer.getChildren().add(
                    DialogBox.getKervynDialog(task.toString(), kervynImage)
            );
            userTasks.remove(task);
        }
        catch (IndexOutOfBoundsException e) {
            // Need to account for trying to delete a task that doesn't exist
            dialogContainer.getChildren().add(
                    DialogBox.getKervynDialog("\tTask number provided doesn't exist. Please try again.", kervynImage)
            );
        }
    }

    public void findTask(ArrayList<Task> userTasks, String userInput, Image kervynImage, VBox dialogContainer) {
        String lowerCaseInput = userInput.toLowerCase();
        String[] processedUserInput = lowerCaseInput.split(" ");
        StringBuilder userKeywords = new StringBuilder();
        ArrayList<Task> results = new ArrayList<>();

        for (int i = 1; i < processedUserInput.length; i++) {
            if (i != processedUserInput.length - 1) {
                userKeywords.append(processedUserInput[i] + " ");
            } else {
                userKeywords.append(processedUserInput[i]);
            }
        }

        for (Task userTask : userTasks) {
            // Check the type of the tasks
            String task = "";
            task = userTask.toString().toLowerCase();
            if (task.contains(userKeywords)) {
                results.add(userTask);
            }
        }

        if (results.isEmpty()) {
            dialogContainer.getChildren().add(
                    DialogBox.getKervynDialog("\tThere are no tasks that match your keyword provided.", kervynImage)
            );
        } else {
            dialogContainer.getChildren().add(
                    DialogBox.getKervynDialog("\tHere are the matching tasks in your list:", kervynImage)
            );
            StringBuilder textToOutput = new StringBuilder();
            for (int j = 0; j < results.size(); j++) {
                // Check the type of the tasks
                textToOutput.append("\t" + (j + 1) + ". " + results.get(j).toString() + "\n");
            }

            dialogContainer.getChildren().add(
                    DialogBox.getKervynDialog(textToOutput.toString(), kervynImage)
            );
        }
    }
}
