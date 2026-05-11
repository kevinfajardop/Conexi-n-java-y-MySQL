package com.mycompany.table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Formulario {

    Scanner sc = new Scanner(System.in);

    public void registrar() {
        String nombre, apellido, correo, telefono;
        int edad;

        do {
            System.out.print("Nombre: ");
            nombre = sc.nextLine();
        } while (!nombre.matches("[a-zA-Z ]{3,}"));

        do {
            System.out.print("Apellido: ");
            apellido = sc.nextLine();
        } while (!apellido.matches("[a-zA-Z ]{3,}"));

        do {
            System.out.print("Correo: ");
            correo = sc.nextLine();
        } while (!correo.matches("^[A-Za-z0-9+_.-]+@(.+)$"));

        do {
            System.out.print("Teléfono: ");
            telefono = sc.nextLine();
        } while (!telefono.matches("\\d{7,10}"));

        do {
            System.out.print("Edad: ");
            edad = sc.nextInt();
        } while (edad < 1 || edad > 120);

        insertar(nombre, apellido, correo, telefono, edad);
    }

    public void insertar(String nombre, String apellido, String correo, String telefono, int edad) {
        try {
            Connection con = Conexion.conectar();
            String sql = "INSERT INTO estudiantes(nombre, apellido, correo, telefono, edad) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, correo);
            ps.setString(4, telefono);
            ps.setInt(5, edad);

            ps.executeUpdate();
            System.out.println("Registro guardado correctamente");

        } catch (Exception e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    public void mostrar() {
        try {
            Connection con = Conexion.conectar();
            String sql = "SELECT * FROM estudiantes";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- REGISTROS ---");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("nombre") + " | " +
                    rs.getString("apellido") + " | " +
                    rs.getString("correo") + " | " +
                    rs.getString("telefono") + " | " +
                    rs.getInt("edad")
                );
            }

        } catch (Exception e) {
            System.out.println("Error al mostrar: " + e.getMessage());
        }
    }
}