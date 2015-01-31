/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mo.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author hasee
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Administrator.FindAdministratorById",query="select a from Administrator a where a.id=:id"),
    @NamedQuery(name="Administrator.FindAdministrator",query="select a from Administrator a where a.email=:email and a.password=:password"),
})
public class Administrator extends Users implements Serializable {
    private static final long serialVersionUID = 1L;

    private String password;
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
