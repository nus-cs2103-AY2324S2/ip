package jiayou.task;

/**
 * Represents todo, a subtype of the task.
 * @author Liu Jiayao
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toStringForStore() {
        return "T" + super.toStringForStore();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ToDo)) {
            return false;
        }
        if (this.getDescription().equals(((ToDo) object).getDescription())) {
            return true;
        }
        return false;
    }
}
