package com.company;

import com.company.service.StringList;
import com.company.service.impl.StringListImpl;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        StringList sl = new StringListImpl();
        System.out.println("Печатаем созданный массив:");
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Добавляем методом элементы:");
        System.out.println(sl.add("123"));
        System.out.println(sl.add("456"));
        System.out.println(sl.add("789"));
        System.out.println(sl.add("101112"));
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Добавляем методом элементы со сдвигом вправо:");
        System.out.println(sl.add(0, "131415"));
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Заменяем значение одного индекса:");
        System.out.println(sl.set(0, "000"));
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Удаляем значение по содержимому:");
        System.out.println(sl.remove("789"));
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Удаляем значение по индексу:");
        System.out.println(sl.remove(0));
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Проверяем существует ли элемент:");
        System.out.println(sl.contains("123"));
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Поиск элемента с начала:");
        System.out.println(sl.indexOf("000"));
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Поиск элемента с конца:");
        sl.add(0, "000");
        System.out.println(sl.lastIndexOf("000"));
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Получаем элемент по индексу:");
        System.out.println(sl.get(1));
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Создаём новый объект и добавляем в него элементы:");
        StringList slNew = new StringListImpl();
        System.out.println(slNew.add("000"));
        System.out.println(slNew.add("123"));
        System.out.println(slNew.add("456"));
        System.out.println(slNew.add("101112"));
        System.out.println(sl.equals(slNew));
        slNew = sl;
        System.out.println(sl.equals(slNew) + "\n");

        System.out.println("Фактическое количество элементов в списке:");
        System.out.println(sl.size() + "\n");

        System.out.println("Возвращаем true, если элементов в списке нет:");
        System.out.println(sl.isEmpty());
        StringList qwerty = new StringListImpl();
        System.out.println(qwerty.isEmpty() + "\n");

        System.out.println("Удаляем все элементы из списка:");
        sl.clear();
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Получаем массив из списка:");
        sl.add("notNull");
        System.out.println(Arrays.toString(sl.toArray()) + "\n");
    }

}
