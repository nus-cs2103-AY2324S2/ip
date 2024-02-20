# Snoopy Bot User Guide


![Product screenshot goes here](docs/images/Ui.png)

Welcome to Snoopy Bot! Snoopy Bot is a desktop app for managing your everyday tasks, optimized for use via a Command Line Interface (CLI)! 

If you are a speed-typger, Snoopy Bot is your best friend!

## Adding deadlines

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

Example: `deadline return book /by 2021-09-30 1800`

Example: `list`

// A description of the expected outcome goes here

```
expected output
```

## Features
### Adding tasks: 
- `todo`: a task
- `deadline`: a task with a deadline
- `event`: a task with a start and end date and time

### View tasks


### Find tasks
`find`

Can't remember what your exact task is in the long list? Just `find YOUR_KEYWORD`!

### Tags:
`/tag`

This is an additional parameter for any task. You can tag your task with a keyword to make it easier to find later.


## How to use Snoopy Bot
### Commands
#### Retrieving tasks
- `find`: Lists all tasks that contain the keyword
- `list`: Lists all tasks
- `mark {Number}`: Marks a task as done e.g.`mark 2`
- `unmark {Number}`: Marks a task as not done e.g.`unmark 2`
- `delete {Number}`: Deletes a task e.g.`delete 2`
- `/tag {Number}`: Tags a task e.g.`/tag important`, appear as task_descirption #important.

#### Creating tasks
- `todo`: Adds a task e.g.`todo read book`
- `deadline`: Adds a task with a deadline e.g.`deadline return book /by 2021-09-30 1800`
- `event`: Adds a task with a start and end date and time e.g.`event project meeting /from 2021-09-30 1400 /to 2021-09-30 1600 /tag fun`

## Troubleshooting
1. Datetime is not recognised: Please use the format `YYYY-MM-DD HHMM` at least for the date and time.
