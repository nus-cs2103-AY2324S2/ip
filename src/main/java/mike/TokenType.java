package mike;

enum TokenType {
    // primary command tokens
    EXIT, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND,

    // prefix character tokens
    FORWARD_DASH,

    // parameter tokens
    PARAM,

    // argument tokens
    LITERAL,

    // end of command
    EOC
}
