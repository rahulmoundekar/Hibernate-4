package com.app.client;

import org.dom4j.dom.DOMNodeHelper.EmptyNodeList;
import org.hibernate.Session;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.internal.SessionFactoryImpl;

import com.app.entity.Employee;
import com.app.utility.HibernateUtility;

public class Test {

	public void save() {
		Session session = HibernateUtility.getSession().openSession();

		Employee employee = new Employee();
		employee.setName("Mukesh");
		employee.setSalary(20000f);

		session.save(employee);
		session.beginTransaction().commit();
		System.out.println("success");

	}

	public void updateVersion() {
		Session session = HibernateUtility.getSession().openSession();

		Employee employee = (Employee) session.get(Employee.class, 2);
		employee.setSalary(50000f);

		session.update(employee);
		session.beginTransaction().commit();

	}

	@SuppressWarnings("unchecked")
	public void selectAll() {
		HibernateUtility.getSession().openSession().createCriteria(Employee.class).list().forEach(System.out::println);

	}

	@SuppressWarnings("unchecked")
	public void findAllEmployees() {
		Session session = HibernateUtility.getSession().openSession();
		session.getNamedQuery("findAll").list().forEach(System.out::println);
		System.out.println("+++++++++++++++++");
		session.getNamedQuery("findByName").setParameter("ename", "Rahul").list().forEach(System.out::println);

		session.close();
	}

	public static void main(String[] args) {
		Test t = new Test();
		t.updateVersion();
		// t.selectAll();
		t.findAllEmployees();
	}

}
