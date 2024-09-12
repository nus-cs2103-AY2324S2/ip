# Snoopy Bot User Guide


![Product screenshot goes here](Ui.png)

Welcome to Snoopy Bot! Snoopy Bot is a desktop app for managing your everyday tasks, optimized for use via a Command Line Interface (CLI)! 

If you are a speed-typer, _Snoopy Bot_ is your best friend!

## Key Features
Example: `deadline return book /by 2021-09-30 1800`
### 1. Add tasks `todo`, `deadline`, `event`
### 2. Mark tasks as done/undone `mark`
### 3. View tasks `list`
### 4. Find tasks `find`
### 5. Tags `/tag`
### 6. Delete tasks `delete`

## Command Formats

### Commands
#### Adding/Deleting tasks
- `todo {description}`: Adds a task e.g.`todo return book`
- `deadline {description} /by {datetime}`: Adds a task with a **deadline** e.g.`deadline return book /by 2021-09-30 1800`
- `event {description} /from {datetime} /to {datetime}`: Adds a task with a **start** and **end** **date** and **(optional) time** e.g.`event book reading /from 2021-09-30 1400 /to 2021-09-30 1600`
- (Optional) `/tag {your_tag}`: Tags a task e.g.`/tag important`, appear as task_descirption #important.
- `delete {Number}`: Deletes a task e.g.`delete 2`

#### Checking/Unchecking tasks
- `mark {Number}`: Marks a task as done e.g.`mark 2`
- `unmark {Number}`: Marks a task as not done e.g.`unmark 2`

#### Retrieving tasks
- `find`: Lists all tasks that contain the keyword
- `list`: Lists all tasks

#### Allowed Datetime formats
- `YYYY-MM-DD HH:MM`: e.g. `2021-09-30 18:00`
- `YYYY-MM-DD HHMM`: e.g. `2021-09-30 1800`
- `YYYY-MM-DD`: e.g. `2021-09-30`
- `YYYY/MM/DD HH:MM`: e.g. `2021/09/30 18:00`
- `YYYY/MM/DD HHMM`: e.g. `2021/09/30 1800`
- `YYYY/MM/DD`: e.g. `2021/09/30`

## Troubleshooting
1. Datetime is not recognised: Please use the format `YYYY-MM-DD HHMM` at least for the date and time.

## Glossary
- `todo`: a task
- `deadline`: a task with a deadline
- `event`: a task with a start and end date and time
