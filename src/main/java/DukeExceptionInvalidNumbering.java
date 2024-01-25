public class DukeExceptionInvalidNumbering extends DukeException {
    int id;
    public DukeExceptionInvalidNumbering(String m, int id) {
        super(m);
        this.id = id;
    }
    @Override
    public String toString() {
        return "Check the list! The task id of " + this.id + " you are referring to doesn't exist!" + super.toString();
    }
}
