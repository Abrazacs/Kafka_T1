Учебный проект для знакомства с Kafka. 
Состоит из 2 микросервисов: MetricProducer и MetricConsumer. MetricProducer - собирает метрики о себе из Spring Boot Actuator и передает из через Kafka в MetricConsumer.
Помимо самих микропроцессоро содержит docker-compose.yml для подъема необходимого окружения в докере (Zookeeper и Kafka).

## MetricProducer
Чтобы собрать какую-либо метрику и передать ее в MetricConsumer необходимо отправить `POST` запрос на `api/v1/metrics/{metricName}`
```
curl POST 'http://localhost:8080/api/v1/metrics/disk.free'
```
Перечень доступных 'metricName' можно посмотреть отправить `GET` запрос на `/actuator/metrics`
```
curl GET 'http://localhost:8080/actuator/metrics'
```
Полученные метрики MetricConsumer передает в топик `metric` 

## MetricConsumer
Слушает топик `metric` и сохраняет полученные из MetricProducer метрики.
Перечень сохраненных метрик можно получить отправив `GET` запрос на `/api/v1/metrics/`
```
curl GET 'http://localhost:8081/api/v1/metrics/'
```
Response:
```json
[
    "jvm.memory.max",
    "executor.pool.max",
    "jvm.classes.loaded",
    "disk.free"
]
```

Получить конкретную метрику можно направив `GET` запрос на `/api/v1/metrics/{name}`
```
curl GET 'http://localhost:8081/api/v1/metrics/disk.free'
```
Response:
```json
{
    "name": "disk.free",
    "unitOfMeasure": "bytes",
    "value": 5.8057289728E11
}
```
