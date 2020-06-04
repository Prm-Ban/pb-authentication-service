/**
 * Contact.java
 *
 * Created on March 1, 2014
 */
package com.sunwell.authentication.model;

import java.io.Serializable

;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Inheritance (strategy = InheritanceType.JOINED)
@Table (name = "contact")
public class Contact implements Serializable
{
    @Transient
    public static final int GENDER_MALE = 0;
    @Transient
    public static final int GENDER_FEMALE = 1;
    
    @Transient
    public static final int MS_SINGLE = 0;
    @Transient
    public static final int MS_MARRIED = 1;
    /** Duda */
    @Transient
    public static final int MS_WIDOWER = 2;
    /** Janda */
    @Transient
    public static final int MS_WIDOW = 3;
    
    @Id
    @SequenceGenerator (name = "contact_systemid_seq", sequenceName = "contact_systemid_seq", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "contact_systemid_seq" )
    @Column (name = "systemid")
    private long systemId;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="usercred_id")
	private UserCredential userCredential;
    
    @NotNull(message="{error_no_first_name}")
    @Column (name = "firstname")
    private String firstName;
    
    @Column (name = "lastname")
    private String lastName;
    
    @Column (name = "birthdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar birthDate;
    
    @Column (name = "phone")
    private String phone;
    
    @Column (name = "email")
//    @Pattern(regexp="[a-z0-9!#$%&’*+/=?^_‘{|}~-]+(?:\\." +
//                    "[a-z0-9!#$%&’*+/=?^_‘{|}~-]+)*@" +
//                    "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
//                    message="{invalid.email}")
    private String email;
    
    @Column (name = "citizenid")
    private String citizenId;
    
    @Column (name = "citizenid_type")
    private int citizenIdType;
    
    @Column(name="type")
    private String type;
    
    @OneToMany (mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch (FetchMode.SELECT)
    private List<ContactAddress> addresses;
 
    
    public Contact()
    {
    }
    
    public Contact (long _sysId)
    {
        systemId = _sysId;
    }
    
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
    public void setSystemId (long _systemid)
    {
        this.systemId = _systemid;
    }

    /**
     * @return the m_firstname
     */
    public String getFirstName ()
    {
        return firstName;
    }

    /**
     * @param m_firstname the m_firstname to set
     */
    public void setFirstName(String m_firstname)
    {
        this.firstName = m_firstname;
    }


    /**
     * @return the m_lastname
     */
    public String getLastName ()
    {
        return lastName;
    }

    /**
     * @param m_lastname the m_lastname to set
     */
    public void setLastName (String m_lastname)
    {
        this.lastName = m_lastname;
    }
    
    public String getPhone ()
	{
		return phone;
	}

	public void setPhone (String _phone)
	{
		phone = _phone;
	}

	public Calendar getBirthDate ()
	{
		return birthDate;
	}

	public void setBirthDate (Calendar _birthDate)
	{
		birthDate = _birthDate;
	}
    

    /**
     * @return the m_email
     */
    public String getEmail ()
    {
        return email;
    }

    /**
     * @param m_email the m_email to set
     */
    public void setEmail (String m_email)
    {
        this.email = m_email;
    }

    
    public int getCitizenIdType ()
    {
        return citizenIdType;
    }
    
    public void setCitizenIdType (int m_citizenid)
    {
        this.citizenIdType = m_citizenid;
    }        
            

    /**
     * @return the m_citizenid
     */
    public String getCitizenId ()
    {
        return citizenId;
    }

    /**
     * @param m_citizenid the m_citizenid to set
     */
    public void setCitizenId (String m_citizenid)
    {
        this.citizenId = m_citizenid;
    }

    
    public void setAddresses (List<ContactAddress> _setCA)
    {
        addresses = _setCA;
        if (addresses == null)
            return;
        
        ContactAddress ca;
        for (Iterator<ContactAddress> it = addresses.iterator(); it.hasNext();) {
            ca = it.next ();
            ca.setOwner (this);
        }
    }
    
    /**
     * Jika dibutuhkan untuk menduplikat ContactAddress milik objek ini, maka
     * gunakan method clone() yg disediakan oleh kelas ContactAddress.
     * 
     * @return 
     */
    public List<ContactAddress> getAddresses ()
    {
        return addresses;
    }
    
    public UserCredential getUserCredential ()
	{
		return userCredential;
	}

	public void setUserCredential (UserCredential _userCredential)
	{
		userCredential = _userCredential;
	}

	public String getType ()
	{
		return type;
	}

	public void setType (String _type)
	{
		type = _type;
	}
    
    @Override
    public String toString ()
    {
        StringBuilder sb = new StringBuilder (firstName);
        if (lastName != null)
            sb.append (" ").append (lastName);
        
        return sb.toString ();
    }

    
}
