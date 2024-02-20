package logic;

/**
 * This class contains all the static methods used to convert
 */
public class Extractor {
    public static String[] extractTodoParameters(String command) {
        String[] parameters = new String[1];
        String todoDescription = command.replaceFirst("(?i)todo", "").trim();
        parameters[0] = todoDescription;
        return parameters;
    }

    public static String[] extractDeadlineParameters(String command) {
        String[] parameters = new String[2];
        String deadlineDescriptionDueDate = command.replaceFirst("(?i)deadline", "").trim();
        String[] deadlineDescriptionDueDateArray = deadlineDescriptionDueDate.split(" /by ");
        String deadlineDescription = deadlineDescriptionDueDateArray[0];
        String deadlineDueDate = deadlineDescriptionDueDateArray[1];
        parameters[0] = deadlineDescription;
        parameters[1] = deadlineDueDate;
        return parameters;
    }

    public static String[] extractEventParameters(String command) {
        String[] parameters = new String[3];
        String eventDescriptionFromTo = command.replaceFirst("(?i)event", "").trim();
        String[] eventDescriptionFromToArray = eventDescriptionFromTo.split(" /from ");
        String eventDescription = eventDescriptionFromToArray[0];
        String eventFromTo = eventDescriptionFromToArray[1].trim();
        String[] eventFromToArray = eventFromTo.split(" /to ");
        String eventFrom = eventFromToArray[0].trim();
        String eventTo = eventFromToArray[1].trim();
        parameters[0] = eventDescription;
        parameters[1] = eventFrom;
        parameters[2] = eventTo;
        return parameters;
    }

    public static String extractSearchTerm(String command) {
        String searchTerm = command.replace("find ", "");
        return searchTerm;
    }
}
