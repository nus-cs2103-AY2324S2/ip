package ui;

import tasklist.TaskList;

public class Ui {

    /**
     * Constructor of Ui for chatbot Scribbles
     */
    public Ui() {
    }

    /**
     * Prints the greeting message of the chatbot.
     */
    public void greet() {
        System.out.print("Hello! I'm Scribbles :) What can I do for you? \n");
    }

    /**
     * Prints the error message if user inputs an invalid input into chatbot.
     */
    public void invalidInputMessage() {
        System.out.println("Sorry, Scribbles was unable to understand your instructions :(\n" +
                "Try formatting your instructions as follows: \n" +
                "- type \"list\" to view your current list of tasks\n" +
                "- type \"mark [index]\" to mark task at index as completed\n" +
                "- type \"unmark [index]\" to mark task at index as incomplete\n" +
                "- type \"todo [task]\" to insert to-do task into your list\n" +
                "- type \"deadline [task] /by [date]\" to insert task into your list with deadline as date\n" +
                "- type \"event [task] /from [start] to [end]\" " +
                "to insert task into your list with a start and end duration\n" +
                "- type \"delete [index]\" to remove a task at index from your list\n" +
                "Please try again :)\n");
    }

    /**
     * Prints list of tasks in task list.
     *
     * @param taskList List which contains the tasks
     */
    public void listTask(TaskList taskList) {
        int numOfTasks = taskList.size();
        int index = 1;

        System.out.println("Here are the tasks in your list:");
        while (numOfTasks != 0) {
            System.out.println(index + ". " + taskList.get(index - 1).toString());
            numOfTasks--;
            index++;
        }
    }

    /**
     * Prints message if index input by user is out of bounds.
     *
     * @param taskList List containing the tasks
     */
    public void invalidIndexMessage(TaskList taskList) {
        int numOfTasks = taskList.size();
        System.out.println("Please check that you've input a valid index! ");
        System.out.println("You currently only have " + numOfTasks + " task(s) in your list.\n");
    }

    public void taskDeletionMessage(String taskDeleted, TaskList taskList){
        System.out.println("I've removed this task from your list:");
        System.out.println(taskDeleted);
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.\n");
    }

    /**
     * Print message after marking task instruction is called
     *
     * @param index Index of task to mark
     * @param taskList List containing the tasks
     */
    public void markCompletedMessage(int index, TaskList taskList) {
        int numOfTasks = taskList.size();
        if (index <= numOfTasks) {
            System.out.println("Nice! I've marked task number " + index + " as done: \n" +
                    taskList.get(index - 1).toString() + "\n");
        } else {
            // if task index does not exist
            System.out.println("Uh oh! looks like that task does not exist in your list.\n" +
                    "You currently only have " + numOfTasks + " task(s) in your list.\n");
        }
    }

    /**
     * Prints message after "unmark" task instruction is called.
     *
     * @param index Index of task to unmark
     * @param taskList List containing the tasks
     */
    public void markIncompleteMessage(int index, TaskList taskList) {
        int numOfTasks = taskList.size();
        if (index <= numOfTasks) {
            System.out.println("Okay! I've marked task number " + index + " as incomplete: \n" +
                    taskList.get(index - 1).toString() + "\n");
        } else {
            // message if task index does not exist
            System.out.println("Uh oh! looks like that task does not exist in your list.\n" +
                    "You currently only have " + numOfTasks + " task(s) in your list.\n");
        }
    }

    /**
     * Prints message if there is missing information from user input when adding tasks.
     */
    public void taskMissingInformationMessage() {
        System.out.println("Uh oh! Looks like there's missing information in your instructions!\n" +
                "Ensure your tasks are formatted as follows: \n" +
                "- type \"todo [task]\" to insert to-do task into your list\n" +
                "- type \"deadline [task] /by [date/time]\" to insert task into your list " +
                "with [date/time] in the format dd/MM/yyyy HHmm\n" +
                "- type \"event [task] /from [start date/time] to [end date/time]\" " +
                "to insert task into your list with a start and end date/time in format dd/MM/yyyy HHmm\n" +
                "Please try again :)\n");
    }

    /**
     * Prints message if date/time String format is wrong.
     */
    public void wrongDateTimeFormatMessage() {
        System.out.println("Uh oh! Looks like your date/time format is wrong!: ");
        System.out.println("Try formatting your date/time as dd/MM/yyyy HHmm.\n");
    }

    /**
     * Prints message if file is not found.
     */
    public void fileNotFoundMessage() {
        System.out.println("File not found to save task update. ");
    }

    /**
     * Prints message after task has been successfully added into task list.
     *
     * @param taskList List containing the tasks
     */
    public void confirmTaskAddition(TaskList taskList) {
        System.out.println("I've added this task to your list: ");
        System.out.println(taskList.get(taskList.size() - 1).toString());
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.\n");
    }

    /**
     * Prints the exiting message of the chatbot.
     */
    public void exit() {
        System.out.println("Bye! Hope to see you again soon :)");
    }

}
