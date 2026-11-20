package utils

import java.io.File
import models.*
import enums.*

class FileReader(
    val year: Int = 2026
) {
    val driversFile = loadCompleteFile("drivers.txt")
    var teamsFile = loadCompleteFile("teams.txt")
    var categoriesFile = loadCompleteFile("categories.txt")
    var tracksFile = loadCompleteFile("tracks.txt")
    
    fun loadCompleteFile(nameFile: String): String {
        return "data/$year/$nameFile"
    }
    
    fun readTrackFile(): MutableList<Track> {
        var tracks = mutableListOf<Track>()
        
        val arquive = File(tracksFile)
        
        arquive.forEachLine { line -> 
            var data = line.split(";")
            
            var track = Track(
                id = data[0].toInt(),
                name = data[1],
                length = data[2].toDouble(),
                laps = data[3].toInt(),
                country = data[4]
            )
            
            tracks.add(track)
        }
        
        return tracks
    }
    
    fun readCategoryFile(): MutableList<Category> {
        var categories = mutableListOf<Category>()
        
        val arquive = File(categoriesFile)
        
        arquive.forEachLine { line ->
            var data = line.split(";")
            
            var category = Category(
                id = data[0].toInt(),
                name = data[1],
                categoryType = TypeCategory.getTypeCategoryBasedOnString(data[2]),
                teamsQuantity = data[3].toInt()
            )

            categories.add(category)
        }
        
        return categories
    }
    
    fun readTeamsFile(categories: MutableList<Category>): MutableList<Team> {
        var teams = mutableListOf<Team>()
        
        val arquive = File(teamsFile)
        
        arquive.forEachLine { line -> 
            var data = line.split(";")
            
            var category = categories.find{it.id == data[4].toInt()} ?: categories[0]
            
            var team = Team(
                id = data[0].toInt(),
                name = data[1],
                seats = data[2].toInt(),
                category = category,
                level = data[3].toInt()
            )
            
            team.calcObjective()
            
            category.addTeam(team)
            teams.add(team)
        }
        
        return teams
    }
    
    fun readDriversFile(teams: MutableList<Team>): MutableList<Driver> {
        var drivers = mutableListOf<Driver>()
        
        val arquive = File(driversFile)
        
        arquive.forEachLine { line -> 
            var data = line.split(";")
            
            var team = teams.find{it.id == data[6].toInt()} ?: teams[0]
            
            var driver = Driver(
                id = data[0].toInt(),
                name = data[1],
                defense = data[2].toInt(),
                consistency = data[3].toInt(),
                overtake = data[4].toInt(),
                control = data[5].toInt(),
                team = team
            )
            
            team.addDriver(driver)
            drivers.add(driver)
        }
        
        return drivers
    }
}