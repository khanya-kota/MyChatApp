Chat Application high-Part 3
Student Information
Name : Khanya Kota

Student Number : ST10512866
Module : PROG5121

Project Information
MESSAGE.JAVA FEATURES

1. Message Creation

* Creates a message object containing a recipient number and message text.
* Automatically generates a unique message ID when a new message is created.
* Automatically generates a message hash for message tracking.

2. Message ID Generation

* Generates a random 10-digit message ID.
* Each message receives its own identifier for searching and reporting.

3. Message Hash Generation

* Creates a message hash using:

  * Message count
  * First two digits of the message ID
  * First and last words of the message text
* Used to uniquely identify messages.

4. Message Length Validation

* Checks whether a message contains 250 characters or fewer.
* Displays a confirmation message if valid.
* Displays the number of excess characters if the limit is exceeded.

5. Recipient Number Validation

* Verifies that the recipient number begins with a '+' symbol.
* Ensures that the recipient number does not exceed 13 characters.

6. Send, Store, and Disregard Options

* Provides menu options to:

  * Send a message
  * Disregard a message
  * Store a message

7. JSON File Storage

* Stores message information in a JSON file.
* Saves:

  * Message ID
  * Recipient
  * Message Text
  * Message Hash
* Allows messages to be permanently stored for future use.

8. Message Management Using ArrayLists

* Maintains lists of:

  * Sent messages
  * Stored messages
  * Disregarded messages
  * Message IDs
  * Message hashes
  * Recipients

9. Message Details Display

* Displays complete message information including:

  * Message ID
  * Message Hash
  * Recipient
  * Message Text

10. Load Stored Messages

* Reads previously stored messages from the JSON file.
* Loads them into memory when the application starts.

11. Longest Message Search

* Finds and displays the longest stored message.

12. Search by Message ID

* Allows users to search for a message using its unique message ID.
* Displays the recipient and message content if found.

13. Search by Recipient

* Retrieves all messages sent to a specific recipient number.

14. Delete Message by Hash

* Allows messages to be deleted using their message hash.
* Removes associated information from the message collections.

15. Message Reporting

* Generates a complete report of all sent messages.
* Displays:

  * Message ID
  * Message Hash
  * Recipient
  * Message Content

16. Display Stored Messages

* Shows all messages currently stored in the system.

17. Getter Methods

* Provides access to:

  * Message ID
  * Message Hash
  * Recipient
  * Message Text
* Supports encapsulation by protecting private variables.

