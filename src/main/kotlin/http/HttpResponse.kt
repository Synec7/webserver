package http

import core.protocol.Response

/**u
 * Created by Vincente A. Campisi on 10/04/17.
 */
interface HttpResponse : Response {
    val responseText: StringBuilder
    val headers: Headers
    val httpVersion: String
    var responseBody: ByteArray?
}