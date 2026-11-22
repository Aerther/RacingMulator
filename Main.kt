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
var champs: MutableList<Championship> = calculation.getStartingChampionships(categories)

var champ = champs.find {it.category.categoryType == STARTER_CATEGORY} ?: champs[2]

fun main() {
    println("Welcome so RacingMulator")
    var name = input.getPlayerName()
    
    println("Here is your initial offers")
    println("Please, input the offer number you want")
    menu.showOffers(offers)
    
    var team = input.getOffer(offers)
    
    offers.clear()
    
    var player = Driver(id = 0, name = name, team = team, isFirstSeason = true, isPlayer = true)
    team.setDriver(player, 2, true)
    drivers.add(player)
    
    calculation.startChampionships(champs, tracks, teams, drivers)
    
    while(true) {
        menu.showMenu()
        var number = input.getOption()
        
        if(number == 1) {
            // simulate current race
            calculation.simulateCurrentRaces(champs, champ)
            
            var race = champ.simulateCurrentRace()
            
            menu.showRaceResults(race)
            
            player.updatePointsLeft += champ.category.categoryType.pointsPerRace
            
            if(race.hasBeatenTeammate(player)) player.updatePointsLeft += 3
            
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
            
            if(player.updatePointsLeft <= 0 && option == "y") {
                menu.showNotEnoughPointsToUpdate()  
            } else if(option == "y") {
                
                while(true) {
                    menu.showStatsMenu(player)
                
                    var stat = input.getOption()
                    var updated = false
                    
                    if(stat == 1) {
                        if(player.consistency != 100) updated = true
                        
                        player.consistency = minOf(player.consistency + 1, 100)
                    } else if(stat == 2) {
                        if(player.defense != 100) updated = true
                        
                        player.defense = minOf(player.defense + 1, 100)
                    } else if(stat == 3) {
                        if(player.overtake != 100) updated = true
                        
                        player.overtake = minOf(player.overtake + 1, 100)
                    } else if(stat == 4) {
                        if(player.control != 100) updated = true
                        
                        player.control = minOf(player.control + 1, 100)
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
            menu.showChampionships(champs, champ)
            
            var champ = input.getChamp(champs)
            
            menu.showDriverStandings(champ)
            menu.showConstructorStandings(champ)
        } else if(number == 9) {
            // save
        } else if(number == 17) {
            // exit
            break
        }
        
        if(champ.hasEnded()) {
            menu.showDriverStandings(champ)
            menu.showConstructorStandings(champ)
            
            offers.addAll(calculation.getOffers(champ, player))
            
            if(offers.size != 0) {
                menu.showOffers(offers)
                
                var team = input.getOffer(offers)
                
                player.team.removePlayer(index = 1)
                team.setDriver(player, 2, true)
                player.team = team
                
                offers.clear()
            }
            
            println()
            println("Championship has ended, starting another")
            println()
            
            champ.restartDriversAndTeamsPoints()
            champ = Championship(id = 1, category = player.team.category)
            champ.start(tracks, teams, drivers)  
            
            player.isFirstSeason = false
        }
    }
    
    println("Bye bye...")
}

// kotlinc Main.kt src\constants\*.kt src\utils\*.kt src\visual\*.kt src\models\*.kt src\enums\*.kt src\service\*.kt -include-runtime -d Main.jar
// java -jar Main.jar