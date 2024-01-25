import javax.xml.stream.events.StartDocument;

public class Liv {
    // to adhere to the singleton pattern
    private enum State {
        ACTIVE_TALKING, ACTIVE_LISTENING, INACTIVE
    }
    private static Liv instance = null;
    private static HorizontalLine horizontalLine = null;

    private static State currentState = null;

    private Liv() {
        // initial setup
        horizontalLine = HorizontalLine.getInstance();
        currentState = State.INACTIVE;
    }

    public static Liv getInstance() {
        if (instance == null) {
            instance = new Liv();
        }

        return instance;
    }
    public static void main(String[] args) {
        getInstance().Start();
    }

    private void Start() {
        instance.ToggleActiveState();
        instance.Greet();
        instance.EndSession();
    }

    private void Greet() {
        System.out.println("Hello there, Liv here.");
        System.out.println("How may I help you?");
        ToggleConversationState();
    }

    private void EndSession() {
        ToggleConversationState();
        System.out.println("Hope you find my service helpful.");
        System.out.println("Till next time!");
        ToggleActiveState();
    }
    private void ToggleConversationState() {

        horizontalLine.printLine();

        if (currentState == State.ACTIVE_TALKING) {
            currentState = State.ACTIVE_LISTENING;
        }

        if (currentState == State.ACTIVE_LISTENING) {
            currentState = State.ACTIVE_TALKING;
        }
    }

    private void ToggleActiveState() {

        horizontalLine.printLine();

        if (currentState != State.INACTIVE) {
            currentState = State.INACTIVE;
        }

        if (currentState == State.INACTIVE) {
            currentState = State.ACTIVE_TALKING;
        }
    }
}
