package blog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

/**
 * Created by rafa on 02/10/18.
 */
@Entity
public class Texto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String texto;
    private Instant datahora;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Instant getDatahora() {
        return datahora;
    }

    public void setDatahora(Instant datahora) {
        this.datahora = datahora;
    }
}
