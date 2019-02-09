Retail service readme file

Installation and set up:

    This service talks to mongodb to read/write data

    For installing mongodb, please follow the instructions here https://docs.mongodb.com/manual/installation/
    For mac: Here are the instructions for installing the mongodb in mac
    https://www.youtube.com/watch?v=DX15WbKidXY


Running Application: 
  
   Once the installation is complete, we need to update the test data in the local database to test the get and update price pieces

   From the root folder, open two terminals and run these commands 
   `mongod` and `mongo`

   Running `mongo` would spin up the mongo server and running `mongod` would allow you see all the databases and the can update the data in any db


   Now, we have the db instance up and running and we have to insert some test price data which we are going to use in testing our service

   And the database we are using is `local`

   run this query from cmd `db.prices.insertMany([{ "_id" : 1, "productId" : 13860428, "value" : "13.49", "currencyCode" : "USD" }, { "_id" : 2, "productId" : 53331575, "value" : "12.11", "currencyCode" : "USD" }])`

   This will insert prices for products `13860428` and `53331575`


   Now we have the ready and run the application as a sring boot application

   Once the app is up and running now you can test the following end points

   GET call: 
     End point: `localhost:8080/products/53331575`

   PUT call:
     End point: `localhost:8080/products/53331575` 
     Request Body: Make get call with this product, change the price, and then pass that as request body for put call