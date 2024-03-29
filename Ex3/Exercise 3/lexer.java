import java.util.*;
import java.io.*;
/* semantic value of token returned by scanner */
public class lexer
{
	public static void main (String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader("Code0000001.java"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("Code0000001_tokens.txt"));
		Yylex yy = new Yylex (reader);
		while(true)
		{
			String x =yy.next_token();
			if(x==null)
				break;
			writer.write(x);	
			writer.write('\n');
		}
		reader.close();
		writer.close();
	}
}


class Yylex {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

  public String sourceFilename;
  public String temp = "";
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int STRING = 3;
	private final int YYINITIAL = 0;
	private final int MLCOMMENTS = 2;
	private final int COMMENTS = 1;
	private final int yy_state_dtrans[] = {
		0,
		63,
		65,
		67
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NOT_ACCEPT,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NOT_ACCEPT,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NOT_ACCEPT,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NOT_ACCEPT,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NOT_ACCEPT,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NOT_ACCEPT,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NOT_ACCEPT,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NOT_ACCEPT,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NOT_ACCEPT,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NOT_ACCEPT,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"5:8,12:2,3,5,12,2,5:18,12,21,6,5:3,28,8,13,14,4,18,27,17,10,1,9:10,5,26,22," +
"20,19,5:2,52:4,11,52:13,29,52:7,15,7,16,5,52,5,42,39,44,46,37,48,34,45,32,5" +
"2,43,41,38,33,40,50,52,31,36,30,47,51,49,52,35,52,24,23,25,5:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,125,
"0,1,2,3,1:3,4,1,5,1:2,6,1,7,8,9,10,11,12,1:15,5,1,13,1:8,14,15,16,14,17,18," +
"19,20,21,22,23,24,25,26,27,17,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42," +
"43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67," +
"68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90")[0];

	private int yy_nxt[][] = unpackFromString(91,53,
"1,2,3:2,4,5,6,5,47,7,8,9,3,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25," +
"119,91,120,48,73,9:2,109,9:2,92,74,93,9:2,94,9,121,9,75,110,122,95,9,-1:54," +
"26,-1:2,27,-1:50,3:2,-1:8,3,-1:49,7,55,-1:51,9,-1,9,-1:17,9:24,-1:16,28,-1:" +
"53,29,-1:53,30,-1:54,31,-1:52,32,-1:52,33,-1:52,34,-1:41,37,-1,61,-1:49,36," +
"-1:45,46,-1:2,46:3,53,46:45,-1:9,9,-1,9,-1:17,9:4,54,9:14,35,9:4,-1:9,50,-1" +
":44,42,-1:58,69,-1:46,46,-1:2,46:3,57,49,46:44,-1:9,9,-1,9,-1:17,9,35,9:22," +
"-1:9,37,-1:52,9,-1,9,-1:17,9:20,35,9:3,-1:6,59,-1,36,-1:53,9,-1,9,-1:17,9:2" +
",35,9:21,-1:8,38,-1:53,9,-1,9,-1:17,9:8,35,9:15,-1:9,9,-1,9,-1:17,9:5,35,9:" +
"18,1,39:2,40,39:49,-1:9,9,-1,9,-1:17,9:17,35,9:6,1,41,-1,41,51,41:48,-1:9,9" +
",-1,9,-1:17,9:14,35,9:9,1,43:5,44,52,43:45,-1:9,9,-1,9,-1:17,9:7,35,9:16,-1" +
":6,45,-1:55,9,-1,9,-1:17,9:9,35,9:14,-1:9,9,-1,9,-1:17,9:4,35,9:19,-1:9,9,-" +
"1,9,-1:17,9:15,35,9:8,-1:9,9,-1,9,-1:17,9:8,56,9:15,-1:9,9,-1,9,-1:17,9:18," +
"54,9:5,-1:9,9,-1,9,-1:17,9:11,58,9,99,9:10,-1:9,9,-1,9,-1:17,9:18,60,9:5,-1" +
":9,9,-1,9,-1:17,9,60,9:22,-1:9,9,-1,9,-1:17,9:4,62,9:19,-1:9,9,-1,9,-1:17,9" +
":13,58,9:10,-1:9,9,-1,9,-1:17,9:3,64,9:20,-1:9,9,-1,9,-1:17,9:2,54,9:21,-1:" +
"9,9,-1,9,-1:17,9:13,66,9:10,-1:9,9,-1,9,-1:17,9:7,68,9:16,-1:9,9,-1,9,-1:17" +
",9:7,60,9:16,-1:9,9,-1,9,-1:17,9:12,60,9:11,-1:9,9,-1,9,-1:17,9:8,70,9:15,-" +
"1:9,9,-1,9,-1:17,9:2,71,9:21,-1:9,9,-1,9,-1:17,9:3,72,9:20,-1:9,9,-1,9,-1:1" +
"7,9:13,71,9:10,-1:9,9,-1,9,-1:17,9:12,71,9:11,-1:9,9,-1,9,-1:17,9:2,76,9:21" +
",-1:9,9,-1,9,-1:17,9:2,97,9:3,77,9:4,123,9:12,-1:9,9,-1,9,-1:17,9:11,78,9:1" +
"2,-1:9,9,-1,9,-1:17,9:12,98,9:3,79,9:7,-1:9,9,-1,9,-1:17,9:11,80,9:12,-1:9," +
"9,-1,9,-1:17,9:11,81,9:12,-1:9,9,-1,9,-1:17,9:8,82,9:15,-1:9,9,-1,9,-1:17,9" +
":13,83,9:10,-1:9,9,-1,9,-1:17,9:12,84,9:11,-1:9,9,-1,9,-1:17,9:3,85,9:20,-1" +
":9,9,-1,9,-1:17,9:3,78,9:20,-1:9,9,-1,9,-1:17,9,86,9:22,-1:9,9,-1,9,-1:17,9" +
":18,87,9:5,-1:9,9,-1,9,-1:17,9,88,9:22,-1:9,9,-1,9,-1:17,9:10,85,9:13,-1:9," +
"9,-1,9,-1:17,9:12,88,9:11,-1:9,9,-1,9,-1:17,9:8,89,9:15,-1:9,9,-1,9,-1:17,9" +
",90,9:22,-1:9,9,-1,9,-1:17,9,114,9:14,96,9:7,-1:9,9,-1,9,-1:17,9:16,100,9:7" +
",-1:9,9,-1,9,-1:17,9:2,101,9:21,-1:9,9,-1,9,-1:17,9:7,102,9:16,-1:9,9,-1,9," +
"-1:17,9,103,9:22,-1:9,9,-1,9,-1:17,9:13,104,9:10,-1:9,9,-1,9,-1:17,9:18,105" +
",9:5,-1:9,9,-1,9,-1:17,9:10,106,9:13,-1:9,9,-1,9,-1:17,9:12,107,9:11,-1:9,9" +
",-1,9,-1:17,9:4,108,9:19,-1:9,9,-1,9,-1:17,9,111,9:4,112,9:17,-1:9,9,-1,9,-" +
"1:17,9:8,113,9:15,-1:9,9,-1,9,-1:17,9:11,115,9:12,-1:9,9,-1,9,-1:17,9:2,124" +
",9:15,116,9:5,-1:9,9,-1,9,-1:17,9:11,117,9:12,-1:9,9,-1,9,-1:17,9:3,118,9:2" +
"0");

	public String next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

  return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{ 
  return "DB\t/"; 
}
					case -3:
						break;
					case 3:
						{ }
					case -4:
						break;
					case 4:
						{ 
  return "MB\t*"; 
}
					case -5:
						break;
					case 5:
						{ 
  return "Undefined\t"+yytext(); 
}
					case -6:
						break;
					case 6:
						{
  temp = "\"";
  yybegin(STRING);
}
					case -7:
						break;
					case 7:
						{
  return "NM\t"+yytext(); 
}
					case -8:
						break;
					case 8:
						{ 
  return "DO\t."; 
}
					case -9:
						break;
					case 9:
						{ 
  return "ID\t"+yytext(); 
}
					case -10:
						break;
					case 10:
						{ 
  return "LB\t("; 
}
					case -11:
						break;
					case 11:
						{ 
  return "RB\t)"; 
}
					case -12:
						break;
					case 12:
						{ 
  return "LS\t["; 
}
					case -13:
						break;
					case 13:
						{ 
  return "RS\t]"; 
}
					case -14:
						break;
					case 14:
						{ 
  return "MO\t-"; 
}
					case -15:
						break;
					case 15:
						{ 
  return "PO\t+"; 
}
					case -16:
						break;
					case 16:
						{ 
  return "RT\t>"; 
}
					case -17:
						break;
					case 17:
						{ 
  return "AO\t="; 
}
					case -18:
						break;
					case 18:
						{ 
  return "LN\t!"; 
}
					case -19:
						break;
					case 19:
						{ 
  return "LT\t<"; 
}
					case -20:
						break;
					case 20:
						{ 
  return "LO\t|"; 
}
					case -21:
						break;
					case 21:
						{ 
  return "LC\t{"; 
}
					case -22:
						break;
					case 22:
						{ 
  return "RC\t}"; 
}
					case -23:
						break;
					case 23:
						{ 
  return "SM\t;"; 
}
					case -24:
						break;
					case 24:
						{ 
  return "FA\t,"; 
}
					case -25:
						break;
					case 25:
						{ 
  return "LA\t&"; 
}
					case -26:
						break;
					case 26:
						{
  yybegin(COMMENTS);
}
					case -27:
						break;
					case 27:
						{
  yybegin(MLCOMMENTS);
}
					case -28:
						break;
					case 28:
						{ 
  return "AA\t[]"; 
}
					case -29:
						break;
					case 29:
						{ 
  return "MM\t--"; 
}
					case -30:
						break;
					case 30:
						{ 
  return "PP\t++"; 
}
					case -31:
						break;
					case 31:
						{ 
  return "GE\t>="; 
}
					case -32:
						break;
					case 32:
						{ 
  return "EQ\t=="; 
}
					case -33:
						break;
					case 33:
						{ 
  return "NE\t!="; 
}
					case -34:
						break;
					case 34:
						{ 
  return "LE\t<="; 
}
					case -35:
						break;
					case 35:
						{ 
  return "KW\t"+yytext(); 
}
					case -36:
						break;
					case 36:
						{
  return "CH\t"+yytext(); 
}
					case -37:
						break;
					case 37:
						{
  return "NM\t"+yytext(); 
}
					case -38:
						break;
					case 38:
						{ 
  return "CH\t"+yytext(); 
}
					case -39:
						break;
					case 39:
						{
}
					case -40:
						break;
					case 40:
						{
  yybegin(YYINITIAL);
}
					case -41:
						break;
					case 41:
						{
}
					case -42:
						break;
					case 42:
						{
  yybegin(YYINITIAL);
}
					case -43:
						break;
					case 43:
						{
  temp += yytext();
}
					case -44:
						break;
					case 44:
						{
  temp += "\"";
  yybegin(YYINITIAL);
  return "ST\t"+temp; 
}
					case -45:
						break;
					case 45:
						{
  temp += "\\\"";
}
					case -46:
						break;
					case 47:
						{ 
  return "Undefined\t"+yytext(); 
}
					case -47:
						break;
					case 48:
						{ 
  return "ID\t"+yytext(); 
}
					case -48:
						break;
					case 49:
						{
  return "CH\t"+yytext(); 
}
					case -49:
						break;
					case 50:
						{
  return "NM\t"+yytext(); 
}
					case -50:
						break;
					case 51:
						{
}
					case -51:
						break;
					case 52:
						{
  temp += yytext();
}
					case -52:
						break;
					case 54:
						{ 
  return "ID\t"+yytext(); 
}
					case -53:
						break;
					case 56:
						{ 
  return "ID\t"+yytext(); 
}
					case -54:
						break;
					case 58:
						{ 
  return "ID\t"+yytext(); 
}
					case -55:
						break;
					case 60:
						{ 
  return "ID\t"+yytext(); 
}
					case -56:
						break;
					case 62:
						{ 
  return "ID\t"+yytext(); 
}
					case -57:
						break;
					case 64:
						{ 
  return "ID\t"+yytext(); 
}
					case -58:
						break;
					case 66:
						{ 
  return "ID\t"+yytext(); 
}
					case -59:
						break;
					case 68:
						{ 
  return "ID\t"+yytext(); 
}
					case -60:
						break;
					case 70:
						{ 
  return "ID\t"+yytext(); 
}
					case -61:
						break;
					case 71:
						{ 
  return "ID\t"+yytext(); 
}
					case -62:
						break;
					case 72:
						{ 
  return "ID\t"+yytext(); 
}
					case -63:
						break;
					case 73:
						{ 
  return "ID\t"+yytext(); 
}
					case -64:
						break;
					case 74:
						{ 
  return "ID\t"+yytext(); 
}
					case -65:
						break;
					case 75:
						{ 
  return "ID\t"+yytext(); 
}
					case -66:
						break;
					case 76:
						{ 
  return "ID\t"+yytext(); 
}
					case -67:
						break;
					case 77:
						{ 
  return "ID\t"+yytext(); 
}
					case -68:
						break;
					case 78:
						{ 
  return "ID\t"+yytext(); 
}
					case -69:
						break;
					case 79:
						{ 
  return "ID\t"+yytext(); 
}
					case -70:
						break;
					case 80:
						{ 
  return "ID\t"+yytext(); 
}
					case -71:
						break;
					case 81:
						{ 
  return "ID\t"+yytext(); 
}
					case -72:
						break;
					case 82:
						{ 
  return "ID\t"+yytext(); 
}
					case -73:
						break;
					case 83:
						{ 
  return "ID\t"+yytext(); 
}
					case -74:
						break;
					case 84:
						{ 
  return "ID\t"+yytext(); 
}
					case -75:
						break;
					case 85:
						{ 
  return "ID\t"+yytext(); 
}
					case -76:
						break;
					case 86:
						{ 
  return "ID\t"+yytext(); 
}
					case -77:
						break;
					case 87:
						{ 
  return "ID\t"+yytext(); 
}
					case -78:
						break;
					case 88:
						{ 
  return "ID\t"+yytext(); 
}
					case -79:
						break;
					case 89:
						{ 
  return "ID\t"+yytext(); 
}
					case -80:
						break;
					case 90:
						{ 
  return "ID\t"+yytext(); 
}
					case -81:
						break;
					case 91:
						{ 
  return "ID\t"+yytext(); 
}
					case -82:
						break;
					case 92:
						{ 
  return "ID\t"+yytext(); 
}
					case -83:
						break;
					case 93:
						{ 
  return "ID\t"+yytext(); 
}
					case -84:
						break;
					case 94:
						{ 
  return "ID\t"+yytext(); 
}
					case -85:
						break;
					case 95:
						{ 
  return "ID\t"+yytext(); 
}
					case -86:
						break;
					case 96:
						{ 
  return "ID\t"+yytext(); 
}
					case -87:
						break;
					case 97:
						{ 
  return "ID\t"+yytext(); 
}
					case -88:
						break;
					case 98:
						{ 
  return "ID\t"+yytext(); 
}
					case -89:
						break;
					case 99:
						{ 
  return "ID\t"+yytext(); 
}
					case -90:
						break;
					case 100:
						{ 
  return "ID\t"+yytext(); 
}
					case -91:
						break;
					case 101:
						{ 
  return "ID\t"+yytext(); 
}
					case -92:
						break;
					case 102:
						{ 
  return "ID\t"+yytext(); 
}
					case -93:
						break;
					case 103:
						{ 
  return "ID\t"+yytext(); 
}
					case -94:
						break;
					case 104:
						{ 
  return "ID\t"+yytext(); 
}
					case -95:
						break;
					case 105:
						{ 
  return "ID\t"+yytext(); 
}
					case -96:
						break;
					case 106:
						{ 
  return "ID\t"+yytext(); 
}
					case -97:
						break;
					case 107:
						{ 
  return "ID\t"+yytext(); 
}
					case -98:
						break;
					case 108:
						{ 
  return "ID\t"+yytext(); 
}
					case -99:
						break;
					case 109:
						{ 
  return "ID\t"+yytext(); 
}
					case -100:
						break;
					case 110:
						{ 
  return "ID\t"+yytext(); 
}
					case -101:
						break;
					case 111:
						{ 
  return "ID\t"+yytext(); 
}
					case -102:
						break;
					case 112:
						{ 
  return "ID\t"+yytext(); 
}
					case -103:
						break;
					case 113:
						{ 
  return "ID\t"+yytext(); 
}
					case -104:
						break;
					case 114:
						{ 
  return "ID\t"+yytext(); 
}
					case -105:
						break;
					case 115:
						{ 
  return "ID\t"+yytext(); 
}
					case -106:
						break;
					case 116:
						{ 
  return "ID\t"+yytext(); 
}
					case -107:
						break;
					case 117:
						{ 
  return "ID\t"+yytext(); 
}
					case -108:
						break;
					case 118:
						{ 
  return "ID\t"+yytext(); 
}
					case -109:
						break;
					case 119:
						{ 
  return "ID\t"+yytext(); 
}
					case -110:
						break;
					case 120:
						{ 
  return "ID\t"+yytext(); 
}
					case -111:
						break;
					case 121:
						{ 
  return "ID\t"+yytext(); 
}
					case -112:
						break;
					case 122:
						{ 
  return "ID\t"+yytext(); 
}
					case -113:
						break;
					case 123:
						{ 
  return "ID\t"+yytext(); 
}
					case -114:
						break;
					case 124:
						{ 
  return "ID\t"+yytext(); 
}
					case -115:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
