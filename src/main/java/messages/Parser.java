package messages;

class Parser {  // default access modifier
    private static String separatorBetweenCommandAndArgument = " ";
    private static String separatorBetweenDescriptionAndDueDate = "/by ";
    private static String separatorBetweenDescriptionAndStart = "/from ";
    private static String separatorBetweenStartAndEnd = "/to ";

    static String getCommandType(String input) {  // default access modifier
        return input.split(separatorBetweenCommandAndArgument)[0].toUpperCase();
    }

    static String getTodoDescription(String input) {  // default access modifier
        return input.substring(input.indexOf(separatorBetweenCommandAndArgument) + 1);
    }

    static String getDeadlineDescription(String input) {  // default access modifier
        return input.substring(input.indexOf(separatorBetweenCommandAndArgument) + 1,
                input.indexOf(separatorBetweenDescriptionAndDueDate) - 1);
    }

    static String getDeadlineDueDate(String input) {  // default access modifier
        return input.substring(input.indexOf(separatorBetweenDescriptionAndDueDate) + 4);
    }

    static String getEventDescription(String input) {  // default access modifier
        return input.substring(input.indexOf(separatorBetweenCommandAndArgument) + 1,
                input.indexOf(separatorBetweenDescriptionAndStart) - 1);
    }

    static String getEventStart(String input) {  // default access modifier
        return input.substring(input.indexOf(separatorBetweenDescriptionAndStart) + 6,
                input.indexOf(separatorBetweenStartAndEnd) - 1);
    }

    static String getEventEnd(String input) {  // default access modifier
        return input.substring(input.indexOf(separatorBetweenStartAndEnd) + 3);
    }

    static int getTaskNumber(String input)  // default access modifier
            throws NumberFormatException { // TODO: handle NumberFormatException
        return Integer.parseInt(input.split(separatorBetweenCommandAndArgument)[1]);
    }
}
