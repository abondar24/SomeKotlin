package basics

class Intern(name: String, age: Int) : Employee(name, age) {
    init {
        println(name)
        println(age)
    }


    override fun work() {
        println("I am intern")
        super.work()
    }

    override fun toString(): String {
        return "Intern()"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }


}
