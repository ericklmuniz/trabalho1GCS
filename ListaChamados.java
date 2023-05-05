import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListaChamados {
    static List<Chamado> chamadosList = new ArrayList<>();

    //ArrayList que armazena chamados localizados.
    static ArrayList<Chamado> chamadosLocalizados = new ArrayList<>();

    private ListaChamados() {
        // empty constructor for consistency
    }

    // métodos para remoção de chamados a partir de uma atributo determinado
    public static void removeByEquipamento(long id) {
        chamadosList.removeIf(chamado -> chamado.getEquipamento().getId().equals(id));
    }

    public static void removeByDescricao(String descricao) {
        chamadosList.removeIf(chamado -> chamado.getDescricao().equalsIgnoreCase(descricao));
    }

    public static void removeByRequisitante(long id) {
        chamadosList.removeIf(chamado -> chamado.getRequisitante().getId().equals(id));
    }

    public static void removeByResponsavel(long id){
        chamadosList.removeIf(chamado -> chamado.getResponsavel().getId().equals(id));
    }

    public static void removeByDataSolicitacao(String dataSolicitacao){
        chamadosList.removeIf(chamado -> chamado.getDataSolicitacao().equals(LocalDateTime.parse(dataSolicitacao)));
    }

    public static void removeByStatus(String status){
        chamadosList.removeIf(chamado -> chamado.getStatus().equals(Status.valueOf(status)));
    }

    public static void removeByPrioridade(String prioridade){
        chamadosList.removeIf(chamado -> chamado.getPrioridade().equals(Status.valueOf(prioridade)));
    }

    public static void removeByTextoResolucao(String textoResolucao){
        chamadosList.removeIf(chamado -> chamado.getTextoResolucao().equalsIgnoreCase(textoResolucao));
    }

    public static void clearList(){
        chamadosList.clear();
    }


    /**
     * O sistema deverá permitir localizar chamados por uma palavra-chave
     *
     * @param in input para determinar a opção desejada
     * @return StringBuilder contendo todos chamados que possuem a palavra-chave
     */
    public static String localizarChamados(Scanner in){
        StringBuilder saida = new StringBuilder();
        String palavraChave = "";
        int opcao = 0;

        System.out.println("Em qual campo deseja localizar:");
        System.out.println("1: Nome do Requisitante.");
        System.out.println("2: Nome do Responsavel.");
        System.out.println("3: Descrição do equipamento.");
        System.out.println("4: Nome do setor.");
        System.out.println("5: Texto da solicitação.");
        System.out.println("6: Texto da resolução.");
        System.out.println("Digite a opção desejada:");
        opcao = in.nextInt();
        in.nextLine();
        System.out.println("Digite a palavra-chave:");
        palavraChave = in.nextLine();
        
        //Percorre o arraylist e verifica se contem no campo selecionado.
        for(Chamado localizado : chamadosList){
            switch(opcao){
                case 1:
                    if(localizado.getRequisitante().getNome().contains(palavraChave))
                        chamadosLocalizados.add(localizado);
                    break;

                case 2:
                    if(localizado.getResponsavel().getNome().contains(palavraChave))
                        chamadosLocalizados.add(localizado);
                    break;

                case 3:
                    if(localizado.getEquipamento().getDescricao().contains(palavraChave))
                        chamadosLocalizados.add(localizado);
                    break;

                case 4:
                    if(localizado.getSetor().getNome().contains(palavraChave))
                        chamadosLocalizados.add(localizado);
                    break;

                case 5:
                    if(localizado.getDescricao().contains(palavraChave))
                        chamadosLocalizados.add(localizado);
                    break;
                
                case 6:
                    if(localizado.getTextoResolucao().contains(palavraChave))
                        chamadosLocalizados.add(localizado);
                    break;
            }
        }

        //Para concatenar o toString de cada chamado localizado.
        for(Chamado chamadoLocalizado : chamadosLocalizados){
            saida.append(chamadoLocalizado.toString());
        }
        return "\nChamados Localizados: " + saida + "\n";
    }


    // métodos padrões
    public static boolean contains(Chamado chamado){
        return chamadosList.contains(chamado);
    }

    public static void remove(Chamado chamado){
        chamadosList.remove(chamado);
    }

    public static void add(Chamado chamado){
        chamadosList.add(chamado);
    }

    public static List<Chamado> getChamadosList() {
        return chamadosList;
    }
}
