package enums

enum class TypeCategory(val difficulty: Int, val pointsPerRace: Int) {
    FORMULA3(1, 5),
    FORMULA2(2, 2),
    FORMULA1(4, 1);
    
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