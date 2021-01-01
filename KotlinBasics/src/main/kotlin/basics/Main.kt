package basics



import util.*
import kotlin.collections.joinToString
import util.max as mx

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
                val emp = Employee("Alex")
                emp.age=21
                println(emp.name)
                println(emp.age)
                println("Old: ${emp.isOld}")
                println(emp is Human)
                emp.validate(emp,7)

                emp.talk()
                emp.walk()
                emp.breathe()
                emp.name="Ronald"
                emp.age=31


                val intern = Intern("Rajesh", 20)
                val work = Work(intern)
                work.doWork()

                val work1 = Work(emp)
                work1.doWork()

                val cj = Job.ComplexJob()
                cj.doJob()

                val per = Person("Donald","Duck")
                println(per.toString())
                println(per.hashCode())
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

            Args.LM.nm -> {
                val interns = listOf(Intern("Arsen",19),
                    Intern("Suren",18),
                    Intern("Ali",20))

                println(interns.maxOf { it.age })
                println(interns.filter { it.name=="Arsen" })
                println(interns.map (Intern::name))

                val mult = {x: Int , y: Int -> x*y}
                println(mult(21,11))

                println(alphabet())
                println(reverseAlphabet())
            }

            Args.NULL.nm ->{
                println(strUpper("test"))
                println(strUpper(null))

                println(strLen("str"))
               // println(strLen(null))

                var s : String ? = "something"
                s?.let { println(it) }

                s = null
                s?.let { println("null") }

                println("string".isNullOrBlank())
                println("".isNullOrBlank())
                println(s.isNullOrBlank())
            }

            Args.OV.nm ->{
                val a = Math(1)

                var b = a+1;
                println(b.value)
                b +=2
                println(b.value)

                val c= ++b
                println(c.value)
            }

        }

    }

}
