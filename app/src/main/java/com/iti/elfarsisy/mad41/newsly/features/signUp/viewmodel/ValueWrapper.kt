package com.iti.gov.weather.favorite.viewmodel

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