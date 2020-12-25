package basics

import java.lang.StringBuilder

fun<T> joinToString(collection: Collection<T>, separator: String = ":",
                    prefix: String, postfix:String) : String{

    val res = StringBuilder(prefix)
    for ((index,elem) in collection.withIndex()){
        if (index>0) res.append(separator)
        res.append(elem)
    }

    res.append(postfix)
    return res.toString()
}

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}
