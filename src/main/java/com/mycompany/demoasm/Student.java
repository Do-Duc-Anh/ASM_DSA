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
    private int id;          // Student ID
    private String name;     // Student name
    private double score;    // Student score

    // Constructor to initialize the Student object with ID, name, and score
    public Student(int id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    // Getter and Setter methods for the attributes
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

    /**
     * Method to return the rank of the student based on their score.
     */
    public String getRank() {
        if (score < 5.0) return "Fail";          // Score below 5 is "Fail"
        else if (score < 6.5) return "Medium";   // Score between 5.0 and below 6.5 is "Medium"
        else if (score < 7.5) return "Good";     // Score between 6.5 and below 7.5 is "Good"
        else if (score < 9.0) return "Very Good"; // Score between 7.5 and below 9.0 is "Very Good"
        else return "Excellent";                 // Score above 9 is "Excellent"
    }

    /**
     * Method to print the student's information to the console.
     */
    public void printInfo() {
        System.out.println("ID: " + id + ", Name: " + name + ", Score: " + score + ", Rank: " + getRank());
    }

    // List of students
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in); // Scanner for input

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

            // Get the user's choice
            int choice = getValidInt();
            switch (choice) {
                case 1:
                    addStudent(); // Add a new student
                    break;
                case 2:
                    editStudent(); // Edit a student's information
                    break;
                case 3:
                    deleteStudent(); // Delete a student
                    break;
                case 4:
                    displayAllStudents(); // Display all students
                    break;
                case 5:
                    sortStudentsByScore(); // Sort students by score (Bubble Sort)
                    break;
                case 6:
                    sortStudentsByQuickSort(); // Sort students by score (Quick Sort)
                    break;
                case 7:
                    searchStudentById(); // Search for a student by ID (Linear Search)
                    break;
                case 8:
                    searchStudentByBinarySearch(); // Search for a student by ID (Binary Search)
                    break;
                case 9:
                    running = false; // Exit the program
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 9.");
                    break;
            }
        }
        scanner.close(); // Close scanner when done
    }

    /**
     * Method to add a new student to the list.
     */
    private static void addStudent() {
        int id;
        String name;
        double score;

        // Loop until a valid student is added
        while (true) {
            System.out.print("Enter ID: ");
            id = getValidInt();

            // Check if the ID already exists
            if (findStudentById(id) != null) {
                System.out.println("ID already exists. Please enter a unique ID.");
                continue;
            }

            System.out.print("Enter Name: ");
            name = getValidString();

            System.out.print("Enter Score: ");
            score = getValidDouble(0.0, 10.0);

            // Confirm student details before adding
            System.out.printf("\nConfirm details:\nID: %d, Name: %s, Score: %.2f\n", id, name, score);
            System.out.print("Is this correct? (y/n): ");
            String confirmation = getValidString();

            if (confirmation.equalsIgnoreCase("y")) {
                students.add(new Student(id, name, score)); // Add the student to the list
                System.out.println("Student added successfully.");
                break;
            } else {
                System.out.println("Let's try again.");
            }
        }
    }

    /**
     * Method to edit a student's information.
     */
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

                // Confirm changes to the student's information
                System.out.printf("\nConfirm changes:\nNew Name: %s, New Score: %.2f\n", name, score);
                System.out.print("Is this correct? (y/n): ");
                String confirmation = getValidString();

                if (confirmation.equalsIgnoreCase("y")) {
                    student.setName(name); // Update name
                    student.setScore(score); // Update score
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

    /**
     * Method to delete a student from the list.
     */
    private static void deleteStudent() {
        while (true) {
            System.out.print("Enter the ID of the student to delete: ");
            int id = getValidInt();
            Student student = findStudentById(id);
            if (student != null) {
                System.out.printf("Are you sure you want to delete student ID %d? (y/n): ", id);
                String confirmation = getValidString();
                if (confirmation.equalsIgnoreCase("y")) {
                    students.remove(student); // Remove student from the list
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

    /**
     * Method to display all students in the list.
     */
    private static void displayAllStudents() {
        System.out.println("\nStudent List:");
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : students) {
                student.printInfo(); // Print each student's info
            }
        }
    }

    /**
     * Method to sort students by score using Bubble Sort.
     */
    private static void sortStudentsByScore() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (students.get(j).getScore() < students.get(j + 1).getScore()) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp); // Swap students
                }
            }
        }
        System.out.println("Students sorted by Bubble Sort:");
        displayAllStudents(); // Display students after sorting
    }

    /**
     * Method for QuickSort (sorting by score).
     */
    private static void quickSort(ArrayList<Student> students, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(students, low, high); // Partition and get pivot index
            quickSort(students, low, pivotIndex - 1); // Sort left side
            quickSort(students, pivotIndex + 1, high); // Sort right side
        }
    }

    /**
     * Partition method for QuickSort.
     */
    private static int partition(ArrayList<Student> students, int low, int high) {
        Student pivot = students.get(high); // Choose the last element as pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (students.get(j).getScore() > pivot.getScore()) {
                i++;
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp); // Swap if student j's score is greater than pivot's score
            }
        }

        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high)); // Move pivot to the correct position
        students.set(high, temp);

        return i + 1;
    }

    /**
     * Method to sort students by QuickSort.
     */
    private static void sortStudentsByQuickSort() {
        if (students.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }
        quickSort(students, 0, students.size() - 1); // Call QuickSort method
        System.out.println("Students sorted by Quick Sort:");
        displayAllStudents(); // Display students after sorting
    }

    /**
     * Method to search for a student by ID using Linear Search.
     */
    private static void searchStudentById() {
        System.out.print("Enter the ID to search: ");
        int id = getValidInt();
        Student student = findStudentById(id);
        if (student != null) {
            student.printInfo(); // Print student's information
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    /**
     * Method to search for a student by ID using Binary Search.
     */
    private static void searchStudentByBinarySearch() {
        if (students.isEmpty()) {
            System.out.println("No students to search.");
            return;
        }
        students.sort((s1, s2) -> Integer.compare(s1.getId(), s2.getId())); // Sort students by ID for binary search
        System.out.print("Enter the ID to search: ");
        int id = getValidInt();
        int index = binarySearch(students, id);
        if (index != -1) {
            students.get(index).printInfo(); // Print student's information
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    /**
     * Binary search method.
     */
    private static int binarySearch(ArrayList<Student> students, int id) {
        int low = 0;
        int high = students.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (students.get(mid).getId() == id) {
                return mid; // Found student
            } else if (students.get(mid).getId() < id) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1; // Student not found
    }

    /**
     * Helper method to get a valid integer input.
     */
    private static int getValidInt() {
        while (true) {
            try {
                return scanner.nextInt(); // Read an integer
            } catch (InputMismatchException e) {
                scanner.next(); // Clear the invalid input
                System.out.print("Invalid input. Please enter an integer: ");
            }
        }
    }

    /**
     * Helper method to get a valid string input.
     */
    private static String getValidString() {
        return scanner.next(); // Read a string
    }

    /**
     * Helper method to get a valid double input within a given range.
     */
    private static double getValidDouble(double min, double max) {
        while (true) {
            try {
                double value = scanner.nextDouble();
                if (value >= min && value <= max) {
                    return value; // Valid score
                } else {
                    System.out.printf("Invalid input. Please enter a score between %.2f and %.2f: ", min, max);
                }
            } catch (InputMismatchException e) {
                scanner.next(); // Clear the invalid input
                System.out.printf("Invalid input. Please enter a score between %.2f and %.2f: ", min, max);
            }
        }
    }

    /**
     * Helper method to find a student by ID.
     */
    private static Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student; // Return student if found
            }
        }
        return null; // Return null if not found
    }
}