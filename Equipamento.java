import java.time.LocalDate;
import java.time.LocalDateTime;

public class Equipamento {
    private Long id;
    private String descricao;
    private LocalDate dataAquisicao;
    private Departamento departamento;

    public Equipamento(Long id, String descricao, LocalDate dataAquisicao, Departamento departamento) {
        this.id = id;
        this.descricao = descricao;
        this.dataAquisicao = dataAquisicao;
        this.departamento = departamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    private Chamado chamarSuporte(Funcionario solicitante, String descricao) {
        return new Chamado(this,descricao, solicitante, null, LocalDateTime.now(),
                Status.ABERTO, null, "", null);
    }

    @Override 
    public String toString() {
        return "Equipamento{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", dataAquisicao=" + dataAquisicao +
                ", departamento=" + departamento +
                '}';
    }
}
