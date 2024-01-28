package duke.command;

public enum Command {
    BYE ("bye") {
        @Override
        public boolean isExit() {
            return true;
        };
    },
    DEADLINE ("deadline"),
    EVENT ("event"),
    TODO ("todo"),
    LIST ("list"),
    MARK ("mark"),
    UNMARK ("unmark"),
    DELETE ("delete");

    String equivalentString;
    Command (String equivalentString) {
        this.equivalentString = equivalentString;
    }

    
    public boolean isExit() {
        return false;
    }
}


