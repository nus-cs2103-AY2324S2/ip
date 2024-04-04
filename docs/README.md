# Tobias User Guide

Meet Tobias, your trusty CLI companion, making task management a blast with his interactive chat-based interface, perfect for effortlessly handling to-dos, deadlines, and events with a sprinkle of fun and flair! Say goodbye to mundane organization and hello to a whole new level of productivity and enjoyment with Tobias by your side.

![Ui screenshot](https://jawad280.github.io/ip/Ui.png)

## Adding Todos

Creates and adds a todo.

`todo buy bread`

Outcome:
```
[T][ ] buy bread
```

## Adding Deadlines

Creates and adds a deadline with a deadline date and time.

`deadline submission /by 15-03-2024 2359`

Outcome:
```
[D][ ] submission
  BY : 15 March 2024 1159 PM
```


## Adding Events

Creates and adds an event with a start and end date and time.

`event Marathon /from 17-02-2024 1000 /to 17-02-2024 1600`

Outcome:
```
[E][ ] Marathon
  FROM : 17 February 2024 1000 AM
  TO : 17 February 2024 0400 PM
```

## Listing Your Tasks

View all your tasks listed out. For commands that require task numbers, you can refer to the list.

`list`

Outcome:
```
1.[T][ ] buy bread
2.[D][ ] submission
    BY : 15 March 2024 1159 PM
3.[E][ ] Marathon
    FROM : 17 February 2024 1000 AM
    TO : 17 February 2024 0400 PM
```

## Deleting Tasks

Delete a task from your current list with their task number.

`delete 1`

Outcome:
```
Noted, I've removed this task:
  [T][ ] buy bread
```

## Marking/Unmarking Tasks as done

You can mark Tasks as done or not done using their task number.

Mark : `mark 1`

Outcome:
```
Nice! I've marked this task as done:
[T][✔] buy bread
```

Unmark : `unmark 1`

Outcome:
```
Ok, I've marked this task as not done:
[T][ ] buy bread
```

## Tagging/Untagging Tasks

You can add or remove tags to a task using their task number.

Tag : `tag 1 gardenia`

Outcome:
```
Noted, I've tagged this task:
[T][ ] buy bread
  (#gardenia)
```

> Note : When untagging, follow the following syntax : untag {task number} {tag number}

Untag : `untag 1 1`

Outcome:
```
Ok, I've untagged this task:
[T][ ] buy bread
```

## Finding Tasks

You can find tasks based on keyword searches.

`find bread`

Outcome:
```
Here are the matching tasks in your list:
    1.[T][ ] buy bread
```

## Saving

This feature will save your current task list to your local data file.

`save`


## Exiting

This feature will exit the application.
> Note : It also saves your task list before exiting !

`bye`
