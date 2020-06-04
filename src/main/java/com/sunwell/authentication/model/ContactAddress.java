/*
 * ContactAddress.java
 *
 * Created on May, 2014.
 */
package com.sunwell.authentication.model;

import java.io.Serializable;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.persistence.*;
import javax.validation.constraints.NotNull;



/**
 *
 * @author Daisy
 * @author Irfin
 */
@Entity
@Table (name = "contactaddress")
//@NamedQueries({
//    @NamedQuery(name = "ContactAddress.deleteByHCid", query = "DELETE FROM ContactAddress WHERE owner.systemId = :id")
//})
public class ContactAddress implements Serializable, Cloneable
{
    @Id
    @SequenceGenerator (name = "contactaddress_systemid_seq", sequenceName = "contactaddress_systemid_seq", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "contactaddress_systemid_seq")
    @Column (name = "systemid")
    private long systemId;
    
    @ManyToOne
    @JoinColumn (name = "owner", referencedColumnName = "systemid")
    private Contact owner;

    @NotNull(message="{error_no_street}")
    @Column (name = "street")
    private String street;

    @ManyToOne
    @JoinColumn (name = "regency_id")
    private Regency regency;


    /**
     * @return the m_systemid
     */
    public long getSystemId ()
    {
        return systemId;
    }

    /**
     * @param m_systemid the m_systemid to set
     */
    public void setSystemId (long m_systemid)
    {
        this.systemId = m_systemid;
    }

    public void setOwner (Contact _c)
    {
        owner = _c;
    }

    public Contact getOwner ()
    {
        return owner;
    }

    /**
     * @return the m_fulladdress
     */
    public String getStreet ()
    {
        return street;
    }

    /**
     * @param m_street the m_fulladdress to set
     */
    public void setStreet (String m_street)
    {
        this.street = m_street;
    }

    public Regency getRegency ()
    {
        return regency;
    }

    public void setRegency (Regency _r)
    {
        regency = _r;
    }

    @Override
    public int hashCode ()
    {
    	return (int)systemId;
    }

    /**
     * Warning - this method won't work in the case the id fields are not set
     * @param _o
     * @return
     */
    @Override
    public boolean equals (Object _o)
    {
    	if(_o == null)
    		return false;
        if (!(_o instanceof ContactAddress)) {
            return false;
        }

        ContactAddress other = (ContactAddress) _o;
        return systemId == other.systemId;
    }
               
    /**
     * 
     * @return 
     */
    @Override
    public String toString ()
    {
        StringBuilder sb = new StringBuilder (street);
        
        if (regency != null)
            sb.append ("\n").
                append (regency.getName ()).
                append ("\n").
                append (regency.getProvince ().getName ()).
                append ("\n").
                append (regency.getProvince ().getCountry ().getCountryName ());
        
        return sb.toString ();
    }
}
