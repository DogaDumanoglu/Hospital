import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ReadFiles {
    /*ReadFromFile function reads the lines from the file. Returns the list.
    *if a problem is encountered throws IOException.*/
    public static List<String> readFile(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) { 
            e.printStackTrace();
            return null;
            }
        }

    public static void readOperationInfos(OperationLogger operationLogger, PatientDao patients, AdmissionDao admissions, String path) throws IOException {
        /*This function reads input.txt and helps operations perform by given order*/
        List<String> lines = readFile(path);
        for (String line : Objects.requireNonNull(lines)) {
            String[] infosArray = line.split(" ");
            String operations=infosArray[0];
            int patientId;
            int admissionId;
            switch(operations){
                case "AddPatient":
                    String address="";
                    for (int i=5;i<infosArray.length;i++) {
                        address+=" "+infosArray[i];}
                    address="Address:"+address;
                    patientId=Integer.parseInt(infosArray[1]);
                    Patient patient = new Patient(patientId,infosArray[2]/*name*/,infosArray[3]/*surname*/,infosArray[4]/*phone*/,address.trim());
                    patients.addPatientIfNotExist(patientId, patient);
                    break;

                case"RemovePatient":
                    patientId=Integer.parseInt(infosArray[1]);
                    patients.removePatient(patientId);
                    break;

                case"CreateAdmission":
                    admissionId=Integer.parseInt(infosArray[1]);
                    patientId=Integer.parseInt(infosArray[2]);
                    admissions.addAdmission(admissionId, new Admission(admissionId, patientId));
                    operationLogger.writeLine("Admission " + admissionId + " created\n");
                    break;

                case"AddExamination":
                    admissionId=Integer.parseInt(infosArray[1]);
                    String[] operation=new String[infosArray.length - 3];
                    for (int i=3;i<infosArray.length;i++) {
                        operation[i-3] = infosArray[i];
                    }
                    ExaminationInterface exam = admissions.createExamination(infosArray[2], operation);
                    Admission admission = admissions.getAdmission(admissionId);
                    admission.addExamination(exam);
                    operationLogger.writeLine((exam instanceof Exam_Inpatient ? "Inpatient" : "Outpatient")+ " examination added to admission " + admissionId+"\n");
                    admissions.saveToFile();
                    break;

                case"TotalCost":
                    admissionId=Integer.parseInt(infosArray[1]);
                    admission = admissions.getAdmission(admissionId);
                    operationLogger.writeLine("TotalCost for admission " + admissionId);
                    operationLogger.writeLine("\n"+admission.toString());
                    operationLogger.writeLine("\tTotal: " + admission.cost() + "$\n");
                    break;

                case"ListPatients":
                    patients.printAllPatientsSorted();
                    break;
            }
        }
    }

}
