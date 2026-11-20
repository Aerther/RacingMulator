package visual

import models.*
import enums.*
    
fun showConstructorStandingsList(champ: Championship) {
    champ.calcConstructorStandings()
    
    var standings = champ.teams.toList().sortedByDescending { it.pointsScored }
    
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

fun showConstructorStandingsList(championships: MutableList<Championship>, categoryType: TypeCategory) {
    var championship = championships.find {it.category.categoryType == categoryType} ?: return
    
    showConstructorStandingsList(championship)
}

fun showDriverStandingsList(champ: Championship) {
    champ.calcDriverStandings()
    
    val standings = champ.drivers.toList().sortedByDescending { it.pointsScored }
    
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

fun showDriverStandingsList(championships: MutableList<Championship>, categoryType: TypeCategory) {
    var championship = championships.find {it.category.categoryType == categoryType} ?: return
    
    showDriverStandingsList(championship)
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
            driver.defense,
            driver.control,
            driver.overtake,
            driver.consistency
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
    
    var index = 0
    for(race in champ.races) {
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
        index++
    }
    
    println("+----------+--------------------------+-------------+--------+----------------------+----------+")
    println()
}

fun showPlayerStatsList(champ: Championship, player: Driver) {
    champ.calcDriverStandings()
    
    println()
    println("+-------------------------+-------------------------+---------------+---------+---------+----------+-------------+")
    println("| Your name               | Your team               | Points Scored | Defense | Control | Overtake | Consistency |")
    println("+-------------------------+-------------------------+---------------+---------+---------+----------+-------------+")
    
    val format = "| %-23s | %-23s | %-13d | %-7d | %-7d | %-8d | %-11d |"
    
    val formatString = format.format(
        player.name,
        player.team.name,
        player.pointsScored,
        player.defense,
        player.control,
        player.overtake,
        player.consistency
    )
        
    println(formatString)
    
    println("+-------------------------+-------------------------+---------------+---------+---------+----------+-------------+")
    println()
}