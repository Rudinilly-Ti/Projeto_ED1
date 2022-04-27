package model.entities;
import java.util.Date;

public class Tarefa {
  
  private Integer id;
  private String cabecalho;
  private String conteudo;
  private Date data;
  private boolean favorito;
  private Usuario usuario;
  
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getCabecalho() {
    return cabecalho;
  }
  public void setCabecalho(String cabecalho) {
    this.cabecalho = cabecalho;
  }
  public String getConteudo() {
    return conteudo;
  }
  public void setConteudo(String conteudo) {
    this.conteudo = conteudo;
  }
  public Date getData() {
    return data;
  }
  public void setData(Date data) {
    this.data = data;
  }
  public boolean isFavorito() {
    return favorito;
  }
  public void setFavorito(boolean favorito) {
    this.favorito = favorito;
  }
  public Usuario getUsuario() {
    return usuario;
  }
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Tarefa other = (Tarefa) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Tarefa [cabecalho=" + cabecalho + ", conteudo=" + conteudo + ", data=" + data + ", favorito=" + favorito
        + ", id=" + id + ", usuario=" + usuario + "]";
  }
}
