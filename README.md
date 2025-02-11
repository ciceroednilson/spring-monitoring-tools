# The lab is for learning some tools for monitoring.

<p align="center">
  <img src="/images/logo.drawio.png" alt="Tech">
</p>

## ⚙️ Introduction.

This lab has the objective of testing different tools for monitoring. 

## 🛠 Stack.

<ol>
  <li>Java</li>
  <li>Spring</li>
  <li>Spring boot</li>
  <li>Docker</li>
  <li>Docker Compose</li>
  <li>OpenTelemetry</li>
  <li>Grafana</li>
  <li>Prometheus</li>
  <li>Jaeger</li>
  <li>PostgreSQL</li>
  <li>Dbeaver</li>
  <li>Postman</li>
  <li>Intellij</li>
  <li>VsCode</li>
</ol>

## ⚙️ General Architecture.

![images/general.png](images/general.png)


## ⚙️ Create a Docker image for ms-spring-product-api.

source: [ms-spring-product-api](ms-spring-product-api)

This service is responsible for the CRUD operations of products.

Below is the command to create a Docker image.

~~~~shell
sudo docker build -f Dockerfile -t img-ms-spring-product-api .
~~~~

![images/docker_image_server.png](images/docker_image_server.png)


## ⚙️ Create a Docker image for ms-spring-product-client-api.

source: [ms-spring-product-client-api](ms-spring-product-client-api)

This service is responsible for the call operations of products.

Below is the command to create a Docker image.

~~~~shell
sudo docker build -f Dockerfile -t img-ms-spring-product-client-api .
~~~~

![images/create_docker_image_client.png](images/create_docker_image_client.png)


## ⚙️ Setting up Prometheus through the prometheus.yml file.

source: [prometheus.yml](docker-compose/prometheus.yml)
 
In this file, we configure the endpoints to capture metrics.

~~~~yml
global:
  scrape_interval: 15s  # The default scrape interval (how often Prometheus scrapes data)

# The scrape configuration specifies the target services that Prometheus will scrape.
scrape_configs:
  - job_name: 'prometheus'  # A job name for Prometheus itself
    static_configs:
      - targets: ['10.5.0.4:9090']  # Scrape Prometheus metrics itself (Prometheus UI)

  - job_name: 'grafana'  # Example of scraping Grafana
    static_configs:
      - targets: ['10.5.0.3:3000']  # Replace with the address of your Grafana instance

  - job_name: 'ms-spring-product-api'  # application
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['10.5.0.6:8001']  # Replace with your application's metrics endpoint
  
  - job_name: 'ms-spring-product-client-api'  # application
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['10.5.0.7:7001']  # Replace with your application's metrics endpoint
~~~~