# Dude User Guide

![Dude Ui](./Ui.png)

Dude is your friendly neighborhood personal assistant that helps you track your tasks, deadlines and events.

# Features

## Adding Tasks

### Adding Todos

Add a todo to Dude's tracker.

```
todo (todo_name)
```

Dude should respond with an acknowledgement of the todo created!

### Adding Deadlines

Add a deadline to Dude's tracker.

```
deadline (deadline_name) /by (due_date) 
```

Note that `due_date` should be in the yyyy-MM-dd format.

Dude should respond with an acknowledgement of the deadline created!

### Adding Events

Add a event to Dude's tracker.

```
event (event_name) /from (start_date) /to (end_date) 
```

Note that `start_date` and `end_date` should be in the yyyy-MM-dd format.

Dude should respond with an acknowledgement of the event created!

## Closing Dude

Close the Dude App.

```
bye
```

## Listing Tasks

List out the tasks you've previously told Dude.

```
list 
```

Dude should respond with the current list of tasks stored!

## Deleting Tasks

Delete tasks you've completed.

```
delete (index)
```

Dude should acknowledge that it has deleted the task!

## Mark/Unmark Tasks

Mark/Unmark tasks as done.

```
mark (index)
unmark (index)
```

Dude should acknowledge the mark/unmark.

## Find Tasks

Search for a task you've saved.

```
find (search_query)
```

Dude should return the search result.

## Check Schedule

Check your schedule on the given date.

```
schedule (date)
```

Note that `date` should be in the yyyy-MM-dd format.

Dude should return any deadlines due on that date or any events occurring during that time period.

