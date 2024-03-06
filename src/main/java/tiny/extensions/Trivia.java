package tiny.extensions;

/**
 * Represents a trivia.
 */
public class Trivia {
    protected String body;

    /**
     * Initializes Trivias.
     *
     * @param body Content of the trivia.
     */
    public Trivia(String body) {
        this.body = body;
    }

    public String formatToSave() {
        return "TR | " + body;
    }

    @Override
    public String toString() {
        return body;
    }
}
