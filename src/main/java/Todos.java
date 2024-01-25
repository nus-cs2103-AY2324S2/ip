import java.util.*;

class Todos extends Task {

    Todos(String str) {
        super(str);
    }

    String getStatusIcon() {
        return (this.is_marked ? "[T][X]" : "[T][ ]");
    }

    String added(int length) {
        return "   Got it. I've added this task:\n" + "     " +
                this.getStatusIcon() + " " + this.taskname + "\n" +
                "   Now you have " + String.valueOf(length) + " tasks in the list";
    }
}