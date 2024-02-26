package ru.HW;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private int RIGHT_CHOICES = 0;   // верный выбор
    private int WRONG_CHOICES = 0;    // неверный выбор
    private final int INITIAL_VALUE = 1;   // начальное значение
    private final int NUMBER_DOORS = 4;
    private boolean flag;
    private int newDoor, bonus, select, open, newSelect;

    Random random = new Random();
    List<Integer> result;


    public List<Integer> getChoices(){
        result = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            bonus = bonusDoor();
            select = selectDoor();
            open = openDoor();
            newDoor = newSelectDoor(open);

            if (newDoor == bonus){
                RIGHT_CHOICES++;
            }else {
                WRONG_CHOICES++;
            }
            bonus = select  = newDoor = 0;
        }
        result.add(0, RIGHT_CHOICES);
        result.add(1, WRONG_CHOICES);
        return result;
    }
    public List<Integer> getResult(){
        return result;
    }

    public int newSelectDoor(int exception){
        flag = true;
        while (flag){
            newSelect = selectDoor();
            if (newSelect != exception && newSelect != select){
                flag = false;
            }
        }
        return newSelect;
    }

    public int openDoor(){
        int x = 0;
        flag = true;
        while (flag){
            x = selectDoor();
            if (x != select && x != bonus){
                flag = false;
            }
        }
        return x;
    }
    public int bonusDoor(){
        return selectDoor();
    }
    public int selectDoor(){
        return random.nextInt(INITIAL_VALUE, NUMBER_DOORS);
    }
}
