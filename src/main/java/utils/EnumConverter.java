package utils;

import task.Priority;

public class EnumConverter {
    public static Priority convertStringToPriority(String priority) {
        switch (priority) {
            case "high":
                return Priority.HIGH;
            case "medium":
                return Priority.MEDIUM;
            case "low":
                return Priority.LOW;
            case "none":
                return Priority.NONE;
            default:
                throw new IllegalArgumentException("Invalid priority input.");
        }
    }

    public static String convertPriorityToFileString(Priority priority) {
        switch (priority) {
            case HIGH:
                return "high";
            case MEDIUM:
                return "medium";
            case LOW:
                return "low";
            case NONE:
                return "none";
            default:
                throw new IllegalArgumentException("Invalid priority input.");
        }
    }

    public static String convertPriorityToString(Priority priority) {
        switch (priority) {
            case HIGH:
                return "🔴 High";
            case MEDIUM:
                return "🟡 Medium";
            case LOW:
                return "🟢 Low";
            case NONE:
                return "None";
            default:
                throw new IllegalArgumentException("Invalid priority input.");
        }
    }
}
