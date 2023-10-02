package com.restapi.service.dao;

import com.restapi.service.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }


    @Override
    public List<Employee> findAll(){
        //create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        List<Employee> employees = theQuery.getResultList();

        return employees;

    }

    @Override
    public Employee findById(int theId) {
        //get employee
        Employee theEmployee = entityManager.find(Employee.class, theId);
        //return employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        //save or update the employee

        Employee updatedEmployee = entityManager.merge(theEmployee);

        return  updatedEmployee;
    }

    @Override
    public void deleteById(int theId) {
        //find by id
        Employee theEmployee = entityManager.find(Employee.class, theId);

        //delete employee
        entityManager.remove(theEmployee);

    }
}
