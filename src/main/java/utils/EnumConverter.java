package utils;

import task.Priority;

/**
 * Represents a converter that is used to convert between different types of
 * enums.
 * <p>
 * This class is used to convert between different types of enums.
 * </p>
 */
public class EnumConverter {
    /**
     * Converts the string representation of a priority to a {@code Priority} enum.
     * 
     * @param priority The string representation of a priority.
     * @return The {@code Priority} enum.
     */
    public static Priority convertStringToPriority(String priority) throws IllegalArgumentException {
        switch (priority.toLowerCase()) {
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

    /**
     * Converts the {@code Priority} enum to a string representation for file
     * storage.
     * 
     * @param priority The {@code Priority} enum.
     * @return The string representation of the priority.
     */
    public static String convertPriorityToFileString(Priority priority) throws IllegalArgumentException{
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

    /**
     * Converts the {@code Priority} enum to a string representation.
     * 
     * @param priority The {@code Priority} enum.
     * @return The string representation of the priority.
     */
    public static String convertPriorityToString(Priority priority) throws IllegalArgumentException{
        switch (priority) {
        case HIGH:
            return "High";
        case MEDIUM:
            return "Medium";
        case LOW:
            return "Low";
        case NONE:
            return "None";
        default:
            throw new IllegalArgumentException("Invalid priority input.");
        }
    }
}
