package com.example.broadcast_server.data


data class Client(
    var clientPackageName: String?,
    var clientProcessId: String,
    var clientData: String?,
    var ipcMethod: String
)

object RecentClient {
    var client: Client? = null
}
