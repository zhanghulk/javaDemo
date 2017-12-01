package com.hulk.xpath;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringBufferInputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class XPathTools {
	
	public Document parseFile(String filename) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.parse(filename);
		return doc;
	}
	
	/*public Document parseXml(String xmlText) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		BufferedReader buff = new BufferedReader(new StringReader(xmlText));
		InputStreamReader r = new InputStreamReader(in);
		Document doc = builder.parse(buff);
		return doc;
	}*/
	
	public Document parseInput(InputStream input) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.parse(input);
		return doc;
	}
}
