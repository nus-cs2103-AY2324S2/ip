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
