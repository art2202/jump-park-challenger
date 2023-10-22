package com.example.jumpparkchallenger.data.mapper

import com.example.jumpparkchallenger.data.models.login.LoginDataResponse
import com.example.jumpparkchallenger.domain.entities.Establishment
import com.example.jumpparkchallenger.domain.entities.LoginInfos
import com.example.jumpparkchallenger.domain.entities.Session
import com.example.jumpparkchallenger.domain.entities.User

class LoginDataResponseToHomeInfosMapper : Mapper<LoginDataResponse, LoginInfos> {
    override fun map(input: LoginDataResponse): LoginInfos {
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
            input.establishmentDataResponse[0]?.id ?: 0,
            input.establishmentDataResponse[0]?.name ?: "",
            input.establishmentDataResponse[0]?.vacanciesMarks ?: 0,
        )

        return LoginInfos(user, session, establishment)
    }
}