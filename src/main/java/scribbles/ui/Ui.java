package scribbles.ui;

import scribbles.tasklist.TaskList;

/**
 * This class implements the ui of Scribbles.
 */
public class Ui {

    /**
     * Constructor of Ui for chatbot Scribbles
     */
    public Ui() {
    }

    /**
     * Prints the greeting message of the chatbot.
     */
    public String greet() {
        return ("Hello! I'm Scribbles :) What can I do for you?\n"
                + "(input commands or type help for guide)");
    }

    /**
     * Prints the error message if user inputs an invalid input into chatbot.
     */
    public String printOtherInputMessage() {
        return ("Sorry, Scribbles was unable to understand your instructions :(\n"
                + "Try formatting your instructions as follows: \n"
                + "- type \"list\" to view your current list of tasks\n"
                + "- type \"mark [index]\" to mark task at index as completed\n"
                + "- type \"unmark [index]\" to mark task at index as incomplete\n"
                + "- type \"todo [task]\" to insert to-do task into your list\n"
                + "- type \"deadline [task] /by [date]\" to insert task into your list with deadline as date\n"
                + "- type \"event [task] /from [start] to [end]\" "
                + "to insert task into your list with a start and end duration\n"
                + "- type \"delete [index]\" to remove a task at index from your list\n"
                + "- type \"find [keyword]\" to find tasks with matching keyword\n"
                + "- type \"bye\" to exit Scribbles\n"
                + "Please try again :)");
    }

    /**
     * Prints list of tasks in task list.
     *
     * @param taskList List which contains the tasks
     */
    public String listTasks(TaskList taskList) {
        int numOfTasks = taskList.size();
        int index = 1;
        String tasks = "";

        while (numOfTasks != 0) {
            tasks += (index + ". " + taskList.get(index - 1).toString() + "\n");
            numOfTasks--;
            index++;
        }

        return ("Here are the tasks in your list:\n" + tasks);
    }

    /**
     * Prints message if index input by user is out of bounds.
     *
     * @param taskList List containing the tasks
     */
    public String printInvalidIndexMessage(TaskList taskList) {
        int numOfTasks = taskList.size();
        return ("Please check that you've input a valid index!\n"
                + "You currently only have " + numOfTasks + " task(s) in your list.");
    }

    /**
     * Prints message when task is deleted from the list.
     *
     * @param taskDeleted String of the task deleted from the list.
     * @param taskList task list that contains the tasks.
     */
    public String printTaskDeletionMessage(String taskDeleted, TaskList taskList) {
        return ("I've removed this task from your list:\n" + taskDeleted + "\n"
                + "Now you have " + taskList.size() + " task(s) in the list.");
    }

    /**
     * Print message after marking task instruction is called
     *
     * @param index Index of task to mark
     * @param taskList List containing the tasks
     */
    public String printMarkCompletedMessage(int index, TaskList taskList) {
        int numOfTasks = taskList.size();
        if (index <= numOfTasks) {
            return ("Nice! I've marked task number " + index + " as done: \n"
                    + taskList.get(index - 1).toString());
        } else {
            // if task index does not exist
            return ("Uh oh! looks like that task does not exist in your list.\n"
                    + "You currently only have " + numOfTasks + " task(s) in your list.");
        }
    }

    /**
     * Prints message after "unmark" task instruction is called.
     *
     * @param index Index of task to unmark
     * @param taskList List containing the tasks
     */
    public String printMarkIncompleteMessage(int index, TaskList taskList) {
        int numOfTasks = taskList.size();
        if (index <= numOfTasks) {
            return ("Okay! I've marked task number " + index + " as incomplete: \n"
                    + taskList.get(index - 1).toString());
        } else {
            // message if task index does not exist
            return ("Uh oh! looks like that task does not exist in your list.\n"
                    + "You currently only have " + numOfTasks + " task(s) in your list.");
        }
    }

    /**
     * Prints message if there is missing information from user input when adding tasks.
     */
    public String printTaskMissingInformationMessage() {
        return ("Uh oh! Looks like there's missing information in your instructions!\n"
                + "Ensure your tasks are formatted as follows: \n"
                + "- type \"todo [task]\" to insert to-do task into your list\n"
                + "- type \"deadline [task] /by [date/time]\" to insert task into your list "
                + "with [date/time] in the format dd/MM/yyyy HHmm\n"
                + "- type \"event [task] /from [start date/time] to [end date/time]\" "
                + "to insert task into your list with a start and end date/time in format dd/MM/yyyy HHmm\n"
                + "Please try again :)");
    }

    /**
     * Prints message if date/time String format is wrong.
     */
    public String printWrongDateTimeFormatMessage() {
        return ("Uh oh! Looks like your date/time format is wrong!:\n"
                + "Try formatting your date/time as dd/MM/yyyy HHmm.");
    }

    /**
     * Prints message if file is not found.
     */
    public String printFileNotFoundMessage() {
        return ("File not found to save task update. ");
    }

    /**
     * Prints message after task has been successfully added into task list.
     *
     * @param taskList List containing the tasks
     */
    public String confirmTaskAddition(TaskList taskList) {
        return ("I've added this task to your list:\n" + taskList.get(taskList.size() - 1).toString() + "\n"
                + "Now you have " + taskList.size() + " task(s) in the list.");
    }

    /**
     * List tasks in task list that contain the keyword.
     *
     * @param keyword Keyword that task should contain.
     * @param taskList Task list that contains tasks.
     */
    public String printTasksWithKeyword(String keyword, TaskList taskList) {
        int numOfTasks = taskList.size();
        int taskNumber = 0;
        int index = 1;
        String tasks = "";

        while (taskNumber < numOfTasks) {
            if (taskList.get(taskNumber).getDescription().contains(keyword)) {
                tasks += (index + ". " + taskList.get(taskNumber).toString() + "\n");
                index++;
            }
            taskNumber++;
        }

        return ("Here are the matching tasks in your list:\n" + tasks);
    }

    /**
     * Prints error message if there is no keyword included in the find command.
     */
    public String printMissingKeywordMessage() {
        return ("Please include a keyword so Scribbles can search for the tasks you are looking for!\n" +
                "i.e. find [keyword]");
    }

    /**
     * Prints the exiting message of the chatbot.
     *
     * @return Message when user says bye
     */
    public String printExitMessage() {
        return ("Bye! Hope to see you again soon :)");
    }

}
