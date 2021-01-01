package basics

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

open class Employee(name: String, age: Int) : Human,Animal, PropertyChangeAware() {

    private val observer = {
        prop: KProperty<*>,oldVal: Int,newVal: Int -> changeSupport.firePropertyChange(prop.name,oldVal,newVal)
    }

    var name: String  by NameProperty(name,changeSupport)

    var age: Int by Delegates.observable(age,observer)


    constructor(name: String) : this(name,0) {
        this.name = name
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
