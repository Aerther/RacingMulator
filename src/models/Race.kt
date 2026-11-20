package models

import models.Result

class Race(
    var id: Int,
    var track: Track,
    val results: MutableList<Result> = mutableListOf<Result>(),
    var isCompleted: Boolean = false,
    var isAccounted: Boolean = false
) {
    fun addDrivers(drivers: MutableList<Driver>) {
        var position = 1
        for(driver in drivers) {
            var result = Result(id = position, position = position, driver = driver)
            
            results.add(result)
            
            position++
        }
    }
    
    fun calculateDriverResultsPoints() {
        if(!this.isCompleted || this.isAccounted) return
        
        for(result in results) {
            var points = getPointsBasedOnPosition(result.position)
            
            result.points = points
            result.driver.pointsScored += points
        }
        
        this.isAccounted = true
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