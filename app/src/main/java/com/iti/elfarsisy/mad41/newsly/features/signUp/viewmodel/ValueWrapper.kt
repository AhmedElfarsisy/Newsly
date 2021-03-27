package com.iti.elfarsisy.mad41.newsly.features.signUp.viewmodel

class ValueWrapper<T>(private val value: T) {

    private var isConsumed = false

    fun getContent():T? {

        return if(isConsumed){
            null
        } else {
            isConsumed = true
            value

        }
    }
}