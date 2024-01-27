public class Task {
        protected String symbol;
        protected String description;
        protected boolean isDone;

        public Task(String symbol, String description) {
            this.symbol = symbol;
            this.description = description;
            this.isDone = false;
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

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
}
