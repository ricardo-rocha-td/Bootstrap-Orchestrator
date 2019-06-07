package com.example.demo

import com.example.demo.model.ConfigInfo
import com.example.demo.model.Step
import java.util.concurrent.Executors


class ConfigHandler(){

    private val configList = listOf(
        ConfigInfo(
            1, 1, mapOf(
                1 to Step("inviteAgent", 2),
                2 to Step("connectAgent", 3),
                3 to Step("conversationWithAgent", null)
            )
        ),

        ConfigInfo(
            2, 1, mapOf(
                1 to Step("connectAgent", 2),
                2 to Step("conversationWithAgent", null)
            )
        )
    )

    public fun runConfigs(){
        println("\n \n[RUNNING CONFIGS] \n ")

        configList.forEach { c : ConfigInfo -> firstStep(c) }

        println("Finished running configs")
    }

    private fun firstStep(c : ConfigInfo){
        println("Running Config: ${c.id} \nInitial Step: ${c.initialStep}")
        nextStep(c.initialStep, c.steps)
    }

    private fun nextStep(step : Int, map: Map<Int, Step>){
        val currentStep = map.get(step)

        if (currentStep == null){ return }

        val log = "Running step: $step, Action: ${currentStep.name}"

        if (currentStep.transaction == null) {
            println(log)
            println("No more steps \n")
        } else {
            println(log + ", Next: ${currentStep.transaction}")
            nextStep(currentStep.transaction, map)
        }

    }

    public fun runConfigsWithThread(){
        println("\n \n[RUNNING CONFIGS] \n ")

        val executor = Executors.newFixedThreadPool(5)
        for (config in configList) {
            val worker = Runnable {
                println("Thread " + Thread.currentThread().id )
                firstStep(config)
            }
            executor.execute(worker)
        }

        executor.shutdown()
        while (!executor.isTerminated) {
        }
        println("Finished all threads")
    }

}


