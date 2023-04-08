ALTER TABLE "user_place"
	ADD	CONSTRAINT "Car_id_validate" 
		CHECK("Car_id" ~ ('([А|В|Е|К|М|Н|О|Р|С|Т|У|Х]{1})([0-9]{3})([А|В|Е|К|М|Н|О|Р|С|Т|У|Х]{2})([0-9]*)$') AND char_length("Car_id") >= 7);