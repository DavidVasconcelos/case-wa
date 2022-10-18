package br.com.wa.api.extension

import br.com.wa.api.domain.constant.BusinessConstants.Patterns.DATE_TIME_PATTERN
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.toStringPattern(): String =
    this.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN))
