import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * A class that deals with making sense of the user command.
 */
public class Parser {
    /**
     * Analyses user input and translates it to a command.
     *
     * @param input A string representation of user input.
     * @param tasks TaskList stored.
     * @return The respective command.
     */
    public Command parse(String input, TaskList tasks) throws
            NoSuchTaskException, NoContentException,
            NoTimingException, IncorrectTaskTypeException {
        if (input.equals("bye")) { // Bye command.
            return new ByeCommand(tasks);
        }
        if (input.equals("list")) { // List command.
            return new ListCommand(tasks);
        }
        if (input.startsWith("mark")) { // Mark command.
            String[] inputs = input.split(" ");
            if (inputs.length < 2) {
                throw new NoSuchTaskException();
            }
            int idx = Integer.valueOf(inputs[1]);
            if (idx > tasks.getSize()) {
                throw new NoSuchTaskException();
            }
            return new MarkCommand(tasks, idx - 1);
        }
        if (input.startsWith("unmark")) { // Unmark command.
            String[] inputs = input.split(" ");
            if (inputs.length < 2) {
                throw new NoSuchTaskException();
            }
            int idx = Integer.valueOf(inputs[1]);
            if (idx > tasks.getSize()) {
                throw new NoSuchTaskException();
            }
            return new UnmarkCommand(tasks, idx - 1);
        }
        if (input.startsWith("delete")) { // Delete command.
            String[] inputs = input.split(" ");
            if (inputs.length < 2) {
                throw new NoSuchTaskException();
            }
            int idx = Integer.valueOf(inputs[1]);
            if (idx > tasks.getSize()) {
                throw new NoSuchTaskException();
            }
            return new DeleteCommand(tasks, idx - 1);
        } else { // Add command.
            String[] task = input.split(" ", 2);
            String taskType = task[0];
            Task nextTask = null;

            if (taskType.equals("todo")) { // Checks if the task type is todo.
                if (task.length < 2) {
                    throw new NoContentException();
                }
                String content = task[1];
                nextTask = new ToDo(content);
                return new AddCommand(nextTask, tasks);

            } else if (taskType.equals("deadline")) { // Checks if the task type is deadline.
                if (task.length < 2) {
                    throw new NoContentException();
                }
                if (!task[1].contains("/by")) {
                    throw new NoTimingException();
                }
                String[] content = task[1].split(" /by ");
                String[] time = content[1].split(" ");
                int[] timing = Arrays.stream(time[0].split("-"))
                        .mapToInt(Integer::valueOf).toArray();

                if (time.length != 2 || timing.length != 3) {
                    throw new NoTimingException();
                }

                nextTask = new Deadline(
                        content[0],
                        LocalDateTime.of(
                                LocalDate.of(timing[0], timing[1], timing[2]),
                                LocalTime.of(Integer.valueOf(time[1]), 0)));
                return new AddCommand(nextTask, tasks);

            } else if (taskType.equals("event")) { // Checks if the task type is event.
                if (task.length < 2) {
                    throw new NoContentException();
                }
                if (!task[1].contains("/from") || !task[1].contains("/to")) {
                    throw new NoTimingException();
                }
                String[] content = task[1].split(" /from ");
                String text = content[0];
                String[] interval = content[1].split(" /to ");

                // Gets start date and time.
                String[] from = interval[0].split(" ");
                int[] fromDate = Arrays.stream(from[0].split("-"))
                        .mapToInt(Integer::valueOf).toArray();

                //Gets end date and time.
                String[] to = interval[1].split(" ");
                int[] toDate = Arrays.stream(to[0].split("-"))
                        .mapToInt(Integer::valueOf).toArray();

                if (fromDate.length != 3 || toDate.length != 3
                        || from.length != 2 || to.length != 2) {
                    throw new NoTimingException();
                }

                nextTask = new Event(
                        text,
                        LocalDateTime.of(
                                LocalDate.of(fromDate[0], fromDate[1], fromDate[2]),
                                LocalTime.of(Integer.valueOf(from[1]), 0)),
                        LocalDateTime.of(
                                LocalDate.of(toDate[0], toDate[1], toDate[2]),
                                LocalTime.of(Integer.valueOf(to[1]), 0)));
                return new AddCommand(nextTask, tasks);

            } else {
                throw new IncorrectTaskTypeException();
            }
        }
    }
}
