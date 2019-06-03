package com.example.demo.database

import com.example.demo.model.BasicUser
import org.springframework.data.repository.CrudRepository

interface Repository : CrudRepository<BasicUser, String> {

}

