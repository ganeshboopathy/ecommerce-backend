$services = @(
    @{ Path = "Eureka\Eureka"; Name = "Eureka Server" },
    @{ Path = "api-gateway\api-gateway"; Name = "API Gateway" },
    @{ Path = "Hero_Service\Hero_Service"; Name = "Hero Service" },
    @{ Path = "product-service-api\product-service-api"; Name = "Product Service" },
    @{ Path = "sales-analytics-service\sales-analytics-service"; Name = "Sales Analytics" },
    @{ Path = "order-service-api\order-service-api"; Name = "Order Service" }
)

foreach ($service in $services) {
    Write-Host "Starting $($service.Name)..."
    Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$($service.Path)'; mvn spring-boot:run"
    Start-Sleep -Seconds 5
}
