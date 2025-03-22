Features:
- System should be able to allow vehicles to park in the parking spot
- System should be able to allow vehicles to un-park and free the parking spot
- System should Issue a ticket at entrance, which is tracker for billing
- System should accept multiple modes of payments (card, cash, mobile app etc)
- Multiple Floors, Different SpotTypes and Different vehicleTypes needs to be supported

Qualities:
- Handle edge cases like parking lot being full gracefully - reliability
- allot a spot with concurrency taken into account - reliability
- concurrency
- handle large number of floors and spots - scalability
- Payment failed, prompt to user to retry.

park a vehicle to spot
free the spot when vehicle leaves
calculate amount per vehicle according to the timeSpent in parking lot
Handle different payment strategies
Handling floors and handling spots

Entrance - Issue a ticket and admit a vehicle
ParkingLot: List<Floor>
- parkVehicle(SpotType)
- unParkVehicle(Vehicle)
- findAvailableSpot(SpotType)
Floor: List<Spot> 
- numOfSpots, floorNumber
- different proportions of spotTypes
- lets say 50% Bikes, 30% Cars, 10% Trucks, 10% EV-Cars
Spot: isOccupied, vehicle, SpotType, spotNumber
- parkVehicle(Vehicle)
- unParkVehicle()
- isOccupied()
Ticket
- TicketNumber
- EntryTime
- Vehicle
Payment
- Ticket
- PaymentStrategy
- From Ticket calculate duration in minutes and calculate amount as per vehicles payment calculation policies.
- PaymentStrategy.pay(amount) and allow vehicle to leave on successful payment otherwise , prompt to user for retry payment.
Exit - Calculate amount and accept payment and un-park vehicle
