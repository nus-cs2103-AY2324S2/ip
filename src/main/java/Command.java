enum Command {
    BYE ("bye") {
        @Override 
        boolean isExit() {
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

    
    boolean isExit() {
        return false;
    }
}


