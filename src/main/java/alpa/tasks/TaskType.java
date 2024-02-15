package alpa.tasks;

public enum TaskType {
    TODO,
    DEADLINE,
    EVENT;

    public String getShortName() {
        switch (this) {
        case TODO:
            return "T";
        case DEADLINE:
            return "D";
        case EVENT:
            return "E";
        default:
            return "Unknown";
        }
    }
    
    public static TaskType fromShortName(String shortName) {
        switch (shortName) {
        case "T":
            return TODO;
        case "D":
            return DEADLINE;
        case "E":
            return EVENT;
        default:
            throw new IllegalArgumentException("Unknown TaskType: " + shortName);
        }
    }
}
