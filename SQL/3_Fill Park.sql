INSERT INTO public."parking"("Parking_id", "Park_adress")
VALUES (1, 'ул. Шишкова, 82'),
		(2, 'ул. Владимира Невского, 38п'),
		(3, 'Московский просп., 11'),
		(4, 'ул. Хользунова, 38');
		
INSERT INTO public."park_place"("Place_id", "Park_id")
SELECT gs::integer, (gs::integer-1)/25+1
from generate_series(1, 100) as gs