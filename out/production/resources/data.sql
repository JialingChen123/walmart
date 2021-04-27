-- inserting into customer table
insert into customers
values(1, 'Jason Brown');
insert into customers
values(2, 'Sarah Williams');
insert into customers
values(3, 'Frank Smith');
insert into customers
values(4, 'Anthony Jones');
insert into customers
values(5, 'Maria Miller');

-- inserting into items table
insert into items
values(
        1,
        'Great Value Sweet Cream Unsalted Butter, 4 count, 16 oz'
    );
insert into items
values(
        2,
        'Dove Style + Care Flexible Hold Hairspray, 5.5 oz'
    );
insert into items
values(
        3,
        'Del Monte Summer Crisp Whole Kernel Golden Sweet Corn, 11 Oz'
    );
insert into items
values(4, 'Manwich Original Sloppy Joe Sauce, 15 oz');
insert into items
values(
        5,
        'Campbell Condensed?Healthy Request Tomato Soup, 10.75 oz. Can'
    );
insert into items
values(
        6,
        'SunChips Harvest Cheddar Flavored Whole Grain Snacks, 7 oz. Bag'
    );
insert into items
values(7, 'Fresh Strawberries, 2 lb');
insert into items
values(
        8,
        'Great Value Milk Chocolate Hot Cocoa Mix, Single Serve Cups, 12 Count'
    );
insert into items
values(
        9,
        'Land O Lakes Light Butter with Canola Oil, 8 oz.'
    );
insert into items
values(
        10,
        'Kraft Spirals Original Macaroni & Cheese Dinner, 5.5 oz Box'
    );
insert into items
values(
        11,
        'Simply Orange Pulp Free Orange Juice, 52 fl oz'
    );
insert into items
values(
        12,
        'Marburger Farm Dairy 2% Reduced Fat Milk, 1 Quart'
    );
insert into items
values(
        13,
        'Marburger Farm Dairy Fat-Free Unflavored Milk, 1 Gallon'
    );
insert into items
values(14, 'Schwebel Baking Schwebels  Bread, 20 oz');
insert into items
values(15, 'Nissin Foods Nissin Top Ramen Bowl Beef');
insert into items
values(16, 'Great Value 1% Chocolate Milk, Half Gallon');


-- inserting into order_lines table
insert into order_lines (order_id, item_id, qty)
values(1,3,3);
insert into order_lines (order_id, item_id, qty)
values(1,5,1);
insert into order_lines (order_id, item_id, qty)
values(1,7,2);
insert into order_lines (order_id, item_id, qty)
values(1,16,1);
insert into order_lines (order_id, item_id, qty)
values(2,2,2);
insert into order_lines (order_id, item_id, qty)
values(2,3,2);
insert into order_lines (order_id, item_id, qty)
values(2,4,2);
insert into order_lines (order_id, item_id, qty)
values(2,6,2);
insert into order_lines (order_id, item_id, qty)
values(2,11,2);
insert into order_lines (order_id, item_id, qty)
values(2,15,2);
insert into order_lines (order_id, item_id, qty)
values(3,1,1);
--insert into order_lines (order_id, item_id, qty)
--values(3,3,1);
insert into order_lines (order_id, item_id, qty)
values(3,5,2);
insert into order_lines (order_id, item_id, qty)
values(3,8,4);
insert into order_lines (order_id, item_id, qty)
values(3,12,2);
insert into order_lines (order_id, item_id, qty)
values(3,13,2);
insert into order_lines (order_id, item_id, qty)
values(3,14,2);
insert into order_lines (order_id, item_id, qty)
values(3,3,3);
--insert into order_lines (order_id, item_id, qty)
--values(3,5,1);
insert into order_lines (order_id, item_id, qty)
values(3,7,2);
--insert into order_lines (order_id, item_id, qty)
--values(3,8,1);
insert into order_lines (order_id, item_id, qty)
values(3,9,1);
insert into order_lines (order_id, item_id, qty)
values(3,10,2);
insert into order_lines (order_id, item_id, qty)
values(4,1,2);
insert into order_lines (order_id, item_id, qty)
values(4,5,1);
insert into order_lines (order_id, item_id, qty)
values(4,7,2);
insert into order_lines (order_id, item_id, qty)
values(4,8,1);
insert into order_lines (order_id, item_id, qty)
values(4,9,1);
insert into order_lines (order_id, item_id, qty)
values(4,11,1);
insert into order_lines (order_id, item_id, qty)
values(5,3,1);
insert into order_lines (order_id, item_id, qty)
values(5,6,2);
insert into order_lines (order_id, item_id, qty)
values(5,12,2);
insert into order_lines (order_id, item_id, qty)
values(5,14,1);
insert into order_lines (order_id, item_id, qty)
values(5,15,1);
insert into order_lines (order_id, item_id, qty)
values(5,16,2);
insert into order_lines (order_id, item_id, qty)
values(6,2,4);
insert into order_lines (order_id, item_id, qty)
values(6,5,1);
insert into order_lines (order_id, item_id, qty)
values(6,7,2);
insert into order_lines (order_id, item_id, qty)
values(6,13,2);
insert into order_lines (order_id, item_id, qty)
values(6,16,1);
insert into order_lines (order_id, item_id, qty)
values(7,1,3);
insert into order_lines (order_id, item_id, qty)
values(7,2,1);
insert into order_lines (order_id, item_id, qty)
values(7,5,2);
insert into order_lines (order_id, item_id, qty)
values(7,7,1);
insert into order_lines (order_id, item_id, qty)
values(7,14,1);
insert into order_lines (order_id, item_id, qty)
values(7,16,2);
insert into order_lines (order_id, item_id, qty)
values(8,1,1);
insert into order_lines (order_id, item_id, qty)
values(8,2,1);
insert into order_lines (order_id, item_id, qty)
values(8,3,2);
insert into order_lines (order_id, item_id, qty)
values(8,4,2);
insert into order_lines (order_id, item_id, qty)
values(8,6,1);
insert into order_lines (order_id, item_id, qty)
values(8,8,2);
insert into order_lines (order_id, item_id, qty)
values(8,9,2);
insert into order_lines (order_id, item_id, qty)
values(8,12,2);
insert into order_lines (order_id, item_id, qty)
values(8,15,2);


-- inserting into order table
insert into orders
values(1,1);
insert into orders
values(2,1);
insert into orders
values(3,2);
insert into orders
values(4,3);
insert into orders
values(5,4);
insert into orders
values(6,5);
insert into orders
values(7,3);
insert into orders
values(8,2);
