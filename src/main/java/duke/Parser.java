package duke;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {
    private final String[] strs;
    private int cursor = 0;
    
    public Parser(String str) {
        this.strs = str.split(" ");
    }
    
    public String next() throws DukeOptionParsingException {
        String ret = this.peek();
        cursor++;
        return ret;
    }
    
    public String rest() throws DukeOptionParsingException {
        if (!this.hasNext()) {
            throw new DukeOptionParsingException("command ended when an argument was expected");
        } else {
            return Stream.iterate(this.cursor, i -> i < this.strs.length, i -> i + 1)
                    .map(i -> this.strs[i])
                    .collect(Collectors.joining(" "));
        }
    }
    
    public String peek() throws DukeOptionParsingException {
        if (!this.hasNext()) {
            throw new DukeOptionParsingException("command ended when an argument was expected");
        } else {
            return this.strs[this.cursor];
        }
    }
    
    public boolean hasNext() {
        return this.cursor < this.strs.length;
    }
    
    public void assertEnd() throws DukeOptionParsingException {
        if (this.hasNext()) {
            throw new DukeOptionParsingException
                    ("option was not expected but was given: " + this.strs[this.cursor]);
        }
    }
}
