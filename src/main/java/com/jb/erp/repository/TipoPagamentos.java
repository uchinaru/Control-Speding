package com.jb.erp.repository;

public enum TipoPagamentos {
	
	Debito("Pagamento via Debito"),
	Crédito("Pagamento via Crédito"),
	Pix("Pagamento via Pix"),
	VA("Pagamento via Vale Alimentação"),
	VR("Pagamento via Vale Refeição");

	private String descricao;

	TipoPagamentos(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	

}
