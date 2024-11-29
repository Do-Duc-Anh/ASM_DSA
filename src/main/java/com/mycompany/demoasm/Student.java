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
            System.out.println("5. Sort Students by Bubble Sort");
            System.out.println("6. Sort Students by Quick Sort");
            System.out.println("7. Search Student by Linear Search");
            System.out.println("8. Search Student by Binary Search");
            System.out.println("9. Exit");
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
                    sortStudentsByScore(); // Bubble Sort
                    break;
                case 6:
                    sortStudentsByQuickSort(); // Quick Sort
                    break;
                case 7:
                    searchStudentById(); // Linear Search
                    break;
                case 8:
                    searchStudentByBinarySearch(); // Binary Search
                    break;
                case 9:
                    running = false;
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 9.");
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

            if (findStudentById(id) != null) {
                System.out.println("ID already exists. Please enter a unique ID.");
                continue;
            }

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
        System.out.println("Students sorted by Bubble Sort:");
        displayAllStudents();
    }

    private static void quickSort(ArrayList<Student> students, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(students, low, high);
            quickSort(students, low, pivotIndex - 1);
            quickSort(students, pivotIndex + 1, high);
        }
    }

    private static int partition(ArrayList<Student> students, int low, int high) {
        Student pivot = students.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (students.get(j).getScore() > pivot.getScore()) {
                i++;
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }

        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);

        return i + 1;
    }

    private static void sortStudentsByQuickSort() {
        if (students.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }
        quickSort(students, 0, students.size() - 1);
        System.out.println("Students sorted by Quick Sort:");
        displayAllStudents();
    }

    private static void searchStudentById() {
        System.out.print("Enter the ID of the student to search: ");
        int id = getValidInt();
        Student student = findStudentById(id);
        if (student != null) {
            System.out.println("Student found:");
            student.printInfo();
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    private static void searchStudentByBinarySearch() {
        if (students.isEmpty()) {
            System.out.println("No students to search.");
            return;
        }

        students.sort((s1, s2) -> Integer.compare(s1.getId(), s2.getId()));

        System.out.print("Enter the ID of the student to search: ");
        int id = getValidInt();
        int index = binarySearch(students, 0, students.size() - 1, id);

        if (index != -1) {
            System.out.println("Student found:");
            students.get(index).printInfo();
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    private static int binarySearch(ArrayList<Student> students, int low, int high, int targetId) {
        if (low <= high) {
            int mid = low + (high - low) / 2;
            if (students.get(mid).getId() == targetId) {
                return mid;
            }
            if (students.get(mid).getId() > targetId) {
                return binarySearch(students, low, mid - 1, targetId);
            } else {
                return binarySearch(students, mid + 1, high, targetId);
            }
        }
        return -1;
    }

    private static Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private static int getValidInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid integer: ");
                scanner.next();
            }
        }
    }

    private static String getValidString() {
        scanner.nextLine(); // Consume newline left-over
        return scanner.nextLine();
    }

    private static double getValidDouble(double min, double max) {
        while (true) {
            try {
                double value = scanner.nextDouble();
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.print("Invalid input. Please enter a value between " + min + " and " + max + ": ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
                scanner.next();
            }
        }
    }
}
