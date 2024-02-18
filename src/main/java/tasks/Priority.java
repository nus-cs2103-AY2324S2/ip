package tasks;

public enum Priority {
    LOW {
        @Override
        public String priorityToString() {
            return "LOW";
        }
    },
    MEDIUM {
        @Override
        public String priorityToString() {
            return "MEDIUM";
        }
    },
    HIGH {
        @Override
        public String priorityToString() {
            return "HIGH";
        }
    };

    public abstract String priorityToString();
}
