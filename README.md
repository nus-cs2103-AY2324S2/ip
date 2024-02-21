# Bingus: The Friendly Cat TaskChecker!

Cats Galore!



>   "I have no special talent. I am only passionately curious." -  Albert Einstein [(source)](https://github.com/dwyl/quotes)


Bingus is a task management application based on [Project Duke](https://nus-cs2103-ay1920s1.github.io/website/se-book-adapted/projectDuke/index.html).

## Commands that Bingus Understands!

- __todo <task-name>:__ Adds a new ToDo item.

* __deadline <task-name> \by <dd-MM-yyyy HH:mm>:__ Adds a new Deadline task.

+ __event <task-name> \from <dd-MM-yyyy HH:mm> \to <dd-MM-yyyy HH:mm>:__ Adds a new Event item.

+ __mark <task-number>:__ Marks task at index <task-number> as done.

+ __unmark <task-number>:__ Marks task at index <task-number> as not done.

+ __delete <task-number>:__ Deletes task at index <task-number>.

+ __find <task-name>:__ Finds task with matching keywords of <task-name>.

+ __bye:__ Exits the program.

## Levels that Bingus went through!

- [x] Level-0: Renaming Bot
- [x] Level-1: Greet, Echo, Exit
- [x] Level-2: Add, List
- [x] Level-3: Mark as Done
- [x] Level-4: ToDos, Events, Deadlines
- [x] Level-5: Handle Errors
- [x] Level-6: Delete
- [x] Level-7: Save
- [x] Level-8: Dates and Times
- [x] Level-9: Find
- [x] Level-10: GUI

## Bingus is here to...

1. record the tasks
2. mark the taks as done or not
3. record deadlines and durations of deadlines and events!

## Bingus records events using...

````
```
public class Event extends Task{

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Creates an event task.
     *
     * @param description Description of the task.
     * @param from Start date of the task.
     * @param to End date of the task.
     */
    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.from = LocalDate.parse(from, formatter);
        this.to = LocalDate.parse(to, formatter);
    }

    /**
     * getter for task icon.
     *
     */
    @Override
    public String getTaskIcon() {
        return "E";
    }

    /**
     * returns string representation of event.
     *
     */
    @Override
    public String ToString() {
        return super.ToString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    /**
     * returns a string representation of event used to store the event on hard drive.
     *
     */
    @Override
    public String toStore() {
        if (isDone) {
            return getTaskIcon() + "/" + "1" + "/" + description + "/" + from + "/" + to;
        } else {
            return getTaskIcon() + "/" + "0" + "/" + description + "/" + from + "/" + to;
        }
    }
```
````
    



