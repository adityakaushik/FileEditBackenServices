package org.fisglobal.com.service;

import java.io.File; 
  
public class TrasversingFileNames  
{ 
     public void RecursivePrint(File[] arr,int index,int level)  
     { 
         // terminate condition 
         if(index == arr.length) 
             return; 
           
         // tabs for internal levels 
         for (int i = 0; i < level; i++) 
            // System.out.print("\t"); 
           
         // for files 
         if(arr[index].isFile()) {
            // System.out.println(arr[index].getName()); 
         }
         // for sub-directories 
         else if(arr[index].isDirectory()) 
         { 
            // System.out.println("[" + arr[index].getName() + "]"); 
               
             // recursion for sub-directories 
             RecursivePrint(arr[index].listFiles(), 0, level + 1); 
         } 
            
         // recursion for main directory 
         RecursivePrint(arr,++index, level); 
    } 
}