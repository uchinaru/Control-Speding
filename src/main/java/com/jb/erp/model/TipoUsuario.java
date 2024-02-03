package com.jb.erp.model;

public enum TipoUsuario {
	
	B("Usuario basico"),
	P("Usuario Premium"),
	A("Usuario Administrador"),
	S("Super Usuario");
	
	private String descricao;

	private TipoUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
