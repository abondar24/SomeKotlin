package basics

data class Math(val value: Int){

    operator fun plus(vl: Int) : Math{
        return Math(value+vl+1)
    }

    operator fun inc() : Math{
        return Math(value+100)
    }
}
