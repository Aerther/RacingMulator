package models

import models.Result

open class Driver(
    val id: Int,
    var name: String,
    var team: Team,
    var defense: Int = 0,
    var consistency: Int = 0,
    var overtake: Int = 0,
    var control: Int = 0,
    var pointsScored: Int = 0,
    var updatePointsLeft: Int = 10
) {

}