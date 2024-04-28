package com.jb.erp.repository;

public enum TipoPagamentos {
	
	Debito("Pagamento via Debito"),
	Cr�dito("Pagamento via Cr�dito"),
	Pix("Pagamento via Pix"),
	VA("Pagamento via Vale Alimenta��o"),
	VR("Pagamento via Vale Refei��o");

	private String descricao;

	TipoPagamentos(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	

}
