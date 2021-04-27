import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AdmissionDao{
    /*This class reads admission.txt and saves new infos on it*/
    HashMap<Integer, Admission> admissions = new HashMap<>();
    OperationLogger operationsLogger;

    public AdmissionDao(PatientDao patients,OperationLogger operationsLogger) {
        this.operationsLogger = operationsLogger;
    }

    public void addAdmission(Integer admissionId, Admission admission){
        admissions.put(admissionId, admission);
    }
    public void loadFromFile(String path){
        /*This function reads*/
        List<String> lines = ReadFiles.readFile(path);
        int admissionNo = 0;
        int patientNo = 0;
        for (String line : Objects.requireNonNull(lines)) {
            String[] infosArray = line.split("\t");
            try {
                admissionNo = Integer.parseInt(infosArray[0]);
                patientNo = Integer.parseInt(infosArray[1]);
                this.admissions.put(admissionNo, new Admission(admissionNo, patientNo));
            } catch (NumberFormatException ex) {
                Admission admission = this.admissions.get(admissionNo);
                ExaminationInterface exam = this.createExamination(infosArray[0], infosArray[1].split(" "));
                admission.addExamination(exam);
            }
        }
    }

    public void saveToFile() throws IOException {
        /*This function for uploading file when a change occurs*/
        try{
            FileWriter fw=new FileWriter("admission.txt");
            List<Admission> list=new ArrayList<Admission>(admissions.values());
            Collections.sort(list, new Comparator<Admission >() {
                @Override
                public int compare(Admission adm1, Admission adm2) {
                    return adm1.getId() - adm2.getId();
                }
            });

            for (Admission admission : list) {
                fw.write(admission.getId() + "\t" + admission.getPatientId() + "\n");
                for (ExaminationInterface exam : admission.getExaminations()) {
                    fw.write(exam.toStringWithTab() + "\n");
                }
            }
            fw.close();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

    }


    public ExaminationInterface createExamination(String examType, String[] operations) {
        /*This function creates examinations */
        ExaminationDecorator root = new ExaminationDecorator();
        ExaminationDecorator current = root;

        for (int i = 0 ; i < operations.length; i++) {
            ExaminationDecorator e = null;
            switch (operations[i]) {
                case "tests": e = new Operation_Tests(null);break;
                case "doctorvisit": e = new Operation_DoctorVisit(null);break;
                case "measurements": e = new Operation_Measurements(null);break;
                case "imaging": e = new Operation_Imaging(null);break;
                default:break;
            }
            current.addOperation(e);
            current = e;
        }

        if (examType.equals("Inpatient")) {
            return new Exam_Inpatient(root);
        }

        return  new Exam_Outpatient(root);
    }

    public Admission getAdmission(Integer admissionId) {
        /*This function returns admission id for sets patients to admission by admission id*/
        return this.admissions.get(admissionId);
    }

}
