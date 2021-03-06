package app.http

import http.HttpStatusCode
import http.response.BadRequestHttpResponse
import org.junit.Assert.*

/**
* Created by Vincente A. Campisi on 12/04/17.
*/
class BadRequestHttpResponseTest {
    @org.junit.Test
    fun blankClass() {
        var response = "HTTP/1.1 ${HttpStatusCode.BAD_REQUEST.code} ${HttpStatusCode.BAD_REQUEST.description}\r\n"
        response += "Date:${java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME.format(java.time.ZonedDateTime.now(java.time.ZoneId.of("GMT")))}\r\n"
        response += "Connection:close\r\n"

        assertEquals(response, BadRequestHttpResponse().getResponseText())
    }

}