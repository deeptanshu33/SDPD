[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/R_7cjhEg)

# A2-PersistUI

## Contributors
- **DEEPTANSHU PRASAD** (2021A4PS2886G) - f20212886@goa.bits-pilani.ac.in
- **TEJAS AJIT SOVANI** (2021A7PS2720G) - f20212720@goa.bits-pilani.ac.in

## Overview
A2-PersistUI is an Android app that simulates a 6-faced dice roll. Users tap a button to roll the dice, which generates a number between 1 and 6. The app has the following features:

- **Rewards**:
  - Rolling a 6 awards the user 5 coins.
  - Rolling two consecutive 6s awards the user 10 coins.

- **Penalties**:
  - Rolling the same non-6 number twice consecutively deducts 5 coins.

The UI is designed using Linear Layouts and Text Views, with separate layouts for landscape and portrait orientations to ensure UI state persistence upon rotation.

### Features

- **Toast Notifications**: Implemented in the `WalletActivity` class. Referenced Android Documentation and [this guide](https://www.dre.vanderbilt.edu/~schmidt/android/android-4.0/out/target/common/docs/doc-comment-check/guide/topics/ui/notifiers/toasts.html) for implementation details.

- **Statistics Tracking**:
  - Includes Previously Rolled Value, Total Rolls, Single Sixes, Double Sixes, and Double Others.
  - UI elements are assigned IDs for access and updates in the `WalletActivity` class.
  - The previously rolled value is tracked using two variables: one for the current roll and one for the previous roll. This helps in updating other statistics, such as detecting double sixes.

### Testing

- **Test-Driven Development**: Adopted a test-driven approach by analyzing test cases and expected responses before writing the code.
  
- **Unit Testing**:
  - Used JUnit for unit testing.
  - The `Die6` class was overridden to provide custom inputs for verification of final states.

- **Instrumented Testing**:
  - Utilized Espresso for interacting with the app.
  - Employed Hamcrest for writing readable test cases.
  - Rolled the dice multiple times to calculate and verify if the balance fell within the expected range.

- **Monkey Testing**:
  - Conducted stress testing using Monkey Test for 500, 800, 1000, and 1500 events.
  - The system crashed during the 1500-event test with the following message: **"System appears to have crashed at event 377 of 1500 using seed 1726075928515"**.

### Accessibility
- **Using Talkback**: Talkback mode required significant effort to go to the home page and find the application. The die was not difficult to find, but it was hard to realise that I am pressing on the die. Further, due to the required double click, the die sometimes didn't roll at all leading to confusion if I am pressing on it at all. However after a 1-2 minutes, I was able to press it for a few times. The talkback helped understand what is going on.

- **Image Button**:
  - Use a content description attribute to help the screen reader recognise the button.
  - Make the image large enough so that the user is able to find it.

- **Suggestions & Solutions**:
  - Scaling the text inside the die button. 
  - "Multiple items have the same description"
  - Updated the button to use `wrap_content` with `minWidth` and `minHeight`, fixing accessibility issues related to text scaling.


## Time and Effort
- **Total Time Taken**: Approximately 14 hours
- **Extent of Pair Programming**: 3 (on a scale of 1 to 5)
- **Difficulty Level**: 7 (on a scale of 1 to 10)
