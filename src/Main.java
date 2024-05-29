import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

class Clothing {
    //Поля класса:
    static int count = 0;
    String size;
    String color;
    String brand;
    String type;
    Person owner;

    public Clothing(String size, String color, String brand, String type, Person owner) {// Конструктор класса, который инициализирует поля объекта при его создании и увеличивает счетчик
        this.size = size;
        this.color = color;
        this.brand = brand;
        this.type = type;
        this.owner = owner;//поле владельца одежды
        Clothing.count++;
    }
    @Override  //переопределенный метод
    public String toString() {
        return "Размер: " + size + ", Цвет: " + color + ", Брэнд: " + brand + ", Тип: " + type + ", Владелец: " + owner.name;
    }
}

public class Main {
    static ArrayList<Clothing> clothingItems = new ArrayList<>();


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int variant;
        while (true) {
            System.out.println("\n1. Добавить одежду");
            System.out.println("2. Удалить одежду");
            System.out.println("3. Редактировать одежду");
            System.out.println("4. Поиск одежды");
            System.out.println("5. Отобразить параметры одежды");
            System.out.println("6. Отобразить все предметы одежды");
            System.out.println("7. Показать количество предметов одежды");
            System.out.println("8. Перемещение");
            System.out.println("9. Выбрать файл для записи");
            System.out.println("0. Выйти");

            System.out.print("Выберите пункт меню: ");
            try {
                variant = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Неверный выбор");
                continue;
            }
            switch (variant) {
                case 1:
                    System.out.println("Выберите способ добавления одежды:");
                    System.out.println("1. Вручную");
                    System.out.println("2. Из файла");
                    int addMethod;
                    try {
                        addMethod = Integer.parseInt(in.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный выбор. Добавление вручную по умолчанию.");
                        addMethod = 1; // По умолчанию добавляем вручную
                    }

                    if (addMethod == 1) {
                        // Добавление вручную
                        // Добавление предметов одежды в список
                        System.out.print("Введите количество предметов одежды: ");
                        int count1;
                        try{
                            count1 = Integer.parseInt(in.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Неверный ввод "+e);
                            continue;
                        }

                        System.out.println("Выберите, куда добавить одежду:");
                        System.out.println("1. В конец списка");
                        System.out.println("2. В начало списка");

                        int addToEndOrStart;
                        try {
                            addToEndOrStart = Integer.parseInt(in.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Неверный ввод. Добавление в конец списка по умолчанию.");
                            addToEndOrStart = 1; // По умолчанию добавим в конец списка
                        }

                        for (int i = 1; i <= count1; i++) {
                            // Ввод параметров для каждого предмета одежды
                            System.out.print("Введите размер: ");
                            String size = in.nextLine();
                            System.out.print("Введите цвет: ");
                            String color = in.nextLine();
                            System.out.print("Введите брэнд: ");
                            String brand = in.nextLine();
                            System.out.print("Введите тип: ");
                            String type = in.nextLine();
                            System.out.print("Введите имя владельца: ");
                            String ownerName = in.nextLine();
                            Person owner = new Person(ownerName);

                            // Создание нового экземпляра одежды
                            Clothing clothing = new Clothing(size, color, brand, type, owner);

                            // Выбор, куда добавить одежду
                            if (addToEndOrStart == 1) {
                                // Добавление в конец списка
                                clothingItems.add(clothing);
                            } else {
                                // Добавление в начало списка
                                clothingItems.add(0, clothing);
                            }

                            System.out.println("Одежда добавлена в список");
                        }
                    } else if (addMethod == 2) {
                        System.out.print("Введите имя файла для загрузки данных: ");
                        String loadFileName = in.nextLine();
                        loadFromFile(loadFileName);
                        System.out.println("Данные загружены из файла: " + loadFileName);
                    } else {
                        System.out.println("Неверный выбор. Добавление вручную по умолчанию.");
break;
                    }
                    break;

                case 2:
                    // Удаление одежды из списка с указанием диапазона индексов
                    if (!clothingItems.isEmpty()) { // Проверка, что список не пуст
                        System.out.print("Введите начальный индекс для удаления: ");
                        try {
                            try{
                                try{
                            int startIndex = Integer.parseInt(in.nextLine()); // Считывание начального индекса для удаления
                                System.out.print("Введите количество индексов для удаления: ");
                                int deleteCount = Integer.parseInt(in.nextLine()); // Считывание количества индексов для удаления
                                int endIndex = startIndex + deleteCount - 1; // Вычисление конечного индекса для удаления
                            }catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("Неверный ввод " + e);
                            }
                            }catch (IndexOutOfBoundsException e) {
                                System.out.println("Неверный ввод " + e);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Неверный ввод "+e);
                        }
                    } else {
                        System.out.println("Список одежды пуст");
                    }
                    break;

                case 3:
                    // Редактирование параметров одежды
                    if (!clothingItems.isEmpty()) { // Проверка, что список не пуст
                        System.out.print("Введите индекс редактируемой одежды: ");
                        try {
                            int index = Integer.parseInt(in.nextLine()); // Считывание индекса для редактирования
                            if (index >= 0 && index < clothingItems.size()) { // Проверка, что индекс находится в пределах списка
                                Clothing clothing = clothingItems.get(index); // Получение экземпляра одежды по индексу
                                System.out.print("Введите новый размер: ");
                                clothing.size = in.nextLine(); // Обновление размера
                                System.out.print("Введите новый цвет: ");
                                clothing.color = in.nextLine(); // Обновление цвета
                                System.out.print("Введите новый брэнд: ");
                                clothing.brand = in.nextLine(); // Обновление брэнда
                                System.out.print("Введите новый тип: ");
                                clothing.type = in.nextLine(); // Обновление типа
                                System.out.print("Введите новое имя владельца: ");
                                clothing.owner.name = in.nextLine(); // Обновление имени владельца
                                System.out.println("Одежда обновлена");
                            } else {
                                System.out.println("Неверный индекс");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Неверный ввод");
                        }
                    } else {
                        System.out.println("Список одежды пуст");
                    }
                    break;

                case 4:
                    // Поиск одежды по различным параметрам
                    if (!clothingItems.isEmpty()) { // Проверка, что список не пуст
                        System.out.println("Выберите параметр для поиска:");
                        System.out.println("1. По цвету");
                        System.out.println("2. По индексу");
                        System.out.println("3. По типу");
                        int searchOption;
                        try {
                            searchOption = Integer.parseInt(in.nextLine()); // Считывание выбора пользователя
                        } catch (NumberFormatException e) {
                            System.out.println("Неверный ввод");
                            continue;
                        }
                        switch (searchOption) {
                            case 1:
                                // Поиск по цвету
                                System.out.print("Введите цвет для поиска: ");
                                String colorToSearch = in.nextLine();
                                for (int index = 0; index < clothingItems.size(); index++) {
                                    Clothing clothing = clothingItems.get(index);
                                    if (colorToSearch.equalsIgnoreCase(clothing.color)) { // Сравнение цвета без учета регистра
                                        System.out.println("Индекс: " + index + ", Одежда: " + clothing);
                                    }
                                }
                                break;

                            case 2:
                                // Поиск по индексу
                                System.out.print("Введите индекс для поиска: ");
                                try {
                                    int indexToSearch = Integer.parseInt(in.nextLine()); // Считывание индекса для поиска
                                    if (indexToSearch >= 0 && indexToSearch < clothingItems.size()) { // Проверка, что индекс находится в пределах списка
                                        Clothing clothing = clothingItems.get(indexToSearch); // Получение экземпляра одежды по индексу
                                        System.out.println("Одежда: " + clothing);
                                    } else {
                                        System.out.println("Неверный индекс");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Неверный ввод");
                                }
                                break;

                            case 3:
                                // Поиск по типу
                                System.out.print("Введите тип для поиска: ");
                                String typeToSearch = in.nextLine();
                                for (int index = 0; index < clothingItems.size(); index++) {
                                    Clothing clothing = clothingItems.get(index);
                                    if (typeToSearch.equalsIgnoreCase(clothing.type)) { // Сравнение типа без учета регистра
                                        System.out.println("Индекс: " + index + ", Одежда: " + clothing);
                                    }
                                }
                                break;

                            default:
                                System.out.println("Неверный выбор параметра для поиска");
                        }
                    } else {
                        System.out.println("Список одежды пуст");
                    }
                    break;

                case 5:
                    // Вывод параметров конкретной одежды
                    if (!clothingItems.isEmpty()) { // Проверка, что список не пуст
                        System.out.print("Введите индекс одежды для отображения: ");
                        try {
                            int index = Integer.parseInt(in.nextLine()); // Считывание индекса для отображения
                            if (index >= 0 && index < clothingItems.size()) { // Проверка, что индекс находится в пределах списка
                                Clothing clothing = clothingItems.get(index); // Получение экземпляра одежды по индексу
                                System.out.println("Параметры одежды: " + clothing);
                            } else {
                                System.out.println("Неверный индекс");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Неверный ввод");
                        }
                    } else {
                        System.out.println("Список одежды пуст");
                    }
                    break;

                case 6:
                    // Вывод списка одежды с указанием индекса каждого элемента
                    if (!clothingItems.isEmpty()) { // Проверка, что список не пуст
                        for (int index = 0; index < clothingItems.size(); index++) {
                            // Вывод индекса и параметров каждого элемента одежды
                            System.out.println("Индекс: " + index + ", Одежда: " + clothingItems.get(index));
                        }
                    } else {
                        System.out.println("Список одежды пуст");
                    }
                    break;

                case 7:
                    // Вывод количества предметов одежды в списке
                    System.out.println("Количество предметов одежды в списке: " + clothingItems.size());
                    break;

                case 8:
                    // Перемещение одежды в списке
                    if (!clothingItems.isEmpty()) { // Проверка, что список не пуст
                        System.out.print("Введите индекс перемещаемой одежды: ");
                        try {
                            // Ввод индекса элемента для перемещения
                            int index = Integer.parseInt(in.nextLine());

                            // Проверка на корректность введенного индекса
                            if (index >= 0 && index < clothingItems.size()) {
                                // Удаление одежды из текущей позиции
                                Clothing clothing = clothingItems.remove(index);

                                // Ввод количества позиций для перемещения
                                System.out.print("Введите количество позиций для перемещения: ");
                                int shiftAmount = Integer.parseInt(in.nextLine());

                                // Вычисление новой позиции после перемещения
                                int newIndex = index + shiftAmount;

                                // Добавление одежды в новую позицию
                                clothingItems.add(Math.max(0, Math.min(newIndex, clothingItems.size())), clothing);

                                // Вывод измененного расположения одежды
                                System.out.println("Одежда перемещена. Измененное расположение:");
                                for (Clothing item : clothingItems) {
                                    System.out.println(item);
                                }
                            } else {
                                System.out.println("Неверный индекс");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Неверный ввод");
                        }
                    } else {
                        System.out.println("Список одежды пуст");
                    }
                    break;
                case 9:
                    System.out.print("Введите имя файла (с расширением): ");
                    String fileName = in.nextLine();
                    saveToFile(fileName);
                    System.out.println("Данные сохранены в файл: " + fileName);
                    break;
                case 0:
                    System.out.println("Программа завершена. Данные сохранены в файле.");
                    System.exit(0);
            }
        }
    }
    // Метод для сохранения данных в файл
    private static void saveToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Clothing item : clothingItems) {
                writer.println(item);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных в файл.");
            e.printStackTrace();
        }
    }
    // Метод для загрузки данных из файла
    private static void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                Clothing clothing = new Clothing(parts[0], parts[1], parts[2], parts[3], new Person(parts[4]));
                clothingItems.add(clothing);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке данных из файла.");
            e.printStackTrace();
        }
    }
}
