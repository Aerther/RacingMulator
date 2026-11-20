import utils.FileReader
import visual.*
import service.*
import models.*
import enums.*
import constants.STARTER_CATEGORY

var reader = FileReader()
var menu = Menu()
var input = Input()
var calculation = Calculation()

var tracks = reader.readTrackFile()
var categories = reader.readCategoryFile()
var teams = reader.readTeamsFile(categories)
var drivers = reader.readDriversFile(teams)
var offers: MutableList<Offer> = calculation.getStarterOffers(teams, STARTER_CATEGORY)

var formula = categories.find {it.categoryType == STARTER_CATEGORY} ?: categories[0]
var champ = Championship(id = 1, category = formula)

fun main() {
    println("Welcome so RacingMulator")
    var name = input.getPlayerName()
    
    println("Here are your initial offers")
    println("Please, input the offer number you want")
    menu.showOffers(offers)
    
    var offerNumber = input.getOffer()
    
    var offerChosen = offers.find {it.id == offerNumber} ?: offers[0]
    var team = offerChosen.team
    
    offers.clear()
    
    var player = Driver(id = 0, name = name, team = team)
    team.setDriver(player, 2, true)
    drivers.add(player)
    
    champ.start(tracks, teams, drivers)
    
    while(true) {
        menu.showMenu()
        var number = input.getOption()
        
        if(number == 1) {
            // simulate current race
        } else if(number == 2) {
            // see current race
            menu.showCurrentRace(champ)
        } else if(number == 3) {
            // see drivers championship
            menu.showDriverStandings(champ)
        } else if(number == 4) {
            // see constructor championship
            menu.showConstructorStandings(champ)
        } else if(number == 5) {
            // see calendar of races
            menu.showCalendarOfRaces(champ)
        } else if(number == 6) {
            // see contract offers
            menu.showOffers(offers)
        } else if(number == 7) {
            // see your stats
            menu.showPlayerStats(champ, player)
            
            var option = input.getOptionToUpdateStats()
            
            if(player.updatePointsLeft <= 0) {
                menu.showNotEnoughPointsToUpdate()  
            } else if(option == "yes") {
                
                while(true) {
                    menu.showStatsMenu(player)
                
                    var stat = input.getOption()
                    var updated = false
                    
                    if(stat == 1) {
                        player.consistency += 1
                        
                        updated = true
                    } else if(stat == 2) {
                        player.defense += 1
                        
                        updated = true
                    } else if(stat == 3) {
                        player.overtake += 1
                        
                        updated = true
                    } else if(stat == 4) {
                        player.control += 1
                        
                        updated = true
                    } else if(stat == 17) {
                        break
                    }
                    
                    if(updated) {
                        player.updatePointsLeft -= 1
                    }
                    
                    if(player.updatePointsLeft <= 0) {
                        menu.showNotEnoughPointsToUpdate()
                        break
                    }
                }
            }
        } else if(number == 8) {
            // see other championships standings
        } else if(number == 9) {
            // save
        } else if(number == 17) {
            // exit
            break
        }
        
    }
    
    println("Bye bye...")
}

// kotlinc Main.kt src\constants\*.kt src\utils\*.kt src\visual\*.kt src\models\*.kt src\enums\*.kt src\service\*.kt -include-runtime -d Main.jar
// java -jar Main.jar