package models

class Championship(
    val id: Int,
    var category: Category,
    var currentRaceIndex: Int = 0,
    var races: MutableList<Race> = mutableListOf<Race>(),
    var drivers: MutableList<Driver> = mutableListOf<Driver>(),
    var teams: MutableList<Team> = mutableListOf<Team>()
) {
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
    
    fun calcConstructorStandings() {
        this.calcDriverStandings()
        
        for(team in teams) {
            var driver1 = team.drivers[0]
            var driver2 = team.drivers[1]
            
            team.pointsScored = driver1.pointsScored + driver2.pointsScored
        }
    }
    
    fun calcDriverStandings() {
        for(race in races) {
            if(!race.isCompleted || race.isAccounted) continue
            
            race.calculateDriverResultsPoints()
        }
    }
}