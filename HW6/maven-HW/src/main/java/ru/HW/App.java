package ru.HW;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.random.*;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Game game = new Game();
        game.getChoices();


        System.out.println("Правильно отгадали: " + game.getResult().get(0) + " раз");
        System.out.println("Неправильно отгадали: " + game.getResult().get(1) + " раз");
    }
}