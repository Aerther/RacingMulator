package  visual

import visual.*
import models.*
import enums.*

class Menu() {
    fun showNotEnoughPointsToUpdate() {
        println()
        println("Sorry, you don't have any update points left. Go racing to get some")
        println()    
    }
    
    fun showStatsMenu(player: Driver) {
        println()
        println("--------------------------------------------")
        println("Current Points to Update: ${player.updatePointsLeft}")
        println("1 - Consistency (current: ${if (player.consistency >= 100) "MAX" else player.consistency})")
        println("2 - Defense (current: ${if (player.defense >= 100) "MAX" else player.defense})")
        println("3 - Overtake (current: ${if (player.overtake >= 100) "MAX" else player.overtake})")
        println("4 - Control (current: ${if (player.control >= 100) "MAX" else player.control})")
        println("17 - Exit")
        println("--------------------------------------------")
        println()
    }
    
    fun showMenu() {
        println()
        println("--------------------------------------------")
        println("1 - Simulate current race")
        println("2 - See current race")
        println("3 - See the Drivers Championship Standings")
        println("4 - See the Constructors Championship Standings")
        println("5 - See the calendar of races")
        println("6 - See contract offers")
        println("7 - See your stats")
        println("8 - See other championships standings")
        println("9 - Save")
        println("17 - Exit")
        println("--------------------------------------------")
        println()
    }
    
    fun showRaceResults(race: Race) {
        showRaceResultsList(race)
    }
    
    fun showPlayerStats(champ: Championship, player: Driver) {
        showPlayerStatsList(champ, player)
    }
    
    fun showCurrentRace(champ: Championship) {
        showCalendarOfRacesList(champ, true)
    }
    
    fun showCalendarOfRaces(champ: Championship) {
        showCalendarOfRacesList(champ)
    }
    
    fun showOffers(offers: MutableList<Offer>) {
        showOffersList(offers)
    }
    
    fun showDriverStandings(championship: Championship) {
        showDriverStandingsList(championship)
    }
    
    fun showDriverStandings(championships: MutableList<Championship>, categoryType: TypeCategory) {
        var champ = championships.find {it.category.categoryType == categoryType} ?: return
        
        showDriverStandingsList(champ)
    }
    
    fun showConstructorStandings(championship: Championship) {
        showConstructorStandingsList(championship)
    }
    
    fun showConstructorStandings(championships: MutableList<Championship>, categoryType: TypeCategory) {
        var champ = championships.find {it.category.categoryType == categoryType} ?: return
        
        showConstructorStandingsList(champ)
    }
    
    fun showChampionships(championships: MutableList<Championship>, currentChamp: Championship) {
        var champs = championships.filter { it != currentChamp }.toMutableList()
        
        showChampionshipsList(champs)
    }
}