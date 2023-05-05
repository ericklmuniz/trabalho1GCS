import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.AbstractDocument.BranchElement;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ListaDepartamentos{

    private LocalDate dataAquisicao;
    private ArrayList<Departamento> departamentos;

    public ListaDepartamentos(){
        departamentos = new ArrayList<>();
    }

    /*
     * Adiciona um novo departamento
     * Departamento d é o departamento que se deseja adicionar
     */
    public boolean addDepartamento(Departamento d){
        return departamentos.add(d);
    }

    //Adiciona um ou mais equipamentos a um departamento
    public void addEquipamentosAoDepartamento(Scanner sc){
        System.out.print("Digite o número de equipamentos que se deseja adicionar: ");
        int n = sc.nextInt();
        ArrayList<Equipamento> aux = new ArrayList<>();
        System.out.print("Digite o nome do departamento que se deseja adicionar um novo equipamento: ");
        String nomeDepartamento = sc.nextLine();
        Departamento d;
        sc.nextLine();
        int v = 0;
        while(v!=n){
            for(int i = 0; i<departamentos.size();i++){
                if(departamentos.get(i).getNome().equals(nomeDepartamento)){
                    d = departamentos.get(i);
                    System.out.print("Digite o id do novo equipamento: ");
                    Long id = sc.nextLong();
                    sc.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = sc.nextLine();
                    LocalDate dataAquisicao = null;
                    Equipamento e = new Equipamento(id, descricao, dataAquisicao, d);
                    aux.add(e);
                    v++;
                    d.addEquipamentos(aux);
                }
            }
        }
    }

    public void opcaoMoveEquipamento(Scanner sc){
        System.out.print("Digite o id do equipamento: ");
        Long id = sc.nextLong();
        sc.nextLine();
        System.out.print("Digite o nome do departamento que você deseja mover o equipamento: ");
        String nome = sc.nextLine();
        if(moverEquipamento(id,nome) == true){
            System.out.println("Equipamento transferido!");
        }
        else{ 
            System.out.println("Equipamento não foi transferido!");
        }
    }

    /*
     * Move um quipamento de um departamento para outro.
     * @Long id é id do equipamento a ser movido de departamento
     * @String nome é o nome do departamento para o qual se deseja mover o equipamento
     */
    public boolean moverEquipamento(Long id, String nome){
        Equipamento e;
        for(int i=0; i<departamentos.size(); i++){
            for(int j = 0;j<departamentos.get(i).getEquipamentos().size();j++){
                if(departamentos.get(i).getEquipamentos().get(j).getId() == id){
                    e = departamentos.get(i).getEquipamentos().get(j);
                    departamentos.get(i).getEquipamentos().remove(e);
                    for(int k = 0; k<departamentos.size();k++){
                        if(departamentos.get(k).getNome().equals(nome)){
                            return departamentos.get(k).addEquipamento(e);
                        }
                    }
                }
            }
        }
        return false;
    }
}