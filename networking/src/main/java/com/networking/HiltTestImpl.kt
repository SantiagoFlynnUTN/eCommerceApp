package com.networking

import com.domain.HiltTestInterface

class HiltTestImpl : HiltTestInterface {
    override fun getString(): String {
        return "Network Module"
    }
}
