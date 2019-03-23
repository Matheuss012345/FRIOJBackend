package com.example.friojspring.Helpers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipHelper {

    /*public static void main(String[] args) throws IOException {
        File inputDirectory = new File("C:\\frioj\\zipDirectory.zip");
        File outputUnZippedContents = new File("C:\\output\\unzipContents");
        outputUnZippedContents.delete();
        outputUnZippedContents.getParentFile().mkdirs();
        unZip(inputDirectory, outputUnZippedContents);
    }*/

    public static void unZip(File zipFile, File outputDirectory) throws IOException {

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile))) {
            int numberOfFiles = 0;
            ZipEntry zipEntry;

            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                numberOfFiles++;
                System.out.printf("%d. Extracting content:%s\n",numberOfFiles, zipEntry.getName());
                File unZippedFile = new File(outputDirectory + File.separator + zipEntry.getName());

                //Create output director
                unZippedFile.getParentFile().mkdirs();
                System.out.printf("Creating new file :%s\n", unZippedFile.getCanonicalPath());

                //Write contents to file
                writeContents(zipInputStream, unZippedFile);
                System.out.printf("Written content to file:%s\n\n",unZippedFile.getCanonicalPath());

                //Close current entry
                zipInputStream.closeEntry();
            }
            System.out.printf("Finished execution, UnZipped file count:%d", numberOfFiles);
        }
    }

    public static void writeContents(ZipInputStream zipInputStream, File outputFile)
                                                                        throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        int len;
        byte[] content = new byte[1024];
        while ((len = zipInputStream.read(content)) > 0) {
            fileOutputStream.write(content, 0, len);
        }
        fileOutputStream.close();
    }
    
    
    
    public static void unzip2(File source, String out) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(source))) {

            ZipEntry entry = zis.getNextEntry();

            while (entry != null) {

                File file = new File(out, entry.getName());

                if (entry.isDirectory()) {
                    file.mkdirs();
                } else {
                    File parent = file.getParentFile();

                    if (!parent.exists()) {
                        parent.mkdirs();
                    }

                    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {

                        byte[] buffer = new byte[Math.toIntExact(entry.getSize())];

                        int location;

                        while ((location = zis.read(buffer)) != -1) {
                            bos.write(buffer, 0, location);
                        }
                    }
                }
                entry = zis.getNextEntry();
            }
        }
    }
    
    
    
    
    
    
    
    private static final int  BUFFER_SIZE = 4096;

    private static void extractFile(ZipInputStream in, File outdir, String name) throws IOException
    {
      byte[] buffer = new byte[BUFFER_SIZE];
      BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(outdir,name)));
      int count = -1;
      while ((count = in.read(buffer)) != -1)
        out.write(buffer, 0, count);
      out.close();
    }

    private static void mkdirs(File outdir,String path)
    {
      File d = new File(outdir, path);
      if( !d.exists() )
        d.mkdirs();
    }

    private static String dirpart(String name)
    {
      int s = name.lastIndexOf( File.separatorChar );
      return s == -1 ? null : name.substring( 0, s );
    }

    public static void extract(File zipfile, File outdir)
    {
      try
      {
        ZipInputStream zin = new ZipInputStream(new FileInputStream(zipfile));
        ZipEntry entry;
        String name, dir;
        while ((entry = zin.getNextEntry()) != null)
        {
          name = entry.getName();
          if( entry.isDirectory() )
          {
            mkdirs(outdir,name);
            continue;
          }
          /* this part is necessary because file entry can come before
           * directory entry where is file located
           * i.e.:
           *   /foo/foo.txt
           *   /foo/
           */
          dir = dirpart(name);
          if( dir != null )
            mkdirs(outdir,dir);

          extractFile(zin, outdir, name);
        }
        zin.close();
      } 
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    
    public static void extractZipContent(ZipFile zipFile) {
    	try {
            // Open the zip file
            Enumeration<?> enu = zipFile.entries();
            while (enu.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) enu.nextElement();

                String name = zipEntry.getName();
                long size = zipEntry.getSize();
                long compressedSize = zipEntry.getCompressedSize();
                System.out.printf("name: %-20s | size: %6d | compressed size: %6d\n", 
                        name, size, compressedSize);

                // Do we need to create a directory ?
                File file = new File(name);
                if (name.endsWith("/")) {
                    file.mkdirs();
                    continue;
                }

                File parent = file.getParentFile();
                if (parent != null) {
                    parent.mkdirs();
                }

                // Extract the file
                InputStream is = zipFile.getInputStream(zipEntry);
                FileOutputStream fos = new FileOutputStream(file);
                byte[] bytes = new byte[1024];
                int length;
                while ((length = is.read(bytes)) >= 0) {
                    fos.write(bytes, 0, length);
                }
                is.close();
                fos.close();

            }
            zipFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static public void extractFolder(String zipFile, String extractTo) throws ZipException, IOException {
        System.out.println(zipFile);
        int BUFFER = 2048;
        File file = new File(zipFile);

        ZipFile zip = new ZipFile(file);
        String newPath = extractTo;
 
        new File(newPath).mkdir();
        Enumeration zipFileEntries = zip.entries();

        // Process each entry
        while (zipFileEntries.hasMoreElements())
        {
            // grab a zip file entry
            ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
            String currentEntry = entry.getName();
            File destFile = new File(newPath, currentEntry);
            //destFile = new File(newPath, destFile.getName());
            File destinationParent = destFile.getParentFile();

            // create the parent directory structure if needed
            destinationParent.mkdirs();

            if (!entry.isDirectory())
            {
                BufferedInputStream is = new BufferedInputStream(zip
                .getInputStream(entry));
                int currentByte;
                // establish buffer for writing file
                byte data[] = new byte[BUFFER];

                // write the current file to disk
                FileOutputStream fos = new FileOutputStream(destFile);
                BufferedOutputStream dest = new BufferedOutputStream(fos,
                BUFFER);

                // read and write until last byte is encountered
                while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
                    dest.write(data, 0, currentByte);
                }
                dest.flush();
                dest.close();
                is.close();
            }

            if (currentEntry.endsWith(".zip"))
            {
                // found a zip file, try to open
                extractFolder(destFile.getAbsolutePath(),destFile.getAbsolutePath().substring(0, zipFile.length() - 4));
            }
        }
    }
    
    
    public static boolean checkFormatOfProblemDirectory(String extractedFolder) {
    	
    	File problemFolder = new File(extractedFolder);
    	int numberOfInputFiles=0;
    	int numberOfOutputFiles=0;
    	int numberOfFiles=0;
    	boolean pdf=false;
    	
	    File [] files = problemFolder.listFiles();
		numberOfFiles=files.length;
    	 
	    for (int i = 0; i < files.length; i++){
	    	
	    	System.out.println("File: "+files[i].getName());
	        if (files[i].isDirectory() && files[i].getName().equals("input")){ 
	            numberOfInputFiles=files[i].listFiles().length;
	            System.out.println("File: "+files[i].getName()+" is input drectory");
	        }
	        
	        if (files[i].isDirectory() && files[i].getName().equals("output")){ 
	            numberOfOutputFiles=files[i].listFiles().length;
	            System.out.println("File: "+files[i].getName()+" is output drectory");
	        }
	        
	        if (files[i].isFile() && files[i].getName().endsWith(".pdf")){ 
	            pdf=true;
	            System.out.println("File: "+files[i].getName()+" is a pdf file");
	        }
	    }
    	
    	boolean result=true;
    	
    	if(numberOfFiles!=3) {
    		System.out.println("The number of files is not 3");
    		result=false;
    	}
    	
    	if(!pdf) {
    		System.out.println("The pdf is missing");
    		result=false;
    	}
    	
    	if(numberOfInputFiles!=numberOfOutputFiles) {
    		System.out.println("The number of output and input files is not the  same");
    		result=false;
    	}
    	
    	if(numberOfInputFiles==0) {
    		System.out.println("There is no input folder or no input files");
    		result=false;
    	}
    	
    	if(numberOfOutputFiles==0) {
    		System.out.println("There is no output folder or no output files");
    		result=false;
    	}
    	
    	return result;
    }
    
}