import utils.FileReader
import visual.*
import service.*
import models.*
import enums.*

var reader = FileReader()
var menu = Menu()
var input = Input()
var calculation = Calculation()

var tracks = reader.readTrackFile()
var categories = reader.readCategoryFile()
var teams = reader.readTeamsFile(categories)
var drivers = reader.readDriversFile(teams)
var offers: MutableList<Offer> = calculation.getStarterOffers(teams)

var formula = categories.find {it.categoryType == TypeCategory.FORMULA1} ?: categories[0]
var champ = Championship(id = 1, category = formula)

fun main() {
    println("Welcome so RacingMulator")
    var name = input.getPlayerName()
    
    println("Here is your initial offers")
    println("Please, input the offer number you want")
    menu.showOffers(offers)
    
    var offerNumber = input.getOffer()
    
    var offerChosen = offers.find {it.id == offerNumber} ?: offers[0]
    var team = offerChosen.team
    
    var player = Driver(id = 0, name = name, team = team)
    team.setDriver(player, 2, true)
    drivers.add(player)
    
    champ.start(tracks, drivers)
    
    menu.showDriverStandings(champ)
    menu.showConstructorStandings(champ)
}

// kotlinc Main.kt src\utils\*.kt src\visual\*.kt src\models\*.kt src\enums\*.kt src\service\*.kt -include-runtime -d Main.jar
// java -jar Main.jar