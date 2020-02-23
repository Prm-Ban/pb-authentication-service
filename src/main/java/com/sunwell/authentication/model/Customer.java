package com.sunwell.authentication.model;
//package com.sunwell.sales.model;
//
//
//
///*
// * Customer.java
// *
// * Created 22 April 2014
// */
//
//import java.io.Serializable;
//
//
//
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Inheritance;
//import javax.persistence.InheritanceType;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.EntityManager;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.TypedQuery;
//import javax.validation.constraints.NotNull;
//
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//
//
///**
// *
// * @author Daisy
// * @author Irfin
// */
//@Entity
//@Table(name = "customer")
//@Inheritance (strategy = InheritanceType.JOINED)
////@NamedQueries({
////    @NamedQuery(name="Customer.findBySystemId", query="SELECT c FROM Customer c WHERE c.systemId = :id"),
////    @NamedQuery(name="Customer.getAllSortedByFirstname", query="SELECT c FROM Customer c ORDER BY c.firstName"),
////    @NamedQuery(name="Customer.getAllSortedByLastname", query="SELECT c FROM Customer c ORDER BY c.lastName")
////})
//public class Customer extends Contact
//{
//    public static final int SORT_BY_FIRSTNAME = 1;
//    public static final int SORT_BY_LASTNAME = 2;
//     
//    
//    @OneToMany(mappedBy="customer", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//    @Fetch (FetchMode.SELECT)
//    private List<CartDetail> cartDetails;
//    
//    @Column(name="timezone")
//    private float timezone;
//
//    
//    public Customer() {
//    	
//    }
//    
//    public Customer(long _id)
//    {
//    	super(_id);
//    }
//    
// 
//
//	public List<CartDetail> getCartDetails ()
//	{
//		return cartDetails;
//	}
//
//	public void setCartDetails (List<CartDetail> _cartDetails)
//	{
//		cartDetails = _cartDetails;
//	}
//
//	public float getTimezone ()
//	{
//		return timezone;
//	}
//
//	public void setTimezone (float _timezone)
//	{
//		timezone = _timezone;
//	}
//
//
//}
