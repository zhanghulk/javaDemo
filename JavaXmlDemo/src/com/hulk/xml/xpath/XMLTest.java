package com.hulk.xml.xpath;

public class XMLTest {
	public static void main(String[] args) {
		XMLTools xml = new XMLTools();
		// xml.creat("users.xml");
		try {
			 // xml.read("users.xml", "0");
			// xml.addUser("users.xml", "4", "no", "100");
			// xml.readUser("users.xml");
			// xml.readName("users.xml");
			xml.readAll("project.xml");
			// xml.modify("users.xml", "0", "helloworld");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
