# Setup

You need to host your own postgreSQL server and add connection info to `BookwormDatabase#createHikariDS`
All tables will be created automatically

# How to run

``` 
./gadlew run
```

# How to connect

[127.0.0.1:4721/]()

## Routes

*[127.0.0.1:4721/books]() - запросить все книги

*[127.0.0.1:4721/books/{id}]() - запросить книгу по ID

*[127.0.0.1:4721/books?query=some_query]() - поиск книг по названию

*[127.0.0.1:4721/user/register]() - регистрация

*[127.0.0.1:4721/user/login]() - авторизация

*[127.0.0.1:4721/user/{login}]() - просмотр пользователя