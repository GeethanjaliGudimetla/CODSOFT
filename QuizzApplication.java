import java.util.*;

class QuizApplication {
    // Question class
    private static class Question {
        private String question;
        private String[] options;
        private int correctAnswer;

        Question(String question, String[] options, int correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }

        // Getter methods to access private variables
        public String getQuestion() {
            return question;
        }

        public String[] getOptions() {
            return options;
        }

        public int getCorrectAnswer() {
            return correctAnswer;
        }
    }

    private static List<Question> questions = new ArrayList<>();
    private static int score = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static Timer timer;
    private static boolean answered;

    public static void main(String[] args) {
        // Add questions
        questions.add(new Question("What is the capital of France?", new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 3));
        questions.add(new Question("Which data structure uses LIFO?", new String[]{"1. Queue", "2. Stack", "3. Linked List", "4. Tree"}, 2));
        questions.add(new Question("What is 5 + 3?", new String[]{"1. 5", "2. 8", "3. 10", "4. 15"}, 2));

        // Start quiz
        startQuiz();
    }

    private static void startQuiz() {
        for (Question q : questions) {
            answered = false;
            displayQuestion(q);
            startTimer();

            int userAnswer = getUserAnswer();
            if (answered) {
                if (userAnswer == q.getCorrectAnswer()) {
                    score++;
                    System.out.println("Correct!\n");
                } else {
                    System.out.println("Wrong answer!\n");
                }
            } else {
                System.out.println("\nTime's up! Moving to the next question.\n");
            }
            timer.cancel();
        }
        // Show results
        displayResults();
    }

    private static void displayQuestion(Question q) {
        System.out.println(q.getQuestion());  // Use getter method
        for (String option : q.getOptions()) {  // Use getter method
            System.out.println(option);
        }
        System.out.print("Enter your choice (1-4): ");
    }

    private static void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!answered) {
                    System.out.println("\nTime's up!");
                    answered = true;
                }
            }
        }, 10000); // 10 seconds
    }

    private static int getUserAnswer() {
        try {
            int userAnswer = scanner.nextInt();
            answered = true;
            return userAnswer;
        } catch (Exception e) {
            return -1;
        }
    }

    private static void displayResults() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your final score: " + score + "/" + questions.size());
    }
}