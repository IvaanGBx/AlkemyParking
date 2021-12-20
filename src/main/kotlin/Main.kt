import `class`.ParkingSpace
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun main(args: Array<String>) {

    val parking = Parking(mutableSetOf())


    //Test
    val calendar = Calendar.getInstance()
    val date = SimpleDateFormat("dd-MM-yyyy").parse("20-12-2021")
    calendar.time = date
    val vehicleCustom = Vehicle("AAA-99", VehicleType.BUS, checkInTime = calendar)
    parking.addVehicle(vehicleCustom)
    
    (0..20).forEach { i ->
        val randomVehicle = VehicleType.values().toList().shuffled().first()
        val hasDiscount = if (i % 2 == 0) "DISCOUNT_CARD_$i" else null
        val response = parking.addVehicle(Vehicle("AAA-$i", randomVehicle, hasDiscount))

        if(response) println("Welcome to AlkeParking!") else println("Sorry, the has check-in failed")
    }

    val parkingSpace = ParkingSpace(vehicleCustom, parking.vehicles)
    parkingSpace.checkOutVehicle(vehicleCustom.plate)
}