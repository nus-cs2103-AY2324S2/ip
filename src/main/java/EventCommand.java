import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EventCommand implements Command {
  private final String details;

  public EventCommand(String details) {
    this.details = details;
  }

  @Override
  public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws AlpaException {
    try {
      Pattern fromPattern = Pattern.compile("\\s*/from\\s*");
      Pattern toPattern = Pattern.compile("\\s*/to\\s*");
      Matcher fromMatcher = fromPattern.matcher(details);
      Matcher toMatcher = toPattern.matcher(details);
      if (!fromMatcher.find() || !toMatcher.find()) {
        throw new AlpaException("\nInvalid event format, human! Please use '/from' and '/to' to specify the event time.");
      }
      int fromIndex = fromMatcher.start();
      int toIndex = toMatcher.start();
      String description = details.substring(0, fromIndex).trim();
      String startStr = details.substring(fromIndex + fromMatcher.group().length(), toIndex).trim();
      String endStr = details.substring(toIndex + toMatcher.group().length()).trim();
      LocalDateTime startDateTime = DateTimeUtils.parseDateTime(startStr);
      LocalDateTime endDateTime = DateTimeUtils.tryParseEndDateTime(endStr, startDateTime.toLocalDate());
      if (endDateTime.isBefore(startDateTime)) {
        throw new AlpaException("\nEnd time cannot be before start time, human!");
      }
      Event event = new Event(description, startDateTime, endDateTime);
      taskList.addTask(event);
      ui.showAddedTask(event, taskList.getSize());
      storage.saveTasks(taskList.getTasks());
    } catch (DateTimeParseException e) {
      throw new AlpaException("\nInvalid date and time format, human!! Start: '" + details + "'.");
    }
  }

  @Override
  public boolean isExit() {
    return false;
  }
}


