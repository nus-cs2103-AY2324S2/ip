package ChatbotRan;

import java.util.ArrayList;
import java.util.Arrays;

abstract class Task {
    protected String contents;
    protected boolean completed;

    public Task(String contents) {
        this.contents = contents;
        this.completed = false;
    }

    abstract String getType();

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public static String[] parse(String line, int space, String... delims) {
        if (space == -1) {
            System.out.println("You've forgotten to write the contents of your task.");
            return null;
        } else {
            String[] texts = new String[delims.length + 1];
            int pos = space;
            int oldpos = pos;
            for (int i = 0; i < delims.length; i++) {
                String delim = delims[i];
                pos = line.indexOf(delim, oldpos);
                if (pos == -1) {
                    System.out.println("Missing delimiter " + delim + ".");
                    return null;
                } else {
                    texts[i] = line.substring(oldpos, pos).strip();
                    oldpos = pos + delim.length();
                }
            }
            texts[delims.length] = line.substring(oldpos).strip();
            //System.out.println(Arrays.toString(texts));
            return texts;

        }

    }

    @Override
    public String toString() {
        return "[" + this.getType() + "][" + (this.completed ? "X" : " ") + "] " + this.contents;
    }

}
