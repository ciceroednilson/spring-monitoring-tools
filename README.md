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

