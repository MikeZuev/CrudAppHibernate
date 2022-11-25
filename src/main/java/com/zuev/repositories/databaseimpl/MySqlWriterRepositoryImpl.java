package com.zuev.repositories.databaseimpl;

import com.zuev.entities.Writer;
import com.zuev.repositories.WriterRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlWriterRepositoryImpl implements WriterRepository {

    @Override
    public Writer getByld(Long integer) {
        Writer writer = null;

        Connection connection = ConnectionManager.open();
        PreparedStatement preparedStatement = null;



        String sql = null;


        try {
            sql = "select * from writers where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, integer);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");

                writer = new Writer(id, firstName, lastName);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return writer;
    }

    @Override
    public List<Writer> getAll() {
        List<Writer> writers = new ArrayList<>();

        Connection connection = ConnectionManager.open();
        PreparedStatement preparedStatement = null;



        String sql = null;

        sql = "select * from writers";
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");

                writers.add(new Writer(id, firstName, lastName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return writers;
    }

    @Override
    public Writer save(Writer writer) {
        Connection connection = ConnectionManager.open();
        PreparedStatement preparedStatement = null;



        String sql = null;
        sql = "insert into writers (FirstName, LastName) values (?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, writer.getFirstName());
            preparedStatement.setString(2, writer.getLastName());
            preparedStatement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Writer update(Writer writer) {
        Connection connection = ConnectionManager.open();
        PreparedStatement preparedStatement = null;



        String sql = null;
        try {
            sql = "update writers set FirstName = ?, LastName = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, writer.getFirstName());
            preparedStatement.setString(2, writer.getLastName());
            preparedStatement.setLong(3, writer.getId());
            preparedStatement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void deleteByld(Long id) {
        Connection connection = ConnectionManager.open();
        PreparedStatement preparedStatement = null;
        String sql = null;

        try {
            sql = "delete from writers where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
