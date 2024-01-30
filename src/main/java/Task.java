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

        public void markAsDone(){
            isDone = true;
        }

    public void unmarkAsDone(){
        isDone = false;
    }

        public String toStore(){
            if(isDone) {
                return getTaskIcon() + "/" + "1" + "/" + description;
            } else{
                return getTaskIcon() + "/" + "0" + "/" + description;
            }

        }

        //...
    }

