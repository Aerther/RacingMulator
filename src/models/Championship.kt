package models

import enums.*

class Championship(
    val id: Int,
    var category: Category,
    var currentRaceIndex: Int = 0,
    var races: MutableList<Race> = mutableListOf<Race>(),
    var drivers: MutableList<Driver> = mutableListOf<Driver>(),
    var teams: MutableList<Team> = mutableListOf<Team>()
) {
    fun simulateCurrentRace(): Race {
        var race = getCurrentRace()
        race.simulate(this)
        
        this.currentRaceIndex++
            
        return race
    }
    
    fun getCurrentRace(): Race {
        return races[currentRaceIndex]
    }
    
    fun start(tracks: MutableList<Track>, teams: MutableList<Team>, drivers: MutableList<Driver>) {
        var driversCopy: MutableList<Driver> = drivers.filter {it.team.category.categoryType == category.categoryType}.toMutableList()
        driversCopy.remove(category.leftoverDriver)
        
        this.drivers = driversCopy
        
        var teamsCopy = teams.filter {it.category.categoryType == category.categoryType}.toMutableList()
        this.teams = teamsCopy
        
        setTracksToRace(tracks, driversCopy)
    }
    
    fun setTracksToRace(tracks: MutableList<Track>, drivers: MutableList<Driver>) {
        val tracksCopy = tracks.shuffled()
        
        var index = 1
        for(track in tracksCopy) {
            if(races.size >= category.maxQuantityOfRaces) break
            
            var race = Race(id = index, track = track)
            race.addDrivers(drivers)
            
            races.add(race)
            
            index++
        }
    }
    
    fun hasEnded(): Boolean {
        return this.currentRaceIndex == this.races.size
    }
    
    fun restartDriversAndTeamsPoints() {
        for(driver in drivers) {
            driver.pointsScored = 0
            driver.highestPosition = 100
        }
        
        for(team in teams) {
            team.pointsScored = 0
        }
    }
    
    fun sortDriversStandings() {
        this.drivers = this.drivers.sortedWith(
            compareByDescending<Driver> { it.pointsScored }.thenBy { it.highestPosition }
        ).toMutableList()
        
        var position = 1
        for(driver in drivers) {
            driver.position = position
            
            position++
        }
    }
    
    fun sortConstructorsStandings() {
        this.teams = this.teams.toList().sortedByDescending { it.pointsScored }.toMutableList()
        
        var position = 1
        for(team in teams) {
            team.position = position
            
            position++
        }
    }
    
    fun calcConstructorStandings() {
        this.calcDriverStandings()
        
        for(team in teams) {
            var points = 0
            
            for(driver in team.drivers) {
                points += driver.pointsScored
            }
            
            team.pointsScored = points
        }
    }
    
    fun calcDriverStandings() {
        for(race in races) {
            if(!race.isCompleted || race.isAccounted) continue
            
            race.calculateDriverResultsPoints()
        }
    }
}