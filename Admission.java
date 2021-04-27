import java.util.ArrayList;
public class Admission {
    /*This class adds patient in a admission then gives them examinations*/
    Integer id;
    Integer patientId;
    ArrayList<ExaminationInterface> examinations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public ArrayList<ExaminationInterface> getExaminations() {
        return examinations;
    }

    public void setExaminations(ArrayList<ExaminationInterface> examinations) {
        this.examinations = examinations;
    }


    public Admission(Integer admissionId, Integer patientNo) {
        this.examinations = new ArrayList<>();
        this.id = admissionId;
        this.patientId = patientNo;
    }
    public Integer cost() {
        /*This Function returns all costs*/
        int total = 0;
        for (ExaminationInterface exam : this.examinations) {
            total += exam.cost();
        }
        return total;
    }

    public void addExamination(ExaminationInterface exam) {
        /*This function for adding examination*/
        this.examinations.add(exam);
    }

    public String toString() {
        /*This function writes output file to examinations*/
        String output = "";
        for (ExaminationInterface exam: examinations) {
            output += "\t" + exam.toString() + exam.cost() + "$\n";
        }
        return output;
    }

}
