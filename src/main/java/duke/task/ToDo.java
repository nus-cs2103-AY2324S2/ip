package duke.task;

import duke.exception.DukeException;

public class ToDo extends Task {
        public ToDo(String description) throws DukeException {
            super(TaskType.T, description);
        }

        @Override
        public String toString() {
            return "Got it. I've added this task: \n [T][" + getStatusIcon() + "] " + getDescription();
        }
}
