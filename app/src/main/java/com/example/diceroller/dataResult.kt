package com.example.diceroller

data class dataResult(var Numberstored: MutableList<Int>, var Numbers: Int)
{
    override fun toString(): String
    {
        var results : String = ""

        results += Numbers
        results += ": "

        for (item in Numberstored)
        {
            results += item
            results += " "
        }

        return results
    }
}