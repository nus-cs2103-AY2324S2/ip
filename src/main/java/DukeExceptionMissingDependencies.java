public class DukeExceptionMissingDependencies extends DukeException {
    public DukeExceptionMissingDependencies(String m) {
        super(m);
    }
    @Override
    public String toString() {
        return "Wait a second, your command has missing dependencies! \n" +
        "Please follow the following format for the commands: \n" +
        "1. list \n" +
        "2. mark <number of task in list> \n" +
        "3. todo <event name> \n" +
        "4. deadlines <deadline name> <deadline date> \n" +
        "5. event <event name> <event from date> <event to date> \n" +
        "6. bye" + super.toString();
    }
}
