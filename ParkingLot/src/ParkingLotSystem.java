import com.parkinglot.entranceexit.Entrance;
import com.parkinglot.entranceexit.Exit;
import com.parkinglot.parkinglot.ParkingLot;
import com.parkinglot.payment.CardPayment;
import com.parkinglot.payment.PaymentStrategy;
import com.parkinglot.ticketing.Ticket;
import com.parkinglot.vehicle.Bike;
import com.parkinglot.vehicle.Car;
import com.parkinglot.vehicle.Truck;
import com.parkinglot.vehicle.Vehicle;

public class ParkingLotSystem {
    private ParkingLot parkingLot;
    private Entrance entrance;
    private Exit exit;

    public ParkingLotSystem(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.entrance = new Entrance(parkingLot);
        this.exit = new Exit(parkingLot);
    }

    public Ticket vehicleEntry(Vehicle vehicle) {
        return entrance.processEntry(vehicle);
    }

    public void vehicleExit(Ticket ticket, Vehicle vehicle, PaymentStrategy paymentStrategy) {
        exit.processExit(ticket, vehicle, paymentStrategy);
    }

    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(3, 100);
        ParkingLotSystem system = new ParkingLotSystem(parkingLot);

        Vehicle car = new Car("Asknskns");
        Vehicle truck = new Truck("aksnkns");
        Vehicle bike = new Bike("akdsnkns");

        Ticket carTicket = system.vehicleEntry(car);
        Ticket truckTicket = system.vehicleEntry(truck);
        Ticket bikeTicket = system.vehicleEntry(bike);


        system.vehicleExit(carTicket, car, new CardPayment("cardNo1"));


    }
}
