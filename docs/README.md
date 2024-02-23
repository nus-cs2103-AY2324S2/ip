# Alfred chatbot User Guide

// ![img.png](Ui.png)

// Alfred helps you keep track of all your tasks! (mainly 3 types)

## Adding deadlines

// Deadlines are tasks that have a fixed end time.

// Example of usage

Example: `deadline (description) /by (date)`

// The expected outcome is:

```
Got it. I've added this task:
  [D][] description (by: date)
Now you have x tasks in the list.
```

## Adding todos

// todos are tasks that can be done at any pont in time.

// Example of usage

Example: `todo (description)`

// The expected outcome is:

```
Got it. I've added this task:
  [T][] description
Now you have x tasks in the list.
```


## adding events

// events are tasks that last for a peroid of time.

// Example of usage

Example: `event (description) /from (start date) /to (end date)`

// The expected outcome is:

```
Got it. I've added this task:
  [E][] description (from: (start date) to: (end date))
Now you have x tasks in the list.
```

## listing tasks

// Lists all the tasks recorded by alfred

// Example of usage

Example: `list`

// The expected outcome is:

```
Here are the tasks in your list:
1.[T][] read book
2. ...
3. ...
```

## marking tasks

// Marks a task as done

// Example of usage

Example: `mark (task index)`

// The expected outcome is:

```
Nice! I've marked this task as done:
1.[T][X] read book
2. ...
3. ...
```

## unmarking tasks

// Unmarks a task as done

// Example of usage

Example: `unmark (task index)`

// The expected outcome is:

```
OK, I've marked this task as not done yet:
[T][] read book
```

## deletes tasks

// deletes a task

// Example of usage

Example: `delete (task index)`

// The expected outcome is:

```
OK, I've removed this task:
  [T][] read book
Now you have x tasks in the list.
```

## snoozing tasks

// delays a task to the next day, only applicable to deadlines and events.
// if date format is not of LocalDateTime, simply adds "day after to the end time"

// Example of usage

Example: `snooze (task index)`

// The expected outcome is:

```
This task has been delayed by a day:
  [D][] read book (by: (day after original end date))
```

## postpone tasks

// postpones a task by specified, only applicable to deadlines and events.
// if date format is not of LocalDateTime, simply adds "x days after" to the end time

// Example of usage

Example: `postpone (task index) (days)`

// The expected outcome is:

```
This task has been delayed by x days:
  [D][] read book (by: (x days after original end date))
```

## rescheduling tasks

// reschedules a task to specified dates, only applicable to deadlines and events.
// input differs between deadlines and events 

// Example of usage

Example for deadline: `reschedule (deadline index) (new date)`

Example for event: `reschedule (event index) /from (new start date) /to (new end date)`

// The expected outcome is:

```
This task has been rescheduled:
  [D][] read book (by: new date)
  
or 

This task has been rescheduled:
  [E][] read book (from: new start date to: new end date)
```