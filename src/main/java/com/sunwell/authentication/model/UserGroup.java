package com.sunwell.authentication.model;
///**
// * UserGroup.java
// *
// * Created on March 24, 2014
// */
//package sunwell.permaisuri.core.entity.cred;
//
//import java.io.Serializable;
//
//import java.util.Calendar;
//import java.util.Collection;
//import java.util.List;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EntityManager;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
//import javax.persistence.Query;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.validation.constraints.NotNull;
//
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//
///**
// * @author Yoga
// * @author Irfin
// * @version 1.0
// * @created 24-Mar-2014 13:34:03
// */
//@Entity
//@Table (name = "usergroup")
//@NamedQueries ({
//    @NamedQuery(name="UserGroup.findBySystemId", query="SELECT u FROM UserGroup u WHERE u.systemId = :sysid"),
//    @NamedQuery(name="UserGroup.findAll", query="SELECT u FROM UserGroup u ORDER BY u.name"),
//    @NamedQuery(name="UserGroup.findByName", query="SELECT u FROM UserGroup u WHERE UPPER(u.name) = UPPER(:name) ")
//})
//public class UserGroup implements Serializable, Comparable<UserGroup>
//{
//    public static final int ADMINISTRATOR = 1;
//    public static final int SALES_OFFICER = 2;
//    public static final int CUSTOMER = 3;
////    public static final int KLINIK_RJ_INPUT = 2;
////    public static final int KLINIK_RJ_SUPERVISOR = 3;
////    public static final int APOTEK_INPUT = 4;
////    public static final int APOTEK_SUPERVISOR = 5;
////    public static final int KLINIK_RINAP_INPUT = 6;
////    public static final int KLINIK_RINAP_SUPERVISOR = 7;
////    public static final int ANCILLARY_LAB = 8;
//    
//    @Id
//    @SequenceGenerator (name = "usergroup_systemid_seq", sequenceName = "usergroup_systemid_seq", allocationSize = 1)
//    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "usergroup_systemid_seq" )
//    @Column (name = "systemid")
//    private int systemId;
//    
//    @NotNull(message="{error_no_group_name}")
//    @Column (name = "name")
//    private String name;
//    
//    @Column (name = "memo")
//    private String memo;
//    
//    @OneToMany (cascade = CascadeType.ALL, mappedBy = "owner", fetch=FetchType.EAGER)
//    @Fetch (FetchMode.SELECT)
//    private List<AccessRights> accessRights;
//    
//    @ManyToOne
//    @JoinColumn (name = "sys_creator")
//    private UserCredential sys_creator;
//    
//    @Column (name = "sys_createdate")
//    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
//    private Calendar sys_createdate;
//    
//    @ManyToOne
//    @JoinColumn (name = "sys_lastupdater")
//    private UserCredential sys_lastupdater;
//    
//    @Column (name = "sys_lastupdate")
//    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
//    private Calendar sys_lastupdate;
//
//    public UserGroup ()
//    {
//    }
//    
//    public UserGroup (int _systemId)
//    {
//    	systemId = _systemId;
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
//     * @return the m_name
//     */
//    public String getName ()
//    {
//        return name;
//    }
//
//    /**
//     * @param m_name the m_name to set
//     */
//    public void setName (String m_name)
//    {
//        this.name = m_name;
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
//    public void setAccessRights (List<AccessRights> _listAR)
//    {
//        accessRights = _listAR;
//        if (accessRights != null)
//            for (AccessRights ar : accessRights) {
//                ar.setOwner (this);
//            }
//    }
//    
//    public List<AccessRights> getAccessRights ()
//    {
//        return accessRights;
//    }
//    
//    /**
//     * Mengembalikan nilai access-bit untuk jenis aktivitas yang dilewatkan ke
//     * parameter {@code _taskType}. Jika tidak ditemukan jenis aktivitas yang dicari,
//     * maka method ini menganggap bahwa hak-akses memang tidak diberikan, dan 
//     * mengembalikan {@link AccessRights#BIT_NOACCESS} (nilai 0).
//     * 
//     * @param _taskType
//     * @return 
//     */
//    public int getAccessBitForTask (int _taskType)
//    {
//        if (accessRights == null)
//            return AccessRights.BIT_NOACCESS;
//        
//        for (AccessRights ar : accessRights) {
//            if (ar.getTaskType () == _taskType)
//                return ar.getAccessBit ();
//        }
//        
//        return AccessRights.BIT_NOACCESS;
//    }
//    
//    /**
//     * Memeriksa apakah objek UserGroup ini diberikan akses untuk melakukan aksi
//     * {@code _actionBit} pada aktivitas {@code _taskType}. Jika ya, nilai true
//     * dikembalikan, sebaliknya false.
//     * 
//     * @param _taskType gunakan konstanta yg dideklarasikan di {@link AccessRights}
//     * @param _actionBit gunakan konstanta yg dideklarasikan di {@link AccessRights}
//     * @return 
//     */
//    public boolean canPerform (int _taskType, int _actionBit)
//    {
//        if (accessRights == null)
//            return false;
//        
//        for (AccessRights ar : accessRights) {
//            if (ar.getTaskType () == _taskType) {
//                if ((ar.getAccessBit () & _actionBit) > AccessRights.BIT_NOACCESS)
//                    return true;
//            }
//        }
//        
//        return false;
//    }
//
//    /**
//     * @return the m_sys_creator
//     */
//    public UserCredential getSysCreator ()
//    {
//        return sys_creator;
//    }
//
//    /**
//     * @param m_sys_creator the m_sys_creator to set
//     */
//    public void setSysCreator (UserCredential m_sys_creator)
//    {
//        this.sys_creator = m_sys_creator;
//    }
//
//    /**
//     * @return the m_sys_createdate
//     */
//    public Calendar getSysCreateDate ()
//    {
//        return sys_createdate;
//    }
//
//    /**
//     * @param m_sys_createdate the m_sys_createdate to set
//     */
//    public void setSysCreateDate (Calendar m_sys_createdate)
//    {
//        this.sys_createdate = m_sys_createdate;
//    }
//
//    /**
//     * @return the m_sys_lastupdater
//     */
//    public UserCredential getSysLastUpdater ()
//    {
//        return sys_lastupdater;
//    }
//
//    /**
//     * @param m_sys_lastupdater the m_sys_lastupdater to set
//     */
//    public void setSysLastUpdater (UserCredential m_sys_lastupdater)
//    {
//        this.sys_lastupdater = m_sys_lastupdater;
//    }
//
//    /**
//     * @return the m_sys_lastupdate
//     */
//    public Calendar getSysLastUpdate ()
//    {
//        return sys_lastupdate;
//    }
//
//    /**
//     * @param m_sys_lastupdate the m_sys_lastupdate to set
//     */
//    public void setSysLastUpdate (Calendar m_sys_lastupdate)
//    {
//        this.sys_lastupdate = m_sys_lastupdate;
//    }
//    
//    @Override
//    public int compareTo (UserGroup _o)
//    {
//        return this.name.compareTo (_o.name);
//    }
//
//    @Override
//    public int hashCode ()
//    {
////        return systemId != null ? systemId.hashCode () : 0;
//    	return systemId;
//    }
//
//    @Override
//    public boolean equals (Object obj)
//    {
//    	if(obj == null)
//    		return false;
//        if (!(obj instanceof UserGroup)) {
//            return false;
//        }
//        
//        UserGroup other = (UserGroup) obj;
////        if ((this.systemId == null && other.systemId != null) || 
////            (this.systemId != null && !this.systemId.equals(other.systemId))) {
////            return false;
////        }
////        return true;
//        return systemId == other.systemId;
//    }
//    
////    public static boolean isNameAlreadyExists(String _ugName)
////    {
////        Query q = MainAppRuntime.getEntityManager ().
////                createNamedQuery ("UserGroup.findByName");
////        q.setParameter ("name", _ugName);
////        return q.getResultList ().size () < 1;
////    }
////    
////    /**
////     * Mengembalikan daftar UserGroup dengan sebelumnya cache JPA utk type kelas ini
////     * dikosongkan.
////     * @return 
////     */
////    public static List<UserGroup> getAll()
////    {
////        EntityManager em = MainAppRuntime.getEntityManager ();
////        em.getEntityManagerFactory ().getCache ().evict (UserGroup.class);
////        
////        return em.createNamedQuery ("UserGroup.findAll", UserGroup.class).getResultList ();
////    }
////
////    @Override
////    public String getSimpleObjectString ()
////    {
////        return m_name;
////    }
//    
//    @Override
//    public String toString ()
//    {
//        return name;
//    }
//}
