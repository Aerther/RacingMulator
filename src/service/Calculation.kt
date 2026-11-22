package service

import models.*
import enums.*

class Calculation() {
    fun startChampionships(
        champs: MutableList<Championship>, 
        tracks: MutableList<Track>, 
        teams: MutableList<Team>, 
        drivers: MutableList<Driver>) {
        
        for(champ in champs) {
            champ.start(tracks, teams, drivers)
        }
    }
    
    fun simulateCurrentRaces(championships: MutableList<Championship>, currentChamp: Championship) {
        var champs = championships.filter { it != currentChamp }.toMutableList()
        
        for(champ in champs) {
            champ.simulateCurrentRace()
        }
    }
    
    fun getStartingChampionships(categories: MutableList<Category>): MutableList<Championship> {
        var champs: MutableList<Championship> = mutableListOf()
        
        var index = 1
        for(category in categories) {
            champs.add(Championship(id = index, category = category))
            
            index++
        }
        
        return champs
    }
    
    fun getStarterOffers(teams: MutableList<Team>, starterCategory: TypeCategory): MutableList<Offer> {
        var offers: MutableList<Offer> = mutableListOf()
        
        var formulaTeams = teams.filter { it.category.categoryType == starterCategory}
        
        var max: Int = formulaTeams.size
        var min = max * 9/10
        
        var percentageToReceiveOffer = 100
        
        while(max >= min) {
            var team = formulaTeams.find {it.level == max}
            
            if(team == null) {
                max--
                continue
            }
            
            var num = (0..99).random()
            
            if(num < percentageToReceiveOffer) {
                var offer = Offer(
                    max,
                    team,
                    team.objective
                )
                
                offers.add(offer)
            }
            
            percentageToReceiveOffer = percentageToReceiveOffer/2
            max--
        }
        
        return offers
    }
    
    fun getOffers(champ: Championship, player: Driver): MutableList<Offer> {
        var offers: MutableList<Offer> = mutableListOf<Offer>()
        
        if(champ.races.size * 80/100 > champ.currentRaceIndex + 1) return mutableListOf()
        
        var index = 1
        for(team in champ.teams) {
            if(team == player.team) continue
            
            if(team.objective >= player.position) {
                var offer = Offer(
                    id = index,
                    team = team,
                    objective = team.objective
                )
                
                offers.add(offer)
            }
            
            index++
        }
        
        offers.add(Offer(id = index, team = player.team, objective = player.team.objective))
        
        return offers
    }
}