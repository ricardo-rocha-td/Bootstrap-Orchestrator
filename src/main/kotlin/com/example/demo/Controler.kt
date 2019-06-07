package com.example.demo

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserControler() {

    @PostMapping("/users/{id}")
    fun logUserID(@PathVariable id: Int) : ResponseEntity<Int> {
        println("POST of user with id $id")

        ConfigHandler().runConfigs()

        return ResponseEntity.ok().body(id)
    }

    
}