package Tasks;

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

}
