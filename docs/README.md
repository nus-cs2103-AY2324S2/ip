# Squid

## Overview
Introducing **Squid**! No, he's not seafood 🦑. Instead, he's a black cat! 🐈‍⬛
He seems grumpy and too cool for you, but deep inside he's just a cuddly cat.
> I'll handle your tasks for you... Just feed me after. - Squid

<img src="./Ui.png">

## Features

> Keywords in square brackets [ ] are parameters that need to be in the following formats:

`[message]`: A message of your choice!

`[task]`: A task name of your choice!

`[date]`: A date. One possible format could be `hh:mm:ss, DD-MM-YYYY`.

`[keyword]`: A keyword that you might want to look for.

`[index]`: A valid numbering of a task.

### Help
Gets the full list of commands.

Usage: `help`
### Echo
Gets Squid to repeat after you.

Usage: `echo [message]`
### Todo
Adds a task without a deadline or timeframe.

Usage: `todo [task]`
### Deadline
Adds a task with a deadline.

Usage: `deadline [task] /by [date]`
### Event
Adds a task with a from and to date.

Usage: `event [task] /from [date] /to [date]`
### Find
Find tasks with matching keywords.

Usage: `find [keyword]`
### Delete
Deletes a task at the specified index.

Usage: `delete [index]`
### Mark
Marks a task as complete.

Usage: `mark [task]`
### Unmark
Marks a task as incomplete.

Usage: `unmark [task]`