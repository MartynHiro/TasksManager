package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 8989);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            Task task = new Task();
            task.setType("ADD");
            task.setTask("C");

            Gson gson = new GsonBuilder().create();

            writer.println(gson.toJson(task));

            System.out.println(reader.readLine());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
