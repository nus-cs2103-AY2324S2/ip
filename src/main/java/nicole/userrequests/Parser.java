package nicole.userrequests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nicole.nicoleexceptions.NicoleException;
import nicole.task.Task;

public class Parser {
    public Parser() {
    }

    /**
     * Checks the validity of the user request.
     *
     * @param name the user request.
     * @return the appropriate task for the user request.
     * @throws NicoleException if the request is unknown or in the wrong format.
     */
    protected static Task parseRequest(String name) throws NicoleException {
        Pattern todoPattern = Pattern.compile("^todo(?: (.*?))?$");
        Pattern deadlinePattern = Pattern.compile("^deadline(?: (.*?))?(?: by (\\d{4}-\\d{2}-\\d{2}))?$");
        Pattern eventPattern = Pattern.compile("^event(?: (.*?))?(?: "
                + "from (\\d{4}-\\d{2}-\\d{2}) at (\\d{2}:\\d{2}:\\d{2}) "
                + "to (\\d{4}-\\d{2}-\\d{2}) at (\\d{2}:\\d{2}:\\d{2}))?$");
        Matcher todoMatcher = todoPattern.matcher(name);
        Matcher deadlineMatcher = deadlinePattern.matcher(name);
        Matcher eventMatcher = eventPattern.matcher(name);

        if (todoMatcher.matches()) {
            return Task.taskFactory(todoMatcher.group(1), 'T');
        } else if (deadlineMatcher.matches()) {
            return Task.taskFactory(
                        deadlineMatcher.group(1) + " by " + deadlineMatcher.group(2),
                        'D');
        } else if (eventMatcher.matches()) {
            return Task.taskFactory(eventMatcher.group(1)
                        + " from "
                        + eventMatcher.group(2) + " at " + eventMatcher.group(3)
                        + " to "
                        + eventMatcher.group(4) + " at " + eventMatcher.group(5), 'E');
        } else {
            throw new NicoleException("What does this mean? "
                    + "Send \"help\" if you want to know what commands I can help you with");
        }
    }
}
