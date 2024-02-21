## 🐢 Hang Ten with Tony! 🏄‍♂️

### Adding Tasks

Hey dudes! Check out how you can give Tony some gnarly tasks to keep track of!

For tasks that require dates, like deadlines and events, Tony uses `YYYY-MM-DDTHH:MM`! Totally rad, right?

#### Todo

Todos are chill, no deadlines, just vibes! Use `todo DESCRIPTION` to add one!

Examples:

- `todo Catch some waves`
- `todo Lay some eggs`

Outcome: Tony adds the Todo task to his list!

#### Deadline

Deadlines are like, well, deadlines! Use `deadline DESCRIPTION /by TIME` to set one!

Examples:

- `deadline Pick Up Shelly from Whirlpool School /by 2024-01-21T18:30`

Outcome: Tony adds the Deadline task to his list!

#### Event

Events are like, totally happenin', dude! Use `event DESCRIPTION /from START_TIME /to END_TIME` to add one!

Examples:

- `event High Tide /from 2024-02-20T16:00 /to 2024-02-18T04:00`

Outcome: Tony adds the Event task to his list!

### Updating Tasks

Time for some tune-ups! Use update commands to modify your tasks.

#### Update Description

To update the description of a task, use `update INDEX description NEW_DESCRIPTION`.

Outcome: Tony updates the description of the task at `INDEX` to `NEW_DESCRIPTION`.

#### Update Date

To update the due date, start date, or end date of a task, use `update INDEX FIELD NEW_DATE`.

Fields: `by` (for Deadline tasks), `from` (for Event tasks), `to` (for Event tasks).

Examples:

- `update 1 by 2024-03-15T12:00`: Updates the due date of the task at index 1.
- `update 2 from 2024-03-15T12:00`: Updates the start date of the event at index 2.

Outcome: Tony updates the date of the task at `INDEX` to `NEW_DATE`.

### Listing Tasks

Check out all the tasks Tony's hangin' onto, each with its own groovy `INDEX`!

Outcome: Tony returns all the tasks he's trackin' along with their statuses!

### Marking Tasks

Time to make some moves and mark tasks as complete or incomplete!

#### Mark

To give a task the stamp of completion, use `mark INDEX`.

Outcome: The task at `INDEX` gets marked as complete!

#### Unmark

To undo a task's completion, use `unmark INDEX`.

Outcome: The task at `INDEX` gets marked as incomplete!

### Deleting Tasks

Time to let go, dude! Use `delete INDEX` to remove a task.

Outcome: The task at `INDEX` gets deleted from Tony's list!

### Finding Tasks

Time to do some deep-sea searching! Use `find PHRASE` to look for tasks.

Examples:

- `find ocean`: This would return tasks with "ocean" in their descriptions.

Outcome: Tony shows ya tasks with descriptions containing `PHRASE` as a substring!

Cowabunga! 🐢🌊

