import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static Command parseInput(String input) {
        input = input.trim();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Task cannot be empty. Please enter a valid task.");
        }
        String[] parts = input.trim().split("\\s+", 2);
        String command = parts[0];
        int l = parts.length;
        if (l == 1) {
            return CommandWithNoIndex(command);
        }
        String possibleIndexOrTask = parts[1];
        boolean followedByInteger = isFollowedByInteger(possibleIndexOrTask);
        if (followedByInteger && !command.equals("todo")) {
            int index = Integer.parseInt(possibleIndexOrTask);
            return indexRelatedCommand(command,index);
        } else {
            return addCommand(command, possibleIndexOrTask);
        }
    }
    public static boolean IsexitCommand(String s){

        return s.trim().toLowerCase().equals("bye");
    }

    private static Command CommandWithNoIndex(String command){
        switch (command) {
            case "list":
                return new showListCommand();
            default:
                throw new IllegalArgumentException("Please input valid commands: ");
        }
    }
    private static Command indexRelatedCommand(String command, int index){
        switch (command) {
            case "unmark":
                return new unMarkCommand(index);
            case "remove":
                return new removeCommand(index);
            case "mark":
                return new MarkCommand(index);
        }
        throw new IllegalArgumentException("Please input valid commands");
    }
    private static boolean isFollowedByInteger(String s){
        return s.matches("\\d+");
    }
    private static Command addCommand(String command, String possibleTask){
        switch (command) {
            case "todo":
                return new addToListCommand(new ToDoTask(possibleTask));
            case "deadline":
                String[] deadlineParts = possibleTask.split("\\s+by\\s+", 2);
                String pattern1 = "\\d{2}-\\d{2}";
                String pattern2 = "\\d{2}-\\d{2} \\d{2}:\\d{2} [ap]m";
                //handling case that does not include by
                if (deadlineParts.length < 2) {
                    throw new IllegalArgumentException("Invalid deadline format. Please specify the task following by its deadline using 'by mm-dd or mm-dd hh:mm am/pm' eg. task1 by 01-14 01:00 pm  or just task1 by 01-14");
                }
                if (!deadlineParts[1].trim().matches(pattern1) && !deadlineParts[1].trim().matches(pattern2)) {
                    throw new IllegalArgumentException("Invalid deadline format. Please specify the task following by its deadline using 'by mm-dd or mm-dd hh:mm am/pm' eg. task1 by 01-14 01:00 pm  or task1 by 01-14");
                }
                String time = deadlineParts[1];
                String taskName = deadlineParts[0];
                return new addToListCommand(new DeadLineTask(time, taskName));
            case "event":
                String eventPattern1 = "\\d{2}:\\d{2} [ap]m to \\d{2}:\\d{2} [ap]m";
                String eventPattern2 = "\\d{2}-\\d{2} \\d{2}:\\d{2} [ap]m to \\d{2}:\\d{2} [ap]m";
                int posPattern1 = setEventTimingPos(possibleTask, eventPattern1);
                int posPattern2 = setEventTimingPos(possibleTask, eventPattern2);
                String taskN;
                String eventDetail;
                if (posPattern2 != -1) {
                    taskN = possibleTask.substring(0, posPattern2);
                    eventDetail = possibleTask.substring(posPattern2);
                } else if (posPattern1 != -1) {
                    taskN = possibleTask.substring(0, posPattern1);
                    eventDetail = possibleTask.substring(posPattern1);
                } else {
                    throw new IllegalArgumentException("Invalid event format. Please specify the event using event followed by task name and its time in the following format <hh:mm am/pm to hh:mm am/pm or MM-dd hh:mm am/pm to hh:mm am/pm> eg1. event task1 10:00 am to 12:00 am  eg.2 event task1 01-01 10:00 am to 12:00 pm");
                }
                String[] timingDetails = eventDetail.split("to");
                EventTask t = new EventTask(timingDetails[0], timingDetails[1], taskN);
                return new addToListCommand(t);
            // invalid command no such command at all in case invalidCommand etcasdad
            default:
                throw new IllegalArgumentException("Please input valid commands");

        }
    }
    private static int setEventTimingPos(String input, String regex) {
        int match;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            match = matcher.start();
        } else {
            match = -1;
        }
        return match;
    }
    public static boolean isDisplayCommand(String input){
        return input.equals("--commands");
    }
}
