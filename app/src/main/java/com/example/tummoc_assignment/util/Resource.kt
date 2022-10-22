package com.example.tummoc_assignment.util

class Resource<T>(
    var status: String,
    var data: T? = null,
    var errorMessage: String? = null
)