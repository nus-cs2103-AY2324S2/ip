package duke.task;

<<<<<<< Updated upstream
import duke.task.TaskType;

=======
/**
 * The Task class represents a generic task with a description and completion status.
 * It serves as the base class for more specific task types.
 */
>>>>>>> Stashed changes
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

        public Task(String description) {
            this.description = description;
        }

        public boolean checkStatus() {
            return this.isDone;
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

