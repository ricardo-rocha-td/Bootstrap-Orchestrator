package com.example.demo.model

data class ConfigInfo(val id: Int, val initialStep: Int, val steps : Map<Int,Step>){

    override fun toString() : String {
        var r = "id : $id, \n" +
                "initialStep : $initialStep, \n"

        steps.forEach { (_,s) -> r += "id : $id, $s \n" }

        return r
    }


}

data class Step(val name: String, val transaction : Int?){

    override fun toString(): String {
        var r =  "name : $name, "

        if (transaction != null)
            r += "transaction : $transaction"

        return r
    }
}