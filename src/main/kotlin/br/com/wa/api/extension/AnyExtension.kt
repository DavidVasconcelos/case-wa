package br.com.wa.api.extension

import java.util.Optional

fun <T> Optional<T>.unwrap(): T? = orElse(null)
