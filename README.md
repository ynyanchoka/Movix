
# Movix
By Ymelda Monari

# Project Description
This and android app that collects and tracks movies.

## Getting Started

#### To clone the repository:
- Clone this repository using:
  git clone 'https://github.com/ynyanchoka/Movix'
- Navigate to the local directory:
- Open the directory with your preferred text editor.

### Prerequisites

What things you need to install the software and how to install them
- An access to the internet
- Android Studio
- Create a virtual device or use a physical device


## Running the tests

There are two tests to be run:
+ Instrumentation test
+ Local Test

### Instrumentation test

Tests that require an Android device or emulator to simulate a user actions.
### Local Test
Tests that run locally on the JVM, without an emulator or device.Robolectric  is used for this.It test's the behaviors of a particular component in isolation of other components.It is important to test a small portion of the App.

-   @Test
    public void anotherActivityStarted(){
    activity.findViewById(R.id.signUpButton).performClick();
    Intent expectedIntent = new Intent(activity, SignupActivity.class);
    ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
    Intent actualIntent = shadowActivity.getNextStartedActivity();
    assertTrue(actualIntent.filterEquals(expectedIntent));
    }


## Built With

* [Java](https://www.java.com) - The language used
* [Android Studio](https://developer.android.com/studio) - IDE
* [Gradle](https://gradle.org) - To automate the creation of Movix app

## Authors

* **Ymelda Monari** - [ynyanchoka](https://github.com/ynyanchoka)

## Contact information
+ Ymelda Monari : `monaryymelda@gmail.com`


## Copyright and license information

Copyright (c) 2022 [click here to view license](LICENSE)