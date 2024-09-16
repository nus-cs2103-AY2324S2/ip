package mike;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CommandScannerTest {
    // command tokens
    private static final Token exitToken = new Token(TokenType.EXIT, "exit");
    private static final Token listToken = new Token(TokenType.LIST, "list");
    private static final Token todoToken = new Token(TokenType.TODO, "todo");
    private static final Token deadlineToken = new Token(TokenType.DEADLINE, "deadline");
    private static final Token eventToken = new Token(TokenType.EVENT, "event");
    private static final Token markToken = new Token(TokenType.MARK, "mark");
    private static final Token unmarkToken = new Token(TokenType.UNMARK, "unmark");
    private static final Token findToken = new Token(TokenType.FIND, "find");

    // other
    private static final Token forwardDashToken = new Token(TokenType.FORWARD_DASH, "/");
    private static final Token eocToken = new Token(TokenType.EOC, "");

    @Test
    public void scanTokens_commandInput_tokenList() {
        Token tempLiteralToken;

        // literal
        List<Token> outputList = new CommandScanner("hi").scanTokens();
        Token hiToken = new Token(TokenType.LITERAL, "hi");
        List<Token> expectedList = createTokenList(hiToken, eocToken);
        assertEquals(expectedList.toString(), outputList.toString());

        // unfinished param declaration
        outputList = new CommandScanner("mark /").scanTokens();
        Token emptyParamToken = new Token(TokenType.PARAM, "");
        expectedList = createTokenList(markToken, forwardDashToken, emptyParamToken, eocToken);
        assertEquals(expectedList.toString(), outputList.toString());

        // unmark as literal not command
        outputList = new CommandScanner("mark unmark").scanTokens();
        tempLiteralToken = new Token(TokenType.LITERAL, "unmark");
        expectedList = createTokenList(markToken, tempLiteralToken, eocToken);
        assertEquals(expectedList.toString(), outputList.toString());

        // space in literal
        outputList = new CommandScanner("todo hello sir").scanTokens();
        tempLiteralToken = new Token(TokenType.LITERAL, "hello sir");
        expectedList = createTokenList(todoToken, tempLiteralToken, eocToken);
        assertEquals(expectedList.toString(), outputList.toString());

        // double spaces
        outputList = new CommandScanner("todo  hello sir   ").scanTokens();
        tempLiteralToken = new Token(TokenType.LITERAL, "hello sir");
        expectedList = createTokenList(todoToken, tempLiteralToken, eocToken);
        assertEquals(expectedList.toString(), outputList.toString());
    }

    private List<Token> createTokenList(Token... tokens) {
        return new ArrayList<Token>(Arrays.asList(tokens));
    }
}
