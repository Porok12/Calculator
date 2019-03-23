package Kalkulator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Vector;

public class Calculator {
	public static int priority(char ch) {
		switch (ch) {
		case '+':
			return 1;
		case '-':
			return 1;
		case '*':
			return 2;
		case '/':
			return 2;
		default:
			return 0;
		}
	}
	
	public static float getResult(float a, float b, char ch) {
		switch (ch) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			return a / b;
		default:
			return 0;
		}
	}
	
	public static String infixToOnp(String str) {
		Pattern pattern = Pattern.compile(
				//"([0-9]+[.,]?[0-9]*)|([+\\-*\\/)(])"  &&[^)(] %<s&&[^)(]
				String.format("(?:\\s*)([%s]?)(?:\\s*)(([+\\-]?\\d+[.,]?\\d*)|([)(]))", "+\\-\\*\\/)(")
				);
		String stack = "";
		String out = "";
		Matcher m = pattern.matcher(str);
		while(m.find()) {
			List<String> lista = new Vector<String>();
			if(m.group(1) != null && !m.group(1).isEmpty())
				lista.add(m.group(1));
			if(m.group(3) != null && !m.group(3).isEmpty())
				lista.add(m.group(3));
			if(m.group(4) != null && !m.group(4).isEmpty())
				lista.add(m.group(4));
			
			for(int j = 0; j < lista.size(); j++) {
				//System.out.println(lista.get(j));
				String ch = lista.get(j);
				
				if(ch.matches(String.format("([%s]?\\d+[.,]?\\d*)", "+\\-\\*\\/)("))) {
					out += String.format(" %s ", ch);
				} else {
					int i;
					switch (ch) {
					case "(":
						stack += "(";
						break;
					case ")":
						i = stack.length() - 1;
						while (stack.charAt(i) != '(') {
							//System.out.println("!"+stack.charAt(i));
							out += String.format(" %s ", stack.charAt(i--));
						}
						stack = stack.substring(0, i);
						break;
					case "+":
					case "-":
					case "*":
					case "/":
						i = stack.length() - 1;
						while ( i >= 0 && priority(stack.charAt(i)) >= priority(ch.charAt(0)) ) {
							out += String.format(" %s ", stack.charAt(i--));
						}
						stack = stack.substring(0, i+1);
						
						stack += String.format("%s", ch);
						break;
					default:
						break;
					}
				}
			}
			
		}
		
		int i = stack.length() - 1;
		while ( i >= 0 ) {
			out += String.format(" %s ", stack.charAt(i--));
		}
		
		return out.replaceAll("  ", " ");
	}
	
	public static float calc(String str) {
		str = infixToOnp(str);
		//System.out.println(str);
		List<Float> stack = new Vector<Float>();
		Pattern pattern = Pattern.compile("([^\\s]+)");
		Matcher match = pattern.matcher(str);
		
		while (match.find()) {
			//System.out.println(match.group());
			if(match.group().matches("([+\\-]?\\d+[,.]?\\d*)")) {
				stack.add(0, Float.parseFloat(match.group().replaceAll(",", ".")));
			} else {
				Float b = stack.get(0);
				Float a = stack.get(1);
				stack.remove(0);
				stack.remove(0);
				stack.add(0, getResult(a, b, match.group().charAt(0)));
			}
		}
		return stack.get(0);
	}
	
	public static String input() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter infix equation: ");
        String s = "";
		try {
			s = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return s;
	}
	
	public static void main(String [] args) {
		System.out.println(calc(input()));
	}
}
