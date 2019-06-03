package com.example.demo

import com.example.demo.model.BasicUser
import com.example.demo.model.idWrapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Controler(private val repo: com.example.demo.database.Repository){

    @GetMapping("/")
    fun getAll() : ResponseEntity<Any> {
        return ResponseEntity.ok().body(repo.findAll())
    }


    @PostMapping("/")
    fun addUser(@RequestBody user: BasicUser ) : ResponseEntity<Any> {
        val s = repo.save(user)
        println("POST of user with id ${s.id}")
        return ResponseEntity.ok().body(s);
    }

    @PostMapping("/ID/")
    fun logID(@RequestBody id: idWrapper ) : ResponseEntity<Any> {
        println("POST of user with id ${id.id}")
        return ResponseEntity.ok().body(id);
    }

    /**
     *
    @PostMapping("/")
    fun addUser(@RequestBody id: Int) : ResponseEntity<Any> {
    println("Received id $id")
    return ResponseEntity.ok().build();
    }
     */

}