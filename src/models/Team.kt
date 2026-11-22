package models

class Team(
    val id: Int,
    val name: String,
    val seats: Int = 2,
    val drivers: MutableList<Driver> = mutableListOf(),
    val category: Category,
    var pointsScored: Int = 0,
    var level: Int = 100,
    var objective: Int = 1,
    var position: Int = 0
) {
    fun calcObjective() {
        this.objective = this.level * this.seats * 9/10
    }
    
    fun addDriver(driver: Driver) {
        if(drivers.size > seats) return
        
        drivers.add(driver)
    }
    
    fun setDriver(driver: Driver, seatTaken: Int = 2, isPlayer: Boolean = false) {
        var index = seatTaken - 1
        
        val tempDriver = drivers[index] 
        drivers[index] = driver
        
        if(isPlayer) {
            category.leftoverDriver = tempDriver
        }
    }
    
    fun removePlayer(index: Int) {
        drivers[index] = category.leftoverDriver ?: return
        
        category.leftoverDriver = null
    }
}