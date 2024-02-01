package duke;

import java.util.Arrays;
import java.util.List;

public class Parser {

  private static final String BY_CMD = "/by";
  private static final String FROM_CMD = "/from";
  private static final String TO_CMD = "/to";

  private static String cmdJoin(String[] xs) {
    return String.join(" ", xs);
  }

  private static boolean isNumber(String str) {
    return str.matches("-?\\d+(\\.\\d+)?");
  }

  static <T> T[] range(T[] xs, int a, int b) {
    return Arrays.copyOfRange(xs, a, b);
  }

  static String[] parseCommand(String cmdString) throws DukeException {
    String[] cmdSplit = cmdString.split(" ");
    String command = cmdSplit[0];
    switch (command) {
      case "end":
      case "list":
        return new String[] { command };
      case "mark":
      case "unmark":
      case "delete":
        {
          String ferr1 = "%s command: expected an integer argument.";
          if (cmdSplit.length != 2) throw new DukeException(
            String.format(ferr1, command)
          );
          String idxString = cmdSplit[1];
          if (!isNumber(idxString)) throw new DukeException(
            String.format(ferr1, command)
          );
          return new String[] { command, idxString };
        }
      case "find":
      case "todo":
        {
          String ferr1 = "%s command: %s cannot be empty.";
          if (cmdSplit.length < 2) throw new DukeException(
            String.format(
              ferr1,
              command,
              command.equals("find") ? "query" : "description"
            )
          );
          String argument = cmdJoin(range(cmdSplit, 1, cmdSplit.length));
          return new String[] { command, argument };
        }
      case "deadline":
        {
          List<String> cmds = Arrays.asList(cmdSplit);
          String ferr1 = "deadline command: expected `%s` argument.";
          String ferr2 = "deadline command: %s description cannot be empty.";
          if (!cmds.contains(BY_CMD)) throw new DukeException(
            String.format(ferr1, BY_CMD)
          );
          int by_idx = cmds.indexOf(BY_CMD);
          String taskStr = cmdJoin(range(cmdSplit, 1, by_idx));
          String deadline = cmdJoin(range(cmdSplit, by_idx + 1, cmds.size()));
          if (taskStr.length() == 0) throw new DukeException(
            String.format(ferr2, "task")
          );
          if (deadline.length() == 0) throw new DukeException(
            String.format(ferr2, "deadline")
          );
          return new String[] { command, taskStr, deadline };
        }
      case "event":
        {
          List<String> cmds = Arrays.asList(cmdSplit);
          String ferr1 = "event command: expected `%s` argument.";
          String ferr2 = "event command: %s description cannot be empty.";
          String ferr3 =
            "event command: `%s` argument expected before `%s` argument.";
          if (!cmds.contains(FROM_CMD)) throw new DukeException(
            String.format(ferr1, FROM_CMD)
          );
          if (!cmds.contains(TO_CMD)) throw new DukeException(
            String.format(ferr1, TO_CMD)
          );
          int fromIdx = cmds.indexOf(FROM_CMD);
          int toIdx = cmds.indexOf(TO_CMD);
          if (toIdx < fromIdx) throw new DukeException(
            String.format(ferr3, FROM_CMD, TO_CMD)
          );
          String taskStr = cmdJoin(range(cmdSplit, 1, fromIdx));
          String fromStr = cmdJoin(range(cmdSplit, fromIdx + 1, toIdx));
          String toStr = cmdJoin(range(cmdSplit, toIdx + 1, cmds.size()));
          if (taskStr.length() == 0) throw new DukeException(
            String.format(ferr2, "task")
          );
          if (fromStr.length() == 0) throw new DukeException(
            String.format(ferr2, "from")
          );
          if (toStr.length() == 0) throw new DukeException(
            String.format(ferr2, "to")
          );
          return new String[] { command, taskStr, fromStr, toStr };
        }
      default:
        throw new DukeException(
          String.format("Unhandled command: %s", command)
        );
    }
  }
}
