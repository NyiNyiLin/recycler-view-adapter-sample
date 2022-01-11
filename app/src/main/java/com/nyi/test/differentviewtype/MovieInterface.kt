package com.nyi.test.differentviewtype

interface MovieInterface

data class MovieDetail (
    val id : Int,
    val name : String
) : MovieInterface

data class MovieType (
    val id : Int,
    val type : String,
) : MovieInterface