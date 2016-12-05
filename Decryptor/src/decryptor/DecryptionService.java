package decryptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import cipher.CipherKeys;

/**
 * This class was created for the BDD Framework, its function is to decrypt any encrypted
 * file passed into it so that vital data and information can be retrieved
 * @author Jesse Derochie
 *
 */
public class DecryptionService 
{
	File encryptedFile;
	String m_filePath;
	String m_fileName;
	private String[] m_wordArray;

	/**
	 * Constructor
	 * @param filePath to the file that needs to be decrypted
	 */
	public DecryptionService(String filePath)
	{
		m_filePath = filePath;
	}
	
	/**
	 * This readFile method is used to read the file that needs to be encrypted into a 
	 * string so it can be manipulated
	 * @param filePath
	 * @return a string with the file contents ready to be manipulated
	 */
	public String readFile(String filePath)
	{
		File file = new File(filePath);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
	    Scanner scanner = null;
	    String contents = "";
	    
		try {
			scanner = new Scanner(file);
	        while(scanner.hasNextLine()) 
	        {
	            fileContents.append(scanner.nextLine());
	        }
	        contents = fileContents.toString();
	        
		} catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
	    } finally {
	        scanner.close();
	    }
		return contents;
	}
	
	/**
	 * This method will run the encryption service on the file that is passed into the constructor
	 */
	public void run()
	{
	    String contents = readFile(m_filePath);
		
		// prepare encryptedFile for word conversion and storage in word array
		wordConverter(contents);
		
		contents = "";
		
		// undo bit shift
		for(int i = 0; i < m_wordArray.length; i++)
		{
			CipherKeys.bitShifter(m_wordArray[i], -8);
		}
		
		
		// undo alphaCipher2
		for(int i = 0; i < m_wordArray.length; i++)
		{
			m_wordArray[i] = undoAlphaCipher2(m_wordArray[i]);
		}
		
		for(int i = 0; i < m_wordArray.length; i ++)
		{
			if(i != m_wordArray.length)
			{
				if(i % 2 == 0)
				{
					m_wordArray[i] = undoAlphaCipher(m_wordArray[i]) + new String(" ");
				}
				else
				{
					m_wordArray[i] = CipherKeys.reverseLetters(m_wordArray[i]) + new String(" ");
				}
			}
			else
			{
				if(i % 2 == 0)
				{
					m_wordArray[i] = undoAlphaCipher(m_wordArray[i]);
				}
				else
				{
					m_wordArray[i] = CipherKeys.reverseLetters(m_wordArray[i]);
				}
			}
		}
		
		for(int i = 0; i < m_wordArray.length; i++)
		{
			contents += m_wordArray[i];
		}
		
		FileWriter fw;
		try {
			fw = new FileWriter(m_filePath);
			fw.write(contents);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Converts the string into an array or strings containing each individual word
	 * @param fileContents
	 */
	public void wordConverter(String fileContents)
	{
		m_wordArray = fileContents.split(" ");
	}
	
	/**
	 * Undoes the Second Alpha cipher applied to a word supplied as a parameter
	 * @param word to undo the second alpha cipher too
	 * @return the word now un-manipulated by the second alpha cipher
	 */
	public String undoAlphaCipher2(String word)
	{
		String[] tempWord = new String[word.length()];
		String[] temp = new String[word.length()];
		
		tempWord = word.split("");
		
		for(int i = 0; i < word.length(); i++)
		{
			for(int j = 0; j < CipherKeys.m_alphaCipher2.length; j++)
			{
				if(tempWord[i].equals(" "))
				{
					temp[i] = " ";
				}
				else if(tempWord[i].equals("'"))
				{
					temp[i] = "'";
				}
				else if(tempWord[i].equals(";"))
				{
					temp[i] = ";";
				}
				else if(tempWord[i].equals(CipherKeys.m_alphaCipher2[j]))
				{
					temp[i] = CipherKeys.m_alphaCipher[j];
				}
			}
		}
		word = "";
		for(int i = 0; i < temp.length; i++)
		{
			word += temp[i];
		}
		
		return word;
	}
	
	/**
	 * Undoes the first Alpha cipher applied to a word supplied as a parameter
	 * @param word to undo the alpha cipher too
	 * @return the word now un-manipulated by the alpha cipher
	 */
	public String undoAlphaCipher(String word)
	{
		String[] tempWord = new String[word.length()];
		String[] temp = new String[word.length()];
		
		tempWord = word.split("");
		
		for(int i = 0; i < word.length(); i++)
		{
			for(int j = 0; j < CipherKeys.m_alphaCipher.length; j++)
			{
				if(tempWord[i].equals(CipherKeys.m_alphaCipher[j]))
				{
					temp[i] = CipherKeys.m_alphabet[j];
				}
			}
		}
		
		word = "";
		for(int i = 0; i < temp.length; i++)
		{
			word += temp[i];
		}
		
		return word;
	}
	
}
