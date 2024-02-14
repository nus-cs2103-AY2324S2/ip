package duke;

public class Task {

        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }
        /**
         * getter for status icon.
         *
         */
        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }
        /**
        * getter for task icon.
        *
        */
        public String getTaskIcon() {
            return "T";
        }

        /**
        * returns string representation of task.
        *
        */
        public String ToString(){
            return "[" + getTaskIcon() + "] " + "[" + getStatusIcon() + "] "+ description;
        }
        /**
        *  marks task as done.
        *
        */
        public void markAsDone(){
            isDone = true;
        }
        /**
        *   marks task as undone.
        *
        */
        public void unmarkAsDone(){
        isDone = false;
    }
        /**
        * returns a string representation of task used to store the task on hard drive.
        *
        */
        public String toStore(){
            if (isDone) {
                return getTaskIcon() + "/" + "1" + "/" + description;
            } else {
                return getTaskIcon() + "/" + "0" + "/" + description;
            }

        }

        //...
    }

