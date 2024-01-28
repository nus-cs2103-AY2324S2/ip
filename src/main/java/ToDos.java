import java.io.Serializable;

public class ToDos extends Task implements Serializable {
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "T ";
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
