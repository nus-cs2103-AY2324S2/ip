package logic;

/**
 * This class contains all the static methods used to extract details from Strings
 */
public class Extractor {
    /**
     * Extract Todo details from a valid Todo command
     * @param command The String input Todo command from user
     * @return An array containing the parameters of the Todo task (description)
     */
    public static String[] extractTodoParameters(String command) {
        String[] parameters = new String[1];
        String todoDescription = command.replaceFirst("(?i)todo", "").trim();
        parameters[0] = todoDescription;
        return parameters;
    }

    /**
     * Extracts Deadline details from a valid Deadline command
     * @param command The String input Deadline command from user
     * @return An array containing the parameters of the Deadline task (description, due date)
     */
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

    /**
     * Extracts Event details from a valid Event command
     * @param command The String input Event command from the user
     * @return An array containing the parameters of the Event task (description, from date, to date)
     */
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

    /**
     * Extracts keyword(s) to be searched from a valid Search command
     * @param command The String input Search command from the user
     * @return A string containing the extracted search terms
     */
    public static String extractSearchTerm(String command) {
        String searchTerm = command.replace("find ", "");
        return searchTerm;
    }

    /**
     * Extracts link to help page from the output of a valid Help command
     * @param text The full String of help details
     * @return The extracted link from the full String to the external help page
     */
    public static String extractHelpLink(String text) {
        String link = text.substring(text.indexOf("http"), text.indexOf(".md") + 3);
        return link;
    }
}
