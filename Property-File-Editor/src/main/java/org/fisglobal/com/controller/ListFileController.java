package org.fisglobal.com.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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
@CrossOrigin(origins = "http://localhost:4200")
public class ListFileController {

	TrasversingFileNames gfg=new TrasversingFileNames();
    private final String maindirpath = "C:\\Projects\\GC_OAC_Branding_CI\\GC_OAC_Branding_CI\\branding";
	
	@GetMapping(value="listAllFolderFiles/{version}")
	public List<String> loadAllFolderContent(@PathVariable("version") String version) throws IOException  {
		  // Provide full path for directory(change accordingly)   
       // String maindirpath = "C:\\Users\\Gaurav Miglani\\Desktop\\Test"; 
        //String maindirpath = "C:\\Projects\\GC_OAC_Branding_CI\\GC_OAC_Branding_CI\\branding"; 
		String maindirpath1 = maindirpath+"\\"+version;
		System.out.println("listAllFolderFiles"+maindirpath1);
        // File object 
        File maindir = new File(maindirpath1); 
        File arr[]=null ;
        if(maindir.exists() && maindir.isDirectory()) 
        { 
            // array for files and sub-directories  
            // of directory pointed by maindir 
            arr= maindir.listFiles(); 
              
            // Calling recursive method 
            gfg.RecursivePrint(arr,0,0);  
       }  
        List<String> arrt=removeFolderName(arr, maindirpath);
        return arrt;
    }
	
	
	@GetMapping(value="listAllFiles/{version}/{folder}")
	public List<String>  loadAllProperties(@PathVariable("version") String version,@PathVariable("folder") String folder) throws IOException  {
		  // Provide full path for directory(change accordingly)   
       // String maindirpath = "C:\\Users\\Gaurav Miglani\\Desktop\\Test"; 

		String mainDirPath=maindirpath+"\\"+version+"\\"+folder+"\\"+"properties";
		 System.out.println("listAllFiles"+mainDirPath);
        // File object 
        File maindir = new File(mainDirPath); 
        File arr[]=null ;
        if(maindir.exists() && maindir.isDirectory()) 
        { 
            // array for files and sub-directories  
            // of directory pointed by maindir 
            arr= maindir.listFiles(); 
              
            // Calling recursive method 
            gfg.RecursivePrint(arr,0,0);  
       } else {
    	   System.out.println("Directory does not exist");
       }
        List<String> arrt=removeFolderName(arr, mainDirPath);
        return arrt;
    }
	public List<String> removeFolderName(File[] arr, String mainDirPath) {
		List<String> arrt=new ArrayList<String>();
		mainDirPath=mainDirPath +File.separatorChar;
		for (int i=0; i<arr.length; i++) {
			String result=arr[i].getPath();
			result = result.replace(mainDirPath ,"");
			arrt.add(result);
			//System.out.println(result);

		}
		
		return arrt;
	}
	
	@GetMapping(value="listAllDefaultFiles")
	public File[] listAllDefaultFiles() throws IOException  {
		  // Provide full path for directory(change accordingly)   
		String mainDirPath=maindirpath+"\\default";
        
        // File object 
        File maindir = new File(mainDirPath); 
        File arr[]=null ;
        if(maindir.exists() && maindir.isDirectory()) 
        { 
            // array for files and sub-directories  
            // of directory pointed by maindir 
            arr= maindir.listFiles(); 

            // Calling recursive method 
            gfg.RecursivePrint(arr,0,0);  
       }  
        return arr;
    }
	
	
	@GetMapping(value="listAllVersion2Files")
	public File[] listAllVersion2Files() throws IOException  {
		
		// Provide full path for directory(change accordingly)   
		String mainDirPath=maindirpath+"\\version2";
		        
		// File object 
		File maindir = new File(mainDirPath);  
        File arr[]=null ;
        if(maindir.exists() && maindir.isDirectory()) 
        { 
            // array for files and sub-directories  
            // of directory pointed by maindir 
            arr= maindir.listFiles(); 

            // Calling recursive method 
            gfg.RecursivePrint(arr,0,0);  
       }  
        return arr;
    }
	
	@GetMapping(value="listAllVersion3Files")
	public File[] listAllVersion3Files() throws IOException  {
		
		// Provide full path for directory(change accordingly)   
		String mainDirPath=maindirpath+"\\version3";
		        
		// File object 
		File maindir = new File(mainDirPath);  
        File arr[]=null ;
        if(maindir.exists() && maindir.isDirectory()) 
        { 
            // array for files and sub-directories  
            // of directory pointed by maindir 
            arr= maindir.listFiles(); 

            // Calling recursive method 
            gfg.RecursivePrint(arr,0,0);  
       }  
        return arr;
    }
}
