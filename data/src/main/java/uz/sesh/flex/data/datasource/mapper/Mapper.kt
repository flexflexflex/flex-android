package uz.sesh.flex.data.datasource.mapper

import java.util.ArrayList

abstract class Mapper<T1, T2> {

    abstract fun map(value: T1): T2

    abstract fun reverseMap(value: T2): T1

    fun map(values: ArrayList<T1>): ArrayList<T2> {
        val returnValues = ArrayList<T2>(values.size)
        for (value in values) {
            returnValues.add(map(value))
        }
        return returnValues
    }

    fun reverseMap(values: ArrayList<T2>): ArrayList<T1> {
        val returnValues = ArrayList<T1>(values.size)
        for (value in values) {
            returnValues.add(reverseMap(value))
        }
        return returnValues
    }
}