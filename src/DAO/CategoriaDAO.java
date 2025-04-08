/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Modelo.Categoria;
import Util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell Notebook
 */
public class CategoriaDAO {
    
    public void crearCategoria(Categoria categoria) throws SQLException {
    String sql = """
    INSERT INTO Categorias (
    nombre_categoria,
    descripcion_categoria
    ) VALUES (?, ?) """;
    try (Connection c = ConexionDB.getConnection();
        PreparedStatement stmt = c.prepareStatement(sql)) {
        stmt.setString(1, categoria.getNombreCategoria());
        stmt.setString(2, categoria.getDescripcionCategoria());
        stmt.executeUpdate();
    }
}
    
    public List<Categoria> leerTodasCategorias() throws SQLException {
        String sql = "SELECT * FROM Categorias";
        List<Categoria> categorias = new ArrayList<>();
        try (Connection c = ConexionDB.getConnection();
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombreCategoria(rs.getString("nombre_categoria"));
                categoria.setDescripcionCategoria(rs.getString("descripcion_categoria"));
                categorias.add(categoria);
        }
    }
        return categorias;
}
    public static void main(String[] args) {
        try {
            CategoriaDAO dao = new CategoriaDAO();
            List<Categoria> categorias = dao.leerTodasCategorias();
            System.out.println("\nLista de categorías:");
            for (Categoria cat : categorias) {
                System.out.println("ID: " + cat.getIdCategoria()
                        + ", Nombre: " + cat.getNombreCategoria()
                        + ", Descripción: " + cat.getDescripcionCategoria());

            }
    } catch (SQLException e) {
        System.err.println("Error: " + e.getMessage());
        }
    }
    
}
