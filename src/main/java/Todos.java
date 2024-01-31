class Todos extends Task {

    Todos(String str) {
        super(str);
    }

    Todos(String str, boolean marked) {
        super(str, marked);
    }

    String getStatusIcon() {
        return (this.is_marked ? "[T][X]" : "[T][ ]");
    }

    String added(int length) {
        return "   Got it. I've added this task:\n" + "     " +
                super.toString() + "\n" +
                "   Now you have " + String.valueOf(length) + " tasks in the list";
    }
}