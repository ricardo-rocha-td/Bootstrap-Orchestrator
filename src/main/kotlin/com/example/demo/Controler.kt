package com.example.demo

import com.example.demo.model.idWrapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Controler(){
    //private val repo: com.example.demo.database.Repository


    //get objects on repo
    //@GetMapping("/")
    //fun getAll() : ResponseEntity<Any> {
    //    return ResponseEntity.ok().body(repo.findAll())
    //}

    //as json
    @PostMapping("/ID/")
    fun logID(@RequestBody id: idWrapper ) : ResponseEntity<Any> {
        println("POST of user with id ${id.id}")
        return ResponseEntity.ok().body(id);
    }

    // with path id
    @PostMapping("/ID/{id}")
    fun logID2(@PathVariable id: Int) : ResponseEntity<Any> {
        println("POST of user with id $id")
        return ResponseEntity.ok().body(id);
    }






}