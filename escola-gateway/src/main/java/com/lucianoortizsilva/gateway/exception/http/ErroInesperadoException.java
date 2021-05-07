package com.lucianoortizsilva.gateway.exception.http;

/**
 * 
 * Utilizada para erros de inesperados.<br><br>
 * 
 * Exemplo:<br><br>
 * Qualquer erro que que não esperamos NullPointer, Memória, TimeOut ...<br><br> 
 * 
 * <strong><code>HTTP 500</code></strong>
 *
 */
public class ErroInesperadoException extends RuntimeException {

	private static final long serialVersionUID = 3425947584658491L;

	public ErroInesperadoException() {
		super("Não foi possível concluir a operação, erro inesperado");
	}

}