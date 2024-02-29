package ken.ui;

import ken.response.Response;
import ken.task.Task;

import java.util.List;

/**
 * The Ui class is responsible for handling user interface-related messages.
 * It provides methods to display welcome and goodbye messages to the user.
 */
public class Ui {

    /**
     * Gets Welcome Response.
     *
     * @return Response with welcome message.
     */
    public Response getWelcomeMessage() {
        return new Response("Hi Barbie!\nI'm Ken!\nWhat would you like to beach today?\np.s. say help if you need!\n");
    }

    /**
     * Gets Bye Response.
     *
     * @return Response with bye message.
     */
    public Response getByeMessage() {
        return new Response("Beach off!\n");
    }

    /**
     * Gets Delete Response.
     *
     * @param task task to be deleted.
     * @param size number of tasks left in list.
     * @return Response with delete message.
     */
    public Response getDeleteMessage(Task task, int size) {
        return new Response("Ohh okayy...\nDeleted task: " + task + "\nNow Barbie has " + size + " tasks in list.\n");
    }

    /**
     * Gets Mark Task Response.
     *
     * @param index index of task to be marked as done.
     * @param details details of task to be marked as done.
     * @return Response with mark task message.
     */
    public Response getMarkTaskMessage(int index, String details) {
        return new Response("SUBLIME! Task " + index + " completed!\n " + details);
    }

    /**
     * Gets Already Mark Task Response.

     * @return Response with already mark task message.
     */
    public Response getAlreadyMarkTaskMessage() {
        return new Response("chill, you've already done it");
    }

    /**
     * Gets Invalid Task Response.
     *
     * @param index index of non-existing task.
     * @return Response with invalid task message.
     */
    public Response getInvalidTaskMessage(int index) {
        return new Response("Barbie has no task " + index);
    }

    /**
     * Gets Unmark Task Response.
     *
     * @param index of task to be unmarked as done.
     * @param details of task to be unmarked as done.
     * @return Response with unmark task message.
     */
    public Response getUnmarkTaskMessage(int index, String details) {
        return new Response("ookayy, so task " + index + " is not actually done\n " + details +
                "\nYou are not doing task very well :(");
    }

    /**
     * Gets Already Unmark Task Response.

     * @return Response with already unmark task message.
     */
    public Response getAlreadyUnmarkTaskMessage() {
        return new Response("but it's already not done??");
    }

    /**
     * Gets List Task Response.
     *
     * @param isListEmpty boolean if list is empty.
     * @param tasks list of tasks in list.
     * @return Response with list task message.
     */
    public Response getListTasksMessage(boolean isListEmpty, List<Task> tasks) {
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

    /**
     * Gets Seek Response.
     *
     * @param keyword to find matching tasks in list.
     * @param matchingTasks all tasks that match keyword in list.
     * @return Response with matching tasks message.
     */
    public Response getDisplayMatchingTasksMessage(String keyword, List<Task> matchingTasks) {
        StringBuilder response = new StringBuilder("seeking...\n\nThese are all the ")
                .append(keyword).append("s in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            response.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
        }
        return new Response(response.toString());
    }

    /**
     * Gets Help Response.
     *
     * @return Response with help message.
     */
    public Response getHelpMessage() {
        String HELP_LIST = "list\n-> Lists tasks.\n";
        String HELP_TODO = "todo <task>\n-> Adds todo task.\n";
        String HELP_DEADLINE = "deadline <task> /by\n <due by in YYYY-MM-DD HHmm>\n" +
                "-> Adds task with deadline.\n";
        String HELP_EVENT = "event <task> /from\n <from in YYYY-MM-DD HHmm>\n /to <to in YYYY-MM-DD HHmm>\n" +
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

    /**
     * Gets Invalid Deadline Response.
     *
     * @return Response with invalid deadline message.
     */
    public Response getInvalidDeadlineMessage() {
        return new Response("That's not how you declare a deadline. p.s. use /by.\n");
    }

    /**
     * Gets Invalid Event Response.
     *
     * @return Response with invalid event message.
     */
    public Response getInvalidEventMessage() {
        return new Response("That's not how you declare an event. p.s. use /from, and /to.\n");
    }

    /**
     * Gets Add Task Response.
     *
     * @return Response with add task message.
     */
    public Response getAddTaskMessage(Task task, int size) {
        return new Response("Got it!\nAdded task: " + task + "\nNow Barbie has " + size + " tasks in list\n");
    }

    /**
     * Gets Too Many Tasks Response.
     *
     * @return Response with too many tasks message.
     */
    public Response getTooManyTaskMessage() {
        return new Response("Way too many tasks for today Barbie!\nSlow the Slayy\n");
    }
}
