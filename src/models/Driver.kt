package models

import models.Result

open class Driver(
    val id: Int,
    var name: String,
    var defense: Int = 0,
    var consistency: Int = 0,
    var overtake: Int = 0,
    var control: Int = 0,
    var team: Team,
    var pointsScored: Int = 0
) {

}