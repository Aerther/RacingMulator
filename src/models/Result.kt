package models

class Result(
    val id: Int,
    var position: Int,
    var driver: Driver,
    var points: Int = 0,
    var timeToComplete: Double = 0.0
) {
    
}