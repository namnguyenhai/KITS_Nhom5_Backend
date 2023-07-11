# KITS_Nhom5_Backend

## Rule
1. Check current branch before add, commitl, push
2. Checkout remember what branch you want to checkout
3. Always pull in branch main

Example add product with exist category:
Path postman: http://localhost:8080/product_images/addProductImage_NewProduct
```
{
    "urlImage" : "https://hoa.png",
    "product": {
        "name": "nike air",
        "brand": "nike",
        "description": "giay choi bong ro",
        "category": {
            "categoryId": 1
            }
    },
    "color":{
        "name": "bubble"   
        },
    "size":{
        "name":"S"
        }
}

```