package model;

import database.Banco;
import entity.Cliente;


import java.util.Optional;
import java.util.Scanner;

public class ClienteNegocio {

    private Banco bancoDados;

    public ClienteNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    public Optional<Cliente> consultar(String cpf) {

      return this.bancoDados.getClientes().stream()
        .filter(c -> c.getCpf().equals(cpf))
        .findFirst();
    }

    public void cadastrarCliente(Scanner sc){
      Cliente cliente = new Cliente();

      System.out.println("============================");
      System.out.println("===   Cadastro Cliente   ===");
      System.out.println("============================");
      System.out.print("Nome:");
      cliente.setNome(sc.nextLine());
      System.out.print("Cpf:");
      cliente.setCpf(sc.nextLine());

      if(consultar(cliente.getCpf()).isPresent()){
        System.out.println("** Cliente já cadastrado ** ");
      }else {
        bancoDados.adicionarCliente(cliente);
        System.out.println("** Cliente cadastrado ** ");
      }
    }

   public Cliente login(Scanner sc){

    System.out.println("============================");
    System.out.println("===         LOGIN        ===");
    System.out.println("============================");
    System.out.print(  "Digite o cpf: ");
    String cpf = sc.nextLine();
    Optional<Cliente> cliente = consultar(cpf);

    if(cliente.isPresent()){
      return cliente.get();
    }else {
      System.out.println("** cliente não encontrado... **");
      return null;
    }
  }

    public void excluirCliente(Scanner sc){
      System.out.println("============================");
      System.out.println("===   Exclusão Cliente   ===");
      System.out.println("============================");

      System.out.print("Cpf:");
      String cpf = sc.nextLine();

      bancoDados.removerClientePorCpf(cpf);

    }

    /**
     * Exclui um cliente específico.
     * @param cpf CPF do cliente
     */
    //TODO Fazer a exclusão de cliente

}
