package mike;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import mike.command.Command;
import mike.command.ExitCommand;
import mike.command.ListCommand;
import mike.command.MarkCommand;

public class CommandParserTest {
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

    // parameter tokens
    private static final Token byParamToken = new Token(TokenType.PARAM, "by");
    private static final Token fromParamToken = new Token(TokenType.PARAM, "from");
    private static final Token toParamToken = new Token(TokenType.PARAM, "to");
    private static final Token viewParamToken = new Token(TokenType.PARAM, "view");
    private static final Token attributeParamToken = new Token(TokenType.PARAM, "attribute");

    // literal tokens
    private static final Token literalToken = new Token(TokenType.LITERAL, "random literal");
    private static final Token descriptionToken = new Token(TokenType.LITERAL, "task description");
    private static final Token dateToken1 = new Token(TokenType.LITERAL, "2024-01-31");
    private static final Token dateToken2 = new Token(TokenType.LITERAL, "2024-02-31");
    private static final Token dateArgumentToken = new Token(TokenType.LITERAL, "date");
    private static final Token numberToken = new Token(TokenType.LITERAL, "1");
    private static final Token descriptionArgumentToken = new Token(TokenType.LITERAL, "description");
    private static final Token keywordToken = new Token(TokenType.LITERAL, "desc");

    @Test
    public void parse_exitValidTokenList_exitCommand() throws MikeException {
        List<Token> tokens = createTokenList(exitToken, eocToken);
        ExitCommand expectedCommand = new ExitCommand();

        try {
            assertEquals(new CommandParser(tokens).parse().toString(), expectedCommand.toString());
        } catch (MikeException e) {
            assertEquals("Usage: bye", e.getMessage());
            fail();
        }
    }

    @Test
    public void parse_exitInvalidTokenList_exceptionThrown() {
        List<Token> tokens;

        // literal after exit command
        tokens = createTokenList(exitToken, literalToken, eocToken);
        try {
            new CommandParser(tokens).parse();
            fail();
        } catch (MikeException e) {
            assertEquals("Usage: bye", e.getMessage());
        }

        // forward dash after exit command
        tokens = createTokenList(exitToken, forwardDashToken, eocToken);
        try {
            new CommandParser(tokens).parse();
            fail();
        } catch (MikeException e) {
            assertEquals("Usage: bye", e.getMessage());
        }

        // forward dash, parameter, argument after exit command
        tokens = createTokenList(exitToken, forwardDashToken, byParamToken, literalToken, eocToken);
        try {
            new CommandParser(tokens).parse();
            fail();
        } catch (MikeException e) {
            assertEquals("Usage: bye", e.getMessage());
        }

        // no eoc
        /*tokens = createTokenList(exitToken);
        try {
            new CommandParser(tokens).parse();
            fail();
        } catch (MikeException e) {
            assertEquals("Usage: bye", e.getMessage());
        }*/
    }

    @Test
    public void parse_listValidTokenList_listCommand() {
        List<Token> tokens;
        Command outputCommand;
        ListCommand expectedCommand;

        // list default
        tokens = createTokenList(listToken, eocToken);
        try {
            expectedCommand = new ListCommand(new ListView(ListViewType.NONE));
            outputCommand = new CommandParser(tokens).parse();
            assertEquals(outputCommand.toString(), expectedCommand.toString());
        } catch (MikeException e) {
            fail();
        }

        // list with view date
        tokens = createTokenList(listToken,
                forwardDashToken, viewParamToken, dateArgumentToken,
                forwardDashToken, attributeParamToken, dateToken1,
                eocToken);
        try {
            expectedCommand = new ListCommand(new ListView(ListViewType.DATE, dateToken1.getText()));
            outputCommand = new CommandParser(tokens).parse();
            assertEquals(outputCommand.toString(), expectedCommand.toString());
        } catch (MikeException e) {
            fail();
        }

        // list with view keyword
        tokens = createTokenList(listToken,
                forwardDashToken, viewParamToken, descriptionArgumentToken,
                forwardDashToken, attributeParamToken, keywordToken,
                eocToken);
        try {
            expectedCommand = new ListCommand(new ListView(ListViewType.DESCRIPTION, keywordToken.getText()));
            outputCommand = new CommandParser(tokens).parse();
            assertEquals(outputCommand.toString(), expectedCommand.toString());
        } catch (MikeException e) {
            fail();
        }
    }

    @Test
    public void parse_listInvalidList_exceptionThrown() {
        List<Token> tokens;

        // unexpected literal
        tokens = createTokenList(listToken, literalToken, eocToken);
        try {
            new CommandParser(tokens).parse();
            fail();
        } catch (MikeException e) {
            assertEquals("Usage: list", e.getMessage());
        }

        // unexpected literal
        tokens = createTokenList(listToken, literalToken,
                forwardDashToken, viewParamToken, descriptionArgumentToken,
                forwardDashToken, attributeParamToken, keywordToken,
                eocToken);
        try {
            new CommandParser(tokens).parse();
            fail();
        } catch (MikeException e) {
            assertEquals("Usage: list", e.getMessage());
        }

        // double forward dash
        tokens = createTokenList(listToken, forwardDashToken, attributeParamToken, eocToken);
        try {
            new CommandParser(tokens).parse();
            fail();
        } catch (MikeException e) {
            assertEquals("Usage: list /view [type] /attribute [attribute]", e.getMessage());
        }
    }

    @Test
    public void parse_markValidTokenList_markCommand() {
        List<Token> tokens;
        Command outputCommand;
        MarkCommand expectedCommand;

        // mark default
        tokens = createTokenList(markToken, numberToken, eocToken);
        try {
            expectedCommand = new MarkCommand(Integer.parseInt(numberToken.getText()));
            outputCommand = new CommandParser(tokens).parse();
            assertEquals(outputCommand.toString(), expectedCommand.toString());
        } catch (MikeException e) {
            fail();
        }
    }

    @Test
    public void parse_markInvalidTokenList_exceptionThrown() {
        List<Token> tokens;

        // no argument
        tokens = createTokenList(markToken, eocToken);
        try {
            new CommandParser(tokens).parse();
            fail();
        } catch (MikeException e) {
            assertEquals("Usage: mark [number]", e.getMessage());
        }

        // not a number
        tokens = createTokenList(markToken, literalToken, eocToken);
        try {
            new CommandParser(tokens).parse();
            fail();
        } catch (MikeException e) {
            assertEquals("One, two, three, four, get the kid back through the door!\n"
                    + "'random literal' is not an integer Sulley...", e.getMessage());
        }

        // option
        tokens = createTokenList(markToken, numberToken,
                forwardDashToken, toParamToken, dateToken1, eocToken);
        try {
            new CommandParser(tokens).parse();
            fail();
        } catch (MikeException e) {
            assertEquals("Usage: mark [number]", e.getMessage());
        }
    }

    private List<Token> createTokenList(Token... tokens) {
        return new ArrayList<Token>(Arrays.asList(tokens));
    }
}
