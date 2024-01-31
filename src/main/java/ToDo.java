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
    public void writeToData() throws IOException {

    }
}
