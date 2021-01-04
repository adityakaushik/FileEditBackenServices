package org.fisglobal.com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.PropertiesConfigurationLayout;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.fisglobal.com.attr.PropertyC;
import org.fisglobal.com.attr.Response;
import org.fisglobal.com.service.TrasversingFileNames;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class PropertiesController {

	TrasversingFileNames gfg = new TrasversingFileNames();
	private final String maindirpath = "C:\\Projects\\GC_OAC_Branding_CI\\GC_OAC_Branding_CI\\branding";

//		@Autowired
//		IUIServices uIServices;
//		
//		@Autowired
//		Response res;
//		
	

	@GetMapping(value = "loadAllProperties/{version}/{bank}/{file}")
	public List<PropertyC> loadAllProperties(@PathVariable("version") String version,@PathVariable("bank") String bank,
			@PathVariable("file") String file
			) throws IOException {
		
		// return uIServices.getALLDignostics() ;
		String maindirpath1=maindirpath+"\\"+version+"\\"+bank+"\\"+"properties"+"\\"+file;
		
		System.out.println(maindirpath1);
		FileReader reader = new FileReader(maindirpath1);

		Properties p = new Properties();
		p.load(reader);

		Set set = p.entrySet();
		List propertyList=new ArrayList<PropertyC>();
		Iterator itr = set.iterator();
		while (itr.hasNext()) {
			PropertyC properties =new PropertyC();
			Map.Entry entry = (Map.Entry) itr.next();
			properties.setPropertyName(entry.getKey().toString());
			properties.setValue(entry.getValue().toString());
			propertyList.add(properties);
			// System.out.println(entry.getKey()+" = "+entry.getValue());
		}
		return propertyList;
	}

	@PostMapping(value = "editProperty")
	public Response editAProperty(@RequestBody PropertyC pc) throws IOException, Exception {
//			System.out.println(pc.getPropertyName());
//			FileReader reader=new FileReader("C:\\Users\\e5581833\\Desktop\\security.properties");  
//		      
//		    Properties p=new Properties();  
//		    p.load(reader);  
//		    p.setProperty(pc.getPropertyName(), pc.getValue());
//
//		    p.store(new FileWriter("C:\\Users\\e5581833\\Desktop\\security.properties"),"New Edits");  
//  
		Response rs = new Response();
		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(
				PropertiesConfiguration.class).configure(
						params.properties().setFileName("C:\\Users\\e5581833\\Desktop\\security.properties"));
		// .setListDelimiterHandler(new DefaultListDelimiterHandler(',')));
		Configuration config = builder.getConfiguration();
		config.setProperty(pc.getPropertyName(), pc.getValue());
		builder.save();
		rs.setMessage("Changes Saved");
		rs.setStatus("Ok");

		return rs;
	}

//		@PostMapping(value="/addDignosticData")
//		 public Response search(@RequestBody Dignostic data){
//			
//	        try {
//	        	uIServices.addDignostic(data);
//	        	res.setStatus("OK");
//	        	res.setMessage("Data added");
//	        } catch(Exception ex) {
//	        	res.setStatus("OK");
//	        	res.setMessage("Error occured during execution"+ex);
//	        }
//	        return res;
//	    }
//		
//		@GetMapping(value="/getbyId")
//		public List<Dignostic> getById() {
//	        return uIServices.getALLDignostics() ;
//	    }
//		

	@GetMapping(value = "searchProperty/{version}/{bank}/{word}")
	public HashMap searchProperty(@PathVariable("version") String version,@PathVariable("bank") String bank
	,@PathVariable("word") String word) throws IOException {
		// return uIServices.getALLDignostics() ;

		// Provide full path for directory(change accordingly)
		String mainDirPath = maindirpath +"\\"+version+"\\"+bank+"\\"+"properties";
		System.out.print("searchProperty:"+mainDirPath+','+word);
		// File object
		File maindir = new File(mainDirPath);
		File arr[] = null;
		if (maindir.exists() && maindir.isDirectory()) {
			// array for files and sub-directories
			// of directory pointed by maindir
			arr = maindir.listFiles();

			// Calling recursive method
			gfg.RecursivePrint(arr, 0, 0);
		}
		// return arr;

		// Set result;
		HashMap mp = new HashMap<String, String>();

		for (int i = 0; i < arr.length; i++) {
			//FileReader reader = new FileReader("C:\\Users\\e5581833\\Desktop\\security.properties");
			//System.out.println(arr[i]);
			FileReader reader = new FileReader(arr[i]);
			
			Properties p = new Properties();
			p.load(reader);

			Set set = p.entrySet();

			Iterator itr = set.iterator();
			while (itr.hasNext()) {
				Map.Entry entry = (Map.Entry) itr.next();
				if (entry.getKey().toString().contains(word) || entry.getValue().toString().contains(word)) {
					//
					mp.put(arr[i], entry.getKey().toString()+" , "+entry.getValue().toString());
				//, entry.getValue().toString()
				}
				// System.out.println(entry.getKey()+" = "+entry.getValue());
			}
		}
		return mp;
	}

}
