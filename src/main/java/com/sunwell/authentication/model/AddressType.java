package com.sunwell.authentication.model;
///**
// * AddressType.java
// *
// * Created on March 11, 2014
// */
//package sunwell.permaisuri.core.entity.contact;
//
//import java.io.Serializable;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
//import javax.validation.constraints.NotNull;
//
///**
// * @author Irfin
// * @version 1.0
// * @created 11-Mar-2014 15:48:18
// */
//@Entity
//@Table (name = "addresstype",
//        uniqueConstraints = @UniqueConstraint(columnNames = {"typename"}))
////@NamedQueries({
////    @NamedQuery(name = "AddressType.findAll", query = "SELECT h FROM AddressType h ORDER BY h.systemId"),
////    @NamedQuery(name = "AddressType.findBySystemid", query = "SELECT h FROM AddressType h WHERE h.systemId = :systemid"),
////    @NamedQuery(name = "AddressType.findByName", query = "SELECT tt FROM AddressType tt WHERE tt.typeName = :nama")})
//public class AddressType implements Serializable
//{
//    public static final int TYPE_HOME_ADDR = 1;
//    public static final int TYPE_OFFICE_ADDR = 2;
//    
////    public static final String NAME_HOME_ADDR = "Home";
////    public static final String NAME_OFFICE_ADDR = "Office";
//    
//    
//    @Id
//    @SequenceGenerator (name = "AddressTypeSeqGen", allocationSize = 1, sequenceName = "addresstype_systemid_seq")
//    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "AddressTypeSeqGen")
//    @Column (name = "systemid", updatable = true)
//    private int systemId;
//    
//    @NotNull(message="{error_no_address_type_name}")
//    @Column (name = "typename")
//    private String typeName;
//    
//    @Column (name = "memo")
//    private String memo;
//
//    public AddressType ()
//    {
//    }
//
//    /**
//     * @return the m_systemid
//     */
//    public int getSystemId ()
//    {
//        return systemId;
//    }
//
//    /**
//     * @param m_systemid the m_systemid to set
//     */
//    public void setSystemId (int m_systemid)
//    {
//        this.systemId = m_systemid;
//    }
//
//    /**
//     * @return the m_typename
//     */
//    public String getTyoeName ()
//    {
//        return typeName;
//    }
//
//    /**
//     * @param m_typename the m_typename to set
//     */
//    public void setYpeName (String m_typename)
//    {
//        this.typeName = m_typename;
//    }
//
//    /**
//     * @return the m_memo
//     */
//    public String getMemo ()
//    {
//        return memo;
//    }
//
//    /**
//     * @param m_memo the m_memo to set
//     */
//    public void setMemo (String m_memo)
//    {
//        this.memo = m_memo;
//    }
//
//    @Override
//    public int hashCode ()
//    {
////        return (systemId != null) ? systemId.hashCode () : 0;
//    	return systemId;
//    }
////
//    @Override
//    public boolean equals (Object obj)
//    {
//    	if(obj == null)
//    		return false;
//        if (!(obj instanceof AddressType))
//            return false;
//        
//        AddressType at = (AddressType) obj;
//        
//        return systemId == at.systemId;
//        
////        if(systemId != null) {
////        	if (at.getSystemId ().intValue () == this.systemId.intValue ())
////        		return false;
////        }
////        else {
////        	if(at.getSystemId() != null)
////        		return false;
////        }
//        
////        return true;
//    }
//
//    @Override
//    public String toString ()
//    {
//        return typeName;
//    }
//}
