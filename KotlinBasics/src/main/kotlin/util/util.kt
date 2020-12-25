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


fun String.lastChar(): Char = get(this.length-1)
val String.lastToLast: Char get() = get(this.length-2)
