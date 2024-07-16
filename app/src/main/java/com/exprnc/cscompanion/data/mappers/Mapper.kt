package com.exprnc.cscompanion.data.mappers

interface Mapper<From, To> {
    fun map(from: From): To
}