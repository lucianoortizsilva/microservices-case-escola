package com.lucianoortizsilva.gateway.exception.http;

/**
 * 
 * <strong><code>HTTP 404</code></strong>
 *
 */
public class ObjetoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 98430534098509L;

	public ObjetoNaoEncontradoException(final String mensagem) {
		super(mensagem);
	}

}