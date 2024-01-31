package mike;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ListView {

    private final ListViewType type;
    private final String attribute;
    private LocalDate date;

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

    public ListViewType getType() {
        return type;
    }

    public boolean dateFilter(LocalDate taskDate) {
        return !type.equals(ListViewType.DATE) || date.equals(taskDate);
    }

}
