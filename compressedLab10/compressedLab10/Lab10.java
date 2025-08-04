import java.io.*;
import java.util.*;

public class Lab10 {
	private static ArrayList<Student> students = new ArrayList<>();
	private static ArrayList<Trainer> trainers = new ArrayList<>();
	static int Tcount = 0;
	static int Scount = 0;
	    
	public static void printMainMenu () {
		System.out.println("\n----- Main Menu -----");
		System.out.println("[1] Add Trainer\n"
				+ "[2] Add Student\n"
				+ "[3] Change Student Level\n"
				+ "[4] Change Trainer Level\n"
				+ "[5] List All Trainers\n"
				+ "[6] List All Students\n"
				+ "[7] Save & Exit");
        System.out.print("Enter your choice: ");
	}
	 
	public static void readTrainersFile (File file) throws IOException {
		if (!file.exists()) {
			System.out.println("File does not exist! Creating new one...");
			file.createNewFile();
		} else {
			try (Scanner outFile = new Scanner(new FileReader(file))) {
				while (outFile.hasNextLine()) {
					String line = outFile.nextLine();
					String[] fields = line.split("\t");
					
					if (fields.length < 3 || fields.length > 6)
						System.out.println("Invalid line format: " + line);
					else {
						trainers.add(new Trainer(fields[0], fields[1], fields[2]));
						Tcount++;
					}
				} 
				outFile.close();
			}
		}
	}
	
	public static void readStudentsFile (File file) throws IOException {
		if (!file.exists()) {
			System.out.println("File does not exist! Creating new one...");
			file.createNewFile();
		} else {
			try (Scanner outFile = new Scanner(new FileReader(file))) {
				while (outFile.hasNextLine()) {
					String line = outFile.nextLine();
					String[] fields = line.split("\t");
					
					if (fields.length < 4 || fields.length > 7)
						System.out.println("Invalid line format: " + line);
					else {
						String name = fields[0];
	                    String address = fields[1];
	                    int age = Integer.parseInt(fields[2]);
	                    String level = fields[3];
	                    
	                    if (level.equalsIgnoreCase("Intermediate")) {
	                        double freestyle50m = Double.parseDouble(fields[4]);
	                        double backstroke25m = Double.parseDouble(fields[5]);
	                        students.add(new IntermediateStudent(name, address, age, freestyle50m, backstroke25m));
	                    } else if (level.equalsIgnoreCase("Advanced")) {
	                        double freestyle100m = Double.parseDouble(fields[4]);
	                        double backstroke50m = Double.parseDouble(fields[5]);
	                        double breaststroke25m = Double.parseDouble(fields[6]);
	                        students.add(new AdvancedStudent(name, address, age, freestyle100m, backstroke50m, breaststroke25m));
	                    } else {
	                        students.add(new Student(name, address, age, level));
	                    }
	                    
	                    Scount++;
					}
				} 
				outFile.close();
			}
		}
	}
	
	public static void writeFile(File trainersFile, File studentsFile) throws IOException {
	    // Write trainers to file
	    try (PrintWriter tOutFile = new PrintWriter(new FileWriter(trainersFile))) {
	        for (Trainer trainer : trainers) {
	            tOutFile.println(trainer.getName() + "\t" + trainer.getAddress() + "\t" + trainer.getTeachingLevel());
	        }
	        tOutFile.close();
	        System.out.println("Trainers data recorded.");
	    } catch (IOException e) {
	        System.out.println("Error writing trainers file: " + e.getMessage());
	    }

	    // Write students to file
	    try (PrintWriter sOutFile = new PrintWriter(new FileWriter(studentsFile))) {
	        for (Student student : students) {
	            sOutFile.print(student.getName() + "\t" + student.getAddress() + "\t" + student.getAge() + "\t" + student.getLevel());

	            // Check for swim level and include specific timings
	            if (student instanceof IntermediateStudent) {
	                IntermediateStudent intermediateStudent = (IntermediateStudent) student;
	                sOutFile.print("\t" + intermediateStudent.getFreestyle50m() + "\t" + intermediateStudent.getBackstroke25m());
	            } else if (student instanceof AdvancedStudent) {
	                AdvancedStudent advancedStudent = (AdvancedStudent) student;
	                sOutFile.print("\t" + advancedStudent.getFreestyle100m() + "\t" + advancedStudent.getBackstroke50m() + "\t" + advancedStudent.getBreaststroke25m());
	            }

	            sOutFile.println(); // Move to next line for the next student
	        }
	        sOutFile.close();
	        System.out.println("Students data recorded.");
	    } catch (IOException e) {
	        System.out.println("Error writing students file: " + e.getMessage());
	    }
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner (System.in);
		File trainersFile = new File ("trainers.txt");
		File studentsFile = new File ("students.txt");
		
		int main;
	
		readTrainersFile(trainersFile);
		readStudentsFile(studentsFile);

		do {
			printMainMenu ();
			main = sc.nextInt();
			
			switch (main) {
			case 1:
				addTrainer(sc); break;
				
			case 2:
				addStudent(sc); break;
				
			case 3:
				changeStudentLevel(sc); break;
				
			case 4:
				changeTrainerLevel(sc); break;
				
			case 5, 6:
				listAll(main); break;
				
			case 7:
				System.out.println();
				writeFile(trainersFile, studentsFile); break;
				
			default:
				System.out.println("Invalid choice. Please try again."); break;
			}
			
		} while (main != 7);
	}

	public static void addTrainer (Scanner sc) {
		System.out.print("\nTrainer name: ");
        sc.nextLine(); 
        String name = sc.nextLine();
        System.out.print("Trainer address: ");
        String address = sc.nextLine();
        System.out.print("Trainer teaching level (Beginners, Intermediate, Advanced): ");
        String level = sc.nextLine();

        trainers.add(new Trainer(name, address, level));
        System.out.println("\nTrainer added successfully.");
        System.out.println(trainers.get(Tcount).toString());
        Tcount++;
	}
	
	public static void addStudent (Scanner sc) {
        System.out.println();

		System.out.print("Student name: ");
        sc.nextLine(); 
        String name = sc.nextLine();
        System.out.print("Student address: ");
        String address = sc.nextLine();
        System.out.print("Enter student age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Student swim level (Beginners, Intermediate, Advanced): ");
        String level = sc.nextLine();

        if (level.equalsIgnoreCase("Intermediate")) {
            System.out.print("Enter timing for 50 meters freestyle: ");
            double freestyle50m = sc.nextDouble();
            System.out.print("Enter timing for 25 meters backstroke: ");
            double backstroke25m = sc.nextDouble();
            sc.nextLine(); 
            students.add(new IntermediateStudent(name, address, age, freestyle50m, backstroke25m));
        
        } else if (level.equalsIgnoreCase("Advanced")) {
            System.out.print("Enter timing for 100 meters freestyle: ");
            double freestyle100m = sc.nextDouble();
            System.out.print("Enter timing for 50 meters backstroke: ");
            double backstroke50m = sc.nextDouble();
            System.out.print("Enter timing for 25 meters breaststroke: ");
            double breaststroke25m = sc.nextDouble();
            sc.nextLine(); 
            students.add(new AdvancedStudent(name, address, age, freestyle100m, backstroke50m, breaststroke25m));
        } else
        	students.add(new Student(name, address, age, level));
        
        System.out.println("\nStudent added successfully.");
        System.out.println(students.get(Scount).toString());
        
        assignTrainer(students.get(Scount));
        Scount++;
	}
	
	public static void assignTrainer(Student student) {
	    for (int i=0; i<Tcount; i++) {
	        if (trainers.get(i).getTeachingLevel().equalsIgnoreCase(student.getLevel())) {
	            System.out.println("Assigned Trainer: " + trainers.get(i).getName() + " for student: " + student.getName());
	            return;
	        }
	    }
	    System.out.println("No trainer available for this level.");
	}

	public static void changeStudentLevel(Scanner sc) {
		System.out.print("Enter student's name: ");
		sc.nextLine();
        String name = sc.nextLine();
        
        int i;
        for (i=0; i<Scount; i++) {
        	if (students.get(i).getName().equals(name)) {
            	System.out.print("Enter new level (Beginners, Intermediate, Advanced): ");
                String level = sc.nextLine();
                
                students.get(i).setLevel(level);
                System.out.println("Student level changed successfully.");
                System.out.println(students.get(i).toString());
                break;
        	}
        }
        if (i >= Scount)
			System.out.println("No student found.");
    }

    public static void changeTrainerLevel(Scanner sc) {
    	System.out.print("\nEnter trainer's name: ");
    	sc.nextLine();
        String name = sc.nextLine();
        
        int i;
        for (i=0; i<Tcount; i++) {
        	if (trainers.get(i).getName().equals(name)) {
            	System.out.print("Enter trainer's new teaching level (Beginners, Intermediate, Advanced): ");
            	String level = sc.nextLine();
              
                trainers.get(i).setTeachingLevel(level);
                System.out.println("Trainer's teaching level changed successfully.");
                System.out.println(trainers.get(i).toString());
                break;
        	}
        }
        if (i >= Tcount)
			System.out.println("No trainer found.");
    }
    
    public static void listAll(int opt) {
    	if (opt == 5) {
    		System.out.println("\nAll trainers:");
	    	for (int i=0; i<Tcount; i++)
	            System.out.println(trainers.get(i).toString());
    	} else {
    		System.out.println("\nAll students:");
    		for (int i=0; i<Scount; i++)
	            System.out.println(students.get(i).toString());
    	}
    }
}
