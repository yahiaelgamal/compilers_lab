import java.util.*;
import java.io.*;

class Word
{
	private String w;
	
	public Word(String w)
	{
		this.w=w;
	}
}

class Fragment
{
	private char type;
	private Vector<Word> ws;
	
	public Fragment()
	{
		ws=new Vector<Word>();
	}
	
	public void addWord(Word w)
	{
		ws.add(w);
	}
	
	public void setType(char t)
	{
		type=t;
	}
	
	public int getWordCount()
	{
		return ws.size();
	}
	
	public char getType()
	{
		return type;
	}
}

class Paragraph
{
	private Vector<Fragment> fs;
	
	public Paragraph()
	{
		fs=new Vector<Fragment>();
	}
	
	public void addFragment(Fragment f)
	{
		fs.add(f);
	}
	
	public int getFragmentsCount()
	{
		return fs.size();
	}
	
	public Fragment getFragment(int x)
	{
		return fs.get(x);
	}
}

public class NReader 
{

    public static void main(String [] args) throws IOException
    {
    	BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
    	BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt"));

		boolean streamHasInput = true;
		char ch, EOF = (char)(-1);

		Vector<Paragraph> res=new Vector<Paragraph>();
		String textBuffer = "";
		Paragraph paragraphBuffer =new Paragraph();
		Fragment fragmentBuffer=new Fragment();
		
		int wordCount=1;
		int fragmentCount=1;
		int paragraphCount=1;

		ch = (char)(reader.read());
		while (streamHasInput)
		{
			if (ch == EOF)
			{
				streamHasInput = false;
				if(paragraphBuffer.getFragmentsCount()!=0)
				{
					res.add(paragraphBuffer);
				}
			}
			
			if(ch == ' ')
			{
				if(textBuffer.length()!=0)
				{
					Word w=new Word(textBuffer);
					textBuffer="";
					fragmentBuffer.addWord(w);
					wordCount++;
				}
			}
			else if(ch == '!'||ch == '.'||ch == ','||ch == '?'||ch == ';')
			{
				Word w=new Word(textBuffer);
				textBuffer="";
				fragmentBuffer.addWord(w);
				fragmentBuffer.setType(ch);
				paragraphBuffer.addFragment(fragmentBuffer);
				fragmentBuffer=new Fragment();
				wordCount++;
				fragmentCount++;
			}
			else if(ch == '\n')
			{
				if(paragraphBuffer.getFragmentsCount()!=0)
				{
					res.add(paragraphBuffer);
					paragraphBuffer=new Paragraph();
					paragraphCount++;
				}
			}
			else if(ch == '('||ch == ')'||ch == '"')
			{
				if(textBuffer.length()!=0)
				{
					Word w=new Word(textBuffer);
					textBuffer="";
					fragmentBuffer.addWord(w);
					wordCount++;
				}
				Word w=new Word(""+ch);
				fragmentBuffer.addWord(w);
				wordCount++;
			}
			else
			{
				textBuffer+=ch;
			}
			
			ch = (char)(reader.read());
		}
		reader.close();
		
		writer.write("Words count: "+wordCount+".\nFragment count: "+fragmentCount+".\nParagraphs count: "+paragraphCount+".\n\n");
		writer.write("Full report:\n\n");
		for(int i=0;i<res.size();i++)
		{
			Paragraph pr=res.get(i);
			writer.write("Paragraph "+(i+1)+" containes "+pr.getFragmentsCount()+" fragments:\n");
			for(int j=0;j<pr.getFragmentsCount();j++)
			{
				Fragment fr=pr.getFragment(j);
				writer.write("\tFragment "+(j+1)+" has "+fr.getWordCount()+" words, and of type ("+fr.getType()+")\n");
			}
		}
		writer.close();
    }
}