package sleepy.items;

/**
 * This class is a type of item.
 *
 * @author kjw142857
 */
public class ToDo extends Item {
    public ToDo(String rawDescription, String description) {
        super(rawDescription, description);
    }

    /**
     * Returns the description of this toDo.
     *
     * @return Description of this toDo.
     */
    @Override
    public String getDescription() {
        return "[T]" + super.getDescription();
    }
}
