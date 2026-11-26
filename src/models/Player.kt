package models

class Player(
    id: Int,
    name: String,
    team: Team,
    attributes: DriverAttributes = DriverAttributes(0, 0, 0, 0),
    pointsScored: Int = 0,
    updatePointsLeft: Int = 10,
    position: Int = 0,
    highestPosition: Int = 100,
    isFirstSeason: Boolean = true,
    isPlayer: Boolean = true,
    
    var categoryStats: MutableList<CategoryStats> = mutableListOf()
) : Driver(
    id,
    name,
    team,
    attributes,
    pointsScored,
    updatePointsLeft,
    position,
    highestPosition,
    isFirstSeason,
    isPlayer
) {
    fun dividePoints() {
        this.attributes.dividePoints(this.team.category.categoryType.level + 1)  
    }
    
    fun updateStatsAfterRace(race: Race) {
        if(!categoryStats.any {it.category == this.team.category}) {
            categoryStats.add(CategoryStats(category = this.team.category))   
        }
        
        var categoryStat = categoryStats.find {it.category == this.team.category} ?: return
        
        categoryStat.racesParticipated += 1
        
        var result = race.findResultOfDriver(this)
        categoryStat.totalPointsScored += result.points
        
        if(result.position <= 3) {
            categoryStat.podiums += 1
            
            if(result.position == 1) {
                categoryStat.wins += 1
            }
        }
    }
    
    fun updateStatsAfterSeason() {
        if(!categoryStats.any {it.category == this.team.category}) {
            categoryStats.add(CategoryStats(category = this.team.category))   
        }
        
        var categoryStat = categoryStats.find {it.category == this.team.category} ?: return
        
        categoryStat.seasonsParticipated += 1
        
        if(this.position == 1) {
            categoryStat.championshipsWon += 1
        }
    }
}