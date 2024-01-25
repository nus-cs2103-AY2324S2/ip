public class Task {
        protected String description;
        protected boolean isDone;

        protected String type;


        public Task(String description, String type) {
            this.description = description;
            this.isDone = false;
            this.type = type;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String getDes() {
            return this.description;
        }

        public String getType() {
            return this.type;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void unmarkAsDone() {
            this.isDone = false;
        }

}
