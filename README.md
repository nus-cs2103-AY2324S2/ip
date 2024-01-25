Initial planning for the implementation of the Duke project Chatbot

date: 25 Jan 2024

Overall Description:

The project aims to build a Personal Assistant Chatbot that helps a person to keep track of various things

Level 1

Requirements:
- rename: Liv
- Greet the user once launched
- Exits after the conversation is finished

Design:
- Try to adhere to singleton principle by keeping only one Liv active
- abstract greet() and exit() behaviour
- store lines locally in functions, simply as strings. 
	- rationale: given the purpose of the bot being to track various things, it is likely not to need many lines


