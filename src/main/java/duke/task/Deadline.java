package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
        protected LocalDateTime by;
        protected String byString;

        public Deadline(String description, String byString) throws DukeException {
            super(TaskType.D, description);
            this.byString = byString.trim();

            try {
                if (!this.byString.isEmpty()) {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    this.by = LocalDateTime.parse(byString, dateTimeFormatter);
                }
            } catch (DateTimeParseException e) {
                this.by = null;
            }

            if (this.by == null && this.byString.isEmpty()) {
                throw new DukeException("By when? You forgot to enter \"/by\"");
            } else if (description.isEmpty()) {
                throw new DukeException("You forgot to enter the task for which you have to complete it by");
            }
        }
        public Object getBy() {
            return (this.by != null) ? this.by : this.byString;
        }
        public LocalDateTime getByTime() {
            return this.by;
        }

        public String getByString() {
            return this.byString;
        }
        @Override
        public String toString() {
            String byStringFormatted = (this.by != null) ?
                    " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")" :
                    (this.byString != null ? " (by: " + this.byString + ")" : "");

            return "Got it. I've added this task:\n [D][" + getStatusIcon() + "] " + getDescription() + byStringFormatted; // + by.getClass();
        }
}
