# Duke User Guide

![Screenshot of Ui.](Ui.png)

Let's all manage our brainrot tasks with Yapper!

# Features

## Viewing help

View the list of available commands and their formats

Format: `help`

## Adding tasks

Add a task with a description to your list.

Format: `todo <description>`

Example: `todo procrastinate homework`

## Adding deadlines

Add a task with a deadline date to your list.

Format: `deadline <description> /by <yyyy-mm-dd>`

Example: `deadline learn fortnite dance /by 2024-02-22`

## Adding events

Add an event with a start and end date to your list.

Format: `event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>`

Example: `event party rock /from 2024-02-22 /to 2024-02-23`

## Listing tasks

Lists out all tasks in your task list.

Format: `list`

## Deleting tasks

Delete a task from your task list specified by index.
* Index should be positive integers starting from 1. (E.g. `1`,`2`,`3` ...)

Format: `delete <index>`

Example: `delete 2`

## Marking tasks

Mark a task as done from your task list specified by index.
* Index should be positive integers starting from 1. (E.g. `1`,`2`,`3` ...)

Format: `mark <index>`

Example: `mark 2`

## Un-marking tasks

Mark a task as not done from your task list specified by index.
* Index should be positive integers starting from 1. (E.g. `1`,`2`,`3` ...)

Format: `unmark <index>`

Example: `unmark 2`

## Finding tasks

Shows a list of tasks that has a matches your search.
* Returns tasks which has a description that contains your searched phrase
* Returns tasks that contains exact phrase and not individual words
  - (E.g. searching _**cry and sob**_ will match with tasks that has _**cry and sob and weep**_ but not match with _**sob and cry**_)

Format: `find <description>`

Example: `unmark cry`

## Saving tasks

Saves your changes to your task list to your device.

Format: `bye`
