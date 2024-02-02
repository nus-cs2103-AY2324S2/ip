package tasks;

public class Todo extends Task{
    public Todo(boolean done, String name){
        super(done, name);
    };
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toSavedString() {
        return String.format("T,%s,%s"
                , this.done ? '1' : '0'
                , this.name);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s\n", this.done ? "X" : " ", this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Todo todo = (Todo) obj;
        return this.name.equals(todo.name) && this.done == todo.done;
    }
}
