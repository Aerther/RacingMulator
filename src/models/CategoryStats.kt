package models

class CategoryStats(
    val category: Category,
    
    var racesParticipated: Int = 0,
    var wins: Int = 0,
    var podiums: Int = 0,
    var championshipsWon: Int = 0,
    var totalPointsScored: Int = 0,
    var seasonsParticipated: Int = 0
) {
    
}