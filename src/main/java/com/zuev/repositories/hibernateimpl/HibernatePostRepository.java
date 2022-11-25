package com.zuev.repositories.hibernateimpl;

import com.zuev.entities.Label;
import com.zuev.entities.Post;
import com.zuev.repositories.PostRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HibernatePostRepository implements PostRepository {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    @Override
    public Post getByld(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Post newPost = session.createQuery("select p from Post p left join fetch p.labels where p.id =: id", Post.class)
                .setParameter("id", id).uniqueResult();
        session.getTransaction().commit();
        session.close();

        return newPost;
    }

    @Override
    public List<Post> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List posts = session.createQuery("select distinct p from Post p left join fetch p.labels").list();
        session.getTransaction().commit();
        session.close();

        return posts;
    }

    @Override
    public Post save(Post post) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(post);
        session.getTransaction().commit();
        session.close();



        return post;
    }

    @Override
    public Post update(Post post) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(post);
        session.getTransaction().commit();
        session.close();


        return post;
    }

    @Override
    public void deleteByld(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Post postDel = session.get(Post.class, id);
        session.delete(postDel);
        session.getTransaction().commit();
        session.close();

    }
}
