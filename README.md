# Usage

1\.
```
docker-compose up -d
```

2\. Launch Application.kt
```
curl -i -X POST -H "Content-Type: application/json" 'http://localhost:8080/user' -d '{"name": "name10", "personData": {"age": 86}, "sessionData": {"token": "abc"}}'
curl -i 'http://localhost:8080/user'
```

3\.
```
docker-compose exec postgresql psql -U jdbc
select * from customers;
```