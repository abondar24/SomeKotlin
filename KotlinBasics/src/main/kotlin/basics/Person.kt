package basics

interface Human
class Person(val name: String,
             var age: Int): Human{
    val isOld: Boolean
        get(){
            return age>21
        }


    fun validate(person: Person,age: Int){
        fun validate(){
            if (age<person.age){
                println("validation failed")
            }
        }

        validate()
    }
}
