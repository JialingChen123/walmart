# Dependencies used

Following dependencies used:
1) Springboot Web: to have create the rest api with embedded tomcat server
2) Springboot JPA: To use the ORM features and JPA based repositories
3) H2: embedded runtime, in-memory database used for testing 
4) Springboot Validation: to check the user input data by standard validation annotations


## Installation

1) clone the repository from ```walmart ``` OR download the zip file

2) cd walmart

3) If you have gradle installed: Run ```gradle build -PexcludeTests=**/**AcceptanceTest**``` to install all the dependencies and run all the unit test cases
   If you don't have gradle installed: Run ```gradlew build -PexcludeTests=**/**AcceptanceTest**```

## Running

Inside the folder run the below command

```
gradle bootRun
```
OR
```
gradlew bootRun
```
It will start the springboot container with embedded tomcat on port 8080

## Testing
The data.sql file in the resources will insert the dummy data into the H2 database.
The database can be viewed ```http://localhost:8080/h2-console``` username=sa and the password=password and database-url=jdbc:h2:mem:ordermanagement. 
The 4 tables are
1) customer: have customer data 
2) orders: have the orders 
3) items: items information, id and the item name
4) order_lines: information about the orders with items and quantity ordered

The API has 5 endpoints:
1) create customer: POST /customer, create the customer by accepting the customer object in the request
2) update customer name: PUT /customer/{id}, updates the customer name based on the user id, it takes the customer object as a request with the updated name
3) create an order: POST/order, create a new order and accepts the customer id and the list of order_lines object as a request
4) modify an order(change or delete of an item): PUT/order/{order_id}, updates or deletes the item
 from the order. If the number of item's quantity is less than zero it removes it from the order_lines tables and if the order doesn't have any item in it after update it removes from the order table.
5) recommendation: GET /customer/recommendation/userid: recommend top 3 ordered items, it considers the customer's past order history and doesn't recommend those things again.

## Postman scripts:
I have also included the postman scripts which will make it easy to test all the endpoints with sample values. To test using postman you have to follow
1) run the application using ```gradle bootRun``` OR ```gradlew bootRun``` (make sure you have build the application as shown above)
2) launch the postman app (you can download from here: https://www.postman.com/downloads/)
3) once launch go to file menu and click import and import the attached postman script files. All the APIs will be imported and you can test all the endpoints

## The recommendation approach
The recommendation approach can be tested in two ways 
1) You can run the acceptance test attached in the test suite ```RecommendationAcceptanceTest.java``` using ```gradle test --tests RecommendationAcceptanceTest```. It basically fetches the recommendation using the recommendation endpoint and check the itemid's recommended. And choose any of the item id and create an order with that item id. After created when he fetches again the recommendation, he will not see the item_id which he has created the order with.
2) Other way is using the postman script attached. Please import it as explained above and then you can follow the same as above. Fetch the recommendation using the recommendation endpoint and not the item ids, now use any of the itemid to create a new order using the create order endpoint. Again try to hit the recommendation endpoint you will not see the item id for which you have created the order.

## Pros and Cons
Pros: The customer will not see the recommendation of the items he has already ordered
Cons: Once the order is placed by the customer even its too old he will not see in the recommendation. It may be possible the customer may be looking for the same item and it will be good as its in top most recommendation orders

Solution for this could be to have some timeframe like may be 3months after which we can show the same recommendation if its in top trend/ordered


## License
[MIT](https://choosealicense.com/licenses/mit/)