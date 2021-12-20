data class Parking(val vehicles : MutableSet<Vehicle>) {
    fun addVehicle(vehicle: Vehicle): Boolean {
        if (vehicles.size == 20) return false
        return vehicles.add(vehicle)
    }
}