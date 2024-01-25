import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class CommandScanner {
    private static final Map<String, TokenType> commands;

    static {
        commands = new HashMap<String, TokenType>(0);
        commands.put("bye", TokenType.EXIT);
        commands.put("list", TokenType.LIST);
        commands.put("mark", TokenType.MARK);
        commands.put("unmark", TokenType.UNMARK);
        commands.put("todo", TokenType.TODO);
        commands.put("deadline", TokenType.DEADLINE);
        commands.put("event", TokenType.EVENT);
        commands.put("delete", TokenType.DELETE);
    }

    private final String source;
    private final List<Token> tokens;
    private int start;
    private int current;

    CommandScanner(String source) {
        this.source = source;
        this.tokens = new ArrayList<Token>();
        this.start = 0;
        this.current = 0;
    }

    public List<Token> scanTokens() {
        // command token
        scanStartToken();
        // argument tokens
        while (!isAtEnd()) {
            start = current;
            scanToken();
        }

        scanEndToken();
        return tokens;
    }

    private void scanEndToken() {
        tokens.add(new Token(TokenType.EOC, ""));
    }

    private void scanToken() {
        char c = advance();
        switch (c) {
            case '/':
                prefix();
                parameter();
                break;
            case ' ':
            case '\r':
            case '\t':
                // ignore whitespace
                break;
            default:
                literal();
                break;
        }
    }

    private void literal() {
        while (peek() != '/' && !isAtEnd()) advance();
        addToken(TokenType.LITERAL);
    }

    private void parameter() {
        start = current;
        while (!isWhiteSpace(peek()) && !isAtEnd()) advance();
        addToken(TokenType.PARAM);
    }

    private void prefix() {
        addToken(TokenType.FORWARD_DASH);
    }

    private void scanStartToken() {
        while (!isWhiteSpace(peek()) && !isAtEnd()) advance();
        String text = source.substring(start, current);
        TokenType type = commands.get(text);
        if (type == null) type = TokenType.LITERAL;
        // flag that LITERAL is not recognised command when parsing
        addToken(type);
    }

    private void addToken(TokenType type) {
        String text = source.substring(start, current);
        Token newToken = new Token(type, text);
        tokens.add(newToken);
    }

    private char peek() {
        if (isAtEnd()) return '\0';
        return source.charAt(current);
    }

    private boolean isWhiteSpace(char c) {
        return c == ' ' || c == '\t' || c == '\r';
    }

    private char advance() {
        return source.charAt(current++);
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }


}
