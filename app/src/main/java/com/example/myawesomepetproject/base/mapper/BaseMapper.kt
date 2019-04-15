package com.example.myawesomepetproject.base.mapper

abstract class BaseMapper<From, To> {
  
  abstract fun map(from: From): To
  
  fun map(froms: List<From>): List<To> {
    val result = ArrayList<To>(froms.size)
    for (from in froms) {
      result.add(map(from))
    }
    return result
  }
}