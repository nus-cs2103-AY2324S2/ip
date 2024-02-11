package mike;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CommandScanner is the class responsible or scanning the command input
 * @author ningc
 */
class CommandScanner {
    /**
     * Look up table for commands.
     */
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
        commands.put("find", TokenType.FIND);
    }

    private final String source;
    private final List<Token> tokens;
    private int start;
    private int current;

    /**
     * Constructor.
     * @param source The raw command input.
     */
    CommandScanner(String source) {
        this.source = source;
        this.tokens = new ArrayList<Token>();
        this.start = 0;
        this.current = 0;
    }

    /**
     * Scans the command input to return a list of tokens. See {@link mike.Token}.
     * @return the list of tokens characterizing the command input.
     */
    public List<Token> scanTokens() {
        // command token
        tokens.add(getStartToken());

        // argument tokens
        while (!isAtEnd()) {
            start = current;
            tokens.addAll(getNextTokens());
        }

        // EOC token
        tokens.add(getEndToken());
        return tokens;
    }

    private Token getEndToken() {
        return new Token(TokenType.EOC, "");
    }

    private ArrayList<Token> getNextTokens() {
        char c = advance();
        ArrayList<Token> nextTokens = new ArrayList<Token>();
        switch (c) {
        case '/':
            nextTokens.add(getPrefix());
            nextTokens.add(getParameter());
            return nextTokens;
        case ' ':
        case '\r':
        case '\t':
            // ignore whitespace
            break;
        default:
            nextTokens.add(getLiteral());
            return nextTokens;
        }
        return nextTokens;
    }

    private Token getLiteral() {
        while (peek() != '/' && !isAtEnd()) {
            advance();
        }
        return createToken(TokenType.LITERAL);
    }

    private Token getParameter() {
        start = current;

        while (!isWhiteSpace(peek()) && !isAtEnd()) {
            advance();
        }

        return createToken(TokenType.PARAM);
    }

    private Token getPrefix() {
        return createToken(TokenType.FORWARD_DASH);
    }

    private Token getStartToken() {
        while (!isWhiteSpace(peek()) && !isAtEnd()) {
            advance();
        }

        String text = source.substring(start, current);
        TokenType type = commands.get(text);

        if (type == null) {
            type = TokenType.LITERAL; // flag that LITERAL is not recognised command when parsing
        }

        return createToken(type);
    }

    private Token createToken(TokenType type) {
        String text = source.substring(start, current).strip();
        return new Token(type, text);

    }

    private char peek() {
        if (isAtEnd()) {
            return '\0';
        }
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
