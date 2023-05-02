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


}
