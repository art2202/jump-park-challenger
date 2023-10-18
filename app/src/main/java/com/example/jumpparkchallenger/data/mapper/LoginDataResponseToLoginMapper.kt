package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.models.LoginDataResponse
import com.example.jumpparkchallenger.domain.entities.Establishment
import com.example.jumpparkchallenger.domain.entities.Login
import com.example.jumpparkchallenger.domain.entities.Session
import com.example.jumpparkchallenger.domain.entities.User

class LoginDataResponseToLoginMapper : Mapper<LoginDataResponse, Login> {
    override fun map(input: LoginDataResponse): Login {
        val user = User(
            input.userDataResponse?.id ?: 0,
            input.userDataResponse?.name ?: "",
            input.userDataResponse?.email ?: "",
            input.userDataResponse?.accessToken ?: ""
            )
        val session = Session(
            input.sessionDataResponse?.id ?: 0,
            input.sessionDataResponse?.establishmentId ?: 0
            )
        val establishment = Establishment(
            input.establishmentDataResponse?.id ?: 0,
            input.establishmentDataResponse?.name ?: "",
            input.establishmentDataResponse?.vacanciesMarks ?: 0,
        )

        return Login(user, session, establishment)
    }
}