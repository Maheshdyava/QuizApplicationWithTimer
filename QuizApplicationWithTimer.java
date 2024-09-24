



	import java.util.Scanner;
	import java.util.Timer;
	import java.util.TimerTask;

	class QuizQuestion {
	    String question;
	    String[] options;
	    int correctAnswer;

	    public QuizQuestion(String question, String[] options, int correctAnswer) {
	        this.question = question;
	        this.options = options;
	        this.correctAnswer = correctAnswer;
	    }
	}

	class Quiz {
	    private QuizQuestion[] questions;
	    private int score;
	    private int currentQuestionIndex = 0;
	    private boolean answered = false;
	    private Timer timer;

	    public Quiz(QuizQuestion[] questions) {
	        this.questions = questions;
	    }

	    public void start() {
	        Scanner scanner = new Scanner(System.in);
	        score = 0;

	        for (QuizQuestion question : questions) {
	            answered = false;
	            displayQuestion(question);
	            startTimer(scanner, question);
	        }

	        System.out.println("\nQuiz Finished!");
	        System.out.println("Your final score: " + score + "/" + questions.length);
	    }

	    private void displayQuestion(QuizQuestion question) {
	        System.out.println("\n" + question.question);
	        for (int i = 0; i < question.options.length; i++) {
	            System.out.println((i + 1) + ". " + question.options[i]);
	        }
	    }

	    private void startTimer(Scanner scanner, QuizQuestion question) {
	        timer = new Timer();
	        timer.schedule(new TimerTask() {
	            @Override
	            public void run() {
	                if (!answered) {
	                    System.out.println("\nTime's up! Moving to the next question.");
	                }
	                timer.cancel();
	            }
	        }, 10000);

	        System.out.print("Your answer (enter the option number): ");
	        if (scanner.hasNextInt()) {
	            int userAnswer = scanner.nextInt();
	            if (!answered) {
	                checkAnswer(userAnswer, question);
	            }
	            answered = true;
	            timer.cancel();
	        } else {
	            System.out.println("Invalid input. Moving to the next question.");
	            scanner.next();
	        }
	    }

	    private void checkAnswer(int userAnswer, QuizQuestion question) {
	        if (userAnswer == question.correctAnswer) {
	            score++;
	            System.out.println("Correct!");
	        } else {
	            System.out.println("Incorrect. The correct answer was: " + question.correctAnswer);
	        }
	    }
	}

	public class QuizApplicationWithTimer {
	    public static void main(String[] args) {
	        QuizQuestion[] questions = new QuizQuestion[] {
	            new QuizQuestion("What is the capital of France?",
	                new String[] {"Berlin", "Madrid", "Paris", "Rome"}, 3),
	            new QuizQuestion("Which planet is known as the Red Planet?",
	                new String[] {"Earth", "Mars", "Jupiter", "Venus"}, 2),
	            new QuizQuestion("Who wrote 'Romeo and Juliet'?",
	                new String[] {"Charles Dickens", "William Shakespeare", "J.K. Rowling", "Mark Twain"}, 2)
	        };

	        Quiz quiz = new Quiz(questions);
	        quiz.start();
	    }
	}


