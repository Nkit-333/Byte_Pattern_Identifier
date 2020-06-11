import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * 
 * Models a single set of bytes that represent a pattern.
 * 
 * @author Ankit Rouniyar
 * @version 1.0
 */

public class BytePattern{ 

    /**
     * The bytes within the pattern.
     */
    private byte[] bytes;

    /**
     * The next position to be checked within the pattern's byte array.
     */
    private int pattern = -1; //position to the array list
    ArrayList<byte[]> patterns = new ArrayList<byte[]>();
   
    private boolean error;

    public boolean isError()
    {
    	return error;
    }
    private int checkPos = 0;
    
    /**
     * Checks if the given value matches the next byte to be checked within the pattern.
     * 
     * Each time this method is called it progresses to the next byte within the pattern, until the value does not match
     * or the end of the pattern is reached (in which case a match has been found).
     * 
     * @param value the value to be checked against the next byte in the pattern.
     * @return true if the pattern has been matched, false if the pattern has not (yet) matched.
     */
    public boolean checkNext(byte value) {

         if(value == bytes[checkPos])
       {
    	   checkPos++;
    	   if(checkPos == bytes.length)
    	   {
    		   checkPos = 0;
    		   return true;
    	   }
    	   else 
    		   return false;
       }
       else
       {
    	   checkPos = 0;
    	   return false;       	   
    			   
       }
    }
    
    /**
     *Used for setting the bytes which is to be called from other class through reading a File
     *@param bytes to be assigned in the byte
     */
    public void setBytes(byte[] bytes){
    	this.bytes=bytes;
    }
    
  
       public BytePattern()
       {
    	   
       }
       public BytePattern(byte[] bytes)
       {   
             this.bytes = bytes;
             
       }
       /**
        * Return the length of the pattern
        * @return length of the pattern
        */
       public int length()
       {
    	   return patterns.get(pattern).length;
       }
       
       /**
        * Returns the size of the patterns
        * @return size of the patterns
        */
       public int patternLength()
       {
    	   return patterns.size();
       }
    
       /**
        * To get to the next pattern and store it and
        * then convert it to bytes
        */
       public void nextPattern()
       {
    	   checkPos = 0;
    	   pattern++;
    	   bytes = patterns.get(pattern);
       }
       /**
        * reset() used to reset the pattern and the checkPos
        */
       void reset()
       {
    	   pattern = -1;
    	   checkPos = 0;
       }
       void clearPattern()
       {
    	   patterns.clear();
       }
       void addPattern(byte[] pattern)
       {
    	   patterns.add(pattern);
       }
       
      
       public byte[] getBytes()
       {
    	   if(pattern == -1)
    	   {
    		   nextPattern();
    	   }
    	   return bytes;
       }
       
       /**
        * is used to get the patterns and store it in ArrayList
        * @return patterns which gets stored in the ArrayList<byte[]>
        */
       public ArrayList<byte[]> getPatterns()
       {
    	   return patterns;
       }
      
    
     	public String parseLine(String line) 
        {   
    	error = false;
         String[] linesplit = line.split(" ");
         for(String linebyte : linesplit)
         {
        	 if(linebyte.length() != 2)
        	 {
        		 error = true;
        	 }
        	 try
        	 {
        		 @SuppressWarnings("unused")
				int test = Integer.parseInt(linebyte,16);
        	 }
        	 catch(NumberFormatException e)
        	 {
        		 error = true;
        	 }
        	
         }
         if(error)
         {
        	 JOptionPane.showMessageDialog(null, "Pattern break down","Error", -1);
        	// linesplit++;
         }
         else
         {
        	 //System.out.println(DatatypeConverter.parseHexBinary(line.replace(" "," ")));
             return line.replaceAll(" ", "");
         }
		return null;
        }
     	
     	/**
     	 * Changes the value of hexadecimal pattern to byte 
     	 * @param hexString is used to change from hex to byte
     	 * @return data which is the converted form of hex to byte
     	 */
     public static byte[] hexToByte(String hexString)
          {
       	   int len = hexString.length();
       	   byte[] data = new byte[len/2];
       	   
       	   for(int i=0; i<len; i=i+2)
       	   {
       		   data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
   	   	               + Character.digit(hexString.charAt(i+1), 16));
   	   	  }
       	   return data;
          }
 }
    


