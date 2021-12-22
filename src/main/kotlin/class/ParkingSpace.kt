package `class`
import Parking
import Vehicle
import java.util.*
import kotlin.math.ceil

data class ParkingSpace(val vehicle:Vehicle, val parking: Parking) {
    private val parkedTime:Long
    get() = (Calendar.getInstance().timeInMillis - vehicle.checkInTime.timeInMillis) / Constants.MINUTE_IN_MILIS


    //`checkOutVehicle` doesn't receive `plate` as an arg because it's called from the constructor class.
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


    /*
    * `calculateFee` doesn't receive `parkedTime` or `type` as args because it's called from the property class `parkedTime`
    * and `type` is a property from the constructor class `vehicle`.
    */
    private fun calculateFee() : Int{
        val baseHours = Constants.HOUR_IN_MINS * 2
        var fee = vehicle.vehicleType.price

        if (parkedTime > baseHours){
            val excedentTime = (parkedTime - baseHours)
            val blocks = ceil((excedentTime.toDouble() / Constants.FRACTION_MINUTES)).toInt()
            fee += (Constants.EXCEDENT_PRICE * blocks)
        }

        //`hasDiscountCard` is not an arg because it could be accessed from the constructor `vehicle`.
        // `discountCard` is a nullable (if null, the vehicle doesn't have discount card), so it must be validated using null safety
        // and the scope function `let`.
        vehicle.discountCard?.let {
            fee = (fee * Constants.DISCOUNT).toInt()
        }

        return fee
    }

}