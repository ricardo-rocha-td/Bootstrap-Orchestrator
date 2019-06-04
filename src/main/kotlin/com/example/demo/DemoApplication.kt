package com.example.demo

import com.example.demo.model.Configuration
import com.example.demo.model.Step
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.concurrent.Executors

@SpringBootApplication
class DemoApplication



fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)

	ConfigExec()
}

class ConfigExec{

	private val list = mutableListOf<Configuration>()

	init {
		loadConfig()
		//runConfigs()
		runConfigsWithThread()
	}

	/**
	 *  STEP 2
	 *
	 */

	private fun loadConfig(){
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

	private fun runConfigs(){
		println("\n \n[RUNNING CONFIGS] \n ")

		list.forEach { c : Configuration -> firstStep(c) }
	}

	private fun firstStep(c : Configuration){
		println("Running Config: ${c.id} \nInitial Step: ${c.initialStep}")
		nextStep(c.initialStep,c.steps)
	}

	private fun nextStep(step : Int, map: Map<Int,Step>){
		val s = map.get(step)
		if (s != null){
			val log = "Running step: $step, Action: ${s.name}"

			if (s.transaction == null) {
				println(log)
				println("No more steps \n")
			}
			else {
				println(log + ", Next: ${s.transaction}")
				nextStep(s.transaction,map)
			}
		}
	}

	/**
	 *  STEP 2 - with thread pool
	 *
	 */

	private fun runConfigsWithThread(){
		println("\n \n[RUNNING CONFIGS] \n ")

		val executor = Executors.newFixedThreadPool(5)
		for (c in list) {
			val worker = Runnable {
				println("Thread " + Thread.currentThread().id )
				firstStep(c)
			}
			executor.execute(worker)
		}

		executor.shutdown()
		while (!executor.isTerminated) {
		}
		println("Finished all threads")
	}

}

