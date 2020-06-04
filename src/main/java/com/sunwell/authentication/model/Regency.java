/**
 * Regency.java
 * 
 * Created on April 1, 2014, 12:41
 */
package com.sunwell.authentication.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Yoga
 */
@Entity
@Table(name = "regency")
//@NamedQueries({
//    @NamedQuery(name = "Regency.findAll", query = "SELECT r FROM Regency r ORDER BY r.name"),
//    @NamedQuery(name = "Regency.findBySystemid", query = "SELECT r FROM Regency r WHERE r.systemid = :systemid"),
//    @NamedQuery(name = "Regency.findByName", query = "SELECT r FROM Regency r WHERE r.name = :name"),
//    @NamedQuery(name = "Regency.findByProvince", query = "SELECT r FROM Regency r WHERE r.province = :prov")})
public class Regency implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator (name = "regency_systemid_seq", sequenceName = "regency_systemid_seq", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "regency_systemid_seq" )
    @Column(name = "systemid")
    private long systemId;
    
    @NotNull(message="{error_no_name}")
    @Column(name = "name")
    private String name;
    
    @NotNull(message="{error_no_province}")
    @JoinColumn(name = "prov_id", referencedColumnName = "systemid")
    @ManyToOne(fetch = FetchType.EAGER)
    private Province province;

    public Regency ()
    {
    }

    public Regency (long systemid)
    {
        this.systemId = systemid;
    }

    public long getSystemid ()
    {
        return systemId;
    }

    public void setSystemid (long systemid)
    {
        this.systemId = systemid;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Province getProvince ()
    {
        return province;
    }

    public void setProvince (Province provId)
    {
        this.province = provId;
    }

    @Override
    public int hashCode ()
    {
    	return (int)systemId;
    }

    @Override
    public boolean equals (Object object)
    {
    	if(object == null)
    		return false;
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Regency)) {
            return false;
        }
        Regency other = (Regency) object;
        return systemId == other.systemId;
    }

    @Override
    public String toString ()
    {
        return name;
    }
    
}
