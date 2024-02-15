import java.util.function.BinaryOperator;

//1. Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы:
//  sum(), multiply(), divide(), subtract(). Параметры этих методов – два числа разного типа, над которыми
//  должна быть произведена операция.
public class Calculator {


    public static <T extends Number, E extends Number> double sum(T a, E b){
        return  a.doubleValue() + b.doubleValue();
    }
    public static <T extends Number, E extends Number> double multiply(T a, E b){
        return  a.doubleValue() * b.doubleValue();
    }
    public static <T extends Number, E extends Number> double divide(T a, E b){
        if (b.doubleValue() == 0){
            throw new ArithmeticException("делить на нуль нельзя");
        }
        return  a.doubleValue() / b.doubleValue();
    }
    public static <T extends Number, E extends Number> double subtract(T a, E b){
        return  a.doubleValue() - b.doubleValue();
    }


}
