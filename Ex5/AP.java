
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;

import java_cup.runtime.Symbol;

public class AP {

    public static void main(String[] args) {

        String DESC = "";
        String inFile = "Sample.in";

        if (args.length > 1) {
            inFile = args[0];
        }

        try {
            FileInputStream fis = new FileInputStream(inFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            DataInputStream dis = new DataInputStream(bis);

            parser p = new parser(new Lexer(dis));
            Symbol res = p.parse();
            boolean value = ((Boolean)res.value).booleanValue();

            if(value)
                System.out.println("File: " + inFile + " parsed successfully.");
            else
                System.out.println("Error in parsing file: " + inFile);

            fis.close();
            bis.close();
            dis.close();

            PrintWriter writer = new PrintWriter("description.txt", "UTF-8");
            writer.println(parser.desc);
            writer.close();
            System.out.println("Wrote file: description.txt");
            System.out.println(parser.typesTable);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
