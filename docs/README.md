# Steven User Guide

![Steven at work!](/Ui.png)

Steven is a bot that helps you keep track of things like your deadlines, events, deadlines and so forth! With a simple command, you too can easily keep track of the little things in your schedule!

## Adding deadlines: `deadline`

Adds a "deadline" item to the task list. A deadline is defined as a task with an end date

Format: `deadline (item) /by (date1)`

- `date1` must strictly be a date in the fromat `yyyy-mm-dd`

Example: `deadline Attend Tournament /by 2024-04-14`

## Adding todos: `todo`

Adds a "todo" item to the task list. A todo is defined as a task with no start or end date

Format: `todo (item)`

Example: `todo Draw Cards`

## Adding events: `event`

Adds a "event" item to the task list. A event is defined as a task with a start and end date

Format: `event (item) /from (date1) /to (date2)`

- `date1` and `date2` must strictly be a date in the fromat `yyyy-mm-dd`

Example: `event Give advice /from 2024-01-01 /to 2024-01-01`

## Listing what the current task list is: `list`

Displays the current list, even if there is nothing in the list, in which case nothing will be displayed as the list.

Format: `list`

## Marking an item: `mark`

Marks an item in the list. While the definition of a "mark" is relatively arbitrary, it can be thought of as just noting something as done or not done.

Format: `mark (x)`

- `x` is the index of the item as noted by its position in the list (to view this list, one can use `list`)

Example: `mark 1` - this marks the first item in the list.

## Unmarking an item: `unmark`

Unarks an item in the list. While the definition of a "mark" is relatively arbitrary, it can be thought of as just noting something as done or not done.

Format: `unmark (x)`

- `x` is the index of the item as noted by its position in the list (to view this list, one can use `list`)

Example: `unmark 1` - this unmarks the first item in the list.

## Deleting an item: `delete`

Deletes an item in the list. Note that deleting an item may cause the indices of items in the list to be changed.

Format: `delete (x)`

- `x` is the index of the item as noted by its position in the list (to view this list, one can use `list`)

Example: `delete 1`

## Finding an item: `find`

Finds an item with the specified name in the list, then shows a list of tasks which match the specified item name. Note that so long as an item contains the words listed, it will be searched out with this command.

Format: `find (name)`

- `name` is the name of the item that you want to search.

Example: `search the`

- this will return any task which contains "the" in part of its name. Consequently, with the above command, the following items will be shown. Note the presence of the set of letters that form "the" in the following examples.
  - **the**
  - **the**re
  - **the** Statue of Liberty
  - Ba**the**
- However, the search does not return tasks which have the specified word, but is broken up in some way. Again using the same example command, the following will **not** be searched out with this command.
  - **Th e**
  - Fif**th E**vent
  - **Th**ank **E**mily

## Sorting the list: `sort`

Sorts the task list. The order can be specified by the user. Note that this affects the ordering of the list, so be sure to use `list` if you need to check the new indices of the items in the list.

Format: `sort (asc/des)`

- `asc` is used to sort the list in lexicographical order, ascending, so an event `AA` will be before `AC`, and `AC` will be before `ZZ`.
- `des` is used to sort the list in lexicographical order, descending, so an event `AA` will be after `AC`, and `AC` will be after `ZZ`.
- note that this spelling of "asc" or "dec" must strictly be followed.

Example: `sort asc`

## Display a help list: `help`

Shows the user a list of commands that can be used.

Format: `help`

## Exiting the program: `bye`

Exits the program

Format: `bye`

- note that there is a small delay between the program closing and typing in of the command
- commands can be typed in this time, but it may cause corruption of data. Type at your own risk.

## Other features

### Data saving

Data is saved in the hard disk, so manual backup is not necessary.

### Editing the data file

Data is saved in the file data/Steven.txt. Note that editing this file might result in corrupted data and/or unintended behavior. Edit at your own risk!
