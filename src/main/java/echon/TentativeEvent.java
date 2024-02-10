package echon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a tentative event task scheduled in multiple slots.
 */
public class TentativeEvent extends Task {
    private ArrayList<LocalDateTime> fromDates;
    private ArrayList<LocalDateTime> toDates;

    /**
     * Creates a tentative event task.
     *
     * @param description Description of the task.
     * @param fromDate Tentative start date of the task.
     * @param toDate Tentative end date of the task.
     */
    public TentativeEvent(String description, String fromDate, String toDate) throws EchonException {
        super(description);
        this.fromDates = new ArrayList<>();
        this.toDates = new ArrayList<>();
        addSlotToArray(fromDate, toDate);
    }

    private void addSlotToArray(String fromDate, String toDate) throws EchonException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fromDateObject;
        LocalDateTime toDateObject;
        try {
            fromDateObject = LocalDateTime.parse(fromDate, formatter);
            toDateObject = LocalDateTime.parse(toDate, formatter);
        } catch (Exception e) {
            throw new EchonException("Please enter a valid date and time in the format yyyy-MM-dd HH:mm");
        }
        this.fromDates.add(fromDateObject);
        this.toDates.add(toDateObject);
    }

    /**
     * Adds a tentative slot to the event.
     *
     * @param fromDate Tentative start date of the task.
     * @param toDate Tentative end date of the task.
     */
    public void addTentativeSlot(String fromDate, String toDate) throws EchonException {
        addSlotToArray(fromDate, toDate);
    }

    /**
     * Creates an event with only one selected tentative slot.
     */
    public Event createEvent(int index) throws EchonException {
        if (index < 0 || index >= this.fromDates.size()) {
            throw new EchonException("OOPS!!! The slot index is invalid.");
        }
        LocalDateTime fromDate = this.fromDates.get(index);
        LocalDateTime toDate = this.toDates.get(index);
        return new Event(this.getDescription(), fromDate, toDate);
    }

    @Override
    public String toFileLine() {
        String fileLine = super.toFileLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        StringBuilder fileLineBuilder = new StringBuilder("TE | ");
        fileLineBuilder.append(fileLine.substring(4));
        for (int i = 0; i < this.fromDates.size(); i++) {
            fileLineBuilder.append(" | ");
            fileLineBuilder.append(this.fromDates.get(i).format(formatter));
            fileLineBuilder.append(" | ");
            fileLineBuilder.append(this.toDates.get(i).format(formatter));
        }
        return fileLineBuilder.toString();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        StringBuilder stringBuilder = new StringBuilder("[E]");
        stringBuilder.append(super.toString());
        for (int i = 0; i < this.fromDates.size(); i++) {
            stringBuilder.append(String.format(" (tentative slot %d from: ", i + 1));
            stringBuilder.append(this.fromDates.get(i).format(formatter));
            stringBuilder.append(" to: ");
            stringBuilder.append(this.toDates.get(i).format(formatter));
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }
}
