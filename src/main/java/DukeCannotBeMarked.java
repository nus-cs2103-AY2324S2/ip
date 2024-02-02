public class DukeCannotBeMarked extends DukeException{

    @Override
    public String toString() {
        return String.format("%s Task is already marked!",super.toString());
    }
}
