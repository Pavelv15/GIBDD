package GIBDD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GIBDDOffice {
    private static BufferedReader reader;//для считывания консоли
    private List<CarNumber> carNumberList; //Коллекция номеров авто
    private String series; //Серия номера авто
    private String region; // Региион
    private boolean sameSeries, manualSeries = false;

    /**
     * Конструктор GIBDDOffice
     * @param count - Какой количесвто нужно создать авто номеров
     * @throws IOException
     */
    public GIBDDOffice(int count) throws IOException {
        carNumberList = new ArrayList<CarNumber>();
        reader = new BufferedReader(new InputStreamReader(System.in));
        getSeries();
        if(!sameSeries && !manualSeries) {
            if (series == null) {
                System.out.println("Невозможнос создать номер т.к задана неверная серия");
            } else {
                while (count > 0) {
                    createNumberCar();
                    count--;
                }
            }

        }
        if(sameSeries) {
            while (count > 0) {
                series = generateSeriesNumberCar();
                createNumberCar();
                count--;
            }
        }
        if (manualSeries) {
            while (count > 0) {
                manualSeriesEntry();
                createNumberCar();
                count--;
            }
        }

    }

    /**
     * Метод создания номера авто
     * @throws IOException
     */
    private void createNumberCar() throws IOException {
        String number;
        while (true){
            number = generateRandomNumber(3);
            if(isDublicate(number)){
                continue;
            }
            break;
        }
        System.out.println("Введите имя владельца номера: " + series.charAt(0) + " "  +  number  + " "  + series.charAt(1) + series.charAt(2) + " " + region) ;
        String fio = reader.readLine();
        var carnumber = new CarNumber(series,number,region,fio);
        carNumberList.add(carnumber);
        System.out.println("Создан номер автомабиля: " + series.charAt(0) + " " + number + " " + series.charAt(1) + series.charAt(2) + " " + region + " на имя " + fio) ;
    }
    boolean isDublicate(String number){
        for (CarNumber carnumber : carNumberList) {
            if(carnumber.getSeries().equals(series) && carnumber.getNumber().equalsIgnoreCase(number)){
                return true;
            }
        }
        return false;
    }

    /**
     * Метод проверки корректности введённой серии номера
     * @param s - ведённая серия авто номера
     * @return
     */
    static boolean isSeries(String s){
        if(s.length() == 3){
            char mas[] = s.toCharArray();
            char masB[] = {'A','B','E','K','M','H','O','P','C','T','Y','X'};
            int count = 0;
            for (int i = 0; i  < mas.length; i++){
                for(int j = 0; j < masB.length; j++) {
                    if (mas[i] ==  masB[j]) {
                        count++;
                        break;
                    }

                }
            }
            if (count == 3) {

                return true;
            }

            return false;
        }
        return false;
    }

    /**
     * Метод генерации номера
     * @param count - Длина генерируемого нормера
     * @return
     */
    static String generateRandomNumber(int count){
        var builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append((int)(Math.random() * 10));
        }
        return builder.toString();
    }

    /**
     * Метод автогенерации серии автономера
     * @return
     */
    static String generateSeriesNumberCar(){
        var builder = new StringBuilder();
        char masB[] = {'A','B','E','K','M','H','O','P','C','T','Y','X'};
        for (int i = 0; i < 3; i++) {
            builder.append(masB[(int)(Math.random() * masB.length)]);
        }
        return builder.toString();
    }

    /**
     * Метод ручного ввода серии
     * @throws IOException
     */
    private void manualSeriesEntry() throws IOException {
        System.out.println("Введите серию в формате XXX, где используют буквы А, В, Е, К, М, Н, О, Р, С, Т, У, Х ");
        var s = reader.readLine();//получили введенное значение
        if(isSeries(s)){
            series = s;//сохранили в поле класса, т.е. глобально для всех паспортов
        }

    }

    /**
     * Метод отображении автономеров с коллеции с выводом владельца
     * @return
     */
    public List<CarNumber> getCarNumberList() {
        for (CarNumber carNumber : carNumberList) {
            System.out.println(carNumber.getSeries().charAt(0) + " "  + carNumber.getNumber() + " " + carNumber.getSeries().charAt(1) + carNumber.getSeries().charAt(2) + " " + carNumber.getRegion()  + " " + carNumber.getFio());
        }
        return carNumberList;
    }

    /**
     * Метод создания серии автономера
     * @throws IOException
     */
    private void getSeries() throws IOException {
        System.out.println("Укажите регион");
        region = reader.readLine();
        System.out.println("Заполнить серию автоматически (a) или вручную (h)?");
        var answer = reader.readLine();
        switch (answer){
            case "h":
                System.out.println("Сделать одинаковую серию? d (Да), n(нет)  ");
                var answer3 = reader.readLine();
                switch (answer3) {
                    case "d":
                        manualSeries = false;
                        manualSeriesEntry();
                        break;
                    case  "n":
                        manualSeries = true;
                        break;
                    default:
                        System.out.println("Вы ввели некорректное значение!");
                }
                break;
            case "a":
                System.out.println("Сделать одинаковую серию? d (Да), n(нет)  ");
                var answer2 = reader.readLine();
                switch (answer2) {
                    case "d":
                        series = generateSeriesNumberCar();
                        sameSeries = false;
                        break;
                    case  "n":
                        sameSeries = true;
                        break;
                    default:
                        System.out.println("Вы ввели некорректное значение!");
                }
                break;
            default:
                System.out.println("Вы ввели некорректное значение!");
        }
    }

    /**
     * Метод поиска по номеру авто
     * @throws IOException
     */
    void showFIOByNumber() throws IOException {
        System.out.println(" Для поиска ведите ведите номер автомобиля в формате: A 123 BC 75 или A123BC75 ");
        var sn = reader.readLine();
        boolean isFind = false;
        if (sn.contains(" ")) {
            String mas[] = sn.split(" ");

            for (CarNumber carnumber : carNumberList) {
                if (carnumber.getNumber().equalsIgnoreCase(mas[1]) && carnumber.getRegion().equalsIgnoreCase(mas[3]) && (carnumber.getSeries().charAt(0) == mas[0].charAt(0)) && (carnumber.getSeries().charAt(1) == mas[2].charAt(0)) && (carnumber.getSeries().charAt(2) == mas[2].charAt(1))) {
                    System.out.println("Владелец: " + carnumber.getFio());
                    isFind = true;
                }
            }
            if (!isFind) {
                System.out.println("Номер авто не найден");
            }
        }
        else {
            String srav;
            for (int i = 0 ; i < carNumberList.size();i++) {
                srav =  carNumberList.get(i).getSeries().charAt(0)+ carNumberList.get(i).getNumber() + carNumberList.get(i).getSeries().charAt(1) + carNumberList.get(i).getSeries().charAt(2) + carNumberList.get(i).getRegion()  ;
                if(sn.equalsIgnoreCase(srav)) {
                    System.out.println("Найден владелец.ФИО " + carNumberList.get(i).getFio());
                    isFind = true;

                }


            }
            if (!isFind) {
                System.out.println("Номер авто не найден");
            }

        }
    }
}

