package lex.tasks;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Todo extends Task {

    public Todo(@JsonProperty("title") String title) {
        super(title);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
