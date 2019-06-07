package com.example.demo.model

data class ConfigInfo(val id: Int, val initialStep: Int, val steps : Map<Int,Step>){

    override fun toString() : String {
        var output = "id : $id, \n" +
                "initialStep : $initialStep, \n"

        steps.forEach { (_,next) -> output += "id : $id, $next \n" }

        return output
    }


}

data class Step(val name: String, val transaction : Int?){

    override fun toString(): String {
        var output =  "name : $name, "

        if (transaction != null)
            output += "transaction : $transaction"

        return output
    }
}