ALTER TABLE "park_place" 
ADD COLUMN "Place_number" integer NOT NULL DEFAULT 0;
		
		
UPDATE public."park_place" p
SET "Place_number" = (SELECT num FROM
					(SELECT row_number() OVER (ORDER BY "Place_id") as num, "Place_id" FROM "park_place" pp 
					  WHERE p."Park_id" = pp."Park_id") ppp
					 WHERE p."Place_id" = ppp."Place_id")