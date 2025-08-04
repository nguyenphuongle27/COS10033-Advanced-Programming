public class Student {
	private String name;
    private String address;
    private int age;
    private String swimLevel;
    
    public Student(String name, String address, int age, String level) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.swimLevel = level;
    }
    
    public String getName() { return name; }
    public String getAddress() { return address; }
    public int getAge() { return age; }
    public String getLevel() { return swimLevel; }
    
    public void setLevel(String Level) { this.swimLevel = Level; }
    
    public String toString () {
    	return  "Name: " + this.name + 
    			" | Address: " + this.address + 
    			" | Age: " + this.age +
    			" | Level: " + this.swimLevel;
    }
}

class IntermediateStudent extends Student {
    private double freestyle50m;
    private double backstroke25m;

    public IntermediateStudent(String name, String address, int age, double freestyle50m, double backstroke25m) {
        super(name, address, age, "Intermediate");
        this.freestyle50m = freestyle50m;
        this.backstroke25m = backstroke25m;
    }

    public double getFreestyle50m() {return freestyle50m; }
    public double getBackstroke25m() {return backstroke25m;}

    public String toString () {
    	return super.toString()
    			+"\n- 50m freestyle: " +this.freestyle50m+ " seconds\n"
    			+ "- 25m backstroke: " +this.backstroke25m+ " seconds";
    }
}

class AdvancedStudent extends Student {
    private double freestyle100m;
    private double backstroke50m;
    private double breaststroke25m;

    public AdvancedStudent(String name, String address, int age, double freestyle100m, double backstroke50m, double breaststroke25m) {
        super(name, address, age, "Advanced");
        this.freestyle100m = freestyle100m;
        this.backstroke50m = backstroke50m;
        this.breaststroke25m = breaststroke25m;
    }

    public double getFreestyle100m() { return freestyle100m; }
    public double getBackstroke50m() {return backstroke50m;}
    public double getBreaststroke25m() {return breaststroke25m;}
    
    public String toString () {
    	return super.toString()
    			+"\n- 100m freestyle: " +this.freestyle100m+ " seconds\n"
    			+ "- 50m backstroke: " +this.backstroke50m+ " seconds\n"
    			+ "- 25m breastsroke: " +this.breaststroke25m+ " seconds";
    }
}
