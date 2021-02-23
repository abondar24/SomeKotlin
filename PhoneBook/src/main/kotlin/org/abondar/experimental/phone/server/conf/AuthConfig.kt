package org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.conf

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

object AuthConfig {

    const val secret = "some-secret"
    const val validTime = 36_000_00 * 24
    const val jwtHeader = "authorization"
    const val loginPath = "/login"


    var jwtIssuer = ""
    var jwtAudience = ""
    var jwtRealm = ""

    val alg: Algorithm = Algorithm.HMAC512(secret)

    val verifier: JWTVerifier = JWT
        .require(alg)
        .withIssuer(jwtIssuer)
        .withAudience(jwtAudience)
        .build()
}
