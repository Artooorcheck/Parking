ALTER TABLE public."user"
ADD COLUMN "Login" varchar(128) NOT NULL UNIQUE,
ADD COLUMN "Password" varchar(128) NOT NULL