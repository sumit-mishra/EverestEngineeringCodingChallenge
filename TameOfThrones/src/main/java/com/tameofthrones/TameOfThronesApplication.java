package com.tameofthrones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tameofthrones.communicator.Communicator;


@SpringBootApplication
public class TameOfThronesApplication {

    public static void main(String[] args) {
        SpringApplication.run(TameOfThronesApplication.class, args);
        System.out.println("Welcome to Tame Of Thrones : Gloden crown & Braker of chains");
        Communicator communicator = new Communicator();
        communicator.initiateCommunication();
    }
}
