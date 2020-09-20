package ru.cft.team.Model;

import ru.cft.team.View.View;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Model model = new Model();
        new View(model);
    }
}