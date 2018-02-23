import java.io.*;
import java.util.*;
import java.net.URL;

class MainMethods {
	public static ArrayList<String> Split(String s, HashSet<Character> divider, boolean RemoveEmptyEntries) {
		s += " ";
		ArrayList<String> word = new ArrayList<String>();
		String subS = "";
		for (int i = 0; i < s.length(); i++)
			if (divider.contains(s.charAt(i))) {
				if (subS.length() != 0 || !RemoveEmptyEntries)
					word.add(subS);
				subS = "";
			}
			else
				subS += s.charAt(i);
		return word;
	}
	public static void Download(String from, String to) throws IOException {
		URL url = new URL(from);
		BufferedInputStream Input = new BufferedInputStream(url.openStream());
		FileOutputStream Output = new FileOutputStream(to);
		byte[] buffer = new byte[1024];
		int count = -1;
		while ((count = Input.read(buffer)) != -1)
			Output.write(buffer, 0, count);
		Input.close();
		Output.close();
	}
}

public class Program {
	static final HashSet<String> color = new HashSet<String>(Arrays.asList("red", "green", "blue", "yellow", "white", "black", "brown", "grey", "красный", "синий", "зеленый", "желтый", "белый", "черный", "коричневый", "серый"));
	public static void main(String[] args) throws IOException {
		MainMethods.Download(args[0], args[1]);
		BufferedReader Input = new BufferedReader(new FileReader(args[1]));
		BufferedWriter Output = new BufferedWriter(new FileWriter("output.txt"));
		String s = "";
		while ((s = Input.readLine()) != null) {
			ArrayList<String> word = MainMethods.Split(s, new HashSet<Character>(Arrays.asList(' ', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '[', '{', ']', '}', ';', ':', '\'', '\"', '|', '\\', ',', '<', '.', '>', '/', '?')), true);
			for (int i = 0; i < word.size(); i++)
				if (color.contains(word.get(i).toLowerCase()))
					Output.write(word.get(i) + " ", 0, word.get(i).length() + 1);
		}
		Input.close();
		Output.close();
	}
}
