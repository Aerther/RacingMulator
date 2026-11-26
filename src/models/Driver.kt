package models

import models.Result

open class Driver(
    val id: Int,
    var name: String,
    var team: Team,
    var attributes: DriverAttributes = DriverAttributes(0, 0, 0, 0),
    var pointsScored: Int = 0,
    var updatePointsLeft: Int = 10,
    var position: Int = 0,
    var highestPosition: Int = 100,
    var isFirstSeason: Boolean = false,
    var isPlayer: Boolean = false
) {

}