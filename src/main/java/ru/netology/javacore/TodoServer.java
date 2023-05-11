package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {

    private final int port;
    private final Todos todos;
    private final Logger logger;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
        this.logger = Logger.getInstance();
    }

    public void start() {
        System.out.println("Starting server at " + port + "...");

        try (ServerSocket serverSocket = new ServerSocket(port);) {

            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter writer = new PrintWriter(socket.getOutputStream());
                ) {

                    Gson gson = new GsonBuilder().create();

                    Task task = gson.fromJson(reader.readLine(), Task.class);

                    selection(task);

                    writer.println(todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    private void selection(Task taskFromUser) {
        switch (taskFromUser.getType()) {

            case "ADD":
                todos.addTask(taskFromUser.getTask());
                logger.addTask(taskFromUser);

                break;
            case "REMOVE":
                todos.removeTask(taskFromUser.getTask());
                logger.addTask(taskFromUser);

                break;
            case "RESTORE":
                //смотрим в логе нашу последнюю задачу
                Task lastTaskFromLog = logger.getLastTask();

                switch (lastTaskFromLog.getType()) {

                    //если она была "ADD", то делаем обратное -> удаление ее же
                    case "ADD":
                        todos.removeTask(lastTaskFromLog.getTask());
                        break;

                    //если она была "REMOVE", то делаем -> ее добавление
                    case "REMOVE":
                        todos.addTask(lastTaskFromLog.getTask());
                        break;
                }
                break;
        }
    }
}
