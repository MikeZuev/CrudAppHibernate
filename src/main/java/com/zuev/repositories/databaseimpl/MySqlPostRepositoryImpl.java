package com.zuev.repositories.databaseimpl;

import com.zuev.entities.Label;
import com.zuev.entities.Post;
import com.zuev.repositories.PostRepository;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

public class MySqlPostRepositoryImpl implements PostRepository {


    @Override
    public Post getByld(Long aLong) {
        Connection connection = ConnectionManager.open();

        List<Label> labels = new ArrayList<>();
        Post newPost;
        Label newLabel;


        PreparedStatement preparedStatement = null;



        String sql = null;
        String content = null;
        String name = null;

        try {
            sql = "select p.content, l.name\n" +
                    "from posts p left join labels l on p.post_id = l.post_id " +
                    "where p.post_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while(resultSet.next()){
                content = resultSet.getString("content");
                name = resultSet.getString("name");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        newLabel = new Label(name);
        labels.add(newLabel);


        return newPost = new Post(content, labels);
    }

    @Override
    public List<Post> getAll() {
        Map<Post, List<Label>> postsMap = new HashMap<>();
        List<Post> posts = null;


        Connection connection = ConnectionManager.open();
        PreparedStatement preparedStatement = null;



        String sql = null;


        try {
            sql = "select p.id, p.content, p.created, p.updated, l.label_id, l.name " +
                    "from posts p left join labels l on p.id = l.post_id";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            posts = getResultSetOfAllPosts(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return posts;
    }


    @Override
    public Post save(Post post) {
        Connection connection = ConnectionManager.open();
        PreparedStatement preparedStatement = null;



        String sql = null;





        try {
            sql = "insert into posts(content, created) values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, post.getContent());
            Date date = Date.valueOf(java.time.LocalDate.now());

            preparedStatement.setDate(2, date);
            preparedStatement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return post;
    }

    @Override
    public Post update(Post post) {
        Connection connection = ConnectionManager.open();
        PreparedStatement preparedStatement = null;



        String sql = null;


        try {
            sql = "update posts set content = ? where post_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setLong(2, post.getId());
            preparedStatement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        return post;
    }

    @Override
    public void deleteByld(Long aLong) {
        Connection connection = ConnectionManager.open();
        PreparedStatement preparedStatement = null;


        String sql = null;
        try {
            sql = "DELETE FROM posts WHERE post_id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, aLong);
            preparedStatement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }


    private List<Post> getResultSetOfAllPosts(ResultSet resultSet){
        Map<Post, List<Label>> postsMap = new HashMap<>();
        List<Post> posts = null;


            try {
                 while (resultSet.next()){
                    long id = resultSet.getLong("id");
                    String content = resultSet.getString("content");
                    Date created = resultSet.getDate("created");
                    Date updated = resultSet.getDate("updated");
                    long label_id = resultSet.getLong("label_id");
                    String name = resultSet.getString("name");

                    List<Label> labels = new ArrayList<>();

                    Post newPost = new Post(id, content, created, updated, labels);

                    Label newLabel = new Label(label_id, name);

                    if (postsMap.containsKey(newPost)) {
                        postsMap.get(newPost).add(newLabel);
                    } else {
                        postsMap.put(newPost, labels);
                        postsMap.get(newPost).add(newLabel);
                    }

                    posts = postsMap.entrySet().stream()
                            .map(m -> {
                                Post post = m.getKey();
                                post.setLabels(m.getValue());
                                return post;
                            }).collect(Collectors.toList());
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return posts;




    }
}
