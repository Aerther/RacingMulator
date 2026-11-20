package service

class Input() {
    fun getPlayerName(): String {
        println("What is your name?")
        print("Name: ")
        
        var input = readln()
        
        return input
    }
    
    fun getOffer(): Int {
        println("Choose an offer number of the list")
        
        print("Choice: ")
        var input = readln().toIntOrNull()
        while(input == null) {
            println("Invalid input, please choose a number")
            
            print("Choice: ")
            input = readln().toIntOrNull()
        }
        
        return input
    }
    
    fun getOption(): Int {
        println("Choose a number of the list")
        
        print("Choice: ")
        var input = readln().toIntOrNull()
        while(input == null) {
            println("Invalid input, please choose a number")
            
            print("Choice: ")
            input = readln().toIntOrNull()
        }
        
        return input
    }
    
    fun getOptionToUpdateStats(): String {
        print("Do you wish to update your points? (yes/no) ")
        var input = readln()
        
        return input
    }
}