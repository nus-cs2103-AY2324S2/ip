package hammy.response;//package duke;


import hammy.task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;


/**
 * The Ui class manages user interface interactions and displays messages to the user.
 */
public class Ui {
    /** The name of the bot. */
    String botName;

    private static final String[] welcomeMessages = {
            "I miss you!",
            "Hiii! What can I do for you today?",
            "Wazzup! jk i luv u ~",
            "Heyloo! Soo glad to see you!"
    };

    private static final String[] hammyMessages = {
            "Do you know what starts with success?" + "\nS/U.",
            "Why u call me??",
            "Ok fine. I luv u ~~",
            "The 'S' in NUS stands for S/U",
            "Do you know what someone will say when they have low CAP?" + "\nNo cap.",
            "I don't know what time it is, but you probably need some rest now."
    };

    /**
     * Constructs a Ui object with the specified bot name.
     *
     * @param botName the name of the bot
     */
    public Ui(String botName) {
        this.botName = botName;
    }

    /**
     * Sends a welcome message to the user.
     *
     * @return a String of welcome message.
     */
    public String sendWelcome() {
        Random random = new Random();
        int index = random.nextInt(welcomeMessages.length);
        return welcomeMessages[index];
    }

    /**
     * Sends a special message from Hammy.
     *
     * @return a String of Hammy message.
     */
    public String getHammyMessage() {
        Random random = new Random();
        int index = random.nextInt(hammyMessages.length);
        return hammyMessages[index];
    }

    public String savedTasks() {
        return "Your tasks are successfully saved. You can now exit the program." +
                "\n--> Tip: You can type \"list\" to check your current list.";
    }

    /**
     * Sends this when user input is not unrecognized.
     *
     * @return a String when user input is invalid
     */
    public String badUserInput() {
        return botName + " does not understand you :((" +
                "\n--> Tip: Enter \"help\" to search for available commands.";
    }

    /**
     * Sends the list of current tasks
     * Also sends the amount of tasks
     *
     * @param taskList the task lists
     * @return a String to show the task list
     */
    public String showTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return "There are no tasks!";
        } else {
            String tasksString = "Here are the tasks in your list:";
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                tasksString = tasksString + "\n" + (i + 1) + ". " + task;
            }
            return tasksString;
        }
    }

    /**
     * Sends the list of completed tasks
     *
     * @param taskList the task lists
     * @return a String to show completed tasks in the task list
     */
    public String showDoneTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return "There are no tasks!";
        } else {
            String tasksString = "Here are completed tasks in your list:";
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                if (!task.getStatus()) {
                    continue;
                }
                tasksString = tasksString + "\n" + (i + 1) + ". " + task;
            }
            return tasksString;
        }
    }

    /**
     * Sends the list of incomplete tasks
     *
     * @param taskList the task lists
     * @return a String to show incomplete tasks in the task list
     */
    public String showUndoneTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return "There are no tasks!";
        } else {
            String tasksString = "Here are task which are not completed in your list:";
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                if (task.getStatus()) {
                    continue;
                }
                tasksString = tasksString + "\n" + (i + 1) + ". " + task;
            }
            return tasksString;
        }
    }

    /**
     * Sends the list of completed tasks on top and incomplete tasks below
     *
     * @param taskList the task lists
     * @return a String to show completed tasks in the task list on top and incomplete tasks below
     */
    public String doneUndoneList(ArrayList<Task> taskList) {
        return showDoneTaskList(taskList) +
                "\n" +
                "\n" +
                showUndoneTaskList(taskList);
    }

    /**
     * Sends the list of incomplete tasks on top and completed tasks below
     *
     * @param taskList the task lists
     * @return a String to show incomplete tasks in the task list on top and completed tasks below
     */
    public String undoneDoneList(ArrayList<Task> taskList) {
        return showUndoneTaskList(taskList) +
                "\n" +
                "\n" +
                showDoneTaskList(taskList);
    }

    /**
     * Sends the list of tasks in alphabetical order
     *
     * @param taskList the task lists
     * @return a String to show tasks in alphabetical order
     */
    public String sortAlphabetically(ArrayList<Task> taskList) {
        // Sort tasks using a custom comparator
        Collections.sort(taskList, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                // Compare tasks based on their descriptions
                 return task1.getDescription().compareToIgnoreCase(task2.getDescription());
            }
        });
        // Build a string representation of the sorted tasks
        StringBuilder sortedTasks = new StringBuilder("Task list (sorted alphabetically)");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            sortedTasks.append("\n").append(i + 1).append(". ").append(task);
        }
        return sortedTasks.toString();
    }

    public String addNewTask(Task addedTask, int taskListSize) {
        return "Got it. I have added:\n"
                + " " + addedTask + "\n"
                + "There are " + taskListSize + " tasks in the task list currently.";
    }

    public String deleteTask(Task deletedTask, int taskListSize) {
        return "Task has been successfully removed:\n"
                + " " + deletedTask + "\n"
                + "There are " + taskListSize + " tasks in the task list currently.";
    }

    public String deleteTasks(String deletedTasks, ArrayList<Integer> invalidIndexes, int taskListSize) {
        return "Tasks have been successfully removed:\n"
                + deletedTasks
                + "There are " + taskListSize + " tasks in the task list currently.";
    }

    public String clearedTasks() {
        return "All tasks have been successfully removed." +
                "\nEnter \"save\" to permanenetly save your actions.";
    }

    public String invalidTaskIndex() {
        return "Invalid Task Index!";
    }

    public String markAsDone(Task doneTask) {
        return "Nice! I've marked this task as done:\n"
                + "  " + doneTask;
    }

    public String markAsUndone(Task undoneTask) {
        return "OK, I've marked this task as not done:\n"
                + "  " + undoneTask;
    }

    /**
     * Sends when user enter invalid index
     *
     * @return a String to remind the user regarding invalid index
     */
    public String noIndexDeleteTask() {
        return "Please provide the task index you want to delete.";
    }

    /**
     * Sends when user enter invalid index
     *
     * @return a String to remind the user regarding invalid index
     */
    public String noIndexMarkAsDone() {
        return "Please provide the task index to mark as done.";
    }

    /**
     * Sends when user enter invalid index
     *
     * @return a String to remind the user regarding invalid index
     */
    public String noIndexMarkAsUndone() {
        return "Please provide the task index to mark as not done.";
    }

    public String invalidDateInput() {
        return "Error input: Date format is invalid (Date format: YYYY-MM-DD)";
    }

    public String insufficientTodoDescription() {
        return "Please provide a description for your Todo task.";
    }

    public String insufficientDeadline() {
        return "Please provide a deadline for your Deadline task.";
    }

    public String insufficientEventStartTimeEndTime() {
         return "Please provide both starting time and ending time for your event task.";
    }

    public String insufficientEventStartTime() {
        return "Please provide a starting time for your event task.";
    }

    public String insufficientEventEndTime() {
        return "Please provide a ending time for your event task.";
    }

    public String invalidEventStartingTimeAndEndingTime() {
        return "Error input: Start time should be earlier than end time.";
    }

    public String noMatchingTasks(String keyword) {
        return "There are no tasks matching with '" + keyword + "'.";
    }

    public String noKeywordsForSearching() {
        return "Please provide a keyword to search.";
    }


    public String help() {
        return "Welcome to " + botName + " chatbot!" +
                "\n" + "You can follow instructions below to use this chatbot!" +
                "\n" +
                "\n" + "todo description" +
                "\n" + "--> Shortcut: /t description" +
                "\n" + "--> Add todo task" +
                "\n" +
                "\n" + "deadline description /by date"  +
                "\n" + "--> Shortcut: (/d description /by date)" +
                "\n" + "--> Add deadline task (Date format: YYYY-M-D)" +
                "\n" +
                "\n" + "event description /from date /to date" +
                "\n" + "--> Shortcut: (/e description /from date /to date)" +
                "\n" + "--> Add event task (Date format: YYYY-M-D)" +
                "\n" +
                "\n" + "list (ls)" +
                "\n" + "--> Shortcut: /ls" +
                "\n" + "--> list out all tasks in the task list" +
                "\n" +
                "\n" + "list done" +
                "\n" + "--> Shortcut: /ls done" +
                "\n" + "--> list out all completed tasks in the task list" +
                "\n" +
                "\n" + "list undone" +
                "\n" + "--> Shortcut: /ls undone" +
                "\n" + "--> list out all uncompleted tasks in the task list" +
                "\n" +
                "\n" + "list top undone" +
                "\n" + "--> Shortcut: /ls top undone" +
                "\n" + "--> list out all uncompleted tasks on the top and completed tasks below" +
                "\n" +
                "\n" + "list top done" +
                "\n" + "--> Shortcut: /ls top done" +
                "\n" + "--> list out all completed tasks on the top and uncompleted tasks below" +
                "\n" +
                "\n" + "list a" +
                "\n" + "--> Shortcut: /ls a" +
                "\n" + "--> list out all tasks in the task list alphabetically" +
                "\n" +
                "\n" + "delete taskIndex" +
                "\n" + "--> Shortcut: /del taskIndex" +
                "\n" + "--> delete certain task" +
                "\n" +
                "\n" + "clear" +
                "\n" + "--> clear all tasks in task list" +
                "\n" +
                "\n" + "done taskIndex" +
                "\n" + "--> mark certain task as done" +
                "\n" +
                "\n" + "undone taskIndex" +
                "\n" + "--> mark certain task as not done" +
                "\n" +
                "\n" + "search keyword" +
                "\n" + "--> Shortcut: /s keyword" +
                "\n" + "--> search tasks matching with keyword" +
                "\n" +
                "\n" + "save" +
                "\n" + "--> save the tasks locally (IMPORTANT!)" +
                "\n" +
                "\n" + "hi" +
                "\n" + "--> I will say Hiii to you." +
                "\n" +
                "\n" + "exit, bye or quit" +
                "\n" + "--> exit the program instantly (without saving the tasks!)" +
                "\n" +
                "\n" + "hammy" +
                "\n" + "--> Surpise!" +
                "\n" +
                "\n" + "That's the end of the features (more is yet to come!)";
    }

}
