import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private String nome;
    private ArrayList<Funcionario> funcionarios;
    private ArrayList<Equipamento> equipamentos;

    public Departamento(String nome, ArrayList<Funcionario> funcionarios) {
        this.nome = nome;
        this.funcionarios = funcionarios;
        
        this.equipamentos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    //metodo redundante para ficar mais simples a impressão do nome no departamento dentro do toString de funcionário
    public String toString() {
        return nome;
    }

    //retorna o array de equipamentos
    public ArrayList<Equipamento> getEquipamentos(){
        return equipamentos;
    }

    //adiciona um equipamento
    public boolean addEquipamento(Equipamento e){
        return equipamentos.add(e);
    }
    
}