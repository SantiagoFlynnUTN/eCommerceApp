package com.networking.usecase

import com.domain.usecase.ICreateNewClientUseCase
import com.networking.api.Api
import com.networking.model.CardTokenDto
import com.networking.model.CustomerCardIssuer
import com.networking.model.PaymentMethodDto
import com.networking.model.TokenDto

class CreateClientUseCase(
    private val api: Api,
) : ICreateNewClientUseCase {

    override suspend fun invoke(): Unit? {

        //return api.getClient("1575260378-1bDRkOoAm9Ld5H").body()?.data
        //return api.postCard(CardTokenDto("4517660918819813","1575260378-1bDRkOoAm9Ld5H", "207")).body()?.data
        return api.saveCardToClient("1575260378-1bDRkOoAm9Ld5H",
            TokenDto("506262ed131394ac48574754812e9398",
                CustomerCardIssuer("25", "Visa"),
                "Visa",
                6,
                2029,

            )).body()?.data
    }
}
