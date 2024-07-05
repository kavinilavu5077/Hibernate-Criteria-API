package com.model;
 import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
public class ProductCriteriaExample {
@SuppressWarnings("unchecked")
public static void main(String args[]) {
	SessionFactory sessionFactory = HBUtil.getSessionFactory();
	Session session = sessionFactory.getCurrentSession();
	Transaction tx = session.beginTransaction();
	
	Criteria criteria = session.createCriteria(Product.class);
	List<Product> prdList=criteria.list();
	for(Product prd : prdList) {
		System.out.println(prd.getSno()+prd.getProductName()+prd.getPrice()+" "+prd.getQty());
	}
	
	//greater than 500
	prdList =  session.createCriteria(Product.class).add(Restrictions.gt("price", 500)).list();
	System.out.println("Products with Price more than 500: ");
	for(Product prd1 : prdList) {
	System.out.println(prd1.getProductName()+" "+"price "+" "+prd1.getPrice());
}
	
	//qty greater than 10
	prdList = session.createCriteria(Product.class).add(Restrictions.gt("qty", 10)).list();
	System.out.println("More than 10 pcs products : ");
	for(Product prd1 : prdList) {
	System.out.println(prd1.getProductName()+" "+"qty"+" "+prd1.getQty());
}
	
	
	Criteria c = session.createCriteria(Product.class);
	c.setProjection(Projections.rowCount());
       prdList = c.add(Restrictions.gt("sno",0)).list();
	System.out.println("Number of products = "+prdList.getFirst());
	
	
	Criteria d = session.createCriteria(Product.class);
	
	prdList = d.setProjection(Projections.min("price")).list();
	System.out.println("Min of Salaries : 	"+prdList.getFirst());
	
	Criteria e = session.createCriteria(Product.class);
	
	prdList = e.setProjection(Projections.max("price")).list();
	System.out.println("Max of Salaries : "+prdList.getFirst());
	
	Criteria f = session.createCriteria(Product.class);
	
	prdList = f.setProjection(Projections.sum("price")).list();
	System.out.print("Sum od Salaries : "+prdList.getFirst());
    tx.commit();
    sessionFactory.close();
}
	
}




