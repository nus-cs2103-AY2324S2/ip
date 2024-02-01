package mike;

/**
 * Representation of source text and context.
 * @author ningc
 */
class Token {
    public final TokenType type;
    public final String text;

    /**
     * Constructor.
     * @param type The token type.
     * @param text The source text contained in the token.
     */
    Token(TokenType type, String text) {
        this.type = type;
        this.text = text;
    }

    /**
     * Returns the type of the token as a {@link TokenType}.
     * @return the token type.
     */
    public TokenType getType() {
        return type;
    }

    /**
     * Returns the text stored in the token as a String.
     * @return the token text.
     */
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return type + " " + text;
    }
}
