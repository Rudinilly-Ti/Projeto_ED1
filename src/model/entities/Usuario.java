package model.entities;
public class Usuario {
  private Integer usuarioId;
  private String email;
  private String nome;
  private String senha;

  
  public Usuario() {
  }


  public Integer getUsuarioId() {
    return usuarioId;
  }


  public void setUsuarioId(Integer usuarioId) {
    this.usuarioId = usuarioId;
  }


  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public String getNome() {
    return nome;
  }


  public void setNome(String nome) {
    this.nome = nome;
  }


  public String getSenha() {
    return senha;
  }


  public void setSenha(String senha) {
    this.senha = senha;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((usuarioId == null) ? 0 : usuarioId.hashCode());
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
    Usuario other = (Usuario) obj;
    if (usuarioId == null) {
      if (other.usuarioId != null)
        return false;
    } else if (!usuarioId.equals(other.usuarioId))
      return false;
    return true;
  }


  @Override
  public String toString() {
    return "Usuario [email=" + email + ", nome=" + nome + ", senha=" + senha + ", usuarioId=" + usuarioId + "]";
  }


  public Object getId() {
    return null;
  }
}
