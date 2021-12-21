import `class`.ParkingSpace
import java.text.SimpleDateFormat
import java.util.*

fun main() {
    val parking = Parking(mutableSetOf())

    //Test
    val calendar = Calendar.getInstance()
    val date = SimpleDateFormat("dd-MM-yyyy").parse("20-12-2021")
    calendar.time = date
    val vehicleCustom = Vehicle("AAA-99", VehicleType.BUS,  checkInTime = calendar)
    parking.addVehicle(vehicleCustom)

    //Test excedent
    val calendar2 = Calendar.getInstance()
    calendar2.set(Calendar.HOUR_OF_DAY, 14)
    calendar2.set(Calendar.MINUTE, 0)
    val vehicleCustom2 = Vehicle("AAA-98", VehicleType.MOTORCYCLE, checkInTime = calendar2)
    parking.addVehicle(vehicleCustom2)

    (0..20).forEach { i ->
        val randomVehicle = VehicleType.values().toList().shuffled().first()
        val hasDiscount = if (i % 2 == 0) "DISCOUNT_CARD_$i" else null
        val response = parking.addVehicle(Vehicle("AAA-$i", randomVehicle, hasDiscount))

        if(response) println("Welcome to AlkeParking!") else println("Sorry, the has check-in failed")
    }

    val parkingSpace = ParkingSpace(vehicleCustom2, parking)
    parkingSpace.checkOutVehicle()

    parking.showProfits()

    parking.listVehicles().mapIndexed { index, plate ->
        println("Vehicle ${index + 1}: $plate")
    }
}