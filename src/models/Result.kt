package models

class Result(
    val id: Int,
    val position: Int,
    val driver: Driver,
    var points: Int = 0
) {
    
}