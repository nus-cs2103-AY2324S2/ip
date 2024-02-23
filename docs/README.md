# Tiny User Guide

Meet Tiny, your all-in-one desktop app designed to streamline your daily life. Whether you're juggling tasks, keeping track of contacts, brushing up on trivia, jotting down notes, managing clients, organizing merchandise, handling loans, tracking expenses, or exploring new places, Tiny has you covered. With the ability to save, delete, find, and load your saved items seamlessly, Tiny simplifies your workflow, making it easier than ever to stay organized and productive. Say goodbye to scattered to-do lists and scattered information—Tiny puts everything you need at your fingertips, helping you tackle each day with confidence and efficiency.


## Quick Start

Prerequisites: Ensure you have Java 11 or above installed in your Computer.

1. Download the entire folder from [here](https://github.com/Yskie/ip/releases/).
2. Open double click to open the JAR file.
3. Use the program!



## Features

### Adding a client: `client`
Adds a client to the program.

Format: `client <name> /num <contact number>`

Example: 
`client Duke /num 91234567`

<br>

### Adding a contact: `contact`
Adds a contact to the program.

Format: `contact <name> /num <contact number>`

Example: 
`contact Duke /num 91234567`

<br>

### Adding a deadline: `deadline`
Adds a deadline to the program.

Format: `deadline <description> /by <yyyy-mm-dd> <time>`

Example: 
`deadline CS2103T Quiz 1 /by 2024-02-02 2359`

<br>

### Adding an expense: `expense`
Adds an expense to the program.

Format: `expense <description> /for <amount> /on <yyyy-MM-dd>`

Example:
`expense Taxi /for 20 /on 2022-01-29`

<br>

### Adding an event: `event`
Adds an event to the program.

Format: `event <description> /from <start date> /to <end date>`

Example: 
`event NUS Career Fair /from 2024-02-20 /to 2024-02-22`

<br>

### Adding a loan given: `loan given`
Adds a loan given to the program.

Format: `loan given /to <name> /for <amount> /due <yyyy-MM-dd>`

Example: 
`loan given /to Duke /for 100 /due 2023-10-10`

<br>

### Adding a loan taken: `loan taken`
Adds a loan taken to the program.

Format: `loan taken /from <name> /for <amount> /due <yyyy-MM-dd>`

Example: 
`loan taken /from Duke /for 100 /due 2023-10-10`

<br>

### Adding a merchandise: `merchandise`
Adds a merchandise to the program.

Format: `merchandise <name> /quantity <quantity> /price <price>`

Example: 
`merchandise black hat /quantity 100 /price 4.99`

<br>

### Adding a note: `note`
Adds a note to the program.

Format: `note <title> /body <content>`

Example: 
`note CS2103T /body Do assignment! Do IP!`

<br>

### Adding a place: `place`
Adds a place to the program.

Format: `place <name> /at <address>`

Example: 
`place The Terrance /at NUS COM3`

<br>

### Adding a todo: `todo`
Adds a todo to the program.

Format: `todo <description>`

Example: 
`todo do CS2103T IP User Guide`

<br>

### Adding a trivia: `trivia`
Adds a trivia to the program.

Format: `trivia <content>`

Example: 
`trivia Did you know "www" stands for World Wide Web?`

<br>

### Finding a todo, deadline or event: `find`
Finds tasks that contain any of the given keyword in their description field.

Format: `find <keyword>`

Example: 
`find study`

<br>

### Marking a todo, deadline or event as done: `mark`
Marks the specificed task from the program.

Format: `mark <number>`

Example: 
`mark 1`

<br>

### Marking a todo, deadline or event as not done: `unmark`
Unmarks the specificed task from the program.

Format: `unmark <number>`

Example: 
`unmark 1`

<br>

### Listing todos, deadlines and events: `list task`
Shows a list of all tasks (todo, deadline, event) in the program.

Format: `list task`

<br>

### Listing clients: `list client`
Shows a list of all clients in the program.

Format: `list client`

<br>

### Listing contacts: `list contact`
Shows a list of all contacts in the program.

Format: `list contact`

<br>

### Listing expenses: `list expense`
Shows a list of all expenses in the program.

Format: `list expense`

<br>

### Listing loans given: `list loan given`
Shows a list of all loans given in the program.

Format: `list loan given`

<br>

### Listing loans taken: `list loan taken`
Shows a list of all loans taken in the program.

Format: `list loan taken`

<br>

### Listing expenses: `list merchandise`
Shows a list of all expenses in the program.

Format: `list merchandise`

<br>

### Listing notes: `list note`
Shows a list of all notes in the program.

Format: `list note`

<br>

### Listing places: `list place`
Shows a list of all places in the program.

Format: `list place`

<br>

### Listing trivias: `list trivia`
Shows a list of all trivias in the program.

Format: `list trivia`

<br>


### Deleting a todo, deadline or event: `delete task`
Deletes the specified task from the program.
You can use the `list task` command to find the specific task to delete.

Format: `delete task <number>`

Example: 
`delete task 1`

<br>

### Deleting a client: `delete client`
Deletes the specified client from the program.
You can use the `list client` command to find the specific client to delete.

Format: `delete client <number>`
Example: 
`delete client 1`

<br>

### Deleting a contact: `delete contact`
Deletes the specified contact from the program.
You can use the `list contact` command to find the specific contact to delete.

Format: `delete contact <number>`

Example: 
`delete contact 1`

<br>

### Deleting an expense: `delete expense`
Deletes the specified expense from the program.
You can use the `list expense` command to find the specific expense to delete.

Format: `delete expense <number>`

Example: 
`delete expnese 1`

<br>

### Deleting a loan given: `delete loan given`
Deletes the specified loan given from the program.
You can use the `list loan given` command to find the specific loan given to delete.

Format: `delete loan given <number>`

Example: 
`delete loan given 1`

<br>

### Deleting a loan taken: `delete loan taken`
Deletes the specified loan taken from the program.
You can use the `list loan taken` command to find the specific loan taken to delete.

Format: `delete loan taken <number>`

Example: 
`delete loan taken 1`

<br>

### Deleting a expense: `delete merchandise`
Deletes the specified merchandise from the program.
You can use the `list merchandise` command to find the specific merchandise to delete.

Format: `delete merchandise <number>`

Example: 
`delete merchandise 1`

<br>

### Deleting a note: `delete note`
Deletes the specified note from the program.
You can use the `list note` command to find the specific note to delete.

Format: `delete note <number>`

Example: 
`delete note 1`

<br>

### Deleting a place: `delete place`
Deletes the specified place from the program.
You can use the `list place` command to find the specific place to delete.

Format: `delete place <number>`

Example: 
`delete place 1`

<br>

### Deleting a trivia: `delete trivia`
Deletes the specified trivia from the program.
You can use the `list trivia` command to find the specific trivia to delete.

Format: `delete trivia <number>`

Example: 
`delete trivia 1`

<br>

### Exiting the program: `bye`
Exits the program.

Format: `bye`

<br>
<br>


<br>

## Command Summary

| Action | Type | Format |
| ------------- | ------------- | ------------- |
| Add | client | `client <name> /num <contact number>` |
| Add | contact | `contact <name> /num <contact number>` |
| Add | deadline | `deadline <description> /by <yyyy-mm-dd> <time>` |
| Add | expense | `expense <description> /for <amount> /on <yyyy-MM-dd>` |
| Add | event | `event <description> /from <start date> /to <end date>`|
| Add | loan given | `loan given /to <name> /for <amount> /due <yyyy-MM-dd>` |
| Add | loan taken | `loan taken /from <name> /for <amount> /due <yyyy-MM-dd>` |
| Add | merchandise | `merchandise <name> /quantity <quantity> /price <price>` |
| Add | note | `note <title> /body <content>` |
| Add | place | `place <name> /at <address>` |
| Add | todo | `todo <description>` |
| Add | trivia | `trivia <content>` |
| Find | find | `find <keyword>` |
| Mark | mark | `mark <number>` |
| Unmark | unmark | `mark <number>` |
| List | task | `list task` |
| List | client | `list client` |
| List | contact | `list contact` |
| List | expense | `list expense` |
| List | loan given | `list loan given` |
| List | loan taken | `list loan taken` |
| List | merchandise | `list merchandise` |
| List | note | `list note` |
| List | place | `list place` |
| List | trivia | `list trivia` |
| Delete | task | `delete task <number>` |
| Delete | client | `delete client <number>` |
| Delete | contact | `delete contact <number>` |
| Delete | expense | `delete expense <number>` |
| Delete | loan given | `delete loan given <number>` |
| Delete | loan taken | `delete loan taken <number>` |
| Delete | merchandise | `delete merchandise <number>` |
| Delete | note | `delete note <number>` |
| Delete | place | `delete place <number>` |
| Delete | trivia | `delete trivia <number>` |
| Exit | - | `bye` |