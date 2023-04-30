ALTER TABLE "park_place"
	ADD "User_id" integer,
	ADD "Car_id" varchar(9),	
    ADD	CONSTRAINT "FK_User_id" FOREIGN KEY ("User_id")
		REFERENCES public."user" ("User_id") MATCH SIMPLE
		ON UPDATE CASCADE
		ON DELETE SET NULL
		NOT VALID;