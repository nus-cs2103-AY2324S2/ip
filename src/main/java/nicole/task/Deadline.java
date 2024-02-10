package nicole.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import nicole.nicoleexceptions.NicoleException;

public class Deadline extends Task {
    private LocalDate deadlineDateLocalDate;
    private String deadlineDateReformattedString;

    /**
     * Initialises a Deadline
     *
     * @param name the user request.
     * @throws NicoleException if the description is not as deadline [task] by YYYY-MM-DD.
     */
    public Deadline(String name) throws NicoleException {
        super();
        if (name.contains("null")) {
            throw new NicoleException("Describe your deadline like this: deadline [task] by YYYY-MM-DD");
        }
        this.parseDate(name);
    }
    private void parseDate(String name) throws NicoleException {
        // The expected structure of this array is [..., date] where the ... are the deadline description words
        String[] whiteSpaceSeparatedDate = name.split(" ");

        StringBuilder deadlineDescription = new StringBuilder();
        for (int i = 0; i < whiteSpaceSeparatedDate.length - 1; i++) {
            deadlineDescription.append(whiteSpaceSeparatedDate[i]).append(" ");
        }
        super.updateName(deadlineDescription.toString().trim());

        String date = whiteSpaceSeparatedDate[whiteSpaceSeparatedDate.length - 1];
        try {
            deadlineDateLocalDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new NicoleException("Are you sure your date is in the proper [YYYY-MM-DD] format...?");
        }
        if (LocalDate.now().isAfter(deadlineDateLocalDate)) {
            throw new NicoleException("Erm, the deadline can't be before now right...");
        }

        deadlineDateReformattedString = ""
                + deadlineDateLocalDate.getDayOfMonth() + " "
                + deadlineDateLocalDate.getMonth().toString() + " "
                + deadlineDateLocalDate.getYear();
    }

    @Override
    public LocalDate getDate() {
        return deadlineDateLocalDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + deadlineDateLocalDate;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Deadline)) {
            return false;
        }
        Deadline task = (Deadline)object;
        return super.equals(object) && this.getDate().equals(task.getDate());
    }
}
