public enum Command {
  INSERT_TODO, VIEW_LIST, INSERT_DEADLINE, INSERT_EVENT, SET_MARK, SET_UNMARK, EXIT, DELETE_TASK, WHAT;

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
        return SET_MARK;
      case "unmark":
        return SET_UNMARK;
      case "bye":
        return EXIT;
      case "delete":
        return DELETE_TASK;
      default:
        return WHAT;
    }
  }
}
