package com.studyflow.config;

import org.springframework.stereotype.Component;

@Component
public class ConfiguracaoGlobalSistema {

    private final String statusInicialTarefa = "PENDENTE";
    private final String ordenacaoPadraoTarefa = "prazo";

    public String getStatusInicialTarefa() {
        return statusInicialTarefa;
    }

    public String getOrdenacaoPadraoTarefa() {
        return ordenacaoPadraoTarefa;
    }
}
