# Parking Lot Management API

## Package

### Classes Overview

The Parking Toll API will use the following classes:

+ Parking
+ Reservation
+ Car
+ Slot
+ SlotType

### Interface Overview

The Parking Toll Library will use define the following interfaces:
+ PricingPolicy


## Classes description

#### Parking
	Find Slot method. Look for available slot of type carType. if available, slot is returned, else slot not found 404. 

#### Slot
	free Slot

#### PricingPolicy
	invoce method. Invoce the customer based on the policy and reservation information.

## API Features

### Finding Parking Slot

When a new car entering the parking lot, it should be assigned a slot that suits the vehicule. as per documented. every car has its own spot type and cannot use another slot with a different type. For instance, a gasoline based car cannot use an slot for an electric car. Also, electric cars cannot use a gasoline type slot, nor use a electric type slot different than the car type. e.g. electric car using 20KW charger cannot park in a slot with a 50KW charger and vice versa. 

### Release Parking Slot

Once the car leaves the parking lot, the slot it occupied must be freed.

### Billing Customer

When a car leaves the parking lot, the client should be invoced. The price of a parking slot follows a Pricing Policy. Generally speaking it can take many piece of information for invoicing the client. However, a Parking lot implements only one Pricing Policy.