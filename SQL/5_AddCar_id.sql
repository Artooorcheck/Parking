ALTER TABLE "park_place"
	ADD "User_id" integer,
	ADD "Car_id" varchar(9),
	ADD	CONSTRAINT "Car_id_validate" 
		CHECK("Car_id" ~ ('([А|В|Е|К|М|Н|О|Р|С|Т|У|Х]{1})([0-9]{3})([А|В|Е|К|М|Н|О|Р|С|Т|У|Х]{2})([0-9]*)$') AND char_length("Car_id") >= 7),	
    ADD	CONSTRAINT "FK_User_id" FOREIGN KEY ("User_id")
		REFERENCES public."user" ("User_id") MATCH SIMPLE
		ON UPDATE CASCADE
		ON DELETE SET NULL
		NOT VALID;