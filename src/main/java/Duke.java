public class Duke {

    public static void main(String[] args) {
        //This class kinds of serve as an input parser to determine the appropriate response from Bob.
        //Separate this into a new class called input handler and treat this duke class as a Main class?
        //We can have enums instead of hard coding list, bye, mark, unmark.
        BartenderBob bartenderBob = new BartenderBob();
        InputHandler inputHandler = new InputHandler();
        inputHandler.handleInput(bartenderBob);
    }
}
