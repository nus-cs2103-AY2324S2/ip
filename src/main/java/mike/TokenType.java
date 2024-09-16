package mike;

/**
 * Types of tokens in the command input.
 * @author ningc
 */
enum TokenType {
    // primary command tokens
    EXIT, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND,
    ARCHIVE,

    // prefix character tokens
    FORWARD_DASH,

    // parameter tokens
    PARAM,

    // argument tokens
    LITERAL,

    // end of command
    EOC
}
