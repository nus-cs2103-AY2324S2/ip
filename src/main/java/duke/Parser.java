package duke;

import java.util.Arrays;

public class Parser {

    public Parser () {

    }

    public Command parse(String input) throws DukeException{
        String[] parts = input.split(" ");
        switch (parts[0]) {
            case "list":
                return new Command(Duke.CommandType.LIST);
            case "mark":
                try {
                    Integer.parseInt(parts[1]);
                    String[] args = new String[]{parts[1]};
                    return new Command(Duke.CommandType.MARK, args);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    throw new DukeException("Incorrect usage - please provide the task number to be marked done");
                }
            case "unmark":
                try {
                    Integer.parseInt(parts[1]);
                    String[] args = new String[]{parts[1]};
                    return new Command(Duke.CommandType.UNMARK, args);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    throw new DukeException("Incorrect usage - please provide the task number to be unmarked");
                }
            case "todo":
                String todoDesc = input.substring(5);
                if (todoDesc.equals("")) {
                    throw new DukeException("Incorrect usage - description cannot be empty");
                }
                String[] args = new String[]{todoDesc};
                return new Command(Duke.CommandType.TODO, args);
            case "deadline":
                int byIndex;
                for (byIndex = 0; byIndex < parts.length; byIndex++) {
                    if (parts[byIndex].equals("/by")) {
                        break;
                    }
                }

                if (byIndex == parts.length) {
                    throw new DukeException("Unknown usage - /by not found in \"deadline\" command.");
                }

                String deadlineDesc = String.join(" ", Arrays.copyOfRange(parts, 1, byIndex));
                String dueDateStr = String.join(" ", Arrays.copyOfRange(parts, byIndex + 1, parts.length));

                if (deadlineDesc.equals("")) {
                    throw new DukeException("Unknown usage - description of \"deadline\" should not be empty.");
                }
                if (dueDateStr.equals("")) {
                    throw new DukeException("Unknown usage - due date of \"deadline\" should not be empty.");
                }

                return new Command(Duke.CommandType.DEADLINE, new String[]{deadlineDesc, dueDateStr});
            case "event":
                int fromIndex = -1;
                int toIndex = -1;

                for (int i = 0; i < parts.length; i++) {
                    if (parts[i].equals("/from")) {
                        fromIndex = i;
                    } else if (parts[i].equals("/to")) {
                        toIndex = i;
                    }
                }

                if (fromIndex == -1) {
                    throw new DukeException("Unknown usage - /from not found in \"event\" command.");
                }
                if (toIndex == -1) {
                    throw new DukeException("Unknown usage - /to not found in \"event\" command.");
                }

                String description = String.join(" ", Arrays.copyOfRange(parts, 1, fromIndex));
                String startDateStr = String.join(" ", Arrays.copyOfRange(parts, fromIndex + 1, toIndex));
                String endDateStr = String.join(" ", Arrays.copyOfRange(parts, toIndex + 1, parts.length));

                if (description.equals("")) {
                    throw new DukeException("Unknown usage - description of \"event\" should not be empty.");
                }
                if (startDateStr.equals("")) {
                    throw new DukeException("Unknown usage - start date of \"event\" should not be empty.");
                }
                if (endDateStr.equals("")) {
                    throw new DukeException("Unknown usage - end date of \"event\" should not be empty.");
                }
                return new Command(Duke.CommandType.EVENT, new String[]{description, startDateStr, endDateStr});
            case "delete":
                try {
                    if (parts.length == 1) {
                        throw new DukeException("Unknown usage - task number should be included in \"delete\" command");
                    }
                    for (char c : parts[1].toCharArray()) {
                        if (!Character.isDigit(c)) {
                            throw new DukeException("Unknown usage - task number given is not a valid number");
                        }
                    }
                    int i = Integer.parseInt(parts[1]) - 1;
                    return new Command(Duke.CommandType.DELETE, new String[]{parts[1]});
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    throw new DukeException("Unknown usage - task number given is not a valid number");
                }
            default:
                throw new DukeException("Unknown command");
        }
    }


}
