package ru.cft.team.Model;

import java.sql.SQLException;
import java.util.List;

public interface DataChangeable {
    void addRow(Item item) throws SQLException, ClassNotFoundException;

    void delRow(Item item) throws SQLException, ClassNotFoundException;

    void updateRow(Item item) throws SQLException, ClassNotFoundException;

    List<Item> readTable() throws SQLException, ClassNotFoundException;
}
