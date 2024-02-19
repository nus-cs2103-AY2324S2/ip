package duke.actions;

import java.util.ArrayList;

/**
 * A ParseTags command to parse through all the tags and save it in an array.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class ParseTags {
    /** String representing all tags, each separated with a whitespace. */
    private String tags;

    /**
     * Constructor for ParseTags.
     * 
     * @param tags String of all tags.
     */
    public ParseTags(String tags) {
        this.tags = tags;
    }

    /**
     * Transforms the String of all tags into an ArrayList of tags.
     * 
     * @return An ArrayList<String> containing all the tags.
     */
    public ArrayList<String> tagsStringToArray() {
        String[] tags = this.tags.trim().split(" ");
        ArrayList<String> sizableTags = new ArrayList<>();
        for (String tag : tags) {
            sizableTags.add(tag.trim());
        }
        return sizableTags;
    }
}
