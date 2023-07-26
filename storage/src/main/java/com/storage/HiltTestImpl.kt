package com.storage

import com.domain.HiltTestInterface

class HiltTestImpl: HiltTestInterface {
    override fun getString(): String {
        return "Storage Module"
    }
}