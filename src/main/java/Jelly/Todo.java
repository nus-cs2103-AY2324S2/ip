package jelly;

/**
 * Todo task
 */
public class Todo extends Task {

    /**
     * @param name   name of todo
     * @param isDone whether task is done
     */
    public Todo(String name, boolean isDone) {

        super(name, isDone);
    }

    @Override
    public String toString() {

        return "[T]" + super.toString();
    }

    @Override
    public String header() {

        int binary = super.isDone ? 1 : 0;
        return this.type() + binary;
    }

    @Override
    public String type() {

        return "T";
    }
}
