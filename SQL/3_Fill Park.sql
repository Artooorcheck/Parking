INSERT INTO public."parking"("Park_address")
VALUES ('ул. Шишкова, 82'),
		('ул. Владимира Невского, 38п'),
		('Московский просп., 11'),
		('ул. Хользунова, 38');
		
INSERT INTO public."park_place"("Park_id")
SELECT (gs::integer-1)/25+1
from generate_series(1, 100) as gs