package basics

interface Human
class Person(val name: String,
             var age: Int): Human{
    val isOld: Boolean
        get(){
            return age>21
        }
}
