package lex.tasks;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Todo.class, name = "TODO"),
        @JsonSubTypes.Type(value = Deadline.class, name = "DEADLINE"),
        @JsonSubTypes.Type(value = Event.class, name = "EVENT")
})
public abstract class Task {
    public final String type = this.getClass().getSimpleName().toUpperCase();
    protected String title;
    protected boolean isDone;

    public Task(@JsonProperty("title") String title) {
        this.title = title;
        this.isDone = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String isDoneIcon = isDone ? "X" : " ";
        return String.format("[%s] %s", isDoneIcon, title);
    }
}
