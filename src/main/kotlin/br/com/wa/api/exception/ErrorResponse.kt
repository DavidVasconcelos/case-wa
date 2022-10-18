package br.com.wa.api.exception

data class ErrorResponse(val mensagem: String, val erros: List<Error>)

data class Error(val codigo: Int, val mensagem: String)
