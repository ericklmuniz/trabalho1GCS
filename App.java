
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class App {

    private static ListaDepartamentos ld = new ListaDepartamentos();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // objetos pare testes
        ArrayList<Funcionario> funcionariosSuporte = new ArrayList<>();
        Departamento suporte = new Departamento("Suporte", funcionariosSuporte);
        ArrayList<Funcionario> funcionariosVendas = new ArrayList<>();
        Departamento vendas = new Departamento("Vendas", funcionariosSuporte);
        ArrayList<Funcionario> funcionariosRH = new ArrayList<>();
        Departamento RH = new Departamento("Recursos Humanos", funcionariosRH);
        Funcionario um = new Funcionario((long) 001, "Marcelo", suporte, true);
        Funcionario dois = new Funcionario((long) 002, "Pedro", suporte, true);
        Funcionario tres = new Funcionario((long) 003, "Artur", RH, false);
        Funcionario quatro = new Funcionario((long) 004, "Jose", RH, false);
        Funcionario cinco = new Funcionario((long) 005, "Maria", vendas, false);
        Funcionario seis = new Funcionario((long) 005, "Fulana", vendas, false);
        Equipamento equip = new Equipamento((long) 800, "Teclado", LocalDate.now(), vendas);
        Chamado chamado1 = new Chamado(equip, "Teste chamado", tres, quatro, LocalDateTime.now(), Status.EM_ANDAMENTO,
                Prioridade.MEDIA, "teste", suporte);

        Funcionario usuarioAtual = seis; // usario logado no momento

        System.out.println("Escolher Funcionario");
        System.out.println("1: " + um.toString());
        System.out.println("2: " + dois.toString());
        System.out.println("3: " + tres.toString());
        System.out.println("4: " + quatro.toString());
        System.out.println("5: " + cinco.toString());
        System.out.println("6: " + seis.toString());
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1 -> usuarioAtual = um;
            case 2 -> usuarioAtual = dois;
            case 3 -> usuarioAtual = tres;
            case 4 -> usuarioAtual = quatro;
            case 5 -> usuarioAtual = cinco;
            case 6 -> usuarioAtual = seis;
            default -> System.out.println("Entrada inválida. Tente novamente.");
        }

        System.out.println("Usuário Atual é o " + usuarioAtual);

        // ESCOLHER FUNCIONARIO ANTES
        boolean encerrado = false;
        do {
            // SOMENTE FUNCIONARIOS SUPORTE DEVEM TER ACESSO A ALGUMAS FUNCIONALIADADES
            System.out.println("Escolher Funcionalidade");
            System.out.println("1: Abrir novo chamado");
            System.out.println("2: Atualizar status do chamado");
            System.out.println("3: Mover equipamento");
            System.out.println("4: Pesquisar equipamento pela descrição");
            // Fazer as outras
            System.out.println("7: Localizar chamados por uma palavra-chave");
            System.out.println("8: Exibir funcionário logado");
            System.out.println("9: Modificar a lista de chamados");
            System.out.println("0: Encerrar Programa");
            int escolha = sc.nextInt();

            switch (escolha) {
                case 0 -> // case de outras funcionalidades
                    encerrado = true;

                case 1 -> {
                    Equipamento equipamento = escolherEquipamento(sc, usuarioAtual);

                    if (Objects.isNull(equipamento)) {
                        System.out.println("Nenhum equipamento disponivel");
                        break;
                    }

                    System.out.println("Insira a descrição do chamado");
                    String descricao = sc.nextLine();

                    Prioridade prioridade = escolherPrioridade(sc);

                    usuarioAtual.abrirChamado(equipamento, descricao, prioridade);
                }

                case 2 -> {
                    if (chamado1.atualizaStatus(usuarioAtual))
                        System.out.println("Status atualizado com sucesso!");
                    else
                        System.out.println("Falha ao atualizar status, checar se é possível a atualização.");
                }
                case 3 -> // move equipamento de um departamento para outro
                {
                    System.out.print("Digite o id do equipamento: ");
                    Long id = sc.nextLong();
                    sc.nextLine();
                    System.out.print("Digite o nome do departamento que você deseja mover o equipamento: ");
                    String nome = sc.nextLine();
                    if (ld.moverEquipamento(id, nome)) {
                        System.out.println("Equipamento transferido!");
                    } else {
                        System.out.println("Equipamento não foi transferido!");
                    }
                    System.out.println();
                    break;
                }

                case 8 -> System.out.println("Usuário Atual é o " + usuarioAtual);

                case 7 -> System.out.println(ListaChamados.localizarChamados(sc));

                case 9 -> MenuListaChamados.showMenu();

                default -> System.out.println("Entrada inválida. Tente novamente.");
            }
        } while (!encerrado);
    }

    public static Equipamento escolherEquipamento(Scanner sc, Funcionario usuarioAtual) {
        if (usuarioAtual.getDepartamento().getEquipamentos().isEmpty())
            return null;

        System.out.println("Selecione o equipamento para abertura do chamado:");

        usuarioAtual.getDepartamento().getEquipamentos()
                .forEach(equipamento -> System.out.println(equipamento.getId() + " - " + equipamento.getDescricao()));

        String equipamentoEscolhido = sc.next();

        Optional<Equipamento> escolhido = usuarioAtual.getDepartamento().getEquipamentos().stream()
                .filter(equipamento -> String.valueOf(equipamento.getId()).equals(equipamentoEscolhido)).findFirst();

        if (escolhido.isPresent())
            return escolhido.get();

        System.out.println("Escolha um equipamento válido");
        return escolherEquipamento(sc, usuarioAtual);
    }

    public static Prioridade escolherPrioridade(Scanner sc) {
        System.out.println("Escolha a prioridade do chamado");
        System.out.println("0 - " + "Baixa");
        System.out.println("1 - " + "Media");
        System.out.println("2 - " + "Alta");

        String prioridadeEscolhida = sc.nextLine();

        switch (prioridadeEscolhida) {
            case "0":
                return Prioridade.BAIXA;
            case "1":
                return Prioridade.MEDIA;
            case "2":
                return Prioridade.ALTA;
            default:
                System.out.println("Escolha uma prioridade válida!");
                return escolherPrioridade(sc);
        }

    }

    public Equipamento buscarEquipamentoPorDescricao(String descricao, List<Equipamento> listaEquipamentos) {
        for (Equipamento equipamento : listaEquipamentos) {
            if (equipamento.getDescricao().equalsIgnoreCase(descricao)) {
                return equipamento;
            }
        }
        return null;
    }

}