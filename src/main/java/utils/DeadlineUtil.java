package utils;

import objects.Deadlines;
import exception.InvalidDeadlineException;

public class DeadlineUtil {
    public static Deadlines createDeadline(String input) throws InvalidDeadlineException {
        if (!input.contains("/by")) {
            throw new InvalidDeadlineException();
        }

        String[] parts = input.split("/by", 2);
        String name = parts[0].trim();
        // throw error if empty
        String by = parts.length > 1 ? parts[1].trim() : "";

        return new Deadlines(name, by);
    };
}
