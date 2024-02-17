package earl.tasks;

import earl.exceptions.TimeException;

/**
 * Enum class for type of class.
 */
public enum TaskType {

    TODO {
        @Override
        public Task createTask(String... args) {
            return new Todo(args[0]);
        }
    },
    DEADLINE {
        @Override
        public Task createTask(String... args) {
            return new Deadline(args[0], args[1]);
        }
    },
    EVENT {
        @Override
        public Task createTask(String... args) throws TimeException {
            return new Event(args[0], args[1], args[2]);
        }
    };

    public abstract Task createTask(String... args) throws TimeException;
}
