package basics



import util.max as mx
import util.lastChar
import util.lastToLast
import util.parsePath

fun main(args: Array<String>) {

    if (args.isEmpty()) {
        println("Enter argument")
    } else {

        when (args[0]) {
            Args.HW.nm -> {
                println("Hello World!")
            }

            Args.MAX.nm -> {
                val maxVal = mx(7, 10)
                println("Max of 7 and 10 is $maxVal")


            }

            Args.CL.nm -> {
                val cl = Person("Alex", 0)
                cl.age = 21
                println(cl.name)
                println(cl.age)
                println("Old: ${cl.isOld}")
                println(cl is Human)
                cl.validate(cl,7)
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
                println("Kotlin" in "Java".."Scala")
                println("Kotlin" in setOf("Java","Scala"))

            }

            Args.COL.nm ->{
                val st = hashSetOf(1,2,15,6)
                println("Hash Set $st")
                println("Max in set is ${st.max()}")

                val lst = arrayListOf(1,2,5,6)
                println("Array list $lst")
                println("Last in array list is ${lst.last()}")


                println("Join to string ${lst.joinToString(prefix = "{",postfix = "}")}")

                val mp = hashMapOf("One" to 1,"two" to 2)
                println("Hash map $mp")


            }

            Args.EF.nm -> {
                println("Last char of Kotlin is ${"Kotlin".lastChar()}")
                println("Last before last of Kotlin is ${"Kotlin".lastToLast}")
            }

            Args.RE.nm -> {
                println("192.168.1.1".split("\\.".toRegex()))

                val path = "/home/abondar/demo.tf"
                println("Path: $path")
                parsePath(path)

            }

        }

    }

}
