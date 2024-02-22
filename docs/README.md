# Drake Bot - User Guide
> A simple-to-use task management chatbot, designed to help you keep track of your tasks efficiently.

<img src="Ui.png" width="300">

Whether you're managing daily chores, important deadlines, or upcoming events, Drake Bot is here to assist. This guide will walk you through the features available and how to use them.

## Table of Contents

- [Features Overview](#features-overview)
- [Adding Tasks](#adding-tasks)
  - [Todo](#todo)
  - [Deadline](#deadline)
  - [Event](#event)
- [Viewing Tasks](#viewing-tasks)
- [Marking Tasks as Done](#marking-tasks-as-done)
- [Unmarking Tasks](#unmarking-tasks)
- [Deleting Tasks](#deleting-tasks)
- [Finding Tasks](#finding-tasks)
- [Adding Contacts](#adding-contacts)
- [Listing Contacts](#listing-contacts)
- [Deleting Contacts](#deleting-contacts)
- [Exiting](#exiting)

## Features Overview

1. **Add Tasks:** Easily add tasks of three types - Todo, Deadline, and Event.
2. **View Tasks:** View all tasks in your task list at any time.
3. **Mark Tasks as Done:** Mark tasks as completed once you've finished them.
4. **Unmark Tasks:** Revert tasks to an incomplete status if marked done by mistake.
5. **Delete Tasks:** Remove tasks from your list that are no longer relevant.
6. **Find Tasks:** Find tasks from your list with just a keyword.

## Adding Tasks
Easily add tasks of three types - Todo, Deadline, and Event.

### Todo

For tasks without a specific deadline. Ideal for things you want to do at any time.

- **Command:** `todo [task description]`

  **Example:** `todo Read a book`

### Deadline

For tasks that need to be completed by a specific date.

- **Command:** `deadline [task description] /by [YYYY-MM-DD]`

  **Example:** `deadline finish 2103t ip assignment /by 2024-02-23`

### Event

For tasks that occur from a specified start date, until an end date, such as Computing freshman orientation.

- **Command:** `event [event description] /from [YYYY-MM-DD] /to [YYYY-MM-DD]`

  **Example:** `event Computing freshman orientation /from 2024-07-15 /to 2024-07-18`

## Viewing Tasks

To see a list of all your tasks, use the command:

- **Command:** `list`

## Marking Tasks as Done

Once you complete a task, mark it as done with the following command:

- **Command:** `mark [task number]`

  **Example:** `mark 2`

## Unmarking Tasks

If a task was marked as done by mistake, you can revert it:

- **Command:** `unmark [task number]`

  **Example:** `unmark 2`

## Deleting Tasks

To remove a task from your list:

- **Command:** `delete [task number]`

  **Example:** `delete 3`

## Finding Tasks

Find tasks from your list containing a specificed keyword:

- **Command:** `find [keyword]`

  **Example:** `find cs2103t`

## Adding Contacts

Add a contact to your contact list:

- **Command:** `add-contact [contact-name] [contact-details]`

  **Example:** `add-contact dua-lipa pop singer`

## Listing Contacts

List all contacts in your contact list:

- **Command:** `list-contacts`

## Deleting Contacts

Delete a contact from your contact list:

- **Command:** `delete-contact [contact-index]`

  **Example:** `delete-contact 1`

## Exiting

Exit and shut down the Drake bot program:

- **Command:** `bye`
---
