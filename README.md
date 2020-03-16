# Parking Toll Library


## Parkingtoll API Overiew

### Overview

ParkingToll API 

This library represent a parking lot with parking slots that can be book by cars of different types. Each parking slot is associated with one and only one type of car. Also, the Parking Toll allows invoicing clients afther their parking reservation over. Different  pricing policy can be implemented by each ParkingToll. Finally, the ParkingToll class provides three services:
* Book parking slots.
* Release parking slots.
* Bill a car for booking a parking slot.

### Object Models

#### Parking Toll
Parking Toll is the representation of a parking lot. It is defined by the set of slots is where cars can park. The parking toll manage the slot and car reservations as well as bill the customer for the parking period. The lattest is achieve by implementing the Pricing Policy. Different pricing policy can be used but only one can be used per parking lot. Free methods are publicly available :
* BookSlot - Given a car, it will find a free slot in the parking lot for the type of the car, if any.
* ReleaSlot - will release a specific Parking Slot. 
* CalculatePrice - method inherited from the pricing policy interface and allows to calculte the price. 

#### Car 
the car class can represent different types of car, based on their consumption mean. Gasoline and Electric type cars are represented in this API. The car type will define the slot type required when parking the vehicule in the Parking Lot.
All the different type of cars are represented in the CarType enum.

Available type of Car
* Gasoline
* Electric20KW
* Electric50KW

#### Slots
Different types of slots are represented. They are definined by their location. But also, a slot is associated to a type of car. However, a slot can only be used by a car from the exact same type. For instance, a gaosline car cannot use a slot for electric cars, as well as an electric car with using a 20KW charger cannot park in a slot for electric cars using a 50KW charger, and vice versa.

Available type of slot
* Gasoline
* Electric20KW
* Electric50KW

#### Price
The price of a reservation will be returned to the custumer. It defined the amount and the currency.


## Dependencies

### Maven 

Maven is used to manage project dependencies and project lifecycle. 

### Simple Logging Facade for Java 

slf4j is used for logging purposed. slf4j nop is used in version 1.7.28. 

### JUnit

Junit is used for Unit testing as well as integration testing. Version used: 4.12


## Build

For building the ParkingToll API, use the Maven script m in the root of the project.

```bash
./mvnw clean package
```

## JavaDoc Generation

Maven Plugin maven-javadoc-plugin allows to generate the javadoc by running the command. It will also package and deploy the application. 

```bash
./mvnw install
```


## Logging

Logging will be done using simple SLF4J. Using Simple Logging Facade has important benefit of not imposing a logging framework to the end-user. Actually, If no slf4j binding is found on the class path, then slf4j-api will default to a no operation discarding all logging request.