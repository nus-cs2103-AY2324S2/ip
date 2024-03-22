# Balkan Bot User Guide
> "Jebem ti mat" - Balkan Bot


## Features

### Add a task
> Balkan Bot keeps track of all the tasks in your busy schedule.

Add `todo` task
- Format: `todo <description>` <br>
- Example: `todo buy lottery tickets`

Add `deadline` task
- Format: `deadline <description> /by <yyyy-MM-dd>` <br>
- Example: `deadline celebrate Putin's Birthday /by 2024-10-07`

Add `event` task
- Format: `event <description> /from <date> /to <date>` <br>
- Example: `event revolution campaign /from 2024-01-01 /to 2024-12-31`

### View recorded tasks
> Generate a list of all the tasks Balkan Bot has kept track of.

- Format: `list`
### Keep track of task status
Mark task as done
- Format: `mark <task number in list>` <br>
- Example: `mark 2`

Mark task as not done
- Format: `unmark <task number in list>` <br>
- Example: `unmark 2`
### Search for tasks containing a keyword
> Returns a numbered list of tasks containing the keyword.
- Format: `find <keyword>` <br>
- Example: `find slovenia`
### Delete a task
- Format: `delete <task number in list>` <br>
- Example: `delete 1`
### Display all commands
- Format: `help`
### Exit Balkan Bot
- Format: `bye`