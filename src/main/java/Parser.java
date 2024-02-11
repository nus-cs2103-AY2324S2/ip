public class Parser {

  public static Command parse(String userInput) throws AlpaException {
    String[] words= userInput.trim().split("\\s+", 2);
    String commandWord = words[0];
    String taskInfo = words.length > 1 ? words[1] : "";

    CommandType commandType = CommandType.fromString(commandWord);

    switch (commandType) {
    case BYE:
      return new ByeCommand();
    case LIST:
      return new ListCommand();
    case MARK:
      return new MarkCommand(taskInfo);
    case UNMARK:
      return new UnmarkCommand(taskInfo);
    case TODO:
      return new TodoCommand(taskInfo);
    case DEADLINE:
      return new DeadlineCommand(taskInfo);
    case EVENT:
      return new EventCommand(taskInfo);
    case DELETE:
      return new DeleteCommand(taskInfo);
    case INVALID:
    default:
      return new InvalidCommand("Sorry human, but I don't know what that means :-(");
    }
  }
}
