/**
 * UserCredential.java
 *
 * Created on March 24, 2014
 */
package com.sunwell.authentication.dto;

import java.io.BufferedInputStream;




import java.io.Serializable;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import com.sunwell.authentication.model.UserCredential;

public class UserCredentialDTO extends StandardDTO implements Serializable
{    
    private Integer systemId;
//    private UserGroupDTO group;
    private String userName;
    private String pwd;
    private Calendar expiredate;
    private String notes;
    private String imgData;
    private String image;
    
    public UserCredentialDTO ()
    {
    }
    
    public UserCredentialDTO (UserCredential _uc)
    {
        setData(_uc);
    }
    
    public void setData(UserCredential _uc) {
		systemId = _uc.getSystemId();
//        if(_uc.getGroup() != null)
//        		group = new UserGroupDTO(_uc.getGroup());
        userName = _uc.getUserName();
//        pwd = _uc.getPwd();
        expiredate = _uc.getExpireDate();
        notes = _uc.getNotes();
//        setImage(_uc.getImage());
    }
    
    public UserCredential getData() {
    		UserCredential uc = new UserCredential();
    		if(systemId != null)
    			uc.setSystemId(systemId);
//    		if(group != null)
//    			uc.setGroup(group.getData());
    		uc.setUserName(userName);
    		uc.setPwd(pwd);
    		uc.setExpireDate(expiredate);
    		uc.setNotes(notes);
//    		uc.setImage(getImage());
    		return uc;
    }

    /**
     * @return the m_systemid
     */
    public Integer getSystemId ()
    {
        return systemId;
    }

    /**
     * @param m_systemid the m_systemid to set
     */
    public void setSystemId (Integer m_systemid)
    {
        this.systemId = m_systemid;
    }

//    /**
//     * @return the m_group
//     */
//    public UserGroupDTO getGroup ()
//    {
//        return group;
//    }
//
//    /**
//     * @param m_group the m_group to set
//     */
//    public void setGroup (UserGroupDTO _group)
//    {
//        this.group = _group;
//    }

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
     * @return the m_expiredate
     */
    public Calendar getExpireDate ()
    {
        return expiredate;
    }

    /**
     * @param m_expiredate the m_expiredate to set
     */
    public void setExpireDate (Calendar m_expiredate)
    {
        this.expiredate = m_expiredate;
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

	public String getImgData() {
		return imgData;
	}

	public void setImgData(String imgData) {
		this.imgData = imgData;
	}

	private String getImage() {
		return image;
	}

	private void setImage(String image) {
		this.image = image;
	}    
}
