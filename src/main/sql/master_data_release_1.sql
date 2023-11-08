INSERT INTO `user` VALUES 
	(1, true, now(), null, now(), null, 'b77d9d79-a16c-4582-9b51-500ba8378205', 'nirmalakumarsahu7@gmail.com','Nirmala','Sahu',NULL, NULL, NULL,'$2a$10$n/ACAbYelLw4Mt3Id2LrZ.QzyaXsb3Ywt0Q9NrnvFajKFAGnZKvQO',NULL,NULL, 'ACTIVE', NULL),
	(2, true, now(), 1, now(), 1, '94d81b2b-20f6-4561-9249-06f03acf7fc4', 'digitalmarketinghub.info@gmail.com','Support','Digital Marketing Hub',NULL,  NULL, NULL, '$2a$10$N1LVoZFgZER54eprPMnN2.ayMiYnHxn.c8x8522t7OYyO9dvJdPqS',NULL,NULL, 'ACTIVE', NULL);

	
INSERT INTO `role` VALUES 
	(1, true, now(), 1, now(), 1, '3f2176fa-1ca9-413d-ad0f-0fc29950a94c', 'GLOBAL_ADMIN', 'GLOBAL ADMIN'),
    (2, true, now(), 1, now(), 1, '5294379b-e1bd-478e-a7e5-5b371e2c6ea9', 'CUSTOMER', 'CUSTOMER'),
    (3, true, now(), 1, now(), 1, 'a4678ae9-0b17-427f-8f83-075442676cc2', 'SELLER', 'SELLER');
    
INSERT INTO `user_role` VALUES (1,1);

INSERT INTO `permission` VALUES 
	(1, true, now(), 1, now(), 1, 'ac0b25a4-c349-43cd-aea4-61dab38290ca', 'GLOBAL_ADMINISTRATION', 'GLOBAL ADMINISTRATION');

INSERT INTO `role_permission` VALUES (1,1);

INSERT INTO `app_param_group` VALUES 
	(1, true, 'These statuses help customers understand whether a product is in stock and ready for purchase, or if there are any limitations or delays in obtaining the product', 'Product Availability', 'PRODUCT_AVAILABILITY');

INSERT INTO `app_param_value` VALUES 
	(1, true, 'This status indicates that the product is available and ready for immediate purchase', 'In Stock', 'IN_STOCK', 1),
    (2, true, 'It means that the item is not currently available for purchase', 'Out of Stock', 'OUT_OF_STOCK', 1),
    (3, true, 'Pre-order status is used for products that are not yet in stock but can be reserved in advance', 'Pre-Order', 'PRE_ORDER', 1),
    (4, true, 'Backorder status indicates that the product is currently out of stock but can still be ordered', 'Backorder', 'BACKORDER', 1),
    (5, true, 'Some products may be available only in physical retail stores and not for online purchase', 'In-Store Only', 'IN_STORE_ONLY', 1),
    (6, true, 'This status is used when there are a limited number of units available for a product', 'Limited Stock', 'LIMITED_STOCK', 1),
    (7, true, 'It means that the manufacturer has stopped producing it, and it is no longer available for purchase', 'Discontinued', 'DISCONTINUED', 1),
    (8, true, 'This status may indicate a temporary issue that prevents the product from being in stock', 'Temporarily Unavailable', 'TEMPORARILY_UNAVAILABLE', 1),
    (9, true, 'This status may inform customers that the product is not readily available and will be produced based on their specific requirements', 'Custom Order', 'CUSTOM_ORDER', 1),
    (10, true, 'This status to indicate that a product will be available for sale soon, creating anticipation among customers', 'On Sale Soon', 'ON_SALE_SOON', 1);