package br.com.jogosusados.network

import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

interface Requestable {
    val callbackNetworkRequest: CallbackNetworkRequest?
}