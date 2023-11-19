package com.company;

import java.sql.SQLOutput;
import java.util.*;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, String> sitesMap = new HashMap<String, String>();
        sitesMap.put("pikabu.ru", "Котята занялись рукоделием, посмотрите. #пятничное #моё #котята");
        sitesMap.put("vk.com", "Бизнес-цитаты и поцанский паблик. Заработал МИЛЛИОН за день в 16 лет.");
        sitesMap.put("rutracker.org", "Доступ запрещен. Слава Роскомнадзору!");
        Map<String, Map<String, Integer>> index = new HashMap<>(); // Создаем мапу для индексов

        while (true) {
            System.out.println("Введите поисковый запрос:");
            String input = sc.nextLine().toLowerCase(); // приводим весь текст ввода в нижний регистр
            if (input.equals("exit")) {
                System.out.println("Программа завершена");
                break;
            }
            String[] part = input.split(" "); // Сплитим строку на части

            for (Map.Entry<String, String> wordEntry : sitesMap.entrySet()) { // Получаем ключ значение во врем переменную wordEntry
                Map<String, Integer> word = new HashMap<>(); // Создаем переменную для сайта и количества совпавших слов ввода
                int count = 0;

                String toLower = wordEntry.getValue().toLowerCase(); // приводим весь текст (значение wordEntry.getValue()) в нижний регистр
                toLower = toLower.replaceAll("\\p{P}", ""); // убираем все знаки пунктуации из значения

                for (int i = 0; i < part.length; i++) {
                    if (toLower.contains(part[i])) {  // Из врем переменной берем значение и сравниваем с введенным словом
                        count++;
                        word.put(wordEntry.getKey(), count);
                    }
                }
                index.put(wordEntry.getKey(), word);
            }
        }

        for (Map.Entry<String, Map<String, Integer>> vivod : index.entrySet()) { // Получаем ключ значение во врем переменную vivod
            Map<String, Integer> vivod1 = vivod.getValue(); // Получаем ключ значение во врем переменную vivod1

            for (Map.Entry<String, Integer> vivod2 : vivod1.entrySet()) { // Получаем ключ значение во врем переменную vivod2
                System.out.println("Сайт: " + vivod2.getKey() + ", совпадений по запросу: " + vivod2.getValue());
            }
        }

        System.out.println("-------------------------------------------");

        // Сортировка
        for (Map.Entry<String, Map<String, Integer>> sortMap : index.entrySet()) {
            Map<String, Integer> wordMap = sortMap.getValue();

            List<Map.Entry<String, Integer>> sortedResults = new ArrayList<>(wordMap.entrySet());
            Collections.sort(sortedResults, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue()); // сортировка по убыванию
                }
            });
        }

        for (Map.Entry<String, Map<String, Integer>> vivod : index.entrySet()) { // Получаем ключ значение во врем переменную vivod
            Map<String, Integer> vivod1 = vivod.getValue(); // Получаем ключ значение во врем переменную vivod1

            for (Map.Entry<String, Integer> vivod2 : vivod1.entrySet()) { // Получаем ключ значение во врем переменную vivod2
                System.out.println("Сайт: " + vivod2.getKey() + ", совпадений по запросу: " + vivod2.getValue());
            }
        }
    }
}