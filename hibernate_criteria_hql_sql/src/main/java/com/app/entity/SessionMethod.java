package com.app.entity;

import org.hibernate.Session;

public class SessionMethod {

	public void getTest() {
		Session session = HibernateUtility.getSession().openSession();
		Employee employee = (Employee) session.get(Employee.class, 2);
		System.out.println(employee);
		Employee employee2 = (Employee) session.get(Employee.class, 2);
		System.out.println(employee2);

	}

	public void loadTest() {
		Session session = HibernateUtility.getSession().openSession();
		Employee employee1 = (Employee) session.load(Employee.class, 2);
		System.out.println(employee1.getId());
		System.out.println(employee1.getName());
		System.out.println(employee1.getMobile());
		Employee employee2 = (Employee) session.load(Employee.class, 2);
		System.out.println(employee2);
		session.flush();
	}

	public void saveAndPersist() {
		Session session = HibernateUtility.getSession().openSession();
		Employee employee = new Employee();
		employee.setName("xyzabc");
		employee.setMobile("555555");
		employee.setSalary(46000d);

		/*
		 * Integer mxid = (Integer) session.save(employee); System.out.println(mxid);
		 * session.beginTransaction().commit();
		 */

		session.persist(employee);
		;
		session.beginTransaction().commit();
	}

	public void saveOrUpdateTest() {
		Session session = HibernateUtility.getSession().openSession();

		Employee employee = new Employee();
		employee.setId(10);
		employee.setName("Rahul");
		employee.setMobile("987654321");
		employee.setSalary(67000d);

		session.saveOrUpdate(employee);
		session.beginTransaction().commit();
	}

	public void mergeTest() {

		Session session = HibernateUtility.getSession().openSession();

		Employee employee = (Employee) session.get(Employee.class, 10);
		employee.setName("abc");

		session.close();

		Session session1 = HibernateUtility.getSession().openSession();
		Employee employee2 = (Employee) session1.get(Employee.class, 10);

		employee.setSalary(70000d);

		session1.merge(employee);
		session1.beginTransaction().commit();

	}

	public static void main(String[] args) {
		SessionMethod sessionMethod = new SessionMethod();
		// sessionMethod.getTest();
		System.out.println("++++++++++++++++++++++++++++++");
		// sessionMethod.loadTest();

		sessionMethod.mergeTest();
	}

}
