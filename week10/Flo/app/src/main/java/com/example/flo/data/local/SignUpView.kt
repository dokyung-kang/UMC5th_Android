package com.example.flo.data.local

import com.example.flo.data.remote.AuthResponse

interface SignUpView {
    fun onSignUpSuccess()
    fun onSignUpFailure(resp: AuthResponse)
}