import java.util.ArrayList;
import java.util.List;

public class EmployeeDB {           // <Employee>
    private List<Employee> employeeList = new ArrayList<>();
    private Employee employee;

    //    Добавить метод добавление нового сотрудника в справочник
    public EmployeeDB addEmployee(Employee employee){           // <Employee>            String name, String serviceNumber, String phone, String experience
//        employeeList.add(name, serviceNumber, phone, experience);
        employeeList.add(employee);
        return this;
    }

    // -   Добавить метод, который ищет сотрудника по табельному номеру
    public Employee searchEmployee(String serviceNumber) throws RuntimeException{
        for (Employee unit:employeeList) {
            if (unit.getServiceNumber().equals(serviceNumber)){
                return unit;
            }
        }
        throw new RuntimeException("Сотрудник с таким табельным номером отсутствует");
    }

    // -   Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
    public List getPhoneEmployee(String name){
        return employeeList.stream().filter(x->x.getName().equals(name)).map(x->x.getPhone()).toList();
    }

    // -   Добавить метод, который ищет сотрудника по стажу (может быть список)
    public List getExperienceEmployee(String experience){
        return employeeList.stream().filter(x->x.getExperience().equals(experience)).map(x->x).toList();
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for (Employee unit: employeeList) {
            builder.append(unit).append("\n");
        }
        return builder.toString();
    }
}
