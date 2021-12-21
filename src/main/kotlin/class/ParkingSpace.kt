package `class`
import Parking
import Vehicle
import VehicleType
import java.util.*
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt

data class ParkingSpace(val vehicle:Vehicle, val parking: Parking) {
    private val parkedTime:Long
    get() = (Calendar.getInstance().timeInMillis - vehicle.checkInTime.timeInMillis) / Constants.MINUTE_IN_MILIS

    fun checkOutVehicle(){
        val vehicleCheck = parking.vehicles.firstOrNull { v -> v.plate == vehicle.plate }
        vehicleCheck?.let {
            val price = calculateFee()
            parking.updateProfits(price)
            onSuccess(price)
            parking.vehicles.remove(vehicle)
        } ?: onError()
    }

    private fun onSuccess(price:Int){
        println("Your fee is $$price. Come back soon")
    }

    private fun onError(){
        println("Sorry, the check-out failed!")
    }

    private fun calculateFee() : Int{
        val baseHours = Constants.HOUR_IN_MINS * 2
        var fee = vehicle.vehicleType.price

        if (parkedTime > baseHours){
            val excedentTime = (parkedTime - baseHours)
            val blocks = ceil((excedentTime.toDouble() / Constants.FRACTION_MINUTES)).toInt()
            fee += (Constants.EXCEDENT_PRICE * blocks)
        }

        vehicle.discountCard?.let {
            fee = (fee * Constants.DISCOUNT).toInt()
        }
        return fee
    }

}