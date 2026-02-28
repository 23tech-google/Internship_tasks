import java.util.*;

class SmartAIChatbot {

    static String userName = "";
    static int conversationCount = 0;
    static List<String> memory = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("🤖 Smart AI Chatbot Activated!");
        System.out.print("Bot: Hi! What is your name? ");
        userName = sc.nextLine();

        System.out.println("Bot: Nice to meet you, " + userName + " 😊");
        System.out.println("Bot: Type 'exit' anytime to quit.\n");

        while (true) {

            System.out.print(userName + ": ");
            String input = sc.nextLine().toLowerCase();
            conversationCount++;

            if (input.equals("exit")) {
                System.out.println("Bot: Goodbye " + userName + "! We had "
                        + conversationCount + " conversations today!");
                break;
            }

            memory.add(input);

            if (input.contains("hi") || input.contains("hello")) {
                System.out.println("Bot: Hello " + userName + "! 😊");
            }

            else if (input.contains("sad") || input.contains("upset")) {
                System.out.println("Bot: I'm sorry you're feeling sad 😔. Want to talk about it?");
            }

            else if (input.contains("happy") || input.contains("excited")) {
                System.out.println("Bot: That's amazing! I love your positive energy 🎉");
            }

            else if (input.contains("what did i say")) {
                if (memory.size() > 1) {
                    System.out.println("Bot: Earlier you said -> "
                            + memory.get(memory.size() - 2));
                } else {
                    System.out.println("Bot: This is just the beginning of our chat!");
                }
            }

            else if (input.startsWith("calculate")) {
                try {
                    String expr = input.replace("calculate", "").trim();
                    String[] parts = expr.split(" ");

                    double num1 = Double.parseDouble(parts[0]);
                    String op = parts[1];
                    double num2 = Double.parseDouble(parts[2]);

                    double result = 0;

                    if (op.equals("+")) result = num1 + num2;
                    else if (op.equals("-")) result = num1 - num2;
                    else if (op.equals("*")) result = num1 * num2;
                    else if (op.equals("/")) result = num1 / num2;
                    else {
                        System.out.println("Bot: Unsupported operator!");
                        continue;
                    }

                    System.out.println("Bot: Result = " + result);

                } catch (Exception e) {
                    System.out.println("Bot: Please use format: calculate 5 + 3");
                }
            }

            else {
                String[] responses = {
                        "Interesting... tell me more!",
                        "Why do you think that?",
                        "Hmm 🤔 that's something to think about.",
                        "I like how you think!",
                        "Can you explain that differently?"
                };

                int randomIndex = rand.nextInt(responses.length);
                System.out.println("Bot: " + responses[randomIndex]);
            }
        }

        sc.close();
    }
}