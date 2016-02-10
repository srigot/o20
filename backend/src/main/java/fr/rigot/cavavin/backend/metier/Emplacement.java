package fr.rigot.cavavin.backend.metier;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by a145426 on 08/02/2016.
 */
@Entity
public class Emplacement {
    @Id
    private String id;
//    private Ref<Vin> vin ;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
/*
    public Long getVin() {
        return (null == vin) ? null: vin.getKey().getId() ;
    }

    public void setVin(Long idVin) {
        if (idVin == null) {
            vin = null;
        } else {
            Key<Vin> vinKey = Key.create(Vin.class, idVin) ;
            vin = Ref.create(vinKey) ;
        }
    }
*/
}
