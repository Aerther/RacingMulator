package models

class Championship(
    val id: Int,
    var category: Category,
    var races: MutableMap<Track, Result> = mutableMapOf<Track, Result>(),
    var drivers: MutableList<Driver> = mutableListOf<Driver>()
) {
    fun start(tracks: MutableList<Track>, drivers: MutableList<Driver>) {
        var driversCopy: MutableList<Driver> = drivers.filter {it.team.category.categoryType == category.categoryType}.toMutableList()
        driversCopy.remove(category.leftoverDriver)
        
        this.drivers = driversCopy
        
        setTracksToRace(tracks, driversCopy)
    }
    
    fun setTracksToRace(tracks: MutableList<Track>, drivers: MutableList<Driver>) {
        val tracksCopy = tracks.shuffled()
        
        var index = 1
        for(track in tracksCopy) {
            if(races.size >= category.maxQuantityOfRaces) break
            
            val result = Result(id = index)
            result.addDrivers(drivers)
            
            races.put(track, result)
            
            index++
        }
    }
    
    fun calcConstructorStandings(): MutableMap<Team, Int> {
        var driverStandings = calcDriverStandings()
        var standings = mutableMapOf<Team, Int>()
        
        for((driver, points) in driverStandings) {
            var team = driver.team
            
            if(standings.containsKey(team)) {
                standings[team] = standings[team]!! + points
            } else {
                standings.put(team, points)
            }
        }
        
        return standings
    }
    
    fun calcDriverStandings(): MutableMap<Driver, Int> {
        var standings = mutableMapOf<Driver, Int>()
        
        for((track, result) in races) {
            if(!result.isCompleted) continue
            
            var raceResult = result.getDriverResults()
            
            for((driver, points) in raceResult) {
                if (standings.containsKey(driver)) {
                    standings[driver] = standings[driver]!! + points
                } else {
                    standings[driver] = points
                }
            }
        }

        return standings
    }
}