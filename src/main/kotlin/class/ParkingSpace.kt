package `class`
import Vehicle
import VehicleType
import java.util.*
import kotlin.math.roundToInt

data class ParkingSpace(val vehicle:Vehicle, val vehicles:MutableSet<Vehicle>) {
    val MINUTE_IN_MILIS = 60000 //TODO Move to constants
    var HOUR_IN_MINS = 60
    var FRACTION_MINUTES = 15
    var EXCEDENT_PRICE = 5

    val parkedTime:Long
    get() = (Calendar.getInstance().timeInMillis - vehicle.checkInTime.timeInMillis) / MINUTE_IN_MILIS

    fun checkOutVehicle(plate:String){
        val vehicleCheck = vehicles.firstOrNull { v -> v.plate == plate }
        vehicleCheck?.let {
            onSuccess(calculateFee(vehicle.vehicleType))
            //TODO Detele Vehicle
        } ?: onError()

    }

    fun onSuccess(price:Int){
        println(price)
    }

    fun onError(){
        println("error")
    }

    fun calculateFee(type: VehicleType) : Int{
        val baseHours = HOUR_IN_MINS * 2
        var fee = type.price
        if (parkedTime > baseHours){
            val excedentTime = (parkedTime - baseHours)
            val blocks = (excedentTime / FRACTION_MINUTES).toDouble().roundToInt()
            fee += (EXCEDENT_PRICE * blocks)
        }

        return fee
    }

}