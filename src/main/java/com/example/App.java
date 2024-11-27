package com.example;

import com.example.Menu.GestionHotel;

public class App {
    public static void main( String[] args ) {
        GestionHotel menu = new GestionHotel();
    }
}
//El sistema de gestión de hoteles que creamos, está pensado para poder ser funcional tanto a los clientes
//que serán los usuarios de mayor frecuencia, pero también para los administradores y el personal del hotel.
//Utiliza 3 tipos distintos de colecciones: ArrayList(Para las habitaciones), Treeset(Para los usuarios
//evitando usuername repetidos) y HashMap(Para las reservas: <NumeroHabitacion, Reserva>.

//Han surgido muchos problemas en el desarrolo de nuestro proyecto, que con paciencia y tiempo de mas lo
//hemos trabajado. Intentamos agregar la mayor cantidad de funcionalidades posibles que estaban a nuestro
//ancalce.
