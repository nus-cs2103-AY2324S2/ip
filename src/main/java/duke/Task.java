package duke;

public class Task {

        protected String description;
        protected boolean isDone;

        protected Priority priority;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
            this.priority = Priority.NONE;
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
            return "[" + getTaskIcon() + "] " + "[" + getStatusIcon() + "] "+ description + " (" + getPriorityDataString() + ")";
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
                return getTaskIcon() + "/" + "1" + "/" + description + "/" + priority;
            } else {
                return getTaskIcon() + "/" + "0" + "/" + description + "/" + priority;
            }

        }

        public String getPriorityDataString() {
            switch (this.priority) {
                case HIGH:
                    return "high";
                case MEDIUM:
                    return "medium";
                case LOW:
                    return "low";
                case NONE:
                    return "none";
                default:
                    return "";
            }
        }

        public void setPriority(String priority) {
            if (priority.equals("HIGH") || priority.equals("high")) {
                this.priority = Priority.HIGH;
            } else if(priority.equals("MEDIUM") || priority.equals("medium")) {
                this.priority = Priority.MEDIUM;
            } else if(priority.equals("LOW") || priority.equals("low")) {
                this.priority = Priority.LOW;
            }

        }

        //...
    }

