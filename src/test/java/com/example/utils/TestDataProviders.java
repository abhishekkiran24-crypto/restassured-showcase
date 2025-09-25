package com.example.utils;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
public class TestDataProviders {
	
	@DataProvider(name="usersFromJson")
	public static Object[][] usersFromJson() throws Exception {
		ObjectMapper mapper=new ObjectMapper();
		InputStream is=TestDataProviders.class.getResourceAsStream("/data/users.json");
		List<UserDatapojo> list=mapper.readValue(is,new TypeReference<List<UserDatapojo>>() {});
		Object[][] out=new Object[list.size()][2];
		for(int i=0;i<list.size();i++) {
			out[i][0]=list.get(i).getName();
			out[i][1]=list.get(i).getJob();
			//json node approach
			/*InputStream is = getClass().getResourceAsStream("/data/users.json");
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(is);
			Object[][] out = new Object[root.size()][2];
			for (int i = 0; i < root.size(); i++) {
			  out[i][0] = root.get(i).get("name").asText();
			  out[i][1] = root.get(i).get("job").asText();
			}*/
		}
		return out;
		//for users.csv
		/*@DataProvider(name = "usersFromCsv")
	    public static Object[][] usersFromCsv() throws Exception {
	        CSVReader reader = new CSVReader(new InputStreamReader(
	            TestDataProviders.class.getResourceAsStream("/data/users.csv")));
	        List<String[]> all = reader.readAll();
	        reader.close();

	        // remove header
	        all.remove(0);

	        Object[][] out = new Object[all.size()][2];
	        for (int i = 0; i < all.size(); i++) {
	            out[i][0] = all.get(i)[0];
	            out[i][1] = all.get(i)[1];
	        }
	        return out;*/
	
            //for user.xls
		    /*@DataProvider(name = "usersFromExcel")
		    public static Object[][] usersFromExcel() throws Exception {
		        InputStream is = TestDataProviders.class.getResourceAsStream("/data/users.xlsx");
		        XSSFWorkbook workbook = new XSSFWorkbook(is);
		        Sheet sheet = workbook.getSheetAt(0);

		        List<Object[]> rows = new ArrayList<>();
		        boolean first = true;
		        for (Row row : sheet) {
		            if (first) { first = false; continue; } // skip header
		            String name = row.getCell(0).getStringCellValue();
		            String job = row.getCell(1).getStringCellValue();
		            rows.add(new Object[] { name, job });
		        }
		        workbook.close();

		        Object[][] out = new Object[rows.size()][2];
		        for (int i = 0; i < rows.size(); i++) out[i] = rows.get(i);
		        return out;
		    }
		}*/

	}
}
