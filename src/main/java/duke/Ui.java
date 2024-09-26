package duke;

import java.util.concurrent.atomic.AtomicInteger;

import duke.task.Task;

/**
 * The `Ui` class represents an Ui interface.
 * It provides methods to print text responses to users.
 */
public class Ui {

    /**
     * Prints all tasks in the given task list.
     *
     * @param tasks Task list containing tasks.
     */
    public String list(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.getItems().size(); i++) {
            Task nextTask = tasks.getItems().get(i);
            sb.append((i + 1) + ". " + nextTask.getDescriptionStatus() + "\n");
        }
        return sb.toString();
    }

    /**
     * Prints all tasks that contains the keyword in the given task list.
     *
     * @param tasks Task list containing tasks.
     * @param keyword A string to be contained in task.
     */
    public String list(TaskList tasks, String keyword) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list: \n");

        AtomicInteger counter = new AtomicInteger(0);

        tasks.getItems()
                .stream()
                .filter(nextTask -> nextTask.getDescriptionStatus().contains(keyword))
                .forEach(nextTask -> {
                    int j = counter.getAndIncrement();
                    sb.append((j + 1) + ". " + nextTask.getDescriptionStatus() + "\n");
                });

        return sb.toString();
    }

    /**
     * Prints a feedback on the change of task status.
     *
     * @param task Task that have changed status.
     */
    public String mark(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getMarkStatus() + "\n");
        sb.append(task.getDescriptionStatus() + "\n");
        return sb.toString();
    }

    /**
     * Prints a feedback on the change of task description.
     *
     * @param task Task that have add tag.
     */
    public String tag(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Alright, this is the current tasks description:\n");
        sb.append(task.getDescriptionStatus() + "\n");
        return sb.toString();
    }

    /**
     * Prints a feedback on the add of task.
     *
     * @param task Task to be added.
     */
    public String add(Task task, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task: " + "\n");
        sb.append(task.getDescriptionStatus() + "\n");
        sb.append("Now you have " + tasks.getItems().size() + " tasks in the list." + "\n");
        return sb.toString();
    }

    /**
     * Prints a feedback on the deletion of task.
     *
     * @param task Task to be deleted.
     */
    public String delete(Task task, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:" + "\n");
        sb.append(task.getDescriptionStatus() + "\n");
        sb.append("Now you have " + (tasks.getItems().size() - 1) + " tasks in the list." + "\n");
        return sb.toString();
    }

    /**
     * Prints program exit message.
     */
    public String exit() {
        // Exit
        return "Alright, I'm always one call away.";
    }


}
