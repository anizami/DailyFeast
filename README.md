User Documentation
Daily Feast is an android application that allows users to find free food events at Macalester College by compiling information about free food events from the Daily Piper, Macalester’s daily newsletter, and displaying them in a user interface. It also allows users to advertise their own events by posting it to the Daily Feast server, making the information available to all other users.

Launching the app:
Launch the app by tapping the Daily Feast icon on your Android’s app launch screen. You will be sent to the Daily Feast home screen after a brief loading period.

Viewing events:
From the Daily Feast home screen, tap the button reading “See today’s menu”. Your app will connect to the Daily Feast server, and will download the event information to your phone. If your wifi is disabled, or you have a bad internet connection, you will be notified. The “Today’s Events” screen will display the event titles of all events of the day that feature free food. By tapping an event title, you will be taken to a new screen for that event which will display the event’s title, time, location and a brief description of the food. Since the Daily Piper does not publish events on the weekends, you will only be able to view events created by fellow users on weekend days (Saturday and Sunday).

Creating Events:
To add your own event to the Daily Feast database, press the “Create New Event” button on the home screen. The button will take you to a new screen where you will be prompted to enter the necessary event information. Enter an event title, time, location and description and then press the “Create Event” button to make your event. You will then be taken to the “Today’s Events” screen that displays all the events of the day. Your newly created event will be at the bottom of the list. Since user created events are added to a database on the Daily Feast server, all other users will be able to see your new event when they launch their Daily Feast apps!

Developer Documentation

# Daily Feast
Provides free food event information to users. Currently only supports Macalester College. 

## Architecture
The DailyFeast and PiperServer repositories contain the android client and the Java-Sparks-based server engine respectively. 

The engine scrapes the Daily Piper’s public website and compiles a list of PiperEvents that contain strings describing public events on Macalester College’s campus that feature free food for students. These PiperEvents are stored in a database deployed on servers belonging to Heroku. The server sends the PiperEvent information as JSON objects to the client upon request. The client can also send user-created events to the server as JSON objects. 
![DailyFeast Flow Chart](fabrgu.github.com/repository/img/DailyFeast Flow Chart.png)

## Client development

The application is targeted for Android version 19, but the lowest version that it will work with is 11. This Android application was built using IntelliJ IDEA 13, but other android application environments can be used. The android client can be found at:  https://github.com/fabrgu/DailyFeast. The android client requires the commons-io-2.4.jar (found at: http://commons.apache.org/proper/commons-io/download_io.cgi), gson-2.2.4.jar (found at: https://code.google.com/p/google-gson/downloads/list), java-json.jar (found at: http://www.java2s.com/Code/Jar/j/Downloadjavajsonjar.htm), and jsoup-1.7.3.jar (found at: http://jsoup.org/download).  To facilitate testing, we used the calendar library to manually switch between weekdays and weekends to see if the weekend alertdialog, which notifies users that the Daily Piper does not publish on weekends and the database would consequently be initially empty, properly worked. In addition, the client-side worked in conjunction with the server to facilitate testing by clearing the database to test for situations where no events occurred. 

-- Note: ## Server Development Section of Developer Documentation can be found at: https://github.com/fabrgu/PiperServer
