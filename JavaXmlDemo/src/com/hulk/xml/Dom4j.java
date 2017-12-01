package com.hulk.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4j {
	
	public static void main(String[] args) {
		Dom4j dom4j = new Dom4j();
		Document document;
		try {
			document = dom4j.parse("project.xml");
			//dom4j.print(document, "buildCommand");
			//dom4j.treeWalk(document);
			//List<Node> list = document.selectNodes("/projectDescription/comment/");
			Node node = document.selectSingleNode("/projectDescription/comment");
			System.out.println("node: " + node.getName() + ", " + node.getStringValue());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public void print(Document document, String elementName) {
		Element root = document.getRootElement();
		for (Element element : root.elements(elementName)) {
			print(element);	
		}
	}
	
	public void print(Element element) {
		System.out.println(getElementText(element));
	}
	
	public Document parse(String filePath) throws DocumentException {
		File file = new File(filePath);
		if (!file.exists()) {
			return null;
		}
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		return document;
	}

	/**
	 * parse Document from net url
	 * @param url
	 * @return
	 * @throws DocumentException
	 */
	public Document parse(URL url) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);
		return document;
	}

	/**
	 * parse xml text as xml Document
	 * @param text  eg. String text = "<person> <name>James</name> </person>";
	 */
	public Document parseText(String text) {
		Document document = null;
		try {
			document = DocumentHelper.parseText(text);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}
	
	private String getElementText(Element element) {
		StringBuffer buff = new StringBuffer();
		buff.append("Name: " + element.getName());
		buff.append(" Value: " + element.getStringValue());
		//buff.append("\n asXML: " + element.asXML());//就是name
		//buff.append("\nText: " + element.getTextTrim());值是空的
		//buff.append(", Path: " + element.getPath());
		return buff.toString();
	}
	
	public void findLinks(Document document, String nodeName) throws DocumentException {

	    List<Node> list = document.selectNodes(nodeName);

	    for (Iterator<Node> iter = list.iterator(); iter.hasNext();) {
	    	Node node = iter.next();
	    	int type = node.getNodeType();
	        String value = node.getStringValue();
	        System.out.println(node.getPath() + " : NodeType= " + type + ", value: " + value);
	    }
	}
	
	public void treeWalk(Document document) {
	    treeWalk(document.getRootElement());
	}

	public void treeWalk(Element element) {
		System.out.println("\n---------------------------------------------------");
		int nodeCount = element.nodeCount();
		System.out.println("treeWalk element Name= " + element.getName() + ", nodeCount= " + nodeCount);
	    for (int i = 0; i < nodeCount; i++) {
	        Node node = element.node(i);
	        int type = node.getNodeType();
	        String name = node.getName();
	        String value = node.getStringValue();
	        if (type != Node.ELEMENT_NODE) {
	        	System.out.println("================" + node.getPath() + " Node type= " + type + ", name= " + name + ", value= " + value);
				continue;
			}
	        Element elment = (Element) node;
        	if (!elment.elements().isEmpty()) {
        		treeWalk(elment);
			} else {
				System.out.println("ELEMENT_NODE: " + node.getPath() + " : Name= " + name + ", value: " + value);
			}
	    }
	}
	
	public List<Element> getElementList(Document document ) {
		List<Element> list = new ArrayList<Element>();
		Element root = document.getRootElement();
		// iterate through child elements of root
	    for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
	        Element element = it.next();
	        // do something
	        System.out.println("Element:      " + getElementText(element));
	        list.add(element);
	    }
	    return list;
	}
	
	public List<Element> getElementList(Document document, String elimentName) {
		List<Element> list = new ArrayList<Element>();
		Element root = document.getRootElement();
		// iterate through child elements of root
	    for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
	        Element element = it.next();
	        // do something
	        System.out.println("element: " + getElementText(element));
	        list.add(element);
	    }
	    return list;
	}
	
	public List<Attribute> getAttributeList(Document document ) {
		Element root = document.getRootElement();
		List<Attribute> list = new ArrayList<Attribute>();
		// iterate through attributes of root
	    for (Iterator<Attribute> it = root.attributeIterator(); it.hasNext();) {
	        Attribute attribute = it.next();
	        // do something
	        System.out.println("attribute: " + attribute);
	        list.add(attribute);
	    }
	    return list;
	}
	
	public void bar(Document document) throws DocumentException {

	    Element root = document.getRootElement();

	    // iterate through child elements of root
	    for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
	        Element element = it.next();
	        // do something
	        System.out.println("element: " + getElementText(element));
	    }

	    // iterate through child elements of root with element name "foo"
	    for (Iterator<Element> it = root.elementIterator("buildSpec"); it.hasNext();) {
	        Element element = it.next();
	        // do something
	        //System.out.println("Special element: " + getElementText(element));
	    }

	    // iterate through attributes of root
	    for (Iterator<Attribute> it = root.attributeIterator(); it.hasNext();) {
	        Attribute attribute = it.next();
	        // do something
	        System.out.println("attribute: " + attribute);
	    }
	 }

	/**
	 * demo of create a xml document
	 * @return
	 */
	public Document createDocument() {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");

        Element author1 = root.addElement("author")
            .addAttribute("name", "James")
            .addAttribute("location", "UK")
            .addText("James Strachan");

        Element author2 = root.addElement("author")
            .addAttribute("name", "Bob")
            .addAttribute("location", "US")
            .addText("Bob McWhirter");

        return document;
    }
	
	public void write(Document document) throws IOException {

        // lets write to a file
		FileWriter fileWriter = new FileWriter("output.xml");
		XMLWriter writer;
        try {
            writer = new XMLWriter(fileWriter);
            writer.write( document );
            writer.close();
        } catch (Exception e) {
			e.printStackTrace();
		}


        // Pretty print the document to System.out
        OutputFormat format = OutputFormat.createPrettyPrint();
        writer = new XMLWriter(System.out, format);
        writer.write( document );

        // Compact format to System.out
        format = OutputFormat.createCompactFormat();
        writer = new XMLWriter(System.out, format);
        writer.write(document);
    }
}
