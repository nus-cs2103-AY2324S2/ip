package lex.parser;

public enum Instruction {
    LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BYE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
