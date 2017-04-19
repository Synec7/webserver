package response

import header.HeaderBuilder
import header.listHeaders
import java.security.MessageDigest
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by Vincente A. Campisi on 10/04/17.
 */
class BinaryResponse(requestBody: ByteArray?) : Response {
    override val responseText = StringBuilder()
    override val headers = HeaderBuilder()
    override val httpVersion = "HTTP/1.1"
    override val responseBody: ByteArray = calculateHash(requestBody).toByteArray(Charsets.UTF_8)

    init {
        this.responseText.append("$httpVersion ${StatusCode.OK.code} ${StatusCode.OK.description}\r\n")
        headers.addHeader("Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}")
        headers.addHeader("Connection:close")
        headers.addHeader("Content-Length:${this.responseBody.size}")
        headers.addHeader("Content-Type:text/plain;charset=utf-8")
        this.responseText.append(headers.getHeaderReader().listHeaders())
    }

    override fun getResponseText(): String = this.responseText.toString()

    private fun calculateHash(body: ByteArray?) =
            if (body != null && body.size > 0) byteArrayToHex(MessageDigest.getInstance("MD5").digest(body))
            else throw IllegalArgumentException("Null request body.")

    private fun byteArrayToHex(a: ByteArray): String {
        val sb = StringBuilder(a.size * 2)
        for (b in a)
            sb.append(String.format("%02x", b))
        return sb.toString()
    }

}