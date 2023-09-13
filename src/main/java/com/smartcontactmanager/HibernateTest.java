package com.smartcontactmanager;

import com.smartcontactmanager.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

public class HibernateTest {

    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        User user = new User();
        user.setFirstName("Balaji");
        user.setLastName("R");
        user.setEmail("baji@gmail.com");
        user.setPassword("123pass#");
        user.setGender("Male");
        user.setContactNumber("8310850942");
        user.setDob(LocalDate.of(1999, 9, 23));
        user.setCreatedBy("Balaji");
        user.setUpdatedBy("");
//        user.setRole(Role.ROLE_NORMAL);
//        user.setProfileImg("profile");

        session.persist(user);
        session.getTransaction().commit();
        session.close();
    }
}
