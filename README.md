# Сервис получения курсов валют
**Сервис возвращает список курсов из ЦБР**

### Доступные методы
1. getcurs(String datetime,String[]vcode)<br>
   Параметры:<br>
   - datetime, обязательный - дата и время в формате yyyy-MM-dd'T'HH:mm:ss.
   - vcode, не обязательный - список кодов валют в формате vcode=AUD,BYN. Проверка на количество символов

    Возвращает:
   Список курсов в формате 
   ```   
   {
        "code": "36",
        "vchCode": "AUD",
        "name": "Австралийский доллар",
        "curs": 53.2432,
        "_links": {
            "self": {
                "href": "http://localhost:8081/getcurs?datetime=2021-09-22T13:30:00&vcode=AUD"
            },
            "curses": {
                "href": "http://localhost:8081/getcurs?datetime=2021-09-22T13:30:00&vcode="
            }
        }
   }
   ```
   соответствующие условиям либо ошибку валидации
    ```
    {
        "status": "BAD_REQUEST",
        "message": "Не корректные входные параметры",
        "errors": [
            "Код валюты \"AU\" не соответствует ограничениям. Код должен состоять из 3 букв"
        ]
    }
    ```

###Технологии
1. Java 8
2. Spring Boot

###Как запускать

```
java -jar rates-vv.jar
```
или
```
mvn clean install