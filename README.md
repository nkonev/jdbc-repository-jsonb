# Reproduction

1\.
```
docker-compose up -d
```

2\. Launch Application.kt


# Play with data
```
curl -i -X POST -H "Content-Type: application/json" 'http://localhost:8080/user' -d '{"name": "name10", "data": {"age": 86}}'
curl -i 'http://localhost:8080/user'
```