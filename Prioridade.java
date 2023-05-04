public enum Prioridade {
    /*
    Níveis de Prioridade do Chamado
    - Mínima: deve ser usado para chamados que podem ser adiados ou resolvidos em um momento posterior
    - Baixa: deve ser usado para chamados que precisam ser resolvidos, mas sem muita importância
    - Média: deve ser usado para chamados que precisam ser resolvidos e certa relevância
    - Alta: deve ser usado para chamados muito importantes, mas não urgentes
    - Urgente: deve ser usado para chamados em que o atendimento imediato é necessário
     */
    MINIMA, BAIXA, MEDIA, ALTA, URGENTE
}
