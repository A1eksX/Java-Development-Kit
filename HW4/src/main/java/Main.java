import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
//    Создать справочник сотрудников

//    Необходимо:
//    Создать класс справочник сотрудников, который содержит внутри
//    коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:                         +
//    - Табельный номер
//    - Номер телефона
//    - Имя
//    - Стаж

//    Добавить метод, который ищет сотрудника по стажу (может быть список)                              +?
//    Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)         +?
//    Добавить метод, который ищет сотрудника по табельному номеру                                      +?
//    Добавить метод добавление нового сотрудника в справочник                                          +


    public static void main(String[] args) {
        EmployeeDB employeeDB = new EmployeeDB();

        Employee employee1 = new Employee("Alex", "123", "+78963", "5");
        Employee employee2 = new Employee("Max", "125", "+53269", "10");
        Employee employee21 = new Employee("Serg", "1225", "+553269", "10");
        Employee employee3 = new Employee("Olga", "257", "+14532", "1");
        Employee employee31 = new Employee("Olga", "257", "+333", "1");
        Employee employee4 = new Employee("Natasha", "23", "+52532", "2");

        employeeDB.addEmployee(employee1)
                    .addEmployee(employee2)
                    .addEmployee(employee21)
                    .addEmployee(employee3)
                    .addEmployee(employee31)
                    .addEmployee(employee4);

        System.out.println(employeeDB);
        System.out.println("---------");

        System.out.println(employeeDB.searchEmployee("257"));
        System.out.println("--getPhone--");
        System.out.println(employeeDB.getPhoneEmployee("Olga"));
        System.out.println("--getExperience--");
        System.out.println(employeeDB.getExperienceEmployee("10"));


    }
}
