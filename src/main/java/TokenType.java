enum TokenType {
    // Command tokens
    EXIT, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE,

    // prefix character tokens
    FORWARD_DASH,

    // parameter tokens
    PARAM,

    // argument tokens
    LITERAL,

    // end of command
    EOC
}
