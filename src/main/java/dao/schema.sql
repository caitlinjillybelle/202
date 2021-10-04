-- Now we need to enter the SQL for 
-- creating the product table in the database.
CREATE TABLE IF NOT EXISTS PRODUCT (
    Product_ID varchar(50),
    Name varchar(50) not null,
    Description varchar(50) not null,
    Category varchar(50) unique not null,
    List_Price decimal(5,2) unique not null,
    Quantity_In_Stock integer unique not null,
    constraint Product_PK primary key (Product_ID)
);
CREATE TABLE IF NOT EXISTS CUSTOMER (
    Customer_ID Integer auto_increment (1000),
    Username varchar(50) unique not null,
    First_Name varchar(50) not null,
    Surname varchar(50) not null,
    Password varchar(50) not null,
    Email_Address varchar(50) not null,
    Shipping_Address varchar(300) not null,
    constraint Customer_PK primary key (Customer_ID)

);
CREATE TABLE IF NOT EXISTS SALE (
    Sale_ID Integer auto_increment (1000),
    Customer_ID Integer,
    Date timestamp not null,
    Status varchar(50),
    constraint Sale_PK primary key (Sale_ID),
    constraint Customer_FK foreign key (Customer_ID) references CUSTOMER
);
CREATE TABLE IF NOT EXISTS SALE_ITEM (
    Product_ID varchar(50),
    Sale_ID varchar(50),
    Product varchar(50) not null,
    Quantity_Purchased integer not null,
    Sale_Price decimal(5,2) not null,
    Total decimal(5,2),
    constraint Sale_Item_PK primary key (Sale_ID, Product_ID),
    constraint Sale_Item_Sale_FK foreign key (Sale_ID) references SALE,
    constraint Sale_Item_Product_FK foreign key (Product_ID) references PRODUCT
);