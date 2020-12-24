package basics

class Person(val name: String,
             var age: Int){
    val isOld: Boolean
        get(){
            return age>21
        }
}


fun main(args: Array<String>) {

    if (args.isEmpty()){
        println("Enter argument")
    } else {
        if (args[0] == "hw"){
            println("Hello World!")
        }

        if (args[0] == "max"){

            val maxVal = max(7,10)
            println("Max of 7 and 10 is $maxVal")
        }

        if (args[0]=="cl"){
            var cl = Person("Alex",0)

            cl.age = 21
            println(cl.name)
            println(cl.age)
            println("Old: ${cl.isOld}")
        }
    }

}

fun max(a: Int, b: Int): Int {
    return if (a>b) a else b
}
