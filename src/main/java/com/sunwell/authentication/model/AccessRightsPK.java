package com.sunwell.authentication.model;
///*
// * AccessRightsPK.java
// *
// * Created on Feb 6, 2015, 1:05:55 PM
// */
//package sunwell.permaisuri.core.entity.cred;
//
//import java.io.Serializable;
//import java.util.Objects;
//
///**
// *
// * @author irfin
// */
//public class AccessRightsPK implements Serializable
//{
//    private int owner;
//    private int taskType;
//
//    @Override
//    public int hashCode ()
//    {
////        int hash = 7;
////        hash = 89 * hash + Objects.hashCode (this.getOwner());
////        hash = 89 * hash + Objects.hashCode (this.getTaskType());
////        return hash;
//    	
////    	int hash = 0;
////        hash += owner != null ? owner.hashCode () : 0;
////        hash += taskType != null ? taskType.hashCode() : 0;
////        return hash;
//    	return owner + taskType;
//    }
//
//    @Override
//    public boolean equals (Object obj)
//    {
//        if (obj == null)
//            return false;
//        if (getClass () != obj.getClass ())
//            return false;
//        
//        final AccessRightsPK that = (AccessRightsPK) obj;
//        if (!Objects.equals (this.getOwner(), that.getOwner()))
//            return false;
//        if (!Objects.equals (this.getTaskType(), that.getTaskType()))
//            return false;
//        
//        return true;
//    }
//
//	public int getOwner ()
//	{
//		return owner;
//	}
//
//	public void setOwner (int _owner)
//	{
//		owner = _owner;
//	}
//
//	public int getTaskType ()
//	{
//		return taskType;
//	}
//
//	public void setTaskType (int _taskType)
//	{
//		taskType = _taskType;
//	}
//}
