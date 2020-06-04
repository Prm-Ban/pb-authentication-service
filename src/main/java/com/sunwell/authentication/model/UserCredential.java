/**
 * UserCredential.java
 *
 * Created on March 24, 2014
 */
package com.sunwell.authentication.model;

import java.io.BufferedInputStream;

import java.io.Serializable;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

@Entity
@Table (name = "usercredential")
@NamedQueries({
    @NamedQuery(name="UserCredential.findByUsername", query = "SELECT u FROM UserCredential u WHERE u.userName = :uname"),
    @NamedQuery(name="UserCredential.findBySystemId", query = "SELECT u FROM UserCredential u WHERE u.systemId = :sysid"),
    @NamedQuery(name="UserCredential.findAll", query="SELECT u FROM UserCredential u ORDER BY u.userName")
})
public class UserCredential implements Serializable
{       
	public final static String TYPE_ADMIN = "TYPE ADMIN";
	public final static String TYPE_CUSTOMER = "TYPE CUSTOMER";
	public final static String TYPE_SALES_OFFICER = "TYPE SALES OFFICER";
	public final static String TYPE_DRIVER = "TYPE DRIVER";
	
	public final static String TASK_LOGIN = "LOGIN";
	public final static String TASK_LOGOUT = "LOGOUT";
	
	public final static String TASK_USERS = "USERS";
	public final static String TASK_VIEW_USERS = "VIEW USERS";
	public final static String TASK_CREATE_USERS = "CREATE USERS";
	public final static String TASK_UPDATE_USERS = "UPDATE USERS";
	public final static String TASK_DELETE_USERS = "DELETE USERS";
	
	public final static String TASK_USER_GROUPS = "USER GROUPS";
	public final static String TASK_VIEW_USER_GROUPS= "VIEW USER GROUPS";
	public final static String TASK_CREATE_USER_GROUPS = "CREATE USER GROUPS";
	public final static String TASK_UPDATE_USER_GROUPS = "UPDATE USER GROUPS";
	public final static String TASK_DELETE_USER_GROUPS = "DELETE USER GROUPS";
	
	public final static String TASK_CUSTOMERS = "CUSTOMERS";
	public final static String TASK_VIEW_CUSTOMERS = "VIEW CUSTOMERS";
	public final static String TASK_CREATE_CUSTOMERS = "CREATE CUSTOMERS";
	public final static String TASK_UPDATE_CUSTOMERS = "UPDATE CUSTOMERS";
	public final static String TASK_DELETE_CUSTOMERS = "DELETE CUSTOMERS";
	
	public final static String TASK_SALES_OFFICERS = "SALES OFFICERS";
	public final static String TASK_VIEW_SALES_OFFICERS = "VIEW SALES OFFICERS";
	public final static String TASK_CREATE_SALES_OFFICERS = "CREATE SALES OFFICERS";
	public final static String TASK_UPDATE_SALES_OFFICERS = "UPDATE SALES OFFICERS";
	public final static String TASK_DELETE_SALES_OFFICERS = "DELETE SALES OFFICERS";
	
	public final static String TASK_PRODUCTS = "PRODUCTS";
	public final static String TASK_VIEW_PRODUCTS = "VIEW PRODUCTS";
	public final static String TASK_CREATE_PRODUCTS = "CREATE PRODUCTS";
	public final static String TASK_UPDATE_PRODUCTS = "UPDATE PRODUCTS";
	public final static String TASK_DELETE_PRODUCTS = "DELETE PRODUCTS";
	
	public final static String TASK_STOCKS = "STOCKS";
	public final static String TASK_VIEW_STOCKS = "VIEW STOCKS";
	public final static String TASK_CREATE_STOCKS = "CREATE STOCKS";
	public final static String TASK_UPDATE_STOCKS = "UPDATE STOCKS";
	public final static String TASK_DELETE_STOCKS = "DELETE STOCKS";
	
	public final static String TASK_CATEGORIES = "CATEGORIES";
	public final static String TASK_VIEW_CATEGORIES = "VIEW CATEGORIES";
	public final static String TASK_CREATE_CATEGORIES = "CREATE CATEGORIES";
	public final static String TASK_UPDATE_CATEGORIES = "UPDATE CATEGORIES";
	public final static String TASK_DELETE_CATEGORIES = "DELETE CATEGORIES";
	
	public final static String TASK_MERKS = "MERKS";
	public final static String TASK_VIEW_MERKS = "VIEW MERKS";
	public final static String TASK_CREATE_MERKS = "CREATE MERKS";
	public final static String TASK_UPDATE_MERKS = "UPDATE MERKS";
	public final static String TASK_DELETE_MERKS = "DELETE MERKS";
	
	public final static String TASK_SALES_ORDERS = "SALES ORDERS";
	public final static String TASK_VIEW_SALES_ORDERS= "VIEW SALES ORDERS";
	public final static String TASK_CREATE_SALES_ORDERS = "CREATE SALES ORDERS";
	public final static String TASK_UPDATE_SALES_ORDERS = "UPDATE SALES ORDERS";
	public final static String TASK_DELETE_SALES_ORDERS = "DELETE SALES ORDERS";
	
	public final static String TASK_SALES_INVOICES = "SALEs INVOICES";
	public final static String TASK_VIEW_SALES_INVOICES= "VIEW SALES INVOICES";
	public final static String TASK_CREATE_SALES_INVOICES = "CREATE SALES INVOICES";
	public final static String TASK_UPDATE_SALES_INVOICES = "UPDATE SALES INVOICES";
	public final static String TASK_DELETE_SALES_INVOICES = "DELETE SALES INVOICES";
	
	public final static String TASK_CART_DETAILS = "CART DETAILS";
	public final static String TASK_VIEW_CART_DETAILS= "VIEW CART DETAILS";
	public final static String TASK_CREATE_CART_DETAILS = "CREATE CART DETAILS";
	public final static String TASK_UPDATE_CART_DETAILS = "UPDATE CART DETAILS";
	public final static String TASK_DELETE_CART_DETAILS = "DELETE CART DETAILS";
	
	public final static String TASK_CHECK_OUT = "CHECK OUT";
	public final static String TASK_VIEW_PRODUCT_IMAGE = "VIEW PRODUCT IMAGE";
	public final static String TASK_VIEW_PAYMENT_IMAGE = "VIEW PAYMENT IMAGE";
	
    @Id
    @SequenceGenerator (name = "usercredential_systemid_seq", sequenceName = "usercredential_systemid_seq", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "usercredential_systemid_seq" )
    @Column (name = "systemid")
    private int systemId;
    
    @NotNull(message="{error_no_user_name}")
    @Column (name = "username")
    private String userName;
    
    @NotNull(message="{error_no_password}")
    @Column (name = "pwd")
    private String pwd;
    
    @Column (name = "isenable")
    private boolean isEnable;
    
    @Column (name = "expiredate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar expireDate;
    
    @Column(name="registration_token")
    private String registrationToken;
    
    @Column (name = "notes")
    private String notes;
    
    @Column (name = "sys_create_ip")
    private String sysCreateIp;
    
    @Column (name = "socialmedia_type")
    private String socialMediaTYpe;
    
    @Column (name = "sys_last_login")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar sysLastLogin;

    public UserCredential ()
    {
    }
    
    public UserCredential (int _id)
    {
        systemId = _id;
        
    }

    /**
     * @return the m_systemid
     */
    public int getSystemId ()
    {
        return systemId;
    }

    /**
     * @param m_systemid the m_systemid to set
     */
    public void setSystemId (int m_systemid)
    {
        this.systemId = m_systemid;
    }

    /**
     * @return the m_username
     */
    public String getUserName ()
    {
        return userName;
    }

    /**
     * @param m_username the m_username to set
     */
    public void setUserName (String m_username)
    {
        this.userName = m_username;
    }

    /**
     * @return the m_pwd
     */
    public String getPwd ()
    {
        return pwd;
    }

    /**
     * String password yang dilewatkan akan di-MD5 dan yg disimpan sebagai atribut
     * adalah hasil MD5-nya.
     * 
     * @param _pwd the m_pwd to set
     */
    public void setPwd (String _pwd)
    {
        this.pwd = _pwd;
    }

    /**
     * @return the m_systemaccessenabled
     */
    public boolean isEnabled ()
    {
        return isEnable;
    }

    /**
     * @param m_systemaccessenabled the m_systemaccessenabled to set
     */
    public void setEnabled (boolean _enabled)
    {
        this.isEnable = _enabled;
    }


    /**
     * @return the m_expiredate
     */
    public Calendar getExpireDate ()
    {
        return expireDate;
    }

    /**
     * @param m_expiredate the m_expiredate to set
     */
    public void setExpireDate (Calendar _exp)
    {
        this.expireDate = _exp;
    }

    /**
     * @return the m_notes
     */
    public String getNotes ()
    {
        return notes;
    }

    /**
     * @param m_notes the m_notes to set
     */
    public void setNotes (String m_notes)
    {
        this.notes = m_notes;
    }
    
    public void setSysCreateIp (String _sysCreateIp)
	{
		sysCreateIp = _sysCreateIp;
	}
	
	public String getSysCreateIp() {
		return sysCreateIp;
	}

	public String getSocialMediaTYpe ()
	{
		return socialMediaTYpe;
	}

	public void setSocialMediaTYpe (String _socialMediaTYpe)
	{
		socialMediaTYpe = _socialMediaTYpe;
	}

	public Calendar getSysLastLogin ()
	{
		return sysLastLogin;
	}

	public void setSysLastLogin (Calendar _sysLastLogin)
	{
		sysLastLogin = _sysLastLogin;
	}
	
	public String getRegistrationToken ()
	{
		return registrationToken;
	}

	public void setRegistrationToken (String _registrationToken)
	{
		registrationToken = _registrationToken;
	}


    @Override
    public int hashCode ()
    {
    	return systemId;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (!(obj instanceof UserCredential)) {
            return false;
        }
        
        UserCredential other = (UserCredential) obj;
        return systemId == other.systemId;
    }

	
}
