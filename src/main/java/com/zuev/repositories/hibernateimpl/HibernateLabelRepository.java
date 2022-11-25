package com.zuev.repositories.hibernateimpl;

import com.zuev.entities.Label;
import com.zuev.repositories.LabelRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateLabelRepository implements LabelRepository {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();





    @Override
    public Label getByld(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Label newLabel = (Label) session.createQuery("select l from Label l left join fetch l.posts where l.id =: id", Label.class)
                .setParameter("id", id).uniqueResult();

        List posts = newLabel.getPosts();
        for(Object post : posts){
            System.out.println(post.toString());
        }

        session.getTransaction().commit();
        session.close();
        return newLabel;
        }

    @Override
    public List<Label> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List labels = session.createQuery("select distinct l from Label l left join fetch l.posts", Label.class).list();
//
        session.getTransaction().commit();
        session.close();

        return labels;
    }

    @Override
    public Label save(Label label) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Label newLabel = label;
        session.save(newLabel);
        session.getTransaction().commit();
        session.close();

        return newLabel;
    }

    @Override
    public Label update(Label label) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        long label_id = label.getId();
        Label newLabel = (Label) session.get(Label.class, label_id);

        newLabel.setName(label.getName());

        session.update(newLabel);
        session.getTransaction().commit();
        session.close();

        return newLabel;
    }

    @Override
    public void deleteByld(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Label label = session.get(Label.class, id);
        session.delete(label);
        session.getTransaction().commit();
        session.close();

    }
}
