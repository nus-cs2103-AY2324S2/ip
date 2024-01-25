public class Token {
    final TokenType type;
    final String text;

    Token(TokenType type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return type + " " + text;
    }
}
