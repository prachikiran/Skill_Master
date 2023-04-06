import java.io.*;

public class FileSplit {

	public static void main(String[] args) {
		
		String certChainPEM = "D:/JavaFiles/cert_chain.pem";
		File certChainPEMFile = new File(certChainPEM);
		String destinationPath = "D:/JavaFiles";
	 	File folder = new File(destinationPath);
		String certPEM	= "cert.pem";
	    File certPEMFile = new File(folder, certPEM);
		String chainPEM = "chain.pem";
		File chainPEMFile = new File(folder, chainPEM);
	        
	    try {
	    	   BufferedReader reader = new BufferedReader(new FileReader(certChainPEMFile));
	            String line = null;
	            StringBuilder stringBuilder1 = new StringBuilder();
	            StringBuilder stringBuilder2 = new StringBuilder();
	            boolean flag = true;
	            while ((line = reader.readLine()) != null) {
	                if (flag) {
	                    stringBuilder1.append(line);
	                    stringBuilder1.append(System.lineSeparator());
	                    if (line.contains("-----END CERTIFICATE")) {
	                    	flag = false;
	                    }
	                } else {
	                    stringBuilder2.append(line);
	                    stringBuilder2.append(System.lineSeparator());
	                }
	            }
	            reader.close();
	            
				certPEMFile.createNewFile();
		        try (BufferedWriter writer = new BufferedWriter(new FileWriter(certPEMFile))) {
		        	 writer.write(stringBuilder1.toString());
		             writer.flush();
		        }
				chainPEMFile.createNewFile();
				try (BufferedWriter writer = new BufferedWriter(new FileWriter(chainPEMFile))) {
		             writer.write(stringBuilder2.toString());
		             writer.flush();
		        }           
		            System.out.println("First File: " + certPEMFile);
		            System.out.println("Second File: " + chainPEMFile);
	      } catch (IOException e) {
	            e.printStackTrace();
	      	}
	}

}
