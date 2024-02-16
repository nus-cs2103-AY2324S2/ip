# Earl User Guide

Earl is a **desktop chat application to track tasks, optimised for 
use via a Command Line Interface (CLI)** while still having the
benefits of a Graphical User Interface (GUI). If you can type fast,
Earl can get your task management done faster than traditional GUI
applications.

![](Ui.png)

## Features

### Adding ToDos
Adds a todo to the list of tasks.

Format: `todo <task name>`

Example: `todo homework`, `todo wash clothes`

*Notes*
+ `<task name>` cannot be empty

### Adding Deadlines

Adds a deadline to the list of tasks.

Format: `deadline <task name> /by <date time>`

Example: `deadline project submission /by 01/01/2024 2359`

*Notes*
+ `<task name>` cannot be empty.
+ `<date time>` must be of the format `dd/mm/yyyy hhmm`

### Adding Events

Adds an event to the list of tasks.

Format: `event <task name> /from <date time> /to <date time>`

Example: `event exam /from 01/01/2024 1200 /to 01/01/2024 1400`

*Notes*
+ `<task name>` cannot be empty.
+ `<date time>` must be of the format `dd/mm/yyyy hhmm`

### List

Displays all tasks currently tracked.

Format: `list`

### Find

Displays all tasks with details matching a pattern.

Format: `find <pattern>`

Example: `find wash clothes`

### Mark

Marks a task as complete.

Format: `mark <index or range> [<index or range>, ...] `

Example: `mark 1 3-5`

*Notes*
+ Arguments are space separated single indices or ranges
+ Ranges must be of the form `<index>-<index>`