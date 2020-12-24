package basics

fun main(args: Array<String>) {

    if (args.isEmpty()){
        println("Enter argument")
    } else {
        if (args[0] == "hw"){
            println("Hello World!")
        }

        if (args[0] == "max"){
            println("Max of 7 and 10 is "+ max(7,10))
        }
    }

}

fun max(a: Int, b: Int): Int {
    return if (a>b) a else b
}
