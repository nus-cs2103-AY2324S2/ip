package tasks;

/**
 * Enum representing different commands that can be executed by the program.
 */
public enum Priority {
    /**
     * Low priority level.
     */
    LOW {
        @Override
        public String priorityToString() {
            return "LOW";
        }
    },

    /**
     * Medium priority level.
     */
    MEDIUM {
        @Override
        public String priorityToString() {
            return "MEDIUM";
        }
    },

    /**
     * High priority level.
     */
    HIGH {
        @Override
        public String priorityToString() {
            return "HIGH";
        }
    };

    public abstract String priorityToString();
}
