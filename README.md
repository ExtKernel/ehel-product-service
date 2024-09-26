# Product Service
A Java-based microservice for managing CRUD operations on various objects that represent marketplace products. 
At this point, only eBay is supported.

## Endpoints
Every `/secured` endpoint requires an Oauth2 Bearer token
that contains the role specified in the `PRINCIPAL_ROLE_NAME` environment variable.
See the configuration documentation under the **Configuration** section.

# Default Product
This product type represents a generic product.

### Create
Endpoint: `POST /secured/product/default`

JSON request body explanation:
```json
{
  "id": "unique-id-for-default-product", // Required
  "title": "product-title", // Required
  "description": "product-description", // Optional
  "condition": "new-or-used-condition", // Required
  "price": "price-in-cents-or-currency-units", // Required
  "quantity": "quantity-available-in-stock" // Required
}
```

### Find all
Endpoint: `GET /secured/product/default`

### Find by ID
Endpoint: `GET /secured/product/<default-product-id>`

### Update
Endpoint: `PUT /secured/product/default`

The same request body is required as for the **Create** endpoint.

### Delete
Endpoint: `DELETE /secured/product/<default-product-id>`

# Comp Product
This product type represents a generic computer product.

### Create
Endpoint: `POST /secured/product/comp`

JSON request body explanation:
```json
{
  "id": "unique-id-for-comp-product", // Required
  "title": "product-title", // Required
  "description": "product-description", // Optional
  "condition": "new-or-used-condition", // Required
  "price": "price-in-cents-or-currency-units", // Required
  "quantity": "quantity-available-in-stock", // Required
  "cpu": "cpu-model-or-name", // Required
  "ramCapacity": "amount-of-ram-in-gb", // Required
  "ramType": "ram-technology-type-ddr4-ddr5-etc", // Required
  "gpuType": "integrated-or-dedicated-gpu", // Required
  "gpuModel": "specific-gpu-model-name", // Optional
  "gpuVramCapacity": "gpu-memory-size-in-gb", // Optional
  "gpuVramType": "gpu-memory-type-gddr6-gddr5-etc", // Optional
  "storageUnitType": "hdd-or-ssd", // Optional
  "storageUnitCapacity": "storage-capacity-in-gb", // Optional
  "os": "operating-system-installed", // Optional
  "goodFor": "recommended-use-case-for-product" // Required
}
```

### Find all
Endpoint: `GET /secured/product/comp`

### Find by ID
Endpoint: `GET /secured/product/<comp-product-id>`

### Update
Endpoint: `PUT /secured/product/comp`

The same request body is required as for the **Create** endpoint.

### Delete
Endpoint: `DELETE /secured/product/<comp-product-id>`

# Laptop Product
This product type represents a generic laptop.

### Create
Endpoint: `POST /secured/product/laptop`

JSON request body explanation:
```json
{
  "id": "unique-id-for-laptop-product", // Required
  "title": "product-title", // Required
  "description": "product-description", // Optional
  "condition": "new-or-used-condition", // Required
  "price": "price-in-cents-or-currency-units", // Required
  "quantity": "quantity-available-in-stock", // Required
  "cpu": "cpu-model-or-name", // Required
  "ramCapacity": "amount-of-ram-in-gb", // Required
  "ramType": "ram-technology-type-ddr4-ddr5-etc", // Required
  "gpuType": "integrated-or-dedicated-gpu", // Required
  "gpuModel": "specific-gpu-model-name", // Optional
  "gpuVramCapacity": "gpu-memory-size-in-gb", // Optional
  "gpuVramType": "gpu-memory-type-gddr6-gddr5-etc", // Optional
  "storageUnitType": "hdd-or-ssd", // Optional
  "storageUnitCapacity": "storage-capacity-in-gb", // Optional
  "os": "operating-system-installed", // Optional
  "goodFor": "recommended-use-case-for-product", // Required
  "releaseDate": "release-date-in-yyyy-mm-dd-format", // Required
  "comesWithCharger": "true-if-laptop-comes-with-charger", // Required
  "screenType": "screen-technology-e.g.-LCD-OLED", // Required
  "screenResolution": "screen-resolution-e.g.-1920x1080", // Required
  "hasBacklit": "true-if-keyboard-has-backlit", // Required
  "batteryHealth": "battery-health-percentage", // Optional
  "model": "laptop-model-name" // Required
}
```

### Find all
Endpoint: `GET /secured/product/laptop`

### Find by ID
Endpoint: `GET /secured/product/<laptop-product-id>`

### Update
Endpoint: `PUT /secured/product/laptop`

The same request body is required as for the **Create** endpoint.

### Delete
Endpoint: `DELETE /secured/product/<comp-laptop-id>`

## Configuration

This application relies on several environment variables for proper configuration. Below is an overview of customizable variables and their default values:

### General
- **PRINCIPAL_ROLE_NAME**: Defines the role required to access secured endpoints. Defaults to `administrator`.
- **EUREKA_URI**: The URL of the Eureka server. Defaults to `http://localhost:8761/eureka`.
- **SERVER_PORT**: The port on which the server runs. Defaults to `8000`.

### Database
The application is configured to use PostgreSQL. The following environment variables should be set:
- **DATASOURCE_HOST**: The host of the PostgreSQL server.
- **DATASOURCE_NAME**: The name of the database.
- **DATASOURCE_USERNAME**: The username for the database.
- **DATASOURCE_PASSWORD**: The password for the database user.

### DevTools
- **LIVERELOAD_PORT**: The port for LiveReload, defaults to `35730`.

### Security (OAuth2)
The application uses OAuth2 with support for both JWT and opaque tokens:
- **OAUTH2_PROVIDER_ISSUER_URL**: The issuer URL of your OAuth2 provider.
- **OAUTH2_PROVIDER_CLIENT_ID**: The client ID for the OAuth2 provider.
- **OAUTH2_PROVIDER_CLIENT_SECRET**: The client secret for the OAuth2 provider.
- **OAUTH2_PROVIDER_INTROSPECTION_URL**: The introspection URL for validating opaque tokens.

# Usage
## Java jar
1) Clone the repository:
    ```bash
      git clone https://github.com/ExtKernel/ehel-product-service.git
    ```
2) Navigate to the directory:
    ```bash
      cd ehel-product-service
    ```
3) Run:
    ```bash
      mvn package
    ```
4) Run the .jar file:
    ```bash
      java -jar target/ehel-product-service-<VERSION>.jar    
    ```
## Maven plugin
1) Clone the repository:
    ```bash
      git clone https://github.com/ExtKernel/ehel-product-service
    ```
2) Navigate to the directory:
    ```bash
      cd ehel-product-service
    ```
3) Run:
    ```bash
      mvn spring-boot:run
    ```

## Docker
1) Pull the Docker image:
    ```bash
      docker pull exkernel/ehel-product-service:<VERSION>
    ```
2) Run the container:
    ```bash
      docker run --name=ehel-product-service -p 8000:8000 exkernel/ehel-product-service:<VERSION>
    ```
   - You can map any external port you want to the internal one
   - You can give any name to the container

   Remember to specify environment variables using the `-e` flag:
   - `-e EUREKA_URI=<value>`
   - `-e DATASOURCE_HOST=<value>`
   - `-e DATASOURCE_USERNAME=<value>`
   - `-e DATASOURCE_PASSWORD=<value>`
   - `-e PRINCIPAL_ROLE_NAME=<value>`
   - `-e OAUTH2_PROVIDER_ISSUER_URL=<value>`
   - `-e OAUTH2_PROVIDER_CLIENT_ID=<value>`
   - `-e OAUTH2_PROVIDER_CLIENT_SECRET=<value>`
   - `-e OAUTH2_PROVIDER_INTROSPECTION_URL=<value>`

   You may also specify the optional ones if you want:
   - `-e LIVERELOAD_PORT=<value>`

**BUT BE AWARE**: `-e SERVER_PORT=<value>` - changes the internal port of the service, which won't be considered by the [Dockerfile](Dockerfile). There always will be port `8000` exposed, until you change it and build the image yourself. 
