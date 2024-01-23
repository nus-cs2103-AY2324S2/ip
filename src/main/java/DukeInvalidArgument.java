public class DukeInvalidArgument extends DukeException{
    @Override
    public String toString() {
        return String.format("%s The argument(s) you entered are invalid!", super.toString());
    }
}
