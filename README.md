# <span style="color: aqua">API Billing Warnet</span>

Aplikasi Billing warnet ini dibuat menggunakan Java Spring Boot. aplikasi ini menerapkan :

- Spring Ioc
- Java Stream
- Native SQL Query
- Restful API
- Scheduler menggunakan cron job untuk execute method stop billing

### Aplication Properties

Konfigurasi Aplikasi berisi config database, config jpa, serta config error-stacktrace :

```properties
spring.datasource.username={username-datasource}
spring.datasource.password={password-datasource}
spring.datasource.url=jdbc:postgresql://localhost:5432/{database-name}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.error.include-stacktrace=never
```

## Beberapa Endpoint yang tersedia

#### Mengelola data billing

- **POST** `/api/v1/billing` : Menambahkan data billing baru

  Request Body :

  ```json
  {
    "customerId": "string",
    "computerId": "string",
    "rentalMinutes": "Long"
  }
  ```

- **GET** `/api/v1/billing` : Menampilkan semua data billing active

      Response Body :
      ```json
      {
      "statusCode": 200,
      "message": "Successfully get all used billing",
      "data": [
          {
              "id": "4042d376-6dbe-4e5b-9c3b-22e87cdb5072",
              "username": "test_a49c5d4ccdfb",
              "customerName": "test_859250d23c69",
              "computerName": "PC03",
              "minutes": 120,
              "isUsed": true,
              "startAt": "2023-08-04T22:53:24.349+00:00",
              "endAt": "2023-08-05T00:53:24.349+00:00"
          }
      ],
      "paging": {
          "currentPage": 1,
          "totalPage": 1,
          "size": 10
      }

  }
  ```

- **PUT** `/api/v1/billing/{id}` : set update data billing agar isUsed menjadi false berdasarkan
- **DELETE** `/api/v1/billing/{id}` : Menghapus data billing berdasarkan id

#### Mengelola data computer

- **POST** `/api/v1/computers` : Menambahkan data computer baru

  Request Body :

  ```json
  {
    "name": "string",
    "price": "Long"
  }
  ```

- **GET** `/api/v1/computers` : Menampilkan semua data computer

      Response Body :

      ```json
      {
      "statusCode": 200,
      "message": "Successfully get all computers",
      "data": [
          {
              "id": "3a899e97-87a3-4261-bc7e-286c69725503",
              "computerName": "PC01",
              "pricePerHour": 20000
          },
          {
              "id": "dfdb2856-bd56-460c-a7c9-638dce2009d4",
              "computerName": "PC02",
              "pricePerHour": 5000
          },
          {
              "id": "6642d24c-c7c6-49c7-aff9-026805e0c0bb",
              "computerName": "PC03",
              "pricePerHour": 5000
          }
      ],
      "paging": {
          "currentPage": 1,
          "totalPage": 1,
          "size": 10
      }

  }
  ```

- **DELETE** `/api/v1/computers/{id}` : Menghapus data computer berdasarkan id
- **PUT** `/api/v1/computers` : Mengupdate data computer berdasarkan request body

  Request Body :

  ```json
  {
    "id": "string",
    "name": "string",
    "price": "Long"
  }
  ```

#### Menampilkan report Data

- **GET** `/api/v1/report` : menampilkan report data

      Response Body :

      ```json
      {
      "statusCode": 200,
      "message": "Successfully get all report data",
      "data": [
          {
              "customerName": "test_859250d23c69",
              "rentalTime": 120,
              "total_price": 40000,
              "createAt": "2023-08-04T20:58:34.309+00:00",
              "computerName": "PC01"
          },
          {
              "customerName": "test_859250d23c69",
              "rentalTime": 60,
              "total_price": 5000,
              "createAt": "2023-08-04T21:24:12.678+00:00",
              "computerName": "PC02"
          },
          {
              "customerName": "test_859250d23c69",
              "rentalTime": 1,
              "total_price": 83,
              "createAt": "2023-08-04T21:36:21.442+00:00",
              "computerName": "PC03"
          },
          {
              "customerName": "test_859250d23c69",
              "rentalTime": 120,
              "total_price": 10000,
              "createAt": "2023-08-04T22:53:24.349+00:00",
              "computerName": "PC03"
          }
      ],
      "paging": {
          "currentPage": 1,
          "totalPage": 1,
          "size": 10
      }

  }
  ```

### <span style="color: aqua">Serta masih terdapat 2 Request Mapping End Point untuk mengelola data customer dan data operator</span>
