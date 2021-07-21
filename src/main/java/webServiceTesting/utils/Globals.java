package webServiceTesting.utils;

import webServiceTesting.models.Root;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Globals {
	public static Root getWebTestingObject()
	{
		try {
            ObjectMapper om = new ObjectMapper();
            Root root = om.readValue(new File(System.getProperty("user.dir") + "/settings.json"), Root.class);
            System.out.println(root);
            return root;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
}
