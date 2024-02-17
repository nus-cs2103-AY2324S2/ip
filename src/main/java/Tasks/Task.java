package Tasks;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task implements Serializable {
    private final String description;
    private boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsUndone() {
            this.isDone = false;
        }

        public String getDescription() {
            return this.description;
        }

        private String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }

        protected static LocalDateTime parseDate(String string) throws DateTimeParseException {
            String dateTimePattern = "\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{2}";
            String datePattern = "\\d{1,2}/\\d{1,2}/\\d{4}";


            if (string.matches(dateTimePattern)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy H:m");
                return LocalDateTime.parse(string, formatter);
            } else if (string.matches(datePattern)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                LocalDate date = LocalDate.parse(string, formatter);
                return date.atStartOfDay();
            } else {
                throw new DateTimeParseException("Invalid date format. Use d/M/yyyy or d/M/yyy H:m.", string, 0);
            }
        }

        protected static String formatDate(LocalDateTime date) {
            return date.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma"));
        }
}
