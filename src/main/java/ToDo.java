import java.io.FileWriter;
import java.io.IOException;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("%s %s", "[T]", super.toString());
    }

    @Override
    public String dataString() {
        return String.format("%s|%s\n", "T", super.dataString());
    }
}
