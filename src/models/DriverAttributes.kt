package models

data class DriverAttributes(
    var defense: Int,
    var consistency: Int,
    var overtake: Int,
    var control: Int
) {
    fun getAverage(): Double {
        return (defense + consistency + overtake + control)/4.0
    }
    
    fun dividePoints(divisionBy: Int = 3) {
        defense = defense / divisionBy
        consistency = consistency / divisionBy
        overtake = overtake / divisionBy
        control = control / divisionBy
    }
}