package kervyn.Tasks;

public class Task {
    private String description;
    private boolean status;
    private Type type;

    public Task(String description, boolean status, Type type) {
        this.description = description;
        this.status = status;
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getStatus() {
        return this.status;
    }

    public Type getType() {
        return this.type;
    }

    public char getCapitalType() {
        switch(this.type) {
            case TODO:
                return 'T';
            case DEADLINE:
                return 'D';
            case EVENT:
                return 'E';
        }
        return ' ';
    }

    public void updateStatus() {
        this.status = !this.status;
    }

    @Override
    public String toString() {
        char check = this.getStatus() ? 'X' : ' ';
        char letterType = this.getCapitalType();
        switch (letterType) {
            case 'T':
                return "[" + letterType + "] " + "[" + check + "] " + this.description;
            case 'D':
                Deadline deadlineTask = (Deadline) this;
                return "[" + letterType + "] " + "[" + check + "] " + deadlineTask.getDescription() + " (by: " + deadlineTask.getDeadline() + ")";
            case 'E':
                Event eventTask = (Event) this;
                return "[" + letterType + "] " + "[" + check + "] " + eventTask.getDescription() + " (from: " + eventTask.getStartDate() + " to: " + eventTask.getEndDate() + ")";
        }
        return "";
    }
}
