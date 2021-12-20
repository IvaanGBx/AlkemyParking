package `class`
import Vehicle
import VehicleType
import java.util.*
import kotlin.math.roundToInt

data class ParkingSpace(val vehicle:Vehicle, val vehicles:MutableSet<Vehicle>) {
    private val parkedTime:Long
    get() = (Calendar.getInstance().timeInMillis - vehicle.checkInTime.timeInMillis) / Constants.MINUTE_IN_MILIS

    fun checkOutVehicle(){
        val vehicleCheck = vehicles.firstOrNull { v -> v.plate == vehicle.plate }
        vehicleCheck?.let {
            onSuccess(calculateFee())
            vehicles.remove(vehicle)
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
            val blocks = (excedentTime / Constants.FRACTION_MINUTES).toDouble().roundToInt()
            fee += (Constants.EXCEDENT_PRICE * blocks)
        }

        vehicle.discountCard?.let {
            fee = (fee * Constants.DISCOUNT).toInt()
        }
        return fee
    }

}