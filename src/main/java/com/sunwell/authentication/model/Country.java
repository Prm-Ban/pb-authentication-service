/**
 * Country.java
 * 
 * Created on 2007
 */
package com.sunwell.authentication.model;

import java.io.Serializable;

import java.sql.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * 
 * @version 1.0 - 2007
 * @version 2.0 - March 20, 2014 ; implementasi JPA.
 * 
 * @author Irfin
 */
@Entity
@Table (name = "country")
//@NamedQueries({
//    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c ORDER BY c.name"),
//    @NamedQuery(name = "Country.findByISOcode", query = "SELECT c FROM Country c WHERE c.isoCodeS2 = :code"),
//    @NamedQuery(name = "Country.findByName", query = "SELECT c FROM Country c WHERE c.name = :name")})
public class Country 
{
    /**
     * 2 chars country ISO code (e.g ID for Indonesia) ; PRIMARY KEY
     */
	@NotNull(message="{error_no_id}")
    @Id
    @Column (name = "iso_code_s2")
    private String isoCodeS2;
    
    /**
     * Must be UNIQUE
     */
    @NotNull(message="{error_no_name}")
    @Column (name = "name")
    private String name;

    /**
     * @roseuid 4626638B00CB
     */
    public Country ()
    {
        isoCodeS2 = null;
        name = null;
    }

    public Country (String _isoCode, String _countryName)
    {
        isoCodeS2 = _isoCode;
        name = _countryName;
    }

    public String getIsoCodeS2 ()
    {
        return isoCodeS2;
    }

    public void setIsoCodeS2 (String m_iso_code_s2)
    {
        this.isoCodeS2 = m_iso_code_s2;
    }

    public String getCountryName ()
    {
        return name;
    }

    public void setCountryName (String m_name)
    {
        this.name = m_name;
    }

    @Override
    public int hashCode ()
    {
        return isoCodeS2.hashCode ();
    }

    @Override
    public boolean equals (Object obj)
    {
    	if(obj == null)
    		return false;
        if (!(obj instanceof Country))
            return false;
        
        Country other = (Country) obj;
        if ((this.isoCodeS2 == null && other.isoCodeS2 != null) ||
            (this.isoCodeS2 != null && !this.isoCodeS2.equals(other.isoCodeS2))) {
                return false;
        }
        
        return true;
        
    }
    
    @Override
    public String toString ()
    {
        return name;
    }
}
