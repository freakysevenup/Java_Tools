package cipher;

/**
 * This is a TOP SECRET file, it should not be included in any repositories or
 * made available to any employee who has not been authorized to utilize it's functions
 * or edit its contents. This file is a copyright by QAC Consultants. The file contains 
 * sensitive information which should not be made available to any persons without the 
 * direct consent of both the Lead Developer of the BDD Framework and that of George Kogan.
 * This is not freeware, this is not shareware.
 * @author Jesse Derochie
 *
 */
public final class CipherKeys 
{
	public static final String[] m_alphabet =
		{
			"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "\"",
			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ",", ".", ";", "'", "`", "_", ":", "/", "{", "}"
		};
	public static final String[] m_alphaCipher =
		{
			"m", "z", "i", "g", "l", "r", "p", "a", "w", "o", "e", "n", "d", "k", "s", "u", "f", "q", "h", "c", "x", "j", "y", "b", "v", "t",
			"M", "Z", "I", "G", "L", "R", "P", "A", "W", "O", "E", "N", "D", "K", "S", "U", "F", "Q", "H", "C", "X", "J", "Y", "B", "V", "T", "\"",
			"'", "4", ".", "{", "7", "`", "9", "}", "6", "0", "_", "/", ":", ";", "5", "1", "3", ",", "8", "2"
		};
	public static final String[] m_alphaCipher2 =
		{
			"]", "`", "!", "D", "@", "P", "#", "A", "$", "L", "%", "U", "5", "{", "6", "&", "T", "*", "(", "9", ")", "0", "_", "-", "+", "=", 
			"[", "~", "}", "|", "\\", ":", ";", "<", ",", ">", ".", "?", "/", "B", "^", "E", "O", "J", "7", "S", "G", "W", "Z", "Q", "X", "I", 
			"H", "N", "F", "1", "Y", "3", "M", "4", "K", "2", "C", "R", "V", "©", "µ", "¿", "a", "p", "h", "k", "f"
		};
	
	/**
	 * Reverses every letter of the supplied word so the word is written backwards
	 * @param word the word to reverse
	 * @return the word now reversed
	 */
	public static String reverseLetters(String word)
	{
		word = new StringBuilder(word).reverse().toString();
		return word;
	}
	
	/**
	 * Shift the bits of every character in the supplied word by the supplied amount of bits
	 *
	 * @param word the word to shift the bits of each character on
	 * @param bits the amount of bits to shift the characters by
	 * @return the word whose characters are now shifted by the supplied number of bits
	 */
	public static String bitShifter(String word, int bits)
	{
		StringBuilder msg = new StringBuilder(word);
		for (int i = 0; i < msg.length(); i ++) 
		{
		    msg.setCharAt(i, (char) (msg.charAt(i) + bits));
		}
		return word;
	}
}
