import java.util.Scanner;

public class MenuListaChamados {

    private MenuListaChamados() {
        // empty constructor for consistency
    }

    public static void showMenu(){ // mostra opções de remoção
        System.out.println("""
                             Escolha a opção desejada:
                             1: Remover todos chamados com X Equipamento.
                             2: Remover todos chamados com X Descrição.
                             3: Remover todos chamados com X Requisitante.
                             4: Remover todos chamados com X Responsável.
                             5: Remover todos chamados com X Data de Solicitação.
                             6: Remover todos chamados com X Status.
                             7: Remover todos chamados com X Texto de Resolução.
                           """);

        Scanner menuOption = new Scanner(System.in);
        if(menuOption.hasNextInt()){
            switch (menuOption.nextInt()) {
                case 1 -> removeByEquipamento();
                case 2 -> removeByDescricao();
                case 3 -> removeByRequisitante();
                case 4 -> removeByResponsavel();
                case 5 -> removeByDataSolicitacao();
                case 6 -> removeByStatus();
                case 7 -> removeByTextoResolucao();
                default -> throw new IllegalArgumentException("Opção inválida.");
            }
        }

    }

    // métodos para remoção de chamados a partir de um atributo determinado no input do usuário

    private static void removeByEquipamento(){
        System.out.println("Digite o ID do equipamento: ");
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextLong()){
            ListaChamados.removeByEquipamento(scanner.nextLong());
        }
    }

    private static void removeByDescricao(){
        System.out.println("Digite a descrição do chamado: ");
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextLine()){
            ListaChamados.removeByDescricao(scanner.nextLine());
        }
    }

    private static void removeByRequisitante(){
        System.out.println("Digite o ID do requisitante do chamado: ");
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextLong()){
            ListaChamados.removeByRequisitante(scanner.nextLong());
        }
    }

    private static void removeByResponsavel(){
        System.out.println("Digite o ID do responsável pelo chamado: ");
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextLong()){
            ListaChamados.removeByResponsavel(scanner.nextLong());
        }
    }

    private static void removeByDataSolicitacao(){
        System.out.println("Digite a data de solicitação do chamado: ");
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextLine()){
            ListaChamados.removeByDataSolicitacao(scanner.nextLine());
        }
    }

    private static void removeByStatus(){
        System.out.println("Digite o status do chamado: ");
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextLine()){
            ListaChamados.removeByStatus(scanner.nextLine());
        }
    }

    private static void removeByTextoResolucao(){
        System.out.println("Digite o texto de resolução do chamado: ");
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextLine()){
            ListaChamados.removeByTextoResolucao(scanner.nextLine());
        }
    }
}
