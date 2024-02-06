package br.com.tech.springtech.domain.Enum;

import java.util.Arrays;
import java.util.List;

public enum PedidoStatus {
    NOVO("Novo"),
    PENDENTE("Pendente", NOVO),
    EM_ESPERA("Em espera", PENDENTE),
    ENVIADO("Enviado", EM_ESPERA),
    ENTREGUE("Entregue", ENVIADO),
    FINALIZADO("Finalizado", ENTREGUE);

    private String descricao;
	private List<PedidoStatus> statusAnteriores;
	
	PedidoStatus(String descricao, PedidoStatus... statusAnteriores) {
		this.descricao = descricao;
		this.statusAnteriores = Arrays.asList(statusAnteriores);
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public boolean naoPodeAlterarPara(PedidoStatus novoStatus) {
		return !novoStatus.statusAnteriores.contains(this);
	}


}
