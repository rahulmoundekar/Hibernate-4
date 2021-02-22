package com.app.entity;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;

public class Test {

	public void criteriaTest() {
		Session session = HibernateUtility.getSession().openSession();
		Criteria cr = session.createCriteria(Employee.class);
		List<Employee> list = cr.list();
		list.forEach(System.out::println);
	}

	public void andRestiction() {
		Session session = HibernateUtility.getSession().openSession();
		Criteria cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.eq("name", "abc")).add(Restrictions.eq("mobile", "23432"));
		List<Employee> list = cr.list();
		list.forEach(System.out::println);
	}

	public void betweenRestiction() {
		Session session = HibernateUtility.getSession().openSession();
		Criteria cr = session.createCriteria(Employee.class);
		// cr.add(Restrictions.between("salary", new Double(10000), new Double(50000)));
		cr.add(Restrictions.lt("salary", new Double(50000)));
		List<Employee> list = cr.list();
		list.forEach(System.out::println);
	}

	public void uniqueRestultRestiction() {
		Session session = HibernateUtility.getSession().openSession();
		Criteria cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.eq("name", "abc")).add(Restrictions.eq("mobile", "23432"));
		Employee employee = (Employee) cr.uniqueResult();
		System.out.println(employee);
	}

	public void projectionsTest() {
		Session session = HibernateUtility.getSession().openSession();
		Criteria cr = session.createCriteria(Employee.class);
		cr.setProjection(Projections.min("salary"));
		Double count = (Double) cr.uniqueResult();
		System.out.println(count);

	}

	public void columnWiseProjection() {
		Session session = HibernateUtility.getSession().openSession();
		Criteria cr = session.createCriteria(Employee.class);
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("name"));
		projectionList.add(Projections.property("mobile"));

		cr.setProjection(projectionList);

		cr.setResultTransformer(new ResultTransformer() {

			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				Employee employee = new Employee();
				employee.setName((String) tuple[0]);
				employee.setMobile((String) tuple[1]);
				return employee;
			}

			@Override
			public List transformList(List list) {
				return list;
			}
		});

		List<Employee> list = cr.list();
		list.forEach(System.out::println);
	}

	public void hql() {
		Session session = HibernateUtility.getSession().openSession();
		Query query = session.createQuery("from Employee");
		query.setResultTransformer(new ResultTransformer() {

			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				Employee employee = new Employee();
				employee.setId((Integer)tuple[0]);
				employee.setName((String) tuple[1]);
				employee.setMobile((String) tuple[2]);
				employee.setSalary((Double)tuple[3]);
				return employee;
			}

			@Override
			public List transformList(List list) {
				return list;
			}
		});
		query.list().forEach(System.out::println);
	}

	public void hql1() {
		Session session = HibernateUtility.getSession().openSession();
		Query query = session.createQuery("from Employee where id=1");
		query.list().forEach(System.out::println);
	}

	public void sql() {
		Session session = HibernateUtility.getSession().openSession();
		SQLQuery query = session.createSQLQuery("select * from Employee");
		query.setResultTransformer(new ResultTransformer() {

			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				Employee employee = new Employee();
				employee.setId((Integer)tuple[0]);
				employee.setName((String) tuple[1]);
				employee.setMobile((String) tuple[2]);
				employee.setSalary((Double)tuple[3]);
				return employee;
			}

			@Override
			public List transformList(List list) {
				return list;
			}
		});
		query.list().forEach(System.out::println);
	}

	
	public void hql2() {
		int id=2;
		Session session = HibernateUtility.getSession().openSession();
		Query query = session.createQuery("from Employee where id=:sid");
		query.setParameter("sid", id);
		query.list().forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		Test test = new Test();
		// test.criteriaTest();
		test.hql2();
	}

}
