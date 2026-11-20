package service

import models.*
import enums.*

class Calculation() {
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
                    team.level * 18 / 10
                )
                
                offers.add(offer)
            }
            
            percentageToReceiveOffer = percentageToReceiveOffer/2
            max--
        }
        
        return offers
    }
}