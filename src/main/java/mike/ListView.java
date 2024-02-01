package mike;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * ListView class is responsible for filtering tasks to be viewed by the user.
 */
public class ListView {

    private final ListViewType type;
    private final String attribute;
    private LocalDate date;

    /**
     * Constructor.
     * @param type The type of list view, usually a field.
     * @param attribute THe value a field should take.
     * @throws MikeException If
     */
    public ListView(ListViewType type, String attribute) throws MikeException {
        this.type = type;
        this.attribute = attribute;
        if (type.equals(ListViewType.DATE)) {
            try {
                this.date = LocalDate.parse(attribute);
            } catch (DateTimeParseException e) {
                throw new MikeException("Please enter a valid date in YYYY-MM-DD format.");
            }
        }
    }

    public ListView(ListViewType type) throws MikeException {
        this(type, "");
    }

    /**
     * Getter.
     * @return The type of list view.
     */
    public ListViewType getType() {
        return type;
    }

    /**
     * Filters tasks by dates.
     * @param taskDate The task date.
     * @return True if the task date is permissible by the list view, otherwise false.
     */
    public boolean dateFilter(LocalDate taskDate) {
        return !type.equals(ListViewType.DATE) || date.equals(taskDate);
    }

}
