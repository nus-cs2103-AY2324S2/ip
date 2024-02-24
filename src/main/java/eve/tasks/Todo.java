package eve.tasks;
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, String isDone) {
        super(description);
        if (isDone.equals("0")) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }

    public String getTask() {
        return super.toString();
    }

    @Override
    public String toString() {
        if(super.tag == null) { 
            return "[T]" + "[" + super.getStatusIcon() + "] " + super.toString();
        } else {
            return "[T]" + "[" + super.getStatusIcon() + "] " + super.toString() + "#" + super.tag;
        }
    }

    @Override
    public String toStore() {
        if(super.tag == null) {
            return "T" + " | " + super.getStatusInteger() + " | " + super.toString();
        } else {
            return "T" + " | " + super.getStatusInteger() + " | " + super.toString() + "#" + super.tag + "\n";
        }
    }

}
