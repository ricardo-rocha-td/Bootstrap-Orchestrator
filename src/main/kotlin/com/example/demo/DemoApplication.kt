package com.example.demo

import com.example.demo.model.Configuration
import com.example.demo.model.Step
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication



fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)

	ConfigExec()
}

class ConfigExec(){

	val list = mutableListOf<Configuration>()

	init {
		loadConfig()
		runConfigs()
	}

	/**
	 *  STEP 2
	 *
	 */

	fun loadConfig(){
		println("Loading hard coded configs \n")

		var steps = mutableMapOf<Int,Step>()
		steps.put(1,Step("inviteAgent",2))
		steps.put(2,Step("connectAgent",3))
		steps.put(3,Step("conversationWithAgent",null))
		list.add(Configuration(1,1,steps))

		steps = mutableMapOf()
		steps.put(1,Step("connectAgent",2))
		steps.put(2,Step("conversationWithAgent",null))
		list.add(Configuration(2,1,steps))

		list.forEach{ c:Configuration -> println(c)}
	}

	fun runConfigs(){
		println("\n \n[RUNNING CONFIGS] \n ")

		list.forEach {
			c : Configuration ->

			println("Running Config: ${c.id} \nInitial Step: ${c.initialStep}")
			nextStep(c.initialStep,c.steps)
		}
	}

	fun nextStep(step : Int, map: Map<Int,Step>){
		val s = map.get(step)
		if (s != null){
			var log = "Running step: $step, Action: ${s.name}"

			if (s.transaction == null) {
				println(log)
				println("No more steps \n")
			}
			else {
				println(log + ", Next: ${s?.transaction}")
				nextStep(s.transaction,map)
			}

		}

	}

}

