package enums

enum class TypeCategory(val level: Int, val difficulty: Int, val pointsPerRace: Int) {
    FORMULA3(3, 1, 5),
    FORMULA2(2, 2, 4),
    FORMULA1(1, 4, 3);
    
    companion object {
        fun getTypeCategoryBasedOnString(string: String): TypeCategory {
            return when(string) {
                "FORMULA1" -> FORMULA1
                "FORMULA2" -> FORMULA2
                "FORMULA3" -> FORMULA3
                else -> FORMULA3
            }
        }
    }
}