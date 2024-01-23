public enum Command {
  INSERT_TODO, VIEW_LIST, INSERT_DEADLINE, INSERT_EVENT, UPDATE_MARK, UPDATE_UNMARK, BYE, WHAT;

  public static Command parseCommand(String in) {
    in = in.toLowerCase();

    switch (in) {
      case "todo":
        return INSERT_TODO;
      case "list":
        return VIEW_LIST;
      case "deadline":
        return INSERT_DEADLINE;
      case "event":
        return INSERT_EVENT;
      case "mark":
        return UPDATE_MARK;
      case "unmark":
        return UPDATE_UNMARK;
      case "bye":
        return BYE;
      default:
        return WHAT;
    }
  }
}
