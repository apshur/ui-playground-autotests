# UI Playground Autotests

Проект автотестов пользовательского интерфейса для сайта **UI Test Automation Playground**.

В проекте реализовано 5 UI-автотестов:

1. **Sample App** — успешная авторизация пользователя.
2. **Frames** — поиск и клик по кнопке внутри `iframe`.
3. **Text Input** — проверка изменения текста кнопки после ввода значения.
4. **AJAX Data** — ожидание и проверка результата AJAX-запроса.
5. **Dynamic ID** — проверка стабильного поиска элемента без привязки к динамическому `id`.

## Стек

- Java 17
- Maven
- JUnit 5
- Playwright Java
- Allure Report
- SLF4J Simple Logger

## Структура проекта

```text
ui-playground-autotests/
├── pom.xml
├── README.md
├── .gitignore
├── .github/
│   └── workflows/
│       └── ui-tests.yml
└── src/
    └── test/
        ├── java/
        │   └── ru/example/playground/
        │       ├── support/
        │       │   ├── BaseTest.java
        │       │   └── AllureScreenshotExtension.java
        │       └── tests/
        │           ├── AjaxDataTest.java
        │           ├── DynamicIdTest.java
        │           ├── FramesTest.java
        │           ├── SampleAppTest.java
        │           └── TextInputTest.java
        └── resources/
            ├── allure.properties
            └── simplelogger.properties
```

## Предварительные требования

Перед запуском должны быть установлены:

- JDK 17+
- Maven 3.8+
- Git

Проверка версий:

```bash
java -version
mvn -version
git --version
```

## Как развернуть проект локально

Склонировать репозиторий:

```bash
git clone <ссылка-на-твой-публичный-репозиторий>
cd ui-playground-autotests
```

Установить браузер Chromium для Playwright:

```bash
mvn -q exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install chromium"
```

Для Linux-сервера или GitHub Actions можно установить браузер вместе с системными зависимостями:

```bash
mvn -q exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install --with-deps chromium"
```

## Как запустить тесты

Обычный запуск в headless-режиме:

```bash
mvn clean test
```

Запуск с открытым браузером:

```bash
mvn clean test -Dheadless=false
```

Запуск с замедлением действий, удобно для демонстрации:

```bash
mvn clean test -Dheadless=false -DslowMo=500
```

Запуск против другого base URL:

```bash
mvn clean test -DbaseUrl=https://www.uitestingplayground.com
```

## Где лежат результаты Allure

После запуска тестов результаты Allure будут сохранены в директории:

```text
target/allure-results
```

Сгенерировать HTML-отчет:

```bash
mvn allure:report
```

Открыть готовый отчет:

```text
target/site/allure-maven-plugin/index.html
```

Либо сразу поднять отчет локально:

```bash
mvn allure:serve
```

## Логирование и скриншоты

В проекте настроено логирование через `slf4j-simple`.

При падении теста расширение `AllureScreenshotExtension` автоматически прикладывает в Allure скриншот страницы. Это помогает быстрее понять, на каком состоянии интерфейса упал тест.

## Тестовые сценарии

### 1. Sample App

Проверяется позитивный сценарий авторизации:

1. Открыть `/sampleapp`.
2. Ввести имя пользователя.
3. Ввести валидный пароль `pwd`.
4. Нажать `Log In`.
5. Проверить текст приветствия: `Welcome, Vladislav!`.

### 2. Frames

Проверяется работа с фреймами:

1. Открыть `/frames`.
2. Найти кнопку `Button` внутри доступных `iframe`.
3. Выполнить клик.
4. Проверить, что кнопка была найдена и клик выполнен без ошибки.

### 3. Text Input

Проверяется изменение текста кнопки:

1. Открыть `/textinput`.
2. Ввести новый текст кнопки.
3. Нажать кнопку.
4. Проверить, что текст кнопки стал равен введенному значению.

### 4. AJAX Data

Проверяется ожидание асинхронной загрузки:

1. Открыть `/ajax`.
2. Нажать кнопку запуска AJAX-запроса.
3. Дождаться сообщения `Data loaded with AJAX get request.`.
4. Проверить текст сообщения.

### 5. Dynamic ID

Проверяется стабильность локатора:

1. Открыть `/dynamicid`.
2. Найти кнопку не по `id`, а по роли и тексту.
3. Проверить, что кнопка отображается.
4. Выполнить клик.

## Как загрузить проект в публичный Git-репозиторий

Создать новый публичный репозиторий на GitHub, GitLab или другом Git-хостинге.

В корне проекта выполнить:

```bash
git init
git add .
git commit -m "Add UI Playground autotests"
git branch -M main
git remote add origin <ссылка-на-твой-репозиторий>
git push -u origin main
```

После этого в ответе на задание можно указать ссылку на публичный репозиторий.

## Что приложить к отчету

Для сдачи задания нужно приложить:

1. Ссылку на публичный Git-репозиторий.
2. Скриншот Allure-отчета после запуска тестов.

На скриншоте Allure должно быть видно:

- общее количество пройденных тестов;
- количество упавших тестов;
- характер ошибок, если они есть.
