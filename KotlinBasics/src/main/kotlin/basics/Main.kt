package basics


fun main(args: Array<String>) {

    if (args.isEmpty()) {
        println("Enter argument")
    } else {

        when (args[0]) {
            Args.HW.nm -> {
                println("Hello World!")
            }

            Args.MAX.nm -> {
                fun max(a: Int, b: Int): Int {
                    return if (a > b) a else b
                }

                val maxVal = max(7, 10)
                println("Max of 7 and 10 is $maxVal")


            }

            Args.CL.nm -> {
                val cl = Person("Alex", 0)
                cl.age = 21
                println(cl.name)
                println(cl.age)
                println("Old: ${cl.isOld}")
                println(cl is Human)
            }

            Args.RN.nm -> {
                val range = 1..10
                for (i in range){
                    println("Range: $i")
                }

                for (i in 20 downTo 2 step 2){
                    println(i)
                }

                println(17 in range)
            }

        }

    }

}
