# Charlie User Guide


## Features 

### Feature-Add Todo Tasks

It is possible to add todo tasks, these 
don't require a deadline or any sort of 
timing information

You will be prompted to assign a priority 
to this task depending on how important it is

### Feature-Add Deadline Tasks

It is possible to add deadline tasks, these
don't require a time by which the deadline is
due

You will be prompted to assign a priority
to this task depending on how important it is

### Feature-Add Event Tasks

It is possible to add event tasks, these
require times by which the event starts and ends

You will be prompted to assign a priority
to this task depending on how important it is

### Feature-Find Keyword

It is possible to find tasks related to keywords,
this can help with prioritisation of tasks such
as "study" or "party"

### Feature-Delete Tasks

It is possible to delete tasks if an accident
occurred, such as you inputted a task you
don't actually have to complete

### Feature-List Tasks

It is possible to list tasks and view whether
or not the task has been completed and its
priority status

### Feature-Mark Tasks

It is possible to mark tasks once you finish
a task but still want to be able to look at it

### Feature-Unmark Tasks

It is possible to unmark tasks if you thought
you had finished but hadn't actually finished

## Usage

### `todo` - creates a todo task

creates a todo task which requires no 
additional information, except for task
description, you will be subsequently 
prompted for prioritisation of the task

Example of usage: 

`todo borrow book`

(assume prioritisation is 1)

Expected outcome:

```
Got it. I've added this task:
    [T][ ][1] borrow book
```

### `deadline` - creates a deadline task

creates a deadline task which requires 
the task description and the time by which
the deadline is due, you will be subsequently 
prompted for prioritisation of the task

Example of usage:

`deadline party /by Sunday`

(assume prioritisation is 1)

Expected outcome:

```
Got it. I've added this task:
    [D][ ][1] party (by: Sunday)
```

### `event` - creates an event task

creates an event task which requires a time
by which the event starts and a time by which
the event ends, you will be subsequently
prompted for prioritisation of the task

Example of usage:

`event hangout with Sara /from Mon 5pm /to Mon 10pm`

(assume prioritisation is 1)

Expected outcome:

```
Got it. I've added this task:
    [E][ ][1] hangout with Sara (from: Mon 5pm to: Mon 10pm)"
```

### `find` - finds tasks related to a certain keyword

finds and outputs tasks which pertain to the 
keyword inputted

Example of usage:

`find hangout`

Expected outcome:

```
Here are the matching tasks in your list
    [E][ ][1] hangout with Sara (from: Mon 5pm to: Mon 10pm)"
```

### `list` - lists all the tasks

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list
    [T][ ][1] borrow book
    [D][ ][1] party (by: Sunday)
    [E][ ][1] hangout with Sara (from: Mon 5pm to: Mon 10pm)"
```

### `mark` - marks tasks 

use the mark command to mark tasks as completed, the mark command should be followed by
an integer specifying the task which has been completed,
using the list command in conjunction to find corresponding
tasks is a good idea

Example of usage:

`mark 1`

Expected outcome:

```
Marked Task: 'borrow book'
```

### `unmark` - unmarks tasks

use the unmark command to mark tasks as uncompleted, 
the unmark command should be followed by an integer 
specifying the task which has not been completed,
using the list command in conjunction to find 
corresponding tasks is a good idea

Example of usage:

`unmark 1`

Expected outcome:

```
Unmarked Task: 'borrow book'
```

### `delete` - deletes tasks

use the delete command to delete tasks, be careful
because once deleted, it cannot be brought back

Example of usage:

`delete 1`

Expected outcome:

```
Deleted task: 'borrow book'
```
