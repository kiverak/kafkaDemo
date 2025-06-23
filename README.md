# Producer

## Описание

**Producer** — это микросервис, разработанный на Spring Boot, который предоставляет один HTTP-эндпоинт для создания заказов. При получении запроса сервис генерирует уникальный идентификатор заказа, дублирует полученную информацию и отправляет её в Kafka-топик (например, "orders"). Таким образом, Order-Service выступает в роли продюсера, передающего данные о заказе для дальнейшей обработки другими компонентами системы.

## Docker Compose для Kafka

В проекте присутствует файл `docker-compose.yml`, который поднимает необходимые контейнеры для работы с Kafka (Kafka-брокер и Zookeeper).  
Чтобы запустить Kafka, выполните следующую команду в терминале:

```bash
docker-compose up -d
```

Убедитесь, что у вас установлен Docker и Docker Compose. Подробнее об установке смотрите в разделе ниже.

## Тестирование

### Нагрузочное тестирование

Для имитации нагрузки на HTTP-эндпоинт запустите скрипт:

```bash
bash http/http_load_test.sh
```

Этот скрипт использует Apache Bench (ab) для отправки 100 запросов с 10 параллельными потоками.

### Тестирование единичного запроса

Чтобы отправить единичный запрос, откройте файл `save-order.http` в вашем редакторе (например, в VS Code с плагином REST Client) и выполните запрос, либо воспользуйтесь следующей командой:

```bash
curl -X POST -H "Content-Type: application/json" \
-d '{"orderId": "12345", "product": "product-name", "quantity": 10}' \
http://localhost:8080/orders
```

## Зависимости и установка необходимых инструментов

### Docker & Docker Compose

**Docker** необходим для запуска контейнеров с Kafka и Zookeeper.

- **Linux:**  
  Инструкции по установке Docker можно найти на  
  [Docker Engine Installation Guide for Linux](https://docs.docker.com/engine/install/).

- **macOS:**  
  Установите [Docker Desktop for Mac](https://docs.docker.com/desktop/mac/install/).

- **Windows:**  
  Используйте [Docker Desktop for Windows](https://docs.docker.com/desktop/windows/install/).

Для Linux Docker Compose можно установить согласно  
[инструкциям по установке Docker Compose](https://docs.docker.com/compose/install/).  
На macOS и Windows Docker Compose обычно входит в состав Docker Desktop.

### Apache Bench (ab)

**Apache Bench** используется для нагрузочного тестирования HTTP-эндпоинта.

- **Linux (Ubuntu/Debian):**

```bash
sudo apt update
sudo apt install apache2-utils
```

- **macOS:**  
  Установите через Homebrew:

```bash
brew install apache2
```

- **Windows:**
    - Можно использовать сборку Apache HTTP Server, доступную на [Apache Lounge](https://www.apachelounge.com/download/), где утилита `ab` включена.
    - Либо воспользоваться Windows Subsystem for Linux (WSL) и установить `apache2-utils` так же, как на Linux.

## Заключение

KafkaDemo демонстрирует базовую интеграцию Spring Boot с Apache Kafka через простой HTTP-эндпоинт, который отправляет информацию о заказе в Kafka.