package com.zuev.repositories.hibernateimpl;

import com.zuev.entities.Writer;
import com.zuev.repositories.WriterRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HibernateWriterRepository implements WriterRepository {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Writer getByld(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Writer writer = session.createQuery("select w from Writer w left join fetch w.posts where w.id =: id", Writer.class)
                .setParameter("id", id).uniqueResult();
        session.getTransaction().commit();
        session.close();

        return writer;
    }

    @Override
    public List<Writer> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List writers = session.createQuery("select w from Writer w left join fetch w.posts", Writer.class).list();
        session.getTransaction().commit();
        session.close();
        return writers;
    }

    @Override
    public Writer save(Writer writer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(writer);
        session.getTransaction().commit();
        session.close();

        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(writer);
        session.getTransaction();
        session.close();

        return writer;
    }

    @Override
    public void deleteByld(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Writer writer = session.get(Writer.class, id);
        session.delete(writer);
        session.getTransaction();
        session.close();

    }
}
