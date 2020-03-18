[10:24 PM, 3/18/2020] Jean-Loic Mugnier: # Parking Toll Library

|Table of contents|
|:----|
| [Overview](#overview)
| [API Features](#api-Features)|
| [Dependencies](#dependencies) |
| [Object Models](#object-models)|
| [Examples](#examples)|
| [Manual Installation](#manual-installation)|

## Overview
ParkingToll API 

This library represents parking lot and gives the user the tool to manage it. It has bookable slots by cars of different types. Each parking slot is associated with one and only one type of car. Also, the ParkingToll API allows invoicing clients after their parking reservation is over. Different pricing policy can be implemented by each ParkingToll class. Finally, the ParkingToll class provides three services:
* Book parking slots.
* Release parking slots.
* Bill a car for booking a parking slot.

## API Features
The three main features of the ParkingToll API are the following: 

### Booking Slot
A car willing to park in the parking lot will need an available slot from the same type. if a slot is available, it will be associated to the car in a reservation object and it will be returned to the caller. If no slot available for this car type. An empty optional object will be returned. The reservation will also keep track of the time of the start of the reservation.

### Release Slot
Given the slot to be freed, the parking lot will find the reservation associated to the slot and will close it, by setting the departure time. It also, calculate the whole duration of the reservation.
### Billing 
Given a reservation, the parking lot will calculate the price of the reservation. thanks to the Pricing Policy implemented by the parking toll, the Price will be returned.

## Dependencies
### Java
This project is using Java 8.
### Maven Plugin
Maven Surefire plugin is added in the pom.xml. mvn test will run the JUnit tests.
Maven Javadoc plugin is also present in the pom.xml and will allow to create the Javadoc.
Maven is used to manage project dependencies and project lifecycle. A Maven script is provided at root level

### Simple Logging Facade for Java 
slf4j is used for logging purposed. slf4j nop is used in version 1.7.28. 
Logging will be done using simple SLF4J. Using Simple Logging Facade has important benefit of not imposing a logging framework to the end-user. Actually, If no slf4j binding is found on the class path, then slf4j-api will default to a no operation discarding all logging request.

### JUnit
Junit is used for Unit testing as well as integration testing. Version used: 4.12

## Object Models

### Parking Toll
Parking Toll is the representation of a parking lot. It is defined by the set of slots where cars can park. The parking toll manage the slot and car reservations as well as bill the customer for the parking period. The latest functionality is inherited by implementing a Pricing Policy. Some pricing policy exist but only one can be used per parking lot. Three calls are publicly available:

### Pricing Policy
Pricing policy is an interface that describes the methods that a parking lot has to implement. A parking lot should only implement one pricing policy. The pricing policy holds its business logic. For a parking lot to have a different pricing policy, it needs to implement the new pricing policy, more specifically, its calculateFare method.

### Car 
The car class can represent different types of car, based on their consumption mean. Gasoline and Electric type cars models are represented in this API. The car type and slot type are both referencing the CarType enum. Car and Slot must be of same type so that the car can park in the slot.  All the different type of cars are represented by the CarType enum.

### Slots
Slots  are defined by their location. But also, a slot is associated to a type of car. For instance, a gasoline car cannot use a slot for electric cars, similarly, an electric car using a 20KW charger cannot park in a slot for electric cars using a 50KW charger, and vice versa.

### Reservation
A reservation is object mapping the car and the parking slot. iw will also hold time information such as the time of reservation was done and the full duration of the reservation. The management of the reservation is done internally. 

### Price
The price of a reservation will be returned to the customer. It defines the amount and the currency.

### Currency
An enum that defines a finite number of currencies. It is aggregated in the Price object.

## Build
For building the ParkingToll API, use the Maven script in the root of the project with the clean and package options.

```bash
./mvnw clean package
```

## JavaDoc Generation
Maven script maven-javadoc-plugin allows to generate the public javadoc by running the command. 

```bash
./mvnw site
```

## Examples
In this section we will see how to use the API via API example. please refer to the example package.

### 1 - Initialization
Use the ParkingToll to generate a parking lot and a Car you want to park. For instance, one can use the ParkingToll Default and a Car of type Gasosilne as is follows:

```java
ParkingToll defaultParking = ParkingToll.builder("Default"); 
Car car = new Car(“LSN1234”,CarType.GASOLINE);
```

### 2 - Book as Slot
Use the car instance to book a slot in the parking lot but only if a slot available. the Parkingtoll has a policy that a car is associated to one type of slot only. If slot is available, it is returned in the optional. otherwise no slot present in the API response.

```java
Optional<Slot> slot = parkingdefault.bookSlot(car);
Integer slotId = slot.get().getLocation();
```

### Release a Slot
Gather the reservation associated to the car and the slot.

```java
Optional<Reservation> reservation = parkingDefault.releaseSlot(slot)
Car car = reservation.get().getCar();
Slot slot =  reservation.get().getSlot();
long duration =  reservation.get().getDuration();
```

### Invoice the client
When invoicing a client for booking a slot, it will return the Price Object. user can get the amount and the its currency information.

```java
Price price = parkingDefault.calculatePrice(reservation);
Double amount = price.getCurrency(); 
Currency currency = currency.getCurrency(); 
```


## Manual Installation
Clone or download the GitHub repository.

Inside the project, you'll have the following directories:

parkingtoll

run the following command:

```bash
./mvnw clean install
```

You'll have a .jar file called parkingtoll-[VERSION].jar, depending on your version of the library.

Import and reference the parkingtoll directory in your project For example in Eclipse: right mouse on your project -> Properties -> Java Build Path -> Projects -> Add External jar ->'parkingtoll-[VERSION].jar'