package com.example.jumpparkchallenger.data.mapper

interface Mapper<I, O> {

    fun map(input : I) : O
}