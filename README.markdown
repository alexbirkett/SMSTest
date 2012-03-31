# About
Android proof of concept app to demonstrate intercepting SMS messages.

The app intercepts incoming SMS (text) messages containing the words "activity" or "notification". Intercepted messages are not displayed in the messaging app.

If the incoming SMS message contains the word "activity", an an Android activity is immediately invoked to display the text message.

If the incoming SMS message contains the work "notification", a notification is displayed showing the message's originating address and the message body. Tapping the notification results in an Android activity being invoked to display the message contents.

# Supported versions
The app is targeted at Android 2.1x (API level 7) and above

#Testing
The app has been tested on a Samsung Galaxy S2 (Running android 2.3.4) and the Android 4.0.3 emulator.

#Feedback
Feedback welcome via github
