package com.baseapp

import com.domain.HiltTestInterface

class HiltTestClass(
    private val str: String
): HiltTestInterface {
    override fun getString(): String {
        return str
    }
}