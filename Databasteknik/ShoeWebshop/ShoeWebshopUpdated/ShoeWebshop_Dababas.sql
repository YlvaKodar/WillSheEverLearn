-- Databas till Shoe Webshop Databasteknik inlmn. uppg. 2 --
-- (utbyggd från inlmn. uppg. 1, grupparb. m Heléne Rydberg) -- 
-- Ylva Fröjmark, feb. -25 --

-- BYGGER DATABASEN 
drop database if exists Shoe_Webshop;  
create database Shoe_Webshop;  
use Shoe_Webshop;  
  
-- NORMALISERING: Vi har postkod och stad i samma tabell  för rimligthetens 
-- och överskådlighetens skull 
create table Customer   
(id int not null auto_increment primary key,  
name varchar(100) not null,  
streetAddress varchar(30) not null,  
areaCode char(6) not null,  
city varchar (30) not null,
email varchar (100) not null,
password varchar (30) not null 
);  

create table WebOrder  
(id int not null auto_increment primary key,  
customerId int not null,
foreign key (customerId) references Customer(id),  
orderDate timestamp default CURRENT_TIMESTAMP,
lastUpdate timestamp default null on update CURRENT_TIMESTAMP,
totalPrice double,
order_status enum ('PAYED', 'ACTIVE') default 'ACTIVE' not null
);
  
create table Colour  
(id int not null auto_increment primary key,  
name varchar(30) not null, 
unique (name) 
);  
  
create table Category  
(id int not null auto_increment primary key,  
name varchar(30) not null, 
unique (name) 
);  
   
create table Brand  
(id int not null auto_increment primary key,  
name varchar(30) not null, 
unique (name) 
);   
  
-- NORMALISERING: Vi har name och theme i samma tabell trots att det kan bryta mot 3NF. 
-- Smidigt och intuitivt,  och det är möjligt att flera märken har teman som “Summer”,  “Floral”etc. 
create table Shoe  
(id int not null auto_increment primary key,  
name varchar(30) not null,  
theme varchar(30),  
shoeSize int not null,  
price double not null  
);  
  
-- NORMALISERING: syntetisk nyckel så att man kan beställa dubbletter utan normaliseringsfel. 
create table Order_Table  
(id int not null auto_increment primary key,    
webOrderId int not null,  
foreign key (webOrderId) references WebOrder(id) on delete cascade,  
shoeId int not null,  
foreign key (shoeId) references Shoe(id)  
);  
  
create table Category_Table  
(categoryId int not null,  
foreign key (categoryId) references Category(id),  
shoeId int not null,  
foreign key (shoeId) references Shoe(id)  
);  
  
create table Colour_Table  
(colourId int not null,  
foreign key (colourId) references Colour(id),  
shoeId int not null,  
foreign key (shoeId) references Shoe(id)  
);  
  
create table Brand_Table  
(brandId int not null,  
foreign key (brandId) references Brand(id),  
shoeId int not null,  
foreign key (shoeId) references Shoe(id)  
);  
  
create table Stock  
(id int not null auto_increment primary key,  
shoeId int not null,  
foreign key (shoeId) references Shoe(Id),  
quantity int not null  
);  

create table Out_Of_Stock
(id int not null auto_increment primary key,  
shoeId int not null, 
runOutDate timestamp default CURRENT_TIMESTAMP
);
 
-- SKAPAR VYER --
-- sko m. alla attribut
create or replace view ShoeInStockAllIncluded as
select Shoe.id as Shoe_id, Shoe.name as name, Shoe.theme as theme, Shoe.shoesize as size,
Brand.id as brand_id, Brand.name as brand,
Category.id as category_id, Category.name as category,
Colour.id as colour_id, Colour.name as colour, Shoe.price as price from Stock
left join Shoe on Shoe.id = Stock.shoeId
left join Brand_Table on Shoe.id = Brand_Table.shoeId
left join Brand on Brand.id = Brand_Table.brandId
left join Category_Table on Shoe.id = Category_Table.shoeId
left join Category on Category.id = Category_Table.CategoryId
left join Colour_Table on Shoe.id = Colour_Table.shoeId
left join Colour on Colour.id = Colour_Table.colourId; 

 
-- LÄGGER IN VÄRDEN I TABELLERNA --
insert into Customer (name, streetAddress, areaCode, city, email, password) values  
('Ada Lovelace', 'Pioneer Road', '12 345', 'History', 'Ada@Lovelace', 'Ada'),  
('Gullan von', 'Arkadien', '75 333', 'Uppsala', 'Gullan@Arkadien', 'Gullan'),  
('Arlo Almighty', '1337 Street', '10 101', 'Cyber Space', 'Arlo@Almighty', 'Arlo'),  
('Hedy Lamarr', 'Walk of Fame', '12 346', 'History', 'Hedy@Lamarr', 'Hedy'),  
('P. Kalkyl', 'Hergégatan', '62 345', 'Bryssel', 'P@Kalkyl', 'Professor'),  
('Bianca Castafiore', 'Callas Boulevard', '62 345', 'Bryssel', 'Bianca@Castafiore', 'Bianca'); 
  
insert into webOrder (customerId, orderDate, order_status)values  
(3, '2024-02-02', 'PAYED'),  
(6, '2024-03-12', 'PAYED'),  
(6, '2024-03-14', 'PAYED'),  
(1, '2024-05-03', 'PAYED'),  
(5, '2024-06-03', 'PAYED'),  
(2, '2024-07-10', 'ACTIVE'),  
(4, '2024-08-21', 'ACTIVE'), 
(6, '2024-10-14', 'ACTIVE');  
  
insert into Colour (name) values  
('white'),  
('red'),  
('blue'),  
('brown'),  
('disco beige'),  
('glossy black'),  
('matte black');  
  
insert into Category (name) values  
('hiking'),  
('formal'),  
('winter'),  
('summer'),  
('sport'),  
('ballet'),  
('djungle'),  
('sandal'),  
('cat burglar');  
  
insert into Brand (name) values  
('Fruit Loops'),  
('AI Technocracy'),  
('Cinderella'),  
('Perdy Paw'),  
('Ecco'),  
('KickAss');    

insert into Shoe (name, theme, shoeSize, price) values 
('Gunhild', 'Velvet', 37, 500),   
('Gunhild', 'Velvet', 38, 500),  
 
('Gunhild', 'Froté', 37, 350),   
('Gunhild', 'Froté', 38, 350),   
  
('Sparkles', 'Void', 39, 400),  
('Sparkles', 'Void',  40, 400),  
  
('Sparkles', 'Sprinkles', 38, 400),  
('Sparkles', 'Sprinkles', 39, 400),  
  
('Floppy flip', 'Beach', 38, 300),  
('Floppy flip', 'Beach', 39, 300),  
   
('Luftig', 'Breezy', 37, 350),  
('Luftig', 'Breezy', 38, 350), 
 
('Break a Leg', 'Hippo' , 40, 350), 
('Break a Leg', 'Hippo' , 41, 350);  

insert into Order_Table(webOrderId, shoeId) values 
(1, 1),  
(1, 6),  
(2, 2),  
(2, 13), 
(2, 4),  
(3, 7),  
(4, 10),  
(4, 7),  
(5, 11),  
(5, 3),  
(6, 6),  
(6, 13), 
(7, 8),  
(7, 8),  
(7, 8), 
(8, 7), 
(8, 13); 
  
insert into Category_Table (categoryId, shoeId) values 
(4, 1),  
(8, 1),  
(4, 2),  
(8, 2),  
(4, 3),  
(8, 3), 
(4, 4),  
(8, 4),  
(2, 5),  
(2, 6),  
(2, 7),  
(2, 8),  
(8, 9),  
(9, 9),  
(8, 10),  
(9, 10),  
(3, 11),  
(8, 11),  
(3, 12),  
(8, 12), 
(5, 13),  
(6, 13),  
(5, 14),  
(6, 14);  

insert into Colour_Table (colourId, shoeId) values 
(6, 1),  
(6, 2),  
(5, 3),  
(5, 4),  
(4, 3),  
(4, 4),  
(3, 5),  
(3, 6),  
(2, 7),  
(2, 8),  
(1, 7),  
(1, 8),  
(3, 9),  
(3, 10),  
(1, 11),  
(1, 12),  
(7, 11),  
(7, 12), 
(6, 13),  
(6, 14); 
  
insert into Brand_Table (brandId, shoeId) values 
(5, 1),  
(5, 2),  
(5, 3),  
(5, 4),  
(2, 5),  
(2, 6),  
(2, 7),  
(2, 8),  
(4, 9),  
(4, 10),  
(3, 11),  
(3, 12),  
(6, 13),  
(6, 14);   

insert into Stock (shoeId, quantity) values  
(1, 1),  
(2, 3),  
(3, 10),  
(4, 8),  
(5, 15),  
(6, 7),  
(7, 12),  
(8, 9),  
(9, 15),  
(10, 20),  
(11, 4),  
(12, 9), 
(13, 40),  
(14, 3); 

-- PROCEDURES -- 
-- checkInlog
drop procedure if exists checkInlog;
delimiter &&
create procedure checkInlog(IN userName varchar(30), IN pass varchar(30), OUT shopper int, OUT shopperName varchar(100), OUT activeOrder int)
begin
	select id from Customer where email = userName AND password = pass into shopper;

	if (shopper is not null) then
		select name from Customer where id = shopper into shopperName;
		select id from WebOrder where customerId = shopper AND order_Status = 'ACTIVE' into activeOrder;
	end If;

end &&
delimiter ;

-- addToCart
drop procedure if exists addToCart;
delimiter &&
create procedure addToCart (IN shopper int, INOUT orderNr int, IN product int, OUT success boolean)
proc_cart:begin
	declare exit handler for 1048
	begin
		rollback;
			resignal set message_text = 'Customer not found in database';
	end;
	
	start transaction;

	set @inStock = (select quantity from Stock where shoeId = product) - 1;
	
    -- om skoId inte återfinns i Stock, avbryt.
    if (@inStock is null) then
		leave proc_cart;
	end if;
    
    update Stock set quantity = @inStock where shoeId = product;
	
    -- om skon tar slut iom köp, ta bort ur lager (triggar After_Stock_Delete)
	if (@inStock = 0) then 
		 delete from Stock where shoeId = product;
	end if;

	-- ser till att det finns ett giltigt orderId
	if (orderNr is null)then
		insert into WebOrder(customerId) values (shopper);
		set orderNr = last_insert_id();
	end If;
    
    -- loggar beställning
	insert into Order_Table(webOrderId, shoeId) values (orderNr, product);
    set success = 1;
    commit;

 end &&   
delimiter ;

-- getShoesfromOrderTable
drop procedure if exists getShoesFromOrderTable;
delimiter &&
create procedure getShoesFromOrderTable (IN orderNr int)
begin
	select  shoe.id, shoe.name, shoe.theme, shoe.shoeSize, shoe.price, Brand.name from Order_Table
	left join Shoe on Order_Table.shoeId = Shoe.id
	left join Brand_Table on Shoe.Id = Brand_Table.shoeId
	left join Brand on Brand_Table.brandId = Brand.id
	where webOrderId = orderNr;
end &&
delimiter ;

-- filterShoeModel
drop procedure if exists filterShoeModel;
delimiter &&
create procedure filterShoeModel (IN colourFilter varchar(30), IN categoryFilter varchar(30), IN brandFilter varchar(30))
begin
	select DISTINCT name, theme, min(price) from ShoeInStockAllIncluded where colour like colourFilter AND category like categoryFilter and brand like brandFilter
	group by name, theme order by name;
end &&
delimiter ;

-- setOrderAsPayed --
drop procedure if exists setWebOrderAsPayed;
delimiter &&
create procedure setWebOrderAsPayed(in orderNr int, in shopper int, out success boolean)
begin
	set success = 0; 
	if exists (select 1 from WebOrder where customerId = shopper and id = orderNr and order_status = 'ACTIVE' ) then
		update WebOrder set order_status = 'PAYED' where id = orderNr;
        select * from WebOrder;
        set success = 1;
    end if;
end &&
delimiter ;

-- TRIGGERS --
-- After_Stock_Delete -- trigger som lägger till i Out_Of_Stock
delimiter //
create trigger After_Stock_Delete
	after delete on Stock
	for each row
begin
	insert into Out_Of_Stock (shoeId) values 
	(old.shoeId);
end//
delimiter ;