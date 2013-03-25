import java.util.ArrayList;


public class Shabd implements Comparable<Shabd>, java.io.Serializable
{
	private String word ;
	private int length;
	private int signature;
	static final long serialVersionUID = -7588980448693010399L;

	public Shabd()
	{
		word="";
		length=0;
	}

	public Shabd(String word, int sig)
	{
		this.word=word;
		length=word.length();
		signature = sig;
	}

	public String getWord()
	{
		return word;
	}

	public int getLength()
	{
		return length;
	}

	public int getSignature()
	{
		return signature;
	}

	public void setWord(String word)
	{
		this.word = word;
	}

	@Override
	public int compareTo(Shabd o) {
		// TODO Auto-generated method stub
		if(this.length < o.length)
		{
			return -1;
		}
		if(this.length > o.length)
		{
			return 1;
		}

		return 0;
	}
}
