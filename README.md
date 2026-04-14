# Aston homework 5

Консольное Java-приложение для заполнения, сортировки, сохранения и проверки коллекции пользователей.

Проект выполнен в рамках задания по ручной реализации алгоритмов сортировки и работе с кастомными классами. Основная коллекция проекта: `UserList`, внутри которой хранятся объекты `User`.

## Команда

- Владимир: алгоритмы сортировки `BubbleSortStrategy`, `SelectionSortStrategy`, `InsertionSortStrategy`, режим `EVEN_ODD`.
- Роман: компараторы и валидация, `UserComparator`, `UserValidator`.
- Вадим: ввод/вывод, `ManualInputService`, `FileInputService`, `RandomUserGenerator`, `FileSaveUserService`, `MultiThreadCounter`.
- Станислав: `Main`, меню, цикл программы, интеграция модулей, обработка ошибок, финальная проверка и merge веток.

## Модель данных

Основной класс: `User`.

Поля:

- `id`
- `name`
- `email`
- `password`

Объекты создаются через `User.BuilderUser`.

Валидация:

- `id > 0`
- `name` не пустой
- `email` содержит `@` и `.`
- `password` не короче 6 символов

## Коллекция

Для хранения пользователей используется кастомная коллекция `UserList`.

Основные методы:

- `add(User user)`
- `get(int index)`
- `set(int index, User user)`
- `remove(int index)`
- `size()`
- `isEmpty()`
- `clear()`
- `toList()`

При добавлении пользователя коллекция проверяет, что объект не равен `null`.

## Структура проекта

```text
homework5
├── Main.java
├── User.java
├── collection
│   ├── UserCollection.java
│   └── UserList.java
├── service
│   ├── FileInputService.java
│   ├── FileSaveUserService.java
│   ├── ManualInputService.java
│   ├── MultiThreadCounter.java
│   ├── RandomUserGenerator.java
│   ├── UserComparator.java
│   └── UserValidator.java
├── strategy
│   ├── BubbleSortStrategy.java
│   ├── InsertionSortStrategy.java
│   ├── SelectionSortStrategy.java
│   ├── SortMode.java
│   └── SortStrategy.java
├── test
│   └── ручные тесты
└── users.txt
```

## Сортировки

Сортировки реализованы через паттерн Strategy.

Интерфейс:

- `SortStrategy`

Реализации:

- `BubbleSortStrategy`
- `SelectionSortStrategy`
- `InsertionSortStrategy`

Сортировка поддерживается по полям:

- `id`
- `name`
- `email`
- `password`

Готовые сортировки `Collections.sort()`, `List.sort()`, `Arrays.sort()`, `stream().sorted()` не используются.

## Even/Odd сортировка

Дополнительно реализован режим `EVEN_ODD` по числовому полю `id`.

Логика:

- пользователи с четным `id` сортируются по возрастанию;
- пользователи с нечетным `id` остаются на исходных позициях.

Режим выбирается в меню после выбора алгоритма и поля сортировки.

## Заполнение данных

Программа поддерживает три способа заполнения:

- ручной ввод;
- ввод из файла;
- случайная генерация.

Случайная генерация реализована через `Stream API`.

Для ввода из файла используется файл `users.txt`.

Формат строки:

```text
id,name,email,password
```

Пример:

```text
1,Alexey,alexey@mail.ru,parol123
```

## Дополнительные функции

- Сохранение коллекции в файл в режиме append.
- Многопоточный подсчет количества пользователей с заданным `id`.
- Защита от пустой коллекции перед сортировкой, сохранением и подсчетом.

## Меню

```text
1 - Ввод вручную
2 - Ввод из файла
3 - Случайная генерация
4 - Bubble sort
5 - Selection sort
6 - Insertion sort
7 - Сохранить в файл
8 - Подсчитать вхождения id
9 - Показать текущую коллекцию
0 - Exit
```

Программа работает в цикле. Выход выполняется только через пункт `0`.

## Тесты

Тесты реализованы вручную в отдельных классах без JUnit.

Основные тестовые классы:

- `ComparatorTest`
- `ValidatorTest`
- `UserListTest`
- `EvenOddSortTest`
- `MultiThreadCounterTest`
- `UserGenerateTest`

Дополнительные ручные проверки:

- `FileInputServiceTest`
- `FileSaveServiceTest`
- `ManualInputTest`

## Запуск

Запускать нужно класс:

```text
homework5.Main
```

Если запуск происходит из IntelliJ IDEA, файл `users.txt` должен лежать в рабочей директории запуска. Обычно это корень проекта `AstonStudy`.

Для запуска из терминала нужно находиться в папке `src` проекта:

```bash
javac -d /tmp/aston-homework5-classes $(find homework5 -name "*.java")
java -cp /tmp/aston-homework5-classes homework5.Main
```

## Запуск ручных тестов

После компиляции проекта можно запускать тестовые классы отдельно:

```bash
java -cp /tmp/aston-homework5-classes homework5.test.ComparatorTest
java -cp /tmp/aston-homework5-classes homework5.test.ValidatorTest
java -cp /tmp/aston-homework5-classes homework5.test.UserListTest
java -cp /tmp/aston-homework5-classes homework5.test.EvenOddSortTest
java -cp /tmp/aston-homework5-classes homework5.test.MultiThreadCounterTest
java -cp /tmp/aston-homework5-classes homework5.test.UserGenerateTest
```

Каждый тестовый класс содержит метод `main` и выводит результат проверки в консоль.
