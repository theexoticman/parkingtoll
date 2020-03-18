# Parking Toll Library

|Table of contents|
|:----|
| [Overview](#overview)
| [Prerequisites](#rrerequisites)|
| [Getting started](#getting-started) |
| [API Features](#api-features)|
| [Examples](#examples)|
| [Manual Installation](#manual-installation)|

## Overview
ParkingToll API 

This library represent a parking lot with parking slots that can be book by cars of different types. Each parking slot is associated with one and only one type of car. Also, the Parking Toll allows invoicing clients afther their parking reservation over. Different  pricing policy can be implemented by each ParkingToll. Finally, the ParkingToll class provides three services:
* Book parking slots.
* Release parking slots.
* Bill a car for booking a parking slot.

## Prerequisites

### Dependencies

#### Maven 
Maven is used to manage project dependencies and project lifecycle. 

#### Simple Logging Facade for Java 
slf4j is used for logging purposed. slf4j nop is used in version 1.7.28. 

Logging will be done using simple SLF4J. Using Simple Logging Facade has important benefit of not imposing a logging framework to the end-user. Actually, If no slf4j binding is found on the class path, then slf4j-api will default to a no operation discarding all logging request.

#### JUnit
Junit is used for Unit testing as well as integration testing. Version used: 4.12

## API Features
Different features of the API as it follows. 

### Parking Lot Booking Slot
Given a car, its type will help finding a available slot. it a slot for the car type is free, it will be associated to the car. A reservation object will be creation to make the assocaited between the car and the slot. The reservation will also track the time when the reservation start.

### Release Slot
Given the slot to be free, the parking lot will find the reservation associated to the slot and will close it, by setting the departure time. It also, calculate the whole duration of the reservation.

### Billing 
Given a reservation, the parking lot will calculate the price of the reservation. thanks to the Pricing Policy implemented by the parking tool, the Price will be returned.


## Object Models

### Parking Toll
Parking Toll is the representation of a parking lot. It is defined by the set of slots is where cars can park. The parking toll manage the slot and car reservations as well as bill the customer for the parking period. The lattest is achieve by implementing the Pricing Policy. Different pricing policy can be used but only one can be used per parking lot. Free methods are publicly available :
* BookSlot - Given a car, it will find a free slot in the parking lot for the type of the car, if any.
* ReleaSlot - will release a specific Parking Slot. 
* CalculatePrice - method inherited from the pricing policy interface and allows to calculte the price. 

### Pricing Policy
Pricing policy is an interface that describes the methods that a parking lot as to implement. A parking lot should only implement one princing policy. The pricing policy is provided with the creation of the parking and it holds its business logic.

### Car 
the car class can represent different types of car, based on their consumption mean. Gasoline and Electric type cars are represented in this API. The car type will define the slot type required when parking the vehicule in the Parking Lot.
All the different type of cars are represented in the CarType enum.

Available type of Car
* gaosline
* Electric20KW
* Electric50KW

### Slots
Different types of slots are represented. They are definined by their location. But also, a slot is associated to a type of car. However, a slot can only be used by a car from the exact same type. For instance, a gaosline car cannot use a slot for electric cars, as well as an electric car with using a 20KW charger cannot park in a slot for electric cars using a 50KW charger, and vice versa.

Available type of slot: same as the type of cars.

### Reservation
A reservation is object mapping the car and the parking slot. iw will also hold time information such as the time of reservation was done and the full duration of the reservation. The management of the reservation is done internally. 

### Price
The price of a reservation will be returned to the custumer. It defines the amount and the currency.

### Currency
An enum that defines a finite number of currencies. It is agregated in the Price object.

## Build
For building the ParkingToll API, use the Maven script in the root of the project with the clean and package options.

```bash
./mvnw clean package
```

## JavaDoc Generation
Maven Plugin maven-javadoc-plugin allows to generate the javadoc by running the command. It will also package and deploy the application. 

```bash
./mvnw javadoc:javadoc
```

## Examples
In this section we will see how to use the API via API example. please refer to the example package.

### 1 - Initialization
Use the ParkinToll to generate a parking lot and the a Car you want to park. For instance, one can use the ParkingToll Maracana and a Gasoline Car as is follows:

```java
ParkingToll maracana = ParkingToll.builder("Maracana"); 
Car car = new GasolineCar();
```

### 2 - Book as Slot
Use the car instance to book a slot in the parking lot but only if a slot avaiable. the Parkingtoll has a pollicy that a car is associated to one type of slot only. If slot is available, it is returned in the optional. otherwise no slot present in the API response.

```java
Optional<Slot> slot = maracana.bookSlot(car);
Integer slotId = slot.getLocation();
```

### Release a Slot
Gather  the reservation associated to the car and the slot.

```java
Optional<Reservation> reservation = maracana.releaseSlot(slot)
Car car = reservation.getCar();
Optional<Slot> slot =  reservation.getSlot();
long duration =  reservation.getDuration();
```

### Invoce the client
When invoicing a client for booking a slot, it will return the Price Object. user can get the amount and the its currency information.

```java
Price price = maracana.calculatePrice(reservation);
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

