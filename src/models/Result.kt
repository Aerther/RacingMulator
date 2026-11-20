package models

class Result(
    val id: Int,
    val results: MutableMap<Driver, Int> = mutableMapOf<Driver, Int>(),
    var isCompleted: Boolean = false
) {
    fun addDrivers(drivers: MutableList<Driver>) {
        var position = 1
        for(driver in drivers) {
            results.put(driver, position)
            
            position++
        }
    }
    
    fun getDriverResults(): MutableMap<Driver, Int> {
        var raceResult = mutableMapOf<Driver, Int>()
        
        for((driver, position) in results) {
            var points = getPointsBasedOnPosition(position)
            
            raceResult.put(driver, points)
        }
        
        return raceResult
    }
    
    fun getPointsBasedOnPosition(position: Int): Int {
        if(position > 10) return 0
        
        val points = mapOf(
            1 to 25,
            2 to 18,
            3 to 15,
            4 to 12,
            5 to 10,
            6 to 8,
            7 to 6,
            8 to 4,
            9 to 2,
            10 to 1
        )
        
        return points.getOrElse(position, { 0 })
    }
}