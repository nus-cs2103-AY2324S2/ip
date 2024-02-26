package dave.commands;

import dave.Storage;
import dave.Ui;
import dave.TaskList;
import dave.tasks.Task;
import dave.utils.DateTimeUtil;

public class TaskRemindersCommand extends Command {
    /** Beginning of reminder message. */
    private static final String REMINDER_MSG = String.format(
            "These are the tasks due within a week\n(%s - %s):",
            DateTimeUtil.getCurrentTimeAsString(), DateTimeUtil.getAWeekFromNowAsString());

    /** Beginning of no tasks due message. */
    private static final String NO_TASKS_DUE_MSG = "Dave did not find any tasks due within a week."
            + "\nGood job for completing todo tasks and upcoming tasks early!";

    /** Beginning of todo or expired tasks due message. */
    private static final String TODO_OR_EXPIRED_TASKS_DUE_MSG = "You still have the following"
            + " TODO/expired tasks not done yet:";

    /** Format of printing tasks as string. */
    private static final String TASK_AS_STRING = "\n    - %s";

    /**
     * {@inheritDoc}
     * Shows tasks due in a week alongside todo tasks not done and expired tasks.
     * 
     * @return Message to user showing tasks due in a week alongside todo tasks not
     *         done and expired tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String tasksWithDueDateMsg = REMINDER_MSG;
        String todoOrExpiredTasksReminderMsg = TODO_OR_EXPIRED_TASKS_DUE_MSG;
        String remindersMsg = "";
        boolean haveTasksDue = false;
        boolean haveTodosOrExpiredTasks = false;
        for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
            Task currentTask = taskList.getTask(i);
            if (currentTask.isTaskDueWithinAWeek()) {
                if (!currentTask.getIsDone()) {
                    haveTasksDue = true;
                    tasksWithDueDateMsg += String.format(TASK_AS_STRING, currentTask.toString());
                }
                continue;
            }
            if (!currentTask.getIsDone()) {
                haveTodosOrExpiredTasks = true;
                todoOrExpiredTasksReminderMsg += String.format(TASK_AS_STRING, currentTask.toString());
            }
        }
        if (haveTasksDue) {
            remindersMsg += tasksWithDueDateMsg + "\n\n";
        }
        if (haveTodosOrExpiredTasks) {
            remindersMsg += todoOrExpiredTasksReminderMsg;
        }
        if (!haveTasksDue && !haveTodosOrExpiredTasks) {
            return NO_TASKS_DUE_MSG;
        }
        return remindersMsg;
    }

    /**
     * {@inheritDoc}
     * Not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}