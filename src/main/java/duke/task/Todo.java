package duke.task;

import java.util.Objects;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Todo todo = (Todo) obj;
        return this.isDone == todo.isDone
                && this.description.equals(todo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.description, this.isDone);
    }
}
