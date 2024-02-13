package lex.parser;

/**
 * Represents instructions that the user can give to the chatbot.
 */
public enum Instruction {
    LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, FIND, HELP, BYE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
