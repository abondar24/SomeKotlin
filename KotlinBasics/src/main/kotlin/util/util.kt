package util

import java.lang.StringBuilder

fun<T> Collection<T>.joinToString( separator: String = ":",
                    prefix: String, postfix:String) : String{

    val res = StringBuilder(prefix)
    for ((index,elem) in withIndex()){
        if (index>0) res.append(separator)
        res.append(elem)
    }

    res.append(postfix)
    return res.toString()
}

fun<T> Collection<T>.joinToString( separator: String = ":",
                                prefix: String="", postfix:String="",
                                tranfsorm: ((T) -> String)? = null ) : String{

    val res = StringBuilder(prefix)
    for ((index,elem) in withIndex()){
        if (index>0) res.append(separator)
        val str = tranfsorm?.invoke(elem) ?: elem.toString()
        res.append(str)
    }

    res.append(postfix)
    return res.toString()
}

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}


fun parsePath(path: String){
  val regex = """(.+)/(.+)\.(.+)""".toRegex()
  val match = regex.matchEntire(path)

  if (match!=null){
      val (dir,file,ext) = match.destructured
      println("Dir: $dir, File: $file, Ext: $ext")
  }
}

fun String.lastChar(): Char = get(this.length-1)
val String.lastToLast: Char get() = get(this.length-2)

fun alphabet() = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    toString()
}

fun reverseAlphabet() = StringBuilder().apply {
    for (letter in 'Z'..'A') {
        append(letter)
    }

}.toString()


fun strUpper( s:String?): String{
    return s?.toUpperCase() ?: ""
}

fun strLen(s:String?): Int{
    val nonNull = s!!

    return nonNull.length
}


fun String?.isNullOrBlank(): Boolean = this==null || this.isBlank()

fun fiveAndSix(operation: (Int,Int)->Int){
    val res = operation(5,6)
    println(res)
}

fun String.filter(predicate: (Char)->Boolean):String{
   val sb = StringBuilder()
   for (index in 0 until length){
       val elem = get(index)
       if (predicate(elem)) sb.append(elem)
   }

    return sb.toString()
}

fun doubleFunction(str: String): (String)-> String{
    str.plus("sometest")

    return {res -> str.toUpperCase(); }
}
