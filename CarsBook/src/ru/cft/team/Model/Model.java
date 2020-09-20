package ru.cft.team.Model;

import ru.cft.team.SQL.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model implements DataChangeable {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/autoGuide?characterEncoding=utf8&autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private final DataSource dataSource = new DataSource(DATABASE_URL, USER, PASSWORD);

    @Override
    public void addRow(Item item) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();

            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO auto (year, brand, model, bodyType) VALUES ('").append(item.getYear()).append("','").append(item.getBrand());
            sb.append("','").append(item.getModel()).append("','").append(item.getBodyType()).append("');");

            stmt.executeUpdate(sb.toString());

            stmt.close();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delRow(Item item) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();

            stmt.executeUpdate("DELETE FROM auto WHERE id=" + item.getId());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateRow(Item item) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();

            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE auto SET year='").append(item.getYear()).append("', brand ='").append(item.getBrand());
            sb.append("', model='").append(item.getModel()).append("', bodyType='").append(item.getBodyType()).append("'");
            sb.append("WHERE id=").append(item.getId());

            stmt.executeUpdate(sb.toString());

            stmt.close();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Item> readTable() throws SQLException, ClassNotFoundException {
        List<Item> cars = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM auto");
            while (rs.next()) {
                Item car = new Item();
                car.setId(rs.getInt("id"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setBodyType(rs.getString("bodyType"));
                cars.add(car);
            }
            stmt.close();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cars;
    }
}

