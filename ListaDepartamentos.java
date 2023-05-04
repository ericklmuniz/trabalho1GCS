import java.util.ArrayList;
import javax.swing.text.AbstractDocument.BranchElement;

public class ListaDepartamentos{

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