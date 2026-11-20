package  visual

import visual.*
import models.*
import enums.*

class Menu() {
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
    
    fun showOffers(offers: MutableList<Offer>) {
        showOffersList(offers)
    }
    
    fun showDriverStandings(championship: Championship) {
        showDriverStandings(mutableListOf(championship), championship.category.categoryType)
    }
    
    fun showDriverStandings(championships: MutableList<Championship>, categoryType: TypeCategory) {
        showDriverStandingsList(championships, categoryType)
    }
    
    fun showConstructorStandings(championship: Championship) {
        showConstructorStandingsList(mutableListOf(championship), championship.category.categoryType)
    }
    
    fun showConstructorStandings(championships: MutableList<Championship>, categoryType: TypeCategory) {
        showConstructorStandingsList(championships, categoryType)
    }
}