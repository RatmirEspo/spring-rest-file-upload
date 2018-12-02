**Задание**

Реализовать простое REST API с одним единственным методом, который загружает изображения.

Требования:
- Возможность загружать несколько файлов.
- Возможность принимать multipart/form-data запросы.
- Возможность принимать JSON запросы с BASE64 закодированными изображениями.
- Возможность загружать изображения по заданному URL (изображение размещено где-то в интернете).
- Создание квадратного превью изображения размером 100px на 100px.


**Структура проекта**

- pom.xml - главный pom файл проекта
  |
  server - серверная часть проекта
    |
    load-app - модуль приложения Spring, содержит точку входа в приложение и базовые конфигурационные файлы
    load-api - модуль слоя контроллеров REST-API
    load-service - модуль бизнес логики
    load-common - модуль содержит общие интерфейсы бизнес логики, а также DTO объекты

  Проект реализован на SPRING Boot 2.0.5.RELEASE, написаны unit тесты и интеграционные тесты

**BUILD**
   :java_version: 1.8
    Для сборки необходимо выполнить **mvn clean install**

**RUN**

    Для запуска приложения необходимо запустить
      В IDE - com.easy.upload.application.Application
      Командной строкой:
      java -jar load-app-0.1.0-SNAPSHOT.jar com.easy.upload.application.Application





