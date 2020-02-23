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

//    @ManyToOne
//    @JoinColumn (name = "sys_creator")
//    private UserCredential sysCreator;
//
//    @Column (name = "sys_createdate")
//    @Temporal(javax.persistence.TemporalType.DATE)
//    private Calendar sysCreateDate;
//
//    @ManyToOne
//    @JoinColumn (name = "sys_lastupdater")
//    private UserCredential sysLastUpdater;
//
//    @Column (name = "sys_lastupdate")
//    @Temporal(javax.persistence.TemporalType.DATE)
//    private Calendar sysLastUpdate;
    

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

    /**
     * @return the m_sys_creator
     */
//    public UserCredential getSystemCreator ()
//    {
//        return sysCreator;
//    }
//
//    /**
//     * @param m_systemcreator the m_sys_creator to set
//     */
//    public void setSystemCreator (UserCredential m_systemcreator)
//    {
//        this.sysCreator = m_systemcreator;
//    }
//
//    /**
//     * @return the m_sys_createdate
//     */
//    public Calendar getSystemCreateDate ()
//    {
//        return sysCreateDate;
//    }
//
//    /**
//     * @param m_systemcreatedate the m_sys_createdate to set
//     */
//    public void setSystemCreateDate (Calendar m_systemcreatedate)
//    {
//        this.sysCreateDate = m_systemcreatedate;
//    }

//    /**
//     * @return the m_sys_lastupdater
//     */
//    public UserCredential getSystemlastUpdater ()
//    {
//        return sysLastUpdater;
//    }
//
//    /**
//     * @param m_systemlastupdater the m_sys_lastupdater to set
//     */
//    public void setSystemlastUpdater (UserCredential m_systemlastupdater)
//    {
//        this.sysLastUpdater = m_systemlastupdater;
//    }
//
//    /**
//     * @return the m_sys_lastupdate
//     */
//    public Calendar getSystemLastUpdate ()
//    {
//        return sysLastUpdate;
//    }
//
//    /**
//     * @param m_systemlastupdate the m_sys_lastupdate to set
//     */
//    public void setSystemLastUpdate (Calendar m_systemlastupdate)
//    {
//        this.sysLastUpdate = m_systemlastupdate;
//    }

    @Override
    public int hashCode ()
    {
        //return super.hashCode (); //To change body of generated methods, choose Tools | Templates.
//        return (systemId == null ? 0 : systemId.hashCode ());
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
