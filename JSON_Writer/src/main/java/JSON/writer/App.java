package JSON.writer;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class App {

	public static void main(String[] args) {

		excelUtilities e = new excelUtilities();
		e.Upload("C:\\Users\\jderochie\\workspace\\JSONWriter\\sessions.xlsx");
		try {
			e.read();
		} catch (InvalidFormatException | IOException e1) {
			e1.printStackTrace();
		}
	}
}
