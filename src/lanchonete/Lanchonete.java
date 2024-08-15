package lanchonete;

import java.sql.SQLException;
import java.util.Scanner;

import dominio.Funcionario;
import dominio.Produto;
import negocio.AdminNegocio;
import negocio.ProdutoNegocio;
import negocio.VendaNegocio;
import negocio.PessoaNegocio;
import negocio.SuporteNegocio;
import negocio.FuncionarioNegocio;

public class Lanchonete {

    static ProdutoNegocio produtoNegocio = new ProdutoNegocio();
    static VendaNegocio vendaNegocio = new VendaNegocio();

    private static String categoria, nome, descricao;
    private static double preco;
    private static int quantidade, codigo;
    private static boolean encontrado = true;

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.println("#####  Menu Principal  #####");
            System.out.println("============================");
            System.out.println("##### 1 - Cardapio     #####");
            System.out.println("##### 2 - Meu Perfil   #####");
            System.out.println("##### 3 - Suporte      #####");
            System.out.println("##### 4 - Admin        #####");
            System.out.println("##### 0 - Sair         #####");
            System.out.println("============================");
            System.out.print("##### Escolha uma opção: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    clearScreen();
                    menuOpcao1(sc);
                    break;
                case 2:
                    clearScreen();
                    menuOpcao2(sc);
                    break;
                case 3:
                    clearScreen();
                    menuOpcao3(sc);
                    break;
                case 4:
                    clearScreen();
                    menuOpcao4(sc);
                    break;
                case 0:
                    clearScreen();
                    exit = true;
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    clearScreen();
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        sc.close();

    }

    private static void menuOpcao1(Scanner sc) throws InterruptedException {
        boolean back = false;

        while (!back) {
            System.out.println("\n######    Cardápio    #######");
            System.out.println("===============================");
            System.out.println("##### 1 - Lanche          #####");
            System.out.println("##### 2 - Acompanhamento  #####");
            System.out.println("##### 3 - Bebida          #####");
            System.out.println("##### 0 - Voltar          #####");
            System.out.println("===============================");
            System.out.print("##### Escolha uma opção: ");
            int choice = sc.nextInt();
            clearScreen();

            switch (choice) {
                case 1:
                    categoria = "Lanche";
                    System.out.println("\n##### Esses são nossos lanches: ");
                    GerenciadorDeProdutos gerenciadorLanches = new GerenciadorDeProdutos();
                    try {
                        produtoNegocio.searchByCategory(categoria);
                    } catch (Exception e) {
                        System.out.println("Erro " + e.getMessage());
                    }
                    gerenciadorLanches.viewProductsSequenceLanche();
                    comprarProduto(sc);
                    break;

                case 2:
                    categoria = "Acompanhamento";
                    System.out.println("\n##### Esses são nossos acompanhamentos: ");
                    GerenciadorDeProdutos gerenciadorAcompanhamentos = new GerenciadorDeProdutos();
                    try {
                        produtoNegocio.searchByCategory(categoria);
                    } catch (Exception e) {
                        System.out.println("Erro " + e.getMessage());
                    }
                    gerenciadorAcompanhamentos.viewProductsSequenceAcompanhamento();
                    comprarProduto(sc);
                    break;

                case 3:
                    categoria = "Bebida";
                    System.out.println("\n##### Essas são nossas bebidas: ");
                    GerenciadorDeProdutos gerenciadorProdutos = new GerenciadorDeProdutos();
                    try {
                        produtoNegocio.searchByCategory(categoria);
                    } catch (Exception e) {
                        System.out.println("Erro " + e.getMessage());
                    }
                    gerenciadorProdutos.viewProductsSequenceBebida();
                    comprarProduto(sc);
                    break;

                case 0:
                    back = true;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void menuOpcao2(Scanner sc) throws InterruptedException {
        boolean back = false;

        while (!back) {
            System.out.println("\nMenu da Opção 2:");
            System.out.println("1. Sub-opção 2.1");
            System.out.println("2. Sub-opção 2.2");
            System.out.println("3. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int choice = sc.nextInt();
            clearScreen();

            switch (choice) {
                case 1:
                    System.out.println("Você escolheu a Sub-opção 2.1.");
                    // Adicionar funcionalidade para Sub-opção 2.1 aqui
                    break;
                case 2:
                    System.out.println("Você escolheu a Sub-opção 2.2.");
                    // Adicionar funcionalidade para Sub-opção 2.2 aqui
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void menuOpcao3(Scanner sc) throws InterruptedException {
        boolean back = false;
        SuporteNegocio suporte = new SuporteNegocio();
        while (!back) {
            System.out.println("#########      Suporte     ########");
            System.out.println("===================================");
            System.out.println("##### 1 - Falar com o Suporte #####");
            System.out.println("##### 0 - Sair                #####");
            System.out.println("===================================");
            System.out.print("##### Escolha uma opção: ");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.print("\nDigite o seu CPF: ");
                    String cpf = sc.next();
                    if (isValidCPF(cpf)) {
                        System.out.print("\nDigite seu nome: ");
                        String nome = sc.next();
                        sc.nextLine();
                        //BUG AO MANDAR PARA O BANCO DE DADOS. ENVIA APENAS A PRIMEIRA PALAVRA.
                        System.out.print("Nos fale sobre o seu problema: ");
                        String descricao = sc.next();
                        sc.nextLine();
                        try {
                            suporte.insertSuport(cpf, cpf, descricao, nome);
                        } catch (Exception e) {
                            System.out.println("Erro " + e.getMessage());
                        }
                    }
                    break;
                case 0:
                    back = true;
                    break;
            }

        }
    }

    private static void menuOpcao4(Scanner sc) throws InterruptedException {
        boolean back = false;
        AdminNegocio adminNegocio = new AdminNegocio();

        while (!back) {
            System.out.println("\n#########    ADMIN    ########");
            System.out.println("==============================");
            System.out.println("##### 1 - Login          #####");
            System.out.println("##### 0 - Sair           #####");
            System.out.println("==============================");
            System.out.print("##### Escolha uma opção: ");
            int choice = sc.nextInt();
            clearScreen();
            switch (choice) {
                case 1:
                    System.out.print("Matricula: ");
                    String matricula = sc.next();
                    System.out.print("Senha: ");
                    String senha = sc.next();
                    if (isValidMatricula(matricula) && isValidSenha(senha)) {
                        if (adminNegocio.verificarCredenciais(matricula, senha)) {
                            boolean backControl = false;
                            while (!backControl) {
                                System.out.println("\nLogin bem-sucedido! Bem-vindo à área administrativa.");
                                System.out.println("\n#########        Controle      ########");
                                System.out.println("=======================================");
                                System.out.println("##### 1 - Gerenciar Produto       #####");
                                System.out.println("##### 2 - Gerenciar Funcionário   #####");
                                System.out.println("##### 3 - Gerenciar Cliente       #####");
                                System.out.println("##### 4 - Gerenciar Vendas        #####");
                                System.out.println("##### 5 - Relatórios              #####");
                                System.out.println("##### 0 - Sair                    #####");
                                System.out.println("=======================================");
                                System.out.print("##### Escolha uma opção: ");
                                int choiceControl = sc.nextInt();
                                clearScreen();
                                switch (choiceControl) {
                                    case 1:
                                        boolean backProd = false;
                                        while (!backProd){
                                            System.out.println("#########   Gerenciar Produto   ########");
                                            System.out.println("========================================");
                                            System.out.println("##### 1 - Ver Produtos             #####");
                                            System.out.println("##### 2 - Adicionar Produtos       #####");
                                            System.out.println("##### 3 - Modificar Produtos       #####");
                                            System.out.println("##### 4 - Relatórios               #####");
                                            System.out.println("##### 0 - Sair                     #####");
                                            System.out.println("========================================");
                                            System.out.print("##### Escolha uma opção: ");
                                            int choiceProd = sc.nextInt();
                                            clearScreen();
                                            switch (choiceProd) {
                                                case 1:
                                                    System.out.print("Precisa de alguma categoria especifica? 1 - SIM | 2 - NÃO: ");
                                                    int escolha = sc.nextInt();
                                                    GerenciadorDeProdutos gerenciadorLanches = new GerenciadorDeProdutos();
                                                    if (escolha == 1) {
                                                        System.out.println("\n## Escolha uma Categoria ##");
                                                        System.out.println("===========================");
                                                        System.out.println("## 1 = Lanche            ##");
                                                        System.out.println("## 2 = Acompanhamentos   ##");
                                                        System.out.println("## 3 = Bebidas           ##");
                                                        System.out.println("===========================\n");
                                                        boolean encontrado = true;
                                                        do {
                                                            try {
                                                                System.out.print("Digite a Categoria: ");
                                                                int categoriaEscolha = sc.nextInt();
                                                                if (categoriaEscolha == 1) {
                                                                    categoria = "Lanche";
                                                                    produtoNegocio.searchByCategory(categoria);
                                                                    gerenciadorLanches.viewProductsSequenceLanche();
                                                                } else if (categoriaEscolha == 2) {
                                                                    categoria = "Acompanhamento";
                                                                    produtoNegocio.searchByCategory(categoria);
                                                                    gerenciadorLanches.viewProductsSequenceAcompanhamento();
                                                                } else if (categoriaEscolha == 3) {
                                                                    categoria = "Bebida";
                                                                    produtoNegocio.searchByCategory(categoria);
                                                                    gerenciadorLanches.viewProductsSequenceBebida();
                                                                } else {
                                                                    System.out.println("Categoria não encontrada!");
                                                                    encontrado = false;
                                                                }
                                                            } catch (Exception e) {
                                                                System.out.println("Erro " + e.getMessage());
                                                            }
                                                        } while (!encontrado);
                                                    } else if (escolha == 2) {
                                                        try {
                                                            produtoNegocio.searchAll();
                                                            gerenciadorLanches.sequenciaTodos();
                                                        } catch (Exception e) {
                                                            System.out.println("Erro " + e.getMessage());
                                                        }
                                                    } else {
                                                        System.out.println("Opção Inválida!");
                                                    }
                                                    System.out.println("Tecle ENTER para Sair...");
                                                    break;
                                                case 2:
                                                    System.out.println("\n## Escolha uma Categoria ##");
                                                    System.out.println("===========================");
                                                    System.out.println("## 1 = Lanche            ##");
                                                    System.out.println("## 2 = Acompanhamentos   ##");
                                                    System.out.println("## 3 = Bebidas           ##");
                                                    System.out.println("===========================\n");
                                                    int codigo = 0;
                                                    do {
                                                        System.out.print("Digite a Categoria: ");
                                                        int categoriaEscolha = sc.nextInt();
                                                        if (categoriaEscolha == 1) {
                                                            categoria = "Lanche";
                                                            codigo = 1;
                                                        } else if (categoriaEscolha == 2) {
                                                            categoria = "Acompanhamento";
                                                            codigo = 2;
                                                        } else if (categoriaEscolha == 3) {
                                                            categoria = "Bebida";
                                                            codigo = 3;
                                                        } else {
                                                            System.out.println("Categoria não encontrada!");
                                                            encontrado = false;
                                                        }
                                                    } while (!encontrado);
                                                    System.out.print("Digite o Nome: ");
                                                    String nome = sc.next();
                                                    System.out.print("Digite a Descrição: ");
                                                    String descricao = sc.next();
                                                    System.out.print("Digite o Preço: ");
                                                    double preco = sc.nextDouble();
                                                    System.out.print("Digite a Quantidade: ");
                                                    int quantidade = sc.nextInt();
                                                    try {
                                                        Produto produto = new Produto(codigo, categoria, nome, descricao, preco, quantidade);
                                                        produtoNegocio.insertProduct(produto);
                                                        System.out.println("\nProduto Adicionado com Sucesso!");
                                                        System.out.print("\nTecle ENTER para sair");
                                                    } catch (Exception e) {
                                                        System.out.println("Erro " + e.getMessage());
                                                    }
                                                    break;
                                                case 3:
                                                    System.out.println("\nDigite o ID do produto: ");
                                                    int id = sc.nextInt();
                                                    System.out.print("Digite o Novo Nome: ");
                                                    nome = sc.next();
                                                    System.out.print("Digite a Nova Descrição: ");
                                                    descricao = sc.next();
                                                    System.out.print("Digite o Novo Preço: ");
                                                    preco = sc.nextDouble();
                                                    System.out.print("Digite a Nova Quantidade: ");
                                                    quantidade = sc.nextInt();
                                                    try {
                                                        Produto produto = new Produto(id, nome, descricao, preco, quantidade);
                                                        System.out.println("\nProduto Atualizado com Sucesso!");
                                                        System.out.println("\nTecle ENTER para sair");
                                                        produtoNegocio.updateProduct(produto);
                                                    }catch (Exception e){
                                                        System.out.println("Erro " + e.getMessage());
                                                    }
                                                    break;
                                                case 4:
                                                    break;
                                                case 0:
                                                    backProd = true;
                                                    break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        boolean backFunc = false;
                                        while (!backFunc) {
                                            System.out.println("#######   Gerenciar Funcionário   ######");
                                            System.out.println("========================================");
                                            System.out.println("##### 1 - Cadastrar Funcionário    #####");
                                            System.out.println("##### 2 - Ver Funcionários         #####");
                                            System.out.println("##### 3 - Alterar Funcionários     #####");
                                            System.out.println("##### 4 - Apagar Funcionários      #####");
                                            System.out.println("##### 5 - Relatórios               #####");
                                            System.out.println("##### 0 - Sair                     #####");
                                            System.out.println("========================================");
                                            System.out.print("##### Escolha uma opção: ");
                                            int choiceFunc = sc.nextInt();
                                            FuncionarioNegocio funcionarioNegocio = new FuncionarioNegocio();
                                            switch (choiceFunc) {
                                                case 1:
                                                    System.out.print("Digite o nome: ");
                                                    nome = sc.next();
                                                    System.out.print("Digite o CPF: ");
                                                    String cpf = sc.next();
                                                    if (isValidCPF(cpf)){
                                                        System.out.print("Digite o Email: ");
                                                        String email = sc.next();
                                                        System.out.print("Digite sua senha: ");
                                                        senha = sc.next();
                                                        System.out.print("Digite o salario: ");
                                                        double salario = sc.nextDouble();
                                                        System.out.print("Digite o Telefone: ");
                                                        String numero_tele = sc.next();
                                                        Funcionario funcionario = new Funcionario(nome, email, senha, cpf, salario, numero_tele);
                                                        try {
                                                            funcionarioNegocio.insertFuncionario(funcionario);
                                                            System.out.println("Funcionario cadastrado com Sucesso!");
                                                        }catch (SQLException e){
                                                            System.out.println("Erro " + e.getMessage());
                                                        }
                                                    }else{
                                                        System.out.println("CPF Invalido!");
                                                    }
                                                    break;
                                                case 2:
                                                    try {

                                                        GerenciadorDeFuncionarios gerenciadorFunc = new GerenciadorDeFuncionarios();
                                                        funcionarioNegocio.searchAll();
                                                        gerenciadorFunc.exibirFuncionariosComSequencia();
                                                    } catch (Exception e) {
                                                        System.out.println("Erro " + e.getMessage());
                                                    }
                                                    break;
                                                case 3:
                                                    break;
                                                case 4:
                                                    break;
                                                case 5:
                                                    break;
                                                case 0:
                                                    backFunc = true;
                                                    break;
                                            }
                                        }
                                        break;
                                    case 3:
                                        boolean backClien = false;
                                        while (!backClien) {
                                            System.out.println("#######    Gerenciar Clientes    ######");
                                            System.out.println("=======================================");
                                            System.out.println("##### 1 - Ver Clientes            #####");
                                            System.out.println("##### 2 - Relatórios              #####");
                                            System.out.println("##### 0 - Sair                    #####");
                                            System.out.println("=======================================");
                                            System.out.print("##### Escolha uma opção: ");
                                            int choiceClien = sc.nextInt();
                                            switch (choiceClien) {
                                                case 1:
                                                    try {
                                                        PessoaNegocio pessoaNegocio = new PessoaNegocio();
                                                        GerenciadorDeClientes gerenciadorClien = new GerenciadorDeClientes();
                                                        pessoaNegocio.searchAll();
                                                        gerenciadorClien.exibirClientesComSequencia();
                                                    } catch (Exception e) {
                                                        System.out.println("Erro " + e.getMessage());
                                                    }
                                                    break;
                                                case 2:
                                                    break;
                                                case 0:
                                                    backClien = true;
                                                    break;
                                            }
                                        }
                                        break;
                                    case 4:
                                        boolean backVenda = false;
                                        while (!backVenda) {
                                            System.out.println("########    Gerenciar Vendas    #######");
                                            System.out.println("=======================================");
                                            System.out.println("##### 1 - Relatórios              #####");
                                            System.out.println("##### 0 - Sair                    #####");
                                            System.out.println("=======================================");
                                            System.out.print("##### Escolha uma opção: ");
                                            int choiceVend = sc.nextInt();
                                            switch (choiceVend) {
                                                case 1:
                                                    break;
                                                case 0:
                                                    backVenda = true;
                                                    break;
                                            }
                                        }
                                        break;
                                    case 5:
                                        boolean backRela = false;
                                        while (!backRela) {
                                            System.out.println("#########    Relatório    ########");
                                            System.out.println("==================================");
                                            System.out.println("##### 1 - Ver Relatório      #####");
                                            System.out.println("##### 0 - Sair               #####");
                                            System.out.println("==================================");
                                            System.out.print("##### Escolha uma opção: ");
                                            int choiceRela = sc.nextInt();
                                            switch (choiceRela) {
                                                case 1:
                                                    break;
                                                case 0:
                                                    backRela = true;
                                                    break;
                                            }
                                        }
                                        break;
                                    case 0:
                                        backControl = true;
                                        break;
                                }
                            }
                        } else {
                            if (adminNegocio.verificarMatricula(matricula)) {
                                System.out.println("Senha incorreta.");
                                clearScreen();
                            } else {
                                System.out.println("Matrícula não encontrada.");
                                clearScreen();
                            }
                        }
                    } else {
                        if (!isValidMatricula(matricula)) {
                            System.out.println("Matrícula inválida! Deve conter 12 dígitos.");
                            clearScreen();
                        }
                        if (!isValidSenha(senha)) {
                            System.out.println("Senha inválida! Deve ter entre 8 e 20 caracteres.");
                            clearScreen();
                        }
                    }
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static boolean isValidMatricula(String matricula) {
        return matricula.matches("\\d{12}"); // Verifica se a matrícula tem exatamente 12 dígitos
    }

    public static boolean isValidSenha(String senha) {
        return senha.length() >= 8 && senha.length() <= 20; // Verifica se a senha tem entre 8 e 20 caracteres
    }

    public static void clearScreen() throws InterruptedException {
        // Limpa o terminal imprimindo 100 linhas em branco
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
        System.out.println("\nCarregando...");
        Thread.sleep(500);
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    public static void comprarProduto(Scanner sc) {
        try {
            GerenciadorDeProdutos gerenciadorLanches = new GerenciadorDeProdutos();
            System.out.print("\n##### Deseja fazer um pedido | 1 - SIM | 2 - NÃO: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Digite o Código do pedido que deseja: ");
                    int codigo = sc.nextInt();
                    if (gerenciadorLanches.viewProducts(codigo)) {
                        System.out.print("\nDigite a quantidade: ");
                        int quantidade = sc.nextInt();
                        int id = produtoNegocio.searchId(codigo);
                        vendaNegocio.insertSale(id, codigo, quantidade);
                    }
                case 2:
                    System.out.println("Voltando...");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static boolean isValidCPF(String cpf) {
        Scanner sc = new Scanner(System.in);
        do {
            //Digitar apenas dígitos
            Long cpfLong = Long.valueOf(cpf);
            System.out.print("\nValidando CPF...");
            //O CPF PODE INICIAR COM ZEROS
            if (cpfLong > 0L && cpfLong <= 99999999999L) {
                int vetorCPF[] = new int[11];
                //convertendo a String em um vetor de int
                for (int i = 0; i < cpf.length(); i++) {
                    //Converto cada posição da string em um número
                    vetorCPF[i] = Character.getNumericValue(cpf.charAt(i));
                }
                int sm = (vetorCPF[0] * 10) + (vetorCPF[1] * 9) + (vetorCPF[2] * 8) + (vetorCPF[3] * 7) + (vetorCPF[4] * 6) + (vetorCPF[5] * 5) + (vetorCPF[6] * 4) + (vetorCPF[7] * 3) + (vetorCPF[8] * 2);
                int digito1 = (sm * 10) % 11;
                if (digito1 == 10 || digito1 == 11) {
                    digito1 = 0;
                }
                int sm2 = (vetorCPF[0] * 11) + (vetorCPF[1] * 10) + (vetorCPF[2] * 9) + (vetorCPF[3] * 8) + (vetorCPF[4] * 7) + (vetorCPF[5] * 6) + (vetorCPF[6] * 5) + (vetorCPF[7] * 4) + (vetorCPF[8] * 3) + (digito1 * 2);
                int digito2 = (sm2 * 10) % 11;
                if (digito2 == 10 || digito2 == 11) {
                    digito2 = 0;
                }
                //Verificando se o CPF é válido
                if (digito1 == vetorCPF[9] && digito2 == vetorCPF[10]) {
                    return true;
                } else {
                    System.out.println("\n\n-----------------------------------");
                    System.out.println("\nCPF Inválido!");
                    System.out.println("\n-----------------------------------");
                    return false;
                }
            } else {
                System.out.println("\n\n-----------------------------------");
                System.out.println("\nCPF Inválido!");
                System.out.println("\n-----------------------------------");
                return false;
            }
            //Testa a condição do cpf.
        } while (false);
    }
}
