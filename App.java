
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ListaDepartamentos listaDepartamentos = new ListaDepartamentos();

        // objetos pare testes
        Departamento suporte = new Departamento("Suporte");
        Departamento vendas = new Departamento("Vendas");
        Departamento RH = new Departamento("Recursos Humanos");

        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario(1L, "Marcelo", suporte, true));
        funcionarios.add(new Funcionario(2L, "Pedro", suporte, true));
        funcionarios.add(new Funcionario(3L, "Artur", RH, false));
        funcionarios.add(new Funcionario(4L, "Jose", RH, false));
        funcionarios.add(new Funcionario(5L, "Maria", vendas, false));
        funcionarios.add(new Funcionario(6L, "Fulana", vendas, false));

        ArrayList<Equipamento> equipamentos = new ArrayList<>();
        equipamentos.add(new Equipamento(1L, "Notebook Dell", LocalDate.of(2021, 4, 15), vendas));
        equipamentos.add(new Equipamento(2L, "Monitor Samsung", LocalDate.of(2021, 3, 20), suporte));
        equipamentos.add(new Equipamento(3L, "Impressora HP", LocalDate.of(2021, 2, 10), suporte));
        equipamentos.add(new Equipamento(4L, "Projetor Epson", LocalDate.of(2021, 1, 5), RH));
        equipamentos.add(new Equipamento(5L, "Teclado Logitech", LocalDate.of(2021, 5, 2), vendas));

        Funcionario usuarioAtual = escolherFuncionario(sc, funcionarios);

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
            System.out.println("6: Exibir lista de chamados");
            System.out.println("7: Localizar chamados por uma palavra-chave");
            System.out.println("8: Exibir funcionário logado");
            System.out.println("9: Modificar a lista de chamados");
            System.out.println("10: Painel de chamados");
            System.out.println("11: Adiciona novo equipamento");
            System.out.println("12: Mostra todos chamados");
            System.out.println("13: Mostra todos chamados abertos");
            System.out.println("14: Mostra todos chamados em andamento");
            System.out.println("15: Mostra todos chamados fechados");
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
                    System.out.println("Chamado aberto com sucesso");
                }

                case 2 -> {
                    // TODO implementar método para atualizar status do chamado desejado

                    // if (chamado1.atualizaStatus(usuarioAtual))
                    // System.out.println("Status atualizado com sucesso!");
                    // else
                    // System.out.println("Falha ao atualizar status, checar se é possível a
                    // atualização.");
                }

                case 3 -> // move equipamento de um departamento para outro
                    listaDepartamentos.opcaoMoveEquipamento(sc);

                case 4 -> // busca equipamento por descrição
                {
                    System.out.println("Digite a descricao do equipamento: ");
                    sc.nextLine();
                    String descricao = sc.nextLine();
                    Equipamento equipamento = buscarEquipamentoPorDescricao(descricao, equipamentos);
                    if (Objects.isNull(equipamento)) {
                        System.out.println("Equipamento não encontrado!");
                        break;
                    }
                    System.out.println("Equipamento encontrado!");
                    System.out.println(equipamento.toString());
                }

                case 6 -> {
                    Equipamento equipamento = escolherEquipamentoPorUsuario(sc, equipamentos);

                    if (Objects.isNull(equipamento)) {
                        System.out.println("Nenhum equipamento disponivel");
                        break;
                    }

                }

                case 7 -> System.out.println(ListaChamados.localizarChamados(sc));

                case 8 -> System.out.println("Usuário Atual é o " + usuarioAtual);

                case 9 -> MenuListaChamados.showMenu();

                case 10 -> painelDeChamados(ListaChamados.getChamadosList());
                case 11 -> listaDepartamentos.addEquipamentosAoDepartamento(sc);

                // printar listas de chamados
                case 12 -> System.out.println(ListaChamados.getChamadosList());
                case 13 -> System.out.println(ListaChamados.getChamadosAbertosList());
                case 14 -> System.out.println(ListaChamados.getChamadosEmAndamentoList());
                case 15 -> System.out.println(ListaChamados.getChamadosConcluidosList());

                default -> System.out.println("Entrada inválida. Tente novamente.");
            }
        } while (!encerrado);
    }

    private static Funcionario escolherFuncionario(Scanner sc, List<Funcionario> funcionarios) {
        System.out.println("Escolher Funcionario por ID");
        funcionarios.forEach(System.out::println);
        String opcao = sc.nextLine();

        Optional<Funcionario> usuarioAtual = funcionarios.stream()
                .filter(funcionario -> String.valueOf(funcionario.getId()).equals(opcao)).findFirst();
        return usuarioAtual.orElseGet(() -> {
            System.out.println("Escolha um funcionário válido");
            return escolherFuncionario(sc, funcionarios);
        });
    }

    private static void painelDeChamados(List<Chamado> chamados) {
        int totalChamados = chamados.size();
        System.out.println("Total de chamados registrados: " + totalChamados);

        if (totalChamados > 0) {
            int chamadosAbertos = chamados.stream().filter(chamado -> chamado.getStatus().equals(Status.ABERTO))
                    .toList().size();
            System.out.println(chamadosAbertos);
            System.out.println("Total de chamados abertos: " + chamadosAbertos + " - "
                    + chamadosAbertos * 100.0 / totalChamados + "%");

            int chamadosEmAndamento = chamados.stream()
                    .filter(chamado -> chamado.getStatus().equals(Status.EM_ANDAMENTO)).toList().size();
            System.out.println("Total de chamados em andamento: " + chamadosEmAndamento + " - "
                    + chamadosEmAndamento * 100.0 / totalChamados + "%");

            int chamadosConcluidos = chamados.stream().filter(chamado -> chamado.getStatus().equals(Status.CONCLUIDO))
                    .toList().size();
            System.out.println("Total de chamados concluidos: " + chamadosConcluidos + " - "
                    + chamadosConcluidos * 100.0 / totalChamados + "%");
        }

    }

    private static Equipamento escolherEquipamento(Scanner sc, Funcionario usuarioAtual) {
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

    public static Equipamento buscarEquipamentoPorDescricao(String descricao, List<Equipamento> listaEquipamentos) {

        for (Equipamento equipamento : listaEquipamentos) {
            if (equipamento.getDescricao().equalsIgnoreCase(descricao)) {
                return equipamento;
            }
        }
        return null;
    }

    private static Equipamento escolherEquipamentoPorUsuario(Scanner sc, List<Equipamento> equipamentos) {
        if (equipamentos.isEmpty())
            return null;

        System.out.println("Selecione o equipamento por Id:");

        equipamentos
                .forEach(equipamento -> System.out.println(equipamento.getId() + " - " + equipamento.getDescricao()));

        String equipamentoEscolhido = sc.next();

        Optional<Equipamento> escolhido = equipamentos.stream()
                .filter(equipamento -> String.valueOf(equipamento.getId()).equals(equipamentoEscolhido)).findFirst();

        if (escolhido.isPresent())
            return escolhido.get();

        System.out.println("Escolha um equipamento válido");
        return escolherEquipamentoPorUsuario(sc, equipamentos);
    }

}