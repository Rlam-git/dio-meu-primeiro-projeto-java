package database;

import entity.Cliente;
import entity.Cupom;
import entity.Pedido;
import entity.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Banco {

  private List<Produto> produtos;
  private List<Pedido> pedidos;
  private List<Cupom> cupons;
  private List<Cliente> clientes;

  public Banco() {

    this.produtos = new ArrayList<>();
    this.pedidos = new ArrayList<>();
    this.clientes = new ArrayList<>();

    this.cupons = new ArrayList<>();
    cupons.add(new Cupom("CUPOM2", 2));
    cupons.add(new Cupom("CUPOM5", 5));
    cupons.add(new Cupom("CUPOM7", 7));
  }

  public Cupom[] getCupons() {
    return cupons.toArray(new Cupom[cupons.size()]);
  }
  public Pedido[] getPedidos() {
    return pedidos.toArray(new Pedido[pedidos.size()]);
  }
  public Produto[] getProdutos() {
    return produtos.toArray(new Produto[produtos.size()]);
  }
  public void adicionarProduto(Produto produto) {
    produtos.add(produto);
  }
  public void removerProduto(int posicao) {
    produtos.remove(posicao);
  }

  public void adicionarPedido(Pedido pedido) {
    pedidos.add(pedido);
  }
  public void removerPedido(int posicao) {
    pedidos.remove(posicao);
  }
  public void adicionarCliente(Cliente cliente) {
    clientes.add(cliente);
  }
  public List<Cliente> getClientes() {
    return clientes;
  }
  public void removerClientePorCpf(String cpf) {
    clientes.forEach(cliente -> {
      if(cliente.getCpf().equals(cpf)){
        clientes.remove(cliente);
      }
    });
  }

}
