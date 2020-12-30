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

