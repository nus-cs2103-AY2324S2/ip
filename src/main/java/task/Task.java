package task;

import java.time.LocalDate;

public class Task {
        protected String symbol;
        protected String description;
        protected boolean isDone;
        protected LocalDate by;
        protected LocalDate start;
        protected LocalDate end;

        public Task(String symbol, String description) {
            this.symbol = symbol;
            this.description = description;
            this.isDone = false;
        }

        public Task(String symbol, String description, LocalDate by) {
            this.symbol = symbol;
            this.description = description;
            this.isDone = false;
            this.by = by;
        }

        public Task(String symbol, String description, LocalDate start, LocalDate end) {
            this.symbol = symbol;
            this.description = description;
            this.isDone = false;
            this.start = start;
            this.end = end;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void mark() {
            this.isDone = true;
        }

        public void unmark() {
            this.isDone = false;
        }

        public String getSymbol() {
            return this.symbol;
        }

        public String getStatus() {
            return isDone ? "1" : "0";
        }

        public String getDescription() {
            return this.description;
        }

        public LocalDate getBy() { return this.by; }

        public LocalDate getStart() { return this.start; }

        public LocalDate getEnd() { return this.end; }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
}
