package model;

import database.Banco;
import entity.Cliente;
import entity.Cupom;
import entity.Pedido;
import entity.Produto;

import java.time.LocalDate;
import java.util.List;


public class PedidoNegocio {

    private Banco bancoDados;

    public PedidoNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    private double calcularTotal(List<Produto> produtos, Cupom cupom) {

        double total = 0.0;
        for (Produto produto: produtos) {
            total += produto.calcularFrete();
        }

        if (cupom != null) {
            return  total * (1 - cupom.getDesconto());
        } else {
            return  total;
        }
    }

    public void salvar(Pedido novoPedido) {
        salvar(novoPedido, null, null);
    }

    public void salvar(Pedido novoPedido, Cupom cupom, Cliente cliente) {

        LocalDate data = LocalDate.now();
        String codigo = "PE%4d%2d%84d";
        codigo = String.format(codigo,data.getYear(),data.getMonthValue(),bancoDados.getPedidos().length);
        novoPedido.setTotal(calcularTotal(novoPedido.getProdutos(),cupom));
        novoPedido.setCodigo(codigo);
        novoPedido.setCliente(cliente);
        bancoDados.adicionarPedido(novoPedido);
        System.out.println("Pedido salvo com sucesso");

    }

    public void excluir(String codigo) {

        int pedidoExclusao = -1;
        for (int i = 0; i < bancoDados.getPedidos().length; i++) {

            Pedido pedido = bancoDados.getPedidos()[i];
            if (pedido.getCodigo().equals(codigo)) {
                pedidoExclusao = i;
                break;
            }
        }

        if (pedidoExclusao != -1) {
            bancoDados.removerPedido(pedidoExclusao);
            System.out.println("Pedido excluído com sucesso.");
        } else {
            System.out.println("Pedido inexistente.");
        }
    }

    public void litarTodosPedidos(){
      for (Pedido pedido: bancoDados.getPedidos()){
        System.out.println(pedido.toString());
      }
    }

    /**
     * Lista todos os pedidos realizados.
     */
    //TODO Método de listar todos os pedidos

}
