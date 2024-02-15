package jivox.task;

public class Tag {
    private final String content;

    public Tag(String content){
        this.content = content;
    }

    @Override
    public String toString(){
        return content;
    }
}
