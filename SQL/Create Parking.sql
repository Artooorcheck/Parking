CREATE TABLE public."parking"
(
	"Parking_id" integer NOT NULL,
	"Park_adress" varchar(512) NOT NULL,
	PRIMARY KEY ("Parking_id")
);

CREATE TABLE public."park_place"
(
	"Place_id" integer NOT NULL,
	"Park_id" integer NOT NULL,
	PRIMARY KEY ("Place_id"),
	CONSTRAINT "FK_Park_id" FOREIGN KEY ("Park_id")
		REFERENCES public."parking" ("Parking_id") MATCH SIMPLE
		ON UPDATE CASCADE 
		ON DELETE RESTRICT
		NOT VALID
);

CREATE TABLE public."user"
(
	"User_id" integer NOT NULL,
	"Name" varchar(128) NOT NULL,
	"Card_number" varchar(16) NOT NULL,
	"Phone" varchar(11),
	"Adress" varchar(512),
	PRIMARY KEY ("User_id"),
	CONSTRAINT "Validate_card" 
		CHECK("Card_number" ~ '^[0-9]*$' AND char_length("Card_number") = 16),
	CONSTRAINT "Validate_phone"
		CHECK("Card_number" ~ '^[0-9]*$')
);

CREATE TABLE public."user_place"
(
	"User_id" integer NOT NULL,
	"Place_id" integer NOT NULL,
	CONSTRAINT "FK_User_id" FOREIGN KEY ("User_id")
		REFERENCES public."user" ("User_id") MATCH SIMPLE
		ON UPDATE CASCADE 
		ON DELETE CASCADE
		NOT VALID,
	CONSTRAINT "FK_Place_id" FOREIGN KEY ("Place_id")
		REFERENCES public."park_place" ("Place_id") MATCH SIMPLE
		ON UPDATE CASCADE 
		ON DELETE CASCADE
		NOT VALID
)