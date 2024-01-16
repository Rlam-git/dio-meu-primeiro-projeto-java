package entity.enumered;

public enum TipoCapa {

  DURA("dura"),
  COURO("couro");

  private final String tipo;

  TipoCapa(String tipo) {
    this.tipo = tipo;
  }

  public String getTipo() {
    return tipo;
  }
}
