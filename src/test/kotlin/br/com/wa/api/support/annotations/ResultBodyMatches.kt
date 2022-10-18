package br.com.wa.api.support.annotations

import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.springframework.test.web.servlet.ResultActions

fun ResultActions.andResultBodyMatches(json: String): ResultActions =
    this.andDo { mvcResult ->
        val content = mvcResult.response.contentAsString
        JSONAssert.assertEquals(json, content, JSONCompareMode.LENIENT)
    }
