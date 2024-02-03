package bond;

import command.*;

public abstract class Parser {

    public static boolean isValidCommand(String input) {
        return Command.COMMANDS.contains(input.toLowerCase());
    }

    public static boolean isNumber(String input) {
        char[] digits = input.toCharArray();
        boolean isNumber = true;

        for (char c : digits) {
            if (!Character.isDigit(c)) {
                isNumber = false;
                break;
            }
        }

        return isNumber;
    }

    public static String changeDateFormat(String month, String day, String year) {
        String newMonth = "";
        String newDay = "";

        switch (month) {
            case "Jan":
                newMonth = "01";
                break;
            case "Feb":
                newMonth = "02";
                break;
            case "Mar":
                newMonth = "03";
                break;
            case "Apr":
                newMonth = "04";
                break;
            case "May":
                newMonth = "05";
                break;
            case "Jun":
                newMonth = "06";
                break;
            case "Jul":
                newMonth = "07";
                break;
            case "Aug":
                newMonth = "08";
                break;
            case "Sep":
                newMonth = "09";
                break;
            case "Oct":
                newMonth = "10";
                break;
            case "Nov":
                newMonth = "11";
                break;
            case "Dec":
                newMonth = "12";
                break;
        }

        if (day.length() == 1) {
            newDay = "0" + day;
        } else {
            newDay = day;
        }

        return year + "-" + newMonth + "-" + newDay;
    }

    public static Command parse(String userCommand) throws BondException {

        // System.out.println("I have reached Loop " + "in MAIN function");
        String taskName = "";

        String[] components = userCommand.split(" ");

        // Invalid Command syntax
        if (!Parser.isValidCommand(components[0])) {
            BondException.raiseException("NA",
                    "INVALID_COMMAND_TYPE");
        }

        if (components[0].equalsIgnoreCase("todo")) {

            // No valid task name specified for a todo task
            if (components.length == 1) {
                BondException.raiseException("todo", "EMPTY_DESCRIPTION");
            }

            for (int i = 1; i < components.length; i++) {
                taskName += components[i] + " ";
            }

            taskName = taskName.trim();

            return new AddToDoCommand(taskName);

        } else if (components[0].equalsIgnoreCase("deadline")) {

            if (components.length == 1) {
                BondException.raiseException("deadline", "EMPTY_DESCRIPTION");
            }

            String deadline = "";

            for (int i = 1; i < components.length; i++) {

                if (components[i].equals("/by")) {

                    for (int j = i + 1; j < components.length; j++) {
                        deadline += components[j] + " ";
                    }
                    break;
                } else {
                    taskName += components[i] + " ";
                }
            }

            taskName = taskName.trim();
            deadline = deadline.trim();

            return new AddDeadlineCommand(taskName, deadline);

        } else if (components[0].equalsIgnoreCase("event")) {

            if (components.length == 1) {
                BondException.raiseException("event", "EMPTY_DESCRIPTION");
            }

            int state = 0;
            String start = "";
            String end = "";

            for (int i = 1; i < components.length; i++) {

                if (components[i].equals("/from")) {
                    state = 1;
                } else if (components[i].equals("/to")) {
                    state = 2;
                }

                switch (state) {

                    case 0:
                        taskName += components[i] + " ";
                        break;

                    case 1:
                        if (!components[i].equals("/from")) {
                            start += components[i] + " ";
                        }
                        break;

                    case 2:
                        if (!components[i].equals("/to")) {
                            end += components[i] + " ";
                        }
                        break;

                    default:
                        break;
                }
            }

            taskName = taskName.trim();
            start = start.trim();
            end = end.trim();

            return new AddEventCommand(taskName, start, end);

        } else if (components[0].equalsIgnoreCase("list")) {

            if (components.length != 1) {
                BondException.raiseException("list", "EXTRA_DETAILS");
            }

            return new ListCommand();

        } else if (components[0].equalsIgnoreCase("mark")) {

            if (components.length == 1) {
                BondException.raiseException("mark", "MISSING_INDEX");
            } else if (!Parser.isNumber(components[1])) {
                BondException.raiseException("mark", "INVALID_INDEX");
            }

            int index = Integer.parseInt(components[1]) - 1;

            return new MarkCommand(index);

        } else if (components[0].equalsIgnoreCase("unmark")) {

            if (components.length == 1) {
                BondException.raiseException("unmark", "MISSING_INDEX");
            } else if (!Parser.isNumber(components[1])) {
                BondException.raiseException("unmark", "INVALID_INDEX");
            }

            int index = Integer.parseInt(components[1]) - 1;

            return new UnmarkCommand(index);

        } else if (components[0].equalsIgnoreCase("bye")) {

            if (components.length != 1) {
                BondException.raiseException("bye", "EXTRA_DETAILS");
            }

            return new ExitCommand();

        } else if (components[0].equalsIgnoreCase("delete")) {

            if (components.length == 1) {
                BondException.raiseException("delete", "MISSING_INDEX");
            } else if (!Parser.isNumber(components[1])) {
                BondException.raiseException("delete", "INVALID_INDEX");
            }

            int index = Integer.parseInt(components[1]) - 1;

            return new DeleteCommand(index);
        } else {
            return null;
        }

    }

}
