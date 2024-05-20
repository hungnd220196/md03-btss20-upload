package com.ra.bt_hibernate_plus.Dao.DepartmentImpl;

import com.ra.bt_hibernate_plus.Dao.IDepartmentDao;
import com.ra.bt_hibernate_plus.entity.Department;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentImpl implements IDepartmentDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Department> getDepartment() {
        Session session = sessionFactory.openSession();
        try {
            List list = session.createQuery("from Department ").list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Department getDepartmentById(Integer id) {
        Session session = sessionFactory.openSession();
        Department department = session.get(Department.class, id);
        session.close();
        return department;
    }

    @Override
    public boolean insertDepartment(Department department) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(department);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean updateDepartment(Department department) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(department);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }


    @Override
    public boolean deleteDepartment(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(getDepartmentById(id));
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

}

