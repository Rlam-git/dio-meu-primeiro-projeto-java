package model;

import database.Banco;
import entity.Caderno;
import entity.Livro;
import entity.Produto;

import java.util.List;
import java.util.Optional;

public class ProdutoNegocio {

    private Banco bancoDados;

    public ProdutoNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    public void salvar(Produto novoProduto) {

        String codigo = "PR%04d";
        codigo = String.format(codigo, bancoDados.getProdutos().length);
        novoProduto.setCodigo(codigo);

        boolean produtoRepetido = false;
        for (Produto produto: bancoDados.getProdutos()) {
            if (produto.getCodigo() == novoProduto.getCodigo()) {
                produtoRepetido = true;
                System.out.println("Produto já cadastrado.");
                break;
            }
        }

        if (!produtoRepetido) {
            this.bancoDados.adicionarProduto(novoProduto);
            System.out.println("Produto cadastrado com sucesso.");
        }
    }

    public void excluir(String codigo) {
      Produto[] produtos = bancoDados.getProdutos();
      for(int i = 0; i < produtos.length; i++){
        if(produtos[i].getCodigo().equals(codigo)){
          bancoDados.removerProduto(i);
        }
      }
    }

    public Optional<Produto> consultar(String codigo) {

        for (Produto produto: bancoDados.getProdutos()) {

            if (produto.getCodigo().equalsIgnoreCase(codigo)) {
                return  Optional.of(produto);
            }
        }

        return Optional.empty();
    }

    public void listarTodos() {

        if (bancoDados.getProdutos().length == 0) {
            System.out.println("Não existem produtos cadastrados");
        } else {

            for (Produto produto: bancoDados.getProdutos()) {
                System.out.println(produto.toString());
            }
        }
    }

    public Produto buscarLivroPorNome(String nome){
      Produto[] produtos = bancoDados.getProdutos();
      Produto produto ;
      for (int i = 0; i < produtos.length; i++) {
       if(produtos[i].getClass().equals(Livro.class)){
         Livro livro = (Livro) produtos[i];
         if(livro.getNome().equals(nome)){
           produto = produtos[i];
           return produto;
         }
       }
      }
     return null;
    }

    public Produto buscarCadernoPorMateria(String materia){
      Produto[] produtos = bancoDados.getProdutos();
      Produto produto ;
      for (int i = 0; i < produtos.length; i++) {
       if(produtos[i].getClass().equals(Caderno.class)){
         Caderno caderno = (Caderno) produtos[i];
         if(caderno.getMateria().equals(materia)){
           produto = produtos[i];
           return produto;
         }
       }
      }
     return null;
    }


}
