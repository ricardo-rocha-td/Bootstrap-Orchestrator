package com.example.demo.database

import org.springframework.data.repository.CrudRepository

interface Repository : CrudRepository<com.example.demo.model.Configuration, String> {

}

