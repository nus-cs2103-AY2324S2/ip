package ken.ui;

import ken.response.Response;
import ken.task.Task;
import ken.task.TaskList;

import java.util.List;

/**
 * The Ui class is responsible for handling user interface-related messages.
 * It provides methods to display welcome and goodbye messages to the user.
 */
public class Ui {

//    /**
//     * Displays a welcome message to the user.
//     */
//    public void welcomeMessage() {
//        System.out.println("Hi Barbie!");
//        System.out.println("I'm Ken!");
//        System.out.println("What would you like to beach today?\n");
//    }
//
//    /**
//     * Displays a goodbye message to the user.
//     */
//    public void byeMessage() {
//        System.out.println("Beach off!\n");
//    }
//
//    public void deleteMessage(Task task, int size) {
//        System.out.println("Ohh okayy...");
//        System.out.println("deleted task: " + task);
//        System.out.println("Now Barbie has " + size + " tasks in list.\n");
//    }
//
//    public void markTaskMessage(int index, String details) {
//        System.out.println("SUBLIME! Task " + index + " completed!\n " + details);
//    }
//
//    public void invalidTaskMessage(int index) {
//        System.out.println("Barbie has no task " + index);
//    }
//
//    public void unmarkTaskMessage(int index, String details) {
//        System.out.println("ookayy, so task " + index + " is not actually done\n " + details);
//        System.out.println("You are not doing task very well :(");
//    }
//
//    public void listTasksMessage(boolean isListEmpty) {
//
//        System.out.println("Hold my ice cream,");
//
//        if (isListEmpty) {
//            System.out.println("actually, wait, i'm taking my ice cream back");
//            System.out.println("no tasks yet");
//        } else {
//            System.out.println("Your tasks for today: \n");
//        }
//    }
//
//    public void displayMatchingTasksMessage(String keyword) {
//        System.out.println("seeking...\n");
//        System.out.println("These are all the " + keyword + "s in your list:");
//    }
//    public void invalidDeadlineMessage() {
//        System.out.println("That's not how you declare a deadline. p.s. use /by.");
//    }
//
//    public void invalidEventMessage() {
//        System.out.println("That's not how you declare an event. p.s. use /from, and /to.");
//    }
//
//    public void addTaskMessage(Task task, int size) {
//        System.out.println("Got it!");
//        System.out.println("added task: " + task);
//        System.out.println("Now Barbie has " + size + " tasks in list\n");
//    }
//
//    public void tooManyTaskMessage() {
//        System.out.println("Way too many tasks for today Barbie!");
//        System.out.println("Slow the Slayy\n");
//    }
public Response welcomeMessage() {
    return new Response("Hi Barbie!\nI'm Ken!\nWhat would you like to beach today?\np.s. say help if you need!\n");
}

    public Response byeMessage() {
        return new Response("Beach off!\n");
    }

    public Response deleteMessage(Task task, int size) {
        return new Response("Ohh okayy...\nDeleted task: " + task + "\nNow Barbie has " + size + " tasks in list.\n");
    }

    public Response markTaskMessage(int index, String details) {
        return new Response("SUBLIME! Task " + index + " completed!\n " + details);
    }

    public Response invalidTaskMessage(int index) {
        return new Response("Barbie has no task " + index);
    }

    public Response unmarkTaskMessage(int index, String details) {
        return new Response("ookayy, so task " + index + " is not actually done\n " + details +
                "\nYou are not doing task very well :(");
    }

//    public Response listTasksMessage(boolean isListEmpty) {
//        if (isListEmpty) {
//            return new Response("Hold my ice cream,\nactually, wait, I'm taking my ice cream back\nno tasks yet");
//        } else {
//            return new Response("Hold my ice cream,\nYour tasks for today:");
//        }
//    }

//    public Response displayMatchingTasksMessage(String keyword) {
//        return new Response("seeking...\n\nThese are all the " + keyword + "s in your list:");
//    }

    public Response listTasksMessage(boolean isListEmpty, List<Task> tasks) {
        if (isListEmpty) {
            return new Response("Hold my ice cream,\nactually, wait, I'm taking my ice cream back\nno tasks yet");
        } else {
            StringBuilder response = new StringBuilder("Hold my ice cream,\nYour tasks for today:\n");
            for (int i = 0; i < tasks.size(); i++) {
                response.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
            return new Response(response.toString());
        }
    }

    public Response displayMatchingTasksMessage(String keyword, List<Task> matchingTasks) {
        StringBuilder response = new StringBuilder("seeking...\n\nThese are all the ")
                .append(keyword).append("s in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            response.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
        }
        return new Response(response.toString());
    }

    public Response helpMessage() {
        String HELP_LIST = "list\n-> Lists tasks.\n";
        String HELP_TODO = "todo <task>\n-> Adds todo task.\n";
        String HELP_DEADLINE = "deadline <task> /by <due by in YYYY-MM-DD HHmm>\n" +
                "-> Adds task with deadline.\n";
        String HELP_EVENT = "event <task> /from <from in YYYY-MM-DD HHmm> /to <to in YYYY-MM-DD HHmm>\n" +
                "-> Adds event with duration.\n";
        String HELP_DELETE = "delete <task index>\n-> Deletes task numbered 'index'.\n";
        String HELP_HELP = "help\n-> Lists all commands you can give me - like this!\n";
        String HELP_MARK = "mark <task index>\n-> Marks task numbered 'index' as done.\n";
        String HELP_UNMARK = "unmark <task index>\n-> Marks task numbered 'index' as not done.\n";
        String HELP_SEEK = "seek <keyword>\n-> Lists all tasks with 'keyword'.\n";
        String HELP_BYE = "bye\n-> I'll see you next time!\n";
        String tieTogether = "So... what do you want to beach today?\n";
        return new Response(HELP_LIST + HELP_TODO + HELP_DEADLINE + HELP_EVENT
                + HELP_MARK + HELP_UNMARK + HELP_DELETE + HELP_SEEK + HELP_HELP + HELP_BYE + tieTogether);
    }

    public Response invalidDeadlineMessage() {
        return new Response("That's not how you declare a deadline. p.s. use /by.\n");
    }

    public Response invalidEventMessage() {
        return new Response("That's not how you declare an event. p.s. use /from, and /to.\n");
    }

    public Response addTaskMessage(Task task, int size) {
        return new Response("Got it!\nAdded task: " + task + "\nNow Barbie has " + size + " tasks in list\n");
    }

    public Response tooManyTaskMessage() {
        return new Response("Way too many tasks for today Barbie!\nSlow the Slayy\n");
    }
}
