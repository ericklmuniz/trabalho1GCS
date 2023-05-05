import java.time.LocalDate;
import java.time.LocalDateTime;

public class Chamado {
    private Equipamento equipamento;
    private String descricao;
    private Funcionario requisitante;
    private Funcionario responsavel;
    private String textoResolucao;
    private LocalDateTime dataSolicitacao;
    private Status status;
    private Prioridade prioridade;
    private Departamento setor;

    public Chamado(Equipamento equipamento, String descricao, Funcionario requisitante,
                   Funcionario responsavel, LocalDateTime dataSolicitacao, Status status, Prioridade prioridade, String textoResolucao, Departamento setor) {
        this.equipamento = equipamento;
        this.descricao = descricao;
        this.requisitante = requisitante;
        this.responsavel = responsavel;
        this.dataSolicitacao = dataSolicitacao;
        this.status = status;
        this.prioridade = prioridade; // inicializa com a prioridade escolhida pelo usuário
        this.textoResolucao = textoResolucao;
        this.setor = setor;
    }

    public String getTextoResolucao() {
        return textoResolucao;
    }

    public void setTextoResolucao(String textoResolucao) {
        this.textoResolucao = textoResolucao;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public Funcionario getRequisitante() {
        return requisitante;
    }

    public void setRequisitante(Funcionario requisitante) {
        this.requisitante = requisitante;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public Status getStatus() {
        return status;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }


    /**
     * Atualiza o status de um chamado respeitando a regra imposta do trabalho:
     * aberto -> em andamento -> concluído
     * se chamado está concluído, então é adicionado a lista de chamados
     * @param func funcionário atual querendo atualizar o status do chamado
     * @return se o status foi atualiado ou não
     */
    public boolean atualizaStatus(Funcionario func) {
        if (!func.isSuporte()) // se não for do suporte, não pode atualizar
            return false;

        Status statAtual = getStatus();
        if (statAtual == Status.ABERTO) {
            setStatus(Status.EM_ANDAMENTO);
            return true;
        } else if (statAtual == Status.EM_ANDAMENTO) {
            setStatus(Status.CONCLUIDO);
            ListaChamados.add(this);
            return true;
        } // caso queira mudar de CONCLUIDO, não pode atualizar

        return false;
    }

    public Departamento getSetor() {
        return setor;
    }

    public void setSetor(Departamento setor) {
        this.setor = setor;
    }

    //Adicionei para printar chamados localizados
    public String toString() {
        return "\n\n" +
            "equipamento: '" + getEquipamento() + "'" +
            "\ndescricao: '" + getDescricao() + "'" +
            "\nrequisitante: '" + getRequisitante() + "'" +
            "\nresponsavel: '" + getResponsavel() + "'" +
            "\ntextoResolucao: '" + getTextoResolucao() + "'" +
            "\ndataSolicitacao: '" + getDataSolicitacao() + "'" +
            "\nprioridade: '" + getPrioridade() + "'" +
            "\nstatus: '" + getStatus() + "'" +
            "\nsetor: '" + getSetor() + "'";
    }
}
