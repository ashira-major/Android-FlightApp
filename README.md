# Android Flight Navigator

An Android app that connects to external software named FlightGear simulator.
The app allows the user to fly and navigate the plane in the external simulator.

## Collaborators

The application was developed by two computer science students at Bar Ilan University, Israel : Shiraz Ayashe and Ashira Major


## Description

Written in Kotlin and Java, using MVVM architecture, this Android application allows you to become a real time pilot.
Using this app you can control the plane in the FlightGear simulator.
Navigation of the plane is done by a range of controllers:
* Sliders for the throttle and rudder values of the flight.
* A JoyStick that controls the planes movements at any given moment.


## Getting Started

### Dependencies

* make sure you have the [FlightGear simulator](https://www.flightgear.org/) on your computer
* Android device or emulator


### Executing program

* In the settings of the FlightGear simulator, the correct command for this app is :
```
--telnet=socket,in,10,127.0.0.1,6400,tcp
```
* Connecting :
  * Enter in IP the IPv4 of the computer that is connected to the simulator
     > **important :** if you are running the FlightGear simulator and Android emulator on that same computer - **do not** use your localhost IP (127.0.0.1)
  * Enter in port number 6400
  * Press 'connect' button
  * Make sure you enter in the correct IP and port number
* You are now connected to the flight simulator !
* You can now navigate your flight with the Sliders and JoyStick ! AutoPilot (in top bar of the simulator) can help you start out.

### Features
* JoyStick - The JoyStick class is independent of the applications code and can be used for other android applications easily.
  To use the Joystick in a different project all you need is to create an instance of the class and make sure to implement the OnChangeJ method !
* Design - our app has a 'mode' switch. You can change the app design to night or day mode at any moment during the flight ! The change is not only to the background of the app but also in the joystick.

## Documentation

UML diagram of the projects structure showing the MVVM architecture used in the project :
![Image of UML](https://github.com/ashira-major/Android-FlightApp/blob/c7dc3bd50d49d11d93ccad93974e2e576b7811f0/Screen%20Shot%202021-06-27%20at%2020.08.54.png)
The separation of logic and design is visible in the UML.

[Demo of the app running ](https://youtu.be/AGYCmEJU_LM)
[Presentation of the project](https://github.com/ashira-major/Android-FlightApp/blob/5b4153448b25de44ce4c154710ae334934b04abd/FlightApp.pdf)

**Enjoy !**
