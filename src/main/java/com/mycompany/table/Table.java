package com.mycompany.table;
import java.util.Scanner;

public class Table {

    public static void main(String[] args) {

        Formulario f = new Formulario();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n1. Registrar");
            System.out.println("2. Mostrar registros");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    sc.nextLine(); 
                    f.registrar();
                    break;
                case 2:
                    f.mostrar();
                    break;
            }

        } while (opcion != 3);
    }
}