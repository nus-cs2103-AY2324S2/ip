public class DukeTaskNotFound extends DukeException{

    int index;
    public DukeTaskNotFound(int index) {
        this.index = index;
    }
    @Override
    public String toString() {
        return String.format("%s Task No. %d cannot be found!",super.toString(), this.index);
    }
}
