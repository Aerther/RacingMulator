package models

import models.Result
import enums.*

class Race(
    var id: Int,
    var track: Track,
    var results: MutableList<Result> = mutableListOf<Result>(),
    var isCompleted: Boolean = false,
    var isAccounted: Boolean = false
) {
    fun findResultOfDriver(driver: Driver): Result {
        return results.find {it.driver == driver} ?: results[0]
    }
    
    fun hasBeatenTeammate(player: Driver): Boolean {
        val resultPlayer = results.find { it.driver == player } ?: return false

        val resultTeammate = results.find { it.driver.team == player.team && it.driver != player } ?: return false

        return resultPlayer.position < resultTeammate.position
    }
    
    fun simulate(champ: Championship) {
        var typeCategory = champ.category.categoryType
        var quantOfTeams = champ.teams.size
        var difficulty = typeCategory.difficulty
        
        var lap = 1
        while(lap <= track.laps) {
            for(result in results) {
                var driver = result.driver
                var team = driver.team
                
                var median = driver.attributes.getAverage()
                val teamMultiplier = 0.9 + (team.level - 1) * difficulty / ((quantOfTeams - 1) * 10.0)
                
                if(typeCategory == TypeCategory.FORMULA3 && driver.isPlayer) median += 18
                if(driver.isFirstSeason) median += 13
                
                val luck = (-30..30).random() / 100.0
                
                var time = difficulty * (1 - median / (100.0 * (6 - difficulty))) * teamMultiplier + luck * 2 + 10
                
                result.timeToComplete += time
            }
            
            lap++
        }
        
        this.results = results.sortedBy { it.timeToComplete }.toMutableList()
        
        var position = 1
        for(result in this.results) {
            result.position = position
            position++
                
            if(result.driver.highestPosition > position) {
                result.driver.highestPosition = position
            }
        }
        
        this.isCompleted = true
    }
    
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