package basics

import java.beans.PropertyChangeSupport
import kotlin.reflect.KProperty

class NameProperty(var nameValue: String, private val changeSupport: PropertyChangeSupport) {
    operator fun getValue(e :Employee,prop: KProperty<*>): String = nameValue

    operator fun setValue(e : Employee,prop: KProperty<*>, newVal: String){

        val oldVal = nameValue
        nameValue = newVal
        changeSupport.firePropertyChange(prop.name,oldVal,newVal)
    }
}
