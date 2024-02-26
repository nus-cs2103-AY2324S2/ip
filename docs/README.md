# AWEX User Guide

AWEX (A Wonderful Experience) provides you a simple, yet interactive experience while making your list of things to do, so you'll never feel alone on the path to peak productivity!

![Screenshot of AWEX's first messages](Ui.png)

## Features

### Add Tasks

Input Format:

`todo <task>`

`deadline <task> /by <YYYY-MM-DD hh:mm>`

`event <task> /from <YYYY-MM-DD hh:mm> /to <YYYY-MM-DD hh:mm>`

### Mark/Unmark Tasks

Input Format:

`mark <task number>`

`unmark <task number>`

### Find Tasks

Input Format:

`find <item>`

### List Tasks

Input Format: `list`

### Delete Tasks

Input Format:

`delete <task number>`

### User Manual

Input Format: `help`

Response:

```
Input type must be one of:
  1. list
  2. mark <task number>
  3. unmark <task number>
  4. todo <task>
  5. deadline <task> /by <YYYY-MM-DD hh:mm>
  6. event <task> /from <YYYY-MM-DD hh:mm> /to <YYYY-MM-DD hh:mm>
  7. delete <task number>
  8. find <item>\n"
Type 'bye' to exit.
```
