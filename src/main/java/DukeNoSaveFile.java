public class DukeNoSaveFile extends DukeException{
    @Override
    public String toString() {
        return String.format("%s No save file could be found!",super.toString());
    }
}
