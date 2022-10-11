# Explore With Me

Сервис позволяет делиться информацией об интересных событиях и помогает найти компанию для участия в них. 

## Стек
- Java 11
- Spring Boot 2.7.0
- PostgreSQL 42.3
- Maven сборка

## Архитектура

Сервис состоит из двух приложений
- основное приложение ([API](ewm-main-service-spec.json))
- сервис статистики просмотров ([API](ewm-stats-service-spec.json))

### Основной сервис

API основного сервиса разделён на три части:
* Публичная, доступна без регистрации любому пользователю сети. 
* Закрытая, доступна только авторизованным пользователям. 
* Административная, для администраторов сервиса.

#### Публичное API:

* Сортировка списка событий по количеству просмотров либо по датам событий.
* Просмотр подробной информации о конкретном событии.
* Есть возможность получения всех имеющихся категорий и подборок событий (такие подборки могут составлять администраторы ресурса).
* Каждый публичный запрос для получения списка событий или полной информации о мероприятии фиксируется сервисом статистики.

#### Закрытое API:

* Пользователи могут добавлять в приложение новые мероприятия, редактировать их и просматривать после добавления.
* Могут подавать заявки на участие в интересующих мероприятиях.
* Создатель мероприятия имеет возможность подтверждать заявки, которые отправили другие пользователи сервиса.

#### Административное API:

* Добавление, изменение и удаление категорий для событий.
* Возможность добавлять, удалять и закреплять на главной странице подборки мероприятий.
* Модерацию событий, размещённых пользователями, — публикация или отклонение.
* Управление пользователями — добавление, просмотр и удаление.

### Сервис статистики

Призван собирать информацию о количестве обращений пользователей к спискам событий и о количестве запросов к подробной информации о событии.

## Сборка и развёртывание
Требуется установленный Apache Maven

```
git clone git@github.com:agluh/java-explore-with-me.git
cd java-explore-with-me
mvn package
echo STATS_DB_USER=stats > .env
echo STATS_DB_PASS=stats >> .env
echo MAIN_DB_USER=main >> .env
echo MAIN_DB_PASS=main >> .env
docker-compose up -d
```