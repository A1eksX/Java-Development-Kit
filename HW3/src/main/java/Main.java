import java.util.ArrayList;
//1. Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы:
//  sum(), multiply(), divide(), subtract(). Параметры этих методов – два числа разного типа, над которыми
//  должна быть произведена операция.

//2. Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true, если они
//  одинаковые, и false в противном случае. Массивы могут быть любого типа данных, но должны иметь одинаковую
//  длину и содержать элементы одного типа.

//3. Напишите обобщенный класс Pair, который представляет собой пару значений разного типа. Класс должен иметь
// методы getFirst(), getSecond() для получения значений каждого из составляющих пары, а также
// переопределение метода toString(), возвращающее строковое представление пары.

public class Main<T, E extends T> {
    public static <T, E> void main(String[] args) {

        //  1.
        System.out.println(Calculator.sum(8.0, 2.0));
        System.out.println(Calculator.subtract(10.0, 2));
        System.out.println(Calculator.multiply(2f, 2));
        System.out.println(Calculator.divide(10, 4));

        // 2.
        Number[] arr1 = new Number[]{};
        Number[] arr2 = new Number[]{};

        // 3.
        System.out.println(compareArrays(arr1, arr2));

    }

    public static <T> boolean compareArrays(T[] arr1, T[] arr2) {
        if (arr1.length == arr2.length) {
            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i].getClass().equals(arr2[i].getClass())) {
                    return true;
                }
            }
        }
        return false;
    }



}
