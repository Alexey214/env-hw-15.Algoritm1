package com.company.service.impl;

import com.company.exception.ElementNotFound;
import com.company.exception.MyIllegalArgumentException;
import com.company.exception.MyIndexOfBoundExceptions;
import com.company.service.StringList;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private String[] arr;
    private int actualSize = 0;

    public StringListImpl() {
        int size = 3;
        this.arr = new String[size];
    }

    @Override
    public String add(String item) {
        ifNull(item);
        if (arr.length == actualSize) {
            String[] newArr = Arrays.copyOf(arr, actualSize + 3);
            arr = newArr;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                continue;

            } else {
                arr[i] = item;
                actualSize++;
                break;
            }
        }
        return item;
    }

    @Override
    public String add(int index, String item) {
        ifNull(item);
        ifIndexOfBoundExceptions(index);
        String temp = item;
        actualSize++;
        if (arr[index] == null) {
            arr[index] = item;
            return item;
        } else {
            String buffer;
            String[] newArr = Arrays.copyOf(arr, arr.length + 1);
            arr = newArr;
            for (int i = index; i < newArr.length; i++) {
                buffer = arr[i];
                arr[i] = item;
                item = buffer;
            }
        }
        return temp;
    }

    @Override
    public String set(int index, String item) {
        ifNull(item);
        ifIndexOfBoundExceptions(index);
        return arr[index] = item;
    }

    @Override
    public String remove(String item) {
        arr[find(item)] = null;
        return item;
    }

    @Override
    public String remove(int index) {
        ifIndexOfBoundExceptions(index);
        if (arr[index] == null) {
            return null;
        }
        arr[index] = null;
        return arr[index];
    }

    @Override
    public boolean contains(String item) {
        try {
            find(item);
        } catch (ElementNotFound e) {
            return false;
        }
        return true;
    }

    @Override
    public int indexOf(String item) {
        int index = 0;
        try {
            index = find(item);
        } catch (ElementNotFound e) {
            return -1;
        }
        return index;
    }

    @Override
    public int lastIndexOf(String item) {
        ifNull(item);
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == null) {
                continue;
            } else if (item.equals(arr[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        ifIndexOfBoundExceptions(index);
        return arr[index];
    }

    //За такие методы людей должны сжигать на костре, но я ничего лучше не смог придумать
    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new MyIllegalArgumentException();
        }
        if (this == otherList) {
            return true;
        }

        //из старого массива получаем новый и сразу очищаем его от ненужного мусора, а также сортируем
        String[] oldArr = this.getAll().replace("[", "")
                .replace("null", "")
                .replace("]", "")
                .replace(",", "")
                .split(" ");
        Arrays.sort(oldArr);

        //создаём переменную, равную "количеству заполненных значений и следом создаём новый массив с данным размером
        int tmpCount1 = 0;
        for (int i = 0; i < oldArr.length; i++) {
            if (!oldArr[i].equals("")) {
                tmpCount1++;
            }
        }
        String[] correctOldArr = new String[tmpCount1];

        //копируем из старого массива данные в новый, избегая теперь пустых ячеек
        int stepUndo1 = 0;
        for (int i = 0; i < oldArr.length; i++) {
            if (oldArr[i].equals("")) {
                stepUndo1++;
                continue;
            }
            correctOldArr[i - stepUndo1] = oldArr[i];
        }

        //повторяем все аналогичные операции со вторым массивом (с которым сравниваем)
        String[] newArr = otherList.getAll().replace("[", "")
                .replace("]", "")
                .replace(",", "")
                .replace("null", "")
                .split(" ");
        Arrays.sort(newArr);

        int tmpCount2 = 0;
        for (int i = 0; i < newArr.length; i++) {
            if (!newArr[i].equals("")) {
                tmpCount2++;
            }
        }
        String[] correctNewArr = new String[tmpCount2];

        int stepUndo2 = 0;
        for (int i = 0; i < newArr.length; i++) {
            if (newArr[i].equals("")) {
                stepUndo2++;
                continue;
            }
            correctNewArr[i - stepUndo2] = newArr[i];
        }

        //сравниваем равны ли наши вновь созданные массивы, имеющие общий вид и порядок
        int count = 0;
        if (correctOldArr.length == correctNewArr.length) {
            for (int i = 0; i < correctOldArr.length; i++) {
                if (correctOldArr[i].equals(correctNewArr[i])) {
                    count++;
                }
            }
        }

        //все шаги прошли успешно, значит массивы равны
        if (count == correctOldArr.length) {
            return true;
        }

        return false;
    }

    @Override
    public int size() {
        int elem = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                continue;
            } else {
                elem++;
            }
        }
        return elem;
    }

    @Override
    public boolean isEmpty() {
        int elem = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                elem++;
                continue;
            }
        }
        if (elem == arr.length) {
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = null;
        }
    }

    @Override
    public String[] toArray() {
        String[] newArr = Arrays.copyOf(arr, arr.length);
        return newArr;
    }

    //Служебные методы:
    private String ifNull(String item) {
        if (item != null) {
            return item;
        } else {
            throw new MyIllegalArgumentException();
        }
    }

    private int ifIndexOfBoundExceptions(int index) {
        if (index < arr.length && index >= 0) {
            return index;
        } else {
            throw new MyIndexOfBoundExceptions();
        }
    }

    @Override
    //Создал дополнительный метод для проверки содержимого массива, чтобы проще контролировать процесс
    public String getAll() {
        return Arrays.toString(arr);
    }

    private int find(String item) {
        ifNull(item);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                continue;
            } else if (item.equals(arr[i])) {
                return i;
            }
        }
        throw new ElementNotFound();
    }


}
