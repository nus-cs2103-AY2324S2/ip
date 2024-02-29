# GMO User Guide 🤖

// Product screenshot goes here

Are you an avid gamer that is tied down by work or school? Do you find it hard to keep track of your tasks and deadlines? 
Look no further! GMO is here to help _MANAGE_ and _WHINE_ at you to get your dues done!

## Add Tasks:  `todo`, `due`, `event`

Add your tasks to GMO's log. GMO will keep track of them for you.
1. **Todo:** A simple task with no deadline.
2. **Deadline:** A task with a deadline.
3. **Event:** A task/event with a specific time frame.

**Command formats:** 
- `todo <task description>`
- `due <task description> /by <d/M/yyyy HHmm>`
- `event <task description> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>`

**Example commands:**
- `todo read book`
- `due return book /by 2/5/2024 1800`
- `event work meeting /from 3/3/2024 1400 /to 3/3/2024 1600`

**Expected output:**
```
GMO has added the task (づ￣ ³￣)づ
added: <task details>
you now have <int> tasks in the log
```

## List All Tasks: `log`
Lists all tasks recorded in the log.

**Command format:** `log`

**Example output:**
```
1. [X][T] read book
2. [ ][D] return book (by: 2/5/2024 1800)
3. [ ][E] work meeting (from: 3/3/2024 1400 to: 3/3/2024 1600)
```

## Sort Tasks: `sort`
Sorts all tasks in the log by their task type, and then by datetime if specified.

**Command format:** `sort`

**Expected output:**
```
GMO has sorted your log (•̀ᴗ•́)و ̑
```
## Update Task Status: `done` `redo`
Mark tasks as done or undone.

**Command formats:** 
- `done <task number>`
- `redo <task number>`

**Example commands:**
- `done 2`
- `redo 1`

**Expected output:**
```
GMO has marked the task as done (•̀ᴗ•́)
completed: <task details>
```
```
GMO has marked the task as incomplete (⊙_☉)
incomplete: <task details>
```

## Delete Tasks: `delete`
Delete tasks that are no longer needed in the log.

**Command format:** `delete <task number>`

**Example command:** `delete 3`

**Expected output:**
```
GMO has deleted the task (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧
deleted: <task details>
you now have <int> tasks in the log
```

## Find Task(s): `find`
Find task(s) that contain the specified keyword. Tasks will be returned with their corresponding task number.

**Command format:** `find <keyword>`

**Example command:** `find book`

**Example output:**
```
GMO has found the following tasks with the keyword: book
1. [ ][T] read book
2. [X][D] return book (by: 2/5/2024 1800)
```
## Help: `commands`
Use this command to get a list of all available commands.

**Command format:** `commands`

## Exit GMO: `bye`
Use this command to exit GMO and save your log data.

**Command format:** `bye`
