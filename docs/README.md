# Alpaca :llama: User Guide

![Product Screenshot](./Ui.png)

Organise your life with a helpful companion :llama:

# Commands

## Adding Tasks

### Adding Deadlines

Adding a deadline.

Format: `deadline {task}`

Example: `Deadline Homework /by 02/02/2022`

Expected outcome:

```
Got it. I've added this task
[D][ ] Homework (by: Feb 2 2022)
Now you have 1 task in the list.
```

### Adding Events

Adding an event.

Format: `event {task}`

Example: `Event Dinner w Alapaca /by 02/02/2022`

Expected outcome:

```
Got it. I've added this task
[E][ ] Dinner w Alapaca (by: Feb 2 2022)
Now you have 2 tasks in the list.
```

### Adding ToDo

Adding a todo.

Format: `todo {task}`

Example: `ToDo Feed the Alpaca /by 02/02/2022`

Expected outcome:

```
Got it. I've added this task
[T][ ] Feed the Alpaca (by: Feb 2 2022)
Now you have 3 tasks in the list.
```

## List Tasks

Listing tasks.

Format: `list`

Example: `List`

Expected outcome:

```
Here are the tasks in your list:
1. [D][ ] Homework (by: Feb 2 2022)
2. [E][ ] Dinner w Alapaca (by: Feb 2 2022)
3. [T][ ] Feed the Alpaca (by: Feb 2 2022)
```
## Marking / Unmarking

### Mark

Mark a task.

Format: `mark {index}`

Example: `Mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
[D][X] Homework (by: Feb 2 2022)
```
### Unmark

Unmark a task.

Format: `unmark {index}`

Example: `Unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
[D][ ] Homework (by: Feb 2 2022)
```

# Other Features

## Optional Fields

To create an optional field,

Append `/{field} args` to any task command

Example: `Deadline Homework /by 02/02/2022`

Expected outcome:

```
Got it. I've added this task
[D][ ] Homework (by: Feb 2 2022)
Now you have 1 task in the list.
```

## Dates Processing

To use dates:

Place `{date/day}` anywhere in an optional field.

It adds the date or the next occuring {day} from the current time.

Examples:

- `Deadline Homework /by 02/02/2022`
- `Deadline Homework /by Wednesday`
- `Deadline Homework /by Wed`

Expected outcome:

```
Got it. I've added this task
[D][ ] Homework (by: Feb 2 2022)
Now you have 1 task in the list.
```
