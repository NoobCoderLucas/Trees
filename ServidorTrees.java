import java.util.Scanner;

public class ServidorTrees {
    static class StudentNode {
        String name;
        int grade;
        StudentNode left, right;

        public StudentNode(String name, int grade) {
            this.name = name.toLowerCase();  
            this.grade = grade;
            left = right = null;
        }
    }

    static class StudentTree {
        StudentNode root;

        public StudentTree() {
            root = null;
        }

        void insert(String name, int grade) {
            root = insertRec(root, name.toLowerCase(), grade);
        }

        StudentNode insertRec(StudentNode root, String name, int grade) {
            if (root == null) {
                root = new StudentNode(name, grade);
                return root;
            }
            if (name.compareTo(root.name) < 0) {
                root.left = insertRec(root.left, name, grade);
            } else if (name.compareTo(root.name) > 0) {
                root.right = insertRec(root.right, name, grade);
            }
            return root;
        }

        void delete(String name) {
            root = deleteRec(root, name.toLowerCase());  
        }

        StudentNode deleteRec(StudentNode root, String name) {
            if (root == null) {
                return root;
            }
            if (name.compareTo(root.name) < 0) {
                root.left = deleteRec(root.left, name);
            } else if (name.compareTo(root.name) > 0) {
                root.right = deleteRec(root.right, name);
            } else {
                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                }

                root.name = minValue(root.right);
                root.right = deleteRec(root.right, root.name);
            }
            return root;
        }

        String minValue(StudentNode root) {
            String minValue = root.name;
            while (root.left != null) {
                minValue = root.left.name;
                root = root.left;
            }
            return minValue;
        }

        void display() {
            inorder(root);
        }

        void inorder(StudentNode root) {
            if (root != null) {
                inorder(root.left);
                String result = (root.grade < 75) ? "Fail" : "Pass";
                System.out.println("| " + root.name + " | " + root.grade + " | " + result + " |");
                inorder(root.right);
            }
        }

        void searchAndDisplay(String name) {
            searchAndDisplayRec(root, name.trim().toLowerCase());  
        }

        void searchAndDisplayRec(StudentNode root, String name) {
            if (root == null) {
                System.out.println("Student " + name + " not found.");
                return;
            }
            if (root.name.equals(name)) {
                String result = (root.grade < 75) ? "Fail" : "Pass";
                System.out.println("| " + root.name + " | " + root.grade + " | " + result + " |");
                return;
            }
            if (name.compareTo(root.name) < 0) {
                searchAndDisplayRec(root.left, name);
            } else {
                searchAndDisplayRec(root.right, name);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        StudentTree tree = new StudentTree();

        System.out.println("Student Grading System");
        while (true) {
            System.out.println("\n1. Add student and grade");
            System.out.println("2. Search for student's grade");
            System.out.println("3. Display all students and grades");
            System.out.println("4. Delete student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();  

            switch (choice) {
                case 1:
                    System.out.print("Enter student's name: ");
                    String name = scan.nextLine();
                    System.out.print("Enter grade for " + name + ": ");
                    int grade = scan.nextInt();
                    tree.insert(name, grade);
                    break;
                case 2:
                    System.out.print("Enter student's name to search: ");
                    String searchName = scan.nextLine();
                    tree.searchAndDisplay(searchName);  
                    break;
                case 3:
                    System.out.println("All students and grades: ");
                    System.out.println("| Name | Grade | Pass or Fail |");
                    tree.display();
                    break;
                case 4:
                    System.out.print("Enter student's name to delete: ");
                    String deleteName = scan.nextLine();
                    tree.delete(deleteName);
                    System.out.println("Student " + deleteName + " deleted (if found).");
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scan.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
