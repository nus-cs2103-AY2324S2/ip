public class DukeCannotBeUnmarked extends DukeException{

    @Override
    public String toString() {
        return String.format("%s Task is already unmarked!",super.toString());
    }
}
