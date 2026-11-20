package service

import models.*
import enums.*

class Calculation() {
    fun getStarterOffers(teams: MutableList<Team>): MutableList<Offer> {
        var offers: MutableList<Offer> = mutableListOf()
        
        var formula3Teams = teams.filter { it.category.categoryType == TypeCategory.FORMULA1}
        
        var max: Int = formula3Teams.size
        var min = max * 9/10
        
        var percentageToReceiveOffer = 100
        
        while(max >= min) {
            var team = formula3Teams.find {it.level == max}
            
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