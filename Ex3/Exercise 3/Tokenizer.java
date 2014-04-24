import java.io.*;
import java.util.ArrayList;
/*
 * Tutorial T8
 * 19-1023, Yahia El Gamal
 * 19-2162, Adam Ghanem
 */

public class Tokenizer {

  static String[] TWO_CHARS = {"[]", "<=", ">=", "==", "!=", "--", "++", "!="};
  static char[] FIRST_CHAR = {'[', '<', '>', '=', '!', '-', '+', '!'};
  static String DELIM_FIRST_CHARS  = "[<>=!-+";
  static String DELIM_SUPER = "];,{}()*&|";
  public static String getType(String token) {
    if (isKeyword(token))
      return "KW";
    else if (token.equals("--"))
      return "MM";
    else if (token.equals("++"))
      return "PP";
    else if (token.equals("[]"))
      return "AA";
    else if (token.equals("<="))
      return "LE";
    else if (token.equals(">="))
      return "GE";
    else if (token.equals("&"))
      return "LA";
    else if (token.equals("!="))
      return "NE";
    else if (token.equals("=="))
      return "EQ";
    else if (token.equals("!"))
      return "LN";
    else if (token.equals("|"))
      return "LO";
    else if (token.equals("("))
      return "LB";
    else if (token.equals(")"))
      return "RB";
    else if (token.equals("{"))
      return "LC";
    else if (token.equals("}"))
      return "RC";
    else if (token.equals("["))
      return "LS";
    else if (token.equals("]"))
      return "RS";
    else if (token.equals("="))
      return "AO";
    else if (token.equals("*"))
      return "MB";
    else if (token.equals("/"))
      return "DB";
    else if (token.equals("+"))
      return "PO";
    else if (token.equals("-"))
      return "MO";
    else if (token.equals(";"))
      return "SM";
    else if (token.equals(","))
      return "FA";
    else if (token.equals("."))
      return "DO";
    else if (token.equals("<"))
      return "LT";
    else if (token.equals(">"))
      return "GT";
    else if (token.charAt(0) == '\'' && token.charAt(token.length() - 1) == '\'')
      return "CH";
    else if (token.charAt(0) == '"' && token.charAt(token.length() - 1) == '"')
      return "ST";
    else if (token.matches("[0-9]+(\\.[0-9]+)?"))
      return "NM";
    else if(token.matches("[a-zA-Z][a-zA-Z_0-9]*"))
      return "ID";
    else
      return "A7";
  }


  public static boolean isKeyword(String token) {
    String[] keywords = { "String", "System", "boolean", "break", "byte",
        "char", "class", "double", "false", "for", "if", "int", "long",
        "new", "out", "println", "public", "return", "short", "static",
        "true", "void", "while" };
    for (int i = 0; i < keywords.length; i++)
      if (keywords[i].equals(token))
        return true;
    return false;
  }

  public static boolean include_in_twos(String string){
    for(String two : TWO_CHARS){
      if(two.equals(string))
        return true;
    }
    return false;
  }

  public static boolean include_in_frsts(char c){
    for(char first : FIRST_CHAR){
      if(first == c)
        return true;
    }
    return false;
  }

  public static void main(String[] args) throws IOException {
    /* String filename = "Code.txt"; */
    String filename     = "XLCode.java";
    String out_filename = "XLCode_tokens.txt";

    BufferedReader reader = new BufferedReader(new FileReader(filename));
    BufferedWriter writer = new BufferedWriter(new FileWriter(out_filename));
    boolean streamHasInput = true;

    String currentToken = "";
    boolean escaping    = false;
    boolean sl_comment  = false; // single line
    boolean ml_comment  = false; // multi line
    boolean comment     = sl_comment || ml_comment;
    char last_char;
    char c =' ';
    String last_two = "";
    boolean last_was_two = false;
    boolean number = false;
    while (streamHasInput) {
      boolean two_flag = false;
      boolean number_flag = false;
      boolean enter_2nd_clause = true;
      last_char = c;
      c = (char)reader.read();
      last_two = last_char + "" + c;

      if (c == (char)(-1))
        break;

      if(!escaping && !comment){
        if (last_two.equals("//")){
          sl_comment = true;
          enter_2nd_clause =false;
          currentToken = "";
        } else if (last_two.equals("/*") ){
          ml_comment = true;
          enter_2nd_clause =false;
          currentToken = "";

        } else if (include_in_frsts(last_char)){ // need to look to the second char

          if(include_in_twos(last_two)){
            writer.write(getType(last_two) + "\t" + last_two + "\n");
            enter_2nd_clause = false;
            currentToken = "";
            two_flag = true;
          } else {
            if (DELIM_FIRST_CHARS.indexOf(last_char) != -1 && !last_was_two)
              writer.write(getType(last_char+"") + "\t" + last_char + "\n");
            enter_2nd_clause = true;
          }

        } else if (include_in_frsts(c)){ // need to look to the second char
          if(!currentToken.trim().isEmpty()){
            writer.write(getType(currentToken) + "\t" + currentToken + "\n");
            currentToken = "";
          }
          enter_2nd_clause = false;
        }
      }
       
       if(enter_2nd_clause){
         if(c == '.' && !comment && !escaping && !number){
           if(currentToken.matches("[0-9]+"))
             currentToken += '.';
           else{
             writer.write(getType(currentToken) + "\t" + currentToken + "\n");
             writer.write(getType(c +"") + "\t" + c + "\n");
             currentToken = "";
           }

         } else if (DELIM_SUPER.indexOf(c) != -1 && !escaping && !comment) {
           if (!currentToken.trim().isEmpty())
             writer.write(getType(currentToken) + "\t" + currentToken + "\n");
           writer.write(getType(c + "") + "\t" + c + "\n");
           currentToken = "";

         } else if (Character.isWhitespace(c) && !escaping && !comment) {
           if (!currentToken.trim().isEmpty())
             writer.write(getType(currentToken) + "\t" + currentToken + "\n");
           currentToken = "";

         } else if ("\"'\\".indexOf(c) != -1 && !comment) {
           currentToken += c;
           escaping = !escaping;

         } else if(sl_comment && (c == '\n' || c == '\r')) {
           sl_comment = false;

         } else if(ml_comment && (last_two.equals("*/"))) {
           ml_comment = false;

         } else if(comment){
           currentToken = "";

         } else if("0123456789".indexOf(c) != -1 && currentToken.length() == 0
                   && !escaping && !comment) {
           number_flag = true;
           currentToken = c + "";
         } else if(number && "0123456789.".indexOf(c) != -1 && !escaping
                   && !comment) {
           number_flag = true;
           currentToken += c;
         } else if(number && !escaping && !comment) {
           writer.write(getType(currentToken + "") + "\t" + currentToken + "\n");
           writer.write(getType(c + "") + "\t" + c + "\n");
           currentToken = "";
         } else {
           currentToken += c;
         }
       } 
       comment = sl_comment || ml_comment;
       last_was_two = two_flag;
       number = number_flag;
    }
    reader.close();
    writer.close();
  }

}
