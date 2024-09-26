# Klee User Guide

![Screenshot of Klee running a few commands](/Ui.png)

Klee is a character from the game Genshin Impact.
The images displayed in the application are images from the game itself, 
or taken from videos on the official Genshin Impact Youtube page or fan arts.

For a more detailed source for each image, refer to the final section below

## Adding tasks

### `todo` : The command to create a task to be done

Run the command followed by a description of the task

Example: `todo break out of solitary confinement`

The input above should result in a similar output to below:
![Screenshot of user running a todo command](/ToDoCommand.png)


### `deadline` : The command to create a task with a deadline

Run the command followed by a description of the task

After `/by` give a date time input in the following format

Date can be represented in one of the 2 formats

`dd/mm/yyyy` such as `18/2/2024`

`yyyy-mm-dd` such as `2024-2-18`

Optionally after leaving a space, enter a 24 hr time as follows

`18/2/2024 1800`

Example: `deadline get back before Jean finds us /by (datetime)`

The input above should result in a similar output to below:
![Screenshot of user running a deadline command](/DeadlineCommand.png)

### `event` : The command to create a task with a deadline

Run the command followed by a description of the task

After `/from` give a date time input representing the start of the event

After `/to` give a date time input representing the end of the event

Example: `event conclude this project /from 16/2/2024 /to 2024-2-22`

The input above should result in a similar output to below:
![Screenshot of user running a event command](/EventCommand.png)

## Listing tasks

### `list` : The command to list all tasks currently recorded

Example: `list`

The input above should result in a similar output where all your current tasks are shown:
![Screenshot of user running a list command](/ListCommand.png)

## Marking/Unmarking tasks

### `mark` : The command to mark a recorded task as complete

Run the command with the position of the task in the current list

Example: `mark 1`

The input above should result in a similar output:
![Screenshot of user running a mark command](/MarkCommand.png)

### `unmark` : The command to unmark a completed task as incomplete

Run the command with the position of the task in the current list

Example: `unmark 1`

The input above should result in a similar output:
![Screenshot of user running an unmark command](/UnMarkCommand.png)

## Deleting tasks

### `delete` : The command to delete a recorded task

Run the command with the position of the task in the current list

Example: `delete 1`

The input above should result in a similar output:
![Screenshot of user running a delete command](/DeleteCommand.png)

## Find certain tasks

### `find` : The command to search for tasks by their description

Run the command followed by the search term

Example: `find fish`

The input above should result in a similar output where all relevant tasks are shown:
![Screenshot of user running a find command](/FindCommand.png)

## End the program

### `bye` : Say goodbye to Klee and end the program

Example: `bye`

The program will end

## Image Sources
### On program start

- Klee image

https://twitter.com/KleeHourly/status/1718949061276078490/photo/1

- Paimon image

https://www.hoyolab.com/article/7585712

### On user input

- Paimon reading a book image:

From Genshin Impact story quest (Dracaena Somnolenta Chapter)

### On running mark/unmark/list/find

- Klee drawing image:

From Youtube video on Genshin Impact official channel

"Character Tales - "Fleeing Sunlight in the Night" | Genshin Impact"

https://www.youtube.com/watch?v=TFOeQuShLME

### On running todo/deadline/event

- Klee showing a drawing image:
  
From Youtube video on Genshin Impact official channel

"Character Tales - "Fleeing Sunlight in the Night" | Genshin Impact"

https://www.youtube.com/watch?v=TFOeQuShLME

### On unrecognisable command

- Klee confused image:

From the game itself using the photo function and making the thinking pose with Klee