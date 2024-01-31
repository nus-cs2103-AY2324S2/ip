import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {

    private String arguments;

    public AddDeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws InvalidArgumentException, InvalidDateTimeFormatException {
        try {
            String[] splitDate = this.arguments.split(" /by ", 2);

            LocalDateTime dueDateTime = LocalDateTime.parse(splitDate[1],
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));

            Task newTask = new Deadline(splitDate[0], dueDateTime);
            taskList.add(newTask);
            ui.addTask(newTask, taskList.getLength());

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("DEADLINE");

        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException("DEADLINE");
        }
    }
}
