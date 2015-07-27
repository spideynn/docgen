import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {

		try {

			BufferedReader txtfile = new BufferedReader(new FileReader(""));
			OutputStream htmlfile = new FileOutputStream(new File(""));
			PrintStream printhtml = new PrintStream(htmlfile);

			String[] txtbyLine = new String[500];
			String temp = "";
			String txtfiledata = "";

			String htmlheader = "<html><head>";
			htmlheader += "<title>Equivalent HTML</title>";
			htmlheader += "</head><body>";
			String htmlfooter = "</body></html>";
			int linenum = 0;

			while ((txtfiledata = txtfile.readLine()) != null) {
				txtbyLine[linenum] = txtfiledata;
				linenum++;
			}
			for (int i = 0; i < linenum; i++) {
				if (i == 0) {
					temp = htmlheader + txtbyLine[0];
					txtbyLine[0] = temp;
				}
				if (linenum == i + 1) {
					temp = txtbyLine[i] + htmlfooter;
					txtbyLine[i] = temp;
				}
				printhtml.println(txtbyLine[i]);
			}

			printhtml.close();
			htmlfile.close();
			txtfile.close();

		}

		catch (Exception e) {
		}
	}
	
	private static String txtToHtml(String s) {
        StringBuilder builder = new StringBuilder();
        boolean previousWasASpace = false;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                if (previousWasASpace) {
                    builder.append("&nbsp;");
                    previousWasASpace = false;
                    continue;
                }
                previousWasASpace = true;
            } else {
                previousWasASpace = false;
            }
            switch (c) {
                case '<':
                    builder.append("&lt;");
                    break;
                case '>':
                    builder.append("&gt;");
                    break;
                case '&':
                    builder.append("&amp;");
                    break;
                case '"':
                    builder.append("&quot;");
                    break;
                case '\n':
                    builder.append("<br>");
                    break;
                // We need Tab support here, because we print StackTraces as HTML
                case '\t':
                    builder.append("&nbsp; &nbsp; &nbsp;");
                    break;
                default:
                    builder.append(c);

            }
        }
        String converted = builder.toString();
        String str = "(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:\'\".,<>?«»“”‘’]))";
        Pattern patt = Pattern.compile(str);
        Matcher matcher = patt.matcher(converted);
        converted = matcher.replaceAll("<a href=\"$1\">$1</a>");
        return converted;
    }
}