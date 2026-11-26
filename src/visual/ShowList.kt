package visual

import models.*
import enums.*
    
fun showChampionshipsList(champs: MutableList<Championship>) {
    println()
    println("+-------+--------------------+-----------------+-------------------+")
    println("| Index | Championship       | Quant. Of Teams | Quant. Of Drivers |")
    println("+-------+--------------------+-----------------+-------------------+")
    
    val format = "| %-5d | %-18s | %-15d | %-17d |"
    
    for(champ in champs) {
        var formatString = format.format(
            champ.id,
            champ.category.name,
            champ.teams.size,
            champ.drivers.size
        )
        
        println(formatString)
    }
    
    println("+-------+-------------------+-----------------+-------------------+")
    println()
}
    
fun showConstructorStandingsList(champ: Championship) {
    champ.calcConstructorStandings()
    champ.sortConstructorsStandings()
    
    var standings = champ.teams
    
    println()
    println("+----------+--------------------+----------+")
    println("| Position | Team               | Points   |")
    println("+----------+--------------------+----------+")
    
    val format = "| %-8d | %-18s | %-8d |"
    
    var position = 1
    for(team in standings) {
        val formatString = format.format(
            position,
            team.name,
            team.pointsScored
        )
        
        println(formatString)
        
        position++
    }
    
    println("+----------+--------------------+----------+")
    println()
    
}

fun showDriverStandingsList(champ: Championship) {
    champ.calcDriverStandings()
    champ.sortDriversStandings()
    
    val standings = champ.drivers
    
    println()
    println("+----------+----------------------+--------------------+----------+")
    println("| Position | Driver               | Team               | Points   |")
    println("+----------+----------------------+--------------------+----------+")
    
    val format = "| %-8d | %-20s | %-18s | %-8d |"
    
    var position = 1
    for (driver in standings) {
        val formatString = format.format(
            position,
            driver.name,
            driver.team.name,
            driver.pointsScored
        )
        
        println(formatString)
        
        position++
    }
    
    println("+----------+----------------------+--------------------+----------+")
    println()
}

fun showRaceResultsList(race: Race) {
    println()
    println("+----------+-------------------------+-------------------------+--------------+")
    println("| Position | Driver                  | Team                    | Time         |")
    println("+----------+-------------------------+-------------------------+--------------+")
    
    val format = "| %-8d | %-23s | %-23s | %-12.2f |"
    
    for(result in race.results) {
        val formatString = format.format(
            result.position,
            result.driver.name,
            result.driver.team.name,
            result.timeToComplete
        )
        
        println(formatString)
    }
    
    println("+----------+-------------------------+-------------------------+--------------+")
    println()
}

fun showDriversList(drivers: MutableList<Driver>) {
    println()
    println("+----------------------+--------------------+---------+---------+----------+-------------+")
    println("| Driver               | Team               | Defense | Control | Overtake | Consistency |")
    println("+----------------------+--------------------+---------+---------+----------+-------------+")
    
    val format = "| %-20s | %-18s | %-7d | %-7d | %-8d | %-11d |"
    
    for(driver in drivers) {
        val formatString = format.format(
            driver.name,
            driver.team.name,
            driver.attributes.defense,
            driver.attributes.control,
            driver.attributes.overtake,
            driver.attributes.consistency
        )
        
        println(formatString)
    }
    
    println("+----------------------+--------------------+---------+---------+----------+-------------+")
    println()
}

fun showTeamsList(teams: MutableList<Team>) {
    println()
    println("+----------------------+--------------------+---------------------+---------+")
    println("| Team                 | Category           | Quantity of Drivers | Seats   |")
    println("+----------------------+--------------------+---------------------+---------+")
    
    val format = "| %-20s | %-18s | %-19d | %-7s |"
    
    for(team in teams) {
        val formatString = format.format(
            team.name,
            team.category.name,
            team.drivers.size,
            team.seats
        )
        
        println(formatString)
    }
    
    println("+----------------------+--------------------+---------------------+---------+")
    println()
}

fun showCategoriesList(categories: MutableList<Category>) {
    println()
    println("+----------------------+------------------------+")
    println("| Category             | Quantity of Teams      |")
    println("+----------------------+------------------------+")
    
    val format = "| %-20s | %-22s |"
    
    for(category in categories) {
        val formatString = format.format(
            category.name,
            category.teamsQuantity
        )
        
        println(formatString)
    }
    
    println("+----------------------+------------------------+")
    println()
}

fun showOffersList(offers: MutableList<Offer>) {
    println()
    println("+----------------------+------------------------+---------------+----------------------+")
    println("| Offfer Number        | Team                   | Category      | Objective (position) |")
    println("+----------------------+------------------------+---------------+----------------------+")
    
    val format = "| %-20d | %-22s | %-13s | %-20d |"
    
    for(offer in offers) {
        val formatString = format.format(
            offer.id,
            offer.team.name,
            offer.team.category.name,
            offer.objective
        )
        
        println(formatString)
    }
    
    println("+----------------------+------------------------+---------------+----------------------+")
    println()
}

fun showCalendarOfRacesList(champ: Championship, showCurrentRace: Boolean = false) {
    println()
    println("+----------+--------------------------+-------------+--------+----------------------+----------+")
    println("| Position | Track                    | Length      | Laps   | Country              | Finished |")
    println("+----------+--------------------------+-------------+--------+----------------------+----------+")
    
    val format = "| %-8d | %-24s | %-11.2f | %-6d | %-20s | %-8b |"
    
    var index = -1
    for(race in champ.races) {
        index++
            
        if(champ.currentRaceIndex != index && showCurrentRace) continue
        
        val formatString = format.format(
            race.id,
            race.track.name,
            race.track.length,
            race.track.laps,
            race.track.country,
            race.isCompleted
        )
        
        println(formatString)
    }
    
    println("+----------+--------------------------+-------------+--------+----------------------+----------+")
    println()
}

fun showPlayerStatsList(champ: Championship, player: Player) {
    champ.calcDriverStandings()
    champ.sortDriversStandings()
    
    println()
    println("+-------------------------+-------------------------+---------+-----------+---------------+---------+---------+----------+-------------+")
    println("| Your name               | Your team               | Current | Objective | Points Scored | Defense | Control | Overtake | Consistency |")
    println("+-------------------------+-------------------------+---------+-----------+---------------+---------+---------+----------+-------------+")
    
    val format = "| %-23s | %-23s | %-7d | %-9d | %-13d | %-7d | %-7d | %-8d | %-11d |"
    
    val formatString = format.format(
        player.name,
        player.team.name,
        player.position,
        player.team.objective,
        player.pointsScored,
        player.attributes.defense,
        player.attributes.control,
        player.attributes.overtake,
        player.attributes.consistency
    )

    println(formatString)
    
    println("+-------------------------+-------------------------+---------+-----------+---------------+---------+---------+----------+-------------+")
    println()
}

fun showPlayerCarrerStatsList(champ: Championship, player: Player) {
    champ.calcDriverStandings()
    
    println()
    println("+-------------------+---------+---------------+---------+--------+-----------+---------------+")
    println("| Category          | Seasons | Championships | Races   | Wins   | Podiums   | Points Scored |")
    println("+-------------------+---------+---------------+---------+--------+-----------+---------------+")
    
    val format = "| %-17s | %-7d | %-13d | %-7d | %-6d | %-9d | %-13d |"
    
    for(categoryStat in player.categoryStats) {
        val formatString = format.format(
            categoryStat.category.name,
            categoryStat.seasonsParticipated,
            categoryStat.championshipsWon,
            categoryStat.racesParticipated,
            categoryStat.wins,
            categoryStat.podiums,
            categoryStat.totalPointsScored
        )

        println(formatString)
    }
    
    println("+-------------------+---------+---------------+---------+--------+-----------+---------------+")
    println()
}