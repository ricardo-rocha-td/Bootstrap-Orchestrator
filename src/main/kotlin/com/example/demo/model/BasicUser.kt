package com.example.demo.model

import org.springframework.data.annotation.Id

data class BasicUser(
        @Id
        val id : String? = null,
        val name : String
)