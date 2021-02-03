# Getting Started
```
curl -i 'http://localhost:8080/user'
curl -i -X POST -H "Content-Type: application/json" 'http://localhost:8080/user' -d '{"name": "name1"}'
docker-compose exec postgresql psql -U jdbc
select * from customers;
curl -i -X POST -H "Content-Type: application/json" 'http://localhost:8080/user' -d '{"name": "name10", "data": {"age": 86}}'
```

