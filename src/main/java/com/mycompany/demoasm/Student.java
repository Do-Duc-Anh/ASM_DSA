/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demoasm;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author LENOVO
 */
public class Student {
    private int id;
    private String name;
    private double score;

    public Student(int id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getRank() {
        if (score < 5.0) return "Fail";
        else if (score < 6.5) return "Medium";
        else if (score < 7.5) return "Good";
        else if (score < 9.0) return "Very Good";
        else return "Excellent";
    }

    public void printInfo() {
        System.out.println("ID: " + id + ", Name: " + name + ", Score: " + score + ", Rank: " + getRank());
    }

    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nOptions:");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Sort Students by Score");
            System.out.println("6. Search Student by ID");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = getValidInt();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    sortStudentsByScore();
                    break;
                case 6:
                    searchStudentById();
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                    break;
            }
        }
        scanner.close();
    }

    private static void addStudent() {
        int id;
        String name;
        double score;

        while (true) {
            System.out.print("Enter ID: ");
            id = getValidInt();

            System.out.print("Enter Name: ");
            name = getValidString();

            System.out.print("Enter Score: ");
            score = getValidDouble(0.0, 10.0);

            System.out.printf("\nConfirm details:\nID: %d, Name: %s, Score: %.2f\n", id, name, score);
            System.out.print("Is this correct? (y/n): ");
            String confirmation = getValidString();

            if (confirmation.equalsIgnoreCase("y")) {
                students.add(new Student(id, name, score));
                System.out.println("Student added successfully.");
                break;
            } else {
                System.out.println("Let's try again.");
            }
        }
    }

    private static void editStudent() {
        while (true) {
            System.out.print("Enter the ID of the student to edit: ");
            int id = getValidInt();
            Student student = findStudentById(id);
            if (student != null) {
                System.out.print("Enter new Name: ");
                String name = getValidString();

                System.out.print("Enter new Score: ");
                double score = getValidDouble(0.0, 10.0);

                System.out.printf("\nConfirm changes:\nNew Name: %s, New Score: %.2f\n", name, score);
                System.out.print("Is this correct? (y/n): ");
                String confirmation = getValidString();

                if (confirmation.equalsIgnoreCase("y")) {
                    student.setName(name);
                    student.setScore(score);
                    System.out.println("Student updated successfully.");
                    break;
                } else {
                    System.out.println("Let's try again.");
                }
            } else {
                System.out.println("Student with ID " + id + " not found. Please try again.");
            }
        }
    }

    private static void deleteStudent() {
        while (true) {
            System.out.print("Enter the ID of the student to delete: ");
            int id = getValidInt();
            Student student = findStudentById(id);
            if (student != null) {
                System.out.printf("Are you sure you want to delete student ID %d? (y/n): ", id);
                String confirmation = getValidString();
                if (confirmation.equalsIgnoreCase("y")) {
                    students.remove(student);
                    System.out.println("Student deleted successfully.");
                    break;
                } else {
                    System.out.println("Delete operation canceled.");
                    break;
                }
            } else {
                System.out.println("Student with ID " + id + " not found. Please try again.");
            }
        }
    }

    private static void displayAllStudents() {
        System.out.println("\nStudent List:");
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : students) {
                student.printInfo();
            }
        }
    }

    private static void sortStudentsByScore() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (students.get(j).getScore() < students.get(j + 1).getScore()) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        System.out.println("Students sorted by score:");
        displayAllStudents();
    }

    private static void searchStudentById() {
        while (true) {
            System.out.print("Enter the ID of the student to search: ");
            int id = getValidInt();
            Student student = findStudentById(id);
            if (student != null) {
                System.out.println("Student found:");
                student.printInfo();
                break;
            } else {
                System.out.println("Student with ID " + id + " not found. Please try again.");
            }
        }
    }

    private static Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Input validation methods
    private static int getValidInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid integer: ");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    private static String getValidString() {
        scanner.nextLine(); // Clear buffer
        while (true) {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.print("Invalid input. Please enter a valid string: ");
            }
        }
    }

    private static double getValidDouble(double min, double max) {
        while (true) {
            try {
                double value = scanner.nextDouble();
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.printf("Invalid input. Please enter a value between %.2f and %.2f: ", min, max);
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}
