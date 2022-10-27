
package entidades;


public class Notas {
    
     //Atributos
    protected String nota;
    protected String cuerpoTexto;

    public Notas(String nota, String cuerpoTexto) {
        this.nota = nota;
        this.cuerpoTexto = cuerpoTexto;
    }

    public String getNota() {
        return nota;
    }

    public String getCuerpoTexto() {
        return cuerpoTexto;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public void setCuerpoTexto(String cuerpoTexto) {
        this.cuerpoTexto = cuerpoTexto;
    }

  

    
    
}
