import java.time.LocalDateTime;

public class Funcionario {
    private Long id;
    private String nome;
    private Departamento departamento;
    private boolean suporte;

    public Funcionario(Long id, String nome, Departamento departamento, boolean suporte) {
        this.id = id;
        this.nome = nome;
        this.departamento = departamento;
        this.suporte = suporte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public boolean isSuporte() {
        return suporte;
    }

    public void setSuporte(boolean suporte) {
        this.suporte = suporte;
    }


    /**
     * abre um novo chamado e o adiciona à lista de chamados
     * @param equipamento equipamento do chamado
     * @param descricao descrição do chamado
     * @param prioridade prioridade do chamado
     */
    public void abrirChamado(Equipamento equipamento, String descricao, Prioridade prioridade) {
        Chamado chamado = new Chamado(equipamento, descricao, this,
                null, LocalDateTime.now(), Status.ABERTO, prioridade, null, equipamento.getDepartamento());
        ListaChamados.add(chamado);
    }

    @Override 
    //metodo para imprimir os elementos necessários para escolher o funcionario no menu
    public String toString() {
        return "Funcionario id:" + id + ", nome:" + nome + ", departamento:" + departamento;
    }
}
