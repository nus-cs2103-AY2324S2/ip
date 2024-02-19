package shodan.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.StringJoiner;

import shodan.ShodanException;
import shodan.tasks.impl.Deadline;
import shodan.tasks.impl.Event;
import shodan.tasks.impl.Todo;

/**
 * This class contains static methods that are responsible for
 * parsing new tasks entered by the user.
 */
public class TaskParser {
    /**
     * Parse task.
     *
     * @param tokens the input string, split into words
     * @param type   the type of task to try to parse.
     * @return a new Task.
     * @throws ShodanException if any errors occurred during parsing.
     */
    public static Task parse(List<String> tokens, TaskType type) throws ShodanException {
        Task newTask = null;
        StringJoiner taskName = new StringJoiner(" ");
        StringJoiner startDate = new StringJoiner(" ");
        StringJoiner endDate = new StringJoiner(" ");
        int state = 0;
        int sectionNum = 1;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yy HHmmg");
        switch (type) {
        case TODO:
            newTask = new Todo(String.join(" ", tokens));
            break;
        case DEADLINE:
            for (String token : tokens) {
                if (token.equals("/by")) {
                    state = 1;
                    sectionNum++;
                    continue;
                }
                switch (state) {
                case 0:
                    taskName.add(token);
                    break;
                case 1:
                    endDate.add(token);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + state);
                }
            }
            if (sectionNum < 2) {
                throw new ShodanException("Adding a deadline requires a end date specified with /by. "
                        + "For example:\n\tdeadline return books /by 1/1/2024 1200");
            }
            if (endDate.toString().isBlank()) {
                throw new ShodanException("The /by field cannot be empty. Please specify a end date/time.");
            }
            try {
                newTask = new Deadline(taskName.toString(), LocalDateTime.parse(endDate.toString(), dateTimeFormatter));
            } catch (DateTimeParseException e) {
                throw new ShodanException("Failed to parse entered date. Please use the DD/MM/YYYY TTTT format.");
            }
            break;
        case EVENT:
            for (String token : tokens) {
                if (token.equals("/to")) {
                    state = 1;
                    sectionNum++;
                    continue;
                } else if (token.equals("/from")) {
                    state = 2;
                    sectionNum++;
                    continue;
                }
                switch (state) {
                case 0:
                    taskName.add(token);
                    break;
                case 1:
                    endDate.add(token);
                    break;
                case 2:
                    startDate.add(token);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + state);
                }
            }
            if (sectionNum < 3) {
                throw new ShodanException("Adding an event requires both a start and end date/time using /from and /to."
                        + " For example:\n\tevent attend birthday party /from 1/1/2024 1200 /to 1/1/2024 1300");
            }
            if (startDate.toString().isBlank()) {
                throw new ShodanException("The /from field cannot be empty. Please specify a end date/time.");
            }
            if (endDate.toString().isBlank()) {
                throw new ShodanException("The /to field cannot be empty. Please specify a end date/time.");
            }
            try {
                newTask = new Event(taskName.toString(),
                        LocalDateTime.parse(startDate.toString(), dateTimeFormatter),
                        LocalDateTime.parse(endDate.toString(), dateTimeFormatter));
            } catch (DateTimeParseException e) {
                throw new ShodanException("Failed to parse entered date. Please use the DD/MM/YYYY TTTT format.");
            }
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + type);
        }
        assert newTask != null : "Parsed task is null";
        if (newTask.getName().isBlank()) {
            throw new ShodanException("You need to specify a name for your task.");
        }
        return newTask;
    }
}
