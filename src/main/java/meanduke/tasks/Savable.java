package meanduke.tasks;

/**
 * A savable is an object that can be saved to disk, containing information from the interaction with Mean Duke
 */
public interface Savable {

    /**
     * Produces the string containing interaction data to be stored.
     *
     * @return string containing save data
     */
    String saveString();
}
