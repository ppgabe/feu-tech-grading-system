public class UndergraduateStudent extends Student {
    private static final long serialVersionUID = 1L;

    public UndergraduateStudent(String id, String name) {
        super(id, name);
    }

    @Override
    public String calculateFinalGrade() {
        double gwa = getGWA();

        if (gwa == 0.0 && courseGrades.isEmpty()) return "N/A";

        if (gwa == 4.0) return "Excellent";
        else if (gwa == 3.5) return "Superior";
        else if (gwa == 3.0) return "Very Good";
        else if (gwa == 2.5) return "Good";
        else if (gwa == 2.0) return "Satisfactory";
        else if (gwa == 1.5) return "Fair";
        else if (gwa == 1.0) return "Pass";
        else return "Failed";
    }
}