package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TodosTest {

    Todos todos = new Todos();

    @Test
    void addTask() {

        todos.addTask("Гулять");
        todos.addTask("Играть");
        todos.addTask("Смотреть телек");
        todos.addTask("Рисовать");
        todos.addTask("Работать");
        todos.addTask("Учиться");
        todos.addTask("Убираться");
        todos.addTask("Покормить кота");
        todos.addTask("Стирать вещи");

        Assertions.assertTrue(todos.getTasksList().size() <= Todos.MAX_TASKS);

        Assertions.assertFalse(todos.getTasksList().isEmpty());
    }

    @Test
    void getAllTasks() {

        todos.addTask("Б");
        todos.addTask("А");
        todos.addTask("Г");
        todos.addTask("В");

        String output = todos.getAllTasks();

        String[] tasks = output.split(Todos.DELIMITER);

        Assertions.assertEquals("А", tasks[0]);
        Assertions.assertEquals("Б", tasks[1]);
        Assertions.assertEquals("В", tasks[2]);
        Assertions.assertEquals("Г", tasks[3]);
    }

    @Test
    void removeTask() {

        todos.addTask("Гулять");
        todos.addTask("Играть");
        todos.addTask("Смотреть телек");

        if (!todos.getTasksList().isEmpty()) {

            int sizeBeforeRemove = todos.getTasksList().size();

            todos.removeTask("Гулять");
            todos.removeTask("Несуществующее");

            int sizeAfterRemove = todos.getTasksList().size();

            Assertions.assertEquals(1, sizeBeforeRemove - sizeAfterRemove);
        }
    }
}