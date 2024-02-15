
//3. Напишите обобщенный класс Pair, который представляет собой пару значений разного типа. Класс должен иметь
// методы getFirst(), getSecond() для получения значений каждого из составляющих пары, а также
// переопределение метода toString(), возвращающее строковое представление пары.
public class Pair<T, E> {
    private final T element1;
    private final E element2;

    public Pair(T element1, E element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    public T getFirst(){
        return element1;
    }
    public E getSecond(){
        return  element2;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", element1, element2);
    }
}
