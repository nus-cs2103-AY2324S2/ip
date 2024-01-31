package duke.task;

import duke.task.TaskType;

public class Task {
        protected String description;
        protected TaskType type;
        protected boolean isDone;
        protected String statusIcon;

        public Task(TaskType type, String description) {
            this.type = type;
            this.description = description;
            this.isDone = false;
            updateStatusIcon();
        }

        public TaskType getType() {
            return this.type;
        }

        private void updateStatusIcon() {
            this.statusIcon = (isDone ? "X" : " ");
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        public String getDescription() {
            return this.description;
        }

}

