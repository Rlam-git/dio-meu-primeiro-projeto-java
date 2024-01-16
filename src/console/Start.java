package console;

import database.Banco;
import entity.*;
import model.ClienteNegocio;
import model.PedidoNegocio;
import model.ProdutoNegocio;
import utility.LeitoraDados;

import java.util.Optional;
import java.util.Scanner;


public class Start {

  private static Cliente clienteLogado = null;
  private static Banco banco = new Banco();

  private static Scanner sc = new Scanner(System.in);
  private static ClienteNegocio clienteNegocio = new ClienteNegocio(banco);
  private static PedidoNegocio pedidoNegocio = new PedidoNegocio(banco);
  private static ProdutoNegocio produtoNegocio = new ProdutoNegocio(banco);


  public static void main(String[] args) {


    System.out.println("Bem vindo ao e-Compras");

    String opcao = "";

    while (true) {

      if (clienteLogado == null) {

        System.out.println("Selecione uma opção:");
        System.out.println("1 - Entrar");
        System.out.println("2 - Cadastrar Cliente");
        String fi = LeitoraDados.lerDado();


        if (fi.equals("1")) {
          System.out.println("Digite o cpf:");
          identificarUsuario(LeitoraDados.lerDado());
        } else {
          clienteNegocio.cadastrarCliente(sc);
        }

      } else {

        System.out.println("Selecione uma opção:");
        System.out.println("1 - Cadastrar Livro");
        System.out.println("2 - Excluir Livro");
        System.out.println("3 - Consultar Livro por nome");
        System.out.println("4 - Cadastrar Caderno");
        System.out.println("5 - Excluir Caderno");
        System.out.println("6 - Consultar Caderno por Materia");
        //TODO Desafio: Consultar Caderno(matéria)
        System.out.println("7 - Fazer pedido");
        System.out.println("8 - Excluir pedido");
        //TODO Desafio: Consultar Pedido(código)
        System.out.println("9 - Listar produtos");
        System.out.println("10 - Listar pedidos");
        System.out.println("11 - Deslogar");
        System.out.println("12 - Sair");

        opcao = LeitoraDados.lerDado();

        switch (opcao) {
          case "1":
            Livro livro = LeitoraDados.lerLivro();
            produtoNegocio.salvar(livro);
            break;
          case "2":
            System.out.println("Digite o código do livro");
            String codigoLivro = LeitoraDados.lerDado();
            produtoNegocio.excluir(codigoLivro);
            break;
          case "3":
            System.out.println("Aplicação encerrada.");
            String nome = LeitoraDados.lerDado();
            System.out.println(produtoNegocio.buscarLivroPorNome(nome));
            break;
          case "4":
            Caderno caderno = LeitoraDados.lerCaderno();
            System.out.println(caderno);
            break;
          case "5":
            System.out.println("Digite o código do caderno");
            String codigoCaderno = LeitoraDados.lerDado();
            produtoNegocio.excluir(codigoCaderno);
            break;
          case "6":
            System.out.println("Digite a materia do caderno");
            String materia = LeitoraDados.lerDado();
            System.out.println(produtoNegocio.buscarCadernoPorMateria(materia));
            break;
          case "7":
            Pedido pedido = LeitoraDados.lerPedido(banco);
            Optional<Cupom> cupom = LeitoraDados.lerCupom(banco);

            if (cupom.isPresent()) {
              pedidoNegocio.salvar(pedido, cupom.get(), clienteLogado);
            } else {
              pedidoNegocio.salvar(pedido);
            }
            break;
          case "8":
            System.out.println("Digite o código do pedido");
            String codigoPedido = LeitoraDados.lerDado();
            pedidoNegocio.excluir(codigoPedido);
            break;
          case "9":
            produtoNegocio.listarTodos();
            break;
          case "10":
            pedidoNegocio.litarTodosPedidos();
            break;
          case "11":
            System.out.println(String.format("Volte sempre %s!", clienteLogado.getNome()));
            clienteLogado = null;
            break;
          case "12":
            System.out.println("Aplicação encerrada.");
            System.exit(0);
            break;
          default:
            System.out.println("Opção inválida.");
            break;
        }
      }
    }
  }


   private static void identificarUsuario(String cpf) {

        Optional<Cliente> resultado = clienteNegocio.consultar(cpf);

        if (resultado.isPresent()) {

            Cliente cliente = resultado.get();
            System.out.println(String.format("Olá %s! Você está logado.", cliente.getNome()));
            clienteLogado = cliente;
        } else {
            System.out.println("Usuário não cadastrado.");
            System.exit(0);
        }
    }

}


