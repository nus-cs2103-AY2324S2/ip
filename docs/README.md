# Lucifer

<img src="Ui.png" width="300" >

Lucifer is a task management tool that helps you keep track of your tasks and deadlines. It is a command line application that allows you to add, delete, mark, unmark tasks as done. It also allows you to add deadlines, ToDo and events to your tasks.

## Adding deadlines: ```deadline```

Deadline tasks are added to the list of tasks with the deadline date and time.
It is added to the list of tasks with the deadline date and time.

Format: `deadline <task> /by <date> <time>`

Example: `deadline CS2103T iP /by 31/12/2024 1400`

## Adding events: ```events```
Events are added to the list of tasks with the from and to in the date format of the event.

Format: `event <task> /from <date> /to <date> <time>`

Example: `event CS2103T iP /from 2021-09-17 /to 2021-09-18`

## Adding ToDos: ```todo```

ToDos are added to the list of tasks. 

Format: `todo <task>`

Example: `todo CS2103T iP`

## Listing all tasks: ```list```

List all tasks in the list of tasks.

Format: `list`

Example: `list`

## Marking tasks as done: ```mark```

Marking tasks as done in the list of tasks.

Format: `mark <task number>`

Example: `mark 1`
## Unmarking tasks as done: ```unmark```

Unmarking tasks as done in the list of tasks.

Format: `unmark <task number>`

Example: `unmark 1`

## Deleting tasks: ```delete```

Deleting tasks from the list of tasks.

Format: `delete <task number>`

Example: `delete 1`

## Finding tasks: ```find```

Finding tasks from the list of tasks.

Format: `find <keyword>`

Example: `find CS2103T`

## Accessing Help Page: ```help```

Access the help page and get all the information regarding the commands and features of Lucifer.

Format: `help`

Example: `help`

## Exiting the application: ```bye```

Exiting the application.

Format: `bye`

Example: `bye`
