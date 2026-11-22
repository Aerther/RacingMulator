package service

import models.*

class Input() {
    fun getPlayerName(): String {
        println("What is your name?")
        print("Name: ")
        
        var input = readln()
        
        return input
    }
    
    fun getChamp(champs: MutableList<Championship>): Championship {
        var champNumber = this.getOption()
        
        var champ = champs.find { it.id == champNumber } ?: champs[0]
        
        return champ
    }
    
    fun getOffer(offers: MutableList<Offer>): Team {
        var offerNumber = this.getOption()
    
        var offerChosen = offers.find {it.id == offerNumber} ?: offers[0]
        var team = offerChosen.team
        
        return team
    }
    
    fun getOption(): Int {
        println("Choose a number of the list")
        
        print("Choice: ")
        var input = readln().toIntOrNull()
        while(input == null) {
            println("Invalid input, please choose a number")
            
            print("Choice: ")
            input = readln().toIntOrNull()
        }
        
        return input
    }
    
    fun getOptionToUpdateStats(): String {
        print("Do you wish to update your points? (y/n) ")
        var input = readln()
        
        return input
    }
}