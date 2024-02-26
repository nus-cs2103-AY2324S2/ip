# Dukio User Guide

![Screenshot of the Product](Ui.png)

Welcome to Dukio!

Dukio is the unknown brother of Mario, Nintendo's favourite character. It's

- Fast
- Amazing!
- Swag 😎

## Adding deadlines

You can create a deadline task by specifying the `/by` date and what the task entails.

Example: `deadline <text> /by <yyyy-mm-dd>`

`deadline finish CS2103 /by 2022-02-02`

Expected output:

```
I've added
[D][] finish CS2103 (by: 02 Feb 2022)
Mamma-mia!
```

## Adding TODOs

You can create a TODO task by specifying what the task entails.

Example: `todo <text>`

`todo finish CS2103`

Expected output:

```
I've added
[T][] finish CS2103
Mamma-mia!
```

## Adding Events

You can create a Event task by specifying what the task entails and
the date range it spans (`from` & `to`).

Example: `event <text> /from <yyyy-mm-dd> /to <yyyy-mm-dd>`

`event Hackathon /from 2022-02-02 /to 2-22-02-03`

Expected output:

```
I've added
[E][] Hackathon (from: 02 Feb 2022, to: 03 Feb 2022)
Mamma-mia!
```

## Listing Tasks

List tasks by typing `list`!

Expected output:

```
1. [T][] Task 1
2. [T][] Task 2
3. [T][] Task 3
```

## Mark Tasks

Mark task as done by typing:
`mark <index of the task in the list>`

Example: `mark 1`

Expected output:

```
I've marked
1. [T][X] Task 1
as done!
```

## Delete Tasks

Delete task as done by typing:
`delete <index of the task in the list>`

Example: `delete 1`

Expected output:

```
I've deleted
1. [T][X] Task 1
```

## View Schedule

View the schedule for a given date where it shows
events that fall within a specified date or a deadline that ends on the specified date.

Expected command: `view_schedule <yyyy-mm-dd>`

Example: `view_schedule 2022-02-02`
Expected output:

```
1. [D][] CS2101 CA1 Pitch Practice (by: 02 Feb 2022)
```

## Find tasks

Find tasks through titles with keywords!

Expected command: `find <keywords>`

Example: `find CS2101`

Expected output:

```
1. [D][] CS2101 CA1 Pitch Practice (by: 02 Feb 2022)
1. [D][] CS2101 CA2 Pitch Practice (by: 03 Feb 2022)
```
