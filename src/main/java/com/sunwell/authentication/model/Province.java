/**
 * Province.java
 * 
 * Created on April 1, 2014, 12:40
 */
package com.sunwell.authentication.model;

import java.io.Serializable;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Yoga
 */
@Entity (name = "province")
@Table (name = "province")
//@NamedQueries({
//    @NamedQuery(name = "Province.findAll", query = "SELECT p FROM province p ORDER BY p.name"),
//    @NamedQuery(name = "Province.findBySystemid", query = "SELECT p FROM province p WHERE p.systemId = :sysid"),
//    @NamedQuery(name = "Province.findByName", query = "SELECT p FROM province p WHERE p.name = :name")})
public class Province implements Serializable
{
    private static final long serialVersionUID = 1L;
	    
    @Id
    @SequenceGenerator (name = "province_systemid_seq", sequenceName = "province_systemid_seq", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "province_systemid_seq" )
    @Column(name = "systemid")
    private long systemId;
    
    @NotNull(message="{error_no_countr}")
	@JoinColumn(name = "country_code", referencedColumnName = "iso_code_s2")
    @ManyToOne
    private Country countryCode;
    
    @NotNull(message="{error_no_name}")
    @Column(name = "name")
    private String name;
    
    @OneToMany(mappedBy = "province", fetch = FetchType.EAGER)
    @Fetch (FetchMode.SELECT)
    private Set<Regency> regencies;

    public Province ()
    {
    }

    public Province (long systemid)
    {
        this.systemId = systemid;
    }

    public long getSystemId ()
    {
        return systemId;
    }

    public void setSystemId (long systemid)
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

    public Set<Regency> getRegencies ()
    {
        return regencies;
    }

    public void setRegencies (Set<Regency> regencySet)
    {
        this.regencies = regencySet;
    }

    @Override
    public int hashCode ()
    {
        return (int) systemId;
    }

    @Override
    public boolean equals (Object object)
    {
    	if(object == null)
    		return false;
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Province))
            return false;
        
        Province other = (Province) object;
        return systemId == other.systemId;
    }

    @Override
    public String toString ()
    {
        return name;
    }

    public Country getCountry ()
    {
        return countryCode;
    }

    public void setCountry (Country countryCode)
    {
        this.countryCode = countryCode;
    }
}
