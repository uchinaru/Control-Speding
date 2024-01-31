package com.jb.erp.model;

public enum TipoUsuario {
	
	PADRAO("Usuario basico"),
	PREMIUM("Usuario Premium"),
	ADM("Usuario Administrador"),
	SU("Super Usuario");
	
	private String descricao;

	private TipoUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
