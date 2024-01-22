public class Deadline extends Task {
    private String endDate;

    public Deadline(String name, String endDate) {
        super(name);
        this.endDate = endDate;
    }

    public void updateStatus(boolean status) {
        super.status = status;
    }

    public String getName() {
        return super.name;
    }

    public boolean getStatus() {
        return super.status;
    }

    @Override
    public String toString() {
        return String.format("[D]%s %s (by: %s)", (super.status ? "[X]" : "[ ]"), super.name, this.endDate);
    }
}
