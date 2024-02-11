import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineCommand implements Command {
  private final String details;

  public DeadlineCommand(String details) {
    this.details = details;
  }

  @Override
  public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws AlpaException {
    try {
      String[] parts = this.details.split("\\s*/by\\s*", 2);
      if (parts.length < 2) {
        throw new AlpaException("\nInvalid deadline format, human! Use '/by' to specify the deadline.");
      }
      String description = parts[0].trim();
      String deadlineStr = parts[1].trim();
      LocalDateTime parsedDeadlineDateTime = DateTimeUtils.parseDeadlineDateTime(deadlineStr);
      Deadline deadline = new Deadline(description, parsedDeadlineDateTime);
      taskList.addTask(deadline);
      ui.showAddedTask(deadline, taskList.getSize());
      storage.saveTasks(taskList.getTasks());
    } catch (DateTimeParseException e) {
      throw new AlpaException("\nInvalid date or time format, human!!");
    }
  }

  @Override
  public boolean isExit() {
    return false;
  }
}
