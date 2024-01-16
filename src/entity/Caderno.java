package entity;

import entity.enumered.TipoCapa;

public class Caderno extends Produto{

  private String folhas;

  private String materia;

  private TipoCapa tipoCapa;


  public Caderno() {
  }

  public String getFolhas() {
    return folhas;
  }

  public void setFolhas(String folhas) {
    this.folhas = folhas;
  }

  public String getMateria() {
    return materia;
  }

  public void setMateria(String materia) {
    this.materia = materia;
  }

  public TipoCapa getTipoCapa() {
    return tipoCapa;
  }

  public void setTipoCapa(TipoCapa tipoCapa) {
    this.tipoCapa = tipoCapa;
  }

  @Override
  public double calcularFrete() {
    return ( getPreco() * getQuantidade() ) * 0.5;
  }

  @Override
  public String toString() {
    return "Caderno{" +
      "folhas=" + folhas +
      ", materia=" + materia +
      ", tipoCapa=" + tipoCapa +
      '}';
  }
}
