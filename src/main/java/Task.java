public class Task {

        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String getTaskIcon() {
            return "T";
        }

        public String ToString(){
            return "[" + getTaskIcon() + "] " + "[" + getStatusIcon() + "] "+ description;
        }

        //...
    }

