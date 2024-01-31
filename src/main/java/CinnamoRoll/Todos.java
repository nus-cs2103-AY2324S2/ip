package CinnamoRoll;
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
        return String.format("Got it. I've added this task:%n   " +
                "%s%nNow you have %d tasks in the list", super.toString(), length);

    }
}