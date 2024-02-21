package jade.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jade.exception.JadeException;

/**
 * The <code>RecurringTask</code> object represents a user task within a time range,
 * and repeats in certain frequency.
 */
public class RecurringTask extends Task {
    /**
     *  Frequency of the recurrence task.
     */
    public enum TaskFreq {
        Daily,
        Weekly,
        Monthly,
    }
    /** For converting letter into the TaskFreq it represents */
    public static final Map<String, TaskFreq> LOOK_UP_TABLE = Arrays.stream(TaskFreq.values())
            .collect(Collectors.toMap(
                    freq -> freq.toString().substring(0, 1),
                    freq -> freq));
    /** For converting TaskFreq into its first char */
    private final Map<TaskFreq, String> lookUpTableReversed = Arrays.stream(TaskFreq.values())
            .collect(Collectors.toMap(
                    freq -> freq,
                    freq -> freq.toString().substring(0, 1)));
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final TaskFreq taskFreq;

    /**
     * Class constructor specifying the event description, start date, end date,
     * start time, end time, and the task frequency.
     */
    public RecurringTask(String description, LocalDate startDate, LocalDate endDate,
                         LocalTime startTime, LocalTime endTime, TaskFreq taskFreq) throws JadeException {
        super(description);
        if (endDate.isBefore(startDate)) {
            throw new JadeException(Event.DATE_UNEXPECTED_ERROR);
        }
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.taskFreq = taskFreq;
    }

    /**
     * Class constructor specifying the event description, start date, end date,
     * start time, end time, the task frequency, and the completion status.
     */
    public RecurringTask(String description, LocalDate startDate, LocalDate endDate, LocalTime startTime,
                         LocalTime endTime, TaskFreq taskFreq, boolean isDone) throws JadeException {
        super(description, isDone);
        if (endDate.isBefore(startDate)) {
            throw new JadeException(Event.DATE_UNEXPECTED_ERROR);
        }
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.taskFreq = taskFreq;
    }

    public TaskList<Event> getAllTasks() {
        return generateRecurTaskList(description, startDate, endDate, startTime, endTime, taskFreq);
    }

    private TaskList<Event> generateRecurTaskList(String description, LocalDate startDate, LocalDate endDate,
                                                  LocalTime startTime, LocalTime endTime, TaskFreq taskFreq) {
        switch(taskFreq) {
        case Daily:
            return addDailyTasks(description, startDate, endDate, startTime, endTime);
        case Weekly:
            return addWeeklyTasks(description, startDate, endDate, startTime, endTime);
        case Monthly:
            return addMonthlyTasks(description, startDate, endDate, startTime, endTime);
        default:
            assert false;
            return new TaskList<>();
        }
    }
    private LocalDateTime combineDateTime(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date, time);
    }


    private TaskList<Event> addDailyTasks(String description, LocalDate startDate, LocalDate endDate,
                                          LocalTime startTime, LocalTime endTime) {
        List<Event> recurTasks = Stream.iterate(startDate, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(startDate, endDate))
                .map(x -> {
                    try {
                        return new Event(description, combineDateTime(x, startTime),
                                combineDateTime(x, endTime));
                    } catch (JadeException e) {
                        return null;
                    }
                })
                .collect(Collectors.toList());
        return new TaskList<>(recurTasks);
    }

    private TaskList<Event> addWeeklyTasks(String description, LocalDate startDate, LocalDate endDate,
                                           LocalTime startTime, LocalTime endTime) {
        List<Event> recurTasks = Stream.iterate(startDate, date -> date.plusWeeks(1))
                .limit(ChronoUnit.WEEKS.between(startDate, endDate))
                .map(x -> {
                    try {
                        return new Event(description, combineDateTime(x, startTime),
                                combineDateTime(x, endTime));
                    } catch (JadeException e) {
                        return null;
                    }
                })
                .collect(Collectors.toList());
        return new TaskList<>(recurTasks);
    }

    private TaskList<Event> addMonthlyTasks(String description, LocalDate startDate, LocalDate endDate,
                                            LocalTime startTime, LocalTime endTime) {
        List<Event> recurTasks = Stream.iterate(startDate, date -> date.plusMonths(1))
                .limit(ChronoUnit.MONTHS.between(startDate, endDate))
                .map(x -> {
                    try {
                        return new Event(description, combineDateTime(x, startTime),
                                combineDateTime(x, endTime));
                    } catch (JadeException e) {
                        return null;
                    }
                })
                .collect(Collectors.toList());
        return new TaskList<>(recurTasks);
    }

    /**
     * Returns a formatted string of the LocalDate object.
     */
    public String dateFormatter(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d uuuu"));
    }
    /**
     * Returns a formatted string of the LocalTime object.
     */
    public String timeFormatter(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern("hh:mm a", Locale.UK));
    }
    /**
     * {@inheritDoc}
     * Checks whether one of all periodical dates of the task
     * is equal the given date.
     */
    @Override
    public boolean isSameDate(LocalDate date) {
        TaskList<Event> taskList = getAllTasks();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).isSameDate(date)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[RT]%s (from: %s to: %s | %s to %s %s)", super.toString(),
                dateFormatter(startDate), dateFormatter(endDate), timeFormatter(startTime),
                timeFormatter(endTime), taskFreq);
    }

    /**
     * {@inheritDoc}
     * Adds the start dateTime and end dateTime at the end.
     */
    @Override
    public String taskFormatter() {
        return String.format("RT | %s | %s | %s - %s | %s - %s | %s\n", statusFormatter(),
                description, dateFormatter(startDate), dateFormatter(endDate),
                timeFormatter(startTime), timeFormatter(endTime), lookUpTableReversed.get(taskFreq));
    }
}

