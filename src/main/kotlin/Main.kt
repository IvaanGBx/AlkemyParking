import java.util.*

fun main(args: Array<String>) {

    val parking = Parking(mutableSetOf())

    (0..20).forEach { i ->
        val randomVehicle = VehicleType.values().toList().shuffled().first()
        val hasDiscount = if (i % 2 == 0) "DISCOUNT_CARD_$i" else null
        val response = parking.addVehicle(Vehicle("AAA-$i", randomVehicle, hasDiscount))

        if(response) println("Welcome to AlkeParking!") else println("Sorry, the has check-in failed")
    }
}