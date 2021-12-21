data class Parking(val vehicles : MutableSet<Vehicle>, var profits:Pair<Int,Int> = Pair(0,0)) {

    fun addVehicle(vehicle: Vehicle): Boolean {
        if (vehicles.size == 20) return false
        return vehicles.add(vehicle)
    }

    fun updateProfits(price:Int){
        profits = Pair(profits.first + 1, profits.second + price)
    }

    fun showProfits(){
        println("${profits.first} vehicles have checked out and have earnings of $${profits.second}")
    }
}