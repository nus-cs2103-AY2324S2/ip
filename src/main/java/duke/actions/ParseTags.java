package duke.actions;

import java.util.ArrayList;

public class ParseTags {
    private String tags;

    public ParseTags(String tags) {
        this.tags = tags;
    }

    public ArrayList<String> tagsStringToArray() {
        String[] tags = this.tags.trim().split(" ");
        ArrayList<String> sizableTags = new ArrayList<>();
        for (String tag : tags) {
            sizableTags.add(tag.trim());
        }
        return sizableTags;
    }
}
