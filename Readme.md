PRODUCT CATALOG SERVICE
---

This micro-service is useful just to display the catalog for products

Package structures:
---
1. src/main/java:

     com.productcatalogservice.api.controller:
       This package for all api level cobtrollers present
       
     com.productcatalogservice.messaging.controller:
       This package is for all kafka messaging consumer controllers.
       
     com.productcatalogservice.data:
       This package contains all the data model for databases
       
     com.productcatalogservice.model:
       This package contains all the API request model
       
     com.productcatalogservice.repositories:
       This package contains all the database repo to save the data
       
     com.productcatalogservice.service:   
       This package contains all the service level/business logic level interfaces
       
     com.productcatalogservice.service.impl:     
        This package contains all the service level implementation
        
     com.productcatalogservice.utils:
        This package contains all the helper utils, Kindly note that this one has to be moved to common libs to reuse this in multiple misroservices
        
      com.warehouse.inventorymanagement.model:
         This package is kept here since kafka connot deserialize the different package other than serialized one, this also has to move to commonlib or schema registry side
         
         
         
2. Entry point:
   The Entry point for this spring application is ProductCatalogServiceApplication
   
   
API's:
---

1. curl --location --request GET 'http://localhost:9001/productcatalog/api/v1/listproducts' \
--header 'Content-Type: application/json' \
--form 'file=@"/path/to/file"'

Description:

  This api is used to list the available products from the product catalog service
  
Response Body:

```
 {
    "message": "Available products list",
    "data": [
        {
            "id": "Dining Chair",
            "name": "Dining Chair",
            "quantity": 1
        },
        {
            "id": "Dinning Table",
            "name": "Dinning Table",
            "quantity": 2
        }
    ],
    "code": 200
}
```


2. curl --location --request PUT 'http://localhost:9001/productcatalog/api/v1/sellproduct' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name" : "Dining"
}'

Description:

  This api is used to sell the products product catalog service
  
Response Body:

```
{
    "message": "Product sold succesful",
    "data": [
        {
            "sold": true
        }
    ],
    "code": 200
}
```

Features to improve:
---
Need to add proper error handling


Architectural diagram:
--
product-catalog-service/src/main/resources/static/Ikeaassignment-archi.png


Note:
---
This code is not production grade code.
        