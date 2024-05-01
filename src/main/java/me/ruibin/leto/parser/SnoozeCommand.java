package me.ruibin.leto.parser;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.ruibin.leto.tasklist.Deadline;
import me.ruibin.leto.tasklist.InvalidTaskException;
import me.ruibin.leto.tasklist.Task;
import me.ruibin.leto.tasklist.TaskList;
import me.ruibin.leto.ui.Ui;

/** Command Class for snoozing a Deadline */
public class SnoozeCommand implements Function<String, Result> {
    /**
     * Extends the deadline of a specified task.
     *
     * @param command the command from the user
     * @return <code>Result</code>
     */
    @Override
    public Result apply(String command) {
        Result r = Result.makeResultOk("");
        Pattern pattern = Pattern.compile("(?i)snooze (\\d+) /by (\\d+)\\s*(d|da|day|days)?");
        Matcher matcher = pattern.matcher(command);

        if (!matcher.matches()) {
            r.updateMessage(Ui.shortSay("Invalid command format. Use: snooze <task number> /by <number> d[ays]"));
            return r;
        }

        try {
            int index = Integer.parseInt(matcher.group(1)) - 1; // Adjust for 0-based indexing
            int days = Integer.parseInt(matcher.group(2));

            Task task = TaskList.getTaskByIndex(index);
            if (!(task instanceof Deadline)) {
                throw new InvalidTaskException("Only deadline tasks can be snoozed.");
            }
            Deadline dl = (Deadline) task;
            dl.extendDeadline(days);
            r.updateMessage(Ui.letoSpeak("Deadline extended by " + days + " days for task: " + task));
        } catch (NumberFormatException e) {
            r.updateWithException("Invalid task index or day count.", e);
        } catch (InvalidTaskException e) {
            r.updateWithException(e.printException(), e);
        }

        return r;
    }
}
