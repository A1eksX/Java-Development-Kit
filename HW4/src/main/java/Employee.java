public class Employee {
//    Создать класс справочник сотрудников, который содержит внутри
//    коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
//    - Табельный номер
//    - Номер телефона
//    - Имя
//    - Стаж

    private String name;
    private String serviceNumber;
    private String phone;
    private String experience;

    public Employee(String name, String serviceNumber, String phone, String experience) {
        this.name = name;
        this.serviceNumber = serviceNumber;
        this.phone = phone;
        this.experience = experience;
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, ServiceNumber: %s, Phone: %s, Experience: %s", getName(), getServiceNumber(), getPhone(), getExperience());
    }
}
