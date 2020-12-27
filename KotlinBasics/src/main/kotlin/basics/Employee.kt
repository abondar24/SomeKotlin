package basics

open class Employee : Human,Animal{

    val name: String

    var age: Int

    constructor(name: String){
        this.name =name
        this.age =0
    }

    constructor(name: String, age: Int){
        this.name = name
        this.age = age
    }


    val isOld: Boolean
        get(){
            return age>21
        }



    fun validate(employee: Employee, age: Int){
        fun validate(){
            if (age<employee.age){
                println("validation failed")
            }
        }

        validate()
    }


    override fun walk() {
        println("I can walk")
    }

    override fun breathe() {
        super<Animal>.breathe()
    }

    protected open fun work(){
        println("I work")
    }

    internal fun doWork(){
        work()
    }
}
