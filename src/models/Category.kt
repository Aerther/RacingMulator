package models

import enums.TypeCategory

class Category(
    val id: Int,
    val name: String,
    val categoryType: TypeCategory = TypeCategory.FORMULA1,
    val teams: MutableList<Team> = mutableListOf(),
    val teamsQuantity: Int = 10,
    val maxQuantityOfRaces: Int = 20,
    var leftoverDriver: Driver? = null,
) {
    fun addTeam(team: Team) {
        if(teams.size > teamsQuantity) return
        
        teams.add(team)
    }
}