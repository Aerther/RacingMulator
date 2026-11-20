package enums

enum class TypeCategory {
    FORMULA3,
    FORMULA2,
    FORMULA1;
    
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