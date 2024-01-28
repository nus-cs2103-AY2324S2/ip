import java.time.LocalDate;

public class Todo extends Task {
    public Todo(String name) throws NicoleException {
        super();
        if (name == null) {
            throw new NicoleException("What exactly do you want me to note...tell me in the form of: todo [task]");
        }
        super.setName(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
